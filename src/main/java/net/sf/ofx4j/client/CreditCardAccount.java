package net.sf.ofx4j.client;

import net.sf.ofx4j.domain.data.creditcard.CreditCardAccountDetails;

/**
 * @author Ryan Heaton
 */
public interface CreditCardAccount extends FinancialInstitutionAccount {

  /**
   * The details of the credit card account.
   *
   * @return The details of the credit card account.
   */
  CreditCardAccountDetails getDetails();
}
