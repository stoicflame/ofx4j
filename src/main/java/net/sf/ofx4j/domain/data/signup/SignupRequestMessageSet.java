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

import net.sf.ofx4j.domain.data.RequestMessageSet;
import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Ryan Heaton
 */
@Aggregate ("SIGNUPMSGSRQV1")
public class SignupRequestMessageSet extends RequestMessageSet {

  private AccountInfoRequestTransaction accountInfoRequest;

  public MessageSetType getType() {
    return MessageSetType.signup;
  }

  /**
   * The account info request.
   *
   * @return The account info request.
   */
  @ChildAggregate ( order = 0 )
  public AccountInfoRequestTransaction getAccountInfoRequest() {
    return accountInfoRequest;
  }

  /**
   * The account info request.
   *
   * @param accountInfoRequest The account info request.
   */
  public void setAccountInfoRequest(AccountInfoRequestTransaction accountInfoRequest) {
    this.accountInfoRequest = accountInfoRequest;
  }

  /**
   * The request messages.
   *
   * @return The request messages.
   */
  public List<RequestMessage> getRequestMessages() {
    ArrayList<RequestMessage> messages = new ArrayList<RequestMessage>();

    if (getAccountInfoRequest() != null) {
      messages.add(getAccountInfoRequest());
    }
    
    return messages;
  }
}
