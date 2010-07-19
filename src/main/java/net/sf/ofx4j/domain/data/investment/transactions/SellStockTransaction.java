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
 * Transaction for selling stock.
 * @see "Section 13.9.2.4.4, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "SELLSTOCK" )
public class SellStockTransaction extends BaseSellInvestmentTransaction {

  private String sellType;

  public SellStockTransaction() {
    super(TransactionType.SELL_STOCK);
  }

  /**
   * Gets the type of stock sale (i.e. "SELL" or "SELLSHORT"). This is a required field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @return the sell type
   */
  @Element( name = "SELLTYPE", required = true, order = 20)
  public String getSellType() {
    return sellType;
  }

  /**
   * Sets the type of stock sale (i.e. "SELL" or "SELLSHORT"). This is a required field
   * according to the OFX spec.
   * @see "Section 13.9.2.4.4, OFX Spec"
   *
   * @param sellType the sell type
   */
  public void setSellType(String sellType) {
    this.sellType = sellType;
  }

  /**
   * Gets the sell type as one of the well-known types.
   *
   * @return the type of sale or null if it's not known
   */
  public SellType getSellTypeEnum() {
    return SellType.fromOfx(sellType);
  }
}
