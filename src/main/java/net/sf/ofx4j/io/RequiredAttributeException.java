package net.sf.ofx4j.io;

import net.sf.ofx4j.OFXRuntimeException;

/**
 * Thrown when a required attribute of an aggregate is null or empty.
 *
 * @author Ryan Heaton
 */
public class RequiredAttributeException extends OFXRuntimeException {

  public RequiredAttributeException() {
  }

  public RequiredAttributeException(String message) {
    super(message);
  }

  public RequiredAttributeException(String message, Throwable cause) {
    super(message, cause);
  }

  public RequiredAttributeException(Throwable cause) {
    super(cause);
  }
}
