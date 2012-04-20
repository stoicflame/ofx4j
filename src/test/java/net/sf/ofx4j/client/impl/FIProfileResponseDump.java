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
import net.sf.ofx4j.client.FinancialInstitutionData;
import net.sf.ofx4j.client.FinancialInstitutionDataStore;
import net.sf.ofx4j.client.FinancialInstitutionProfile;
import net.sf.ofx4j.domain.data.RequestEnvelope;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.io.AggregateUnmarshaller;
import net.sf.ofx4j.io.OFXParseException;
import net.sf.ofx4j.client.net.OFXV1Connection;

import java.io.*;

/**
 * Writes FinancialInstitutionProfile response xml to file.
 *
 * @author Ryan Heaton
 * @author Scott Priddy
 */
public class FIProfileResponseDump {

  public static void main(final String[] args) throws Exception {
    if (args.length < 2) {
      System.out.println("Usage: FIProfileResponseDump <fid> <outFile>");
      System.exit(1);
    }
    FinancialInstitutionDataStore dataStore = new LocalResourceFIDataStore();
    final FinancialInstitutionData fiData = dataStore.getInstitutionData(args[0]);
    OFXV1Connection connection = new OFXV1Connection() {
      @Override
      protected void logRequest(ByteArrayOutputStream outBuffer) throws UnsupportedEncodingException {
      }
    };

    connection.setUnmarshaller(new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class) {
      @Override
      public ResponseEnvelope unmarshal(InputStream stream) throws IOException, OFXParseException {
        File file = new File(args[1]);
        System.out.println("Writing " + stream.available() + " bytes to " + file.getAbsolutePath() + " for response to " + fiData.getOFXURL() + "...");
        FileOutputStream out = new FileOutputStream(file);
        int ch = stream.read();
        while (ch != -1) {
          out.write(ch);
          ch = stream.read();
        }
        out.flush();
        out.close();
        return null;
      }
    });

    FinancialInstitutionImpl fi = new FinancialInstitutionImpl(fiData, connection) {
      @Override
      protected FinancialInstitutionProfile getProfile(ResponseEnvelope response) throws OFXException {
        return null;
      }

      @Override
      protected void doGeneralValidationChecks(RequestEnvelope request, ResponseEnvelope response) throws OFXException {
        // overriding the validation checking, since we're passing null response objects around.
      }
    };

    fi.readProfile();
  }
}