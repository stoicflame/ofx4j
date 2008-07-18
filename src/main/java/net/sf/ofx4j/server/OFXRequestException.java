package net.sf.ofx4j.server;

import net.sf.ofx4j.OFXException;

/**
 * Thrown if there's a problem with a client request.
 * 
 * @author Ryan Heaton
 */
public class OFXRequestException extends OFXException {

  public OFXRequestException() {
  }

  public OFXRequestException(String message) {
    super(message);
  }

  public OFXRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public OFXRequestException(Throwable cause) {
    super(cause);
  }
}
