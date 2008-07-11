package net.sf.ofx4j.client;

import net.sf.ofx4j.domain.data.MessageSetProfile;
import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.SignonProfile;

import java.net.URL;
import java.util.Date;

/**
 * @author Ryan Heaton
 */
public interface FinancialInstitutionProfile {

  /**
   * When this profile was last updated.
   *
   * @return When this profile was last updated.
   */
  Date getLastUpdated();

  /**
   * The name of the financial institution.
   *
   * @return The name of the financial institution.
   */
  String getFinancialInstitutionName();

  /**
   * The address of the financial institution.
   *
   * @return The address of the financial institution.
   */
  String getAddress1();

  /**
   * The address of the financial institution.
   *
   * @return The address of the financial institution.
   */
  String getAddress2();

  /**
   * The address of the financial institution.
   *
   * @return The address of the financial institution.
   */
  String getAddress3();

  /**
   * The city of the financial institution.
   *
   * @return The city of the financial institution.
   */
  String getCity();

  /**
   * The state of this financial institution.
   *
   * @return The state of this financial institution.
   */
  String getState();

  /**
   * The postal code of this financial institution.
   *
   * @return The postal code of this financial institution.
   */
  String getZip();

  /**
   * The country code for this financial institution.
   *
   * @return The country code for this financial institution.
   * @see java.util.Locale#getISO3Country()
   */
  String getCountry();

  /**
   * The phone number to customer service.
   *
   * @return The phone number to customer service.
   */
  String getCustomerServicePhone();

  /**
   * The phone number to tech support.
   *
   * @return The phone number to tech support.
   */
  String getTechnicalSupportPhone();

  /**
   * The fax number.
   *
   * @return The fax number.
   */
  String getFax();

  /**
   * URL for the financial institution.
   *
   * @return URL for the financial institution.
   */
  String getSiteURL();

  /**
   * The email for this FI
   *
   * @return The email for this FI
   */
  String getEmail();

  /**
   * Get the message set profile for the specified message set.
   *
   * @param type The message set type for which to retrieve the profile.
   * @return The message set profile information, or null if the FI doesn't support any message sets of the specified type.
   * @throws IllegalStateException If multiple versions of the specified message set exist.
   */
  MessageSetProfile getMessageSetProfile(MessageSetType type);

  /**
   * Get the message set profile for the specified message set and the specified version.
   *
   * @param type The message set type for which to retrieve the profile.
   * @param version The version for which to retrieve the profile.
   * @return The message set profile information, or null if the FI doesn't support the specified message set of the specified version.
   */
  MessageSetProfile getMessageSetProfile(MessageSetType type, String version);

  /**
   * Get the signon profile for the specified message set.
   *
   * @param messageSet The message set.
   * @return The signon profile, or null if none was found.
   */
  SignonProfile getSignonProfile(MessageSetProfile messageSet);
}
