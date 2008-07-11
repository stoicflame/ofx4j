package net.sf.ofx4j.io;

/**
 * @author Ryan Heaton
 */
public class OFXSyntaxException extends OFXParseException {

  public OFXSyntaxException() {
  }

  public OFXSyntaxException(String message) {
    super(message);
  }

  public OFXSyntaxException(String message, Throwable cause) {
    super(message, cause);
  }

  public OFXSyntaxException(Throwable cause) {
    super(cause);
  }
}