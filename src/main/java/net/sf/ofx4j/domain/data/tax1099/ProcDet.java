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


@Aggregate ( "PROCDET_V100")
public class ProcDet {

	private String dtAqd;
	private String dtSale;
	private String secName;
	private String costBasis;
	private String saleSpr;
	private String longShort;
	private String wasDisAllowed;
	private String noncoveredSec;
	private String basisNotshown;
	/**
	 * @return the dtAqd
	 */
	@Element ( name = "DTAQD", required = false, order = 0 )
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
	 * @return the dtSale
	 */
	@Element ( name = "DTSALE", required = false, order = 2 )
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
	@Element ( name = "SECNAME", required = false, order = 3 )
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
	 * @return the costBasis
	 */
	@Element ( name = "COSTBASIS", required = false, order = 4 )
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
	@Element ( name = "SALESPR", required = false, order = 5 )
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
	 * @return the longShort
	 */
	@Element ( name = "LONGSHORT", required = false, order = 6 )
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
	 * @return the wasDisAllowed
	 */
	@Element ( name = "WASHSALELOSSDISALLOWED", required = false, order = 7 )
	public String getWasDisAllowed() {
		return wasDisAllowed;
	}
	/**
	 * @param wasDisAllowed the wasDisAllowed to set
	 */
	public void setWasDisAllowed(String wasDisAllowed) {
		this.wasDisAllowed = wasDisAllowed;
	}
	/**
	 * @return the noncoveredSec
	 */
	@Element ( name = "NONCOVEREDSECURITY", required = false, order = 8 )
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
	@Element ( name = "BASISNOTSHOWN", required = false, order = 9 )
	public String getBasisNotshown() {
		return basisNotshown;
	}
	/**
	 * @param basisNotshown the basisNotshown to set
	 */
	public void setBasisNotshown(String basisNotshown) {
		this.basisNotshown = basisNotshown;
	}
	
	
	
}
