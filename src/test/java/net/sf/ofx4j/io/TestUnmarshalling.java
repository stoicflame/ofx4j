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

package net.sf.ofx4j.io;

import junit.framework.TestCase;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ryan Heaton
 * @author Scott Priddy
 */
public class TestUnmarshalling extends TestCase {

  private static final Log LOG = LogFactory.getLog(TestBaseOFXReader.class);

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

    unmarshaller.unmarshal(TestUnmarshalling.class.getResourceAsStream("fremont-bank-profile.ofx"));
  }

  public void testProfileUnmarshalling() throws Exception {
    AggregateUnmarshaller<ResponseEnvelope> unmarshaller = new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class);
    LOG.debug("parsing Bank of America Profile Response");
    unmarshaller.unmarshal(TestUnmarshalling.class.getResourceAsStream("bank-of-america-profile.ofx"));
  }

}
