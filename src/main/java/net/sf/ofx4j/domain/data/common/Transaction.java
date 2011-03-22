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
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.domain.data.banking.BankAccountDetails;
import net.sf.ofx4j.domain.data.creditcard.CreditCardAccountDetails;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ryan Heaton
 */
@Aggregate ("STMTTRN")
public class Transaction {

  private TransactionType transactionType;
  private Date datePosted;
  private Date dateInitiated;
  private Date dateAvailable;
  private BigDecimal amount;
  private String id;
  private String correctionId;
  private CorrectionAction correctionAction;
  private String tempId;
  private String checkNumber;
  private String referenceNumber;
  private String standardIndustrialCode;
  private String payeeId;
  private String name;
  private Payee payee;
  private BankAccountDetails bankAccountTo;
  private CreditCardAccountDetails creditCardAccountTo;
  private String memo;
  private Currency currency;
  private Currency originalCurrency;

  /**
   * The transaction type.
   *
   * @return The transaction type.
   */
  @Element( name = "TRNTYPE", required = true, order = 0 )
  public TransactionType getTransactionType() {
    return transactionType;
  }

  /**
   * The transaction type.
   *
   * @param transactionType The transaction type.
   */
  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  /**
   * The date the transaction was posted.
   *
   * @return The date the transaction was posted.
   */
  @Element( name = "DTPOSTED", required = true, order = 10 )
  public Date getDatePosted() {
    return datePosted;
  }

  /**
   * The date the transaction was posted.
   *
   * @param datePosted The date the transaction was posted.
   */
  public void setDatePosted(Date datePosted) {
    this.datePosted = datePosted;
  }

  /**
   * The date the transaction was initiated.
   *
   * @return The date the transaction was initiated.
   */
  @Element( name = "DTUSER", order = 20 )
  public Date getDateInitiated() {
    return dateInitiated;
  }

  /**
   * The date the transaction was initiated.
   *
   * @param dateInitiated The date the transaction was initiated.
   */
  public void setDateInitiated(Date dateInitiated) {
    this.dateInitiated = dateInitiated;
  }

  /**
   * The date the funds are available.
   *
   * @return The date the funds are available.
   */
  @Element ( name = "DTAVAIL", order = 30 )
  public Date getDateAvailable() {
    return dateAvailable;
  }

  /**
   * The date the funds are available.
   *
   * @param dateAvailable The date the funds are available.
   */
  public void setDateAvailable(Date dateAvailable) {
    this.dateAvailable = dateAvailable;
  }

  /**
   * The transaction amount.
   *
   * @return The transaction amount.
   */
  public Double getAmount() {
    return amount == null ? null : amount.doubleValue();
  }

  /**
   * The transaction amount.
   *
   * @param amount The transaction amount.
   */
  public void setAmount(Double amount) {
    this.amount = amount == null ? null : new BigDecimal(amount);
  }

  /**
   * The transaction amount.
   *
   * @return The transaction amount.
   */
  @Element ( name = "TRNAMT", required = true, order = 40 )
  public BigDecimal getBigDecimalAmount() {
    return amount;
  }

  /**
   * The transaction amount.
   *
   * @param amount The transaction amount.
   */
  public void setBigDecimalAmount(BigDecimal amount) {
    this.amount = amount;
  }

  /**
   * The transaction id (server-assigned).
   *
   * @return The transaction id (server-assigned).
   */
  @Element ( name = "FITID", required = true, order = 50 )
  public String getId() {
    return id;
  }

  /**
   * The transaction id (server-assigned).
   *
   * @param id The transaction id (server-assigned).
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * The id of the transaction that this is correcting.
   *
   * @return The id of the transaction that this is correcting.
   */
  @Element ( name = "CORRECTFITID", order = 60 )
  public String getCorrectionId() {
    return correctionId;
  }

  /**
   * The id of the transaction that this is correcting.
   *
   * @param correctionId The id of the transaction that this is correcting.
   */
  public void setCorrectionId(String correctionId) {
    this.correctionId = correctionId;
  }

  /**
   * The action to take on the {@link #getCorrectionId() corrected transaction}.
   *
   * @return The action to take on the {@link #getCorrectionId() corrected transaction}.
   */
  @Element ( name = "CORRECTACTION", order = 70 )
  public CorrectionAction getCorrectionAction() {
    return correctionAction;
  }

  /**
   * The action to take on the {@link #getCorrectionId() corrected transaction}.
   *
   * @param correctionAction The action to take on the {@link #getCorrectionId() corrected transaction}.
   */
  public void setCorrectionAction(CorrectionAction correctionAction) {
    this.correctionAction = correctionAction;
  }

