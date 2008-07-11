package net.sf.ofx4j.client;

/**
 * @author Ryan Heaton
 */
public interface FinancialInstitutionService {

  /**
   * Get the financial institution by the specified id.
   *
   * @param fid The financial institution id.
   * @return The financial institution, or null if not found.
   */
  public FinancialInstitution getFinancialInstitution(String fid);

  /**
   * Get the financial institution by the specified data.
   *
   * @param data The financial institution data.
   * @return The financial institution, or null if not found.
   */
  public FinancialInstitution getFinancialInstitution(FinancialInstitutionData data);

}
