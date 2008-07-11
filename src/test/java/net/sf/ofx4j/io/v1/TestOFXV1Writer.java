package net.sf.ofx4j.io.v1;

import junit.framework.TestCase;

import java.io.StringWriter;

/**
 * @author Ryan Heaton
 */
public class TestOFXV1Writer extends TestCase {

  /**
   * tests character escaping
   */
  public void testCharacterEscaping() throws Exception {
    StringWriter value = new StringWriter();
    OFXV1Writer writer = new OFXV1Writer(value);
    writer.writeElement("NAME", "&<>");
    writer.close();
    assertEquals("<NAME>&amp;&lt;&gt;\r\n", value.toString());

  }

}
