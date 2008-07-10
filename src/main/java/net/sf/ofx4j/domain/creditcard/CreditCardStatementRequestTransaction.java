package net.sf.ofx4j.domain.creditcard;

import net.sf.ofx4j.domain.TransactionWrappedRequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ("CCSTMTTRNRQ")
public class CreditCardStatementRequestTransaction extends TransactionWrappedRequestMessage {

  private CreditCardStatementRequest message;

  /**
   * The message.
   *
   * @return The message.
   */
  @ChildAggregate( required = true, order = 30 )
  public CreditCardStatementRequest getMessage() {
    return message;
  }

  /**
   * The message.
   *
   * @param message The message.
   *
   */
  public void setMessage(CreditCardStatementRequest message) {
    this.message = message;
  }
}