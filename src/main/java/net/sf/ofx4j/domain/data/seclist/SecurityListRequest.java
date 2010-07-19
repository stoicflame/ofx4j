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

import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;

/**
 * Request aggregate for the security list.
 * @see "Section 13.8.2.2, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate("SECLISTRQ")
public class SecurityListRequest extends RequestMessage {

  private List<SecurityRequest> securityRequests;

  @ChildAggregate( required = true, order = 10 )
  public List<SecurityRequest> getSecurityRequests() {
    return securityRequests;
  }

  public void setSecurityRequests(List<SecurityRequest> securityRequests) {
    this.securityRequests = securityRequests;
  }
}
