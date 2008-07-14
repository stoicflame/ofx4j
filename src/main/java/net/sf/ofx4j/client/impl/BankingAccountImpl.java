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
