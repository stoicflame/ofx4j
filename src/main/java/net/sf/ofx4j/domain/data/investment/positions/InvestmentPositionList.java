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

package net.sf.ofx4j.domain.data.investment.positions;

import net.sf.ofx4j.domain.data.investment.positions.BasePosition;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;

/**
 * Aggregate for a list of invesment positions.
 * @see "Section 13.9.2.2, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate("INVPOSLIST")
public class InvestmentPositionList {
  private List<BasePosition> positions;

  /**
   * Gets the list of positions
   *
   * @return the list of positions
   */
  @ChildAggregate( order = 10 )
  public List<BasePosition> getPositions() {
    return positions;
  }

  /**
   * Sets the list of positions.
   *
   * @param positions the list of positions
   */
  public void setPositions(List<BasePosition> positions) {
    this.positions = positions;
  }
}
