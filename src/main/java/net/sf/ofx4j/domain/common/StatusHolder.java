package net.sf.ofx4j.domain.common;

/**
 * A status holder (usually applied to a response).
 *
 * @author Ryan Heaton
 */
public interface StatusHolder {

  /**
   * Get the status.
   *
   * @return The status.
   */
  Status getStatus();
}
