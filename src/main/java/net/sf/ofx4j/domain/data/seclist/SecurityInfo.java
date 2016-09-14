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

package net.sf.ofx4j.domain.data.seclist;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * Info about a security.
 * @see "Section 13.8.5.1, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "SECINFO" )
public class SecurityInfo {
  private SecurityId securityId;
  private String securityName;
  private String tickerSymbol;
  private String fiId;
  private String rating;
  private Double unitPrice;
  private Date marketValueDate;
  private String currencyCode;
  private String memo;

  /**
   * Gets the unique security id for the security. This is a required field according to the OFX
   * spec.
   *
   * @return the security id
   */
  @ChildAggregate( required = true, order = 10 )
  public SecurityId getSecurityId() {
    return securityId;
  }

  /**
   * Sets the unique security id for the security. This is a required field according to the OFX
   * spec.
   *
   * @param securityId the security id
   */
  public void setSecurityId(SecurityId securityId) {
    this.securityId = securityId;
  }

  /**
   * Gets the full name of the security. This is a required field according to the OFX spec.
   *
   * @return the full name of the security
   */
  @Element( name = "SECNAME", required = true, order = 20)
  public String getSecurityName() {
    return securityName;
  }

  /**
   * Sets the full name of the security. This is a required field according to the OFX spec.
   *
   * @param securityName the full name of the security
   */
  public void setSecurityName(String securityName) {
    this.securityName = securityName;
  }

  /**
   * Gets the ticker symbol for the security. This is an optional field according to the OFX spec.
   *
   * @return the ticket symbol or null if there's no ticker symbol
   */
  @Element( name = "TICKER", order = 30)
  public String getTickerSymbol() {
    return tickerSymbol;
  }

  /**
   * Sets the ticker symbol for the security. This is an optional field according to the OFX spec.
   *
   * @param tickerSymbol the ticket symbol or null if there's no ticker symbol
   */
  public void setTickerSymbol(String tickerSymbol) {
    this.tickerSymbol = tickerSymbol;
  }

  /**
   * Gets the FI ID number for the security. This is an optional field according to the OFX spec.
   *
   * @return the FI ID number for the security
   */
  @Element( name = "FIID", order = 40)
  public String getFiId() {
    return fiId;
  }

  /**
   * Sets the FI ID number for the security. This is an optional field according to the OFX spec.
   *
   * @param fiId the FI ID number for the security
   */
  public void setFiId(String fiId) {
    this.fiId = fiId;
  }

  /**
   * Gets the rating of the security. This is an optional field according to the OFX spec.
   *
   * @return the rating
   */
  @Element( name = "RATING", order = 50)
  public String getRating() {
    return rating;
  }

  /**
   * Sets the rating of the security. This is an optional field according to the OFX spec.
   *
   * @param rating the rating
   */
  public void setRating(String rating) {
    this.rating = rating;
  }

  /**
   * Gets the price per commonly-quoted unit. For stocks, mutual funds, and others, this is the
   * share price. For bonds, this is the percentage of par. For options, this is the per share (not
   * per contact) price. This is a noptional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the per unit price
   */
  @Element( name = "UNITPRICE", order = 60)
  public Double getUnitPrice() {
    return unitPrice;
  }

  /**
   * Sets the price per commonly-quoted unit. For stocks, mutual funds, and others, this is the
   * share price. For bonds, this is the percentage of par. For options, this is the per share (not
   * per contact) price. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param unitPrice the per unit price
   */
  public void setUnitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
  }

  /**
   * Gets the date as-of for the unit price. This is an optional field according to the OFX spec.
   *
   * @return the date as-of for the unit price
   */
  @Element( name = "DTASOF", order = 70)
  public Date getUnitPriceAsOfDate() {
    return marketValueDate;
  }

  /**
   * Sets the date as-of for the unit price. This is an optional field according to the OFX spec.
   *
   * param marketValueDate the date as-of for the unit price
   */
  public void setUnitPriceAsOfDate(Date marketValueDate) {
    this.marketValueDate = marketValueDate;
  }

  /**
   * Gets the overriding currency code for the security. If not set, implies the default currency.
   * This is an optional field according to the OFX spec.
   *
   * @return the overriding currency code or null to mean the default currency
   */
  @Element( name = "CURRENCY", order = 80)
  public String getCurrencyCode() {
    return currencyCode;
  }

  /**
   * Sets the overriding currency code for the security. If not set, implies the default currency.
   * This is an optional field according to the OFX spec.
   *
   * @param currencyCode the overriding currency code or null to mean the default currency
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  /**
   * Gets any memo associated with the security. This is an optional field according to the OFX
   * spec.
   *
   * @return the memo
   */
  @Element( name = "MEMO", order = 90)
  public String getMemo() {
    return memo;
  }

  /**
   * Sets any memo associated with the security. This is an optional field according to the OFX
   * spec.
   *
   * @param memo the memo
   */
  public void setMemo(String memo) {
    this.memo = memo;
  }
}
