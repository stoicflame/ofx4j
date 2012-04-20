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

import net.sf.ofx4j.domain.data.banking.AccountType;
import net.sf.ofx4j.domain.data.profile.VersionSpecificMessageSetInfo;
import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.profile.info.banking.EmailProfile;
import net.sf.ofx4j.domain.data.profile.info.common.ImageProfile;
import net.sf.ofx4j.domain.data.profile.info.banking.StopCheckProfile;
import net.sf.ofx4j.domain.data.profile.info.common.TransferProfile;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.util.List;

/**
 * Banking Message Set Profile
 * @author Scott Priddy
 * @author Ryan Heaton
 * @see "Section 11.13.2.1 OFX Spec"
 */
@Aggregate ( "BANKMSGSETV1" )
public class BankingV1MessageSetInfo extends VersionSpecificMessageSetInfo {

  private List<AccountType> invalidAccountTypes;
  private Boolean closingAvail;
  private TransferProfile transferProfile;
  private StopCheckProfile stopCheckProfile;
  private EmailProfile emailProfile;
  private ImageProfile imageProfile;


  public MessageSetType getMessageSetType() {
    return MessageSetType.banking;
  }

  /**
   * The invalidAccountTypes list.
   *
   * @return The invalidAccountTypes list.
   */
  @ChildAggregate ( order = 10 )
  public List<AccountType> getInvalidAccountTypes() {
    return invalidAccountTypes;
  }

  /**
   * The invalidAccountTypes list.
   *
   * @param invalidAccountTypes The invalidAccountTypes list.
   */
  public void setInvalidAccountTypes(List<AccountType> invalidAccountTypes) {
    this.invalidAccountTypes = invalidAccountTypes;
  }

  /**
   * Gets whether closing statement information is available
   *
   * @return whether closing statement information is available
   */
  @Element( name = "CLOSINGAVAIL", required = true, order = 20)
  public Boolean getClosingAvail() {
    return closingAvail;
  }

  /**
   * Sets whether closing statement information is available
   *
   * @param closingAvail whether closing statement information is available
   */
  public void setClosingAvail(Boolean closingAvail) {
    this.closingAvail = closingAvail;
  }

  @ChildAggregate ( name = "XFERPROF", order = 30 )
  public TransferProfile getTransferProfile() {
    return transferProfile;
  }

  public void setTransferProfile(TransferProfile transferProfile) {
    this.transferProfile = transferProfile;
  }

  @ChildAggregate ( name = "STPCKPROF", order = 40 )
  public StopCheckProfile getStopCheckProfile() {
    return stopCheckProfile;
  }

  public void setStopCheckProfile(StopCheckProfile stopCheckProfile) {
    this.stopCheckProfile = stopCheckProfile;
  }

  @ChildAggregate ( name = "EMAILPROF", required = true, order = 50 )
  public EmailProfile getEmailProfile() {
    return emailProfile;
  }

  public void setEmailProfile(EmailProfile emailProfile) {
    this.emailProfile = emailProfile;
  }

  @ChildAggregate ( name = "IMAGEPROF", order = 60 )
  public ImageProfile getImageProfile() {
    return imageProfile;
  }

  public void setImageProfile(ImageProfile imageProfile) {
    this.imageProfile = imageProfile;
  }


}