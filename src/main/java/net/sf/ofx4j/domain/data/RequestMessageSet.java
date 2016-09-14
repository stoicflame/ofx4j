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

package net.sf.ofx4j.domain.data;

import java.util.List;

/**
 * A message set enclosed in an OFX request envelope.
 *
 * @author Ryan Heaton
 */
public abstract class RequestMessageSet implements Comparable<RequestMessageSet> {

  private String version = "1";

  public abstract MessageSetType getType();

  /**
   * The version of this request message.
   *
   * @return The version of this request message.
   */
  public String getVersion() {
    return version;
  }

  /**
   * The version of this request message.
   *
   * @param version The version of this request message.
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * The request messages for this request message set.
   *
   * @return The request messages for this request message set.
   */
  public abstract List<RequestMessage> getRequestMessages();

  // Inherited.
  public int compareTo(RequestMessageSet o) {
    return getType().compareTo(o.getType());
  }
}
