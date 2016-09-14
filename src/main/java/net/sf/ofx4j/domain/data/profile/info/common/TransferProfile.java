/*
 * Copyright 2012 TheStash
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

package net.sf.ofx4j.domain.data.profile.info.common;

import net.sf.ofx4j.domain.data.common.ProcessorDayOff;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;
import java.util.List;

/**
 * Funds Transfer Profile
 * @author Scott Priddy
 * @see "Section 11.13.2.2 OFX Spec"
 */
@Aggregate( "XFERPROF" )
public class TransferProfile {

  private List<ProcessorDayOff> processorDaysOff;
  private String processEndTime;
  private Boolean supportsScheduledTransfers;
  private Boolean supportsRecurringTransfers;
  private Boolean supportsLoanTransfers;
  private Boolean supportsScheduledLoanTransfers;
  private Boolean supportsRecurringLoanTransfers;
  private Boolean supportsTransferModification;
  private Boolean supportsModelModification;
  private Integer modelWindow;
  private Integer withdrawnDays;
  private Integer defaultDaysToPay;


  /**
   * Days of week that no processing occurs: MONDAY, TUESDAY, WEDNESDAY, THURSDAY,
   * FRIDAY, SATURDAY, or SUNDAY. 0 or more <PROCDAYSOFF> can be sent.
   * @return List of days during the week that no processing occurs.
   */
  @Element( name = "PROCDAYSOFF", order = 0 )
  public List<ProcessorDayOff> getProcessorDaysOff() {
    return processorDaysOff;
  }

  public void setProcessorDaysOff(List<ProcessorDayOff> processorDaysOff) {
    this.processorDaysOff = processorDaysOff;
  }

  /**
   * Gets time of day that day's processing ends.
   *
   * Time formatted as "HHMMSS.XXX[gmt offset[:tz name]]",
   * the milliseconds and time zone are still optional, and default to GMT.
   * @see "Section 3.2.8.3 OFX Spec"
   * @return Time String formatted as "HHMMSS.XXX[gmt offset[:tz name]]"
   */
  @Element( name = "PROCENDTM", required = true, order = 10 )
  public String getProcessEndTime() {
    return processEndTime;
  }

  /**
   * Sets the time of day that day's processing ends.
   *
   * Time formatted as "HHMMSS.XXX[gmt offset[:tz name]]",
   * the milliseconds and time zone are still optional, and default to GMT.

   * @see "Section 3.2.8.3 OFX Spec"
   * @param processEndTime formatted as "HHMMSS.XXX[gmt offset[:tz name]]"
   */
  public void setProcessEndTime(String processEndTime) {
    this.processEndTime = processEndTime;
  }

  @Element( name = "CANSCHED", required = true, order = 20 )
  public Boolean getSupportsScheduledTransfers() {
    return supportsScheduledTransfers;
  }

  public void setSupportsScheduledTransfers(Boolean supportsScheduledTransfers) {
    this.supportsScheduledTransfers = supportsScheduledTransfers;
  }

  /**
   * Requires <CANSCHED>
   * @return Boolean whether supports recurring transfers
   */
  @Element( name = "CANRECUR", required = true, order = 30 )
  public Boolean getSupportsRecurringTransfers() {
    return supportsRecurringTransfers;
  }

  public void setSupportsRecurringTransfers(Boolean supportsRecurringTransfers) {
    this.supportsRecurringTransfers = supportsRecurringTransfers;
  }

  /**
   * <CANLOAN>Y must be present for transfers to involve loans
   * @return Boolean whether supports loan transfers
   */
  @Element( name = "CANLOAN", order = 40 )
  public Boolean getSupportsLoanTransfers() {
    return supportsLoanTransfers;
  }

  public void setSupportsLoanTransfers(Boolean supportsLoanTransfers) {
    this.supportsLoanTransfers = supportsLoanTransfers;
  }

  @Element( name = "CANSCHEDLOAN", order = 50 )
  public Boolean getSupportsScheduledLoanTransfers() {
    return supportsScheduledLoanTransfers;
  }

  public void setSupportsScheduledLoanTransfers(Boolean supportsScheduledLoanTransfers) {
    this.supportsScheduledLoanTransfers = supportsScheduledLoanTransfers;
  }

  @Element( name = "CANRECURLOAN", order = 60 )
  public Boolean getSupportsRecurringLoanTransfers() {
    return supportsRecurringLoanTransfers;
  }

  public void setSupportsRecurringLoanTransfers(Boolean supportsRecurringLoanTransfers) {
    this.supportsRecurringLoanTransfers = supportsRecurringLoanTransfers;
  }

  @Element( name = "CANMODXFERS", required = true, order = 70 )
  public Boolean getSupportsTransferModification() {
    return supportsTransferModification;
  }

  public void setSupportsTransferModification(Boolean supportsTransferModification) {
    this.supportsTransferModification = supportsTransferModification;
  }

  @Element( name = "CANMODMDLS", required = true, order = 80 )
  public Boolean getSupportsModelModification() {
    return supportsModelModification;
  }

  public void setSupportsModelModification(Boolean supportsModelModification) {
    this.supportsModelModification = supportsModelModification;
  }

  /**
   * Model window
   * the number of days before a recurring transaction is scheduled to be processed that it is
   * instantiated on the system
   * @return Integer number of days before a recurring transaction is scheduled to be processed that it is instantiated on the system
   */
  @Element( name = "MODELWND", required = true, order = 90 )
  public Integer getModelWindow() {
    return modelWindow;
  }

  public void setModelWindow(Integer modelWindow) {
    this.modelWindow = modelWindow;
  }

  /**
   * Number of days before processing date that funds are withdrawn
   * @return Integer number of days before processing date that funds are withdrawn
   */
  @Element( name = "DAYSWITH", required = true, order = 100 )
  public Integer getWithdrawnDays() {
    return withdrawnDays;
  }

  public void setWithdrawnDays(Integer withdrawnDays) {
    this.withdrawnDays = withdrawnDays;
  }

  /**
   * Default number of days to pay
   * @return Integer Default number of days to pay
   */
  @Element( name = "DFLTDAYSTOPAY", required = true, order = 110 )
  public Integer getDefaultDaysToPay() {
    return defaultDaysToPay;
  }

  public void setDefaultDaysToPay(Integer defaultDaysToPay) {
    this.defaultDaysToPay = defaultDaysToPay;
  }
}
