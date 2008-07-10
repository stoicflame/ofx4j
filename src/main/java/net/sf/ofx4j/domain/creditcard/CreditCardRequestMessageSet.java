package net.sf.ofx4j.domain.creditcard;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.RequestMessageSet;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "CREDITCARDMSGSRQV1" )
public class CreditCardRequestMessageSet extends RequestMessageSet {

  private CreditCardStatementRequestTransaction statementRequest;

  public MessageSetType getType() {
    return MessageSetType.creditcard;
  }

  /**
   * The request.
   *
   * @return The request.
   */
  @ChildAggregate (order = 0)
  public CreditCardStatementRequestTransaction getStatementRequest() {
    return statementRequest;
  }

  /**
   * The request.
   *
   * @param statementRequest The request.
   */
  public void setStatementRequest(CreditCardStatementRequestTransaction statementRequest) {
    this.statementRequest = statementRequest;
  }
}
