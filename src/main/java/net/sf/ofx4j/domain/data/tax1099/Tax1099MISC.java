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

import net.sf.ofx4j.domain.data.tax1099.PayerAddress;
import net.sf.ofx4j.domain.data.tax1099.RecAddress;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import net.sf.ofx4j.meta.Element;

/**
 * @author Aparna Gawali
 * aparna.gawali@sungard.com
 */


@Aggregate ( "TAX1099MISC_V100")
public class Tax1099MISC  {

  
	private String srvrtId;
	private String taxYear;
	
	private String royalties;
	private String otherIncome;
	private String fedTaxWh;
	private String subPmts;
			
	private PayerAddress payerAddress;
	private String payerId;
	private RecAddress recAddress;
	private String recId;
	private String recAcct;
	
	 
	  @Element ( name = "SRVRTID",required = true , order = 0 )
	  public String getSrvrtId() {
	    return srvrtId;
	  }

	  
	  public void setSrvrtId(String srvrtId) {
	    this.srvrtId = srvrtId;
	  }

	 
	  @Element ( name = "TAXYEAR", required = true, order = 1 )
	  public String getTaxYear() {
	    return taxYear;
	  }

	 
	  public void setTaxYear(String taxYear) {
	    this.taxYear = taxYear;
	  }

	/**
	 * @return the royalties
	 */
	@Element ( name = "ROYALTIES",required = false , order = 2 )
	public String getRoyalties() {
		return royalties;
	}


	/**
	 * @param royalties the royalties to set
	 */
	public void setRoyalties(String royalties) {
		this.royalties = royalties;
	}


	/**
	 * @return the otherIncome
	 */
	@Element ( name = "OTHERINCOME",required = false , order = 3 )
	public String getOtherIncome() {
		return otherIncome;
	}


	/**
	 * @param otherIncome the otherIncome to set
	 */
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}


	/**
	 * @return the fedTaxWh
	 */
	@Element ( name = "FEDTAXWH",required = false , order = 4 )
	public String getFedTaxWh() {
		return fedTaxWh;
	}


	/**
	 * @param fedTaxWh the fedTaxWh to set
	 */
	public void setFedTaxWh(String fedTaxWh) {
		this.fedTaxWh = fedTaxWh;
	}


	/**
	 * @return the subPmts
	 */
	@Element ( name = "SUBPMTS",required = false , order = 5 )
	public String getSubPmts() {
		return subPmts;
	}


	/**
	 * @param subPmts the subPmts to set
	 */
	public void setSubPmts(String subPmts) {
		this.subPmts = subPmts;
	}


	/**
	 * @return the payerAddress
	 */
	@ChildAggregate(required=true, order = 6)
	public PayerAddress getPayerAddress() {
		return payerAddress;
	}


	/**
	 * @param payerAddress the payerAddress to set
	 */
	public void setPayerAddress(PayerAddress payerAddress) {
		this.payerAddress = payerAddress;
	}
	
	/**
	 * @return the payerId
	 */
	@Element ( name = "PAYERID", required = true, order = 7 )
	public String getPayerId() {
		return payerId;
	}


	/**
	 * @param payerId the payerId to set
	 */
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	

	/**
	 * @return the recAddress
	 */
	@ChildAggregate(required=true, order = 8)
	public RecAddress getRecAddress() {
		return recAddress;
	}


	/**
	 * @param recAddress the recAddress to set
	 */
	public void setRecAddress(RecAddress recAddress) {
		this.recAddress = recAddress;
	}

	/**
	 * @return the recId
	 */
	@Element ( name = "RECID", required = true, order = 9 )
	public String getRecId() {
		return recId;
	}


	/**
	 * @param recId the recId to set
	 */
	public void setRecId(String recId) {
		this.recId = recId;
	}


	/**
	 * @return the recAcct
	 */
	@Element ( name = "RECACCT", required = true, order = 10 )
	public String getRecAcct() {
		return recAcct;
	}


	/**
	 * @param recAcct the recAcct to set
	 */
	public void setRecAcct(String recAcct) {
		this.recAcct = recAcct;
	}
}
