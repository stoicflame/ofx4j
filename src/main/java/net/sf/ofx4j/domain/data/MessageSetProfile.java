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
