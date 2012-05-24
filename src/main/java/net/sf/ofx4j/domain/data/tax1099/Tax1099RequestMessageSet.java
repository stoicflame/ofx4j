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

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.RequestMessageSet;
import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;
import java.util.ArrayList;

/**
 * @author aparna.gawali
 * aparna.gawali@sungard.com
 * 
 */
@Aggregate ( "TAX1099MSGSRQV1" )
public class Tax1099RequestMessageSet extends RequestMessageSet {

  private Tax1099RequestTransaction taxRequestTransaction;

  public MessageSetType getType() {
    return MessageSetType.tax1099;
  }

  /**
   * The statement request.
   *
   * @return The statement request.
   */
  @ChildAggregate( order = 0 )
  public Tax1099RequestTransaction getTaxRequestTransaction() {
    return taxRequestTransaction;
  }

  /**
   * The statement request.
   *
   * @param taxRequestTransaction The statement request.
   */
  public void setTaxRequestTransaction(Tax1099RequestTransaction taxRequestTransaction) {
    this.taxRequestTransaction = taxRequestTransaction;
  }

  // Inherited.
  public List<RequestMessage> getRequestMessages() {
    ArrayList<RequestMessage> requestMessages = new ArrayList<RequestMessage>();
    if (getTaxRequestTransaction() != null) {
      requestMessages.add(getTaxRequestTransaction());
    }
    return requestMessages;
  }
}
