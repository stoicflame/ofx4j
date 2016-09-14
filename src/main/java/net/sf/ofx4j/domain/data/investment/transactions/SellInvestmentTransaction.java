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
import net.sf.ofx4j.domain.data.investment.positions.Inv401KSource;
import net.sf.ofx4j.domain.data.seclist.SecurityId;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Sell investment transaction aggregate ("INVSELL").
 * @see "Section 13.9.2.4.3, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "INVSELL" )
public class SellInvestmentTransaction {
  private InvestmentTransaction investmentTransaction;
  private SecurityId securityId;
  private Double units;
  private Double unitPrice;
  private Double markdown;
  private Double commission;
  private Double taxes;
  private Double fees;
  private Double load;
  private Double withholding;
  private Boolean taxExempt;
  private Double total;
  private Double gain;
  private String currencyCode;
  private OriginalCurrency originalCurrencyInfo;
  private String subAccountSecurity;
  private String subAccountFund;
  private String loanId;
  private Double stateWithholding;
  private Double penalty;
  private String inv401kSource;

  /**
   * Gets the investment transaction child aggregate.
   *
   * @return the investment transaction child aggregate
   */
  @ChildAggregate( order = 10 )
  public InvestmentTransaction getInvestmentTransaction() {
    return investmentTransaction;
  }

  /**
   * Sets the investment transaction child aggregate.
   *
   * @param investmentTransaction the investment transaction child aggregate
   */
  public void setInvestmentTransaction(InvestmentTransaction investmentTransaction) {
    this.investmentTransaction = investmentTransaction;
  }

  /**
   * Gets the id of the security that was sold. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the security id of the security that was sold
   */
  @ChildAggregate( required = true, order = 20 )
  public SecurityId getSecurityId() {
    return securityId;
  }

  /**
   * Sets the id of the security that was sold. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param securityId the security id of the security that was sold
   */
  public void setSecurityId(SecurityId securityId) {
    this.securityId = securityId;
  }

  /**
   * Gets the number of units of the security that was sold. For security-based actions other
   * than stock splits, this is the quantity sold. For stocks, mutual funds, and others, this
   * is the number of shares. For bonds, this is the face value. For options, this is the number of
   * contacts. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the number of units sold
   */
  @Element( name = "UNITS", required = true, order = 30)
  public Double getUnits() {
    return units;
  }

  /**
   * Sets the number of units of the security that was sold. For security-based actions other
   * than stock splits, this is the quantity sold. For stocks, mutual funds, and others, this
   * is the number of shares. For bonds, this is the face value. For options, this is the number of
   * contacts. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param units the number of units sold
   */
  public void setUnits(Double units) {
    this.units = units;
  }

  /**
   * Gets the price per commonly-quoted unit. For stocks, mutual funds, and others, this is the
   * share price. For bonds, this is the percentage of par. For options, this is the per share (not
   * per contact) price. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the per unit price
   */
  @Element( name = "UNITPRICE", required = true, order = 40)
  public Double getUnitPrice() {
    return unitPrice;
  }

