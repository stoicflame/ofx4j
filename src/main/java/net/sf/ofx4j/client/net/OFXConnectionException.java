package net.sf.ofx4j.client.net;

import net.sf.ofx4j.OFXException;

/**
 * Error with a particular OFX connection.
 * 
 * @author Ryan Heaton
 */
public class OFXConnectionException extends OFXException {

  public OFXConnectionException() {
  }

  public OFXConnectionException(String message) {
    super(message);
  }

  public OFXConnectionException(String message, Throwable cause) {
    super(message, cause);
  }

  public OFXConnectionException(Throwable cause) {
    super(cause);
  }
}
