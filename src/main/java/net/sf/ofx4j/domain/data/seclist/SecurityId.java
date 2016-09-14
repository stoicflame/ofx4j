/*
 * Copyright 2010 Web Cohesion
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

package net.sf.ofx4j.domain.data.seclist;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Identifier for a security.
 * @see "Section 13.8.1, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "SECID" )
public class SecurityId {

  private String uniqueId;
  private String uniqueIdType;

  /**
   * Gets the unique id for the security. This is a required field according to the OFX spec.
   *
   * @return the unique id
   */
  @Element( name = "UNIQUEID", required = true, order = 10)
  public String getUniqueId() {
    return uniqueId;
  }

  /**
   * Sets the unique id for the security. This is a required field according to the OFX spec.
   *
   * @param uniqueId the unique id
   */
  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }

  /**
   * Gets the type of unique id.
   *
   * @return the type of unique id
   */
  @Element( name = "UNIQUEIDTYPE", required = true, order = 20)
  public String getUniqueIdType() {
    return uniqueIdType;
  }

  /**
   * Sets the type of unique id.
   *
   * @param uniqueIdType the type of unique id
   */
  public void setUniqueIdType(String uniqueIdType) {
    this.uniqueIdType = uniqueIdType;
  }
}
