package net.sf.ofx4j.services;

import net.sf.ofx4j.domain.FinancialInstitutionProfile;

import java.net.URL;

/**
 * @author Ryan Heaton
 */
public interface FinancialInstitutionService {

  /**
   * Read the specified financial institution profile.
   *
   * @param profileURL The URL to the profile.
   * @return The profile.
   */
  FinancialInstitutionProfile readProfile(URL profileURL) throws OFXServiceException;

}
