package net.sf.ofx4j.domain.data.common;

import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "STMTRQ" )
public class StatementRequest extends RequestMessage {

  private StatementRange statementRange;

  /**
   * The statement range.
   *
   * @return The statement range.
   */
  @ChildAggregate ( name = "INCTRAN", required = false, order = 10 )
  public StatementRange getStatementRange() {
    return statementRange;
  }

  /**
   * The statement range.
   *
   * @param statementRange The statement range.
   */
  public void setStatementRange(StatementRange statementRange) {
    this.statementRange = statementRange;
  }
}
