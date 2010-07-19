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
 * Security request aggregate.
 * @see "Section 13.8.2.2, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate("SECRQ")
public class SecurityRequest {

  private SecurityId securityId;
  private String tickerSymbol;
  private String fiId;

  @Element( name = "SECID", order = 10)
  public SecurityId getSecurityId() {
    return securityId;
  }

  public void setSecurityId(SecurityId securityId) {
    this.securityId = securityId;
    this.tickerSymbol = null;
    this.fiId = null;
  }

  @Element( name = "TICKER", order = 20)
  public String getTickerSymbol() {
    return tickerSymbol;
  }

  public void setTickerSymbol(String tickerSymbol) {
    this.tickerSymbol = tickerSymbol;
    this.securityId = null;
    this.fiId = null;
  }

  @Element( name = "FIID", order = 30)
  public String getFiId() {
    return fiId;
  }

  public void setFiId(String fiId) {
    this.fiId = fiId;
    this.securityId = null;
    this.tickerSymbol = null;
  }
}
