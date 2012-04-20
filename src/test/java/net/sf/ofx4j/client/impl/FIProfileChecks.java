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

import net.sf.ofx4j.OFXException;
import net.sf.ofx4j.client.FinancialInstitution;
import net.sf.ofx4j.client.FinancialInstitutionData;
import net.sf.ofx4j.client.FinancialInstitutionDataStore;
import net.sf.ofx4j.client.net.OFXServerException;
import net.sf.ofx4j.client.net.OFXV1Connection;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Fetch profile for provided fid, otherwise run through the list of institutions in the DataStore
 *
 * @author Ryan Heaton
 */
public class FIProfileChecks {

  public static void main(String[] args) throws Exception {
    FinancialInstitutionDataStore dataStore = new LocalResourceFIDataStore();
    OFXV1Connection connection = new OFXV1Connection() {
      @Override
      protected void logRequest(ByteArrayOutputStream outBuffer) throws UnsupportedEncodingException {
        //don't log...
      }
    };
    for (FinancialInstitutionData data : dataStore.getInstitutionDataList()) {
      if (args.length == 0 || data.getId().equals(args[0])) {
        FinancialInstitution fi = new FinancialInstitutionImpl(data, connection);
        System.out.println(String.format("Reading profile for %s (id %s) at %s...", fi.getData().getName(), fi.getData().getId(), fi.getData().getOFXURL()));
        try {
          fi.readProfile();
        }
        catch (OFXServerException e) {
          System.out.println("ERROR " + e.getHttpCode() + ": " + e.getMessage());
        }
        catch (OFXException e) {
          System.out.println("ERROR: " + e.getMessage());
        }
        System.out.println("DONE");
        System.out.println();
      }
    }
  }
}
