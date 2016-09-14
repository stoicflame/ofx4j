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

import java.util.List;

/**
 * An OFX aggregate is just an aggregate of name-value pairs that identify the elements and element values of the aggregate.
 * The element values can strings or another (sub)aggregate.  The implementation of a
 *
 * @author Ryan Heaton
 */
public interface OFXAggregate {

  /**
   * The name of the OFX aggregate.
   *
   * @return The name of the aggregate.
   */
  String getName();

  /**
   * Whether this aggregate contains the specified element.
   *
   * @param elementName The name of the element.
   * @return Whether this aggregate contains the specified element.
   */
  boolean containsElement(String elementName);

  /**
   * The element names of this aggregate.
   *
   * @return The element names of this aggregate.
   */
  List<String> elementNames();

  /**
   * The value of the element.  This will be either a string or another OFXAggregate.
   *
   * @param elementName The name of the element.
   * @return The value of the specified element.
   */
  Object getElementValue(String elementName);

  

}
