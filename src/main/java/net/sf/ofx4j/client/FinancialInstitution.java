/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.ofx4j.client;

import net.sf.ofx4j.OFXException;
import net.sf.ofx4j.domain.data.banking.BankAccountDetails;
import net.sf.ofx4j.domain.data.creditcard.CreditCardAccountDetails;
import net.sf.ofx4j.domain.data.investment.accounts.InvestmentAccountDetails;
import net.sf.ofx4j.domain.data.signup.AccountProfile;

import java.util.Collection;

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
   * Read the account profiles of the specified user.
   *
   * @param username The username.
   * @param password The password.
   * @return The profiles.
   */
  Collection<AccountProfile> readAccountProfiles(String username, String password) throws OFXException;

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


  /**
   * Load an investment account.
   *
   * @param details The investment account details.
   * @param username The username.
   * @param password The password.
   * @return The investment account.
   */
  InvestmentAccount loadInvestmentAccount(InvestmentAccountDetails details, String username, String password);
}
