/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.domain.data.MessageSetProfile;
import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseMessage;
import net.sf.ofx4j.domain.data.SignonProfile;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.client.FinancialInstitutionProfile;

import java.net.URL;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;

/**
 * @author Ryan Heaton
 * @see "Section 7.2 OFX Spec"
 */
@Aggregate ( "PROFRS" )
public class ProfileResponse extends ResponseMessage implements FinancialInstitutionProfile {

  private MessageSetInfoList messageSetList;
  private SignonInfoList signonInfoList;
  private Date timestamp;
  private String financialInstitutionName;
  private String address1;
  private String address2;
  private String address3;
  private String city;
  private String state;
  private String zip;
  private String country;
  private String customerServicePhone;
  private String technicalSupportPhone;
  private String fax;
  private String siteURL;
  private String email;

  /**
   * List of message set information.
   * @return List of message set information.
   */
  @ChildAggregate ( order = 0 )
  public MessageSetInfoList getMessageSetList() {
    return messageSetList;
  }

  /**
   * List of message set information.
   *
   * @param messageSetList List of message set information.
   */
  public void setMessageSetList(MessageSetInfoList messageSetList) {
    this.messageSetList = messageSetList;
  }

  /**
   * List of signon information.
   *
   * @return List of signon information.
   */
  @ChildAggregate ( order = 10 )
  public SignonInfoList getSignonInfoList() {
    return signonInfoList;
  }

  /**
   * List of signon information.
   *
   * @param signonInfoList List of signon information.
   */
  public void setSignonInfoList(SignonInfoList signonInfoList) {
    this.signonInfoList = signonInfoList;
  }

  // Inherited.
  public String getResponseMessageName() {
    return "profile";
  }

  // Inherited.
  public Date getLastUpdated() {
    return getTimestamp();
  }

  /**
   * The timestamp of this profile update.
   *
   * @return The timestamp of this profile update.
   */
  @Element ( name = "DTPROFUP", order = 20 )
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * The timestamp of this profile update.
   *
   * @param timestamp The timestamp of this profile update.
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * The name of the financial institution.
   *
   * @return The name of the financial institution.
   */
  @Element ( name = "FINAME", order = 30 )
  public String getFinancialInstitutionName() {
    return financialInstitutionName;
  }

  /**
   * The name of the financial institution.
   *
   * @param financialInstitutionName The name of the financial institution.
   */
  public void setFinancialInstitutionName(String financialInstitutionName) {
    this.financialInstitutionName = financialInstitutionName;
  }

  /**
   * The address of the financial institution.
   *
   * @return The address of the financial institution.
   */
  @Element ( name = "ADDR1", required = true, order = 40 )
  public String getAddress1() {
    return address1;
  }

  /**
   * The address of the financial institution.
   *
   * @param address1 The address of the financial institution.
   */
  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  /**
   * The address of the financial institution.
   *
   * @return The address of the financial institution.
   */
  @Element ( name = "ADDR2", order = 50 )
  public String getAddress2() {
    return address2;
  }

  /**
   * The address of the financial institution.
   *
   * @param address2 The address of the financial institution.
   */
  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  /**
   * The address of the financial institution.
   *
   * @return The address of the financial institution.
   */
  @Element ( name = "ADDR3", order = 60 )
  public String getAddress3() {
    return address3;
  }

  /**
   * The address of the financial institution.
   *
   * @param address3 The address of the financial institution.
   */
  public void setAddress3(String address3) {
    this.address3 = address3;
  }

  /**
   * The city of the financial institution.
   *
   * @return The city of the financial institution.
   */
  @Element ( name = "CITY", required = true, order = 70 )
  public String getCity() {
    return city;
  }

  /**
   * The city of the financial institution.
   *
   * @param city The city of the financial institution.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * The state of this financial institution.
   *
   * @return The state of this financial institution.
   */
  @Element ( name = "STATE", required = true, order = 80 )
  public String getState() {
    return state;
  }

  /**
   * The state of this financial institution.
   *
   * @param state The state of this financial institution.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * The postal code of this financial institution.
   *
   * @return The postal code of this financial institution.
   */
  @Element ( name = "POSTALCODE", required = true, order = 90 )
  public String getZip() {
    return zip;
  }

  /**
   * The postal code of this financial institution.
   *
   * @param zip The postal code of this financial institution.
   */
  public void setZip(String zip) {
    this.zip = zip;
  }

