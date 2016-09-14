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

import net.sf.ofx4j.meta.ChildAggregate;

/**
 * Base class for investment transactions that aren't buys or sales..
 * <br>
 * This class exposes a read-only view of the flattened aggregates that are
 * common to all investment transactions as a convenience to application
 * developers who may not find the ofx aggregation model intuitive.
 *
 * @author Jon Perlow
 */
public class BaseOtherInvestmentTransaction extends BaseInvestmentTransaction {

  private InvestmentTransaction investmentTransaction;

  BaseOtherInvestmentTransaction(TransactionType transactionType) {
    super(transactionType);
  }

  /**
   * Gets the {@link InvestmentTransaction} aggregate.
   *
   * @return the {@link InvestmentTransaction} aggregate
   */
  // @Override
  @ChildAggregate( order = 10 )
  public InvestmentTransaction getInvestmentTransaction() {
    return investmentTransaction;
  }

  /**
   * Sets the {@link InvestmentTransaction} aggregate.
   *
   * @param investmentTransaction the {@link InvestmentTransaction} aggregate
   */
  public void setInvestmentTransaction(InvestmentTransaction investmentTransaction) {
    this.investmentTransaction = investmentTransaction;
  }
}
