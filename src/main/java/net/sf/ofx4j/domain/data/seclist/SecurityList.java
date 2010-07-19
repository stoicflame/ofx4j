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
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;

/**
 * Aggregate for a list of securities.
 * @see "Section 13.8.4, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "SECLIST" )
public class SecurityList {
  private List<BaseSecurityInfo> securityInfos;

  @ChildAggregate( order = 10 )
  public List<BaseSecurityInfo> getSecurityInfos() {
    return securityInfos;
  }

  public void setSecurityInfos(List<BaseSecurityInfo> securityInfos) {
    this.securityInfos = securityInfos;
  }
}
