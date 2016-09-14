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
import net.sf.ofx4j.OFXStatusException;
import net.sf.ofx4j.UnsupportedOFXSecurityTypeException;
import net.sf.ofx4j.OFXTransactionException;
import net.sf.ofx4j.client.*;
import net.sf.ofx4j.client.context.OFXApplicationContextHolder;
import net.sf.ofx4j.domain.data.*;
import net.sf.ofx4j.client.InvestmentAccount;
import net.sf.ofx4j.domain.data.investment.accounts.InvestmentAccountDetails;
import net.sf.ofx4j.domain.data.signup.*;
import net.sf.ofx4j.domain.data.creditcard.CreditCardAccountDetails;
import net.sf.ofx4j.domain.data.banking.BankAccountDetails;
import net.sf.ofx4j.domain.data.common.Status;
import net.sf.ofx4j.domain.data.common.StatusHolder;
import net.sf.ofx4j.domain.data.profile.*;
import net.sf.ofx4j.domain.data.signon.SignonRequest;
import net.sf.ofx4j.domain.data.signon.SignonRequestMessageSet;
import net.sf.ofx4j.domain.data.signon.SignonResponse;
import net.sf.ofx4j.domain.data.signon.SignonResponseMessageSet;
import net.sf.ofx4j.client.net.OFXConnection;
import net.sf.ofx4j.client.net.OFXConnectionException;

import java.net.URL;
import java.util.Date;
import java.util.TreeSet;
import java.util.Set;
import java.util.Collection;

/**
 * Base implementation for the financial institution.
 *
 * @author Ryan Heaton
 */
public class FinancialInstitutionImpl implements FinancialInstitution {

  private final OFXConnection connection;
  private final FinancialInstitutionData data;

  public FinancialInstitutionImpl(FinancialInstitutionData data, OFXConnection connection) {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null");
    }
    if (connection == null) {
      throw new IllegalArgumentException("An OFX connection must be supplied");
    }

