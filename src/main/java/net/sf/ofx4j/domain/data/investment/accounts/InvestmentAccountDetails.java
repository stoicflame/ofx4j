/*
 * Copyright 2010 Web Cohesion
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

package net.sf.ofx4j.domain.data.investment.accounts;

import net.sf.ofx4j.domain.data.common.AccountDetails;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Aggregate for the details that identifity a brokerage account.
 *
 * @author Jon Perlow
 * @see "OFX Spec, Section 13.6.1"
 */
@Aggregate
public class InvestmentAccountDetails implements AccountDetails {

  private String brokerId;
  private String accountNumber;
  private String accountKey;


  /**
   * Gets the broker id.
   *
   * @return the id of the broker
   */
  @Element ( name = "BROKERID", required = true, order = 0 )
  public String getBrokerId() {
    return brokerId;
  }

  /**
   * Sets the broker id.
   *
   * @param brokerId the id of the broker
   */
  public void setBrokerId(String brokerId) {
    this.brokerId = brokerId;
  }

  /**
   * Gets the account number.
   *
   * @return the account number
   */
  @Element( name = "ACCTID", required = true, order = 20)
  public String getAccountNumber() {
    return accountNumber;
  }

  /**
   * Sets the account number.
   *
   * @param accountNumber the account number
   */
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * Gets the account key.
   *
   * @return the account key
   */
  @Element( name = "ACCTKEY", order = 40 )
  public String getAccountKey() {
    return accountKey;
  }

  /**
   * Sets the account key.
   *
   * @param accountKey the account key
   */
  public void setAccountKey(String accountKey) {
    this.accountKey = accountKey;
  }
}
