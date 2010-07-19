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

package net.sf.ofx4j.domain.data.investment.positions;

import net.sf.ofx4j.domain.data.investment.accounts.SubAccountType;
import net.sf.ofx4j.domain.data.seclist.SecurityId;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.Date;

/**
 * Base class for the various types of positions.
 * <br>
 * This class exposes a read-only view of the flattened aggregates that are
 * common to all positions as a convenience to application
 * developers who may not find the ofx aggregation model intuitive.
 *
 * @author Jon Perlow
 */
public class BasePosition {
  private InvestmentPosition investmentPosition;

  /**
   * Gets the investment position child aggregate.
   *
   * @return the investment position child aggregate
   */
  @ChildAggregate( required = true, order = 10 )
  public InvestmentPosition getInvestmentPosition() {
    return investmentPosition;
  }

  /**
   * Sets the investment position child aggregate.
   *
   * @param investmentPosition the investment position child aggregate
   */
  public void setInvestmentPosition(InvestmentPosition investmentPosition) {
    this.investmentPosition = investmentPosition;
  }

  /**
   * Gets the security id for the position. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.6.1, OFX Spec"
   *
   * @return the security id for the position
   */
  public SecurityId getSecurityId() {
    return getInvestmentPosition().getSecurityId();
  }

  /**
   * Gets the sub-account type. One of "CASH", "MARGIN", "SHORT", "OTHER". This is a required field
   * according to the OFX spec.
   * @see "Section 13.9.2.6.1, OFX Spec"
   *
   * @return the sub-account type
   */
  public String getHeldInAccount() {
    return getInvestmentPosition().getHeldInAccount();
  }

  /**
   * Gets the sub-account type as one of the well-known types.
   * @see "Section 13.9.2.6.1, OFX Spec"
   *
   * @return the sub-account type or null if it's not one of the well-known types
   */
  SubAccountType getHeldInAccountEnum() {
    return SubAccountType.fromOfx(getHeldInAccount());
  }

  /**
   * Gets the position type. One of SHORT or LONG. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.6.1, OFX Spec"
   *
   * @return the position type
   */
  public String getPositionType() {
    return getInvestmentPosition().getPositionType();
  }

  /**
   * Gets the position type as one of the well-known types.
   * @see "Section 13.9.2.6.1, OFX Spec"
   *
   * @return the position type or null if it's not one of the well-known types
   */
  public PositionType getPositionTypeEnum() {
    return PositionType.fromOfx(getPositionType());
  }

  /**
   * Gets the number of units in the position. For stocks, mutual funds, and others, this
   * is the number of shares. For bonds, this is the face value. For options, this is the number of
   * contacts. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.6.1, OFX Spec"
   *
   * @return the number of units in the position
   */
  public Double getUnits() {
    return getInvestmentPosition().getUnits();
  }

  /**
   * Gets the price per commonly-quoted unit. For stocks, mutual funds, and others, this is the
   * share price. For bonds, this is the percentage of par. For options, this is the per share (not
   * per contact) price. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.6.1, OFX Spec"
   *
   * @return the per unit price
   */
  public Double getUnitPrice() {
    return getInvestmentPosition().getUnitPrice();
  }

  /**
   * Gets the market value of this position. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.6.1, OFX Spec"
   *
   * @return the market value of the position
   */
  public Double getMarketValue() {
    return getInvestmentPosition().getMarketValue();
  }

  /**
   * Gets the date and time of the unit price and market value. This is a required field according
   * to the OFX spec.
   * @see "Section 13.9.2.6.1, OFX Spec"
   *
   * @return the market value date
   */
  public Date getMarketValueDate() {
    return getInvestmentPosition().getMarketValueDate();
  }

  /**
   * Gets the currency code of the position. This is an optional field according to the OFX spec.
   * If not present, it's the default currency of the account.
   * @see "Section 13.9.2.6.1, OFX Spec"
   *
   * @return the currency code of the position or null for the default currency
   */
  public String getCurrencyCode() {
    return getInvestmentPosition().getCurrencyCode();
  }

  /**
   * Gets the memo associated with the position. This is an optional field according to the OFX
   * spec.
   * @see "Section 13.9.2.6.1, OFX Spec"
   *
   * @return the memo
   */
  public String getMemo() {
    return getInvestmentPosition().getMemo();
  }

  /**
   * Gets the 401K source for the sale. Should be one of "PRETAX", "AFTERTAX", "MATCH",
   * "PROFITSHARING", "ROLLOVER", "OTHERVEST", "OTHERNONVEST".  This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the 401k source
   */
  public String get401kSource() {
    return getInvestmentPosition().get401kSource();
  }

  /**
   * Gets the 401k source as one of the well-known types.
   *
   * @return the 401k source or null if it's not one of the well-known types
   */
  public Inv401KSource get401kSourceEnum() {
    return Inv401KSource.fromOfx(get401kSource());
  }
}
