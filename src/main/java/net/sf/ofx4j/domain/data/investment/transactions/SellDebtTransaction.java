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
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Transaction for selling debt (i.e. bonds, CDs, etc.,).
 * @see "Section 13.9.2.4.4, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "SELLDEBT" )
public class SellDebtTransaction extends BaseSellInvestmentTransaction {

  private String sellReason;
  private Double accruedInterest;

  public SellDebtTransaction() {
    super(TransactionType.SELL_DEBT);
  }

  /**
   * Gets the reason for the sale. One of "CALL" (the debt was called), "SELL" (the debt was sold),
   * "MATURITY" (the debt reached maturity).
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return The reason for the sale
   */
  @Element( name = "SELLREASON", order = 30)
  public String getSellReason() {
    return sellReason;
  }

  /**
   * Sets the reason for the sale. One of "CALL" (the debt was called), "SELL" (the debt was sold),
   * "MATURITY" (the debt reached maturity).
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param sellReason The reason for the sale
   */
  public void setSellReason(String sellReason) {
    this.sellReason = sellReason;
  }

  /**
   * Gets the sell reason as one of the well-known types.
   *
   * @return the sell reason or null if it's not well known
   */
  public SellDebtReason getSellReasonEnum() {
    return SellDebtReason.fromOfx(getSellReason());
  }

  /**
   * Gets the amount of accrued interest on the debt. This is an optional field according to the
   * OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the amount of accrued interest
   */
  @Element( name = "ACCRDINT", order = 40)
  public Double getAccruedInterest() {
    return accruedInterest;
  }

  /**
   * Sets the amount of accrued interest on the debt. This is an optional field according to the
   * OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param accruedInterest the amount of accrued interest
   */
  public void setAccruedInterest(Double accruedInterest) {
    this.accruedInterest = accruedInterest;
  }
}
