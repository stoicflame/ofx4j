package net.sf.ofx4j.client.impl;

import net.sf.ofx4j.OFXException;
import net.sf.ofx4j.client.FinancialInstitutionData;
import net.sf.ofx4j.client.FinancialInstitutionDataStore;
import net.sf.ofx4j.client.FinancialInstitutionProfile;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.io.AggregateUnmarshaller;
import net.sf.ofx4j.io.OFXParseException;
import net.sf.ofx4j.net.OFXV1Connection;

import java.io.*;

/**
 * @author Ryan Heaton
 */
public class FIProfileResponseDump {

  public static void main(final String[] args) throws Exception {
    if (args.length < 2) {
      System.out.println("Usage: FIProfileDump <fid> <outFile>");
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
        FileOutputStream out = new FileOutputStream(args[1]);
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
    };

    fi.readProfile();
  }
}