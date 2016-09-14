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
 * Wire Transfer Message Set Profile
 * @author Scott Priddy
 * @author Ryan Heaton
 * @see "Section 11.13.5 OFX Spec"
 */
@Aggregate ( "WIREXFERMSGSETV1" )
public class WireTransferV1MessageSetInfo extends VersionSpecificMessageSetInfo {

  private List<ProcessorDayOff> processorDaysOff;
  private String processEndTime;
  private Boolean supportsScheduledTransfers;
  private Double domesticWireTransferFee;
  private Double internationalWireTransferFee;

  public MessageSetType getMessageSetType() {
    return MessageSetType.wire_transfer;
  }

  @Element( name = "PROCDAYSOFF", order = 10 )
  public List<ProcessorDayOff> getProcessorDaysOff() {
    return processorDaysOff;
  }

  public void setProcessorDaysOff(List<ProcessorDayOff> processorDaysOff) {
    this.processorDaysOff = processorDaysOff;
  }

  @Element( name = "PROCENDTM", required = true, order = 20 )
  public String getProcessEndTime() {
    return processEndTime;
  }

  public void setProcessEndTime(String processEndTime) {
    this.processEndTime = processEndTime;
  }

  @Element( name = "CANSCHED", required = true, order = 30 )
  public Boolean getSupportsScheduledTransfers() {
    return supportsScheduledTransfers;
  }

  public void setSupportsScheduledTransfers(Boolean supportsScheduledTransfers) {
    this.supportsScheduledTransfers = supportsScheduledTransfers;
  }

  @Element( name = "DOMXFERFEE", required = true, order = 40 )
  public Double getDomesticWireTransferFee() {
    return domesticWireTransferFee;
  }

  public void setDomesticWireTransferFee(Double domesticWireTransferFee) {
    this.domesticWireTransferFee = domesticWireTransferFee;
  }

  @Element( name = "INTLXFERFEE", required = true, order = 50 )
  public Double getInternationalWireTransferFee() {
    return internationalWireTransferFee;
  }

  public void setInternationalWireTransferFee(Double internationalWireTransferFee) {
    this.internationalWireTransferFee = internationalWireTransferFee;
  }
}