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

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;
import java.util.List;

/**
 * The transaction list aggregate.
 * @see "Section 13.9.1.2, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "INVTRANLIST" )
public class InvestmentTransactionList {
  private Date start;
  private Date end;
  private List<BaseInvestmentTransaction> transactions;
  private List<InvestmentBankTransaction> bankTransactions;

  /**
   * Gets the start date. This is a required field according to the OFX spec.
   *
   * @return The start date
   */
  @Element( name = "DTSTART", required = true, order = 0)
  public Date getStart() {
    return start;
  }

  /**
   * Sets the start date. This is a required field according to the OFX spec.
   *
   * @param start The start date
   */
  public void setStart(Date start) {
    this.start = start;
  }

  /**
   * Gets the end date. This is a required field according to the OFX spec.
   *
   * @return he end date
   */
  @Element( name = "DTEND", required = true, order = 10 )
  public Date getEnd() {
    return end;
  }

  /**
   * Sets the end date. This is a required field according to the OFX spec.
   *
   * @param end the end date
   */
  public void setEnd(Date end) {
    this.end = end;
  }

  /**
   * Gets the investment transaction list. This is a heterogenous list of different types of
   * transactions returned in the order the brokerage provides them.
   *
   * @return the investment transaction list
   */
  @ChildAggregate( order = 20 )
  public List<BaseInvestmentTransaction> getInvestmentTransactions() {
    return transactions;
  }

  /**
   * Sets the investment transaction list. This is a heterogenous list of different types of
   * transactions returned in the order the brokerage provides them.
   *
   * @param transactions the investment transaction list
   */
  public void setInvestmentTransactions(List<BaseInvestmentTransaction> transactions) {
    this.transactions = transactions;
  }

  /**
   * Gets the bank transaction list.
   *
   * @return the bank transaction list
   */
  @ChildAggregate( order = 30 )
  public List<InvestmentBankTransaction> getBankTransactions() {
    return bankTransactions;
  }

  /**
   * Sets the bank transaction list.
   *
   * @param bankTransactions the bank transaction list
   */
  public void setBankTransactions(List<InvestmentBankTransaction> bankTransactions) {
    this.bankTransactions = bankTransactions;
  }
}
