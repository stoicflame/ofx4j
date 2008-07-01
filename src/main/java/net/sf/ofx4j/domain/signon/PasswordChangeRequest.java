package net.sf.ofx4j.domain.signon;

import net.sf.ofx4j.domain.RequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Request to change a user password.
 *
 * @author Ryan Heaton
 * @see "Section 2.5.2.1, OFX Spec."
 */
@Aggregate ( "PINCHRQ" )
public class PasswordChangeRequest extends RequestMessage {

  private String userId;
  private String newPassword;

  /**
   * The id of the user changing password.
   *
   * @return The id of the user changing password.
   */
  @Element ( value = "USERID", required = true )
  public String getUserId() {
    return userId;
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
   * The new password.
   *
   * @return The new password.
   */
  @Element ( value = "NEWUSERPASS", required = true )
  public String getNewPassword() {
    return newPassword;
  }

  /**
   * The new password.
   *
   * @param newPassword The new password.
   */
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
