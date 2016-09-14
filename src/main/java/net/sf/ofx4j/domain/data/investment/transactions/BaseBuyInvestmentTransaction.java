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

import net.sf.ofx4j.domain.data.investment.accounts.SubAccountType;
import net.sf.ofx4j.domain.data.seclist.SecurityId;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * Base class for all investment transactions for buying securities.
 * <br>
 * This class exposes a read-only view of the flattened aggregates that are
 * common to all buy investment transactions as a convenience to application
 * developers who may not find the ofx aggregation model intuitive.
 *
 * @author Jon Perlow
 */
public abstract class BaseBuyInvestmentTransaction extends BaseInvestmentTransaction
    implements TransactionWithSecurity {

  private BuyInvestmentTransaction buyInvestment;

  BaseBuyInvestmentTransaction(TransactionType transactionType) {
    super(transactionType);
  }

  /**
   * Gets the buy investment transaction child aggregate.
   *
   * @return the buy investment transaction child aggregate
   */
  @ChildAggregate( order = 10 )
  public BuyInvestmentTransaction getBuyInvestment() {
    return buyInvestment;
  }

  /**
   * Sets the buy investment transaction child aggregate.
   *
   * @param buyInvestment the buy investment transaction child aggregate
   */
  public void setBuyInvestment(BuyInvestmentTransaction buyInvestment) {
    this.buyInvestment = buyInvestment;
  }

  /**
   * Gets the investment transaction aggregate.
   *
   * @return the investment transaction aggregate
   */
  // @Overridden
  public InvestmentTransaction getInvestmentTransaction() {
    return getBuyInvestment().getInvestmentTransaction();
  }

  /**
   * Gets the id of the security that was bought. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the security id of the security that was bought
   */
  public SecurityId getSecurityId() {
    return getBuyInvestment().getSecurityId();
  }

  /**
   * Gets the number of units of the security that was bought. For security-based actions other
   * than stock splits, this is the quantity bought. For stocks, mutual funds, and others, this
   * is the number of shares. For bonds, this is the face value. For options, this is the number of
   * contacts. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the number of units purchased.
   */
  public Double getUnits() {
    return getBuyInvestment().getUnits();
  }

  /**
   * Gets the price per commonly-quoted unit. For stocks, mutual funds, and others, this is the
   * share price. For bonds, this is the percentage of par. For options, this is the per share (not
   * per contact) price. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the per unit price
   */
  public Double getUnitPrice() {
    return getBuyInvestment().getUnitPrice();
  }

  /**
   * Gets the portion of the unit price that is attributed to the dealer markup. This is an
   * optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the per unit markeup price
   */
  public Double getMarkup() {
    return getBuyInvestment().getMarkup();
  }

  /**
   * Gets the transaction commission for the purchase. This is an optional field according to the
   * OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the transaction commision
   */
  public Double getCommission() {
    return getBuyInvestment().getCommission();
  }

  /**
   * Gets the taxes for the purchase. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the transaction taxes
   */
  public Double getTaxes() {
    return getBuyInvestment().getTaxes();
  }

  /**
   * Gets the fees for the purchase. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the transaction fees
   */
  public Double getFees() {
    return getBuyInvestment().getFees();
  }

  /**
   * Gets the load for the purchase. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the load
   */
  public Double getLoad() {
    return getBuyInvestment().getLoad();
  }

  /**
   * Gets the total for the purchase. Should be equal to
   * (units * (unitPrice + markup)) + (commision + fees + load + taxes) according to the OFX
   * spec. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the total
   */
  public Double getTotal() {
    return getBuyInvestment().getTotal();
  }

  /**
   * Gets the currency code for the transaction. Only one of currency code or original currency
   * info should be set according to the OFX spec. If neither are set, means the default currency.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the currency code for the transaction
   */
  public String getCurrencyCode() {
    return getBuyInvestment().getCurrencyCode();
  }

  /**
   * Gets the original currency info for the transaction.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the original currency info for the transaction
   */
  public OriginalCurrency getOriginalCurrencyInfo() {
    return getBuyInvestment().getOriginalCurrencyInfo();
  }

  /**
   * Gets the sub account type for the security (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the sub account type
   */
  public String getSubAccountSecurity() {
    return getBuyInvestment().getSubAccountSecurity();
  }

  /**
   * Gets the result of getSubAccountSecurity as one of the well-known types.
   *
   * @return the type of null if it wasn't one of the well known types
   */
  public SubAccountType getSubAccountSecurityEnum() {
    return SubAccountType.fromOfx(getSubAccountSecurity());
  }

  /**
   * Gets the sub account type that the money came from. (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the sub account fund
   */
  public String getSubAccountFund() {
    return getBuyInvestment().getSubAccountFund();
  }

  /**
   * Gets the result of getSubAccountFund as one of the well-known types.
   *
   * @return the type or null if it wasn't one of the well known types.
   */
  public SubAccountType getSubAccountFundEnum() {
    return SubAccountType.fromOfx(getSubAccountFund());
  }
}
