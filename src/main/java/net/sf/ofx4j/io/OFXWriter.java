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

import java.io.IOException;
import java.util.Map;

/**
 * @author Ryan Heaton
 */
public interface OFXWriter {

  /**
   * Write the specified headers.
   *
   * @param headers The headers to be written.
   */
  void writeHeaders(Map<String, String> headers) throws IOException;

  /**
   * Write the start of a new aggregate.
   *
   * @param aggregateName The aggregate name.
   */
  void writeStartAggregate(String aggregateName) throws IOException;

  /**
   * Write an element to the current aggregate.
   *
   * @param name The name of the element.
   * @param value The value of the element.
   */
  void writeElement(String name, String value) throws IOException;

  /**
   * Write the end of an aggregate.
   *
   * @param aggregateName The aggregate name.
   * @throws IllegalArgumentException If the specified aggregate hasn't been started.
   */
  void writeEndAggregate(String aggregateName) throws IOException;

  /**
   * Close this OFX writer.
   */
  void close() throws IOException;
}
