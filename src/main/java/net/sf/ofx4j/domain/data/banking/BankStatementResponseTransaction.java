package net.sf.ofx4j.domain.data.banking;

import net.sf.ofx4j.domain.data.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "STMTTRNRS")
public class BankStatementResponseTransaction extends TransactionWrappedResponseMessage {

  private BankStatementResponse response;

  public BankStatementResponseTransaction() {
    super("bank statement");
  }

  /**
   * The message.
   *
   * @return The message.
   */
  @ChildAggregate( required = true, order = 30 )
  public BankStatementResponse getResponse() {
    return response;
  }

  /**
   * The message.
   *
   * @param response The message.
   */
  public void setResponse(BankStatementResponse response) {
    this.response = response;
  }

}
