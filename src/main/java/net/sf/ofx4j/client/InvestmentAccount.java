/*
 * Copyright 2010 Web Cohesion
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

package net.sf.ofx4j.client;

import net.sf.ofx4j.OFXException;
import net.sf.ofx4j.domain.data.investment.accounts.InvestmentAccountDetails;
import net.sf.ofx4j.domain.data.investment.statements.InvestmentStatementResponse;
import net.sf.ofx4j.domain.data.seclist.SecurityList;
import net.sf.ofx4j.domain.data.seclist.SecurityListResponse;
import net.sf.ofx4j.domain.data.seclist.SecurityRequest;

import java.util.Date;
import java.util.List;

/**
 * @author Jon Perlow
 */
public interface InvestmentAccount extends FinancialInstitutionAccount {

  /**
   * Read an account statement.
   *
   * @param start The start date of the statement.
   * @param end The end date of the statement.
   * @throws OFXException if there's an error talking to the brokerage
   * @return The account statement.
   */
  // Overriden for type covariance
  InvestmentStatementResponse readStatement(Date start, Date end) throws OFXException;

  /**
   * Reads a list of securities from the brokerage
   *
   * @param securities the securities to read
   * @return The security response containing the security infos
   * @throws OFXException if there's an error talking to the brokerage
   */
  SecurityList readSecurityList(List<SecurityRequest> securities) throws OFXException;

  /**
   * The details of the account.
   *
   * @return The details of the account.
   */
  InvestmentAccountDetails getDetails();
}
