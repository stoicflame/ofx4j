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

import net.sf.ofx4j.domain.data.ResponseMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * Response to a change a user password request.
 *
 * @author Ryan Heaton
 * @see "Section 2.5.2.2, OFX Spec."
 */
@Aggregate ( "PINCHRQ" )
public class PasswordChangeResponse extends ResponseMessage {

  private String userId;
  private Date changeTimestamp;

  /**
   * The id of the user changing password.
   *
   * @return The id of the user changing password.
   */
  @Element ( name = "USERID", required = true, order = 0 )
  public String getUserId() {
    return userId;
  }

  // Inherited.
  public String getResponseMessageName() {
    return "password change";
  }

  /**
   * The id of the user changing password.
   *
   * @param userId The id of the user changing password.
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * The timestamp of the password change.
   *
   * @return The timestamp of the password change.
   */
  @Element ( name = "DTCHANGED", order = 10 )
  public Date getChangeTimestamp() {
    return changeTimestamp;
  }

  /**
   * The timestamp of the password change.
   *
   * @param changeTimestamp The timestamp of the password change.
   */
  public void setChangeTimestamp(Date changeTimestamp) {
    this.changeTimestamp = changeTimestamp;
  }
}