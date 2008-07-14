package net.sf.ofx4j;

/**
 * Thrown for unsupported OFX security type.
 *
 * @author Ryan Heaton
 */
public class UnsupportedOFXSecurityTypeException extends OFXException {

  public UnsupportedOFXSecurityTypeException() {
  }

  public UnsupportedOFXSecurityTypeException(String message) {
    super(message);
  }

  public UnsupportedOFXSecurityTypeException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnsupportedOFXSecurityTypeException(Throwable cause) {
    super(cause);
  }
}
