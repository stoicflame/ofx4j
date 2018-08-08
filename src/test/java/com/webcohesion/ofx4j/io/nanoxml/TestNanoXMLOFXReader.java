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

package com.webcohesion.ofx4j.io.nanoxml;

import junit.framework.TestCase;
import com.webcohesion.ofx4j.io.DefaultHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * @author Ryan Heaton
 */
public class TestNanoXMLOFXReader extends TestCase {

  private static final Log LOG = LogFactory.getLog(TestNanoXMLOFXReader.class);

  /**
   * tests using sax to parse an OFX doc.
   */
  public void testVersion1() throws Exception {
    NanoXMLOFXReader reader = new NanoXMLOFXReader();
    final Map<String, List<String>> headers = new HashMap<String, List<String>>();
    final Stack<Map<String, List<Object>>> aggregateStack = new Stack<Map<String, List<Object>>>();
    TreeMap<String, List<Object>> root = new TreeMap<String, List<Object>>();
    aggregateStack.push(root);

    reader.setContentHandler(getNewDefaultHandler(headers, aggregateStack));
    reader.parse(TestNanoXMLOFXReader.class.getResourceAsStream("example-response.ofx"));
    assertEquals(9, headers.size());
    assertEquals(1, aggregateStack.size());
    assertSame(root, aggregateStack.pop());


    TreeMap<String, List<Object>> OFX = (TreeMap<String, List<Object>>) root.remove("OFX").get(0);
    assertNotNull(OFX);

    TreeMap<String, List<Object>> SIGNONMSGSRSV1 = (TreeMap<String, List<Object>>) OFX.remove("SIGNONMSGSRSV1").get(0);
    assertNotNull(SIGNONMSGSRSV1);
    TreeMap<String, List<Object>> SONRS = (TreeMap<String, List<Object>>) SIGNONMSGSRSV1.remove("SONRS").get(0);
    assertNotNull(SONRS);
    TreeMap<String, List<Object>> STATUS = (TreeMap<String, List<Object>>) SONRS.remove("STATUS").get(0);
    assertNotNull(STATUS);
    assertEquals("0", STATUS.remove("CODE").get(0).toString().trim());
    assertEquals("INFO", STATUS.remove("SEVERITY").get(0).toString().trim());
    assertTrue(STATUS.isEmpty());
    assertEquals("20071015021529.000[-8:PST]", SONRS.remove("DTSERVER").get(0).toString().trim());
    assertEquals("ENG", SONRS.remove("LANGUAGE").get(0).toString().trim());
    assertEquals("19900101000000", SONRS.remove("DTACCTUP").get(0).toString().trim());
    TreeMap<String, List<Object>> FI = (TreeMap<String, List<Object>>) SONRS.remove("FI").get(0);
    assertEquals("Bank&Cd", FI.remove("ORG").get(0).toString().trim());
    assertEquals("01234", FI.remove("FID").get(0).toString().trim());
    assertTrue(FI.isEmpty());
    assertTrue(SONRS.isEmpty());
    assertTrue(SIGNONMSGSRSV1.isEmpty());

    TreeMap<String, List<Object>> BANKMSGSRSV1 = (TreeMap<String, List<Object>>) OFX.remove("BANKMSGSRSV1").get(0);
    TreeMap<String, List<Object>> STMTTRNRS = (TreeMap<String, List<Object>>) BANKMSGSRSV1.remove("STMTTRNRS").get(0);
    assertEquals("23382938", STMTTRNRS.remove("TRNUID").get(0).toString().trim());
    STATUS = (TreeMap<String, List<Object>>) STMTTRNRS.remove("STATUS").get(0);
    assertNotNull(STATUS);
    assertEquals("0", STATUS.remove("CODE").get(0).toString().trim());
    assertEquals("INFO", STATUS.remove("SEVERITY").get(0).toString().trim());
    assertTrue(STATUS.isEmpty());
    TreeMap<String, List<Object>> STMTRS = (TreeMap<String, List<Object>>) STMTTRNRS.remove("STMTRS").get(0);
    assertEquals("USD", STMTRS.remove("CURDEF").get(0).toString().trim());
    TreeMap<String, List<Object>> BANKACCTFROM = (TreeMap<String, List<Object>>) STMTRS.remove("BANKACCTFROM").get(0);
    assertEquals("SAVINGS", BANKACCTFROM.remove("ACCTTYPE").get(0).toString().trim());
    assertEquals("098-121", BANKACCTFROM.remove("ACCTID").get(0).toString().trim());
    assertEquals("987654321", BANKACCTFROM.remove("BANKID").get(0).toString().trim());
    assertTrue(BANKACCTFROM.isEmpty());
    TreeMap<String, List<Object>> BANKTRANLIST = (TreeMap<String, List<Object>>) STMTRS.remove("BANKTRANLIST").get(0);
    assertEquals("20070101", BANKTRANLIST.remove("DTSTART").get(0).toString().trim());
    assertEquals("20071015", BANKTRANLIST.remove("DTEND").get(0).toString().trim());
    TreeMap<String, List<Object>> STMTTRN = (TreeMap<String, List<Object>>) BANKTRANLIST.remove("STMTTRN").get(0);
    assertEquals("CREDIT", STMTTRN.remove("TRNTYPE").get(0).toString().trim());
    assertEquals("20070329", STMTTRN.remove("DTPOSTED").get(0).toString().trim());
    assertEquals("20070329", STMTTRN.remove("DTUSER").get(0).toString().trim());
    assertEquals("150.00", STMTTRN.remove("TRNAMT").get(0).toString().trim());
    assertEquals("980310001", STMTTRN.remove("FITID").get(0).toString().trim());
    assertEquals("TRANSFER", STMTTRN.remove("NAME").get(0).toString().trim());
    assertEquals("Transfer from checking &<> etc.", STMTTRN.remove("MEMO").get(0).toString().trim());
    assertTrue(STMTTRN.isEmpty());
    assertTrue(BANKTRANLIST.isEmpty());
    TreeMap<String, List<Object>> LEDGERBAL = (TreeMap<String, List<Object>>) STMTRS.remove("LEDGERBAL").get(0);
    assertEquals("5250.00", LEDGERBAL.remove("BALAMT").get(0).toString().trim());
    assertEquals("20071015021529.000[-8:PST]", LEDGERBAL.remove("DTASOF").get(0).toString().trim());
    assertTrue(LEDGERBAL.isEmpty());
    TreeMap<String, List<Object>> AVAILBAL = (TreeMap<String, List<Object>>) STMTRS.remove("AVAILBAL").get(0);
    assertEquals("5250.00", AVAILBAL.remove("BALAMT").get(0).toString().trim());
    assertEquals("20071015021529.000[-8:PST]", AVAILBAL.remove("DTASOF").get(0).toString().trim());
    assertTrue(AVAILBAL.isEmpty());
    assertTrue(LEDGERBAL.isEmpty());
    assertTrue(STMTRS.isEmpty());
    assertTrue(STMTTRNRS.isEmpty());
    assertTrue(BANKMSGSRSV1.isEmpty());

    assertTrue(OFX.isEmpty());
    assertTrue(root.isEmpty());
  }

