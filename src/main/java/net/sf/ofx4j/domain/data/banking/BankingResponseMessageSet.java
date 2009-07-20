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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseMessage;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "BANKMSGSRSV1" )
public class BankingResponseMessageSet extends ResponseMessageSet {

  private List<BankStatementResponseTransaction> statementResponses;

  public MessageSetType getType() {
    return MessageSetType.banking;
  }

  /**
   * The statement response list.
   *
   * Most OFX files have a single statement response, except MT2OFX
   * which outputs OFX with multiple statement responses
   * in a single banking response message set.
   *
   * @return The statement response list.
   */
  @ChildAggregate ( order = 0 )
  public List<BankStatementResponseTransaction> getStatementResponses() {
    return statementResponses;
  }

  /**
   * The statement response.
   *
   * @param statementResponses The statement responses.
   */
  public void setStatementResponses(List<BankStatementResponseTransaction> statementResponses) {
    this.statementResponses = statementResponses;
  }

  // Inherited.
  public List<ResponseMessage> getResponseMessages() {
    return new ArrayList<ResponseMessage>(statementResponses);
  }

  /**
   * The first statement response.
   *
   * @return the first bank statement response.
   * @deprecated Use getStatementResponses() because sometimes there are multiple responses
   */
  public BankStatementResponseTransaction getStatementResponse() {
    return statementResponses == null || statementResponses.isEmpty() ? null : statementResponses.get(0);
  }

  public void setStatementResponse(BankStatementResponseTransaction statementResponse) {
    this.statementResponses = Collections.singletonList(statementResponse);
  }

}