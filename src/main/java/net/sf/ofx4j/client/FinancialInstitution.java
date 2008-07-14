package net.sf.ofx4j.client;

import net.sf.ofx4j.OFXException;
import net.sf.ofx4j.domain.data.banking.BankAccountDetails;
import net.sf.ofx4j.domain.data.creditcard.CreditCardAccountDetails;

/**
 * @author Ryan Heaton
 */
public interface FinancialInstitution {

  /**
   * The financial institution data defining this FI.
   *
   * @return The financial institution data.
   */
  FinancialInstitutionData getData();

  /**
   * Read the specified financial institution profile. Implies a network call.
   *
   * @return The profile.
   * @throws OFXException if something goes awry.
   */
  FinancialInstitutionProfile readProfile() throws OFXException;

  /**
   * Load a bank account.
   *
   * @param details The bank account details.
   * @param username The username.
   * @param password The password.
   * @return The bank account.
   */
  BankAccount loadBankAccount(BankAccountDetails details, String username, String password);

  /**
   * Load a credit card account.
   *
   * @param details The credit card account details.
   * @param username The username.
   * @param password The password.
   * @return The credit card account.
   */
  CreditCardAccount loadCreditCardAccount(CreditCardAccountDetails details, String username, String password);
}
