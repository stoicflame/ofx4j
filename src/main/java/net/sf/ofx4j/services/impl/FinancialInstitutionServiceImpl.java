package net.sf.ofx4j.services.impl;

import net.sf.ofx4j.domain.*;
import net.sf.ofx4j.domain.common.Status;
import net.sf.ofx4j.domain.common.StatusHolder;
import net.sf.ofx4j.domain.profile.*;
import net.sf.ofx4j.domain.signon.SignonRequest;
import net.sf.ofx4j.domain.signon.SignonRequestMessageSet;
import net.sf.ofx4j.domain.signon.SignonResponse;
import net.sf.ofx4j.domain.signon.FinancialInstitution;
import net.sf.ofx4j.net.OFXConnection;
import net.sf.ofx4j.services.FinancialInstitutionService;
import net.sf.ofx4j.services.OFXServiceException;

import java.net.URL;
import java.util.Date;
import java.util.TreeSet;

/**
 * @author Ryan Heaton
 */
public class FinancialInstitutionServiceImpl implements FinancialInstitutionService {

  private String appId = "OFX4J";
  private String appVersion = "0100";
  private OFXConnection connection;

  public FinancialInstitutionProfile readProfile(URL profileURL) throws OFXServiceException {
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
    ResponseEnvelope response = sendRequest(request, profileURL);
    return getProfile(requestId, response);
  }

  /**
   * Send a request.
   *
   * @param request The request.
   * @param profileURL The profile url.
   * @return The request.
   */
  protected ResponseEnvelope sendRequest(RequestEnvelope request, URL profileURL) throws OFXServiceException {
    ResponseEnvelope response;
    try {
      response = connection.sendRequest(request, profileURL);
    }
    catch (Exception e) {
      throw new OFXServiceException("Problem making OFX request.", e);
    }
    return response;
  }

  /**
   * Open the specified response envelope and look for the profile.
   *
   * @param requestId The request id.
   * @param response The response envelope.
   * @return The profile.
   */
  protected FinancialInstitutionProfile getProfile(String requestId, ResponseEnvelope response) throws OFXServiceException {
    if (response.getSecurity() != ApplicationSecurity.NONE) {
      throw new OFXServiceException(String.format("Unable to participate in %s security.", response.getSecurity()));
    }

    if (!requestId.equals(response.getUID())) {
      throw new OFXServiceException(String.format("Invalid transaction ID '%s' in response.  Expected: %s", response.getUID(), requestId));
    }

    SignonResponse signonResponse = response.getSignonResponse();
    if (signonResponse == null) {
      throw new OFXServiceException("No response to the signon request.");
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
  protected void validateStatus(StatusHolder statusHolder) throws OFXServiceException {
    Status status = statusHolder.getStatus();
    if (status == null) {
      throw new OFXServiceException("Invalid OFX response: no status returned in the " + statusHolder.getStatusHolderName() + " response.");
    }

    if (!Status.Code.SUCCESS.equals(status.getCode())) {
      String message = status.getMessage();
      if (message == null) {
        message = "No response status code.";

        if (status.getCode() != null) {
          message = status.getCode().getMessage();
        }
      }

      throw new OFXServiceException("Invalid " + statusHolder.getStatusHolderName() + ":" + message);
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
    FinancialInstitution fi = new FinancialInstitution();
    fi.setOrganization("AMEX");
    signonRequest.setFinancialInstitution(fi);
    signonRequest.setUserId(SignonRequest.ANONYMOUS_USER);
    signonRequest.setPassword(SignonRequest.ANONYMOUS_USER);
    signonRequest.setApplicationId(getAppId());
    signonRequest.setApplicationVersion(getAppVersion());
    return signonRequest;
  }

  public OFXConnection getConnection() {
    return connection;
  }

  public void setConnection(OFXConnection connection) {
    this.connection = connection;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }
}
