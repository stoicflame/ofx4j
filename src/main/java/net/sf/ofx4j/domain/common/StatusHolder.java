package net.sf.ofx4j.domain.common;

/**
 * A status holder (usually applied to a response).
 *
 * @author Ryan Heaton
 */
public interface StatusHolder {

  /**
   * The name of this status holder (for error reporting).
   *
   * @return The name of this status holder (for error reporting).
   */
  String getStatusHolderName();

  /**
   * Get the status.
   *
   * @return The status.
   */
  Status getStatus();
}
