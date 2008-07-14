package net.sf.ofx4j.client;

import net.sf.ofx4j.domain.data.banking.BankAccountDetails;

/**
 * @author Ryan Heaton
 */
public interface BankAccount extends FinancialInstitutionAccount {

  /**
   * The details of the account.
   *
   * @return The details of the account.
   */
  BankAccountDetails getDetails();
}
