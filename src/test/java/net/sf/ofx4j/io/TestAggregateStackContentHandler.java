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

/**
 * @author Ryan Heaton
 */
public class TestAggregateStackContentHandler extends TestCase {

  /**
   * tests the construction of an aggregate.
   */
  public void testAggregateLifecycle() throws Exception {
    AggregateIntrospector.AGGREGATE_CLASSES_BY_NAME.put("EXAMPLE4", AggregateExample4.class);
    AggregateIntrospector.AGGREGATE_CLASSES_BY_NAME.put("EXAMPLE3", AggregateExample3.class);
    AggregateIntrospector.AGGREGATE_CLASSES_BY_NAME.put("EXAMPLE2", AggregateExample2.class);
    
    AggregateExample example = new AggregateExample();
    AggregateStackContentHandler handler = new AggregateStackContentHandler<AggregateExample>(example, new DefaultStringConversion());
    handler.onHeader("HEADER1", "Header One Value");
    handler.onHeader("ANOTHERHEADER", "Header Two Value");
    handler.startAggregate("EXAMPLE");
    handler.onElement("SOMEELEMENT", "Element One Value");
    handler.startAggregate("EXAMPLE2");
    handler.onElement("EXAMPLE2EL1", "Child One Element One");
    handler.endAggregate("EXAMPLE2");
    handler.startAggregate("EXAMPLE3");
    handler.onElement("EXAMPLE3EL1", "Child Two Element One");
    handler.endAggregate("EXAMPLE3");
    handler.startAggregate("EXAMPLE4");
    handler.onElement("EXAMPLE4EL1", "Child Three Element One");
    handler.endAggregate("EXAMPLE4");
    handler.startAggregate("DIFFERENT");
    handler.onElement("EXAMPLE2EL1", "Child Four Element One");
    handler.endAggregate("DIFFERENT");
    handler.endAggregate("EXAMPLE");

    assertEquals("Header One Value", example.getHeader1());
    assertEquals("Header Two Value", example.getHeader2());
    assertEquals("Element One Value", example.getElement1());
    assertNotNull(example.getAggregate1());
    assertEquals("Child One Element One", example.getAggregate1().getElement());
    assertNotNull(example.getAggregateList());
    assertEquals(2, example.getAggregateList().size());
    assertEquals("Child Two Element One", ((AggregateExample3) example.getAggregateList().get(0)).getElement());
    assertEquals("Child Three Element One", ((AggregateExample4) example.getAggregateList().get(1)).getElement());
    assertNotNull(example.getAggregate2());
    assertEquals("Child Four Element One", example.getAggregate2().getElement());
  }

}
