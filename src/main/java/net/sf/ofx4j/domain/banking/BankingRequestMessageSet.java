package net.sf.ofx4j.domain.banking;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.RequestMessageSet;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "BANKMSGSRQV1" )
public class BankingRequestMessageSet extends RequestMessageSet {

  private BankStatementRequestTransaction statementRequest;

  public MessageSetType getType() {
    return MessageSetType.banking;
  }

  /**
   * The statement request.
   *
   * @return The statement request.
   */
  @ChildAggregate( order = 0 )
  public BankStatementRequestTransaction getStatementRequest() {
    return statementRequest;
  }

  /**
   * The statement request.
   *
   * @param statementRequest The statement request.
   */
  public void setStatementRequest(BankStatementRequestTransaction statementRequest) {
    this.statementRequest = statementRequest;
  }
}
