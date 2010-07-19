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

import net.sf.ofx4j.domain.data.investment.positions.ShortOptionSecurity;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Transaction for selling options.
 * @see "Section 13.9.2.4.4, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "SELLOPT" )
public class SellOptionTransaction extends BaseSellInvestmentTransaction {

  private String optionSellType;
  private Integer sharesPerContact;
  private String relatedTransactionId;
  private String relatedType;
  private String secured;

  public SellOptionTransaction() {
    super(TransactionType.SELL_OPTION);
  }

  /**
   * Gets the type of option sale (i.e. "SELLTOCLOSE" or "SELLTOOPEN"). This is a required field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the option sell type
   */
  @Element( name = "OPTSELLTYPE", required = true, order = 20)
  public String getOptionSellType() {
    return optionSellType;
  }

  /**
   * Sets the type of option sale (i.e. "SELLTOCLOSE" or "SELLTOOPEN"). This is a required field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param optionSellType the option sell type
   */
  public void setOptionSellType(String optionSellType) {
    this.optionSellType = optionSellType;
  }

  /**
   * Gets the option sell type as one of the well-known types.
   *
   * @return the type of sale or null if it's not known.
   */
  public OptionSellType getOptionSellTypeEnum() {
    return OptionSellType.fromOfx(optionSellType);
  }

  /**
   * Gets the number of shares per contact. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the number of shares per contact
   */
  @Element( name = "SHPERCTRCT", required = true, order = 30)
  public Integer getSharesPerContact() {
    return sharesPerContact;
  }

  /**
   * Sets the number of shares per contact. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param sharesPerContact the number of shares per contact
   */
  public void setSharesPerContact(Integer sharesPerContact) {
    this.sharesPerContact = sharesPerContact;
  }

  /**
   * Gets a related transaction for the option sale for complex option transactions. This
   * is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return The related transaction id
   */
  @Element( name = "RELFITID", order = 40)
  public String getRelatedTransactionId() {
    return relatedTransactionId;
  }

  /**
   * Sets a related transaction for the option sale for complex option transactions. This
   * is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param relatedTransactionId The related transaction id
   */
  public void setRelatedTransactionId(String relatedTransactionId) {
    this.relatedTransactionId = relatedTransactionId;
  }

  /**
   * Gets the type for the related transaction. One of "SPREAD", "STRADDLE", "NONE", "OTHER". This
   * is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return The related tansaction type
   */
  @Element( name = "RELTYPE", order = 50)
  public String getRelatedType() {
    return relatedType;
  }

  /**
   * Sets the type for the related transaction. One of "SPREAD", "STRADDLE", "NONE", "OTHER". This
   * is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param relatedType The related tansaction type
   */
  public void setRelatedType(String relatedType) {
    this.relatedType = relatedType;
  }

  /**
   * Gets the related transaction as one of the well-known types.
   *
   * @return the related tansaction type or null if it's not well known
   */
  public RelatedOptionType getRelatedTypeEnum() {
    return RelatedOptionType.fromOfx(getRelatedType());
  }

  /**
   * Gets how the option sale is secured. One of "NAKED" or "COVERED". This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return how the option sale is secured
   */
  @Element( name = "SECURED", order = 60)
  public String getSecured() {
    return secured;
  }

  /**
   * Sets how the option sale is secured. One of "NAKED" or "COVERED". This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param secured how the option sale is secured
   */
  public void setSecured(String secured) {
    this.secured = secured;
  }

  /**
   * Gets how the option sale is secured as one of the well-known types.
   *
   * @return the type indicating how the option is secured or null if it's not well known.
   */
  public ShortOptionSecurity getSecuredEnum() {
    return  ShortOptionSecurity.fromOfx(getSecured());
  }
}
