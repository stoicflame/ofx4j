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

package net.sf.ofx4j.domain.data.investment.transactions;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * Investment transaction aggregate ("INVTRAN").
 * @see "Section 13.9.2.4.1, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "INVTRAN" )
public class InvestmentTransaction {

  private String transactionId;
  private String serverId;
  private Date tradeDate;
  private Date settlementDate;
  private String reversalTransactionId;
  private String memo;

  /**
   * Gets the unique financial institution assigned transaction id. This is a
   * required field according to the OFX spec.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @return the financial institution asssigned transaction id
   */
  @Element( name = "FITID", required = true, order = 0)
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Sets the unique financial institution assigned transaction id. This is a
   * required field according to the OFX spec.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @param transactionId the financial institution asssigned transaction id
   */
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  /**
   * Gets the server assigned transaction id. This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @return the server assigned transaction id
   */
  @Element( name = "SRVRTID", order = 10)
  public String getServerId() {
    return serverId;
  }

  /**
   * Sets the server assigned transaction id. This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @param serverId the server assigned transaction id
   */
  public void setServerId(String serverId) {
    this.serverId = serverId;
  }

  /**
   * Gets the trade date of the transaction. For stock splits, this is the
   * day of record. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @return the trade date
   */
  @Element( name = "DTTRADE", required = true, order = 20)
  public Date getTradeDate() {
    return tradeDate;
  }

  /**
   * Sets the trade date of the transaction. For stock splits, this is the
   * day of record. This is a required field according to the OFX spec.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @param tradeDate the trade date
   */
  public void setTradeDate(Date tradeDate) {
    this.tradeDate = tradeDate;
  }

  /**
   * Gets the settlement date of the transaction. For stock splits, this is the
   * day of of execution. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @return the trade date
   */
  @Element( name = "DTSETTLE", order = 30)
  public Date getSettlementDate() {
    return settlementDate;
  }

  /**
   * Sets the settlement date of the transaction. For stock splits, this is the
   * day of of execution. This is an optional field according to the OFX spec.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @param settlementDate the trade date
   */
  public void setSettlementDate(Date settlementDate) {
    this.settlementDate = settlementDate;
  }

  /**
   * For a reveral transaction, gets the financial institution assigned
   * transaction id for the transaction being revesed.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @return the transaction id of the transaction being reversed
   */
  @Element( name = "REVERSALFITID", order = 40)
  public String getReversalTransactionId() {
    return reversalTransactionId;
  }

  /**
   * For a reveral transaction, gets the financial institution assigned
   * transaction id for the transaction being revesed.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @param reversalTransactionId the transaction id of the transaction being reversed
   */
  public void setReversalTransactionId(String reversalTransactionId) {
    this.reversalTransactionId = reversalTransactionId;
  }

  /**
   * Gets the memo associated with the transaction. This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @return the memo
   */
  @Element( name = "MEMO", order = 50)
  public String getMemo() {
    return memo;
  }

  /**
   * Sets the memo associated with the transaction. This is an optional field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.1, OFX Spec"
   *
   * @param memo the memo
   */
  public void setMemo(String memo) {
    this.memo = memo;
  }
}
