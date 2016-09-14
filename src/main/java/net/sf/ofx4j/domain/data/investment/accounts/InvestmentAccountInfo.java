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

package net.sf.ofx4j.domain.data.investment.accounts;

import net.sf.ofx4j.domain.data.common.AccountDetails;
import net.sf.ofx4j.domain.data.common.AccountInfo;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Aggregate for the info about a brokerage account.
 *
 * @author Jon Perlow
 * @see "OFX Spec, Section 13.6.2"
 */
@Aggregate( "INVACCTINFO" )
public class InvestmentAccountInfo implements AccountInfo {

  private InvestmentAccountDetails investmentAccount;
  private String unitedStatesAccountType;
  private Boolean supportsChecking;
  private String activationStatus;
  private String investmentAccountType;
  private String optionLevel;

  /**
   * Gets the investment account this information is referencing.
   *
   * @return the investment account this information is referencing
   */
  @ChildAggregate( name = "INVACCTFROM", required = true, order = 0 )
  public InvestmentAccountDetails getInvestmentAccount() {
    return investmentAccount;
  }

  /**
   * Sets the investment account this information is referencing. This is a required field
   * according to the OFX spec.
   *
   * @param investmentAccount the investment account this information is referencing
   */
  public void setInvestmentAccount(InvestmentAccountDetails investmentAccount) {
    this.investmentAccount = investmentAccount;
  }

  // Inherited.
  public AccountDetails getAccountDetails() {
    return getInvestmentAccount();
  }

  /**
   * Gets the United States account type. This is a required field according to the OFX spec.
   * @see "OFX Spec, Section 13.6.1"
   *
   * @return the United States account type
   */
  @Element( name = "USPRODUCTTYPE", required = true, order = 10)
  public String getUnitedStatesAccountType() {
    return unitedStatesAccountType;
  }

  /**
   * Sets United States account type. This is a required field according to the OFX spec.
   * @see "OFX Spec, Section 13.6.1"
   *
   * @param unitedStatesAccountType the United States account type
   */
  public void setUnitedStatesAccountType(String unitedStatesAccountType) {
    this.unitedStatesAccountType = unitedStatesAccountType;
  }

  /**
   * Gets the United States account type as one of the well-known types.
   *
   * @return the account type or null if it's not one of the well-known types
   */
  public UnitedStatesAccountType getUnitedStatesAccountTypeEnum() {
    return UnitedStatesAccountType.fromOfx(unitedStatesAccountType);
  }

  /**
   * Gets whether the account supports checking. This is a required field according to the OFX spec.
   * @see "OFX Spec, Section 13.6.1"
   *
   * @return whether the account supports checking
   */
  @Element( name = "CHECKING", required = true, order = 20)
  public Boolean getSupportsChecking() {
    return supportsChecking;
  }

  /**
   * Sets whether the account supports checking. This is a required field according to the OFX spec.
   * @see "OFX Spec, Section 13.6.1"
   *
   * @param supportsChecking whether the account supports checking
   */
  public void setSupportsChecking(Boolean supportsChecking) {
    this.supportsChecking = supportsChecking;
  }

  /**
   * Gets the activation status for investment statement download. This is a required field
   * according to the OFX spec.
   *
   * @return the activation status
   */
  @Element( name = "SVCSTATUS", required = true, order = 30)
  public String getActivationStatus() {
    return activationStatus;
  }

  /**
   * Sets the activation status for investment statement download. This is a required field
   * according to the OFX spec.
   *
   * @param activationStatus the activation status
   */
  public void setActivationStatus(String activationStatus) {
    this.activationStatus = activationStatus;
  }

  /**
   * Gets the activation status as one of the well-known types.
   *
   * @return the activation status or null if it wasn't one of the well known types
   */
  public ActivationStatus getActivationStatusEnum() {
    return ActivationStatus.fromOfx(getActivationStatus());
  }

  /**
   * Gets the type of investment account. One of "INDIVIDUAL", "JOINT", "TRUST", or "CORPORATE".
   * This is an optional field according to the OFX spec.
   *
   * @return the type of account
   */
  @Element( name = "INVACCTTYPE", order = 40)
  public String getInvestmentAccountType() {
    return investmentAccountType;
  }

  /**
   * Sets the type of investment account. One of "INDIVIDUAL", "JOINT", "TRUST", or "CORPORATE".
   * This is an optional field according to the OFX spec.
   *
   * @param investmentAccountType the type of account
   */
  public void setInvestmentAccountType(String investmentAccountType) {
    this.investmentAccountType = investmentAccountType;
  }

  /**
   * Gets the type of investment account as one of the well-known types.
   *
   * @return the type of investment account or null if it's not one of the well-known types
   */
  public AccountType getInvestmentAccountTypeEnum() {
    return  AccountType.fromOfx(getInvestmentAccountType());
  }

  /**
   * Gets the description of option trading privileges. * This is an optional field according to
   * the OFX spec.
   *
   * @return the description of option trading privileges.
   */
  @Element( name = "OPTIONLEVEL", order = 50)
  public String getOptionLevel() {
    return optionLevel;
  }

  /**
   * Sets the description of option trading privileges. * This is an optional field according to
   * the OFX spec.
   *
   * @param optionLevel the description of option trading privileges.
   */
  public void setOptionLevel(String optionLevel) {
    this.optionLevel = optionLevel;
  }
}
