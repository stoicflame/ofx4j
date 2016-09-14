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

import net.sf.ofx4j.domain.data.common.BalanceRecord;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;

/**
 * Aggregate for the investment balance list.
 * @see "Section 13.9.2.7, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate("BALLIST")
public class BalanceList {

  private List<BalanceRecord> balanceRecords;

  /**
   * Gets the list of balance records.
   *
   * @return the list of balance records.
   */
  @ChildAggregate( order = 10 )
  public List<BalanceRecord> getBalanceRecords() {
    return balanceRecords;
  }

  /**
   * Sets the list of balance records.
   *
   * @param balanceRecords the list of balance records.
   */
  public void setBalanceRecords(List<BalanceRecord> balanceRecords) {
    this.balanceRecords = balanceRecords;
  }
}
