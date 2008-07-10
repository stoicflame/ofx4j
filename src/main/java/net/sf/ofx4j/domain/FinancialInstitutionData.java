package net.sf.ofx4j.domain;

import java.net.URL;

/**
 * Interface for core FI data.  This is the base set of information
 * required in order to initiate a connection to an FI server.
 *
 * @author Ryan Heaton
 */
public interface FinancialInstitutionData {

  /**
   * The name of the FI.
   *
   * @return The name of the FI.
   */
  String getName();

  /**
   * The id of the FI.
   *
   * @return The id of the FI.
   */
  String getId();

  /**
   * The OFX organization name.
   *
   * @return The OFX organization name.
   */
  String getOrganization();

  /**
   * The URL to the OFX server for this institution.
   *
   * @return The URL to the OFX server for this institution.
   */
  URL getOFXURL();

  /**
   * The broker id.
   *
   * @return The broker id.
   */
  String getBrokerId();
}
