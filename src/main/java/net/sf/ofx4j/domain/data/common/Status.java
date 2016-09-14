/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.ofx4j.domain.data.common;

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
   * Known status codes.
   */
  public enum KnownCode implements StatusCode {

    SUCCESS(0, "Success", Severity.INFO),
    CLIENT_UP_TO_DATE(1, "Client is up-to-date", Severity.INFO),
    GENERAL_ERROR(2000, "General error.", Severity.ERROR),
    GENERAL_ACCOUNT_ERROR(2002, "General account error.", Severity.ERROR),
    ACCOUNT_NOT_FOUND(2003, "Account not found.", Severity.ERROR),
    ACCOUNT_CLOSED(2004, "Account closed.", Severity.ERROR),
    ACCOUNT_NOT_AUTHORIZED(2005, "Account not authorized.", Severity.ERROR),
    DATE_TOO_SOON(2014, "Date too soon", Severity.ERROR),
    DUPLICATE_REQUEST(2019, "Duplicate request.", Severity.ERROR),
    UNSUPPORTED_VERSION(2021, "Unsupported version", Severity.ERROR),
    INVALID_TAN(2022, "Invalid transaction authorization number.", Severity.ERROR),
    MFA_CHALLENGE_REQUIRED(3000, "Further authentication required.", Severity.ERROR),
    MFA_CHALLENGE_FAILED(3001, "MFA failed.", Severity.ERROR),
    PASSWORD_CHANGE_REQUIRED(15000, "Password change required.", Severity.INFO),
    SIGNON_INVALID(15500, "Invalid signon", Severity.ERROR),
    CUSTOMER_ACCOUNT_IN_USE(15501, "Customer account in use.", Severity.ERROR),
    PASSWORD_LOCKED(15502, "Password locked.", Severity.ERROR),
    INVALID_CLIENT_UID(15510, "Invalid client UID.", Severity.ERROR),
    CONTACT_FI(15511, "User must contact FI.", Severity.ERROR),
    AUTHTOKEN_REQUIRED(15512, "Auth token required.", Severity.ERROR),
    INVALID_AUTHTOKEN(15513, "Invalid auth token.", Severity.ERROR),
    NO_DATA(14701, "No Tax Data for Account.", Severity.ERROR),
    DB_EXCEPTION(14702,"Database error has occured.",Severity.ERROR),
    NO_TAXSUPPORT(14703,"This Tax Year is not supported.",Severity.ERROR);
    private final int code;
    private final String message;
    private final Severity defaultSeverity;

    KnownCode(int code, String message, Severity defaultSeverity) {
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

    public static KnownCode fromCode(int code) {
      for (KnownCode value : values()) {
        if (value.getCode() == code) {
          return value;
        }
      }
      return null;
    }


    @Override
    public String toString() {
      return String.valueOf(code);
    }
  }

  private StatusCode code = KnownCode.SUCCESS;
  private Severity severity;
  private String message;

  /**
   * Status code.
   *
   * @return The status code.
   */
  @Element ( name = "CODE", required = true, order = 0 )
  public StatusCode getCode() {
    return code;
  }

  /**
   * Status code.
   *
   * @param code Status code.
   */
  public void setCode(StatusCode code) {
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
  @Element ( name = "SEVERITY", required = true, order = 10 )
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
  @Element ( name = "MESSAGE", order = 20 )
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
