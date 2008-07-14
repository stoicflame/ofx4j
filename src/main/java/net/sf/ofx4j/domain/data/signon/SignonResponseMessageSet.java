package net.sf.ofx4j.domain.data.signon;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.domain.data.ResponseMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

import java.util.List;
import java.util.ArrayList;

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

  // Inherited.
  public List<ResponseMessage> getResponseMessages() {
    ArrayList<ResponseMessage> messages = new ArrayList<ResponseMessage>();

    if (getSignonResponse() != null) {
      messages.add(getSignonResponse());
    }

    return messages;
  }
}