  /**
   * The server-assigned temporary id for client-initiated transactions.
   *
   * @return The server-assigned temporary id for client-initiated transactions.
   */
  @Element ( name = "SRVRTID", order = 80 )
  public String getTempId() {
    return tempId;
  }

  /**
   * The server-assigned temporary id for client-initiated transactions.
   *
   * @param tempId The server-assigned temporary id for client-initiated transactions.
   */
  public void setTempId(String tempId) {
    this.tempId = tempId;
  }

  /**
   * The check number.
   *
   * @return The check number.
   */
  @Element ( name = "CHECKNUM", order = 90 )
  public String getCheckNumber() {
    return checkNumber;
  }

  /**
   * The check number.
   *
   * @param checkNumber The check number.
   */
  public void setCheckNumber(String checkNumber) {
    this.checkNumber = checkNumber;
  }

  /**
   * The reference number.
   *
   * @return The reference number.
   */
  @Element ( name = "REFNUM", order = 100 )
  public String getReferenceNumber() {
    return referenceNumber;
  }

  /**
   * The reference number.
   *
   * @param referenceNumber The reference number.
   */
  public void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  /**
   * The standard industrial code.
   *
   * @return The standard industrial code.
   */
  @Element ( name = "SIC", order = 110)
  public String getStandardIndustrialCode() {
    return standardIndustrialCode;
  }

  /**
   * The standard industrial code.
   *
   * @param standardIndustrialCode The standard industrial code.
   */
  public void setStandardIndustrialCode(String standardIndustrialCode) {
    this.standardIndustrialCode = standardIndustrialCode;
  }

  /**
   * The payee id.
   *
   * @return The payee id.
   */
  @Element ( name = "PAYEEID", order = 120 )
  public String getPayeeId() {
    return payeeId;
  }

  /**
   * The payee id.
   *
   * @param payeeId The payee id.
   */
  public void setPayeeId(String payeeId) {
    this.payeeId = payeeId;
  }

  /**
   * The name (description) or the transaction.
   *
   * @return The name (description) or the transaction.
   */
  @Element ( name = "NAME", order = 130)
  public String getName() {
    return name;
  }

  /**
   * The name (description) or the transaction.
   *
   * @param name The name (description) or the transaction.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * The payee.
   *
   * @return The payee.
   */
  @ChildAggregate ( order = 140 )
  public Payee getPayee() {
    return payee;
  }

  /**
   * The payee.
   *
   * @param payee The payee.
   */
  public void setPayee(Payee payee) {
    this.payee = payee;
  }

  /**
   * The bank account the transfer was to.
   *
   * @return The bank account the transfer was to.
   */
  @ChildAggregate( name = "BANKACCTTO", order = 150 )
  public BankAccountDetails getBankAccountTo() {
    return bankAccountTo;
  }

  /**
   * The bank account the transfer was to.
   *
   * @param bankAccountTo The bank account the transfer was to.
   */
  public void setBankAccountTo(BankAccountDetails bankAccountTo) {
    this.bankAccountTo = bankAccountTo;
  }

  /**
   * The credit-card account the transfer was to.
   *
   * @return The credit-card account the transfer was to.
   */
  @ChildAggregate ( name = "CCACCTTO", order = 160 )
  public CreditCardAccountDetails getCreditCardAccountTo() {
    return creditCardAccountTo;
  }

  /**
   * The credit-card account the transfer was to.
   *
   * @param creditCardAccountTo The credit-card account the transfer was to.
   */
  public void setCreditCardAccountTo(CreditCardAccountDetails creditCardAccountTo) {
    this.creditCardAccountTo = creditCardAccountTo;
  }

  /**
   * Notes.
   *
   * @return Notes.
   */
  @Element ( name = "MEMO", order = 170)
  public String getMemo() {
    return memo;
  }

  /**
   * Notes.
   *
   * @param memo Notes.
   */
  public void setMemo(String memo) {
    this.memo = memo;
  }

  /**
   * The currency.
   *
   * @return The currency.
   */
  @ChildAggregate ( order = 180 )
  public Currency getCurrency() {
    return currency;
  }

  /**
   * The currency.
   *
   * @param currency The currency.
   */
  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  /**
   * The original currency.
   *
   * @return The original currency.
   */
  @ChildAggregate ( name = "ORIGCURRENCY", order = 190 )
  public Currency getOriginalCurrency() {
    return originalCurrency;
  }

  /**
   * The original currency.
   *
   * @param originalCurrency The original currency.
   */
  public void setOriginalCurrency(Currency originalCurrency) {
    this.originalCurrency = originalCurrency;
  }
}
