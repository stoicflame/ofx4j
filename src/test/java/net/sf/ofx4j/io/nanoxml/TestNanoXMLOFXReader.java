package net.sf.ofx4j.io.nanoxml;

import junit.framework.TestCase;
import net.sf.ofx4j.io.DefaultHandler;
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
    reader.parse(TestNanoXMLOFXReader.class.getResourceAsStream("example-response.ofx"));
    assertEquals(9, headers.size());
    assertEquals(1, aggregateStack.size());
    assertSame(root, aggregateStack.pop());


    TreeMap<String, Object> OFX = (TreeMap<String, Object>) root.remove("OFX");
    assertNotNull(OFX);

    TreeMap<String, Object> SIGNONMSGSRSV1 = (TreeMap<String, Object>) OFX.remove("SIGNONMSGSRSV1");
    assertNotNull(SIGNONMSGSRSV1);
    TreeMap<String, Object> SONRS = (TreeMap<String, Object>) SIGNONMSGSRSV1.remove("SONRS");
    assertNotNull(SONRS);
    TreeMap<String, Object> STATUS = (TreeMap<String, Object>) SONRS.remove("STATUS");
    assertNotNull(STATUS);
    assertEquals("0", STATUS.remove("CODE").toString().trim());
    assertEquals("INFO", STATUS.remove("SEVERITY").toString().trim());
    assertTrue(STATUS.isEmpty());
    assertEquals("20071015021529.000[-8:PST]", SONRS.remove("DTSERVER").toString().trim());
    assertEquals("ENG", SONRS.remove("LANGUAGE").toString().trim());
    assertEquals("19900101000000", SONRS.remove("DTACCTUP").toString().trim());
    TreeMap<String, Object> FI = (TreeMap<String, Object>) SONRS.remove("FI");
    assertEquals("Bank&Cd", FI.remove("ORG").toString().trim());
    assertEquals("01234", FI.remove("FID").toString().trim());
    assertTrue(FI.isEmpty());
    assertTrue(SONRS.isEmpty());
    assertTrue(SIGNONMSGSRSV1.isEmpty());

    TreeMap<String, Object> BANKMSGSRSV1 = (TreeMap<String, Object>) OFX.remove("BANKMSGSRSV1");
    TreeMap<String, Object> STMTTRNRS = (TreeMap<String, Object>) BANKMSGSRSV1.remove("STMTTRNRS");
    assertEquals("23382938", STMTTRNRS.remove("TRNUID").toString().trim());
    STATUS = (TreeMap<String, Object>) STMTTRNRS.remove("STATUS");
    assertNotNull(STATUS);
    assertEquals("0", STATUS.remove("CODE").toString().trim());
    assertEquals("INFO", STATUS.remove("SEVERITY").toString().trim());
    assertTrue(STATUS.isEmpty());
    TreeMap<String, Object> STMTRS = (TreeMap<String, Object>) STMTTRNRS.remove("STMTRS");
    assertEquals("USD", STMTRS.remove("CURDEF").toString().trim());
    TreeMap<String, Object> BANKACCTFROM = (TreeMap<String, Object>) STMTRS.remove("BANKACCTFROM");
    assertEquals("SAVINGS", BANKACCTFROM.remove("ACCTTYPE").toString().trim());
    assertEquals("098-121", BANKACCTFROM.remove("ACCTID").toString().trim());
    assertEquals("987654321", BANKACCTFROM.remove("BANKID").toString().trim());
    assertTrue(BANKACCTFROM.isEmpty());
    TreeMap<String, Object> BANKTRANLIST = (TreeMap<String, Object>) STMTRS.remove("BANKTRANLIST");
    assertEquals("20070101", BANKTRANLIST.remove("DTSTART").toString().trim());
    assertEquals("20071015", BANKTRANLIST.remove("DTEND").toString().trim());
    TreeMap<String, Object> STMTTRN = (TreeMap<String, Object>) BANKTRANLIST.remove("STMTTRN");
    assertEquals("CREDIT", STMTTRN.remove("TRNTYPE").toString().trim());
    assertEquals("20070329", STMTTRN.remove("DTPOSTED").toString().trim());
    assertEquals("20070329", STMTTRN.remove("DTUSER").toString().trim());
    assertEquals("150.00", STMTTRN.remove("TRNAMT").toString().trim());
    assertEquals("980310001", STMTTRN.remove("FITID").toString().trim());
    assertEquals("TRANSFER", STMTTRN.remove("NAME").toString().trim());
    assertEquals("Transfer from checking &<> etc.", STMTTRN.remove("MEMO").toString().trim());
    assertTrue(STMTTRN.isEmpty());
    assertTrue(BANKTRANLIST.isEmpty());
    TreeMap<String, Object> LEDGERBAL = (TreeMap<String, Object>) STMTRS.remove("LEDGERBAL");
    assertEquals("5250.00", LEDGERBAL.remove("BALAMT").toString().trim());
    assertEquals("20071015021529.000[-8:PST]", LEDGERBAL.remove("DTASOF").toString().trim());
    assertTrue(LEDGERBAL.isEmpty());
    TreeMap<String, Object> AVAILBAL = (TreeMap<String, Object>) STMTRS.remove("AVAILBAL");
    assertEquals("5250.00", AVAILBAL.remove("BALAMT").toString().trim());
    assertEquals("20071015021529.000[-8:PST]", AVAILBAL.remove("DTASOF").toString().trim());
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
    reader.parse(TestNanoXMLOFXReader.class.getResourceAsStream("simple.ofx"));
    assertEquals(9, headers.size());
    assertEquals(1, aggregateStack.size());
    assertSame(root, aggregateStack.pop());

    TreeMap<String, Object> OFX = (TreeMap<String, Object>) root.remove("OFX");
    assertNotNull(OFX);
    TreeMap<String, Object> SIGNONMSGSRSV1 = (TreeMap<String, Object>) OFX.remove("SIGNONMSGSRSV1");
    assertNotNull(SIGNONMSGSRSV1);
    TreeMap<String, Object> SONRS = (TreeMap<String, Object>) SIGNONMSGSRSV1.remove("SONRS");
    assertNotNull(SONRS);
    TreeMap<String, Object> STATUS = (TreeMap<String, Object>) SONRS.remove("STATUS");
    assertNotNull(STATUS);
    assertEquals("0", STATUS.remove("CODE").toString().trim());
    assertEquals("INFO", STATUS.remove("SEVERITY").toString().trim());
    assertTrue(STATUS.isEmpty());
    assertEquals("20071015021529.000[-8:PST]", SONRS.remove("DTSERVER").toString().trim());
    assertEquals("ENG", SONRS.remove("LANGUAGE").toString().trim());
    assertEquals("19900101000000", SONRS.remove("DTACCTUP").toString().trim());
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
    reader.parse(TestNanoXMLOFXReader.class.getResourceAsStream("whitespace-example.ofx"));
    assertEquals(9, headers.size());
    assertEquals(1, aggregateStack.size());
    assertSame(root, aggregateStack.pop());

    TreeMap<String, Object> OFX = (TreeMap<String, Object>) root.remove("OFX");
    assertNotNull(OFX);
    assertEquals("E", OFX.remove("S"));
    assertTrue(OFX.isEmpty());
    assertTrue(root.isEmpty());
  }
}
