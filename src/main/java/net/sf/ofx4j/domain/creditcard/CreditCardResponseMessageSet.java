package net.sf.ofx4j.domain.creditcard;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.ResponseMessageSet;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "CREDITCARDMSGSRSV1" )
public class CreditCardResponseMessageSet extends ResponseMessageSet {

  private CreditCardStatementResponseTransaction statementResponse;

  public MessageSetType getType() {
    return MessageSetType.creditcard;
  }

  /**
   * The statement response.
   *
   * @return The statement response.
   */
  @ChildAggregate( order = 0 )
  public CreditCardStatementResponseTransaction getStatementResponse() {
    return statementResponse;
  }

  /**
   * The statement response.
   *
   * @param statementResponse The statement response.
   */
  public void setStatementResponse(CreditCardStatementResponseTransaction statementResponse) {
    this.statementResponse = statementResponse;
  }
}