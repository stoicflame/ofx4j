package net.sf.ofx4j.client.context;

/**
 * The application context.
 *
 * @author Ryan Heaton
 */
public interface OFXApplicationContext {

  /**
   * The current application id.
   *
   * @return The current application id.
   */
  String getAppId();

  /**
   * The application version.
   *
   * @return The application version.
   */
  String getAppVersion();
}