  /**
   * tests using sax to parse an OFX doc.
   */
  public void testSimpleVersion1() throws Exception {
    NanoXMLOFXReader reader = new NanoXMLOFXReader();
    final Map<String, List<String>> headers = new HashMap<String, List<String>>();
    final Stack<Map<String, List<Object>>> aggregateStack = new Stack<Map<String, List<Object>>>();
    TreeMap<String, List<Object>> root = new TreeMap<String, List<Object>>();
    aggregateStack.push(root);

    reader.setContentHandler(getNewDefaultHandler(headers, aggregateStack));
    reader.parse(TestNanoXMLOFXReader.class.getResourceAsStream("simple.ofx"));
    assertEquals(9, headers.size());
    assertEquals(1, aggregateStack.size());
    assertSame(root, aggregateStack.pop());

    TreeMap<String, List<Object>> OFX = (TreeMap<String, List<Object>>) root.remove("OFX").get(0);
    assertNotNull(OFX);
    TreeMap<String, List<Object>> SIGNONMSGSRSV1 = (TreeMap<String, List<Object>>) OFX.remove("SIGNONMSGSRSV1").get(0);
    assertNotNull(SIGNONMSGSRSV1);
    TreeMap<String, List<Object>> SONRS = (TreeMap<String, List<Object>>) SIGNONMSGSRSV1.remove("SONRS").get(0);
    assertNotNull(SONRS);
    TreeMap<String, List<Object>> STATUS = (TreeMap<String, List<Object>>) SONRS.remove("STATUS").get(0);
    assertNotNull(STATUS);
    assertEquals("0", STATUS.remove("CODE").get(0).toString().trim());
    assertEquals("INFO", STATUS.remove("SEVERITY").get(0).toString().trim());
    assertTrue(STATUS.isEmpty());
    assertEquals("20071015021529.000[-8:PST]", SONRS.remove("DTSERVER").get(0).toString().trim());
    assertEquals("ENG", SONRS.remove("LANGUAGE").get(0).toString().trim());
    assertEquals("19900101000000", SONRS.remove("DTACCTUP").get(0).toString().trim());
    assertTrue(SONRS.isEmpty());
    assertTrue(SIGNONMSGSRSV1.isEmpty());
    assertTrue(OFX.isEmpty());
    assertTrue(root.isEmpty());
  }

