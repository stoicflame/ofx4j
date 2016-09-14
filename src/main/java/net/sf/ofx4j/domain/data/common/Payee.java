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

/**
 * @author Ryan Heaton
 */
@Aggregate ("PAYEE")
public class Payee {
  
  private String name;
  private String address1;
  private String address2;
  private String address3;
  private String city;
  private String state;
  private String zip;
  private String country;
  private String phone;

  /**
   * The name of the payee.
   *
   * @return The name of the payee.
   */
  @Element ( name = "NAME", order = 30 )
  public String getName() {
    return name;
  }

  /**
   * The name of the payee.
   *
   * @param name The name of the payee.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * The address of the payee.
   *
   * @return The address of the payee.
   */
  @Element ( name = "ADDR1", required = true, order = 40 )
  public String getAddress1() {
    return address1;
  }

  /**
   * The address of the payee.
   *
   * @param address1 The address of the payee.
   */
  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  /**
   * The address of the payee.
   *
   * @return The address of the payee.
   */
  @Element ( name = "ADDR2", order = 50 )
  public String getAddress2() {
    return address2;
  }

  /**
   * The address of the payee.
   *
   * @param address2 The address of the payee.
   */
  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  /**
   * The address of the payee.
   *
   * @return The address of the payee.
   */
  @Element ( name = "ADDR3", order = 60 )
  public String getAddress3() {
    return address3;
  }

  /**
   * The address of the payee.
   *
   * @param address3 The address of the payee.
   */
  public void setAddress3(String address3) {
    this.address3 = address3;
  }

  /**
   * The city of the payee.
   *
   * @return The city of the payee.
   */
  @Element ( name = "CITY", required = true, order = 70 )
  public String getCity() {
    return city;
  }

  /**
   * The city of the payee.
   *
   * @param city The city of the payee.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * The state of this payee.
   *
   * @return The state of this payee.
   */
  @Element ( name = "STATE", required = true, order = 80 )
  public String getState() {
    return state;
  }

  /**
   * The state of this payee.
   *
   * @param state The state of this payee.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * The postal code of this payee.
   *
   * @return The postal code of this payee.
   */
  @Element ( name = "POSTALCODE", required = true, order = 90 )
  public String getZip() {
    return zip;
  }

  /**
   * The postal code of this payee.
   *
   * @param zip The postal code of this payee.
   */
  public void setZip(String zip) {
    this.zip = zip;
  }

  /**
   * The country code for this payee.
   *
   * @return The country code for this payee.
   * @see java.util.Locale#getISO3Country()
   */
  @Element ( name = "COUNTRY", required = true, order = 100 )
  public String getCountry() {
    return country;
  }

  /**
   * The country code for this payee.
   *
   * @param country The country code for this payee.
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * The phone number.
   *
   * @return The phone number.
   */
  @Element ( name = "PHONE", order = 110 )
  public String getPhone() {
    return phone;
  }

  /**
   * The phone number.
   *
   * @param phone The phone number.
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

}
