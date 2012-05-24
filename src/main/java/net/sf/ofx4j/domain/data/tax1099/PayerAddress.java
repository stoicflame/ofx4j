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
package net.sf.ofx4j.domain.data.tax1099;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * @author Aparna Gawali
 * aparna.gawali@sungard.com
 */


@Aggregate ( "PAYERADDR")
public class PayerAddress {

	private String payerName1;
	private String payerName2;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postalCode;
	private String phone;
	/**
	 * @return the payerName1
	 */
	@Element ( name = "PAYERNAME1",required = true , order = 0 )
	public String getPayerName1() {
		return payerName1;
	}
	/**
	 * @param payerName1 the payerName1 to set
	 */
	public void setPayerName1(String payerName1) {
		this.payerName1 = payerName1;
	}
	/**
	 * @return the payerName2
	 */
	@Element ( name = "PAYERNAME2",required = false , order = 1 )
	public String getPayerName2() {
		return payerName2;
	}
	/**
	 * @param payerName2 the payerName2 to set
	 */
	public void setPayerName2(String payerName2) {
		this.payerName2 = payerName2;
	}
	/**
	 * @return the address1
	 */
	@Element ( name = "ADDR1",required = true , order = 2 )
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	/**
	 * @return the address2
	 */
	@Element ( name = "ADDR2",required = true , order = 3 )
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * @return the city
	 */
	@Element ( name = "CITY",required = true , order = 4 )
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	@Element ( name = "STATE",required = true , order = 5 )
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the postalCode
	 */
	@Element ( name = "POSTALCODE",required = true , order = 6 )
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the phone
	 */
	@Element ( name = "PHONE",required = false , order = 7 )
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
