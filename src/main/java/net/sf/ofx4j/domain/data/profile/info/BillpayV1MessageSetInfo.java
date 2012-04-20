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

import net.sf.ofx4j.domain.data.common.ProcessorDayOff;
import net.sf.ofx4j.domain.data.profile.VersionSpecificMessageSetInfo;
import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.List;

/**
 * BillPay Message Set Profile
 * @author Scott Priddy
 * @author Ryan Heaton
 * @see "Section 12.11.2 OFX Spec"
 */
@Aggregate ( "BILLPAYMSGSETV1" )
public class BillpayV1MessageSetInfo extends VersionSpecificMessageSetInfo {

  private Integer daysWith;
  private Integer defaultDaysToPay;
  private Integer transferDaysWith;
  private Integer transferDefaultDaysToPay;
  private List<ProcessorDayOff> processorDaysOff;
  private String processorEndTime;
  private Integer modelWindow;
  private Integer postProcessorWindow;
  private Boolean supportsStatusUpdateViaPaymentModificationResponse;
  private Boolean supportsPaymentByAddress;
  private Boolean supportsPaymentByTransfer;
  private Boolean supportsPaymentByPayeeId;
  private Boolean userCanAddPayee;
  private Boolean supportsExtendedPayment;
  private Boolean canModifyPayments;
  private Boolean canModifyModels;
  private Boolean supportsDifferentFirstPayment;
  private Boolean supportsDifferentLastPayment;
  private Boolean supportsBillPresentmentContext;

  public MessageSetType getMessageSetType() {
    return MessageSetType.payments;
  }

  @Element( name = "DAYSWITH", required = true, order = 10 )
  public Integer getDaysWith() {
    return daysWith;
  }

  public void setDaysWith(Integer daysWith) {
    this.daysWith = daysWith;
  }

  @Element( name = "DFLTDAYSTOPAY", required = true, order = 20 )
  public Integer getDefaultDaysToPay() {
    return defaultDaysToPay;
  }

  public void setDefaultDaysToPay(Integer defaultDaysToPay) {
    this.defaultDaysToPay = defaultDaysToPay;
  }

  @Element( name = "XFERDAYSWITH", required = true, order = 30 )
  public Integer getTransferDaysWith() {
    return transferDaysWith;
  }

  public void setTransferDaysWith(Integer transferDaysWith) {
    this.transferDaysWith = transferDaysWith;
  }

  @Element( name = "XFERDFLTDAYSTOPAY", required = true, order = 40 )
  public Integer getTransferDefaultDaysToPay() {
    return transferDefaultDaysToPay;
  }

  public void setTransferDefaultDaysToPay(Integer transferDefaultDaysToPay) {
    this.transferDefaultDaysToPay = transferDefaultDaysToPay;
  }

  @Element( name = "PROCDAYSOFF", order = 50 )
  public List<ProcessorDayOff> getProcessorDaysOff() {
    return processorDaysOff;
  }

  public void setProcessorDaysOff(List<ProcessorDayOff> processorDaysOff) {
    this.processorDaysOff = processorDaysOff;
  }

  @Element( name = "PROCENDTM", required = true, order = 60 )
  public String getProcessorEndTime() {
    return processorEndTime;
  }

  public void setProcessorEndTime(String processorEndTime) {
    this.processorEndTime = processorEndTime;
  }

  @Element( name = "MODELWND", required = true, order = 70 )
  public Integer getModelWindow() {
    return modelWindow;
  }

  public void setModelWindow(Integer modelWindow) {
    this.modelWindow = modelWindow;
  }

  @Element( name = "POSTPROCWND", required = true, order = 80 )
  public Integer getPostProcessorWindow() {
    return postProcessorWindow;
  }

  public void setPostProcessorWindow(Integer postProcessorWindow) {
    this.postProcessorWindow = postProcessorWindow;
  }

  @Element( name = "STSVIAMODS", required = true, order = 90 )
  public Boolean getSupportsStatusUpdateViaPaymentModificationResponse() {
    return supportsStatusUpdateViaPaymentModificationResponse;
  }

  public void setSupportsStatusUpdateViaPaymentModificationResponse(Boolean supportsStatusUpdateViaPaymentModificationResponse) {
    this.supportsStatusUpdateViaPaymentModificationResponse = supportsStatusUpdateViaPaymentModificationResponse;
  }

  @Element( name = "PMTBYADDR", required = true, order = 100 )
  public Boolean getSupportsPaymentByAddress() {
    return supportsPaymentByAddress;
  }

  public void setSupportsPaymentByAddress(Boolean supportsPaymentByAddress) {
    this.supportsPaymentByAddress = supportsPaymentByAddress;
  }

  @Element( name = "PMTBYXFER", required = true, order = 110 )
  public Boolean getSupportsPaymentByTransfer() {
    return supportsPaymentByTransfer;
  }

  public void setSupportsPaymentByTransfer(Boolean supportsPaymentByTransfer) {
    this.supportsPaymentByTransfer = supportsPaymentByTransfer;
  }

  @Element( name = "PMTBYPAYEEID", required = true, order = 120 )
  public Boolean getSupportsPaymentByPayeeId() {
    return supportsPaymentByPayeeId;
  }

  public void setSupportsPaymentByPayeeId(Boolean supportsPaymentByPayeeId) {
    this.supportsPaymentByPayeeId = supportsPaymentByPayeeId;
  }

  @Element( name = "CANADDPAYEE", required = true, order = 130 )
  public Boolean getUserCanAddPayee() {
    return userCanAddPayee;
  }

  public void setUserCanAddPayee(Boolean userCanAddPayee) {
    this.userCanAddPayee = userCanAddPayee;
  }

  @Element( name = "HASEXTDPMT", required = true, order = 140 )
  public Boolean getSupportsExtendedPayment() {
    return supportsExtendedPayment;
  }

  public void setSupportsExtendedPayment(Boolean supportsExtendedPayment) {
    this.supportsExtendedPayment = supportsExtendedPayment;
  }

  @Element( name = "CANMODPMTS", required = true, order = 150 )
  public Boolean getCanModifyPayments() {
    return canModifyPayments;
  }

  public void setCanModifyPayments(Boolean canModifyPayments) {
    this.canModifyPayments = canModifyPayments;
  }

  @Element( name = "CANMODMDLS", required = true, order = 160 )
  public Boolean getCanModifyModels() {
    return canModifyModels;
  }

  public void setCanModifyModels(Boolean canModifyModels) {
    this.canModifyModels = canModifyModels;
  }

  @Element( name = "DIFFFIRSTPMT", required = true, order = 170 )
  public Boolean getSupportsDifferentFirstPayment() {
    return supportsDifferentFirstPayment;
  }

  public void setSupportsDifferentFirstPayment(Boolean supportsDifferentFirstPayment) {
    this.supportsDifferentFirstPayment = supportsDifferentFirstPayment;
  }

  @Element( name = "DIFFLASTPMT", required = true, order = 180 )
  public Boolean getSupportsDifferentLastPayment() {
    return supportsDifferentLastPayment;
  }

  public void setSupportsDifferentLastPayment(Boolean supportsDifferentLastPayment) {
    this.supportsDifferentLastPayment = supportsDifferentLastPayment;
  }

  @Element( name = "BILLPUBCONTEXT", order = 190 )
  public Boolean getSupportsBillPresentmentContext() {
    return supportsBillPresentmentContext;
  }

  public void setSupportsBillPresentmentContext(Boolean supportsBillPresentmentContext) {
    this.supportsBillPresentmentContext = supportsBillPresentmentContext;
  }


}