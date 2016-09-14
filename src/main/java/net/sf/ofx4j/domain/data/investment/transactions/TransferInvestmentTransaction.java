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
import net.sf.ofx4j.domain.data.investment.positions.PositionType;
import net.sf.ofx4j.domain.data.seclist.SecurityId;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * Transaction for transfers.
 * @see "Section 13.9.2.4.4, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate("TRANSFER")
public class TransferInvestmentTransaction extends BaseOtherInvestmentTransaction {

  private SecurityId securityId;
  private String subAccountSecurity;
  private Double units;
  private String transferAction;
  private String positionType;
  private Double averageCostBasis;
  private Double unitPrice;
  private Date purchaseDate;
  private String inv401kSource;

  // TODO (jonp) -- INVACCTFROM

  public TransferInvestmentTransaction() {
    super(TransactionType.TRANSFER);
  }

  /**
   * Gets the id of the security that was transferred. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the security id of the security that was transferred
   */
  @ChildAggregate( required = true, order = 20 )
  public SecurityId getSecurityId() {
    return securityId;
  }

  /**
   * Sets the id of the security that was transferred. This is a required field according to the OFX
   * spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param securityId the security id of the security that was transferred
   */
  public void setSecurityId(SecurityId securityId) {
    this.securityId = securityId;
  }

  /**
    * Gets the sub account type for the security (e.g. CASH, MARGIN, SHORT, OTHER).
    * @see "Section 13.9.2.4.3, OFX Spec"
    *
    * @return the sub account type
    */
   @Element( name = "SUBACCTSEC", order = 30)
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
   * Gets the number of units of the security that was transferred. For security-based actions other
   * than stock splits, this is the quantity bought. For stocks, mutual funds, and others, this
   * is the number of shares. For bonds, this is the face value. For options, this is the number of
   * contacts. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the number of units transferred
   */
  @Element( name = "UNITS", required = true, order = 40)
  public Double getUnits() {
    return units;
  }

  /**
   * Sets the number of units of the security that was transferred. For security-based actions other
   * than stock splits, this is the quantity bought. For stocks, mutual funds, and others, this
   * is the number of shares. For bonds, this is the face value. For options, this is the number of
   * contacts. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param units the number of units transferred
   */
  public void setUnits(Double units) {
    this.units = units;
  }

  /**
   * Gets the type of transfer. One of "IN" or "OUT". This is a required field according to the
   * OFX spec.
   *
   * @return the type of transfer
   */
  @Element( name = "TFERACTION", required = true, order = 50)
  public String getTransferAction() {
    return transferAction;
  }

  /**
   * Sets the type of transfer. One of "IN" or "OUT". This is a required field according to the
   * OFX spec.
   *
   * @param transferAction the type of transfer
   */
  public void setTransferAction(String transferAction) {
    this.transferAction = transferAction;
  }

  /**
   * Gets the transfer action as one of the well-known types.
   *
   * @return the type of transfer or null if it's not well known
   */
  public TransferAction getTransferActionEnum() {
    return TransferAction.fromOfx(getTransferAction());
  }

  /**
   * Gets the type of position. One of "LONG" or "SHORT". This is a required field according to the
   * OFX spec.
   *
   * @return the position type
   */
  @Element( name = "POSTYPE", required = true, order = 60)
  public String getPositionType() {
    return positionType;
  }

  /**
   * Sets the type of position. One of "LONG" or "SHORT". This is a required field according to the
   * OFX spec.
   *
   * @param positionType the position type
   */
  public void setPositionType(String positionType) {
    this.positionType = positionType;
  }

  /**
   * Gets the position type as one of the well-known types.
   *
   * @return the position type or null if it's not well known
   */
  public PositionType getPositionTypeEnum() {
    return PositionType.fromOfx(getPositionType());
  }

  /**
   * Gets the average cost basis for the securities being transfered. This is an optional field
   * according to the ofx spec.
   *
   * @return the average cost basis
   */
  @Element( name = "AVGCOSTBASIS", order = 70)
  public Double getAverageCostBasis() {
    return averageCostBasis;
  }

  /**
   * Sets the average cost basis for the securities being transfered. This is an optional field
   * according to the ofx spec.
   *
   * @param averageCostBasis the average cost basis
   */
  public void setAverageCostBasis(Double averageCostBasis) {
    this.averageCostBasis = averageCostBasis;
  }

  /**
   * Gets the price per commonly-quoted unit. For stocks, mutual funds, and others, this is the
   * share price. For bonds, this is the percentage of par. For options, this is the per share (not
   * per contact) price. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the per unit price
   */
  @Element( name = "UNITPRICE", required = true, order = 80)
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
   * Gets the original date of purchase for the securities. This is an optional field according to
   * the ofx spec.
   *
   * @return the original date of purchase
   */
  @Element( name = "DTPURCHASE", order = 90)
  public Date getPurchaseDate() {
    return purchaseDate;
  }

  /**
   * Sets the original date of purchase for the securities. This is an optional field according to
   * the ofx spec.
   *
   * @param purchaseDate the original date of purchase
   */
  public void setPurchaseDate(Date purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  /**
   * Gets the 401K source for the transfer. Should be one of "PRETAX", "AFTERTAX", "MATCH",
   * "PROFITSHARING", "ROLLOVER", "OTHERVEST", "OTHERNONVEST".  This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @return the state withholding
   */
  @Element( name = "INV401KSOURCE", order = 100)
  public String get401kSource() {
    return inv401kSource;
  }

  /**
   * Sets the 401K source for the transfer. Should be one of "PRETAX", "AFTERTAX", "MATCH",
   * "PROFITSHARING", "ROLLOVER", "OTHERVEST", "OTHERNONVEST".  This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.3, OFX Spec"
   *
   * @param inv401kSource the state withholding
   */
  public void set401kSource(String inv401kSource) {
    this.inv401kSource = inv401kSource;
  }

  /**
   * Gets the 401(k) source as one of the well-known types.
   *
   * @return the type of close or null if it's not well known.
   */
  public Inv401KSource get401kSourceEnum() {
    return Inv401KSource.fromOfx(get401kSource());
  }
}
