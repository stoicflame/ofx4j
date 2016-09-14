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
 * Info about a debt security.
 * @see "Section 13.8.5.2, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "DEBTINFO" )
public class DebtSecurityInfo extends BaseSecurityInfo {

  private Double parValue;
  private String debtType;
  private String debtClass;
  private Double couponRate;
  private Date nextMaturityDate;
  private String couponFrequency;
  private Double callPrice;
  private Double yieldToCall;
  private Date nextCallDate;
  private String callType;
  private Double yieldToMaturity;
  private Date debtMaturityDate;
  private String assetClass;
  private String fiAssetClass;

  /**
   * Gets the par value of the debt. This is a required field according to the OFX spec.
   *
   * @return the par value of the debt
   */
  @Element( name = "PARVALUE", required=true, order = 20)
  public Double getParValue() {
    return parValue;
  }

  /**
   * Sets the par value of the debt. This is a required field according to the OFX spec.
   *
   * @param parValue the par value of the debt
   */
  public void setParValue(Double parValue) {
    this.parValue = parValue;
  }

  /**
   * Gets the type of debt. One of "COUPON" or "ZERO". This is a required field according to the
   * OFX spec.
   *
   * @return the type of debt
   */
  @Element( name = "DEBTTYPE", required=true, order = 30)
  public String getDebtType() {
    return debtType;
  }

  /**
   * Sets the type of debt. One of "COUPON" or "ZERO". This is a required field according to the
   * OFX spec.
   *
   * @param debtType the type of debt
   */
  public void setDebtType(String debtType) {
    this.debtType = debtType;
  }

  /**
   * Gets the type of debt as one of the well-known types.
   *
   * @return the type of debt or null if it's not one of the well-known types
   */
  public DebtType getDebtTypeEnum() {
    return DebtType.fromOfx(getDebtType());
  }

  /**
   * Gets the class of debt. One of "TREASURY", "MUNICIPAL", "CORPORATE", or "OTHER".
   * This is an optional field according to the OFX spec.
   *
   * @return the class of debt
   */
  @Element( name = "DEBTCLASS", order = 40)
  public String getDebtClass() {
    return debtClass;
  }

  /**
   * Sets the class of debt. One of "TREASURY", "MUNICIPAL", "CORPORATE", or "OTHER".
   * This is an optional field according to the OFX spec.
   *
   * @param debtClass the class of debt
   */
  public void setDebtClass(String debtClass) {
    this.debtClass = debtClass;
  }

  /**
   * Gets the class of debt as one of the well-known types.
   *
   * @return the class of debt or null if it's not one of the well-known types
   */
  public DebtClass getDebtClassEnum() {
    return DebtClass.fromOfx(debtClass);
  }

  /**
   * Gets the coupon rate of the debt for the next closest call date.
   * This is an optional field according to the OFX spec.
   *
   * @return the coupon rate
   */
  @Element( name = "COUPONRT", order = 50)
  public Double getCouponRate() {
    return couponRate;
  }

  /**
   * Sets the coupon rate of the debt for the next closest call date.
   * This is an optional field according to the OFX spec.
   *
   * @param couponRate the coupon rate
   */
  public void setCouponRate(Double couponRate) {
    this.couponRate = couponRate;
  }

  /**
   * Gets the next maturity date for the next coupon.
   * This is an optional field according to the OFX spec.
   *
   * @return the maturity date for the next coupon
   */
  @Element( name = "DTCOUPON", order = 60)
  public Date getNextMaturityDate() {
    return nextMaturityDate;
  }

  /**
   * Sets the next maturity date for the next coupon.
   * This is an optional field according to the OFX spec.
   *
   * @param nextMaturityDate the maturity date for the next coupon.
   */
  public void setNextMaturityDate(Date nextMaturityDate) {
    this.nextMaturityDate = nextMaturityDate;
  }

  /**
   * Gets the coupon frequency. One of "MONTHLY", "QUARTERLY", "SEMIANNUAL", "ANNUAL", or "OTHER".
   * This is an optional field according to the OFX spec.
   *
   * @return the coupon frequency
   */
  @Element( name = "COUPONFREQ", order = 70)
  public String getCouponFrequency() {
    return couponFrequency;
  }

  /**
   * Sets the coupon frequency. One of "MONTHLY", "QUARTERLY", "SEMIANNUAL", "ANNUAL", or "OTHER".
   * This is an optional field according to the OFX spec.
   *
   * @param couponFrequency the coupon frequency
   */
  public void setCouponFrequency(String couponFrequency) {
    this.couponFrequency = couponFrequency;
  }

