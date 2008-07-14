package net.sf.ofx4j.client;

import net.sf.ofx4j.OFXException;

/**
 * @author Ryan Heaton
 */
public class NoOFXResponseException extends OFXException {

  public NoOFXResponseException() {
  }

  public NoOFXResponseException(String message) {
    super(message);
  }

  public NoOFXResponseException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoOFXResponseException(Throwable cause) {
    super(cause);
  }
}
