package net.n3.nanoxml;

/**
 * Backdoor to a ContentReader.  Just making it public.
 * 
 * @author Ryan Heaton
 */
public class ContentReaderBackdoor extends ContentReader {

  public ContentReaderBackdoor(IXMLReader reader, IXMLEntityResolver resolver, String buffer) {
    super(reader, resolver, buffer);
  }

}
