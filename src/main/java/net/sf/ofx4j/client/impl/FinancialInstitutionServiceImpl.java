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

import net.sf.ofx4j.client.FinancialInstitution;
import net.sf.ofx4j.client.FinancialInstitutionData;
import net.sf.ofx4j.client.FinancialInstitutionDataStore;
import net.sf.ofx4j.client.FinancialInstitutionService;
import net.sf.ofx4j.client.net.OFXConnection;
import net.sf.ofx4j.client.net.OFXV1Connection;

/**
 * @author Ryan Heaton
 */
public class FinancialInstitutionServiceImpl implements FinancialInstitutionService {

  private OFXConnection connection = new OFXV1Connection();
  private FinancialInstitutionDataStore dataStore;

  public FinancialInstitution getFinancialInstitution(String fid) {
    return dataStore == null ? null : getFinancialInstitution(getDataStore().getInstitutionData(fid));
  }

  public FinancialInstitution getFinancialInstitution(FinancialInstitutionData data) {
    if (data == null) {
      return null;
    }

    return new FinancialInstitutionImpl(data, getConnection());
  }

  public FinancialInstitutionDataStore getDataStore() {
    return dataStore;
  }

  public void setDataStore(FinancialInstitutionDataStore dataStore) {
    this.dataStore = dataStore;
  }

  public OFXConnection getConnection() {
    return connection;
  }

  public void setConnection(OFXConnection connection) {
    this.connection = connection;
  }
}