  /**
   * Sets the price per commonly-quoted unit. For stocks, mutual funds, and others, this is the
   * share price. For bonds, this is the percentage of par. For options, this is the per share (not
   * per contact) price. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param unitPrice the per unit price
   */
  public void setUnitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
  }

  /**
   * Gets the portion of the unit price that is attributed to the dealer markdown. This is an
   * optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the per unit markedown price
   */
  @Element( name = "MARKDOWN", order = 50)
  public Double getMarkdown() {
    return markdown;
  }

  /**
   * Sets the portion of the unit price that is attributed to the dealer markdown. This is an
   * optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param markdown the per unit markedown price
   */
  public void setMarkdown(Double markdown) {
    this.markdown = markdown;
  }

  /**
   * Gets the transaction commission for the sale. This is an optional field according to the
   * OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the transaction commision
   */
  @Element( name = "COMMISSION", order = 60)
  public Double getCommission() {
    return commission;
  }

  /**
   * Sets the transaction commission for the sale. This is an optional field according to the
   * OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param commission the transaction commision
   */
  public void setCommission(Double commission) {
    this.commission = commission;
  }

  /**
   * Gets the taxes for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the transaction taxes
   */
  @Element( name = "TAXES", order = 70)
  public Double getTaxes() {
    return taxes;
  }

  /**
   * Sets the taxes for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param taxes the transaction taxes
   */
  public void setTaxes(Double taxes) {
    this.taxes = taxes;
  }

  /**
   * Gets the fees for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the transaction fees
   */
  @Element( name = "FEES", order = 80)
  public Double getFees() {
    return fees;
  }

  /**
   * Sets the fees for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param fees the transaction fees
   */
  public void setFees(Double fees) {
    this.fees = fees;
  }

  /**
   * Gets the load for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the load
   */
  @Element( name = "LOAD", order = 90)
  public Double getLoad() {
    return load;
  }

  /**
   * Sets the load for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param load the load
   */
  public void setLoad(Double load) {
    this.load = load;
  }

  /**
   * Gets the withholding for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the withholding
   */
  @Element( name = "WITHHOLDING", order = 93)
  public Double getWithholding() {
    return withholding;
  }

  /**
   * Sets the withholding for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param withholding the withholding
   */
  public void setWithholding(Double withholding) {
    this.withholding = withholding;
  }

  /**
   * Gets whether the sale was tax exempt. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return whether the transaction was tax exempt
   */
  @Element( name = "TAXEXEMPT", order = 97)
  public Boolean getTaxExempt() {
    return taxExempt;
  }

  /**
   * Sets whether the sale was tax exempt. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param taxExempt whether the transaction was tax exempt
   */
  public void setTaxExempt(Boolean taxExempt) {
    this.taxExempt = taxExempt;
  }

  /**
   * Gets the total for the sale. Should be equal to
   * (units * (unitPrice + markdown)) + (commision + fees + load + taxes + penalty + withholding +
   * statewithholding) according to the OFX spec. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the total
   */
  @Element( name = "TOTAL", required = true, order = 100)
  public Double getTotal() {
    return total;
  }

  /**
   * Sets the total for the sale. Should be equal to
   * (units * (unitPrice + markdown)) + (commision + fees + load + taxes + penalty + withholding +
   * statewithholding) according to the OFX spec. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param total the total
   */
  public void setTotal(Double total) {
    this.total = total;
  }

  /**
   * Gets the gain sale. This is aan optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the gain for the sale
   */
  @Element( name = "GAIN", order = 105)
  public Double getGain() {
    return gain;
  }

  /**
   * Sets the gain sale. This is aan optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param gain the gain for the sale
   */
  public void setGain(Double gain) {
    this.gain = gain;
  }

  /**
   * Gets the currency code for the transaction. Only one of currency code or original currency
   * code should be set according to the OFX spec. If neither are set, means the default currency.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the currency code for the transaction
   */
  @Element( name = "CURRENCY", order = 110)
  public String getCurrencyCode() {
    return currencyCode;
  }

  /**
   * sets the currency code for the transaction. Only one of currency code or original currency
   * code should be set according to the OFX spec. If neither are set, means the default currency.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param currencyCode the currency code for the transaction
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    this.originalCurrencyInfo = null;
  }

  /**
   * Gets the original currency info for the transaction.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the original currency info for the transaction
   */
  @Element( name = "ORIGCURRENCY", order = 120)
  public OriginalCurrency getOriginalCurrencyInfo() {
    return originalCurrencyInfo;
  }

  /**
   * Sets the original currency info for the transaction.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param originalCurrencyInfo the original currency info for the transaction
   */
  public void setOriginalCurrencyInfo(OriginalCurrency originalCurrencyInfo) {
    this.originalCurrencyInfo = originalCurrencyInfo;
    this.currencyCode = null;
  }

  /**
   * Gets the sub account type for the security (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the sub account type
   */
  @Element( name = "SUBACCTSEC", order = 130)
  public String getSubAccountSecurity() {
    return subAccountSecurity;
  }

  /**
   * Sets the sub account type for the security (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param subAccountSecurity the sub account type
   */
  public void setSubAccountSecurity(String subAccountSecurity) {
    this.subAccountSecurity = subAccountSecurity;
  }

  /**
   * Gets the result of getSubAccountSecurity as one of the well-known types.
   *
   * @return the type of null if it wasn't one of the well known types.
   */
  public SubAccountType getSubAccountSecurityEnum() {
    return SubAccountType.fromOfx(getSubAccountSecurity());
  }

  /**
   * Gets the sub account type that the security is being transfered from
   * (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the sub account fund
   */
  @Element( name = "SUBACCTFUND", order = 140)
  public String getSubAccountFund() {
    return subAccountFund;
  }

  /**
   * Sets the sub account type that the security is being transfered from
   * (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param subAccountFund the sub account fund
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

  /**
   * Gets the loan id if this transaction was due to a loan or loan repayment on a 401k. This is an
   * optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the loan id
   */
  @Element( name = "LOANID", order = 150)
  public String getLoanId() {
    return loanId;
  }

  /**
   * Sets the loan id if this transaction was due to a loan or loan repayment on a 401k. This is an
   * optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param loanId the loan id
   */
  public void setLoanId(String loanId) {
    this.loanId = loanId;
  }

  /**
   * Gets the state withholding for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the state withholding
   */
  @Element( name = "STATEWITHHOLDING", order = 160)
  public Double getStateWithholding() {
    return stateWithholding;
  }

  /**
   * Sets the state withholding for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param stateWithholding the state withholding
   */
  public void setStateWithholding(Double stateWithholding) {
    this.stateWithholding = stateWithholding;
  }

  /**
   * Gets the penalty for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the state withholding
   */
  @Element( name = "PENALTY", order = 170)
  public Double getPenalty() {
    return penalty;
  }

  /**
   * Sets the penalty for the sale. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param penalty the state withholding
   */
  public void setPenalty(Double penalty) {
    this.penalty = penalty;
  }

  /**
   * Gets the 401K source for the sale. Should be one of "PRETAX", "AFTERTAX", "MATCH",
   * "PROFITSHARING", "ROLLOVER", "OTHERVEST", "OTHERNONVEST".  This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the 401k source
   */
  @Element( name = "INV401KSOURCE", order = 180)
  public String get401kSource() {
    return inv401kSource;
  }

  /**
   * Sets the 401K source for the sale. Should be one of "PRETAX", "AFTERTAX", "MATCH",
   * "PROFITSHARING", "ROLLOVER", "OTHERVEST", "OTHERNONVEST".  This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param inv401kSource the 401k source
   */
  public void set401kSource(String inv401kSource) {
    this.inv401kSource = inv401kSource;
  }

  /**
   * Gets the 401k source as one of the well-known types.
   *
   * @return the 401k source or null if its not one of the well-known types
   */
  public Inv401KSource get401kSourceEnum() {
    return Inv401KSource.fromOfx(get401kSource());
  }
}
