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

package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.domain.data.SignonProfile;

/**
 * Sign-on information
 *
 * @author Ryan Heaton
 * @see "Section 7.2.2, OFX Spec"
 */
@Aggregate( "SIGNONINFO" )
public class SignonInfo implements SignonProfile {

  private String realm;
  private Integer minPasswordCharacters;
  private Integer maxPasswordCharacters;
  private CharacterType passwordCharacterType;
  private Boolean passwordCaseSensitive = true;
  private Boolean passwordSpecialCharsAllowed = true;
  private Boolean passwordSpacesAllowed = true;
  private Boolean changePasswordSupported;
  private Boolean changePasswordFirstRequired;
  private String additionalCredientialsLabel1;
  private String additionalCredientialsLabel2;
  private Boolean clientUIDRequired;
  private Boolean authTokenRequiredForFirstSignon;
  private String authTokenLabel;
  private String authTokenInfoURL;
  private Boolean mfaSupported;
  private Boolean mfaChallengeRequiredForFirstSignon;

  /**
   * The name of the sign-on realm.
   *
   * @return The name of the sign-on realm.
   */
  @Element ( name = "SIGNONREALM", required = true, order = 0 )
  public String getRealm() {
    return realm;
  }

  /**
   * The name of the sign-on realm.
   *
   * @param realm The name of the sign-on realm.
   */
  public void setRealm(String realm) {
    this.realm = realm;
  }

  /**
   * The minimum number of password characters.
   *
   * @return The minimum number of password characters.
   */
  @Element ( name = "MIN", required = true, order = 10 )
  public Integer getMinPasswordCharacters() {
    return minPasswordCharacters;
  }

  /**
   * The minimum number of password characters.
   *
   * @param minPasswordCharacters The minimum number of password characters.
   */
  public void setMinPasswordCharacters(Integer minPasswordCharacters) {
    this.minPasswordCharacters = minPasswordCharacters;
  }

  /**
   * The maximum number of password characters.
   *
   * @return The maximum number of password characters.
   */
  @Element ( name = "MAX", required = true, order = 20 )
  public Integer getMaxPasswordCharacters() {
    return maxPasswordCharacters;
  }

  /**
   * The maximum number of password characters.
   *
   * @param maxPasswordCharacters The maximum number of password characters.
   */
  public void setMaxPasswordCharacters(Integer maxPasswordCharacters) {
    this.maxPasswordCharacters = maxPasswordCharacters;
  }

  /**
   * The type of password characters supported.
   *
   * @return The type of password characters supported.
   */
  @Element ( name = "CHARTYPE", required = true, order = 30 )
  public CharacterType getPasswordCharacterType() {
    return passwordCharacterType;
  }

  /**
   * The type of password characters supported.
   *
   * @param passwordCharacterType The type of password characters supported.
   */
  public void setPasswordCharacterType(CharacterType passwordCharacterType) {
    this.passwordCharacterType = passwordCharacterType;
  }

  /**
   * Whether the password is case-sensitive.
   *
   * @return Whether the password is case-sensitive.
   */
  @Element ( name = "CASESEN", required = true, order = 40 )
  public Boolean getPasswordCaseSensitive() {
    return passwordCaseSensitive;
  }

  /**
   * Whether the password is case-sensitive.
   *
   * @param passwordCaseSensitive Whether the password is case-sensitive.
   */
  public void setPasswordCaseSensitive(Boolean passwordCaseSensitive) {
    this.passwordCaseSensitive = passwordCaseSensitive;
  }

  /**
   * Whether special characters are allowed in the password.
   *
   * @return Whether special characters are allowed in the password.
   */
  @Element ( name = "SPECIAL", required = true, order = 50 )
  public Boolean getPasswordSpecialCharsAllowed() {
    return passwordSpecialCharsAllowed;
  }

  /**
   * Whether special characters are allowed in the password.
   *
   * @param passwordSpecialCharsAllowed Whether special characters are allowed in the password.
   */
  public void setPasswordSpecialCharsAllowed(Boolean passwordSpecialCharsAllowed) {
    this.passwordSpecialCharsAllowed = passwordSpecialCharsAllowed;
  }

  /**
   * Whether spaces are allowed in the password.
   *
   * @return Whether spaces are allowed in the password.
   */
  @Element ( name = "SPACES", required = true, order = 60 )
  public Boolean getPasswordSpacesAllowed() {
    return passwordSpacesAllowed;
  }

  /**
   * Whether spaces are allowed in the password.
   *
   * @param passwordSpacesAllowed Whether spaces are allowed in the password.
   */
  public void setPasswordSpacesAllowed(Boolean passwordSpacesAllowed) {
    this.passwordSpacesAllowed = passwordSpacesAllowed;
  }

  /**
   * Whether the server can process a password change request for this realm.
   *
   * @return Whether the server can process a password change request for this realm.
   */
  @Element ( name = "PINCH", required = true, order = 70 )
  public Boolean getChangePasswordSupported() {
    return changePasswordSupported;
  }

  /**
   * Whether the server can process a password change request for this realm.
   *
   * @param changePasswordSupported Whether the server can process a password change request for this realm.
   */
  public void setChangePasswordSupported(Boolean changePasswordSupported) {
    this.changePasswordSupported = changePasswordSupported;
  }

