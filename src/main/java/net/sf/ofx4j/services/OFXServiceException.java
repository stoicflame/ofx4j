package net.sf.ofx4j.services;

/**
 * @author Ryan Heaton
 */
public class OFXServiceException extends Exception {

  public OFXServiceException() {
  }

  public OFXServiceException(String message) {
    super(message);
  }

  public OFXServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public OFXServiceException(Throwable cause) {
    super(cause);
  }
}
