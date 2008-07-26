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

package net.sf.ofx4j.domain.data.creditcard;

import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.domain.data.common.AccountDetails;

/**
 * @author Ryan Heaton
 * 
 * @see "OFX Spec, Section 11.3.2"
 */
@Aggregate
public class CreditCardAccountDetails implements AccountDetails {

  private String accountNumber;
  private String accountKey;

  /**
   * The account number.
   *
   * @return The account number.
   */
  @Element ( name = "ACCTID", required = true, order = 0 )
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
   * The account key.
   *
   * @return The account key.
   */
  @Element ( name = "ACCKEY", order = 10 )
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
