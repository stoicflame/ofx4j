package net.sf.ofx4j.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Basic interface for reading an OFX document.
 *
 * @author Ryan Heaton
 */
public interface OFXReader {

  /**
   * Set the handler for this OFX reader.
   *
   * @param handler The handler.
   */
  void setContentHandler(OFXHandler handler);

  /**
   * Parse a stream.
   *
   * @param stream The stream to parse.
   */
  void parse(InputStream stream) throws IOException, OFXParseException;

  /**
   * Parse a reader.
   *
   * @param reader The reader to parse.
   */
  void parse(Reader reader) throws IOException, OFXParseException;
  
}
