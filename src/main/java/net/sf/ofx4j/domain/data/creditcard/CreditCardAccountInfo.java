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

import net.sf.ofx4j.domain.data.common.AccountStatus;
import net.sf.ofx4j.domain.data.common.AccountInfo;
import net.sf.ofx4j.domain.data.common.AccountDetails;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "CCACCTINFO" )
public class CreditCardAccountInfo implements AccountInfo {

  private CreditCardAccountDetails creditCardAccount;
  private Boolean supportsTransactionDetailOperations;
  private Boolean supportsTransferToOtherAccountOperations;
  private Boolean supportsTransferFromOtherAccountOperations;
  private AccountStatus status;

  /**
   * The credit card account this information is referencing.
   *
   * @return The credit card account this information is referencing.
   */
  @ChildAggregate ( name = "CCACCTFROM", required = true, order = 0 )
  public CreditCardAccountDetails getCreditCardAccount() {
    return creditCardAccount;
  }

  /**
   * The credit card account this information is referencing.
   *
   * @param creditCardAccount The credit card account this information is referencing.
   */
  public void setCreditCardAccount(CreditCardAccountDetails creditCardAccount) {
    this.creditCardAccount = creditCardAccount;
  }

  // Inherited.
  public AccountDetails getAccountDetails() {
    return getCreditCardAccount();
  }

  /**
   * Whether this account supports download of transaction details.
   *
   * @return Whether this account supports download of transaction details.
   */
  @Element ( name = "SUPTXDL", required = true, order = 10 )
  public Boolean getSupportsTransactionDetailOperations() {
    return supportsTransactionDetailOperations;
  }

  /**
   * Whether this account supports download of transaction details.
   *
   * @param supportsTransactionDetailOperations Whether this account supports download of transaction details.
   */
  public void setSupportsTransactionDetailOperations(Boolean supportsTransactionDetailOperations) {
    this.supportsTransactionDetailOperations = supportsTransactionDetailOperations;
  }

  /**
   * Whether this account supports transfer operations to other accounts.
   *
   * @return Whether this account supports transfer operations to other accounts.
   */
  @Element ( name = "XFERSRC", required = true, order = 20 )
  public Boolean getSupportsTransferToOtherAccountOperations() {
    return supportsTransferToOtherAccountOperations;
  }

  /**
   * Whether this account supports transfer operations to other accounts.
   *
   * @param supportsTransferToOtherAccountOperations Whether this account supports transfer operations to other accounts.
   */
  public void setSupportsTransferToOtherAccountOperations(Boolean supportsTransferToOtherAccountOperations) {
    this.supportsTransferToOtherAccountOperations = supportsTransferToOtherAccountOperations;
  }

  /**
   * Whether this account supports transfer operations from other accounts.
   *
   * @return Whether this account supports transfer operations from other accounts.
   */
  @Element ( name = "XFERDEST", required = true, order = 30 )
  public Boolean getSupportsTransferFromOtherAccountOperations() {
    return supportsTransferFromOtherAccountOperations;
  }

  /**
   * Whether this account supports transfer operations from other accounts.
   *
   * @param supportsTransferFromOtherAccountOperations Whether this account supports transfer operations from other accounts.
   */
  public void setSupportsTransferFromOtherAccountOperations(Boolean supportsTransferFromOtherAccountOperations) {
    this.supportsTransferFromOtherAccountOperations = supportsTransferFromOtherAccountOperations;
  }

  /**
   * The account status.
   *
   * @return The account status.
   */
  @Element ( name = "SVCSTATUS", required = true, order = 40 )
  public AccountStatus getStatus() {
    return status;
  }

  /**
   * The account status.
   *
   * @param status The account status.
   */
  public void setStatus(AccountStatus status) {
    this.status = status;
  }
}