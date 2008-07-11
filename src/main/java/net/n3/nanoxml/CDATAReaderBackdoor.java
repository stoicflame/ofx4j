package net.n3.nanoxml;

/**
 * Backdoor to a ContentReader.  Just making it public.
 *
 * @author Ryan Heaton
 */
public class CDATAReaderBackdoor extends CDATAReader {

  public CDATAReaderBackdoor(IXMLReader reader) {
    super(reader);
  }

}