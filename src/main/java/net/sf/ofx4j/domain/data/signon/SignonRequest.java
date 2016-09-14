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

package net.sf.ofx4j.domain.data.signon;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.domain.data.RequestMessage;

import java.util.Date;
import java.util.Locale;

/**
 * Sign-on request
 *
 * @author Ryan Heaton
 * @see "Section 2.5.1.2, OFX Spec."
 */
@Aggregate ( "SONRQ" )
public class SignonRequest extends RequestMessage {

  /**
   * @see "Section 2.5.1"
   */
  public static final String ANONYMOUS_USER = "anonymous00000000000000000000000";

  private Date timestamp;
  private String userId;
  private String password;
  private String userKey;
  private Boolean generateUserKey;
  private String language = Locale.US.getISO3Language().toUpperCase();
  private FinancialInstitution financialInstitution;
  private String sessionId;
  private String applicationId = "Money"; //many institutions just won't work with an unrecognized app id...
  private String applicationVersion = "1600"; //many institutions just won't work with an unrecognized app id...
  private String clientUID;
  private String additionalCredentials1;
  private String additionalCredentials2;
  private String authToken;
  private String accessKey;

  /**
   * The date and time of the request.
   *
   * @return The date and time of the request.
   */
  @Element ( name = "DTCLIENT", required = true, order = 0 )
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * The date and time of the request.
   *
   * @param timestamp The date and time of the request.
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * The user id.
   *
   * @return The user id.
   */
  @Element ( name = "USERID", order = 10 )
  public String getUserId() {
    return userId;
  }

  /**
   * The user id.
   *
   * @param userId The user id.
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * The password.
   *
   * @return The password.
   */
  @Element ( name = "USERPASS", order = 20 )
  public String getPassword() {
    return password;
  }

  /**
   * The password.
   *
   * @param password The password.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * The user key provided by the server so as not to require further username/password authentication.
   *
   * @return The user key provided by the server so as not to require further username/password authentication.
   */
  @Element ( name = "USERKEY", order = 30 )
  public String getUserKey() {
    return userKey;
  }

  /**
   * The user key provided by the server so as not to require further username/password authentication.
   *
   * @param userKey The user key provided by the server so as not to require further username/password authentication.
   */
  public void setUserKey(String userKey) {
    this.userKey = userKey;
  }

  /**
   * Whether to request the server to generate a user key.
   *
   * @return Whether to request the server to generate a user key.
   */
  @Element ( name = "GENUSERKEY", order = 40 )
  public Boolean getGenerateUserKey() {
    return generateUserKey;
  }

  /**
   * Whether to request the server to generate a user key.
   *
   * @param generateUserKey Whether to request the server to generate a user key.
   */
  public void setGenerateUserKey(Boolean generateUserKey) {
    this.generateUserKey = generateUserKey;
  }

  /**
   * The three-letter langauge code.
   *
   * @return The three-letter langauge code.
   * @see java.util.Locale#getISO3Language()
   */
  @Element ( name = "LANGUAGE", required = true, order = 50 )
  public String getLanguage() {
    return language;
  }

  /**
   * The three-letter langauge code.
   *
   * @param language The three-letter langauge code.
   */
  public void setLanguage(String language) {
    this.language = language;
  }

  /**
   * The financial institution.
   *
   * @return The financial institution.
   */
  @ChildAggregate ( order = 60 )
  public FinancialInstitution getFinancialInstitution() {
    return financialInstitution;
  }

  /**
   * The financial institution.
   *
   * @param financialInstitution The financial institution.
   */
  public void setFinancialInstitution(FinancialInstitution financialInstitution) {
    this.financialInstitution = financialInstitution;
  }

  /**
   * The server-supplied session id.
   *
   * @return The server-supplied session id.
   */
  @Element ( name = "SESSCOOKIE", order = 70 )
  public String getSessionId() {
    return sessionId;
  }

  /**
   * The server-supplied session id.
   *
   * @param sessionId The server-supplied session id.
   */
  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  /**
   * The application id.
   *
   * @return The application id.
   */
  @Element ( name = "APPID", required = true, order = 80 )
  public String getApplicationId() {
    return applicationId;
  }

  /**
   * The application id.
   *
   * @param applicationId The application id.
   */
  public void setApplicationId(String applicationId) {
    this.applicationId = applicationId;
  }

  /**
   * The application version.
   *
   * @return The application version.
   */
  @Element ( name = "APPVER", required = true, order = 90 )
  public String getApplicationVersion() {
    return applicationVersion;
  }

  /**
   * The application version.
   *
   * @param applicationVersion The application version.
   */
  public void setApplicationVersion(String applicationVersion) {
    this.applicationVersion = applicationVersion;
  }

  /**
   * The client-supplied UID.
   *
   * @return The client-supplied UID.
   */
  @Element ( name = "CLIENTUID", order = 100 )
  public String getClientUID() {
    return clientUID;
  }

  /**
   * The client-supplied UID.
   *
   * @param clientUID The client-supplied UID.
   */
  public void setClientUID(String clientUID) {
    this.clientUID = clientUID;
  }

  /**
   * Any additional credentials.
   *
   * @return Any additional credentials.
   */
  @Element ( name = "USERCRED1", order = 110 )
  public String getAdditionalCredentials1() {
    return additionalCredentials1;
  }

  /**
   * Any additional credentials.
   *
   * @param additionalCredentials1 Any additional credentials.
   */
  public void setAdditionalCredentials1(String additionalCredentials1) {
    this.additionalCredentials1 = additionalCredentials1;
  }

  /**
   * Any additional credentials.
   *
   * @return Any additional credentials.
   */
  @Element ( name = "USERCRED2", order = 120 )
  public String getAdditionalCredentials2() {
    return additionalCredentials2;
  }

  /**
   * Any additional credentials.
   *
   * @param additionalCredentials2 Any additional credentials.
   */
  public void setAdditionalCredentials2(String additionalCredentials2) {
    this.additionalCredentials2 = additionalCredentials2;
  }

  /**
   * The authentication token.
   *
   * @return The authentication token.
   */
  @Element ( name = "AUTHTOKEN", order = 130 )
  public String getAuthToken() {
    return authToken;
  }

  /**
   * The authentication token.
   *
   * @param authToken The authentication token.
   */
  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  /**
   * The access key.
   *
   * @return The access key.
   */
  @Element ( name = "ACCESSKEY", order = 140 )
  public String getAccessKey() {
    return accessKey;
  }

  /**
   * The access key.
   *
   * @param accessKey The access key.
   */
  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  //todo: MFA challenge stuff.
}
