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

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.RequestMessageSet;
import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

import java.util.List;
import java.util.ArrayList;

/**
 * The sign-on request message set.
 *
 * @author Ryan Heaton
 * @see "Section 2.5, OFX Spec."
 */
@Aggregate ("SIGNONMSGSRQV1")
public class SignonRequestMessageSet extends RequestMessageSet {

  private SignonRequest signonRequest;
  private PasswordChangeRequestTransaction passwordChangeRequest;

  public MessageSetType getType() {
    return MessageSetType.signon;
  }

  /**
   * The message for this message set.
   *
   * @return The message for this message set.
   */
  @ChildAggregate ( required = true, order = 0 )
  public SignonRequest getSignonRequest() {
    return signonRequest;
  }

  /**
   * The message for this message set.
   *
   * @param signonRequest The message for this message set.
   */
  public void setSignonRequest(SignonRequest signonRequest) {
    this.signonRequest = signonRequest;
  }

  /**
   * The password change request.
   *
   * @return The password change request.
   */
  @ChildAggregate ( order = 10 )
  public PasswordChangeRequestTransaction getPasswordChangeRequest() {
    return passwordChangeRequest;
  }

  /**
   * The password change request.
   *
   * @param passwordChangeRequest The password change request.
   */
  public void setPasswordChangeRequest(PasswordChangeRequestTransaction passwordChangeRequest) {
    this.passwordChangeRequest = passwordChangeRequest;
  }

  //todo: challenge request/response


  // Inherited.
  public List<RequestMessage> getRequestMessages() {
    ArrayList<RequestMessage> requestMessages = new ArrayList<RequestMessage>();

    if (getSignonRequest() != null) {
      requestMessages.add(getSignonRequest());
    }

    if (getPasswordChangeRequest() != null) {
      requestMessages.add(getPasswordChangeRequest());
    }

    return requestMessages;
  }
}
