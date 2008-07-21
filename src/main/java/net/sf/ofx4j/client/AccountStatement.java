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

package net.sf.ofx4j.client;

import net.sf.ofx4j.domain.data.common.BalanceInfo;
import net.sf.ofx4j.domain.data.common.TransactionList;

/**
 * @author Ryan Heaton
 */
public interface AccountStatement {

  /**
   * The currency code.
   *
   * @return The currency code.
   * @see java.util.Currency#getCurrencyCode()
   */
  String getCurrencyCode();

  /**
   * The transaction list.
   *
   * @return The transaction list.
   */
  TransactionList getTransactionList();

  /**
   * The ledger balance.
   *
   * @return The ledger balance.
   */
  BalanceInfo getLedgerBalance();

  /**
   * The available balance.
   *
   * @return The available balance.
   */
  BalanceInfo getAvailableBalance();

}
