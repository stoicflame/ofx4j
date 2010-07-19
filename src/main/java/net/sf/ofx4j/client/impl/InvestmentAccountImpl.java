package net.sf.ofx4j.client.impl;

import net.sf.ofx4j.OFXException;
import net.sf.ofx4j.domain.data.*;
import net.sf.ofx4j.client.InvestmentAccount;
import net.sf.ofx4j.domain.data.common.StatementRange;
import net.sf.ofx4j.domain.data.common.StatementRequest;
import net.sf.ofx4j.domain.data.investment.accounts.InvestmentAccountDetails;
import net.sf.ofx4j.domain.data.seclist.*;
import net.sf.ofx4j.domain.data.investment.statements.*;
import net.sf.ofx4j.domain.data.investment.statements.IncludePosition;

import java.util.Date;
import java.util.List;

/**
 * @author Jon Perlow
 */
public class InvestmentAccountImpl implements InvestmentAccount {
  private final InvestmentAccountDetails details;
  private final String username;
  private final String password;
  private final FinancialInstitutionImpl institution;

  public InvestmentAccountImpl(InvestmentAccountDetails details, String username, String password,
                               FinancialInstitutionImpl institution) {
    this.details = details;
    this.username = username;
    this.password = password;
    this.institution = institution;
  }

  public InvestmentStatementResponse readStatement(Date start, Date end) throws OFXException {
    StatementRange range = new StatementRange();
    range.setIncludeTransactions(true);
    range.setStart(start);
    range.setEnd(end);

    RequestEnvelope request = institution.createAuthenticatedRequest(username, password);
    InvestmentStatementRequestTransaction requestTransaction =
        new InvestmentStatementRequestTransaction();
    requestTransaction.setWrappedMessage(createStatementRequest(getDetails(), range));
    request.getMessageSets().add(createStatementRequestMessageSet(requestTransaction));

    ResponseEnvelope response = institution.sendRequest(request);
    institution.doGeneralValidationChecks(request, response);

    return unwrapStatementResponse(response);
  }

  public SecurityList readSecurityList(List<SecurityRequest> securities)
      throws OFXException {
    RequestEnvelope request = institution.createAuthenticatedRequest(username, password);
    SecurityListRequestTransaction requestTransaction = new SecurityListRequestTransaction();
    requestTransaction.setWrappedMessage(createSecurityListRequest(securities));
    request.getMessageSets().add(createSecurityListRequestMessageSet(requestTransaction));

    ResponseEnvelope response = institution.sendRequest(request);
    institution.doGeneralValidationChecks(request, response);

    return unwrapSecurityList(response);
  }

  /**
   * The details of this account.
   *
   * @return The details of this account.
   */
  public InvestmentAccountDetails getDetails() {
    return details;
  }

  private InvestmentStatementResponse unwrapStatementResponse(ResponseEnvelope response)
      throws OFXException {
    InvestmentStatementResponseMessageSet investmentStatementSet =
        (InvestmentStatementResponseMessageSet) response.getMessageSet(MessageSetType.investment);
    if (investmentStatementSet == null) {
      throw new OFXException("No investment response message set.");
    }

    InvestmentStatementResponseTransaction statementTransactionResponse =
        investmentStatementSet.getStatementResponse();
    if (statementTransactionResponse == null) {
      throw new OFXException("No investment statement response transaction.");
    }

    InvestmentStatementResponse statement = statementTransactionResponse.getMessage();
    if (statement == null) {
      throw new OFXException("No investment statement in the transaction.");
    }

    // See if there's a security list -- often sent back with an account statement by servers.
    SecurityListResponseMessageSet securityListMessageSet =
        (SecurityListResponseMessageSet) response.getMessageSet(
            MessageSetType.investment_security);
    if (securityListMessageSet != null) {
      statement.setSecurityList(securityListMessageSet.getSecurityList());
    }

    return statement;
  }

  private RequestMessageSet createStatementRequestMessageSet(
      InvestmentStatementRequestTransaction transaction) {
    InvestmentStatementRequestMessageSet investmentStatementRequest =
        new InvestmentStatementRequestMessageSet();
    investmentStatementRequest.setStatementRequest(transaction);
    return investmentStatementRequest;
  }

  private InvestmentStatementRequest createStatementRequest(
      InvestmentAccountDetails details, StatementRange range) {
    InvestmentStatementRequest investRequest = new InvestmentStatementRequest();
    investRequest.setAccount(details);
    investRequest.setStatementRange(range);
    investRequest.setIncludePosition(new IncludePosition());
    return investRequest;
  }

  private RequestMessageSet createSecurityListRequestMessageSet(
      SecurityListRequestTransaction transaction) {
    SecurityListRequestMessageSet securityListRequest =
        new SecurityListRequestMessageSet();
    securityListRequest.setSecurityListRequest(transaction);
    return securityListRequest;
  }

  private SecurityListRequest createSecurityListRequest(List<SecurityRequest> securities) {
    SecurityListRequest securityListRequest = new SecurityListRequest();
    securityListRequest.setSecurityRequests(securities);
    return securityListRequest;
  }

  private SecurityList unwrapSecurityList(ResponseEnvelope response)
      throws OFXException {
    SecurityListResponseMessageSet securityListSet =
        (SecurityListResponseMessageSet) response.getMessageSet(MessageSetType.investment_security);
    if (securityListSet == null) {
      throw new OFXException("No security list response message set.");
    }

    SecurityList securityList = securityListSet.getSecurityList();
    if (securityList == null) {
      throw new OFXException("No security list response transaction.");
    }

    return securityList;
  }
}
