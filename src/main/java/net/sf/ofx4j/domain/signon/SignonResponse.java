package net.sf.ofx4j.domain.signon;

import net.sf.ofx4j.domain.common.Status;
import net.sf.ofx4j.domain.common.StatusHolder;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;
import java.util.Locale;

/**
 * The signon response message.
 *
 * @author Ryan Heaton
 * @see "Section 2.5.1.2, OFX Spec."
 */
@Aggregate ( "SONRS" )
public class SignonResponse implements StatusHolder {

  private Status status;
  private Date timestamp;
  private String userKey;
  private Date userKeyExpiration;
  private String language = Locale.US.getISO3Language();
  private Date profileLastUpdated;
  private Date accountLastUpdated;
  private FinancialInstitution financialInstitution;
  private String sessionId;
  private String accessKey;

  public String getStatusHolderName() {
    return "signon";
  }

  /**
   * The signon response status.
   *
   * @return The signon response status.
   */
  @ChildAggregate ( required = true, order = 0 )
  public Status getStatus() {
    return status;
  }

  /**
   * The signon response status.
   *
   * @param status The signon response status.
   */
  public void setStatus(Status status) {
    this.status = status;
  }

  /**
   * The timestamp of this response.
   *
   * @return The timestamp of this response.
   */
  @Element ( value = "DTSERVER", required = true, order = 10 )
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * The timestamp of this response.
   *
   * @param timestamp The timestamp of this response.
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * The userkey that can be used instead of the username/password.
   *
   * @return The userkey that can be used instead of the username/password.
   */
  @Element ( value = "USERKEY", order = 20 )
  public String getUserKey() {
    return userKey;
  }

  /**
   * The userkey that can be used instead of the username/password.
   *
   * @param userKey The userkey that can be used instead of the username/password.
   */
  public void setUserKey(String userKey) {
    this.userKey = userKey;
  }

  /**
   * The date/time of the expiration of the user key.
   *
   * @return The date/time of the expiration of the user key.
   */
  @Element ( value = "TSKEYEXPIRE", order = 30 )
  public Date getUserKeyExpiration() {
    return userKeyExpiration;
  }

  /**
   * The date/time of the expiration of the user key.
   *
   * @param userKeyExpiration The date/time of the expiration of the user key.
   */
  public void setUserKeyExpiration(Date userKeyExpiration) {
    this.userKeyExpiration = userKeyExpiration;
  }

  /**
   * The three-letter langauge code.
   *
   * @return The three-letter langauge code.
   * @see java.util.Locale#getISO3Language()
   */
  @Element ( value = "LANGUAGE", required = true, order = 40 )
  public String getLanguage() {
    return language;
  }

  /**
   * The three-letter langauge code.
   *
   * @param language The three-letter langauge code.
   */
  public void setLanguage(String language) {
    this.language = language;
  }

  /**
   * The date/time that the FI profile was last updated.
   *
   * @return The date/time that the FI profile was last updated.
   */
  @Element ( value = "DTPROFUP", order = 50 )
  public Date getProfileLastUpdated() {
    return profileLastUpdated;
  }

  /**
   * The date/time that the FI profile was last updated.
   *
   * @param profileLastUpdated The date/time that the FI profile was last updated.
   */
  public void setProfileLastUpdated(Date profileLastUpdated) {
    this.profileLastUpdated = profileLastUpdated;
  }

  /**
   * The date/time that the user's account information was updated.
   *
   * @return The date/time that the user's account information was updated.
   */
  @Element ( value = "DTACCTUP", order = 60 )
  public Date getAccountLastUpdated() {
    return accountLastUpdated;
  }

  /**
   * The date/time that the user's account information was updated.
   *
   * @param accountLastUpdated The date/time that the user's account information was updated.
   */
  public void setAccountLastUpdated(Date accountLastUpdated) {
    this.accountLastUpdated = accountLastUpdated;
  }

  /**
   * The financial instutution identity information.
   *
   * @return The financial instutution identity information.
   */
  @ChildAggregate ( order = 70 )
  public FinancialInstitution getFinancialInstitution() {
    return financialInstitution;
  }

  /**
   * The financial instutution identity information.
   *
   * @param financialInstitution The financial instutution identity information.
   */
  public void setFinancialInstitution(FinancialInstitution financialInstitution) {
    this.financialInstitution = financialInstitution;
  }

  /**
   * The session id for the client.
   *
   * @return The session id for the client.
   */
  @Element ( value = "SESSCOOKIE", order = 80 )
  public String getSessionId() {
    return sessionId;
  }

  /**
   * The session id for the client.
   *
   * @param sessionId The session id for the client.
   */
  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  /**
   * The access key that the client should return in the next sign-on requuest.
   *
   * @return The access key that the client should return in the next sign-on requuest.
   */
  @Element ( value = "ACCESSKEY", order = 90 )
  public String getAccessKey() {
    return accessKey;
  }

  /**
   * The access key that the client should return in the next sign-on requuest.
   *
   * @param accessKey The access key that the client should return in the next sign-on requuest.
   */
  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }
}
