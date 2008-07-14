package net.sf.ofx4j.domain.data.signon;

import net.sf.ofx4j.domain.data.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ("PINCHTRNRS")
public class PasswordChangeResponseTransaction extends TransactionWrappedResponseMessage<PasswordChangeResponse> {

  private PasswordChangeResponse message;

  /**
   * The message.
   *
   * @return The message.
   */
  @ChildAggregate ( required = true, order = 30 )
  public PasswordChangeResponse getMessage() {
    return message;
  }

  /**
   * The message.
   *
   * @param message The message.
   */
  public void setMessage(PasswordChangeResponse message) {
    this.message = message;
  }

  // Inherited.
  public PasswordChangeResponse getWrappedMessage() {
    return getMessage();
  }
}