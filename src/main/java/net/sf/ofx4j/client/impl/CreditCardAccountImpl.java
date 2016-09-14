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

import net.sf.ofx4j.domain.data.common.StatementResponse;
import net.sf.ofx4j.domain.data.common.StatementRequest;
import net.sf.ofx4j.domain.data.common.StatementRange;
import net.sf.ofx4j.domain.data.*;
import net.sf.ofx4j.domain.data.creditcard.*;
import net.sf.ofx4j.OFXException;
import net.sf.ofx4j.client.CreditCardAccount;

/**
 * @author Ryan Heaton
 */
public class CreditCardAccountImpl extends BaseAccountImpl<CreditCardAccountDetails> implements CreditCardAccount {

  public CreditCardAccountImpl(CreditCardAccountDetails details, String username, String password, FinancialInstitutionImpl institution) {
    super(details, username, password, institution);
  }

  protected StatementResponse unwrapStatementResponse(ResponseEnvelope response) throws OFXException {
    CreditCardResponseMessageSet creditCardSet = (CreditCardResponseMessageSet) response.getMessageSet(MessageSetType.creditcard);
    if (creditCardSet == null) {
      throw new OFXException("No credit card response message set.");
    }

    CreditCardStatementResponseTransaction statementTransactionResponse = creditCardSet.getStatementResponse();
    if (statementTransactionResponse == null) {
      throw new OFXException("No credit card statement response transaction.");
    }

    CreditCardStatementResponse statement = statementTransactionResponse.getMessage();
    if (statement == null) {
      throw new OFXException("No credit card statement in the transaction.");
    }

    return statement;
  }

  protected RequestMessageSet createRequestMessageSet(TransactionWrappedRequestMessage transaction) {
    CreditCardRequestMessageSet creditCardRequest = new CreditCardRequestMessageSet();
    creditCardRequest.setStatementRequest((CreditCardStatementRequestTransaction) transaction);
    return creditCardRequest;
  }

  protected TransactionWrappedRequestMessage createTransaction() {
    return new CreditCardStatementRequestTransaction();
  }

  protected StatementRequest createStatementRequest(CreditCardAccountDetails details, StatementRange range) {
    CreditCardStatementRequest bankRequest = new CreditCardStatementRequest();
    bankRequest.setAccount(details);
    bankRequest.setStatementRange(range);
    return bankRequest;
  }

}