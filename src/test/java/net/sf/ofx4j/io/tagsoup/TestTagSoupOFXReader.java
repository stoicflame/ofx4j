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
