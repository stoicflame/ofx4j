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

package net.sf.ofx4j.client.impl;

import net.sf.ofx4j.OFXException;
import net.sf.ofx4j.client.AccountStatement;
import net.sf.ofx4j.client.FinancialInstitutionAccount;
import net.sf.ofx4j.domain.data.*;
import net.sf.ofx4j.domain.data.banking.BankAccountDetails;
import net.sf.ofx4j.domain.data.common.StatementRange;
import net.sf.ofx4j.domain.data.common.StatementRequest;
import net.sf.ofx4j.domain.data.common.StatementResponse;
import net.sf.ofx4j.domain.data.creditcard.CreditCardAccountDetails;
import net.sf.ofx4j.domain.data.investment.accounts.InvestmentAccountDetails;

import java.util.Date;

/**
 * Base account implementation. Supports banking and credit card accounts.
 *
 * @author Ryan Heaton
 */
public abstract class BaseAccountImpl<D> implements FinancialInstitutionAccount {

  private final D details;
  private final MessageSetType messageType;
  private final String username;
  private final String password;
  private final FinancialInstitutionImpl institution;

  protected BaseAccountImpl(D details, String username, String password, FinancialInstitutionImpl institution) {
    this.details = details;
    this.username = username;
    this.password = password;
    this.institution = institution;
    this.messageType = getMessageSetType(details);
  }

  /**
   * Get the message set type of the specified details.
   *
   * @param details The details.
   * @return The message set type.
   */
  protected MessageSetType getMessageSetType(D details) {
    MessageSetType messageType;
    if (details instanceof BankAccountDetails) {
      messageType = MessageSetType.banking;
    }
    else if (getDetails() instanceof CreditCardAccountDetails) {
      messageType = MessageSetType.creditcard;
    }
    else if (getDetails() instanceof InvestmentAccountDetails) {
      messageType = MessageSetType.investment;
    }
    else {
      throw new IllegalStateException("Illegal details: " + this.details.getClass().getName());
    }
    return messageType;
  }

  public AccountStatement readStatement(Date start, Date end) throws OFXException {
    StatementRange range = new StatementRange();
    range.setIncludeTransactions(true);
    range.setStart(start);
    range.setEnd(end);

    RequestEnvelope request = institution.createAuthenticatedRequest(username, password);
    TransactionWrappedRequestMessage requestTransaction = createTransaction();
    requestTransaction.setWrappedMessage(createStatementRequest(getDetails(), range));
    request.getMessageSets().add(createRequestMessageSet(requestTransaction));

    ResponseEnvelope response = institution.sendRequest(request);
    institution.doGeneralValidationChecks(request, response);

    return unwrapStatementResponse(response);
  }

  /**
   * Unwrap the statement response from the specified response envelope.
   *
   * @param response The response envelope to unwrap.
   * @return The response.
   */
  protected abstract StatementResponse unwrapStatementResponse(ResponseEnvelope response) throws OFXException;

  /**
   * Create a request message set from the specified transaction.
   *
   * @param transaction The transaction.
   * @return The request message set.
   */
  protected abstract RequestMessageSet createRequestMessageSet(TransactionWrappedRequestMessage transaction);

  /**
   * Create a transaction.
   *
   * @return The transaction.
   */
  protected abstract TransactionWrappedRequestMessage createTransaction();

  /**
   * Create a statement request.
   *
   * @param details The details.
   * @param range the range.
   * @return The statement request.
   */
  protected abstract StatementRequest createStatementRequest(D details, StatementRange range);

  /**
   * The details of this account.
   *
   * @return The details of this account.
   */
  public D getDetails() {
    return details;
  }

  /**
   * The message set type.
   *
   * @return The message set type.
   */
  protected MessageSetType getMessageType() {
    return messageType;
  }
}
