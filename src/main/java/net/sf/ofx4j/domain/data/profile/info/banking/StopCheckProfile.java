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

package net.sf.ofx4j.domain.data.profile.info.banking;

import net.sf.ofx4j.domain.data.common.ProcessorDayOff;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.List;

/**
 * Stop Check Profile
 * @author Scott Priddy
 * @see "Section 11.13.2.3 OFX Spec"
 */
@Aggregate( "STPCHKPROF" )
public class StopCheckProfile {

  private List<ProcessorDayOff> processorDaysOff;
  private String processEndTime;
  private Boolean canUseRange;
  private Boolean canUseDescription;
  private Double stopCheckFee;

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

  @Element( name = "CANUSERANGE", required = true, order = 20 )
  public Boolean getCanUseRange() {
    return canUseRange;
  }

  public void setCanUseRange(Boolean canUseRange) {
    this.canUseRange = canUseRange;
  }

  @Element( name = "CANUSEDESC", required = true, order = 30 )
  public Boolean getCanUseDescription() {
    return canUseDescription;
  }

  public void setCanUseDescription(Boolean canUseDescription) {
    this.canUseDescription = canUseDescription;
  }

  @Element( name = "STPCHKFEE", required = true, order = 40 )
  public Double getStopCheckFee() {
    return stopCheckFee;
  }

  public void setStopCheckFee(Double stopCheckFee) {
    this.stopCheckFee = stopCheckFee;
  }
}
