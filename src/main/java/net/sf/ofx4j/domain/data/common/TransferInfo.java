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

package net.sf.ofx4j.domain.data.common;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.domain.data.banking.BankAccountDetails;
import net.sf.ofx4j.domain.data.creditcard.CreditCardAccountDetails;

import java.util.Date;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "XFERINFO" )
public class TransferInfo {

  private BankAccountDetails bankAccountFrom;
  private CreditCardAccountDetails creditCardAccountFrom;
  private BankAccountDetails bankAccountTo;
  private CreditCardAccountDetails creditCardAccountTo;

  private Double amount;
  private Date due;

  /**
   * The bank account to transfer from.
   *
   * @return The bank account to transfer from.
   */
  @ChildAggregate ( name = "BANKACCTFROM", order = 0)
  public BankAccountDetails getBankAccountFrom() {
    return bankAccountFrom;
  }

  /**
   * The bank account to transfer from.
   *
   * @param bankAccountFrom The bank account to transfer from.
   */
  public void setBankAccountFrom(BankAccountDetails bankAccountFrom) {
    this.creditCardAccountFrom = null;
    this.bankAccountFrom = bankAccountFrom;
  }

  /**
   * The account to transfer from.
   *
   * @param bankAccountFrom The account to transfer from.
   */
  public void setAccountFrom(BankAccountDetails bankAccountFrom) {
    setBankAccountFrom(bankAccountFrom);
  }

  /**
   * The credit card to transfer from.
   *
   * @return The credit card to transfer from.
   */
  @ChildAggregate ( name = "CCACCTFROM", order = 10 )
  public CreditCardAccountDetails getCreditCardAccountFrom() {
    return creditCardAccountFrom;
  }

  /**
   * The credit card to transfer from.
   *
   * @param creditCardAccountFrom The credit card to transfer from.
   */
  public void setCreditCardAccountFrom(CreditCardAccountDetails creditCardAccountFrom) {
    this.bankAccountFrom = null;
    this.creditCardAccountFrom = creditCardAccountFrom;
  }

  /**
   * The credit card to transfer from.
   *
   * @param creditCardAccountFrom The credit card to transfer from.
   */
  public void setAccountFrom(CreditCardAccountDetails creditCardAccountFrom) {
    setCreditCardAccountFrom(creditCardAccountFrom);
  }

  /**
   * The bank account to transfer to.
   *
   * @return The bank account to transfer to.
   */
  @ChildAggregate ( name = "BANKACCTTO", order = 20 )
  public BankAccountDetails getBankAccountTo() {
    return bankAccountTo;
  }

  /**
   * The bank account to transfer to.
   *
   * @param bankAccountTo The bank account to transfer to.
   */
  public void setBankAccountTo(BankAccountDetails bankAccountTo) {
    this.creditCardAccountTo = null;
    this.bankAccountTo = bankAccountTo;
  }

  /**
   * The bank account to transfer to.
   *
   * @param bankAccountTo The bank account to transfer to.
   */
  public void setAccountTo(BankAccountDetails bankAccountTo) {
    setBankAccountTo(bankAccountTo);
  }

  /**
   * The credit card account to transfer to.
   *
   * @return The credit card account to transfer to.
   */
  @ChildAggregate ( name = "CCACCTTO", order = 30 )
  public CreditCardAccountDetails getCreditCardAccountTo() {
    return creditCardAccountTo;
  }

  /**
   * The credit card account to transfer to.
   *
   * @param creditCardAccountTo The credit card account to transfer to.
   */
  public void setCreditCardAccountTo(CreditCardAccountDetails creditCardAccountTo) {
    this.bankAccountTo = null;
    this.creditCardAccountTo = creditCardAccountTo;
  }

  /**
   * The credit card account to transfer to.
   *
   * @param creditCardAccountTo The credit card account to transfer to.
   */
  public void setAccountTo(CreditCardAccountDetails creditCardAccountTo) {
    setCreditCardAccountTo(creditCardAccountTo);
  }

  /**
   * The amount.
   *
   * @return The amount.
   */
  @Element ( name = "TRNAMT", required = true, order = 40 )
  public Double getAmount() {
    return amount;
  }

  /**
   * The amount.
   *
   * @param amount The amount.
   */
  public void setAmount(Double amount) {
    this.amount = amount;
  }

  /**
   * The due date.
   *
   * @return The due date.
   */
  @Element ( name = "DTDUE", order = 50 )
  public Date getDue() {
    return due;
  }

  /**
   * The due date.
   *
   * @param due The due date.
   */
  public void setDue(Date due) {
    this.due = due;
  }
}
