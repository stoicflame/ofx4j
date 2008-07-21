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
import net.sf.ofx4j.domain.data.banking.*;
import net.sf.ofx4j.OFXException;
import net.sf.ofx4j.client.BankAccount;

/**
 * @author Ryan Heaton
 */
public class BankingAccountImpl extends BaseAccountImpl<BankAccountDetails> implements BankAccount {

  public BankingAccountImpl(BankAccountDetails details, String username, String password, FinancialInstitutionImpl institution) {
    super(details, username, password, institution);
  }

  protected StatementResponse unwrapStatementResponse(ResponseEnvelope response) throws OFXException {
    BankingResponseMessageSet bankingSet = (BankingResponseMessageSet) response.getMessageSet(MessageSetType.banking);
    if (bankingSet == null) {
      throw new OFXException("No banking response message set.");
    }

    BankStatementResponseTransaction statementTransactionResponse = bankingSet.getStatementResponse();
    if (statementTransactionResponse == null) {
      throw new OFXException("No banking statement response transaction.");
    }

    BankStatementResponse statement = statementTransactionResponse.getMessage();
    if (statement == null) {
      throw new OFXException("No banking statement in the transaction.");
    }
    
    return statement;
  }

  protected RequestMessageSet createRequestMessageSet(TransactionWrappedRequestMessage transaction) {
    BankingRequestMessageSet bankingRequest = new BankingRequestMessageSet();
    bankingRequest.setStatementRequest((BankStatementRequestTransaction) transaction);
    return bankingRequest;
  }

  protected TransactionWrappedRequestMessage createTransaction() {
    return new BankStatementRequestTransaction();
  }

  protected StatementRequest createStatementRequest(BankAccountDetails details, StatementRange range) {
    BankStatementRequest bankRequest = new BankStatementRequest();
    bankRequest.setAccount(details);
    bankRequest.setStatementRange(range);
    return bankRequest;
  }

}
