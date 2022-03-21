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

package com.webcohesion.ofx4j.domain.data.investment.transactions;

import com.webcohesion.ofx4j.domain.data.investment.accounts.SubAccountType;
import com.webcohesion.ofx4j.domain.data.investment.positions.Inv401KSource;
import com.webcohesion.ofx4j.domain.data.seclist.SecurityId;
import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.ChildAggregate;
import com.webcohesion.ofx4j.meta.Element;

/**
 * Buy investment transaction aggregate ("INVBUY").
 * @see "Section 13.9.2.4.3, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "INVBUY" )
public class BuyInvestmentTransaction {

  private InvestmentTransaction investmentTransaction;
  private SecurityId securityId;
  private Double units;
  private Double unitPrice;
  private Double markup;
  private Double commission;
  private Double taxes;
  private Double fees;
  private Double load;
  private Double total;
  private OriginalCurrency currencyInfo;
  private OriginalCurrency originalCurrencyInfo;
  private String subAccountSecurity;
  private String subAccountFund;
  private String loanId;
  private Double loanInterest;
  private Double loanPrincipal;
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
   * Gets the id of the security that was bought. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the security id of the security that was bought
   */
  @ChildAggregate( required = true, order = 20 )
  public SecurityId getSecurityId() {
    return securityId;
  }

  /**
   * Sets the id of the security that was bought. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param securityId the security id of the security that was bought
   */
  public void setSecurityId(SecurityId securityId) {
    this.securityId = securityId;
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
  @Element( name = "UNITS", required = true, order = 30)
  public Double getUnits() {
    return units;
  }

  /**
   * Sets the number of units of the security that was bought. For security-based actions other
   * than stock splits, this is the quantity bought. For stocks, mutual funds, and others, this
   * is the number of shares. For bonds, this is the face value. For options, this is the number of
   * contacts. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param units the number of units purchased.
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
   * Gets the portion of the unit price that is attributed to the dealer markup. This is an
   * optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the per unit markeup price
   */
  @Element( name = "MARKUP", order = 50)
  public Double getMarkup() {
    return markup;
  }

  /**
   * Sets the portion of the unit price that is attributed to the dealer markup. This is an
   * optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param markup the per unit markeup price
   */
  public void setMarkup(Double markup) {
    this.markup = markup;
  }

  /**
   * Gets the transaction commission for the purchase. This is an optional field according to the
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
   * Sets the transaction commission for the purchase. This is an optional field according to the
   * OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param commission the transaction commision
   */
  public void setCommission(Double commission) {
    this.commission = commission;
  }

  /**
   * Gets the taxes for the purchase. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the transaction taxes
   */
  @Element( name = "TAXES", order = 70)
  public Double getTaxes() {
    return taxes;
  }

  /**
   * Sets the taxes for the purchase. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param taxes the transaction taxes
   */
  public void setTaxes(Double taxes) {
    this.taxes = taxes;
  }

  /**
   * Gets the fees for the purchase. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the transaction fees
   */
  @Element( name = "FEES", order = 80)
  public Double getFees() {
    return fees;
  }

  /**
   * Sets the fees for the purchase. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param fees the transaction fees
   */
  public void setFees(Double fees) {
    this.fees = fees;
  }

  /**
   * Gets the load for the purchase. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the load
   */
  @Element( name = "LOAD", order = 90)
  public Double getLoad() {
    return load;
  }

  /**
   * Sets the load for the purchase. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param load the load
   */
  public void setLoad(Double load) {
    this.load = load;
  }

  /**
   * Gets the total for the purchase. Should be equal to
   * (units * (unitPrice + markup)) + (commision + fees + taxes) according to the OFX
   * spec. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the total
   */
  @Element( name = "TOTAL", required = true, order = 100)
  public Double getTotal() {
    return total;
  }

  /**
   * Sets the total for the purchase. Should be equal to
   * (units * (unitPrice + markup)) + (commision + fees + taxes) according to the OFX
   * spec. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param total the total
   */
  public void setTotal(Double total) {
    this.total = total;
  }

  /**
   * Gets the currency code for the transaction. Only one of currency code or original currency
   * info should be set according to the OFX spec. If neither are set, means the default currency.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the currency code for the transaction.
   */
  @ChildAggregate( name = "CURRENCY", order = 110)
  public OriginalCurrency getCurrencyInfo() {
    return currencyInfo;
  }

  /**
   * Sets the currency code for the transaction. Only one of currency code or original currency
   * info may be set according to the OFX spec. If neither are set, means the default currency.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param currency the currency code for the transaction.
   */
  public void setCurrencyInfo(OriginalCurrency currency) {
    this.currencyInfo = currency;
    this.originalCurrencyInfo = null;
  }

  /**
   * Gets the original currency info for the transaction.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the original currency info for the transaction
   */
  @ChildAggregate( order = 120 )
  public OriginalCurrency getOriginalCurrencyInfo() {
    return originalCurrencyInfo;
  }

  /**
   * Sets the original currency info for the transaction
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param originalCurrencyInfo the original currency info for the transaction
   */
  public void setOriginalCurrencyInfo(OriginalCurrency originalCurrencyInfo) {
    this.originalCurrencyInfo = originalCurrencyInfo;
    this.currencyInfo = null;
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
   * Gets the sub account type that the money came from. (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the sub account fund
   */
  @Element( name = "SUBACCTFUND", order = 140)
  public String getSubAccountFund() {
    return subAccountFund;
  }

  /**
   * Sets the sub account type that the money came from. (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param subAcctFund the sub account fund
   */
  public void setSubAccountFund(String subAcctFund) {
    this.subAccountFund = subAcctFund;
  }

  /**
   * Gets the result of getSubAccountFund as one of the well-known types.
   *
   * @return the type or null if it wasn't one of the well known types.
   */
  public SubAccountType getSubAccountFundEnum() {
    return SubAccountType.fromOfx(getSubAccountFund());
  }

  /**
   * For 401(k) accounts only. Indicates that the transaction was due to a loan or a loan
   * repayment, and which loan it was.
   * @see "Section 13.9.2.4.2, OFX Spec"
   *
   * @return the loan id.
   */
  @Element(name = "LOANID", order = 150)
  public String getLoanId() {
    return loanId;
  }

  /**
   * For 401(k) accounts only. Sets the loan id that the transaction was due to.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param loanId the loan id.
   */
  public void setLoanId(String loanId) {
    this.loanId = loanId;
  }

  /**
   * For 401(k) accounts only. Indicates how much of the loan repayment was interest.
   * @see "Section 13.9.2.4.2, OFX Spec"
   *
   * @return how much of the loan repayment was interest.
   */
  @Element(name = "LOANINTEREST", order = 160)
  public Double getLoanInterest() {
    return loanInterest;
  }

  /**
   * For 401(k) accounts only. Sets how much of the loan repayment was interest.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param loanInterest the loan interest.
   */
  public void setLoanInterest(Double loanInterest) {
    this.loanInterest = loanInterest;
  }

  /**
   * For 401(k) accounts only. Indicates how much of the loan repayment was principal.
   * @see "Section 13.9.2.4.2, OFX Spec"
   *
   * @return how much of the loan repayment was principal.
   */
  @Element(name = "LOANPRINCIPAL", order = 170)
  public Double getLoanPrincipal() {
    return loanPrincipal;
  }

  /**
   * For 401(k) accounts only. Sets how much of the loan repayment was principal.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param loanPrincipal the loan principal.
   */
  public void setLoanPrincipal(Double loanPrincipal) {
    this.loanPrincipal = loanPrincipal;
  }

    /**
     * Gets the 401K source for the buy. Should be one of "PRETAX", "AFTERTAX", "MATCH",
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
     * Sets the 401K source for the buy. Should be one of "PRETAX", "AFTERTAX", "MATCH",
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
