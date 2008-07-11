package net.sf.ofx4j.io;

import junit.framework.TestCase;
import net.sf.ofx4j.domain.data.ResponseEnvelope;

/**
 * @author Ryan Heaton
 */
public class TestUnmarshalling extends TestCase {

  public void testKnownData() throws Exception {
    AggregateUnmarshaller<ResponseEnvelope> unmarshaller = new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class);
    try {
      unmarshaller.unmarshal(TestUnmarshalling.class.getResourceAsStream("mercantile-brokerage-services-profile.ofx"));
      fail("Shouldn't have been able to parse a document with an empty element value.");
    }
    catch (OFXParseException e) {
      //fall through...
    }

    try {
      unmarshaller.unmarshal(TestUnmarshalling.class.getResourceAsStream("wells-fargo-investments-profile.ofx"));
      fail("Shouldn't have been able to parse a document with an empty element value.");
    }
    catch (OFXParseException e) {
      //fall through...
    }

    try {
      unmarshaller.unmarshal(TestUnmarshalling.class.getResourceAsStream("fremont-bank-profile.ofx"));
      fail("Shouldn't have been able to parse a document with an empty element value.");
    }
    catch (OFXParseException e) {
      //fall through...
    }

  }

}
