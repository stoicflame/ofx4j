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
package com.webcohesion.ofx4j.domain.data.tax1099;

import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.Element;

/**
 * @author Aparna Gawali
 * aparna.gawali@sungard.com
 */


@Aggregate ( "PROCDET_V100")
public class ProcDet {

	private String form8959Code;
	private String dtAqd;
	private String dtVar;
	private String dtSale;
	private String secName;
	private String saleDescription;
	private String numShrs;
	private String costBasis;
	private String saleSpr;
	private String accruedMktDiscount;
	private String longShort;
	private String ordinary;
	private String washSale;
	private String fedTaxWh;
	private String washDisAllowed;
	private String noncoveredSec;
	private String basisNotshown;
	private String form1099BNotRec;
	private String collectible;
	private String stateCode;
	private String stateIdNum;
	private String stateTaxWh;
	private String fatca;
	/**
	 * @return the form8959Code
	 */
	@Element(name = "FORM8949CODE", order = 0)
	public String getForm8959Code() {
		return form8959Code;
	}
	/**
	 * @param form8959Code the form8959Code to set
	 */
	public void setForm8959Code(String form8959Code) {
		this.form8959Code = form8959Code;
	}
	/**
	 * @return the dtAqd
	 */
	@Element ( name = "DTAQD", required = false, order = 1 )
	public String getDtAqd() {
		return dtAqd;
	}
	/**
	 * @param dtAqd the dtAqd to set
	 */
	public void setDtAqd(String dtAqd) {
		this.dtAqd = dtAqd;
	}
	/**
	 * @return the dtVar
	 */
	@Element(name = "DTVAR", order = 2)
	public String getDtVar() {
		return dtVar;
	}
	/**
	 * @param dtVar the dtVar to set
	 */
	public void setDtVar(String dtVar) {
		this.dtVar = dtVar;
	}
	/**
	 * @return the dtSale
	 */
	@Element ( name = "DTSALE", order = 3 )
	public String getDtSale() {
		return dtSale;
	}
	/**
	 * @param dtSale the dtSale to set
	 */
	public void setDtSale(String dtSale) {
		this.dtSale = dtSale;
	}
	/**
	 * @return the secName
	 */
	@Element ( name = "SECNAME", order = 4 )
	public String getSecName() {
		return secName;
	}
	/**
	 * @param secName the secName to set
	 */
	public void setSecName(String secName) {
		this.secName = secName;
	}
	/**
	 * @return the saleDescription
	 */
	@Element ( name = "SALEDESCRIPTION", order = 5 )
	public String getSaleDescription() {
		return saleDescription;
	}
	/**
	 * @param saleDescription the saleDescription to set
	 */
	public void setSaleDescription(String saleDescription) {
		this.saleDescription = saleDescription;
	}
	/**
	 * @return the numShrs
	 */
	@Element ( name = "NUMSHRS", order = 6 )
	public String getNumShrs() {
		return numShrs;
	}
	/**
	 * @param numShrs the numShrs to set
	 */
	public void setNumShrs(String numShrs) {
		this.numShrs = numShrs;
	}
	/**
	 * @return the costBasis
	 */
	@Element ( name = "COSTBASIS", order = 7 )
	public String getCostBasis() {
		return costBasis;
	}
	/**
	 * @param costBasis the costBasis to set
	 */
	public void setCostBasis(String costBasis) {
		this.costBasis = costBasis;
	}
	/**
	 * @return the saleSpr
	 */
	@Element ( name = "SALESPR", order = 8 )
	public String getSaleSpr() {
		return saleSpr;
	}
	/**
	 * @param saleSpr the saleSpr to set
	 */
	public void setSaleSpr(String saleSpr) {
		this.saleSpr = saleSpr;
	}
	/**
	 * @return the accruedMktDiscount
	 */
	@Element(name = "ACCRUEDMKTDISCOUNT", order = 9)
	public String getAccruedMktDiscount() {
		return accruedMktDiscount;
	}
	/**
	 * @param accruedMktDiscount the accruedMktDiscount to set
	 */
	public void setAccruedMktDiscount(String accruedMktDiscount) {
		this.accruedMktDiscount = accruedMktDiscount;
	}
	/**
	 * @return the longShort
	 */
	@Element ( name = "LONGSHORT", order = 10 )
	public String getLongShort() {
		return longShort;
	}
	/**
	 * @param longShort the longShort to set
	 */
	public void setLongShort(String longShort) {
		this.longShort = longShort;
	}
	/**
	 * @return the ordinary
	 */
	@Element(name = "ORDINARY", order = 11)
	public String getOrdinary() {
		return ordinary;
	}
	/**
	 * @param ordinary the ordinary to set
	 */
	public void setOrdinary(String ordinary) {
		this.ordinary = ordinary;
	}
	/**
	 * @return the washSale
	 */
	@Element(name = "WASHSALE", order = 12)
	public String getWashSale() {
		return washSale;
	}
	/**
	 * @param washSale the washSale to set
	 */
	public void setWashSale(String washSale) {
		this.washSale = washSale;
	}
	/**
	 * @return the fedTaxWh
	 */
	@Element(name = "FEDTAXWH", order = 13)
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
	 * @return the washDisAllowed
	 */
	@Element ( name = "WASHSALELOSSDISALLOWED", order = 14 )
	public String getWashDisAllowed() {
		return washDisAllowed;
	}
	/**
	 * @param washDisAllowed the washDisAllowed to set
	 */
	public void setWashDisAllowed(String washDisAllowed) {
		this.washDisAllowed = washDisAllowed;
	}
	/**
	 * @return the noncoveredSec
	 */
	@Element ( name = "NONCOVEREDSECURITY", order = 15 )
	public String getNoncoveredSec() {
		return noncoveredSec;
	}
	/**
	 * @param noncoveredSec the noncoveredSec to set
	 */
	public void setNoncoveredSec(String noncoveredSec) {
		this.noncoveredSec = noncoveredSec;
	}
	/**
	 * @return the basisNotshown
	 */
	@Element ( name = "BASISNOTSHOWN", order = 16 )
	public String getBasisNotshown() {
		return basisNotshown;
	}
	/**
	 * @param basisNotshown the basisNotshown to set
	 */
	public void setBasisNotshown(String basisNotshown) {
		this.basisNotshown = basisNotshown;
	}
	/**
	 * @return the form1099BNotRec
	 */
	@Element(name = "FORM1099BNOTRECEIVED", order = 17)
	public String getForm1099BNotRec() {
		return form1099BNotRec;
	}
	/**
	 * @param form1099BNotRec the form1099BNotRec to set
	 */
	public void setForm1099BNotRec(String form1099BNotRec) {
		this.form1099BNotRec = form1099BNotRec;
	}
	/**
	 * @return the collectible
	 */
	@Element(name = "COLLECTIBLE", order = 18)
	public String getCollectible() {
		return collectible;
	}
	/**
	 * @param collectible the collectible to set
	 */
	public void setCollectible(String collectible) {
		this.collectible = collectible;
	}
	/**
	 * @return the stateCode
	 */
	@Element(name = "STATECODE", order = 19)
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return the stateIdNum
	 */
	@Element(name = "STATEIDNUM", order = 20)
	public String getStateIdNum() {
		return stateIdNum;
	}
	/**
	 * @param stateIdNum the stateIdNum to set
	 */
	public void setStateIdNum(String stateIdNum) {
		this.stateIdNum = stateIdNum;
	}
	/**
	 * @return the stateTaxWh
	 */
	@Element(name = "STATETAXWHELD", order = 21)
	public String getStateTaxWh() {
		return stateTaxWh;
	}
	/**
	 * @param stateTaxWh the stateTaxWh to set
	 */
	public void setStateTaxWh(String stateTaxWh) {
		this.stateTaxWh = stateTaxWh;
	}
	/**
	 * @return the fatca
	 */
	@Element(name = "FATCA", order = 22)
	public String getFatca() {
		return fatca;
	}
	/**
	 * @param fatca the fatca to set
	 */
	public void setFatca(String fatca) {
		this.fatca = fatca;
	}
}
