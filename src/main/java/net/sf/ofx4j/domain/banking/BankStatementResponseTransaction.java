package net.sf.ofx4j.domain.banking;

import net.sf.ofx4j.domain.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "STMTTRNRS")
public class BankStatementResponseTransaction extends TransactionWrappedResponseMessage<BankStatementResponse> {

  private BankStatementResponse message;

  /**
   * The message.
   *
   * @return The message.
   */
  @ChildAggregate( required = true, order = 30 )
  public BankStatementResponse getMessage() {
    return message;
  }

  /**
   * The message.
   *
   * @param message The message.
   */
  public void setMessage(BankStatementResponse message) {
    this.message = message;
  }
}