  /**
   * tests whitespace before and after
   */
  public void testWhitespaceBeforeAndAfter() throws Exception {
    NanoXMLOFXReader reader = new NanoXMLOFXReader();
    final Map<String, List<String>> headers = new HashMap<String, List<String>>();
    final Stack<Map<String, List<Object>>> aggregateStack = new Stack<Map<String, List<Object>>>();
    TreeMap<String, List<Object>> root = new TreeMap<String, List<Object>>();
    aggregateStack.push(root);

    reader.setContentHandler(getNewDefaultHandler(headers, aggregateStack));
    reader.parse(TestNanoXMLOFXReader.class.getResourceAsStream("whitespace-example.ofx"));
    assertEquals(9, headers.size());
    assertEquals(1, aggregateStack.size());
    assertSame(root, aggregateStack.pop());

    TreeMap<String, List<Object>> OFX = (TreeMap<String, List<Object>>) root.remove("OFX").get(0);
    assertNotNull(OFX);
    assertEquals("E", OFX.remove("S").get(0));
    assertTrue(OFX.isEmpty());
    assertTrue(root.isEmpty());
  }
  
  /**
   * tests for closing tags in v1
   */
  public void testClosingTagsVersion1() throws Exception {
    NanoXMLOFXReader reader = new NanoXMLOFXReader();
    final Map<String, List<String>> headers = new HashMap<String, List<String>>();
    final Stack<Map<String, List<Object>>> aggregateStack = new Stack<Map<String, List<Object>>>();
    TreeMap<String, List<Object>> root = new TreeMap<String, List<Object>>();
    aggregateStack.push(root);

    reader.setContentHandler(getNewDefaultHandler(headers, aggregateStack));
    reader.parse(TestNanoXMLOFXReader.class.getResourceAsStream("closing-tags.ofx"));
    assertEquals(9, headers.size());
    assertEquals(1, aggregateStack.size());
    assertSame(root, aggregateStack.pop());

    TreeMap<String, List<Object>> OFX = (TreeMap<String, List<Object>>) root.remove("OFX").get(0);
    assertNotNull(OFX);
    TreeMap<String, List<Object>> SIGNONMSGSRSV1 = (TreeMap<String, List<Object>>) OFX.remove("SIGNONMSGSRSV1").get(0);
    assertNotNull(SIGNONMSGSRSV1);
    TreeMap<String, List<Object>> SONRS = (TreeMap<String, List<Object>>) SIGNONMSGSRSV1.remove("SONRS").get(0);
    assertNotNull(SONRS);
    TreeMap<String, List<Object>> STATUS = (TreeMap<String, List<Object>>) SONRS.remove("STATUS").get(0);
    assertNotNull(STATUS);
    TreeMap<String, List<Object>> FI = (TreeMap<String, List<Object>>) SONRS.remove("FI").get(0);
    assertNotNull(FI);
    assertEquals("0", STATUS.remove("CODE").get(0).toString().trim());
    assertEquals("INFO", STATUS.remove("SEVERITY").get(0).toString().trim());
    assertTrue(STATUS.isEmpty());
    assertEquals("20100717152132", SONRS.remove("DTSERVER").get(0).toString().trim());
    assertEquals("ENG", SONRS.remove("LANGUAGE").get(0).toString().trim());
    assertEquals("ameritrade.com", FI.remove("ORG").get(0).toString().trim());
    assertTrue(SONRS.isEmpty());
    assertTrue(SIGNONMSGSRSV1.isEmpty());
    assertTrue(OFX.isEmpty());
    assertTrue(root.isEmpty());
  }
  
  private DefaultHandler getNewDefaultHandler(final Map<String, List<String>> headers, final Stack<Map<String, List<Object>>> aggregateStack) {
  	return new DefaultHandler() {
      @Override
      public void onHeader(String name, String value) {
        LOG.debug(name + ":" + value);
        List<String> list = headers.get(name);
        if (list == null) {
          list = new ArrayList<String>();
          headers.put(name, list);
        }
        list.add(value);
      }

      @Override
      public void onElement(String name, String value) {
      	LOG.debug("onElement " + aggregateStack.size());
        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + name + "=" + value);

        List<Object> list = aggregateStack.peek().get(name);
        if (list == null) {
          list = new ArrayList<Object>();
          aggregateStack.peek().put(name, list);
        }
        list.add(value);
      }

      @Override
      public void startAggregate(String aggregateName) {
	      LOG.debug("startAggregate " +aggregateName + " " + aggregateStack.size());
        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + aggregateName + " {");

        TreeMap<String, List<Object>> aggregate = new TreeMap<String, List<Object>>();
        List<Object> list = aggregateStack.peek().get(aggregateName);
        if (list == null) {
          list = new ArrayList<Object>();
          aggregateStack.peek().put(aggregateName, list);
        }
        list.add(aggregate);
        aggregateStack.push(aggregate);
      }

      @Override
      public void endAggregate(String aggregateName) {
      	LOG.debug("endAggregate " +aggregateName + " " + aggregateStack.size());
        aggregateStack.pop();

        char[] tabs = new char[aggregateStack.size() * 2];
        Arrays.fill(tabs, ' ');
        LOG.debug(new String(tabs) + "}");
      }
    };
  }
}