    this.data = data;
    this.connection = connection;
  }

  // Inherited.
  public FinancialInstitutionProfile readProfile() throws OFXException {
    RequestEnvelope request = createAuthenticatedRequest(SignonRequest.ANONYMOUS_USER, SignonRequest.ANONYMOUS_USER);
    ProfileRequestMessageSet profileRequest = new ProfileRequestMessageSet();
    profileRequest.setProfileRequest(createProfileTransaction());
    request.getMessageSets().add(profileRequest);
    ResponseEnvelope response = sendRequest(request, getData().getOFXURL());
    doGeneralValidationChecks(request, response);
    return getProfile(response);
  }

  // Inherited.
  public Collection<AccountProfile> readAccountProfiles(String username, String password) throws OFXException {
    RequestEnvelope request = createAuthenticatedRequest(username, password);
    SignupRequestMessageSet signupRequest = new SignupRequestMessageSet();
    signupRequest.setAccountInfoRequest(createAccountInfoTransaction());
    request.getMessageSets().add(signupRequest);
    ResponseEnvelope response = sendRequest(request, getData().getOFXURL());
    doGeneralValidationChecks(request, response);
    return getAccountProfiles(response);
  }

  // Inherited.
  public BankAccount loadBankAccount(BankAccountDetails details, String username, String password) {
    return new BankingAccountImpl(details, username, password, this);
  }

  // Inherited.
  public CreditCardAccount loadCreditCardAccount(CreditCardAccountDetails details, String username, String password) {
    return new CreditCardAccountImpl(details, username, password, this);
  }

  // Inherited
  public InvestmentAccount loadInvestmentAccount(InvestmentAccountDetails details, String username, String password) {
    return new InvestmentAccountImpl(details, username, password, this);
  }

  /**
   * Create an authenticated request envelope.
   *
   * @param username The username.
   * @param password The password.
   * @return The request envelope.
   */
  protected RequestEnvelope createAuthenticatedRequest(String username, String password) {
    RequestEnvelope request = new RequestEnvelope();
    TreeSet<RequestMessageSet> messageSets = new TreeSet<RequestMessageSet>();
    SignonRequestMessageSet signonRequest = new SignonRequestMessageSet();
    signonRequest.setSignonRequest(createSignonRequest(username, password));
    messageSets.add(signonRequest);
    request.setMessageSets(messageSets);
    return request;
  }

  /**
   * Send a request.
   *
   * @param request The request.
   * @return The request.
   */
  protected ResponseEnvelope sendRequest(RequestEnvelope request) throws OFXConnectionException {
    return getConnection().sendRequest(request, getData().getOFXURL());
  }

  /**
   * Send a request to a specific URL.
   *
   * @param request The request.
   * @param url The url.
   * @return The request.
   */
  protected ResponseEnvelope sendRequest(RequestEnvelope request, URL url) throws OFXConnectionException {
    return getConnection().sendRequest(request, url);
  }

  /**
   * Open the specified response envelope and look for the profile.
   *
   * @param response The response envelope.
   * @return The profile.
   */
  protected FinancialInstitutionProfile getProfile(ResponseEnvelope response) throws OFXException {

    ProfileResponseMessageSet profileSet = (ProfileResponseMessageSet) response.getMessageSet(MessageSetType.profile);
    if (profileSet == null) {
      throw new OFXException("No profile response set.");
    }

    ProfileResponseTransaction transactionResponse = profileSet.getProfileResponse();
    if (transactionResponse == null) {
      throw new OFXException("No profile transaction wrapper.");
    }

    ProfileResponse message = transactionResponse.getMessage();
    if (message == null) {
      throw new OFXException("No profile message.");
    }
    return message;
  }

  /**
   * General validation checks on the specified response.
   *
   * @param request The request.
   * @param response Their response.
   * @throws OFXException Upon invalid response.
   */
  protected void doGeneralValidationChecks(RequestEnvelope request, ResponseEnvelope response) throws OFXException {
    if (response.getSecurity() != ApplicationSecurity.NONE) {
      throw new UnsupportedOFXSecurityTypeException(String.format("Unable to participate in %s security.", response.getSecurity()));
    }

    if (!request.getUID().equals(response.getUID())) {
      throw new OFXException(String.format("Invalid transaction ID '%s' in response.  Expected: %s", response.getUID(), request));
    }

    for (RequestMessageSet requestSet : request.getMessageSets()) {
      ResponseMessageSet responseSet = response.getMessageSet(requestSet.getType());
      if (responseSet == null) {
        throw new NoOFXResponseException("No response for the " + requestSet.getType() + " request.");
      }

      if (responseSet.getType() == MessageSetType.signon) {
        SignonResponse signonResponse = ((SignonResponseMessageSet) responseSet).getSignonResponse();

        if (signonResponse == null) {
          throw new NoOFXResponseException("No signon response.");
        }
      }

      Set<String> transactionIds = new TreeSet<String>();
      for (RequestMessage requestMessage : requestSet.getRequestMessages()) {
        if (requestMessage instanceof TransactionWrappedRequestMessage) {
          transactionIds.add(((TransactionWrappedRequestMessage) requestMessage).getUID());
        }
      }

      for (ResponseMessage responseMessage : responseSet.getResponseMessages()) {
        if (responseMessage instanceof StatusHolder) {
          validateStatus((StatusHolder) responseMessage);
        }

        if (responseMessage instanceof TransactionWrappedResponseMessage) {
          String uid = ((TransactionWrappedResponseMessage) responseMessage).getUID();
          if (uid == null) {
            throw new OFXTransactionException("Invalid response transaction: no UID.");
          }
          else if (!transactionIds.remove(uid)) {
            throw new OFXTransactionException("Response to an unknown transaction: " + uid + ".");
          }
        }
      }

      if (!transactionIds.isEmpty()) {
        throw new OFXTransactionException("No response to the following transactions: " + transactionIds);
      }
    }
  }

  /**
   * Validate the status of the given status holder.
   *
   * @param statusHolder The status holder.
   */
  protected void validateStatus(StatusHolder statusHolder) throws OFXException {
    Status status = statusHolder.getStatus();
    if (status == null) {
      throw new OFXException("Invalid OFX response: no status returned in the " + statusHolder.getStatusHolderName() + " response.");
    }

    if (!Status.KnownCode.SUCCESS.equals(status.getCode())) {
      String message = status.getMessage();
      if (message == null) {
        message = "No response status code.";

        if (status.getCode() != null) {
          message = status.getCode().getMessage();
        }
      }

      throw new OFXStatusException(status, "Invalid " + statusHolder.getStatusHolderName() + ": " + message);
    }
  }

  /**
   * Create a transaction message for a profile request.
   *
   * @return The transaction message.
   */
  protected ProfileRequestTransaction createProfileTransaction() {
    ProfileRequestTransaction profileTx = new ProfileRequestTransaction();
    profileTx.setMessage(createProfileRequest());
    return profileTx;
  }

  /**
   * Create a profile request.
   *
   * @return The profile request.
   */
  protected ProfileRequest createProfileRequest() {
    ProfileRequest profileRequest = new ProfileRequest();
    profileRequest.setProfileLastUpdated(new Date(0));
    return profileRequest;
  }

  /**
   * Create a sign-on request for the specified user.
   *
   * @param username The username.
   * @param password The password.
   * @return The signon request.
   */
  protected SignonRequest createSignonRequest(String username, String password) {
    SignonRequest signonRequest = new SignonRequest();
    signonRequest.setTimestamp(new Date());
    net.sf.ofx4j.domain.data.signon.FinancialInstitution fi = new net.sf.ofx4j.domain.data.signon.FinancialInstitution();
    fi.setId(getData().getFinancialInstitutionId());
    fi.setOrganization(getData().getOrganization());
    signonRequest.setFinancialInstitution(fi);
    signonRequest.setUserId(username);
    signonRequest.setPassword(password);
    signonRequest.setApplicationId(OFXApplicationContextHolder.getCurrentContext().getAppId());
    signonRequest.setApplicationVersion(OFXApplicationContextHolder.getCurrentContext().getAppVersion());
    return signonRequest;
  }

  /**
   * Create a transaction for an account info request.
   *
   * @return The transaction.
   */
  protected AccountInfoRequestTransaction createAccountInfoTransaction() {
    AccountInfoRequestTransaction transaction = new AccountInfoRequestTransaction();
    transaction.setMessage(createAccountInfoRequest());
    return transaction;
  }

  /**
   * Create an account info request.
   *
   * @return The account info request.
   */
  protected AccountInfoRequest createAccountInfoRequest() {
    return new AccountInfoRequest();
  }

  /**
   * Get the account profiles for the specified response envelope.
   *
   * @param response The response envelope.
   * @return The account profiles.
   */
  protected Collection<AccountProfile> getAccountProfiles(ResponseEnvelope response) throws OFXException {
    SignupResponseMessageSet messageSet = (SignupResponseMessageSet) response.getMessageSet(MessageSetType.signup);
    if (messageSet == null) {
      throw new OFXException("No signup response message set.");
    }

    AccountInfoResponseTransaction transaction = messageSet.getAccountInfoResponse();
    if (transaction == null) {
      throw new OFXException("No account info transaction in the signup response.");
    }

    AccountInfoResponse infoResponse = transaction.getMessage();
    if (infoResponse == null) {
      throw new OFXException("No account info response in the transaction.");
    }

    return infoResponse.getAccounts();
  }

  /**
   * The connection used by this implementation.
   *
   * @return The connection used by this implementation.
   */
  public OFXConnection getConnection() {
    return connection;
  }

  /**
   * The financial institution data.
   *
   * @return The financial institution data.
   */
  public FinancialInstitutionData getData() {
    return data;
  }
}
