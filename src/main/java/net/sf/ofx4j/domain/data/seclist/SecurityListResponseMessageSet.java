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

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseMessage;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jon Perlow
 */
@Aggregate( "SECLISTMSGSRSV1" )
public class SecurityListResponseMessageSet extends ResponseMessageSet {

  private SecurityListResponseTransaction securityListResponse;
  private SecurityList securityList;

  public MessageSetType getType() {
    return MessageSetType.investment_security;
  }

  /**
   * The security list response list transaction.
   *
   * Most OFX files have a single security response.
   *
   * @return The security list response list.
   */
  @ChildAggregate( order = 0 )
  public SecurityListResponseTransaction getSecurityListResponse() {
    return securityListResponse;
  }

  /**
   * The security list response.
   *
   * @param securityListResponse The security list response.
   */
  public void setSecurityListResponse(SecurityListResponseTransaction securityListResponse) {
    this.securityListResponse = securityListResponse;
  }

  @ChildAggregate( order = 10 )
  public SecurityList getSecurityList() {
    return securityList;
  }

  public void setSecurityList(SecurityList securityList) {
    this.securityList = securityList;
  }

  // Inherited.
  public List<ResponseMessage> getResponseMessages() {
    ArrayList<ResponseMessage> ret = new ArrayList<ResponseMessage>();
    ret.add(securityListResponse);
    return ret;
  }
}
