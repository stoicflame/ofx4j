package net.sf.ofx4j.ser;

import junit.framework.TestCase;
import net.sf.ofx4j.io.OFXWriter;

import java.io.IOException;
import java.util.*;

/**
 * @author Ryan Heaton
 */
public class TestAggregateMarshaller extends TestCase {

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
   * tests the full marshal/unmarshal round-trip...
   */
  public void testMarshalAndUnmarshal() throws Exception {

  }

}
