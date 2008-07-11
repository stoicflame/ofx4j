package net.sf.ofx4j;

/**
 * @author Ryan Heaton
 */
public class OFXRuntimeException extends RuntimeException {

  public OFXRuntimeException() {
  }

  public OFXRuntimeException(String message) {
    super(message);
  }

  public OFXRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public OFXRuntimeException(Throwable cause) {
    super(cause);
  }
}
