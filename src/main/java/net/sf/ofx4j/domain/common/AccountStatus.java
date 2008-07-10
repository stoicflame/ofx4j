package net.sf.ofx4j.domain.common;

/**
 * @author Ryan Heaton
 */
public enum AccountStatus {

  /**
   * Available, but not yet requested.
   */
  AVAIL,

  /**
   * Requested, but not yet available.
   */
  PEND,

  /**
   * Active.
   */
  ACTIVE

}
