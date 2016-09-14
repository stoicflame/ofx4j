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

package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.RequestMessageSet;
import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Ryan Heaton
 * @see "Section 7 OFX Spec"
 */
@Aggregate ( "PROFMSGSRQV1" )
public class ProfileRequestMessageSet extends RequestMessageSet {

  private ProfileRequestTransaction profileRequest;

  public MessageSetType getType() {
    return MessageSetType.profile;
  }

  /**
   * The profile request.
   *
   * @return The profile request.
   */
  @ChildAggregate ( required = true, order = 0 )
  public ProfileRequestTransaction getProfileRequest() {
    return profileRequest;
  }

  /**
   * The profile request.
   *
   * @param profileRequest The profile request.
   */
  public void setProfileRequest(ProfileRequestTransaction profileRequest) {
    this.profileRequest = profileRequest;
  }


  // Inherited.
  public List<RequestMessage> getRequestMessages() {
    ArrayList<RequestMessage> requestMessages = new ArrayList<RequestMessage>();
    if (getProfileRequest() != null) {
      requestMessages.add(getProfileRequest());
    }
    return requestMessages;
  }
}
