package net.sf.ofx4j.domain.data.common;

/**
 * Holder for an unknown status code.
 *
 * @author Ryan Heaton
 */
public class UnknownStatusCode implements StatusCode {

  private final int code;
  private final String message;
  private final Status.Severity defaultSeverity;

  public UnknownStatusCode(int code, String message, Status.Severity defaultSeverity) {
    this.code = code;
    this.message = message;
    this.defaultSeverity = defaultSeverity;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public Status.Severity getDefaultSeverity() {
    return defaultSeverity;
  }

  @Override
  public String toString() {
    return String.valueOf(code);
  }
}
