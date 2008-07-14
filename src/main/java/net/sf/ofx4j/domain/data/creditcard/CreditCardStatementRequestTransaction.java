package net.sf.ofx4j.domain.data.creditcard;

import net.sf.ofx4j.domain.data.TransactionWrappedRequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ("CCSTMTTRNRQ")
public class CreditCardStatementRequestTransaction extends TransactionWrappedRequestMessage<CreditCardStatementRequest> {

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

  // Inherited.
  public void setWrappedMessage(CreditCardStatementRequest message) {
    setMessage(message);
  }
}