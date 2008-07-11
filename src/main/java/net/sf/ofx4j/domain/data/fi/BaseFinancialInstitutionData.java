package net.sf.ofx4j.domain.data.fi;

import net.sf.ofx4j.client.FinancialInstitutionData;

import java.net.URL;

/**
 * Base bean for FI data.
 *
 * @author Ryan Heaton
 */
public class BaseFinancialInstitutionData implements FinancialInstitutionData {

  private String id;
  private String fid;
  private String name;
  private String organization;
  private URL ofxUrl;
  private String brokerId;

  public BaseFinancialInstitutionData() {
  }

  public BaseFinancialInstitutionData(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFinancialInstitutionId() {
    return fid;
  }

  public void setFinancialInstitutionId(String id) {
    this.fid = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public URL getOFXURL() {
    return ofxUrl;
  }

  public void setOFXURL(URL OFXURL) {
    this.ofxUrl = OFXURL;
  }

  public String getBrokerId() {
    return brokerId;
  }

  public void setBrokerId(String brokerId) {
    this.brokerId = brokerId;
  }
}