  /**
   * The country code for this financial institution.
   *
   * @return The country code for this financial institution.
   * @see java.util.Locale#getISO3Country()
   */
  @Element ( name = "COUNTRY", required = true, order = 100 )
  public String getCountry() {
    return country;
  }

  /**
   * The country code for this financial institution.
   *
   * @param country The country code for this financial institution.
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * The phone number to customer service.
   *
   * @return The phone number to customer service.
   */
  @Element ( name = "CSPHONE", order = 110 )
  public String getCustomerServicePhone() {
    return customerServicePhone;
  }

  /**
   * The phone number to customer service.
   *
   * @param customerServicePhone The phone number to customer service.
   */
  public void setCustomerServicePhone(String customerServicePhone) {
    this.customerServicePhone = customerServicePhone;
  }

  /**
   * The phone number to tech support.
   *
   * @return The phone number to tech support.
   */
  @Element ( name = "TSPHONE", order = 120 )
  public String getTechnicalSupportPhone() {
    return technicalSupportPhone;
  }

  /**
   * The phone number to tech support.
   *
   * @param technicalSupportPhone The phone number to tech support.
   */
  public void setTechnicalSupportPhone(String technicalSupportPhone) {
    this.technicalSupportPhone = technicalSupportPhone;
  }

  /**
   * The fax number.
   *
   * @return The fax number.
   */
  @Element ( name = "FAXPHONE", order = 130 )
  public String getFax() {
    return fax;
  }

  /**
   * The fax number.
   *
   * @param fax The fax number.
   */
  public void setFax(String fax) {
    this.fax = fax;
  }

  /**
   * URL for the financial institution.
   *
   * @return URL for the financial institution.
   */
  @Element ( name = "URL", order = 140 )
  public String getSiteURL() {
    return siteURL;
  }

  /**
   * URL for the financial institution.
   *
   * @param siteURL URL for the financial institution.
   */
  public void setSiteURL(String siteURL) {
    this.siteURL = siteURL;
  }

  /**
   * The email for this FI
   *
   * @return The email for this FI
   */
  @Element ( name = "EMAIL", order = 150 )
  public String getEmail() {
    return email;
  }

  /**
   * The email for this FI
   *
   * @param email The email for this FI
   */
  public void setEmail(String email) {
    this.email = email;
  }

  public MessageSetProfile getMessageSetProfile(MessageSetType type) {
    Collection<MessageSetProfile> profiles = getProfiles(type);
    if (profiles.size() > 1) {
      throw new IllegalStateException("More than one profile of type " + type);
    }
    else if (profiles.isEmpty()) {
      return null;
    }
    else {
      return profiles.iterator().next();
    }
  }

  /**
   * Get all the profiles of the specified type.
   *
   * @param type The type.
   * @return The profiles.
   */
  protected Collection<MessageSetProfile> getProfiles(MessageSetType type) {
    Collection<MessageSetProfile> profiles = new ArrayList<MessageSetProfile>();
    if (getMessageSetList() != null && getMessageSetList().getInformationList() != null) {
      for (AbstractMessageSetInfo info : getMessageSetList().getInformationList()) {
        if (info.getVersionSpecificInformationList() != null) {
          for (VersionSpecificMessageSetInfo versionSpecificInfo : info.getVersionSpecificInformationList()) {
            if (versionSpecificInfo.getMessageSetType() == type) {
              profiles.add(versionSpecificInfo);
            }
          }
        }
      }
    }
    return profiles;
  }

  public MessageSetProfile getMessageSetProfile(MessageSetType type, String version) {
    for (MessageSetProfile profile : getProfiles(type)) {
      if (version == null) {
        if (profile.getVersion() == null) {
          return profile;
        }
      }
      else if (version.equals(profile.getVersion())) {
        return profile;
      }
    }
    
    return null;
  }

  public SignonProfile getSignonProfile(MessageSetProfile messageSet) {
    if (getSignonInfoList() != null && getSignonInfoList().getInfoList() != null) {
      for (SignonInfo signonInfo : getSignonInfoList().getInfoList()) {
        if (messageSet.getRealm() == null) {
          if (signonInfo.getRealm() == null) {
            return signonInfo;
          }
        }
        else if (messageSet.getRealm().equals(signonInfo.getRealm())) {
          return signonInfo;
        }
      }
    }
    return null;
  }
}