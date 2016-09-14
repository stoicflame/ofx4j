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


@Aggregate ( "TAX1099DIV_V100")
public class Tax1099DIV  {

  
	private String srvrtId;
	private String taxYear;
	private String ordDiv;
	private String qualifiedDiv;
	private String totCapGain;
	private String p28Gain;
	private String unrecSec1250;
	private String sec1202;
	private String nonTaxDist;
	private String fedTaxWh;
	private String investExp;
	private String forTaxPd;
	private String cashLiq;
	private String nonCashLiq;
	
	private PayerAddress payerAddress;
	private String payerId;
	private RecAddress recAddress;
	private String recId;
	private String recAcct;
	
	 
	  @Element ( name = "SRVRTID",required = false , order = 0 )
	  public String getSrvrtId() {
	    return srvrtId;
	  }

	  
	  public void setSrvrtId(String srvrtId) {
	    this.srvrtId = srvrtId;
	  }

	 
	  @Element ( name = "TAXYEAR", required = false, order = 1 )
	  public String getTaxYear() {
	    return taxYear;
	  }

	 
	  public void setTaxYear(String taxYear) {
	    this.taxYear = taxYear;
	  }


	/**
	 * @return the ordDiv
	 */
	 @Element ( name = "ORDDIV", required = false, order = 2 )
	public String getOrdDiv() {
		return ordDiv;
	}


	/**
	 * @param ordDiv the ordDiv to set
	 */
	public void setOrdDiv(String ordDiv) {
		this.ordDiv = ordDiv;
	}


	/**
	 * @return the qualifiedDiv
	 */
	@Element ( name = "QUALIFIEDDIV", required = false, order = 3 )
	public String getQualifiedDiv() {
		return qualifiedDiv;
	}


	/**
	 * @param qualifiedDiv the qualifiedDiv to set
	 */
	public void setQualifiedDiv(String qualifiedDiv) {
		this.qualifiedDiv = qualifiedDiv;
	}


	/**
	 * @return the totCapGain
	 */
	@Element ( name = "TOTCAPGAIN", required = false, order = 4 )
	public String getTotCapGain() {
		return totCapGain;
	}


	/**
	 * @param totCapGain the totCapGain to set
	 */
	public void setTotCapGain(String totCapGain) {
		this.totCapGain = totCapGain;
	}


	/**
	 * @return the p28Gain
	 */
	@Element ( name = "P28GAIN", required = false, order = 5 )
	public String getP28Gain() {
		return p28Gain;
	}


	/**
	 * @param p28Gain the p28Gain to set
	 */
	public void setP28Gain(String p28Gain) {
		this.p28Gain = p28Gain;
	}


	/**
	 * @return the unrecSec1250
	 */
	@Element ( name = "UNRECSEC1250", required = false, order = 6 )
	public String getUnrecSec1250() {
		return unrecSec1250;
	}


	/**
	 * @param unrecSec1250 the unrecSec1250 to set
	 */
	public void setUnrecSec1250(String unrecSec1250) {
		this.unrecSec1250 = unrecSec1250;
	}


	/**
	 * @return the sec1202
	 */
	@Element ( name = "SEC1202", required = false, order = 7 )
	public String getSec1202() {
		return sec1202;
	}


	/**
	 * @param sec1202 the sec1202 to set
	 */
	public void setSec1202(String sec1202) {
		this.sec1202 = sec1202;
	}


	/**
	 * @return the nonTaxDist
	 */
	@Element ( name = "NONTAXDIST", required = false, order = 8 )
	public String getNonTaxDist() {
		return nonTaxDist;
	}


	/**
	 * @param nonTaxDist the nonTaxDist to set
	 */
	public void setNonTaxDist(String nonTaxDist) {
		this.nonTaxDist = nonTaxDist;
	}


	/**
	 * @return the fedTaxWh
	 */
	@Element ( name = "FEDTAXWH", required = false, order = 9 )
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
	@Element ( name = "INVESTEXP", required = false, order = 10 )
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
	@Element ( name = "FORTAXPD", required = false, order = 11 )
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
	 * @return the cashLiq
	 */
	@Element ( name = "CASHLIQ", required = false, order = 12 )
	public String getCashLiq() {
		return cashLiq;
	}


	/**
	 * @param cashLiq the cashLiq to set
	 */
	public void setCashLiq(String cashLiq) {
		this.cashLiq = cashLiq;
	}


	/**
	 * @return the nonCashLiq
	 */
	@Element ( name = "NONCASHLIQ", required = false, order = 13 )
	public String getNonCashLiq() {
		return nonCashLiq;
	}


	/**
	 * @param nonCashLiq the nonCashLiq to set
	 */
	public void setNonCashLiq(String nonCashLiq) {
		this.nonCashLiq = nonCashLiq;
	}


	/**
	 * @return the payerAddress
	 */
	@ChildAggregate(required=true, order = 14)
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
	@Element ( name = "PAYERID", required = true, order = 15 )
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
	@ChildAggregate(required=true, order = 16)
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
	@Element ( name = "RECID", required = true, order = 17 )
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
	@Element ( name = "RECACCT", required = true, order = 18 )
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
