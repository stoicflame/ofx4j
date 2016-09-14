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


@Aggregate ( "TAX1099INT_V100")
public class Tax1099INT  {

  
	private String srvrtId;
	private String taxYear;
	private String intIncome;
	private String erlWithPen;
	private String intUsbndtrs;
	private String fedTaxWh;
	private String investExp;
	private String forTaxPd;
		
	private PayerAddress payerAddress;
	private String payerId;
	private RecAddress recAddress;
	private String recId;
	private String recAcct;
	private String taxExemptInt;
	private String specifiedPabInt;
	 
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
	 * @return the intIncome
	 */
	@Element ( name = "INTINCOME",required = false , order = 2 )
	public String getIntIncome() {
		return intIncome;
	}


	/**
	 * @param intIncome the intIncome to set
	 */
	public void setIntIncome(String intIncome) {
		this.intIncome = intIncome;
	}


	/**
	 * @return the erlWithPen
	 */
	@Element ( name = "ERLWITHPEN",required = false , order = 3 )
	public String getErlWithPen() {
		return erlWithPen;
	}


	/**
	 * @param erlWithPen the erlWithPen to set
	 */
	public void setErlWithPen(String erlWithPen) {
		this.erlWithPen = erlWithPen;
	}


	/**
	 * @return the intUsbndtrs
	 */
	@Element ( name = "INTUSBNDTRS",required = false , order = 4 )
	public String getIntUsbndtrs() {
		return intUsbndtrs;
	}


	/**
	 * @param intUsbndtrs the intUsbndtrs to set
	 */
	public void setIntUsbndtrs(String intUsbndtrs) {
		this.intUsbndtrs = intUsbndtrs;
	}


	/**
	 * @return the fedTaxWh
	 */
	@Element ( name = "FEDTAXWH", required = false, order = 5 )
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
	 * @return the investExp
	 */
	@Element ( name = "INVESTEXP", required = false, order = 6 )
	public String getInvestExp() {
		return investExp;
	}


	/**
	 * @param investExp the investExp to set
	 */
	public void setInvestExp(String investExp) {
		this.investExp = investExp;
	}


	/**
	 * @return the forTaxPd
	 */
	@Element ( name = "FORTAXPD", required = false, order = 7 )
	public String getForTaxPd() {
		return forTaxPd;
	}


	/**
	 * @param forTaxPd the forTaxPd to set
	 */
	public void setForTaxPd(String forTaxPd) {
		this.forTaxPd = forTaxPd;
	}


	/**
	 * @return the payerAddress
	 */
	@ChildAggregate(required=true, order = 8)
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
	@Element ( name = "PAYERID", required = true, order = 9 )
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
	@ChildAggregate(required=true, order = 10)
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
	@Element ( name = "RECID", required = true, order = 11 )
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
	@Element ( name = "RECACCT", required = true, order = 12 )
	public String getRecAcct() {
		return recAcct;
	}


	/**
	 * @param recAcct the recAcct to set
	 */
	public void setRecAcct(String recAcct) {
		this.recAcct = recAcct;
	}


	/**
	 * @return the taxExemptInt
	 */
	@Element ( name = "TAXEXEMPTINT", required = false, order = 13 )
	public String getTaxExemptInt() {
		return taxExemptInt;
	}


	/**
	 * @param taxExemptInt the taxExemptInt to set
	 */
	public void setTaxExemptInt(String taxExemptInt) {
		this.taxExemptInt = taxExemptInt;
	}


	/**
	 * @return the specifiedPabInt
	 */
	@Element ( name = "SPECIFIEDPABINT", required = false, order = 14 )
	public String getSpecifiedPabInt() {
		return specifiedPabInt;
	}


	/**
	 * @param specifiedPabInt the specifiedPabInt to set
	 */
	public void setSpecifiedPabInt(String specifiedPabInt) {
		this.specifiedPabInt = specifiedPabInt;
	}
	
	
	  
}
