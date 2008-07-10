package net.sf.ofx4j.domain.data.banking;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "BANKMSGSRSV1" )
public class BankingResponseMessageSet extends ResponseMessageSet {

  private BankStatementResponseTransaction statementResponse;

  public MessageSetType getType() {
    return MessageSetType.banking;
  }

  /**
   * The statement response.
   *
   * @return The statement response.
   */
  @ChildAggregate ( order = 0 )
  public BankStatementResponseTransaction getStatementResponse() {
    return statementResponse;
  }

  /**
   * The statement response.
   *
   * @param statementResponse The statement response.
   */
  public void setStatementResponse(BankStatementResponseTransaction statementResponse) {
    this.statementResponse = statementResponse;
  }
}