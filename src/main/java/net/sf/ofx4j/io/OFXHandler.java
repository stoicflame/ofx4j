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
 * Handler for events during OFX parsing.
 *
 * @author Ryan Heaton
 */
public interface OFXHandler {

  /**
   * Handler an OFX header.
   *
   * @param name The name of the header.
   * @param value The value of the header.
   */
  void onHeader(String name, String value) throws OFXSyntaxException;

  /**
   * Handle a new OFX element.
   *
   * @param name The name of the element.
   * @param value The value of the element.
   */
  void onElement(String name, String value) throws OFXSyntaxException;

  /**
   * Handle the start of a new OFX aggregate.
   *
   * @param aggregateName The name of the aggregate.
   */
  void startAggregate(String aggregateName) throws OFXSyntaxException;

  /**
   * Handle the end of an OFX aggregate.
   *
   * @param aggregateName The aggregate name.
   */
  void endAggregate(String aggregateName) throws OFXSyntaxException;

}