  /**
   * Whether the server requires the user to change their password as part of their first signon.
   *
   * @return Whether the server requires the user to change their password as part of their first signon.
   */
  @Element ( name = "CHGPINFIRST", required = true, order = 80 )
  public Boolean getChangePasswordFirstRequired() {
    return changePasswordFirstRequired;
  }

  /**
   * Whether the server requires the user to change their password as part of their first signon.
   *
   * @param changePasswordFirstRequired Whether the server requires the user to change their password as part of their first signon.
   */
  public void setChangePasswordFirstRequired(Boolean changePasswordFirstRequired) {
    this.changePasswordFirstRequired = changePasswordFirstRequired;
  }

  /**
   * Label for a set of additional credentials that the user must supply.
   *
   * @return Label for a set of additional credentials that the user must supply.
   */
  @Element ( name = "USERCRED1LABEL", order = 90 )
  public String getAdditionalCredientialsLabel1() {
    return additionalCredientialsLabel1;
  }

  /**
   * Label for a set of additional credentials that the user must supply.
   *
   * @param additionalCredientialsLabel1 Label for a set of additional credentials that the user must supply.
   */
  public void setAdditionalCredientialsLabel1(String additionalCredientialsLabel1) {
    this.additionalCredientialsLabel1 = additionalCredientialsLabel1;
  }

  /**
   * Label for a set of additional credentials that the user must supply.
   *
   * @return Label for a set of additional credentials that the user must supply.
   */
  @Element ( name = "USERCRED2LABEL", order = 100 )
  public String getAdditionalCredientialsLabel2() {
    return additionalCredientialsLabel2;
  }

  /**
   * Label for a set of additional credentials that the user must supply.
   *
   * @param additionalCredientialsLabel2 Label for a set of additional credentials that the user must supply.
   */
  public void setAdditionalCredientialsLabel2(String additionalCredientialsLabel2) {
    this.additionalCredientialsLabel2 = additionalCredientialsLabel2;
  }

  /**
   * Whether a client UID is required for teh sign-on.
   *
   * @return Whether a client UID is required for teh sign-on.
   */
  @Element ( name = "CLIENTUIDREQ", order = 110 )
  public Boolean getClientUIDRequired() {
    return clientUIDRequired;
  }

  /**
   * Whether a client UID is required for teh sign-on.
   *
   * @param clientUIDRequired Whether a client UID is required for teh sign-on.
   */
  public void setClientUIDRequired(Boolean clientUIDRequired) {
    this.clientUIDRequired = clientUIDRequired;
  }

  /**
   * Whether an auth token is required for the sign-on.
   *
   * @return Whether an auth token is required for the sign-on.
   */
  @Element ( name = "AUTHTOKENFIRST", order = 120 )
  public Boolean getAuthTokenRequiredForFirstSignon() {
    return authTokenRequiredForFirstSignon;
  }

  /**
   * Whether an auth token is required for the sign-on.
   *
   * @param authTokenRequiredForFirstSignon
   *         Whether an auth token is required for the sign-on.
   */
  public void setAuthTokenRequiredForFirstSignon(Boolean authTokenRequiredForFirstSignon) {
    this.authTokenRequiredForFirstSignon = authTokenRequiredForFirstSignon;
  }

  /**
   * The label of the auth token.
   *
   * @return The label of the auth token.
   */
  @Element ( name = "AUTHTOKENLABEL", order = 130 )
  public String getAuthTokenLabel() {
    return authTokenLabel;
  }

  /**
   * The label of the auth token.
   *
   * @param authTokenLabel The label of the auth token.
   */
  public void setAuthTokenLabel(String authTokenLabel) {
    this.authTokenLabel = authTokenLabel;
  }

  /**
   * The URL for the auth token information.
   *
   * @return The URL for the auth token information.
   */
  @Element ( name = "AUTHTOKENINFOURL", order = 140 )
  public String getAuthTokenInfoURL() {
    return authTokenInfoURL;
  }

  /**
   * The URL for the auth token information.
   *
   * @param authTokenInfoURL The URL for the auth token information.
   */
  public void setAuthTokenInfoURL(String authTokenInfoURL) {
    this.authTokenInfoURL = authTokenInfoURL;
  }

  /**
   * Whether MFA is supported.
   *
   * @return Whether MFA is supported.
   */
  @Element ( name = "MFACHALLENGESUPT", order = 150 )
  public Boolean getMfaSupported() {
    return mfaSupported;
  }

  /**
   * Whether MFA is supported.
   *
   * @param mfaSupported Whether MFA is supported.
   */
  public void setMfaSupported(Boolean mfaSupported) {
    this.mfaSupported = mfaSupported;
  }

  /**
   * Whether an MFA challenge request is required for the first sign-on into this realm.
   *
   * @return Whether an MFA challenge request is required for the first sign-on into this realm.
   */
  @Element ( name = "MFACHALLENGEFIRST", order = 160 )
  public Boolean getMfaChallengeRequiredForFirstSignon() {
    return mfaChallengeRequiredForFirstSignon;
  }

  /**
   * Whether an MFA challenge request is required for the first sign-on into this realm.
   *
   * @param mfaChallengeRequiredForFirstSignon
   *         Whether an MFA challenge request is required for the first sign-on into this realm.
   */
  public void setMfaChallengeRequiredForFirstSignon(Boolean mfaChallengeRequiredForFirstSignon) {
    this.mfaChallengeRequiredForFirstSignon = mfaChallengeRequiredForFirstSignon;
  }
}
