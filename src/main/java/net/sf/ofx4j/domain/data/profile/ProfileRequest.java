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

package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * @author Ryan Heaton
 * @see "Section 7.1.5, OFX Spec"
 */
@Aggregate ( "PROFRQ" )
public class ProfileRequest extends RequestMessage {

  private ClientRoutingCapability routingCapability = ClientRoutingCapability.NONE;
  private Date profileLastUpdated;

  /**
   * The client routing capability.
   *
   * @return The client routing capability.
   */
  @Element ( name = "CLIENTROUTING", order = 0 )
  public ClientRoutingCapability getRoutingCapability() {
    return routingCapability;
  }

  /**
   * The client routing capability.
   *
   * @param routingCapability The client routing capability.
   */
  public void setRoutingCapability(ClientRoutingCapability routingCapability) {
    this.routingCapability = routingCapability;
  }

  /**
   * The date the profile was last updated.
   *
   * @return The date the profile was last updated.
   */
  @Element ( name = "DTPROFUP", order = 10 )
  public Date getProfileLastUpdated() {
    return profileLastUpdated;
  }

  /**
   * The date the profile was last updated.
   *
   * @param profileLastUpdated The date the profile was last updated.
   */
  public void setProfileLastUpdated(Date profileLastUpdated) {
    this.profileLastUpdated = profileLastUpdated;
  }
}
