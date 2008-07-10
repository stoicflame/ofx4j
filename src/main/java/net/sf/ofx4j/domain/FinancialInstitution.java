package net.sf.ofx4j.domain;

import net.sf.ofx4j.services.OFXServiceException;

/**
 * @author Ryan Heaton
 */
public interface FinancialInstitution {

  /**
   * Read the specified financial institution profile.
   *
   * @return The profile.
   */
  FinancialInstitutionProfile readProfile() throws OFXServiceException;

}
