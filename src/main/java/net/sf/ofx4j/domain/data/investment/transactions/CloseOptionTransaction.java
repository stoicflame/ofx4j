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
import net.sf.ofx4j.domain.data.seclist.SecurityId;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Transaction for closing an option position due to expiration, exercise, or assignment.
 * @see "Section 13.9.2.4.4, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "CLOSUREOPT" )
public class CloseOptionTransaction extends BaseOtherInvestmentTransaction
    implements TransactionWithSecurity {

  private SecurityId securityId;
  private String optionAction;
  private Double units;
  private Integer sharesPerContact;
  private String subAccountSecurity;
  private String relatedTransactionId;
  private Double gain;

  public CloseOptionTransaction() {
    super(TransactionType.CLOSE_OPTION);
  }

  /**
   * Gets the security id of the option.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the security id of the option
   */
  @ChildAggregate( order = 20 )
  public SecurityId getSecurityId() {
    return securityId;
  }

  /**
   * Sets the security id of the option.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param securityId the security id of the option
   */
  public void setSecurityId(SecurityId securityId) {
    this.securityId = securityId;
  }

  /**
   * Gets the action being performed (i.e. "EXERCISE", "ASSIGN", "EXPIRE" This is a required field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the option action
   */
  @Element( name = "OPTACTION", required = true, order = 30)
  public String getOptionAction() {
    return optionAction;
  }

  /**
   * Sets the action being performed (i.e. "EXERCISE", "ASSIGN", "EXPIRE" This is a required field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param optionAction the option action
   */
  public void setOptionAction(String optionAction) {
    this.optionAction = optionAction;
  }

  /**
   * Gets the action as one of the well-known types.
   *
   * @return the type of close or null if it's not a well-known type
   */
  public CloseOptionAction getOptionActionEnum() {
    return  CloseOptionAction.fromOfx(getOptionAction());
  }

  /**
   * Gets the number of units of the option that were closed. This is a required field according
   * to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the number of units closed
   */
  @Element( name = "UNITS", required = true, order = 40)
  public Double getUnits() {
    return units;
  }

  /**
   * Sets the number of units of the option that were closed. This is a required field according
   * to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param units the number of units closed
   */
  public void setUnits(Double units) {
    this.units = units;
  }

  /**
   * Gets the number of shares per contact. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the number of shares per contact
   */
  @Element( name = "SHPERCTRCT", required = true, order = 50)
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
   * Gets the sub account type for the security (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the sub account type
   */
  @Element( name = "SUBACCTSEC", required = true, order = 60)
  public String getSubAccountSecurity() {
    return subAccountSecurity;
  }

  /**
   * Sets the sub account type for the security (e.g. CASH, MARGIN, SHORT, OTHER).
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param subAccountSecurity the sub account type
   */
  public void setSubAccountSecurity(String subAccountSecurity) {
    this.subAccountSecurity = subAccountSecurity;
  }

  /**
   * Gets the result of getSubAccountFund as one of the well-known types.
   *
   * @return the type of null if it wasn't one of the well known types
   */
  public SubAccountType getSubAccountSecurityEnum() {
    return SubAccountType.fromOfx(getSubAccountSecurity());
  }

  /**
   * Gets the related transaction id for the related buy or sell corresponding to the
   * EXERCISE or ASSIGN action. This is a required field according to the OFX spec if the
   * action or EXERCISE or ASSIGN.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the related transaction id
   */
  @Element( name = "RELFITID", order = 70)
  public String getRelatedTransactionId() {
    return relatedTransactionId;
  }

  /**
   * Sets the related transaction id for the related buy or sell corresponding to the
   * EXERCISE or ASSIGN action. This is a required field according to the OFX spec if the
   * action or EXERCISE or ASSIGN.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param relatedTransactionId the related transaction id
   */
  public void setRelatedTransactionId(String relatedTransactionId) {
    this.relatedTransactionId = relatedTransactionId;
  }

  /**
   * Gets the gain related to the transaction. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the gain related to the transaction
   */
  @Element( name = "GAIN", order = 80)
  public Double getGain() {
    return gain;
  }

  /**
   * Sets the gain related to the transaction. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param gain the gain related to the transaction
   */
  public void setGain(Double gain) {
    this.gain = gain;
  }
}
