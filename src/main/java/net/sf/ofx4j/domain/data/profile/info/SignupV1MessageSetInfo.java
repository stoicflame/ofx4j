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

package net.sf.ofx4j.domain.data.profile.info;

import net.sf.ofx4j.domain.data.profile.VersionSpecificMessageSetInfo;
import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.profile.info.signup.ClientEnrollment;
import net.sf.ofx4j.domain.data.profile.info.signup.OtherEnrollment;
import net.sf.ofx4j.domain.data.profile.info.signup.WebEnrollment;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Servers use the Signup Message Set Profile Information to define how enrollment should proceed.
 *
 * This aggregate should contain 1 Enrollment option among <CLIENTENROLL>, <WEBENROLL>, or <OTHERENROLL>.
 * todo: review how best to enforce this constraint
 *
 * @author Scott Priddy
 * @author Ryan Heaton
 * @see "Section 8.8 OFX Spec"
 */
@Aggregate ( "SIGNUPMSGSETV1" )
public class SignupV1MessageSetInfo extends VersionSpecificMessageSetInfo {

  private ClientEnrollment clientEnrollment;
  private WebEnrollment webEnrollment;
  private OtherEnrollment otherEnrollment;
  private Boolean supportsClientUserInfoChanges;
  private Boolean supportsAvailableAccounts;
  private Boolean supportsClientServiceActivationRequests;

  public MessageSetType getMessageSetType() {
    return MessageSetType.signup;
  }

  @ChildAggregate( name = "CLIENTENROLL", order = 10 )
  public ClientEnrollment getClientEnrollment() {
    return clientEnrollment;
  }

  public void setClientEnrollment(ClientEnrollment clientEnrollment) {
    this.clientEnrollment = clientEnrollment;
  }

  @ChildAggregate( name = "WEBENROLL", order = 20 )
  public WebEnrollment getWebEnrollment() {
    return webEnrollment;
  }

  public void setWebEnrollment(WebEnrollment webEnrollment) {
    this.webEnrollment = webEnrollment;
  }

  @ChildAggregate( name = "OTHERENROLL", order = 30 )
  public OtherEnrollment getOtherEnrollment() {
    return otherEnrollment;
  }

  public void setOtherEnrollment(OtherEnrollment otherEnrollment) {
    this.otherEnrollment = otherEnrollment;
  }

  /**
   * Y if server supports client-based user information changes,
   * @return Boolean
   */
  @Element( name = "CHGUSERINFO", required = true, order = 40)
  public Boolean getSupportsClientUserInfoChanges() {
    return supportsClientUserInfoChanges;
  }

  public void setSupportsClientUserInfoChanges(Boolean supportsClientUserInfoChanges) {
    this.supportsClientUserInfoChanges = supportsClientUserInfoChanges;
  }

  /**
   * Y if server can provide information on accounts with SVCSTATUS available,
   * N means client should expect to ask user for specific account information
   * @return Boolean
   */
  @Element( name = "AVAILACCTS", required = true, order = 50)
  public Boolean getSupportsAvailableAccounts() {
    return supportsAvailableAccounts;
  }

  public void setSupportsAvailableAccounts(Boolean supportsAvailableAccounts) {
    this.supportsAvailableAccounts = supportsAvailableAccounts;
  }

  /**
   * Y if server allows clients to make service activation requests (<ACCTRQ>),
   * N if server will only advise clients via synchronization of service additions,
   * changes, or deletions.
   * @return Boolean
   */
  @Element( name = "CLIENTACTREQ", required = true, order = 60)
  public Boolean getSupportsClientServiceActivationRequests() {
    return supportsClientServiceActivationRequests;
  }

  public void setSupportsClientServiceActivationRequests(Boolean supportsClientServiceActivationRequests) {
    this.supportsClientServiceActivationRequests = supportsClientServiceActivationRequests;
  }



}