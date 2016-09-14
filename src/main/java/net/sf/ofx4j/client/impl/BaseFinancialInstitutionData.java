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

package net.sf.ofx4j.client.impl;

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
