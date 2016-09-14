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

package net.sf.ofx4j.domain.data.investment.statements;

import net.sf.ofx4j.domain.data.common.StatementResponse;
import net.sf.ofx4j.domain.data.investment.accounts.InvestmentAccountDetails;
import net.sf.ofx4j.domain.data.investment.positions.InvestmentPositionList;
import net.sf.ofx4j.domain.data.seclist.SecurityList;
import net.sf.ofx4j.domain.data.seclist.SecurityListResponse;
import net.sf.ofx4j.domain.data.investment.transactions.InvestmentTransactionList;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * Aggregate for the investment statement download response.
 * @see "Section 13.9.2.2, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate("INVSTMTRS")
public class InvestmentStatementResponse extends StatementResponse {

  private Date dateOfStatement;
  private InvestmentAccountDetails account;
  private InvestmentTransactionList transactionList;
  private InvestmentPositionList positionList;
  private InvestmentBalance accountBalance;

  // This is not actually technically part of the INVSTMTRS, but according to Section 13.8.4,
  // OFX spec, this aggregate can appear in a statement response as part of the SECLISTMSGSRQV1
  // message set even when it wasn't requested. We include it here to make it accessible as part of
  // the AccountStatement
  private SecurityList securityList;

  /**
   * Gets the name of the response message.
   *
   * @return the name of the response message
   */
  @Override
  public String getResponseMessageName() {
    return "investment statement";
  }

  /**
   * Gets the date and time for the statement download. This is a required field according to the
   * OFX spec.
   *
   * @return the date and time for the statement download
   */
  @Element( name = "DTASOF", required = true, order = 60)
  public Date getDateOfStatement() {
    return dateOfStatement;
  }

  /**
   * Sets the date and time for the statement download. This is a required field according to the
   * OFX spec.
   *
   * @param dateOfStatement the date and time for the statement download
   */
  public void setDateOfStatement(Date dateOfStatement) {
    this.dateOfStatement = dateOfStatement;
  }

  /**
   * Gets the account for the statement. This is a required field according to the OFX spec.
   *
   * @return the account for the statement
   */
  @ChildAggregate( name ="INVACCTFROM", required = true, order = 10)
  public InvestmentAccountDetails getAccount() {
    return account;
  }

  /**
   * Sets the account for the statement. This is a required field according to the OFX spec.
   *
   * @param account the account for the statement
   */
  public void setAccount(InvestmentAccountDetails account) {
    this.account = account;
  }

  /**
   * Gets the transaction list aggregate. This is an optional field according to the OFX spec.
   *
   * @return the transaction list aggregate
   */
  @ChildAggregate ( order = 70 )
  public InvestmentTransactionList getInvestmentTransactionList() {
    return transactionList;
  }

  /**
   * Sets the transaction list aggregate. This is an optional field according to the OFX spec.
   *
   * @param transactionList the transaction list aggregate
   */
  public void setInvestmentTransactionList(InvestmentTransactionList transactionList) {
    this.transactionList = transactionList;
  }

  /**
   * Gets the position list aggreate. This is an optional field according to the OFX spec.
   *
   * @return the position list aggregate
   */
  @ChildAggregate ( order = 80 )
  public InvestmentPositionList getPositionList() {
    return positionList;
  }

  /**
   * Sets the position list aggreate. This is an optional field according to the OFX spec.
   *
   * @param positionList the position list aggregate
   */
  public void setPositionList(InvestmentPositionList positionList) {
    this.positionList = positionList;
  }

  /**
   * Gets the account balance. This is an optional field according to the OFX spec.
   *
   * @return the account balance
   */
  @ChildAggregate ( order = 90 )
  public InvestmentBalance getAccountBalance() {
    return accountBalance;
  }

  /**
   * Sets the account balance. This is an optional field according to the OFX spec.
   *
   * @param accountBalance the account balance
   */
  public void setAccountBalance(InvestmentBalance accountBalance) {
    this.accountBalance = accountBalance;
  }

  /**
   * Gets the security list aggregate.
   * <br>
   * This is not actually technically part of the investment statement responsr aggregate, but
   * according to Section 13.8.4, OFX spec, this aggregate can appear the overall response and
   * we provide it here for convenience.
   *
   * @return the security list aggregate
   */
  public SecurityList getSecurityList() {
    return securityList;
  }

  /**
   * Sets the security list aggregate.
   * <br>
   * This is not actually technically part of the investment statement responsr aggregate, but
   * according to Section 13.8.4, OFX spec, this aggregate can appear the overall response and
   * we provide it here for convenience.
   *
   * @param securityList the security list aggregate
   */
  public void setSecurityList(SecurityList securityList) {
    this.securityList = securityList;
  }
}
