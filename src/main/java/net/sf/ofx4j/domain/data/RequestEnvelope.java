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

import java.util.SortedSet;
import java.util.UUID;

/**
 * Envelope for enclosing an OFX request.
 *
 * @author Ryan Heaton
 * @see "Section 2.4.3, OFX Spec"
 */
@Aggregate ( "OFX" )
public class RequestEnvelope {

  //headers
  private ApplicationSecurity security = ApplicationSecurity.NONE;
  private String UID;
  private String lastProcessedUID;

  //content
  private SortedSet<RequestMessageSet> messageSets;

  public RequestEnvelope() {
    this.UID = UUID.randomUUID().toString();
  }

  public RequestEnvelope(String UID) {
    this.UID = UID;
  }

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
   * The UID of the last-processed request/response (used for file-based error recovery).
   *
   * @return The UID of the last-processed request/response (used for file-based error recovery).
   * @see "Section 2.2, OFX spec"
   */
  @Header ( name = "OLDFILEUID" )
  public String getLastProcessedUID() {
    return lastProcessedUID;
  }

  /**
   * The UID of the last-processed request/response (used for file-based error recovery).
   *
   * @param lastProcessedUID The UID of the last-processed request/response (used for file-based error recovery).
   * @see "Section 2.2, OFX spec"
   */
  public void setLastProcessedUID(String lastProcessedUID) {
    this.lastProcessedUID = lastProcessedUID;
  }

  /**
   * The message sets that make up the content of this request.
   *
   * @return The message sets that make up the content of this request.
   * @see "Section 2.4.5, OFX Spec"
   */
  @ChildAggregate ( order = 1 )
  public SortedSet<RequestMessageSet> getMessageSets() {
    return messageSets;
  }

  /**
   * The message sets that make up the content of this request.
   *
   * @param messageSets The message sets that make up the content of this request.
   * @see "Section 2.4.5, OFX Spec"
   */
  public void setMessageSets(SortedSet<RequestMessageSet> messageSets) {
    this.messageSets = messageSets;
  }
}
