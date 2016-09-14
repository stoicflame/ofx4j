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

import java.util.Map;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;

import net.sf.ofx4j.io.AggregateAttribute;
import net.sf.ofx4j.io.AggregateInfo;
import net.sf.ofx4j.io.AggregateIntrospector;

/**
 * @author Ryan Heaton
 */
public class TestAggregateMetadata extends TestCase {

  /**
   * test aggregate info.
   */
  public void testAggregateInfo() throws Exception {
    assertNull(AggregateIntrospector.getAggregateInfo(TestAggregateMetadata.class));
    AggregateInfo info = AggregateIntrospector.getAggregateInfo(AggregateExample.class);
    assertNotNull(info);
    assertEquals("EXAMPLE", info.getName());
    AggregateExample example = new AggregateExample();
    example.setHeader1("3");
    example.setHeader2("hvalue2");
    example.setElement1("evalue1");
    AggregateExample2 child1 = new AggregateExample2();
    example.setAggregate1(child1);
    AggregateExample2 child2 = new AggregateExample2();
    example.setAggregate2(child2);
    AggregateExample2 child3 = new AggregateExample2();
    AggregateExample2 child4 = new AggregateExample2();
    example.setAggregateList(Arrays.asList(child3, child4));

    Map<String,Object> headers = info.getHeaders(example);
    assertEquals(2, headers.size());
    assertEquals("3", headers.get("HEADER1"));
    assertEquals("hvalue2", headers.get("ANOTHERHEADER"));
    assertEquals(4, info.getAttributes().size());
    Iterator<AggregateAttribute> it = info.getAttributes().iterator();

    AggregateAttribute elementInfo = it.next();
    assertEquals("SOMEELEMENT", elementInfo.getName());
    assertEquals(AggregateAttribute.Type.ELEMENT, elementInfo.getType());
    assertEquals(String.class, elementInfo.getAttributeType());
    assertEquals("evalue1", elementInfo.get(example));
    elementInfo.set("evalue2", example);
    assertEquals("evalue2", example.getElement1());

    AggregateAttribute childAggregate1 = it.next();
    assertEquals("EXAMPLE2", childAggregate1.getName());
    assertEquals(AggregateAttribute.Type.CHILD_AGGREGATE, childAggregate1.getType());
    assertEquals(AggregateExample2.class, childAggregate1.getAttributeType());
    assertSame(child1, childAggregate1.get(example));
    childAggregate1.set(child3, example);
    assertSame(child3, example.getAggregate1());

    AggregateAttribute childAggregate2 = it.next();
    assertEquals(null, childAggregate2.getName());
    assertEquals(AggregateAttribute.Type.CHILD_AGGREGATE, childAggregate2.getType());
    assertEquals(List.class, childAggregate2.getAttributeType());
    List list = (List) childAggregate2.get(example);
    assertEquals(2, list.size());
    assertSame(child3, list.get(0));
    assertSame(child4, list.get(1));
    example.setAggregateList(null);//clear out the list for a new one.
    childAggregate2.set(child1, example);
    childAggregate2.set(child2, example);
    list = (List) childAggregate2.get(example);
    assertEquals(2, list.size());
    assertSame(child1, list.get(0));
    assertSame(child2, list.get(1));

    AggregateAttribute childAggregate3 = it.next();
    assertEquals("DIFFERENT", childAggregate3.getName());
    assertEquals(AggregateAttribute.Type.CHILD_AGGREGATE, childAggregate3.getType());
    assertEquals(AggregateExample2.class, childAggregate3.getAttributeType());
    assertSame(child2, childAggregate3.get(example));
    childAggregate3.set(child4, example);
    assertSame(child4, example.getAggregate2());

    assertFalse(it.hasNext());

  }

}
