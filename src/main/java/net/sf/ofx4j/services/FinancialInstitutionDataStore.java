package net.sf.ofx4j.services;

import net.sf.ofx4j.domain.FinancialInstitutionData;

import java.util.List;

/**
 * @author Ryan Heaton
 */
public interface FinancialInstitutionDataStore {

  /**
   * Get the data for the financial institution of the specified id.
   *
   * @param fid The id of the financial institution.
   * @return The financial institution data, or null if none exists for the specified id.
   */
  FinancialInstitutionData getInstitutionData(String fid);

  /**
   * Get the whole list of financial institution data.
   *
   * @return The whole list of financial institution data.
   */
  List<FinancialInstitutionData> getInstitutionDataList();
}
