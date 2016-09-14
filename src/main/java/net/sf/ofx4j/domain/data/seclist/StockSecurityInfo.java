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
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * Info about a stock security.
 * @see "Section 13.8.5.6, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "STOCKINFO" )
public class StockSecurityInfo extends BaseSecurityInfo {

  private String stockType;
  private Double yield;
  private Date dateYieldAsOf;
  private String assetClass;
  private String fiAssetClass;

  /**
   * Gets the type of stock. One of "COMMON", "PREFERRED", "CONVERTIBLE", or "OTHER". This is an
   * optional field according to the OFX spec.
   *
   * @return the type of stock
   */
  @Element( name = "STOCKTYPE", order = 20)
  public String getType() {
    return stockType;
  }

  /**
   * Sets the type of stock. One of "COMMON", "PREFERRED", "CONVERTIBLE", or "OTHER". This is an
   * optional field according to the OFX spec.
   *
   * @param stockType the type of stock
   */
  public void setType(String stockType) {
    this.stockType = stockType;
  }

  /**
   * Gets the type of stock as one of the well-known types.
   *
   * @return the type of stock or null if it's not one of the well-known types
   */
  public StockType getTypeEnum() {
    return StockType.fromOfx(getType());
  }

  /**
   * Gets the current yield reported as the dividend expressed as a portion of the current stock
   * price, a rate. This is an optional field according to the OFX spec.
   *
   * @return the dividend yield
   */
  @Element( name = "YIELD", order = 30)
  public Double getYield() {
    return yield;
  }

  /**
   * Sets the current yield reported as the dividend expressed as a portion of the current stock
   * price, a rate. This is an optional field according to the OFX spec.
   *
   * @param yield the dividend yield
   */
  public void setYield(Double yield) {
    this.yield = yield;
  }

  /**
   * Gets the as-of date for the yield. This is an optional field according to the OFX spec.
   *
   * @return the as-of date for the yield
   */
  @Element( name = "DTYIELDASOF", order = 40)
  public Date getDateYieldAsOf() {
    return dateYieldAsOf;
  }

  /**
   * Sets the as-of date for the yield. This is an optional field according to the OFX spec.
   *
   * @param dateYieldAsOf the as-of date for the yield
   */
  public void setDateYieldAsOf(Date dateYieldAsOf) {
    this.dateYieldAsOf = dateYieldAsOf;
  }

  /**
   * Gets the asset class of the stock. This is an optional field according to the OFX spec.
   *
   * @return the asset class of the stock
   */
  @Element( name = "ASSETCLASS", order = 50)
  public String getAssetClass() {
    return assetClass;
  }

  /**
   * Sets the asset class of the stock. This is an optional field according to the OFX spec.
   *
   * @param assetClass the asset class of the stock
   */
  public void setAssetClass(String assetClass) {
    this.assetClass = assetClass;
  }

  /**
   * Gets the assert class as one of the well-known types.
   *
   * @return the asset class or null if it's not one of the well-known types
   */
  public AssetClass getAssetClassEnum() {
    return AssetClass.fromOfx(getAssetClass());
  }

  /**
   * Gets the FI-defined asset class of the stock. This is an optional field according to the OFX
   * spec.
   *
   * @return the FI-defined asset class of the stock
   */
  @Element( name = "FIASSETCLASS", order = 60)
  public String getFiAssetClass() {
    return fiAssetClass;
  }

  /**
   * Sets the FI-defined asset class of the stock. This is an optional field according to the OFX
   * spec.
   *
   * @param fiAssetClass the FI-defined asset class of the stock
   */
  public void setFiAssetClass(String fiAssetClass) {
    this.fiAssetClass = fiAssetClass;
  }
}
