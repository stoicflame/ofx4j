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
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Email Message Set Profile Information
 * @author Scott Priddy
 * @author Ryan Heaton
 * @see "Section 9.4.2 OFX Spec"
 */
@Aggregate ( "EMAILMSGSETV1" )
public class EmailV1MessageSetInfo extends VersionSpecificMessageSetInfo {

  private Boolean supportsMail;
  private Boolean supportsMimeType;

  public MessageSetType getMessageSetType() {
    return MessageSetType.email;
  }

  /**
   * Y if server supports <MAILRQ> request.
   * N if server supports only the <MAILSYNCRQ> request.
   * @return Boolean
   */
  @Element( name = "MAILSUP", required = true, order = 10)
  public Boolean getSupportsMail() {
    return supportsMail;
  }

  public void setSupportsMail(Boolean supportsMail) {
    this.supportsMail = supportsMail;
  }

  /**
   * Y if server supports get MIME message
   * @return Boolean
   */
  @Element( name = "GETMIMESUP", required = true, order = 20)
  public Boolean getSupportsMimeType() {
    return supportsMimeType;
  }

  public void setSupportsMimeType(Boolean supportsMimeType) {
    this.supportsMimeType = supportsMimeType;
  }

}