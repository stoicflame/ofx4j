package net.sf.ofx4j.domain.profile;

import net.sf.ofx4j.domain.ApplicationSecurity;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.net.URL;
import java.util.Locale;

/**
 * Core information about a specific version of a specific message set.
 *
 * @author Ryan Heaton
 * @see "Section 7.2.1, OFX Spec"
 */
@Aggregate ( "MSGSETCORE" )
public class CoreMessageSetInfo {

  private String version = "1";
  private String serviceProviderName;
  private URL url;
  private ApplicationSecurity security;
  private Boolean sslRequired;
  private String realm;
  private String language = Locale.US.getISO3Language();
  private SynchronizationCapability syncCapability;
  private Boolean fileBasedErrorRecoverySupport;

  /**
   * Version of the message set.
   *
   * @return The version of the message set.
   */
  @Element ( name = "VER", required = true, order = 0 )
  public String getVersion() {
    return version;
  }

  /**
   * The version of the message set.
   *
   * @param version The version of the message set.
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * The name of the service provider (sometimes the message set processing is outsourced).
   *
   * @return The name of the service provider (sometimes the message set processing is outsourced).
   */
  @Element ( name = "SPNAME", order = 10 )
  public String getServiceProviderName() {
    return serviceProviderName;
  }

  /**
   * The name of the service provider (sometimes the message set processing is outsourced).
   *
   * @param serviceProviderName The name of the service provider (sometimes the message set processing is outsourced).
   */
  public void setServiceProviderName(String serviceProviderName) {
    this.serviceProviderName = serviceProviderName;
  }

  /**
   * The URL at which the message set is processed.
   *
   * @return The URL at which the message set is processed.
   */
  @Element ( name = "URL", required = true, order = 20 )
  public URL getUrl() {
    return url;
  }

  /**
   * The URL at which the message set is processed.
   *
   * @param url The URL at which the message set is processed.
   */
  public void setUrl(URL url) {
    this.url = url;
  }

  /**
   * The application-level security required for this message set.
   *
   * @return The application-level security required for this message set.
   */
  @Element ( name = "OFXSEC", required = true, order = 30 )
  public ApplicationSecurity getSecurity() {
    return security;
  }

  /**
   * The application-level security required for this message set.
   *
   * @param security The application-level security required for this message set.
   */
  public void setSecurity(ApplicationSecurity security) {
    this.security = security;
  }

  /**
   * Whether transport-level security is required for this message set.
   *
   * @return Whether transport-level security is required for this message set.
   */
  @Element ( name = "TRANSPSEC", required = true, order = 40 )
  public Boolean getSslRequired() {
    return sslRequired;
  }

  /**
   * Whether transport-level security is required for this message set.
   *
   * @param sslRequired Whether transport-level security is required for this message set.
   */
  public void setSslRequired(Boolean sslRequired) {
    this.sslRequired = sslRequired;
  }

  /**
   * The sign-on realm.
   *
   * @return The sign-on realm.
   */
  @Element ( name = "SIGNONREALM", required = true, order = 50 )
  public String getRealm() {
    return realm;
  }

  /**
   * The sign-on realm.
   *
   * @param realm The sign-on realm.
   */
  public void setRealm(String realm) {
    this.realm = realm;
  }

  /**
   * The language.
   *
   * @return The language.
   * @see java.util.Locale#getISO3Language()
   */
  @Element ( name = "LANGUAGE", required = true, order = 60 )
  public String getLanguage() {
    return language;
  }

  /**
   * The language.
   *
   * @param language The language.
   */
  public void setLanguage(String language) {
    this.language = language;
  }

  /**
   * The synchronization capability for this message set.
   *
   * @return The synchronization capability for this message set.
   */
  @Element ( name = "SYNCMODE", required = true, order = 70 )
  public SynchronizationCapability getSyncCapability() {
    return syncCapability;
  }

  /**
   * The synchronization capability for this message set.
   *
   * @param syncCapability The synchronization capability for this message set.
   */
  public void setSyncCapability(SynchronizationCapability syncCapability) {
    this.syncCapability = syncCapability;
  }

  /**
   * Whether there exists support for resposne-file based error recovery.
   *
   * @return Whether there exists support for resposne-file based error recovery.
   */
  @Element ( name = "RESPFILEER", required = true, order = 80 )
  public Boolean getFileBasedErrorRecoverySupport() {
    return fileBasedErrorRecoverySupport;
  }

  /**
   * Whether there exists support for resposne-file based error recovery.
   *
   * @param fileBasedErrorRecoverySupport Whether there exists support for resposne-file based error recovery.
   */
  public void setFileBasedErrorRecoverySupport(Boolean fileBasedErrorRecoverySupport) {
    this.fileBasedErrorRecoverySupport = fileBasedErrorRecoverySupport;
  }
}
