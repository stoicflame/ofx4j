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

import java.util.List;

import net.sf.ofx4j.domain.data.common.T1099Response;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Aparna Gawali
 * aparna.gawali@sungard.com
 */
@Aggregate("TAX1099RS")
public class Tax1099Response extends T1099Response {

	// private Tax1099DIV tax1099div;
	private List<Tax1099DIV> lstTax1099DIV;
	
	private List<Tax1099INT> lstTax1099INT;
	
	private List<Tax1099R> lstTax1099R;
	
	private List<Tax1099B> lstTax1099B;
	
	private List<Tax1099MISC> lstTax1099MISC;
	
	private List<Tax1099OID> lstTax1099OID;

	/**
	 * @return the lstTax1099DIV
	 */
	@ChildAggregate(required = false, order = 0)
	public List<Tax1099DIV> getLstTax1099DIV() {
		return lstTax1099DIV;
	}

	/**
	 * @param lstTax1099DIV
	 *            the lstTax1099DIV to set
	 */
	public void setLstTax1099DIV(List<Tax1099DIV> lstTax1099DIV) {
		this.lstTax1099DIV = lstTax1099DIV;
	}

	

	public String getResponseMessageName() {
		return "1099 Tax details";
	}

	/**
	 * @return the lstTax1099INT
	 */
	@ChildAggregate(required = false, order = 1)
	public List<Tax1099INT> getLstTax1099INT() {
		return lstTax1099INT;
	}

	/**
	 * @param lstTax1099INT the lstTax1099INT to set
	 */
	public void setLstTax1099INT(List<Tax1099INT> lstTax1099INT) {
		this.lstTax1099INT = lstTax1099INT;
	}

	/**
	 * @return the lstTax1099R
	 */
	@ChildAggregate(required = false, order = 2)
	public List<Tax1099R> getLstTax1099R() {
		return lstTax1099R;
	}

	/**
	 * @param lstTax1099R the lstTax1099R to set
	 */
	public void setLstTax1099R(List<Tax1099R> lstTax1099R) {
		this.lstTax1099R = lstTax1099R;
	}

	/**
	 * @return the lstTax1099B
	 */
	@ChildAggregate(required = false, order = 3)
	public List<Tax1099B> getLstTax1099B() {
		return lstTax1099B;
	}

	/**
	 * @param lstTax1099B the lstTax1099B to set
	 */
	public void setLstTax1099B(List<Tax1099B> lstTax1099B) {
		this.lstTax1099B = lstTax1099B;
	}

	/**
	 * @return the lstTax1099MISC
	 */
	@ChildAggregate(required = false, order = 4)
	public List<Tax1099MISC> getLstTax1099MISC() {
		return lstTax1099MISC;
	}

	/**
	 * @param lstTax1099MISC the lstTax1099MISC to set
	 */
	public void setLstTax1099MISC(List<Tax1099MISC> lstTax1099MISC) {
		this.lstTax1099MISC = lstTax1099MISC;
	}

	/**
	 * @return the lstTax1099OID
	 */
	@ChildAggregate(required = false, order =5)
	public List<Tax1099OID> getLstTax1099OID() {
		return lstTax1099OID;
	}

	/**
	 * @param lstTax1099OID the lstTax1099OID to set
	 */
	public void setLstTax1099OID(List<Tax1099OID> lstTax1099OID) {
		this.lstTax1099OID = lstTax1099OID;
	}
	
	
	

}
