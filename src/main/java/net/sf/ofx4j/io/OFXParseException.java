package net.sf.ofx4j.io;

/**
 * @author Ryan Heaton
 */
public class OFXParseException extends Exception {

  public OFXParseException() {
  }

  public OFXParseException(String message) {
    super(message);
  }

  public OFXParseException(String message, Throwable cause) {
    super(message, cause);
  }

  public OFXParseException(Throwable cause) {
    super(cause);
  }
}
