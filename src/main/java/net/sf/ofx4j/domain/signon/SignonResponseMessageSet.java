package net.sf.ofx4j.domain.signon;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.ResponseMessageSet;
import net.sf.ofx4j.domain.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * The sign-on response message set.
 *
 * @author Ryan Heaton
 * @see "Section 2.5, OFX Spec."
 */
@Aggregate ("SIGNONMSGSRSV1")
public class SignonResponseMessageSet extends ResponseMessageSet {

  private SignonResponse signonResponse;
  private PasswordChangeResponseTransaction passwordChangeResponse;

  public MessageSetType getType() {
    return MessageSetType.signon;
  }

  /**
   * The message for this message set.
   *
   * @return The message for this message set.
   */
  @ChildAggregate ( order = 0 )
  public SignonResponse getSignonResponse() {
    return signonResponse;
  }

  /**
   * The message for this message set.
   *
   * @param signonResponse The message for this message set.
   */
  public void setSignonResponse(SignonResponse signonResponse) {
    this.signonResponse = signonResponse;
  }

  /**
   * The password change response.
   *
   * @return The password change response.
   */
  @ChildAggregate ( order = 10 )
  public PasswordChangeResponseTransaction getPasswordChangeResponse() {
    return passwordChangeResponse;
  }

  /**
   * The password change response.
   *
   * @param passwordChangeResponse The password change response.
   */
  public void setPasswordChangeResponse(PasswordChangeResponseTransaction passwordChangeResponse) {
    this.passwordChangeResponse = passwordChangeResponse;
  }

  //todo: challenge request/response

}