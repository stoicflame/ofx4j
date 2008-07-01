package net.sf.ofx4j.domain.signon;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.ResponseMessageSet;
import net.sf.ofx4j.domain.TransactionWrappedRequestMessage;
import net.sf.ofx4j.domain.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * The sign-on response message set.
 *
 * @author Ryan Heaton
 * @see "Section 2.5, OFX Spec."
 */
public class SignonResponseMessageSet extends ResponseMessageSet {

  private SignonResponse message;
  private TransactionWrappedResponseMessage<PasswordChangeResponse> passwordChangeResponse;

  public MessageSetType getType() {
    return MessageSetType.signon;
  }

  /**
   * The message for this message set.
   *
   * @return The message for this message set.
   */
  public SignonResponse getMessage() {
    return message;
  }

  /**
   * The message for this message set.
   *
   * @param message The message for this message set.
   */
  public void setMessage(SignonResponse message) {
    this.message = message;
  }

  /**
   * The password change response.
   *
   * @return The password change response.
   */
  @ChildAggregate (name = "PINCHTRNRQ")
  public TransactionWrappedResponseMessage<PasswordChangeResponse> getPasswordChangeResponse() {
    return passwordChangeResponse;
  }

  /**
   * The password change response.
   *
   * @param passwordChangeResponse The password change response.
   */
  public void setPasswordChangeResponse(TransactionWrappedResponseMessage<PasswordChangeResponse> passwordChangeResponse) {
    this.passwordChangeResponse = passwordChangeResponse;
  }

  //todo: challenge request/response

}