  /**
   * Gets the coupon frequency as one of the well-known types.
   *
   * @return the coupon frequency or null if it's not one of the well-known types
   */
  public CouponFrequency getCouponFrequencyEnum() {
    return CouponFrequency.fromOfx(getCouponFrequency());
  }

  /**
   * Gets the bond price. This is an optional field according to the OFX spec.
   *
   * @return the bond price
   */
  @Element( name = "CALLPRICE", order = 80)
  public Double getCallPrice() {
    return callPrice;
  }

  /**
   * Sets the bond price. This is an optional field according to the OFX spec.
   *
   * @param callPrice the bond price
   */
  public void setCallPrice(Double callPrice) {
    this.callPrice = callPrice;
  }

  /**
   * Gets the yield to call as a rate. This is an optional field according to the OFX spec.
   *
   * @return the yield to call rate
   */
  @Element( name = "YIELDTOCALL", order = 90)
  public Double getYieldToCall() {
    return yieldToCall;
  }

  /**
   * Sets the yield to call as a rate. This is an optional field according to the OFX spec.
   *
   * @param yieldToCall the yield to call rate
   */
  public void setYieldToCall(Double yieldToCall) {
    this.yieldToCall = yieldToCall;
  }

  /**
   * Gets the next call date. This is an optional field according to the OFX spec.
   *
   * @return the next call date.
   */
  @Element( name = "DTCALL", order = 100)
  public Date getNextCallDate() {
    return nextCallDate;
  }

  /**
   * Sets the next call date. This is an optional field according to the OFX spec.
   *
   * @param nextCallDate the next call date.
   */
  public void setNextCallDate(Date nextCallDate) {
    this.nextCallDate = nextCallDate;
  }

  /**
   * Gets the type of call.
   *
   * @return the type of call
   */
  @Element( name = "CALLTYPE", order = 110)
  public String getCallType() {
    return callType;
  }

  /**
   * Sets the type of call.
   *
   * @param callType the type of call
   */
  public void setCallType(String callType) {
    this.callType = callType;
  }

  /**
   * Gets the type of call as one of the well-known types.
   *
   * @return the type of call or null if it's not one of the well-known types
   */
  public CallType getCallTypeEnum() {
    return CallType.fromOfx(getCallType());
  }

  /**
   * Gets the yield to maturity as a rate. This is an optional field according to the OFX spec.
   *
   * @return the yield to call rate
   */
  @Element( name = "YIELDTOMAT", order = 120)
  public Double getYieldToMaturity() {
    return yieldToMaturity;
  }

  /**
   * Sets the yield to maturity as a rate. This is an optional field according to the OFX spec.
   *
   * @param yieldToMaturity the yield to call rate
   */
  public void setYieldToMaturity(Double yieldToMaturity) {
    this.yieldToMaturity = yieldToMaturity;
  }

  /**
   * Gets the date when the debt matures. This is an optional field according to the OFX spec.
   *
   * @return the date when the debt matures
   */
  @Element( name = "DTMAT", order = 130)
  public Date getDebtMaturityDate() {
    return debtMaturityDate;
  }

  /**
   * Sets the date when the debt matures. This is an optional field according to the OFX spec.
   *
   * @param debtMaturityDate the date when the debt matures
   */
  public void setDebtMaturityDate(Date debtMaturityDate) {
    this.debtMaturityDate = debtMaturityDate;
  }

  /**
   * Gets the asset class of the debt. This is an optional field according to the OFX spec.
   *
   * @return the asset class of the debt
   */
  @Element( name = "ASSETCLASS", order = 140)
  public String getAssetClass() {
    return assetClass;
  }

  /**
   * Sets the asset class of the debt. This is an optional field according to the OFX spec.
   *
   * @param assetClass the asset class of the debt
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
   * Gets the FI-defined asset class of the debt. This is an optional field according to the OFX
   * spec.
   *
   * @return the FI-defined asset class of the debt
   */
  @Element( name = "FIASSETCLASS", order = 150)
  public String getFiAssetClass() {
    return fiAssetClass;
  }

  /**
   * Sets the FI-defined asset class of the debt. This is an optional field according to the OFX
   * spec.
   *
   * @param fiAssetClass the FI-defined asset class of the debt
   */
  public void setFiAssetClass(String fiAssetClass) {
    this.fiAssetClass = fiAssetClass;
  }
}
