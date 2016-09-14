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

package net.sf.ofx4j.domain.data.common;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Locale;

/**
 * @author Ryan Heaton
 * @see "Section 5.2, OFX Spec"
 */
@Aggregate ( "CURRENCY" )
public class Currency {

  private String code = java.util.Currency.getInstance(Locale.US).getCurrencyCode().toUpperCase();
  private Float exchangeRate;

  /**
   * The currency code.
   *
   * @return The currency code.
   * @see java.util.Currency#getCurrencyCode()
   */
  @Element ( name = "CURSYM", required = true, order = 0 )
  public String getCode() {
    return code;
  }

  /**
   * The currency code
   *
   * @param code The currency code
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * The exchange rate.
   *
   * @return The exchange rate.
   */
  @Element ( name = "CURRATE", required = true, order = 10 )
  public Float getExchangeRate() {
    return exchangeRate;
  }

  /**
   * The exchange rate.
   *
   * @param exchangeRate The exchange rate.
   */
  public void setExchangeRate(Float exchangeRate) {
    this.exchangeRate = exchangeRate;
  }
}
