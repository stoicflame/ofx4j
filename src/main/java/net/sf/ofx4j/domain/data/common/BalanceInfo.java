package net.sf.ofx4j.domain.data.common;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * @author Ryan Heaton
 */
@Aggregate
public class BalanceInfo {

  private double amount;
  private Date asOfDate;

  /**
   * The amount.
   *
   * @return The amount.
   */
  @Element ( name = "BALAMT", required = true, order = 0)
  public double getAmount() {
    return amount;
  }

  /**
   * The amount.
   *
   * @param amount The amount.
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * The as-of date.
   *
   * @return The as-of date.
   */
  @Element ( name = "DTASOF", required = true, order = 10)
  public Date getAsOfDate() {
    return asOfDate;
  }

  /**
   * The as-of date.
   *
   * @param asOfDate The as-of date.
   */
  public void setAsOfDate(Date asOfDate) {
    this.asOfDate = asOfDate;
  }
}
