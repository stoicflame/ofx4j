package net.sf.ofx4j.client.net;

/**
 * @author Ryan Heaton
 */
public class OFXServerException extends OFXConnectionException {

  private final int httpCode;

  public OFXServerException(int httpCode) {
    this.httpCode = httpCode;
  }

  public OFXServerException(String message, int httpCode) {
    super(message);
    this.httpCode = httpCode;
  }

  public OFXServerException(String message, Throwable cause, int httpCode) {
    super(message, cause);
    this.httpCode = httpCode;
  }

  public OFXServerException(Throwable cause, int httpCode) {
    super(cause);
    this.httpCode = httpCode;
  }

  public int getHttpCode() {
    return httpCode;
  }
}
