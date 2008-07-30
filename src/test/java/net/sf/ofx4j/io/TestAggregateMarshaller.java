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
import net.sf.ofx4j.io.tagsoup.TagSoupOFXReader;
import net.sf.ofx4j.io.v1.OFXV1Writer;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.domain.data.signon.SignonResponse;

import java.io.*;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ryan Heaton
 */
public class TestAggregateMarshaller extends TestCase {

  private static final Log LOG = LogFactory.getLog(TestAggregateMarshaller.class);

  /**
   * test marshal
   */
  public void testMarshal() throws Exception {
    final Map<String, String> elementValuesWritten = new TreeMap<String, String>();
    final Map<String, String> headersWritten = new TreeMap<String, String>();
    final List<String> aggregatesStarted = new ArrayList<String>();
    final List<String> aggregatesEnded = new ArrayList<String>();

    AggregateExample example = new AggregateExample();
    example.setHeader1("header1");
    example.setHeader2("header2");
    example.setElement1("root-element1");
    AggregateExample2 child1 = new AggregateExample2();
    child1.setElement("child1-element1");
    example.setAggregate1(child1);
    AggregateExample2 child2 = new AggregateExample2();
    child2.setElement("child2-element1");
    example.setAggregate2(child2);
    AggregateExample2 child3 = new AggregateExample2();
    child3.setElement("child3-element1");
    AggregateExample3 child4 = new AggregateExample3();
    child4.setElement("child4-element1");
    example.setAggregateList(Arrays.asList(child3, child4));
    new AggregateMarshaller().marshal(example, new OFXWriter() {
      public void writeHeaders(Map<String, String> headers) throws IOException {
        headersWritten.putAll(headers);
      }

      public void writeStartAggregate(String aggregateName) throws IOException {
        aggregatesStarted.add(aggregateName);
      }

      public void writeElement(String name, String value) throws IOException {
        elementValuesWritten.put(value, name);
      }

      public void writeEndAggregate(String aggregateName) throws IOException {
        aggregatesEnded.add(aggregateName);
      }

      public void close() throws IOException {
        //we'll clear these to make sure the tests fail if close() was called.
        headersWritten.clear();
        aggregatesEnded.clear();
        aggregatesStarted.clear();
        elementValuesWritten.clear();
      }
    });

    assertEquals(2, headersWritten.size());
    assertEquals("header1", headersWritten.get("HEADER1"));
    assertEquals("header2", headersWritten.get("ANOTHERHEADER"));
    assertEquals(5, elementValuesWritten.size());
    assertEquals("SOMEELEMENT", elementValuesWritten.get("root-element1"));
    assertEquals("EXAMPLE2EL1", elementValuesWritten.get("child1-element1"));
    assertEquals("EXAMPLE2EL1", elementValuesWritten.get("child2-element1"));
    assertEquals("EXAMPLE2EL1", elementValuesWritten.get("child3-element1"));
    assertEquals("EXAMPLE3EL1", elementValuesWritten.get("child4-element1"));
    assertEquals(5, aggregatesStarted.size());
    Iterator<String> it = aggregatesStarted.iterator();
    assertEquals("EXAMPLE", it.next());
    assertEquals("EXAMPLE2", it.next());
    assertEquals("EXAMPLE2", it.next());
    assertEquals("EXAMPLE3", it.next());
    assertEquals("DIFFERENT", it.next());
    assertEquals(5, aggregatesEnded.size());
    it = aggregatesEnded.iterator();
    assertEquals("EXAMPLE2", it.next());
    assertEquals("EXAMPLE2", it.next());
    assertEquals("EXAMPLE3", it.next());
    assertEquals("DIFFERENT", it.next());
    assertEquals("EXAMPLE", it.next());
  }

  /**
   * tests a simple marshal/unmarshal round-trip...
   */
  public void testSimpleMarshalAndUnmarshal() throws Exception {
    AggregateIntrospector.AGGREGATE_CLASSES_BY_NAME.put("EXAMPLE4", AggregateExample4.class);
    AggregateIntrospector.AGGREGATE_CLASSES_BY_NAME.put("EXAMPLE3", AggregateExample3.class);
    AggregateIntrospector.AGGREGATE_CLASSES_BY_NAME.put("EXAMPLE2", AggregateExample2.class);

    AggregateExample example = new AggregateExample();
    example.setHeader1("header1");
    example.setHeader2("header2");
    example.setElement1("root-element1");
    AggregateExample2 child1 = new AggregateExample2();
    child1.setElement("child1-element1");
    example.setAggregate1(child1);
    AggregateExample2 child2 = new AggregateExample2();
    child2.setElement("child2-element1");
    example.setAggregate2(child2);
    AggregateExample4 child3 = new AggregateExample4();
    child3.setElement("child3-element1");
    AggregateExample3 child4 = new AggregateExample3();
    child4.setElement("child4-element1");
    example.setAggregateList(Arrays.asList(child4, child3));
    StringWriter marshalled = new StringWriter();
    OFXV1Writer writer = new OFXV1Writer(marshalled);
    new AggregateMarshaller().marshal(example, writer);
    writer.close();
    AggregateUnmarshaller<AggregateExample> unmarshaller = new AggregateUnmarshaller<AggregateExample>(AggregateExample.class) {
      @Override
      protected OFXReader newReader() {
        return new TagSoupOFXReader() {
          @Override
          protected char[] getFirstElementStart() {
            return new char[]{'<', 'E', 'X', 'A'};
          }
        };
      }
    };
    String marshalledValue = marshalled.toString();
    LOG.info(marshalledValue);
    example = unmarshaller.unmarshal(new StringReader(marshalledValue));

    assertEquals("OFX writer shouldn't support custom headers.", null, example.getHeader1());
    assertEquals("OFX writer shouldn't support custom headers.", null, example.getHeader2());
    assertEquals("root-element1", example.getElement1());
    assertNotNull(example.getAggregate1());
    assertEquals("child1-element1", example.getAggregate1().getElement());
    assertNotNull(example.getAggregate2());
    assertEquals("child2-element1", example.getAggregate2().getElement());
    assertNotNull(example.getAggregateList());
    assertEquals(2, example.getAggregateList().size());
    assertEquals("child4-element1", ((AggregateExample3) example.getAggregateList().get(0)).getElement());
    assertEquals("child3-element1", ((AggregateExample4) example.getAggregateList().get(1)).getElement());
  }

  /**
   * tests that the aggregate list was generated correctly.
   */
  public void testAggregateListGenerated() throws Exception {
    InputStream aggregateList = AggregateIntrospector.class.getResourceAsStream("/META-INF/ofx4j/ofx-aggregate.list");
    assertNotNull("No aggregate list", aggregateList);
    BufferedReader reader = new BufferedReader(new InputStreamReader(aggregateList));

    assertNotNull("empty aggregate list.", reader.readLine());
    assertNotNull("only one line in aggregate list.", reader.readLine());
    assertNotNull("Looks like the aggregate list wasn't generated correctly.", AggregateIntrospector.AGGREGATE_CLASSES_BY_NAME.values().contains(SignonResponse.class));
  }

}
