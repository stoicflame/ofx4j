package net.sf.ofx4j.client;

import java.net.URL;

/**
 * Interface for core FI data.  This is the base set of information
 * required in order to initiate a connection to an FI server.
 *
 * @author Ryan Heaton
 */
public interface FinancialInstitutionData {

  /**
   * A unique id for this FI.
   *
   * @return A unique id for this FI.
   */
  String getId();

  /**
   * The id of the FI.
   *
   * @return The id of the FI.
   */
  String getFinancialInstitutionId();

  /**
   * The name of the FI.
   *
   * @return The name of the FI.
   */
  String getName();

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
