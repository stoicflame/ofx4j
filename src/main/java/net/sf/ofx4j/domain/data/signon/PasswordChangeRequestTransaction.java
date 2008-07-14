package net.sf.ofx4j.domain.data.signon;

import net.sf.ofx4j.domain.data.TransactionWrappedRequestMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ("PINCHTRNRQ")
public class PasswordChangeRequestTransaction extends TransactionWrappedRequestMessage<PasswordChangeRequest> {

  private PasswordChangeRequest message;

  /**
   * The wrapped message.
   *
   * @return The wrapped message.
   */
  @ChildAggregate ( required = true, order = 30 )
  public PasswordChangeRequest getMessage() {
    return message;
  }

  /**
   * The wrapped message.
   *
   * @param message The wrapped message.
   */
  public void setMessage(PasswordChangeRequest message) {
    this.message = message;
  }

  // Inherited.
  public void setWrappedMessage(PasswordChangeRequest message) {
    setMessage(message);
  }
}
