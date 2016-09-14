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
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * @author Ryan Heaton
 * @see "Section 3.1.3, OFX Spec"
 */
@Aggregate ( "BAL" )
public class BalanceRecord {

  public enum Type {

    DOLLAR,

    PERCENT,

    NUMBER
  }

  private String name;
  private String description;
  private Type type;
  private String value;
  private Date timestamp;
  private Currency currency;

  /**
   * Name of the balance.
   *
   * @return Name of the balance.
   */
  @Element ( name = "NAME", required = true, order = 0 )
  public String getName() {
    return name;
  }

  /**
   * Name of the balance.
   *
   * @param name Name of the balance.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Description of the balance.
   *
   * @return Description of the balance.
   */
  @Element ( name = "DESC", required = true, order = 10 )
  public String getDescription() {
    return description;
  }

  /**
   * Description of the balance.
   *
   * @param description Description of the balance.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Type of the balance.
   *
   * @return Type of the balance.
   */
  @Element ( name = "BALTYPE", required = true, order = 20 )
  public Type getType() {
    return type;
  }

  /**
   * Type of the balance.
   *
   * @param type Type of the balance.
   */
  public void setType(Type type) {
    this.type = type;
  }

  /**
   * The value of the balance.
   *
   * @return The value of the balance.
   */
  @Element ( name = "VALUE", required = true, order = 30 )
  public String getValue() {
    return value;
  }

  /**
   * The value of the balance.
   *
   * @param value The value of the balance.
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Timestamp of the balance.
   *
   * @return Timestamp of the balance.
   */
  @Element ( name = "DTASOF", order = 40 )
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Timestamp of the balance.
   *
   * @param timestamp Timestamp of the balance.
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Currency.
   *
   * @return Currency.
   */
  @ChildAggregate ( order = 50 )
  public Currency getCurrency() {
    return currency;
  }

  /**
   * Currency.
   *
   * @param currency Currency.
   */
  public void setCurrency(Currency currency) {
    this.currency = currency;
  }
}
