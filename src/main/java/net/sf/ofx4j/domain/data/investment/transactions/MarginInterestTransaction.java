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
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Transaction for journal security transactions between sub-accounts within the same investment
 * account.
 * @see "Section 13.9.2.4.4, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "MARGININTEREST" )
public class MarginInterestTransaction extends BaseOtherInvestmentTransaction {

  private Double total;
  private String subAccountFund;
  private String currencyCode;
  private OriginalCurrency originalCurrencyInfo;

  public MarginInterestTransaction() {
    super(TransactionType.MARGIN_INTEREST);
  }

  /**
   * Gets the sub account type the margin interest affects (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the sub account type
   */
  @Element( name = "SUBACCTFUND", order = 30)
  public String getSubAccountFund() {
    return subAccountFund;
  }

  /**
   * Sets the sub account type the margin interest affects (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param subAccountFund the sub account type
   */
  public void setSubAccountFund(String subAccountFund) {
    this.subAccountFund = subAccountFund;
  }

  /**
   * Gets the result of getSubAccountFund as one of the well-known types.
   *
   * @return the type of null if it wasn't one of the well known types.
   */
  public SubAccountType getSubAccountFundEnum() {
    String type = getSubAccountFund();
    return type != null ? SubAccountType.valueOf(type) : null;
  }

  /**
   * Gets the total for the transaction.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the total
   */
  @Element( name = "TOTAL", order = 40)
  public Double getTotal() {
    return total;
  }

  /**
   * Sets the total for the transaction.
   * @see "Section 13.9.2.4.4, OFX Spec"
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
  @Element( name = "CURRENCY", order = 110)
  public String getCurrencyCode() {
    return currencyCode;
  }

  /**
   * Sets the currency code for the transaction. Only one of currency code or original currency
   * info should be set according to the OFX spec. If neither are set, means the default currency.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param currencyCode the currency code for the transaction.
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    this.originalCurrencyInfo = null;
  }

  /**
   * Gets the original currency info for the transaction.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the original currency info for the transaction.
   */
  @Element( name = "ORIGCURRENCY", order = 120)
  public OriginalCurrency getOriginalCurrencyInfo() {
    return originalCurrencyInfo;
  }

  /**
   * Sets the original currency info for the transaction.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param originalCurrency the original currency info for the transaction.
   */
  public void SetOriginalCurrency(OriginalCurrency originalCurrency) {
    this.originalCurrencyInfo = originalCurrency;
    this.currencyCode = null;
  }
}
