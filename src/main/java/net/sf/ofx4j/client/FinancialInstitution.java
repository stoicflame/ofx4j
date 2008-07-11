package net.sf.ofx4j.client;

import net.sf.ofx4j.OFXException;

/**
 * @author Ryan Heaton
 */
public interface FinancialInstitution {

  /**
   * The financial institution data defining this FI.
   *
   * @return The financial institution data.
   */
  FinancialInstitutionData getData();

  /**
   * Read the specified financial institution profile. Implies a network call.
   *
   * @return The profile.
   * @throws OFXException if something goes awry.
   */
  FinancialInstitutionProfile readProfile() throws OFXException;
}
