package net.sf.ofx4j.io.nanoxml;

import net.sf.ofx4j.io.OFXFormatterHandler;

/**
 * @author Ryan Heaton
 */
public class FormatOFX {

  public static void main(String[] args) throws Exception {
    NanoXMLOFXReader reader = new NanoXMLOFXReader();
    reader.setContentHandler(new OFXFormatterHandler());
    reader.parse(FormatOFX.class.getResourceAsStream(args[0]));
  }
}
