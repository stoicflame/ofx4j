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

package com.webcohesion.ofx4j.domain.data.common;

import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.Element;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ryan Heaton
 */
@Aggregate
public class BalanceInfo {

  private BigDecimal amount = BigDecimal.ZERO;
  private Date asOfDate;

  /**
   * The amount.
   *
   * @return The amount.
   */
  public double getAmount() {
    return amount.doubleValue();
  }

  /**
   * The amount.
   *
   * @param amount The amount.
   */
  public void setAmount(double amount) {
    this.amount = BigDecimal.valueOf(amount);
  }
  
  /**
   * The amount.
   *
   * @param amount The amount.
   */
  public void setBigDecimalAmount(BigDecimal amount) {
    this.amount = amount == null? BigDecimal.ZERO : amount;
  }
  
  /**
   * The amount.
   *
   * @return The amount.
   */
  @Element ( name = "BALAMT", required = true, order = 0)
  public BigDecimal getBigDecimalAmount() {
    return amount;
  }

  /**
   * The as-of date.
   *
   * @return The as-of date.
   */
  @Element ( name = "DTASOF", required = true, order = 10)
  public Date getAsOfDate() {
    return asOfDate;
  }

  /**
   * The as-of date.
   *
   * @param asOfDate The as-of date.
   */
  public void setAsOfDate(Date asOfDate) {
    this.asOfDate = asOfDate;
  }
}
