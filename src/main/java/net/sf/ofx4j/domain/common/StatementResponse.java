package net.sf.ofx4j.domain.common;

import net.sf.ofx4j.domain.ResponseMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Locale;

/**
 * @author Ryan Heaton
 */
public class StatementResponse extends ResponseMessage {

  private String currencyCode = java.util.Currency.getInstance(Locale.US).getCurrencyCode().toUpperCase();
  private TransactionList transactionList;
  private BalanceInfo ledgerBalance;
  private BalanceInfo availableBalance;
  private String marketingInfo;

  public String getResponseMessageName() {
    return "bank statement";
  }

  /**
   * The currency code.
   *
   * @return The currency code.
   * @see java.util.Currency#getCurrencyCode()
   */
  @Element ( name = "CURDEF", required = true, order = 0 )
  public String getCurrencyCode() {
    return currencyCode;
  }

  /**
   * The currency code.
   *
   * @param currencyCode The currency code.
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  /**
   * The transaction list.
   *
   * @return The transaction list.
   */
  @ChildAggregate ( order = 20 )
  public TransactionList getTransactionList() {
    return transactionList;
  }

  /**
   * The transaction list.
   *
   * @param transactionList The transaction list.
   */
  public void setTransactionList(TransactionList transactionList) {
    this.transactionList = transactionList;
  }

  /**
   * The ledger balance.
   *
   * @return The ledger balance.
   */
  @ChildAggregate ( name = "LEDGERBAL", order = 30)
  public BalanceInfo getLedgerBalance() {
    return ledgerBalance;
  }

  /**
   * The ledger balance.
   *
   * @param ledgerBalance The ledger balance.
   */
  public void setLedgerBalance(BalanceInfo ledgerBalance) {
    this.ledgerBalance = ledgerBalance;
  }

  /**
   * The available balance.
   *
   * @return The available balance.
   */
  @ChildAggregate ( name = "AVAILBAL", order = 40 )
  public BalanceInfo getAvailableBalance() {
    return availableBalance;
  }

  /**
   * The available balance.
   *
   * @param availableBalance The available balance.
   */
  public void setAvailableBalance(BalanceInfo availableBalance) {
    this.availableBalance = availableBalance;
  }

  /**
   * Marketing information. (?)
   *
   * @return Marketing information.
   */
  @Element ( name = "MKTGINFO", order = 50)
  public String getMarketingInfo() {
    return marketingInfo;
  }

  /**
   * Marketing information. (?)
   *
   * @param marketingInfo Marketing information.
   */
  public void setMarketingInfo(String marketingInfo) {
    this.marketingInfo = marketingInfo;
  }
}
