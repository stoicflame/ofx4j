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

package net.sf.ofx4j.domain.data;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Header;
import net.sf.ofx4j.domain.data.signon.SignonResponseMessageSet;
import net.sf.ofx4j.domain.data.signon.SignonResponse;

import java.util.SortedSet;

/**
 * Envelope for enclosing an OFX response.
 *
 * @author Ryan Heaton
 * @see "Section 2.4.3, OFX Spec"
 */
@Aggregate ( "OFX" )
public class ResponseEnvelope {

  //headers
  private ApplicationSecurity security;
  private String UID;

  //content
  private SortedSet<ResponseMessageSet> messageSets;

  /**
   * The security of this envelope.
   *
   * @return The security of this envelope.
   * @see "Section 2.2, OFX spec"
   */
  @Header ( name = "SECURITY" )
  public ApplicationSecurity getSecurity() {
    return security;
  }

  /**
   * The security of this envelope.
   *
   * @param security The security of this envelope.
   * @see "Section 2.2, OFX spec"
   */
  public void setSecurity(ApplicationSecurity security) {
    this.security = security;
  }

  /**
   * The UID for the envelope.
   *
   * @return The UID for the envelope.
   * @see "Section 2.2, OFX spec"
   */
  @Header ( name = "NEWFILEUID" )
  public String getUID() {
    return UID;
  }

  /**
   * The UID for the envelope.
   *
   * @param UID The UID for the envelope.
   * @see "Section 2.2, OFX spec"
   */
  public void setUID(String UID) {
    this.UID = UID;
  }

  /**
   * The message sets that make up the content of this response.
   *
   * @return The message sets that make up the content of this response.
   * @see "Section 2.4.5, OFX Spec"
   */
  @ChildAggregate ( order = 1 )
  public SortedSet<ResponseMessageSet> getMessageSets() {
    return messageSets;
  }

  /**
   * The message sets that make up the content of this response.
   *
   * @param messageSets The message sets that make up the content of this response.
   * @see "Section 2.4.5, OFX Spec"
   */
  public void setMessageSets(SortedSet<ResponseMessageSet> messageSets) {
    this.messageSets = messageSets;
  }

  /**
   * Helper method for looking up the signon response.
   *
   * @return The signon response, or null if none found.
   */
  public SignonResponse getSignonResponse() {
    MessageSetType type = MessageSetType.signon;
    ResponseMessageSet message = getMessageSet(type);

    if (message != null) {
      return ((SignonResponseMessageSet) message).getSignonResponse();
    }
    else {
      return null;
    }
  }

  /**
   * Get the message set of the specified type.
   *
   * @param type The type.
   * @return The message set, or null.
   */
  public ResponseMessageSet getMessageSet(MessageSetType type) {
    ResponseMessageSet message = null;
    if (this.messageSets != null) {
      for (ResponseMessageSet messageSet : this.messageSets) {
        if (messageSet.getType() == type) {
          message = messageSet;
          break;
        }
      }
    }
    return message;
  }

}