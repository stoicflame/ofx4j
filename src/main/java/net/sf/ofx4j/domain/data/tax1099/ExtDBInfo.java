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

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;


/**
 * @author Aparna Gawali
 * aparna.gawali@sungard.com
 */


@Aggregate ( "EXTDBINFO_V100")
public class ExtDBInfo {

	private List<ProcDet> procDet;
	private String teInterest;
	private String pabInterest;
	private String teIntDividend;
	private String pabDividend;
	
	/**
	 * @return the procDet
	 */
	@ChildAggregate(required=false, order = 0)
	public List<ProcDet> getProcDet() {
		return procDet;
	}

	/**
	 * @param procDet the procDet to set
	 */
	public void setProcDet(List<ProcDet> procDet) {
		this.procDet = procDet;
	}

	/**
	 * @return the teInterest
	 */
	@Element ( name = "TEINTEREST",required = false , order = 1 )
	public String getTeInterest() {
		return teInterest;
	}

	/**
	 * @param teInterest the teInterest to set
	 */
	public void setTeInterest(String teInterest) {
		this.teInterest = teInterest;
	}

	/**
	 * @return the pabInterest
	 */
	@Element ( name = "PABINTEREST",required = false , order = 2 )
	public String getPabInterest() {
		return pabInterest;
	}

	/**
	 * @param pabInterest the pabInterest to set
	 */
	public void setPabInterest(String pabInterest) {
		this.pabInterest = pabInterest;
	}

	/**
	 * @return the teIntDividend
	 */
	@Element ( name = "TEINTDIVIDEND",required = false , order = 3 )
	public String getTeIntDividend() {
		return teIntDividend;
	}

	/**
	 * @param teIntDividend the teIntDividend to set
	 */
	public void setTeIntDividend(String teIntDividend) {
		this.teIntDividend = teIntDividend;
	}

	/**
	 * @return the pabDividend
	 */
	@Element ( name = "PABDIVIDEND",required = false , order = 4 )
	public String getPabDividend() {
		return pabDividend;
	}

	/**
	 * @param pabDividend the pabDividend to set
	 */
	public void setPabDividend(String pabDividend) {
		this.pabDividend = pabDividend;
	}	
}
