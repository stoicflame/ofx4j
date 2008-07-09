package net.sf.ofx4j.services.impl;

import junit.framework.TestCase;
import net.sf.ofx4j.domain.FinancialInstitutionProfile;
import net.sf.ofx4j.domain.RequestEnvelope;
import net.sf.ofx4j.domain.ResponseEnvelope;
import net.sf.ofx4j.io.v1.OFXV1Writer;
import net.sf.ofx4j.net.OFXConnection;
import net.sf.ofx4j.net.OFXConnectionException;
import net.sf.ofx4j.ser.AggregateMarshaller;
import net.sf.ofx4j.services.OFXServiceException;

import java.io.IOException;
import java.net.URL;

/**
 * @author Ryan Heaton
 */
public class TestFinancialInstitutionServiceImpl extends TestCase {

  /**
   * tests reading the profile.
   */
  public void testReadProfile() throws Exception {

    FinancialInstitutionServiceImpl service = new FinancialInstitutionServiceImpl() {
      @Override
      protected FinancialInstitutionProfile getProfile(String requestId, ResponseEnvelope response) throws OFXServiceException {
        return null;
      }
    };
    service.setAppId("TESTAPP");
    service.setAppVersion("0100");

    service.setConnection(new OFXConnection() {
      public ResponseEnvelope sendRequest(RequestEnvelope request, URL url) throws IOException, OFXConnectionException {
        OFXV1Writer writer = new OFXV1Writer(System.out);
        writer.setWriteAttributesOnNewLine(true);
        new AggregateMarshaller().marshal(request, writer);
        writer.close();
        return null; 
      }
    });

    service.readProfile(null);

  }

}
