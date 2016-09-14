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
import net.sf.ofx4j.domain.data.profile.info.common.ImageProfile;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Credit Card Message Set Profile
 * @author Scott Priddy
 * @author Ryan Heaton
 * @see "Section 11.13.3 OFX Spec"
 */
@Aggregate ( "CREDITCARDMSGSETV1" )
public class CreditCardV1MessageSetInfo extends VersionSpecificMessageSetInfo {

  private Boolean closingAvail;
  private ImageProfile imageProfile;

  public MessageSetType getMessageSetType() {
    return MessageSetType.creditcard;
  }

  /**
   * Closing statement information available
   * @return Boolean
   */
  @Element( name = "CLOSINGAVAIL", required = true, order = 20)
  public Boolean getClosingAvail() {
    return closingAvail;
  }

  public void setClosingAvail(Boolean closingAvail) {
    this.closingAvail = closingAvail;
  }

  /**
   * Image profile (if supported)
   * @return ImageProfile
   */
  @ChildAggregate( name = "IMAGEPROF", order = 10 )
  public ImageProfile getImageProfile() {
    return imageProfile;
  }

  public void setImageProfile(ImageProfile imageProfile) {
    this.imageProfile = imageProfile;
  }
}