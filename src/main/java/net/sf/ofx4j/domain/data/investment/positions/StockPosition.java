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
import net.sf.ofx4j.meta.Element;

/**
 * Represents a stock position.
 * @see "Section 13.9.2.6.1, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "POSSTOCK" )
public class StockPosition extends BasePosition {
  private Double unitsStreet;
  private Double unitsUser;
  private Boolean reinvestDividends;

  /**
   * Gets the number of units in the financial insititution's street name.
   *
   * @return the number of units in the financial insititution's street name.
   */
  @Element( name = "UNITSSTREET", order = 20)
  public Double getUnitsStreet() {
    return unitsStreet;
  }

  /**
   * Sets the number of units in the financial insititution's street name.
   *
   * @param unitsStreet the number of units in the financial insititution's street name.
   */
  public void setUnitsStreet(Double unitsStreet) {
    this.unitsStreet = unitsStreet;
  }

  /**
   * Gets the number of units in the user's name.
   *
   * @return the number of units in the user's name.
   */
  @Element( name = "UNITSUSER", order = 30)
  public Double getUnitsUser() {
    return unitsUser;
  }

  /**
   * Sets the number of units in the user's name.
   *
   * @param unitsUser the number of units in the user's name.
   */
  public void setUnitsUser(Double unitsUser) {
    this.unitsUser = unitsUser;
  }

  /**
   * Gets whether dividends are automatically reinvested.
   *
   * @return whether dividends are automatically reinvested
   */
  @Element( name = "REINVDIV", order = 40)
  public Boolean getReinvestDividends() {
    return reinvestDividends;
  }

  /**
   * Sets whether dividends are automatically reinvested.
   *
   * @param reinvestDividends whether dividends are automatically reinvested
   */
  public void setReinvestDividends(Boolean reinvestDividends) {
    this.reinvestDividends = reinvestDividends;
  }
}
