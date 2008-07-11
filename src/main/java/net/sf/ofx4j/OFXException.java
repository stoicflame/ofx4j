package net.sf.ofx4j;

/**
 * Base exception class.
 *
 * @author Ryan Heaton
 */
public class OFXException extends Exception {

  public OFXException() {
  }

  public OFXException(String message) {
    super(message);
  }

  public OFXException(String message, Throwable cause) {
    super(message, cause);
  }

  public OFXException(Throwable cause) {
    super(cause);
  }
}
