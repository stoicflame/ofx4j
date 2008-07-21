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

/**
 * Interface for converting to/from OFX strings.
 *
 * @author Ryan Heaton
 */
public interface StringConversion {

  /**
   * Convert the specified object to a string.
   *
   * @param value The value to convert to a string.
   * @return The string.
   */
  String toString(Object value);

  /**
   * Convert the specified value to an object of the specified type.
   *
   * @param clazz The class.
   * @param value The value.
   * @return The converted value.
   * @throws OFXSyntaxException If there was something wrong with the syntax of the string.
   */
  <E> E fromString(Class<E> clazz, String value) throws OFXSyntaxException;
}
