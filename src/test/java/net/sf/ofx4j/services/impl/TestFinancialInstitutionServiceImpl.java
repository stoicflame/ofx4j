package net.sf.ofx4j.services.impl;

import junit.framework.TestCase;
import net.sf.ofx4j.domain.FinancialInstitutionProfile;
import net.sf.ofx4j.domain.data.RequestEnvelope;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.domain.data.signon.SignonRequest;
import net.sf.ofx4j.io.v1.OFXV1Writer;
import net.sf.ofx4j.net.OFXConnection;
import net.sf.ofx4j.net.OFXConnectionException;
import net.sf.ofx4j.io.AggregateMarshaller;
import net.sf.ofx4j.services.OFXServiceException;

import java.io.IOException;
import java.io.FileOutputStream;
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

      @Override
      protected SignonRequest createProfileSignonRequest() {
        SignonRequest signonRequest = super.createProfileSignonRequest();
        signonRequest.getFinancialInstitution().setOrganization("Chase Bank of Texas");
        return signonRequest;
      }
    };

    service.setConnection(new OFXConnection() {
      public ResponseEnvelope sendRequest(RequestEnvelope request, URL url) throws IOException, OFXConnectionException {
        OFXV1Writer writer = new OFXV1Writer(new FileOutputStream("/tmp/profile-request.ofx"));
        writer.setWriteAttributesOnNewLine(true);
        new AggregateMarshaller().marshal(request, writer);
        writer.close();
        return null; 
      }
    });

    service.readProfile(null);

  }

}
