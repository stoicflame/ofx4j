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

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Transaction for buying mutual funds.
 * @see "Section 13.9.2.4.4, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "BUYMF" )
public class BuyMutualFundTransaction extends BaseBuyInvestmentTransaction {

  private String buyType;
  private String relatedTransactionId;

  public BuyMutualFundTransaction() {
    super(TransactionType.BUY_MUTUAL_FUND);
  }


  /**
   * Gets the type of purchase (i.e. "BUY" or "BUYTOCOVER"). This is a required field according to
   * the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the buy type
   */
  @Element( name = "BUYTYPE", required = true, order = 20)
  public String getBuyType() {
    return buyType;
  }

  /**
   * Sets the type of purchase (i.e. "BUY" or "BUYTOCOVER"). This is a required field according to
   * the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param buyType the buy type
   */
  public void setBuyType(String buyType) {
    this.buyType = buyType;
  }

  /**
   * Gets the buy type as one of the well-known types.
   *
   * @return the type of purchase or null if it's not known
   */
  public BuyType getBuyTypeEnum() {
    return BuyType.fromOfx(buyType);
  }

  /**
   * Gets any related transaction id for a mutual fund purchase (e.g. for a mutual fund exchange).
   * This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the related transaction id
   */
  @Element( name = "RELFITID", order = 30)
  public String getRelatedTransactionId() {
    return relatedTransactionId;
  }

  /**
   * Sets any related transaction id for a mutual fund purchase (e.g. for a mutual fund exchange).
   * This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param relatedTransactionId the related transaction id
   */
  public void setRelatedTransactionId(String relatedTransactionId) {
    this.relatedTransactionId = relatedTransactionId;
  }
}
