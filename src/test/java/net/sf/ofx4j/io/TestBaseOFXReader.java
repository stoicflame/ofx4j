/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.ofx4j.io;

import junit.framework.TestCase;

import java.util.*;
import java.util.regex.Matcher;
import java.io.Reader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ryan Heaton
 */
public class TestBaseOFXReader extends TestCase {

  private static final Log LOG = LogFactory.getLog(TestBaseOFXReader.class);

  /**
   * test parsing.
   */
  public void testParse() throws Exception {
    final char[] firstCharsAfterHeader = new char["<OFX>".length()];
    final Map<String, String> headers = new HashMap<String, String>();
    BaseOFXReader reader = new BaseOFXReader() {
      public void parseV1FromFirstElement(Reader reader) throws IOException, OFXParseException {
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
    assertFalse(BaseOFXReader.OFX_2_PROCESSING_INSTRUCTION_PATTERN.matcher(xmlProlog).find());
    Matcher matcher = BaseOFXReader.OFX_2_PROCESSING_INSTRUCTION_PATTERN.matcher(xmlProlog + ofxpi);
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
    BaseOFXReader reader = new BaseOFXReader() {
      protected void parseV1FromFirstElement(Reader reader) throws IOException, OFXParseException {
        fail();
      }
    };
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
    BaseOFXReader reader = new BaseOFXReader() {
      protected void parseV1FromFirstElement(Reader reader) throws IOException, OFXParseException {
        fail();
      }
    };
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
  public void testVersion2() throws Exception {
    BaseOFXReader reader = new BaseOFXReader() {
      protected void parseV1FromFirstElement(Reader reader) throws IOException, OFXParseException {
        fail();
      }
    };
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
    reader.parse(BaseOFXReader.class.getResourceAsStream("example-response.ofx2"));
    assertEquals(5, headers.size());
    assertEquals(1, aggregateStack.size());
    assertSame(root, aggregateStack.pop());
  }
}
