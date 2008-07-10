package net.sf.ofx4j.domain.common;

/**
 * @author Ryan Heaton
 */
public enum TransactionType {

  /**
   * generic credit.
   */
  CREDIT,

  /**
   * genertic debit.
   */
  DEBIT,

  /**
   * interest earned.
   */
  INT,

  /**
   * dividend.
   */
  DIV,

  /**
   * bank fee.
   */
  FEE,

  /**
   * service charge.
   */
  SRVCHG,

  /**
   * deposit.
   */
  DEP,

  /**
   * ATM transaction.
   */
  ATM,

  /**
   * point of sale
   */
  POS,

  /**
   * transfer
   */
  XFER,

  /**
   * check
   */
  CHECK,

  /**
   * electronic payment
   */
  PAYMENT,

  /**
   * cash.
   */
  CASH,

  /**
   * direct deposit.
   */
  DIRECTDEP,

  /**
   * merchant-initiated debit
   */
  DIRECTDEBIT,

  /**
   * repeating payment.
   */
  REPEATPMT,

  /**
   * other
   */
  OTHER
}
