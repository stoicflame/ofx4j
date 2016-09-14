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

package net.sf.ofx4j.domain.data.signup;

import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * @author Ryan Heaton
 */
@Aggregate ("ACCTINFORQ")
public class AccountInfoRequest extends RequestMessage {

  private Date lastUpdated = new Date(0); //default is never updated.

  /**
   * When the account info was last updated.
   *
   * @return When the account info was last updated.
   */
  @Element( name = "DTACCTUP", required = true, order = 0 )
  public Date getLastUpdated() {
    return lastUpdated;
  }

  /**
   * When the account info was last updated.
   *
   * @param lastUpdated When the account info was last updated.
   */
  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
}
