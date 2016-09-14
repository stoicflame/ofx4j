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

import net.sf.ofx4j.domain.data.profile.SynchronizationCapability;

import java.net.URL;

/**
 * @author Ryan Heaton
 */
public interface MessageSetProfile {
  /**
   * Version of the message set.
   *
   * @return The version of the message set.
   */
  String getVersion();

  /**
   * The name of the service provider (sometimes the message set processing is outsourced).
   *
   * @return The name of the service provider (sometimes the message set processing is outsourced).
   */
  String getServiceProviderName();

  /**
   * The URL at which the message set is processed.
   *
   * @return The URL at which the message set is processed.
   */
  String getUrl();

  /**
   * The application-level security required for this message set.
   *
   * @return The application-level security required for this message set.
   */
  ApplicationSecurity getSecurity();

  /**
   * Whether transport-level security is required for this message set.
   *
   * @return Whether transport-level security is required for this message set.
   */
  boolean isSslRequired();

  /**
   * The sign-on realm.
   *
   * @return The sign-on realm.
   */
  String getRealm();

  /**
   * The language.
   *
   * @return The language.
   * @see java.util.Locale#getISO3Language()
   */
  String getLanguage();

  /**
   * The synchronization capability for this message set.
   *
   * @return The synchronization capability for this message set.
   */
  SynchronizationCapability getSyncCapability();

  /**
   * Whether there exists support for resposne-file based error recovery.
   *
   * @return Whether there exists support for resposne-file based error recovery.
   */
  boolean hasFileBasedErrorRecoverySupport();
}
