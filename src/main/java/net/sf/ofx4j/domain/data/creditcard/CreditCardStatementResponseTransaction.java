package net.sf.ofx4j.domain.data.creditcard;

import net.sf.ofx4j.domain.data.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "CCSTMTTRNRS")
public class CreditCardStatementResponseTransaction extends TransactionWrappedResponseMessage {

  private CreditCardStatementResponse response;

  public CreditCardStatementResponseTransaction() {
    super("credit card statement");
  }

  /**
   * The message.
   *
   * @return The message.
   */
  @ChildAggregate( required = true, order = 30 )
  public CreditCardStatementResponse getResponse() {
    return response;
  }

  /**
   * The message.
   *
   * @param response The message.
   */
  public void setResponse(CreditCardStatementResponse response) {
    this.response = response;
  }
}