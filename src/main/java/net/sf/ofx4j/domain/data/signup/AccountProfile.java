/*
 * Copyright 2008 Web Cohesion
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

package net.sf.ofx4j.domain.data.signup;

import net.sf.ofx4j.domain.data.investment.accounts.InvestmentAccountInfo;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.domain.data.banking.BankAccountInfo;
import net.sf.ofx4j.domain.data.creditcard.CreditCardAccountInfo;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "ACCTINFO" )
public class AccountProfile {

  private String description;
  private String phone;
  private BankAccountInfo bankSpecifics;
  private CreditCardAccountInfo creditCardSpecifics;
  private InvestmentAccountInfo investSpecifics;

  /**
   * Description of the account.
   *
   * @return The description of the account.
   */
  @Element ( name = "DESC", order = 0 )
  public String getDescription() {
    return description;
  }

  /**
   * The description of the account.
   *
   * @param description The description of the account.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Phone number for the account.
   *
   * @return Phone number for the account.
   */
  @Element ( name = "PHONE", order = 10 )
  public String getPhone() {
    return phone;
  }

  /**
   * Phone number for the account.
   *
   * @param phone Phone number for the account.
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Account specifics.
   *
   * @return Account specifics.
   */
  public net.sf.ofx4j.domain.data.common.AccountInfo getSpecifics() {
    if (getBankSpecifics() != null && getCreditCardSpecifics() != null) {
      throw new IllegalStateException("Only one account specifics aggregate can be set at a time.");
    }
    else if (getBankSpecifics() != null) {
      return getBankSpecifics();
    } else if (getInvestmentSpecifics() != null) {
      return getInvestmentSpecifics();
    }
    else {
      return getCreditCardSpecifics();
    }
  }

  /**
   * Account specifics.
   *
   * @param specifics Account specifics.
   */
  public void setSpecifics(net.sf.ofx4j.domain.data.common.AccountInfo specifics) {
    if (specifics instanceof BankAccountInfo) {
      setBankSpecifics((BankAccountInfo) specifics);
    }
    else if (specifics instanceof CreditCardAccountInfo) {
      setCreditCardSpecifics((CreditCardAccountInfo) specifics);
    } else if (specifics instanceof InvestmentAccountInfo) {
      setInvestmentSpecifics((InvestmentAccountInfo) specifics);
    }
    else {
      throw new IllegalArgumentException("Unknown specifics type: " + specifics);
    }
  }

  /**
   * Bank-specific info.
   *
   * @return Bank-specific info.
   */
  @ChildAggregate ( order = 20 )
  public BankAccountInfo getBankSpecifics() {
    return bankSpecifics;
  }

  /**
   * Bank-specific info.
   *
   * @param bankSpecifics Bank-specific info.
   */
  public void setBankSpecifics(BankAccountInfo bankSpecifics) {
    this.creditCardSpecifics = null;
    this.investSpecifics = null;
    this.bankSpecifics = bankSpecifics;
  }

  /**
   * Credit-card account info.
   *
   * @return Credit-card account info.
   */
  @ChildAggregate ( order = 30 )
  public CreditCardAccountInfo getCreditCardSpecifics() {
    return creditCardSpecifics;
  }

  /**
   * Credit-card account info.
   *
   * @param creditCardSpecifics Credit-card account info.
   */
  public void setCreditCardSpecifics(CreditCardAccountInfo creditCardSpecifics) {
    this.bankSpecifics = null;
    this.investSpecifics = null;
    this.creditCardSpecifics = creditCardSpecifics;
  }

  /**
   * Investment account info.
   *
   * @return Investment account info.
   */
  @ChildAggregate ( order = 40 )
  public InvestmentAccountInfo getInvestmentSpecifics() {
    return investSpecifics;
  }

  /**
   * Investment account info.
   *
   * @param investSpecifics Investment account info.
   */
  public void setInvestmentSpecifics(InvestmentAccountInfo investSpecifics) {
    this.bankSpecifics = null;
    this.creditCardSpecifics = null;
    this.investSpecifics = investSpecifics;
  }
}
