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

package net.sf.ofx4j.domain.data.investment.transactions;

import net.sf.ofx4j.domain.data.common.Transaction;
import net.sf.ofx4j.domain.data.investment.accounts.SubAccountType;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Bank transactions that are part of an investment account statement. Wraps a {@link Transaction}.
 * @see "Section 13.9.2.3, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "INVBANKTRAN" )
public class InvestmentBankTransaction {

  private Transaction transaction;
  private String subAccountFund;

  /**
   * Gets the wrapped transaction aggregate.
   * @return the wrapped transaction
   */
  @ChildAggregate( order = 10 )
  public Transaction getTransaction() {
    return transaction;
  }

  /**
   * Sets the wrapped transaction aggregate.
   * @param transaction the wrapped transaction
   */
  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  /**
   * Gets the sub account type that the security is from (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.2, OFX Spec"
   *
   * @return the sub account fund for the transaction
   */
  @Element( name = "SUBACCTFUND", required = true, order = 20)
  public String getSubAccountFund() {
    return subAccountFund;
  }

  /**
   * Sets the sub account type that the security is from (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.2, OFX Spec"
   *
   * @param subAccountFund the sub account fund for the transaction
   */
  public void setSubAccountFund(String subAccountFund) {
    this.subAccountFund = subAccountFund;
  }

  /**
   * Gets the result of getSubAccountFund as one of the well-known types.
   *
   * @return the type of null if it wasn't one of the well known types
   */
  public SubAccountType getSubAccountFundEnum() {
    return SubAccountType.fromOfx(getSubAccountFund());
  }
}
