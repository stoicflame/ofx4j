package net.sf.ofx4j.domain.common;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.Date;
import java.util.List;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "BANKTRANLIST" )
public class TransactionList {

  private Date start;
  private Date end;
  private List<Transaction> transactions;

  /**
   * The start date.
   *
   * @return The start date.
   */
  @Element ( name = "DTSTART", required = true, order = 0)
  public Date getStart() {
    return start;
  }

  /**
   * The start date.
   *
   * @param start The start date.
   */
  public void setStart(Date start) {
    this.start = start;
  }

  /**
   * The end date.
   *
   * @return The end date.
   */
  @Element( name = "DTEND", required = true, order = 10 )
  public Date getEnd() {
    return end;
  }

  /**
   * The end date.
   *
   * @param end The end date.
   */
  public void setEnd(Date end) {
    this.end = end;
  }

  /**
   * The transaction list.
   *
   * @return The transaction list.
   */
  @ChildAggregate ( order = 20 )
  public List<Transaction> getTransactions() {
    return transactions;
  }

  /**
   * The transaction list.
   *
   * @param transactions The transaction list.
   */
  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }
}
