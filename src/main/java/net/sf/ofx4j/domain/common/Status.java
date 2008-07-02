package net.sf.ofx4j.domain.common;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Transaction status element.
 *
 * @author Ryan Heaton
 * @see "Section 3.1.4, OFX Spec"
 */
@Aggregate ( "STATUS" )
public class Status {

  /**
   * Severity of the status.
   */
  public enum Severity {
    INFO,
    WARN,
    ERROR
  }

  /**
   * Status code.
   */
  public enum Code {

    SUCCESS(0, "Success", Severity.INFO),
    CLIENT_UP_TO_DATE(1, "Client is up-to-date", Severity.INFO),
    GENERAL_ERROR(2000, "General error.", Severity.ERROR),
    UNSUPPORTED_VERSION(2021, "Unsupported version", Severity.ERROR),
    INVALID_TAN(2022, "Invalid transaction authorization number.", Severity.ERROR),
    MFA_CHALLENGE_REQUIRED(3000, "Further authentication required.", Severity.ERROR),
    MFA_CHALLENGE_FAILED(3001, "MFA failed.", Severity.ERROR),
    PASSWORD_CHANGE_REQUIRED(15000, "Password change required.", Severity.INFO),
    SIGNON_INVALID(15500, "Invalid signon", Severity.ERROR),
    CUSTOMER_ACCOUNT_IN_USE(15501, "Customer account in use.", Severity.ERROR),
    PASSWORD_LOCKED(15502, "Passoword locked.", Severity.ERROR),
    INVALID_CLIENT_UID(15510, "Invalid client UID.", Severity.ERROR),
    CONTACT_FI(15511, "User must contact FI.", Severity.ERROR),
    AUTHTOKEN_REQUIRED(15512, "Auth token required.", Severity.ERROR),
    INVALID_AUTHTOKEN(15513, "Invalid auth token.", Severity.ERROR);

    private final int code;
    private final String message;
    private final Severity defaultSeverity;

    Code(int code, String message, Severity defaultSeverity) {
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

    public Severity getDefaultSeverity() {
      return defaultSeverity;
    }
  }

  private Code code = Code.SUCCESS;
  private Severity severity;
  private String message;

  /**
   * Status code.
   *
   * @return The status code.
   */
  @Element ( value = "CODE", required = true )
  public Code getCode() {
    return code;
  }

  /**
   * Status code.
   *
   * @param code Status code.
   */
  public void setCode(Code code) {
    this.code = code;
    if (this.severity == null) {
      this.severity = code.getDefaultSeverity();
    }
  }

  /**
   * The severity.
   *
   * @return The severity.
   */
  @Element( value  = "SEVERITY", required = true )
  public Severity getSeverity() {
    return severity;
  }

  /**
   * The severity.
   *
   * @param severity The severity.
   */
  public void setSeverity(Severity severity) {
    this.severity = severity;
  }

  /**
   * Server-supplied message.
   *
   * @return Server-supplied message.
   */
  @Element ( "MESSAGE" )
  public String getMessage() {
    return message;
  }

  /**
   * Server-supplied message.
   *
   * @param message Server-supplied message.
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
