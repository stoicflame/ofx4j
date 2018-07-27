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

package com.webcohesion.ofx4j.domain.data.banking;

import com.webcohesion.ofx4j.domain.data.TransactionWrappedRequestMessage;
import com.webcohesion.ofx4j.meta.Aggregate;
import com.webcohesion.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ("STMTTRNRQ")
public class BankStatementRequestTransaction extends TransactionWrappedRequestMessage<BankStatementRequest> {

  private BankStatementRequest message;

  /**
   * The message.
   *
   * @return The message.
   */
  @ChildAggregate( required = false, order = 30 )
  public BankStatementRequest getMessage() {
    return message;
  }

  /**
   * The message.
   *
   * @param message The message.
   *
   */
  public void setMessage(BankStatementRequest message) {
    this.message = message;
  }

  // Inherited.
  public void setWrappedMessage(BankStatementRequest message) {
    setMessage(message);
  }
}
