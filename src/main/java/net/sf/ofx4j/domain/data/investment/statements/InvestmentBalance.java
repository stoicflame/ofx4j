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

package net.sf.ofx4j.domain.data.investment.statements;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Aggregate for the investment balance.
 * @see "Section 13.9.2.7, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate("INVBAL")
public class InvestmentBalance {

  private Double availableCash;
  private Double marginBalance;
  private Double shortBalance;
  private Double buyingPower;
  private BalanceList balanceList;

  /**
   * Gets the available cash balance across all sub-accounts, including sweep funds. This is
   * required field according to the OFX spec.
   *
   * @return the available cash balance
   */
  @Element( name = "AVAILCASH", required = true, order = 10)
  public Double getAvailableCash() {
    return availableCash;
  }

  /**
   * Sets the available cash balance across all sub-accounts, including sweep funds. This is
   * required field according to the OFX spec.
   *
   * @param availableCash the available cash balance
   */
  public void setAvailableCash(Double availableCash) {
    this.availableCash = availableCash;
  }

  /**
   * Gets the margin account balance. A positive balance indicates a positive cash balance, while
   * a negative balance indicates the customer borrowed funds. This is a required field according
   * to the OFX spec.
   *
   * @return the margin account balance
   */
  @Element( name = "MARGINBALANCE", required = true, order = 20)
  public Double getMarginBalance() {
    return marginBalance;
  }

  /**
   * Sets the margin account balance. A positive balance indicates a positive cash balance, while
   * a negative balance indicates the customer borrowed funds. This is a required field according
   * to the OFX spec.
   *
   * @param marginBalance the margin account balance
   */
  public void setMarginBalance(Double marginBalance) {
    this.marginBalance = marginBalance;
  }

  /**
   * Gets the market value of all short positions. This is a positive balance. This is a required
   * field according to the OFX spec.
   *
   * @return the market value of all short positions
   */
  @Element( name = "SHORTBALANCE", required = true, order = 30)
  public Double getShortBalance() {
    return shortBalance;
  }

  /**
   * Sets the market value of all short positions. This is a positive balance. This is a required
   * field according to the OFX spec.
   *
   * @param shortBalance the market value of all short positions
   */
  public void setShortBalance(Double shortBalance) {
    this.shortBalance = shortBalance;
  }

  /**
   * Gets the buying power amount. This is an optional field according to the OFX spec.
   *
   * @return the buying power
   */
  @Element( name = "BUYPOWER", order = 40)
  public Double getBuyingPower() {
    return buyingPower;
  }

  /**
   * Sets the buying power amount. This is an optional field according to the OFX spec.
   *
   * @param buyingPower the buying power
   */
  public void setBuyingPower(Double buyingPower) {
    this.buyingPower = buyingPower;
  }

  /**
   * Gets the investment balance list. This is an optional field according to the OFX spec.
   *
   * @return the investment balance list
   */
  @ChildAggregate( order = 50 )
  public BalanceList getBalanceList() {
    return balanceList;
  }

  /**
   * Sets the investment balance list. This is an optional field according to the OFX spec.
   *
   * @param balanceList the investment balance list
   */
  public void setBalanceList(BalanceList balanceList) {
    this.balanceList = balanceList;
  }
}
