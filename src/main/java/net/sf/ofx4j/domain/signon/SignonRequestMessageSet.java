package net.sf.ofx4j.domain.signon;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.RequestMessageSet;
import net.sf.ofx4j.domain.TransactionWrappedRequestMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * The sign-on request message set.
 *
 * @author Ryan Heaton
 * @see "Section 2.5, OFX Spec."
 */
@Aggregate ("SIGNONMSGSRQV1")
public class SignonRequestMessageSet extends RequestMessageSet {

  private SignonRequest signonRequest;
  private PasswordChangeRequestTransaction passwordChangeRequest;

  public MessageSetType getType() {
    return MessageSetType.signon;
  }

  /**
   * The message for this message set.
   *
   * @return The message for this message set.
   */
  @ChildAggregate ( required = true, order = 0 )
  public SignonRequest getSignonRequest() {
    return signonRequest;
  }

  /**
   * The message for this message set.
   *
   * @param signonRequest The message for this message set.
   */
  public void setSignonRequest(SignonRequest signonRequest) {
    this.signonRequest = signonRequest;
  }

  /**
   * The password change request.
   *
   * @return The password change request.
   */
  @ChildAggregate ( order = 10 )
  public PasswordChangeRequestTransaction getPasswordChangeRequest() {
    return passwordChangeRequest;
  }

  /**
   * The password change request.
   *
   * @param passwordChangeRequest The password change request.
   */
  public void setPasswordChangeRequest(PasswordChangeRequestTransaction passwordChangeRequest) {
    this.passwordChangeRequest = passwordChangeRequest;
  }

  //todo: challenge request/response

}
