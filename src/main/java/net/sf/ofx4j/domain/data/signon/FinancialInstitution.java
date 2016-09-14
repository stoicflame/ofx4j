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

package net.sf.ofx4j.domain.data.signon;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "FI" )
public class FinancialInstitution {

  private String id;
  private String organization;

  /**
   * Financial institution id.
   *
   * @return Financial institution id.
   */
  @Element ( name = "FID", order = 10 )
  public String getId() {
    return id;
  }

  /**
   * Financial institution id.
   *
   * @param id Financial institution id.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * The organization.
   *
   * @return The organization.
   */
  @Element ( name = "ORG", required = true, order = 0 )
  public String getOrganization() {
    return organization;
  }

  /**
   * The organization.
   *
   * @param organization The organization.
   */
  public void setOrganization(String organization) {
    this.organization = organization;
  }
}
