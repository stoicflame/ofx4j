package net.sf.ofx4j.domain.signon;

import net.sf.ofx4j.domain.TransactionWrappedRequestMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ("PINCHTRNRQ")
public class PasswordChangeRequestTransaction extends TransactionWrappedRequestMessage {

  private PasswordChangeRequest message;

  @ChildAggregate ( required = true, order = 30 )
  public PasswordChangeRequest getMessage() {
    return message;
  }

  public void setMessage(PasswordChangeRequest message) {
    this.message = message;
  }
}
