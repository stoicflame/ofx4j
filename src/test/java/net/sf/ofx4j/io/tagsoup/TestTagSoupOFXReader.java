package net.sf.ofx4j.io.tagsoup;

import junit.framework.TestCase;
import net.sf.ofx4j.io.DefaultHandler;
import net.sf.ofx4j.io.OFXParseException;

import java.util.*;
import java.util.regex.Matcher;
import java.io.StringReader;
import java.io.Reader;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Unit test for simple App.
 */
public class TestTagSoupOFXReader extends TestCase {

  private static final Log LOG = LogFactory.getLog(TestTagSoupOFXReader.class);

  /**
   * test parsing.
   */
  public void testParse() throws Exception {
    final char[] firstCharsAfterHeader = new char["<OFX>".length()];
    final Map<String, String> headers = new HashMap<String, String>();
    TagSoupOFXReader reader = new TagSoupOFXReader() {
      @Override
      public void parseFromFirstElement(Reader reader) throws IOException, OFXParseException {
        reader.read(firstCharsAfterHeader);
      }
    };
    reader.setContentHandler(new DefaultHandler() {
      @Override
      public void onHeader(String name, String value) {
        headers.put(name, value);
      }
    });

    reader.parse(new StringReader("header1:value1\nheader2:value2\n\n<OFX>"));
    assertEquals(2, headers.size());
    assertEquals("value1", headers.get("header1"));
    assertEquals("value2", headers.get("header2"));
    assertEquals("<OFX>", new String(firstCharsAfterHeader));
  }

  /**
   * tests the regexmatch
   */
  public void testRegexMatch() throws Exception {
    String xmlProlog = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    String ofxpi = "\r\n<?OFX THIS IS A BUNCH OF DATA THAT IS SEPARATED BY WHITESPACE?>  \r\nAND SOME STUFF AFTER\r\n<?WITH ANOTHER PI?>";
    assertFalse(TagSoupOFXReader.OFX_2_PROCESSING_INSTRUCTION_PATTERN.matcher(xmlProlog).find());
    Matcher matcher = TagSoupOFXReader.OFX_2_PROCESSING_INSTRUCTION_PATTERN.matcher(xmlProlog + ofxpi);
    assertTrue(matcher.find());
    assertEquals("THIS IS A BUNCH OF DATA THAT IS SEPARATED BY WHITESPACE", matcher.group(1));
  }

  /**
   * test process OFX v1 headers.
   */
  public void testProcessOFXv1Headers() throws Exception {
    int headerCount = 8;
    StringBuilder v1headers = new StringBuilder();
    for (int i = 0; i < headerCount; i++) {
      v1headers.append("HEADER").append(i).append(":VALUE").append(i);
      if (i % 2 == 0) {
        v1headers.append('\r');
      }
      v1headers.append('\n');
    }
    TagSoupOFXReader reader = new TagSoupOFXReader();
    final Map<String, String> headers = new HashMap<String, String>();
    reader.setContentHandler(new DefaultHandler() {
      @Override
      public void onHeader(String name, String value) {
        headers.put(name, value);
      }
    });
    reader.processOFXv1Headers(v1headers.toString());
    assertEquals(headerCount, headers.size());
    for (int i = 0; i < headerCount; i++) {
      assertEquals("VALUE" + i, headers.get("HEADER" + i));
    }
  }

  /**
   * test process OFX v2 headers
   */
  public void testProcessOFXv2Headers() throws Exception {
    TagSoupOFXReader reader = new TagSoupOFXReader();
    final Map<String, String> headers = new HashMap<String, String>();
    reader.setContentHandler(new DefaultHandler() {
      @Override
      public void onHeader(String name, String value) {
        headers.put(name, value);
      }
    });
    reader.processOFXv2Headers("HEADER1=\"VALUE1\" HEADER2=\"VALUE2\" \r\nHEADER3=\"VALUE3\"\nHEADER4=\"VALUE4\" ");
    assertEquals(4, headers.size());
    assertEquals("VALUE1", headers.get("HEADER1"));
    assertEquals("VALUE2", headers.get("HEADER2"));
    assertEquals("VALUE3", headers.get("HEADER3"));
    assertEquals("VALUE4", headers.get("HEADER4"));
  }

