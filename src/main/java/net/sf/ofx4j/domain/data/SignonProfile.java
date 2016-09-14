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

package net.sf.ofx4j.domain.data;

import net.sf.ofx4j.domain.data.profile.CharacterType;

/**
 * @author Ryan Heaton
 */
public interface SignonProfile {
  /**
   * The name of the sign-on realm.
   *
   * @return The name of the sign-on realm.
   */
  String getRealm();

  /**
   * The minimum number of password characters.
   *
   * @return The minimum number of password characters.
   */
  Integer getMinPasswordCharacters();

  /**
   * The maximum number of password characters.
   *
   * @return The maximum number of password characters.
   */
  Integer getMaxPasswordCharacters();

  /**
   * The type of password characters supported.
   *
   * @return The type of password characters supported.
   */
  CharacterType getPasswordCharacterType();

  /**
   * Whether the password is case-sensitive.
   *
   * @return Whether the password is case-sensitive.
   */
  Boolean getPasswordCaseSensitive();

  /**
   * Whether special characters are allowed in the password.
   *
   * @return Whether special characters are allowed in the password.
   */
  Boolean getPasswordSpecialCharsAllowed();

  /**
   * Whether spaces are allowed in the password.
   *
   * @return Whether spaces are allowed in the password.
   */
  Boolean getPasswordSpacesAllowed();

  /**
   * Whether the server can process a password change request for this realm.
   *
   * @return Whether the server can process a password change request for this realm.
   */
  Boolean getChangePasswordSupported();

  /**
   * Whether the server requires the user to change their password as part of their first signon.
   *
   * @return Whether the server requires the user to change their password as part of their first signon.
   */
  Boolean getChangePasswordFirstRequired();

  /**
   * Label for a set of additional credentials that the user must supply.
   *
   * @return Label for a set of additional credentials that the user must supply.
   */
  String getAdditionalCredientialsLabel1();

  /**
   * Label for a set of additional credentials that the user must supply.
   *
   * @return Label for a set of additional credentials that the user must supply.
   */
  String getAdditionalCredientialsLabel2();

  /**
   * Whether a client UID is required for teh sign-on.
   *
   * @return Whether a client UID is required for teh sign-on.
   */
  Boolean getClientUIDRequired();

  /**
   * Whether an auth token is required for the sign-on.
   *
   * @return Whether an auth token is required for the sign-on.
   */
  Boolean getAuthTokenRequiredForFirstSignon();

  /**
   * The label of the auth token.
   *
   * @return The label of the auth token.
   */
  String getAuthTokenLabel();

  /**
   * The URL for the auth token information.
   *
   * @return The URL for the auth token information.
   */
  String getAuthTokenInfoURL();

  /**
   * Whether MFA is supported.
   *
   * @return Whether MFA is supported.
   */
  Boolean getMfaSupported();

  /**
   * Whether an MFA challenge request is required for the first sign-on into this realm.
   *
   * @return Whether an MFA challenge request is required for the first sign-on into this realm.
   */
  Boolean getMfaChallengeRequiredForFirstSignon();
}
