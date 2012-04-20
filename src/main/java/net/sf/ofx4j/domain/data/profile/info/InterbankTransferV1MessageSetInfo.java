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
import net.sf.ofx4j.domain.data.profile.info.common.TransferProfile;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Interbank Funds Transfer Message Set Profile
 * @author Scott Priddy
 * @author Ryan Heaton
 * @see "Section 11.13.4 OFX Spec"
 */
@Aggregate ( "INTERXFERMSGSETV1" )
public class InterbankTransferV1MessageSetInfo extends VersionSpecificMessageSetInfo {

  private TransferProfile transferProfile;
  private Boolean supportsBillPay;
  private Integer cancelWindow;
  private Double domesticInterbankTransferFee;
  private Double internationalInterbankTransferFee;

  public MessageSetType getMessageSetType() {
    return MessageSetType.interbank_transfer;
  }

  @ChildAggregate( name = "XFERPROF", required = true, order = 10 )
  public TransferProfile getTransferProfile() {
    return transferProfile;
  }

  public void setTransferProfile(TransferProfile transferProfile) {
    this.transferProfile = transferProfile;
  }

  @Element( name = "CANBILLPAY", required = true, order = 20 )
  public Boolean getSupportsBillPay() {
    return supportsBillPay;
  }

  public void setSupportsBillPay(Boolean supportsBillPay) {
    this.supportsBillPay = supportsBillPay;
  }

  @Element( name = "CANCELWND", required = true, order = 30 )
  public Integer getCancelWindow() {
    return cancelWindow;
  }

  public void setCancelWindow(Integer cancelWindow) {
    this.cancelWindow = cancelWindow;
  }

  @Element( name = "DOMXFERFEE", required = true, order = 40 )
  public Double getDomesticInterbankTransferFee() {
    return domesticInterbankTransferFee;
  }

  public void setDomesticInterbankTransferFee(Double domesticInterbankTransferFee) {
    this.domesticInterbankTransferFee = domesticInterbankTransferFee;
  }

  @Element( name = "INTLXFERFEE", required = true, order = 50 )
  public Double getInternationalInterbankTransferFee() {
    return internationalInterbankTransferFee;
  }

  public void setInternationalInterbankTransferFee(Double internationalInterbankTransferFee) {
    this.internationalInterbankTransferFee = internationalInterbankTransferFee;
  }
}