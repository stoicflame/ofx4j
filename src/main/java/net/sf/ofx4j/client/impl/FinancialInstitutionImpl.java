package net.sf.ofx4j.client.impl;

import net.sf.ofx4j.OFXException;
import net.sf.ofx4j.client.FinancialInstitution;
import net.sf.ofx4j.client.FinancialInstitutionData;
import net.sf.ofx4j.client.FinancialInstitutionProfile;
import net.sf.ofx4j.client.context.OFXApplicationContextHolder;
import net.sf.ofx4j.domain.data.*;
import net.sf.ofx4j.domain.data.common.Status;
import net.sf.ofx4j.domain.data.common.StatusHolder;
import net.sf.ofx4j.domain.data.profile.*;
import net.sf.ofx4j.domain.data.signon.SignonRequest;
import net.sf.ofx4j.domain.data.signon.SignonRequestMessageSet;
import net.sf.ofx4j.domain.data.signon.SignonResponse;
import net.sf.ofx4j.net.OFXConnection;
import net.sf.ofx4j.net.OFXConnectionException;

import java.net.URL;
import java.util.Date;
import java.util.TreeSet;

/**
 * Base implementation for the financial instutiton.
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

  public FinancialInstitutionProfile readProfile() throws OFXException {
    RequestEnvelope request = new RequestEnvelope();
    TreeSet<RequestMessageSet> messageSets = new TreeSet<RequestMessageSet>();
    SignonRequestMessageSet signonRequest = new SignonRequestMessageSet();
    signonRequest.setSignonRequest(createProfileSignonRequest());
    messageSets.add(signonRequest);
    ProfileRequestMessageSet profileRequestMS = new ProfileRequestMessageSet();
    profileRequestMS.setProfileRequest(createProfileTransaction());
    messageSets.add(profileRequestMS);
    request.setMessageSets(messageSets);
    String requestId = request.getUID();
    ResponseEnvelope response = sendRequest(request, getData().getOFXURL());
    return getProfile(requestId, response);
  }

  /**
   * Send a request.
   *
   * @param request The request.
   * @param profileURL The profile url.
   * @return The request.
   */
  protected ResponseEnvelope sendRequest(RequestEnvelope request, URL profileURL) throws OFXConnectionException {
    return getConnection().sendRequest(request, profileURL);
  }

  /**
   * Open the specified response envelope and look for the profile.
   *
   * @param requestId The request id.
   * @param response The response envelope.
   * @return The profile.
   */
  protected FinancialInstitutionProfile getProfile(String requestId, ResponseEnvelope response) throws OFXException {
    if (response.getSecurity() != ApplicationSecurity.NONE) {
      throw new OFXException(String.format("Unable to participate in %s security.", response.getSecurity()));
    }

    if (!requestId.equals(response.getUID())) {
      throw new OFXException(String.format("Invalid transaction ID '%s' in response.  Expected: %s", response.getUID(), requestId));
    }

    SignonResponse signonResponse = response.getSignonResponse();
    if (signonResponse == null) {
      throw new OFXException("No response to the signon request.");
    }

    validateStatus(signonResponse);

    ProfileResponseTransaction transactionResponse = ((ProfileResponseMessageSet) response.getMessageSet(MessageSetType.profile)).getProfileResponse();
    validateStatus(transactionResponse);
    return transactionResponse.getMessage();
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

    if (!Status.Code.SUCCESS.equals(status.getCode())) {
      String message = status.getMessage();
      if (message == null) {
        message = "No response status code.";

        if (status.getCode() != null) {
          message = status.getCode().getMessage();
        }
      }

      throw new OFXException("Invalid " + statusHolder.getStatusHolderName() + ":" + message);
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
   * The signon request.
   *
   * @return The signon request.
   */
  protected SignonRequest createProfileSignonRequest() {
    SignonRequest signonRequest = new SignonRequest();
    signonRequest.setTimestamp(new Date());
    net.sf.ofx4j.domain.data.signon.FinancialInstitution fi = new net.sf.ofx4j.domain.data.signon.FinancialInstitution();
    fi.setOrganization(getData().getOrganization());
    signonRequest.setFinancialInstitution(fi);
    signonRequest.setUserId(SignonRequest.ANONYMOUS_USER);
    signonRequest.setPassword(SignonRequest.ANONYMOUS_USER);
    signonRequest.setApplicationId(OFXApplicationContextHolder.getCurrentContext().getAppId());
    signonRequest.setApplicationVersion(OFXApplicationContextHolder.getCurrentContext().getAppVersion());
    return signonRequest;
  }

  public OFXConnection getConnection() {
    return connection;
  }

  public FinancialInstitutionData getData() {
    return data;
  }
}
