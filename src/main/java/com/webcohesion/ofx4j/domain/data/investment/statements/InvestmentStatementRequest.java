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

package com.webcohesion.ofx4j.domain.data.investment.statements;

import com.webcohesion.ofx4j.domain.data.common.StatementRequest;
import com.webcohesion.ofx4j.domain.data.investment.accounts.InvestmentAccountDetails;
import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.ChildAggregate;
import com.webcohesion.ofx4j.meta.Element;

/**
 * Aggregate for the investment statement download request.
 * @see "Section 13.9.1.1, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate("INVSTMTRQ")
public class InvestmentStatementRequest extends StatementRequest {

  private InvestmentAccountDetails account;
  private Boolean includeOpenOrders = Boolean.FALSE;
  private IncludePosition includePosition;
  private Boolean includeBalance = Boolean.TRUE;
  private Boolean include401k = Boolean.TRUE;
  private Boolean include401kBalance = Boolean.TRUE;

  /**
   * The account details.
   *
   * @return The account details.
   */
  @ChildAggregate( name = "INVACCTFROM", required = true, order = 0 )
  public InvestmentAccountDetails getAccount() {
    return account;
  }

  /**
   * The account details.
   *
   * @param account The account details.
   */
  public void setAccount(InvestmentAccountDetails account) {
    this.account = account;
  }

  /**
   * Gets whether to include open orders. This is an optional field according to the OFX spec.
   * <br>
   * Note, open orders are not yet implemented.
   *
   * @return whether to include open orders
   */
  @Element( name = "INCOO", order = 20)
  public Boolean getIncludeOpenOrders() {
    return includeOpenOrders;
  }

  /**
   * Sets whether to include open orders. This is an optional field according to the OFX spec.
   * <br>
   * Note, open orders are not yet implemented.
   *
   * @param includeOpenOrders whether to include open orders
   */
  public void setIncludeOpenOrders(Boolean includeOpenOrders) {
    this.includeOpenOrders = includeOpenOrders;
  }

  /**
   * Gets the include position child aggregate. This is a required field according to the OFX spec.
   *
   * @return the include position child aggregate
   */
  @ChildAggregate ( name = "INCPOS", required = true, order = 30 )
  public IncludePosition getIncludePosition() {
    return includePosition;
  }

  /**
   * Gets the include position child aggregate. This is a required field according to the OFX spec.
   *
   * @param includePosition the include position child aggregate
   */
  public void setIncludePosition(IncludePosition includePosition) {
    this.includePosition = includePosition;
  }

  /**
   * Gets whether to include balance info in the response. This is a required field according to
   * the OFX spec.
   *
   * @return whether to include balance info in the response
   */
  @Element( name = "INCBAL", required = true, order = 40)
  public Boolean getIncludeBalance() {
    return includeBalance;
  }

  /**
   * Sets whether to include balance info in the response. This is a required field according to
   * the OFX spec.
   *
   * @param includeBalance whether to include balance info in the response
   */
  public void setIncludeBalance(Boolean includeBalance) {
    this.includeBalance = includeBalance;
  }

  /**
   * Gets whether to include 401K information in the response. This is a optional field according to
   * the OFX spec.
   *
   * @return whether to include balance info in the response
   */
  @Element( name = "INC401K", order = 50)
  public Boolean getInclude401k() {
    return include401k;
  }

  /**
   * Sets whether to include 401K information in the response. This is a optional field according to
   * the OFX spec.
   *
   * @param include401k whether to include balance info in the response
   */
  public void setInclude401k(Boolean include401k) {
    this.include401k = include401k;
  }

  /**
   * Gets whether to include 401K balances in the response. This is a optional field according to
   * the OFX spec.
   *
   * @return whether to include balance info in the response
   */
  @Element( name = "INC401KBAL", order = 60)
  public Boolean getInclude401kBalance() {
    return include401kBalance;
  }

  /**
   * Sets whether to include 401K balances in the response. This is a optional field according to
   * the OFX spec.
   *
   * @param include401kBalance whether to include balance info in the response
   */
  public void setInclude401kBalance(Boolean include401kBalance) {
    this.include401kBalance = include401kBalance;
  }

}
