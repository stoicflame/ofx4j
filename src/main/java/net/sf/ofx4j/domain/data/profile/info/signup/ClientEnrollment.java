/*
 * Copyright 2012 TheStash
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

package net.sf.ofx4j.domain.data.profile.info.signup;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Client Enrollment option, contains indicator as to whether the account number is required as part of enrollment
 * @author Scott Priddy
 * @see "Section 8.8 OFX Spec"
 */
@Aggregate( "CLIENTENROLL" )
public class ClientEnrollment {

  private Boolean accountRequired;

  /**
   * Y if account number is required as part of enrollment
   * @return Boolean
   */
  @Element( name = "ACCTREQUIRED", required = true, order = 0)
  public Boolean getAccountRequired() {
    return accountRequired;
  }

  public void setAccountRequired(Boolean accountRequired) {
    this.accountRequired = accountRequired;
  }

}