  /**
   * tests using sax to parse an OFX doc.
   */
  public void testVersion1() throws Exception {
    TagSoupOFXReader reader = new TagSoupOFXReader();
    final Map<String, String> headers = new HashMap<String, String>();
    final Stack<Map<String, Object>> aggregateStack = new Stack<Map<String, Object>>();
    TreeMap<String, Object> root = new TreeMap<String, Object>();
    aggregateStack.push(root);

    reader.setContentHandler(new DefaultHandler() {

      @Override
      public void onHeader(String name, String value) {
        LOG.debug(name + ":" + value);
        headers.put(name, value);
      }

      @Override
      public void onElement(String name, String value) {
        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + name + "=" + value);

        aggregateStack.peek().put(name, value);
      }

      @Override
      public void startAggregate(String aggregateName) {
        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + aggregateName + " {");

        TreeMap<String, Object> aggregate = new TreeMap<String, Object>();
        aggregateStack.peek().put(aggregateName, aggregate);
        aggregateStack.push(aggregate);
      }

      @Override
      public void endAggregate(String aggregateName) {
        aggregateStack.pop();

        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + "}");
      }
    });
    reader.parse(TestTagSoupOFXReader.class.getResourceAsStream("example-response.ofx"));
    assertEquals(9, headers.size());
    assertEquals(1, aggregateStack.size());
    assertSame(root, aggregateStack.pop());
  }

  /**
   * tests using sax to parse an OFX doc.
   */
  public void testVersion2() throws Exception {
    TagSoupOFXReader reader = new TagSoupOFXReader();
    final Map<String, String> headers = new HashMap<String, String>();
    final Stack<Map<String, Object>> aggregateStack = new Stack<Map<String, Object>>();
    TreeMap<String, Object> root = new TreeMap<String, Object>();
    aggregateStack.push(root);

    reader.setContentHandler(new DefaultHandler() {

      @Override
      public void onHeader(String name, String value) {
        LOG.debug(name + ":" + value);
        headers.put(name, value);
      }

      @Override
      public void onElement(String name, String value) {
        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + name + "=" + value);

        aggregateStack.peek().put(name, value);
      }

      @Override
      public void startAggregate(String aggregateName) {
        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + aggregateName + " {");

        TreeMap<String, Object> aggregate = new TreeMap<String, Object>();
        aggregateStack.peek().put(aggregateName, aggregate);
        aggregateStack.push(aggregate);
      }

      @Override
      public void endAggregate(String aggregateName) {
        aggregateStack.pop();

        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + "}");
      }
    });
    reader.parse(TestTagSoupOFXReader.class.getResourceAsStream("example-response.ofx2"));
    assertEquals(5, headers.size());
    assertEquals(1, aggregateStack.size());
    assertSame(root, aggregateStack.pop());
  }

  /**
   * tests using sax to parse an OFX doc.
   */
  public void testSimpleVersion1() throws Exception {
    TagSoupOFXReader reader = new TagSoupOFXReader();
    final Map<String, String> headers = new HashMap<String, String>();
    final Stack<Map<String, Object>> aggregateStack = new Stack<Map<String, Object>>();
    TreeMap<String, Object> root = new TreeMap<String, Object>();
    aggregateStack.push(root);

    reader.setContentHandler(new DefaultHandler() {

      @Override
      public void onHeader(String name, String value) {
        LOG.debug(name + ":" + value);
        headers.put(name, value);
      }

      @Override
      public void onElement(String name, String value) {
        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + name + "=" + value);

        aggregateStack.peek().put(name, value);
      }

      @Override
      public void startAggregate(String aggregateName) {
        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + aggregateName + " {");

        TreeMap<String, Object> aggregate = new TreeMap<String, Object>();
        aggregateStack.peek().put(aggregateName, aggregate);
        aggregateStack.push(aggregate);
      }

      @Override
      public void endAggregate(String aggregateName) {
        aggregateStack.pop();

        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + "}");
      }
    });
    reader.parse(TestTagSoupOFXReader.class.getResourceAsStream("simple.ofx"));
    assertEquals(9, headers.size());
    assertEquals(1, aggregateStack.size());
    assertSame(root, aggregateStack.pop());
  }

}
