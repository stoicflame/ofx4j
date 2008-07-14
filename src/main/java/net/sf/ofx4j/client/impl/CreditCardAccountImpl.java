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