package net.sf.ofx4j.domain.signon;

import net.sf.ofx4j.domain.ResponseMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * Response to a change a user password request.
 *
 * @author Ryan Heaton
 * @see "Section 2.5.2.2, OFX Spec."
 */
@Aggregate ( "PINCHRQ" )
public class PasswordChangeResponse extends ResponseMessage {

  private String userId;
  private Date changeTimestamp;

  /**
   * The id of the user changing password.
   *
   * @return The id of the user changing password.
   */
  @Element ( value = "USERID", required = true, order = 0 )
  public String getUserId() {
    return userId;
  }

  // Inherited.
  public String getResponseMessageName() {
    return "password change";
  }

  /**
   * The id of the user changing password.
   *
   * @param userId The id of the user changing password.
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * The timestamp of the password change.
   *
   * @return The timestamp of the password change.
   */
  @Element ( value = "DTCHANGED", order = 10 )
  public Date getChangeTimestamp() {
    return changeTimestamp;
  }

  /**
   * The timestamp of the password change.
   *
   * @param changeTimestamp The timestamp of the password change.
   */
  public void setChangeTimestamp(Date changeTimestamp) {
    this.changeTimestamp = changeTimestamp;
  }
}