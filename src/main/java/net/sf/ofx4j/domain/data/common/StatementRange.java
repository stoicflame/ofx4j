package net.sf.ofx4j.domain.data.common;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * @author Ryan Heaton
 */
@Aggregate( "INCTRAN" )
public class StatementRange {

  private Date start;
  private Date end;
  private Boolean includeTransactions = Boolean.TRUE;

  /**
   * The start of the statement range.
   *
   * @return The start of the statement range.
   */
  @Element ( name = "DTSTART", order = 0)
  public Date getStart() {
    return start;
  }

  /**
   * The start of the statement range.
   *
   * @param start The start of the statement range.
   */
  public void setStart(Date start) {
    this.start = start;
  }

  /**
   * The end of the statement range.
   *
   * @return The end of the statement range.
   */
  @Element( name = "DTEND", order = 10 )
  public Date getEnd() {
    return end;
  }

  /**
   * The end of the statement range.
   *
   * @param end The end of the statement range.
   */
  public void setEnd(Date end) {
    this.end = end;
  }

  /**
   * Whether to include transactions.
   *
   * @return Whether to include transactions.
   */
  @Element( name = "INCLUDE", required = true, order = 20 )
  public Boolean getIncludeTransactions() {
    return includeTransactions;
  }

  /**
   * Whether to include transactions.
   *
   * @param includeTransactions Whether to include transactions.
   */
  public void setIncludeTransactions(Boolean includeTransactions) {
    this.includeTransactions = includeTransactions;
  }
}
