package net.sf.ofx4j.domain.creditcard;

import net.sf.ofx4j.domain.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "CCSTMTTRNRS")
public class CreditCardStatementResponseTransaction extends TransactionWrappedResponseMessage<CreditCardStatementResponse> {

  private CreditCardStatementResponse message;

  /**
   * The message.
   *
   * @return The message.
   */
  @ChildAggregate( required = true, order = 30 )
  public CreditCardStatementResponse getMessage() {
    return message;
  }

  /**
   * The message.
   *
   * @param message The message.
   */
  public void setMessage(CreditCardStatementResponse message) {
    this.message = message;
  }
}