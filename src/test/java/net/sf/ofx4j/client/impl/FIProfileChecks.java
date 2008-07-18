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
