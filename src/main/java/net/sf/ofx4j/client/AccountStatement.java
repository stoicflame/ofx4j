package net.sf.ofx4j.client;

import net.sf.ofx4j.domain.data.common.BalanceInfo;
import net.sf.ofx4j.domain.data.common.TransactionList;

/**
 * @author Ryan Heaton
 */
public interface AccountStatement {

  /**
   * The currency code.
   *
   * @return The currency code.
   * @see java.util.Currency#getCurrencyCode()
   */
  String getCurrencyCode();

  /**
   * The transaction list.
   *
   * @return The transaction list.
   */
  TransactionList getTransactionList();

  /**
   * The ledger balance.
   *
   * @return The ledger balance.
   */
  BalanceInfo getLedgerBalance();

  /**
   * The available balance.
   *
   * @return The available balance.
   */
  BalanceInfo getAvailableBalance();

}
