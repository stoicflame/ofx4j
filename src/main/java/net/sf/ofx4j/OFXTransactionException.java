package net.sf.ofx4j;

/**
 * @author Ryan Heaton
 */
public class OFXTransactionException extends OFXException {
  public OFXTransactionException() {
  }

  public OFXTransactionException(String message) {
    super(message);
  }

  public OFXTransactionException(String message, Throwable cause) {
    super(message, cause);
  }

  public OFXTransactionException(Throwable cause) {
    super(cause);
  }
}
