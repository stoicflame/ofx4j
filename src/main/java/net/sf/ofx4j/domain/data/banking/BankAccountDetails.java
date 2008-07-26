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

package net.sf.ofx4j.domain.data.banking;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.domain.data.common.AccountDetails;

/**
 * Base bank account details.
 *
 * @author Ryan Heaton
 * @see "OFX Spec, Section 11.3.1"
 */
@Aggregate
public class BankAccountDetails implements AccountDetails {

  private String bankId;
  private String branchId;
  private String accountNumber;
  private AccountType accountType;
  private String accountKey;

  /**
   * The routing and transit number.
   *
   * @return The routing and transit number.
   */
  @Element ( name = "BANKID", required = true, order = 0 )
  public String getBankId() {
    return bankId;
  }

  /**
   * The routing and transit number.
   *
   * @param bankId The routing and transit number.
   */
  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  /**
   * The routing and transit number.
   *
   * @return The routing and transit number.
   */
  public String getRoutingNumber() {
    return getBankId();
  }

  /**
   * The routing and transit number.
   *
   * @param routingNumber The routing and transit number.
   */
  public void setRoutingNumber(String routingNumber) {
    setBankId(routingNumber);
  }

  /**
   * The branch id.
   *
   * @return The branch id.
   */
  @Element ( name = "BRANCHID", order = 10 )
  public String getBranchId() {
    return branchId;
  }

  /**
   * The branch id.
   *
   * @param branchId The branch id.
   */
  public void setBranchId(String branchId) {
    this.branchId = branchId;
  }

  /**
   * The account number.
   *
   * @return The account number.
   */
  @Element ( name = "ACCTID", required = true, order = 20)
  public String getAccountNumber() {
    return accountNumber;
  }

  /**
   * The account number.
   *
   * @param accountNumber The account number.
   */
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * The account type.
   *
   * @return The account type.
   */
  @Element ( name = "ACCTTYPE", required = true, order = 30)
  public AccountType getAccountType() {
    return accountType;
  }

  /**
   * The account type.
   *
   * @param accountType The account type.
   */
  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  /**
   * The account key.
   *
   * @return The account key.
   */
  @Element( name = "ACCTKEY", order = 40 )
  public String getAccountKey() {
    return accountKey;
  }

  /**
   * The account key.
   *
   * @param accountKey The account key.
   */
  public void setAccountKey(String accountKey) {
    this.accountKey = accountKey;
  }
}
