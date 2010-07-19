/*
 * Copyright 2010 Web Cohesion
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

package net.sf.ofx4j.domain.data.investment.statements;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;


/**
 * Aggreate to indicate whether position information is requested as part of the statement
 * @see "Section 13.9.1.2, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "INCPOS" )
public class IncludePosition {

  private Date sentDownDate;
  private Boolean includePositions = Boolean.TRUE;

  /**
   * Gets the date that the position should be sent down for. This is an optional field according
   * to the OFX spec.
   *
   * @return the date for the position
   */
  @Element( name = "DTASOF", order = 0)
  public Date getDateSentDown() {
    return sentDownDate;
  }

  /**
   * Sets the date that the position should be sent down for. This is an optional field according
   * to the OFX spec.
   *
   * @param sentDownDate the date for the position
   */
  public void setDateSentDown(Date sentDownDate) {
    this.sentDownDate = sentDownDate;
  }

  /**
   * Gets whether to include positions in the statement download.
   *
   * @return whether to include positions in the statement download
   */
  @Element( name = "INCLUDE", order = 10)
  public Boolean getIncludePositions() {
    return includePositions;
  }

  /**
   * Sets whether to include positions in the statement download.
   *
   * @param includePositions whether to include positions in the statement download
   */
  public void setIncludePositions(Boolean includePositions) {
    this.includePositions = includePositions;
  }
}
