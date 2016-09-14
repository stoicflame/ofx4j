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
import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.domain.data.RequestMessageSet;
import net.sf.ofx4j.domain.data.investment.statements.InvestmentStatementRequestTransaction;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.ArrayList;
import java.util.List;

/**
 * Security list request message set.
 * @see "Section 13.7.2.2.1, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "SECLISTMSGSRQV1" )
public class SecurityListRequestMessageSet extends RequestMessageSet {

  private SecurityListRequestTransaction securityListRequest;

  public MessageSetType getType() {
    return MessageSetType.investment;
  }

  /**
   * Gets the security list request.
   *
   * @return the request
   */
  @ChildAggregate(order = 0)
  public SecurityListRequestTransaction getSecurityListRequest() {
    return securityListRequest;
  }

  /**
   * Sets the security list request.
   *
   * @param statementRequest the request
   */
  public void setSecurityListRequest(SecurityListRequestTransaction statementRequest) {
    this.securityListRequest = statementRequest;
  }

  // Inherited.
  public List<RequestMessage> getRequestMessages() {
    ArrayList<RequestMessage> requestMessages = new ArrayList<RequestMessage>();
    if (getSecurityListRequest() != null) {
      requestMessages.add(getSecurityListRequest());
    }
    return requestMessages;
  }
}
