package net.sf.ofx4j.client.context;

/**
 * @author Ryan Heaton
 */
public class OFXApplicationContextHolder {

  private static OFXApplicationContext CURRENT_CONTEXT = new DefaultApplicationContext("Money", "1600"); //some apps fail unless you're Quicken or Money...

  /**
   * Get the current (thread-safe) context.
   *
   * @return The thread-safe context.
   */
  public static OFXApplicationContext getCurrentContext() {
    //todo: implement a strategy (perhaps for thread-local access or something)?
    return CURRENT_CONTEXT;
  }

  /**
   * Set the current context.
   *
   * @param context The context.
   */
  public static void setCurrentContext(OFXApplicationContext context) {
    CURRENT_CONTEXT = context;
  }
}
