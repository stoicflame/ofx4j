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
 * Transaction for a stock split.
 * @see "Section 13.9.2.4.4, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "SPLIT" )
public class SplitTransaction extends BaseOtherInvestmentTransaction {
  private SecurityId securityId;
  private String subAccountSecurity;
  private Double oldUnits;
  private Double newUnits;
  private Double numerator;
  private Double denominator;
  private String currencyCode;
  private OriginalCurrency originalCurrencyInfo;
  private Double cashForFractionalUnits;
  private String subAccountFund;
  private String inv401kSource;

  public SplitTransaction() {
    super(TransactionType.SPLIT);
  }

  /**
   * Gets the id of the security for the split. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the security id of the security for the expsense
   */
  @ChildAggregate( required = true, order = 20 )
  public SecurityId getSecurityId() {
    return securityId;
  }

  /**
   * Sets the id of the security for the split. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param securityId the security id of the security for the expsense
   */
  public void setSecurityId(SecurityId securityId) {
    this.securityId = securityId;
  }

  /**
   * Gets the sub account type for the security (e.g. CASH, MARGIN, SHORT, OTHER). This is a
   * required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the sub account type
   */
  @Element( name = "SUBACCTSEC", order = 30)
  public String getSubAccountSecurity() {
    return subAccountSecurity;
  }

  /**
   * Sets the sub account type for the security (e.g. CASH, MARGIN, SHORT, OTHER). This is a
   * required field according to the OFX spec.
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
   * Gets the old number of units for the split. This is a required field according to the OFX
   * spec.
   *
   * @return the old number of units.
   */
  @Element( name = "OLDUNITS", order = 40)
  public Double getOldUnits() {
    return oldUnits;
  }

  /**
   * Sets the old number of units for the split. This is a  equired field according to the OFX
   * spec.
   *
   * @param oldUnits the old number of units.
   */
  public void setOldUnits(Double oldUnits) {
    this.oldUnits = oldUnits;
  }

  /**
   * Gets the new number of units for the split. This is a required field according to the OFX
   * spec.
   *
   * @return the new number of units.
   */
  @Element( name = "NEWUNITS", order = 50)
  public Double getNewUnits() {
    return newUnits;
  }

  /**
   * Sets the new number of units for the split. This is a required field according to the OFX
   * spec.
   *
   * @param newUnits the new number of units.
   */
  public void setNewUnits(Double newUnits) {
    this.newUnits = newUnits;
  }

  /**
   * Gets the numerator for the split ratio. This is a required field according to the OFX spec.
   *
   * @return the numerator for the split ratio
   */
  @Element( name = "NUMERATOR", order = 60)
  public Double getNumerator() {
    return numerator;
  }

  /**
   * Sets the numerator for the split ratio. This is a required field according to the OFX spec.
   *
   * @param numerator the numerator for the split ratio
   */
  public void setNumerator(Double numerator) {
    this.numerator = numerator;
  }

  /**
   * Gets the denominator for the split ratio. This is a required field according to the OFX spec.
   *
   * @return the numerator for the split ratio
   */
  @Element( name = "DENOMINATOR", order = 70)
  public Double getDenominator() {
    return denominator;
  }

  /**
   * Sets the denominator for the split ratio. This is a required field according to the OFX spec.
   *
   * @param denominator the numerator for the split ratio
   */
  public void setDenominator(Double denominator) {
    this.denominator = denominator;
  }

  /**
   * Gets the currency code for the transaction. Only one of currency code or original currency
   * code should be set according to the OFX spec. If neither are set, means the default currency.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the currency code for the transaction
   */
  @Element( name = "CURRENCY", order = 80)
  public String getCurrencyCode() {
    return currencyCode;
  }

  /**
   * sets the currency code for the transaction. Only one of currency code or original currency
   * code should be set according to the OFX spec. If neither are set, means the default currency.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the currency code for the transaction
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
  @Element( name = "ORIGCURRENCY", order = 90)
  public OriginalCurrency getOriginalCurrencyInfo() {
    return originalCurrencyInfo;
  }

  /**
   * Sets the original currency info for the transaction.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the original currency info for the transaction
   */
  public void setOriginalCurrencyInfo(OriginalCurrency originalCurrencyInfo) {
    this.originalCurrencyInfo = originalCurrencyInfo;
    this.currencyCode = null;
  }

  /**
   * Gets the cash for fractional units.
   *
   * @return the cash for fractional units
   */
  @Element( name = "FRACCASH", order = 100)
  public Double getCashForFractionalUnits() {
    return cashForFractionalUnits;
  }

  /**
   * Sets the cash for fractional units.
   *
   * @param cashForFractionalUnits the cash for fractional units
   */
  public void setCashForFractionalUnits(Double cashForFractionalUnits) {
    this.cashForFractionalUnits = cashForFractionalUnits;
  }

  /**
   * Gets the sub account type for the fund. (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the sub account fund
   */
  @Element( name = "SUBACCTFUND", order = 110)
  public String getSubAccountFund() {
    return subAccountFund;
  }

  /**
   * Sets the sub account type for the fund. (e.g. CASH, MARGIN, SHORT, OTHER).
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
   * Gets the 401K source for the transaction. Should be one of "PRETAX", "AFTERTAX", "MATCH",
   * "PROFITSHARING", "ROLLOVER", "OTHERVEST", "OTHERNONVEST".  This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the 401k source
   */
  @Element( name = "INV401KSOURCE", order = 120)
  public String get401kSource() {
    return inv401kSource;
  }

  /**
   * Sets the 401K source for the transaction. Should be one of "PRETAX", "AFTERTAX", "MATCH",
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
