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

import java.io.FileOutputStream;

/**
 * @author Ryan Heaton
 */
public class ReassignLocalIds {

  public static void main(String[] args) throws Exception {
    LocalResourceFIDataStore store = new LocalResourceFIDataStore();
    for (FinancialInstitutionData data : store.getInstitutionDataList()) {
      BaseFinancialInstitutionData dataImpl = (BaseFinancialInstitutionData) data;
      dataImpl.setId(String.valueOf(dataImpl.hashCode()));
    }

    FileOutputStream out = new FileOutputStream("/tmp/institutions.xml");
    store.storeData(out);
    out.flush();
    out.close();
  }
}
