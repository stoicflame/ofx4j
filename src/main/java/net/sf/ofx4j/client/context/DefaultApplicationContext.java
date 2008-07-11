package net.sf.ofx4j.client.context;

/**
 * Default application context.
 * 
 * @author Ryan Heaton
 */
public class DefaultApplicationContext implements OFXApplicationContext {

  private final String appId;
  private final String appVersion;

  public DefaultApplicationContext(String appId, String appVersion) {
    this.appId = appId;
    this.appVersion = appVersion;
  }

  public String getAppId() {
    return appId;
  }

  public String getAppVersion() {
    return appVersion;
  }
}
