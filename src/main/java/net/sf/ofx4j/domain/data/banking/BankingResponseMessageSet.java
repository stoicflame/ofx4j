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

package net.sf.ofx4j.domain.data.banking;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.domain.data.ResponseMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "BANKMSGSRSV1" )
public class BankingResponseMessageSet extends ResponseMessageSet {

  private BankStatementResponseTransaction statementResponse;

  public MessageSetType getType() {
    return MessageSetType.banking;
  }

  /**
   * The statement response.
   *
   * @return The statement response.
   */
  @ChildAggregate ( order = 0 )
  public BankStatementResponseTransaction getStatementResponse() {
    return statementResponse;
  }

  /**
   * The statement response.
   *
   * @param statementResponse The statement response.
   */
  public void setStatementResponse(BankStatementResponseTransaction statementResponse) {
    this.statementResponse = statementResponse;
  }

  // Inherited.
  public List<ResponseMessage> getResponseMessages() {
    ArrayList<ResponseMessage> messages = new ArrayList<ResponseMessage>();

    if (getStatementResponse() != null) {
      messages.add(getStatementResponse());
    }
    
    return messages;
  }
}