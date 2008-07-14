package net.sf.ofx4j.domain.data.banking;

import net.sf.ofx4j.domain.data.TransactionWrappedRequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ("STMTTRNRQ")
public class BankStatementRequestTransaction extends TransactionWrappedRequestMessage<BankStatementRequest> {

  private BankStatementRequest message;

  /**
   * The message.
   *
   * @return The message.
   */
  @ChildAggregate( required = true, order = 30 )
  public BankStatementRequest getMessage() {
    return message;
  }

  /**
   * The message.
   *
   * @param message The message.
   *
   */
  public void setMessage(BankStatementRequest message) {
    this.message = message;
  }

  // Inherited.
  public void setWrappedMessage(BankStatementRequest message) {
    setMessage(message);
  }
}
