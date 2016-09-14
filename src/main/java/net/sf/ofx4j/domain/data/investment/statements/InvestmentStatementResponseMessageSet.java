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

package net.sf.ofx4j.domain.data.investment.statements;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseMessage;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Investment statement response message set.
 * @see "Section 13.7.1.2.2, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "INVSTMTMSGSRSV1" )
public class InvestmentStatementResponseMessageSet extends ResponseMessageSet {

  private List<InvestmentStatementResponseTransaction> statementResponses;

  public MessageSetType getType() {
    return MessageSetType.investment;
  }

  /**
   * Gets the statement response list. Most OFX files have a single statement response.
   *
   * @return the statement response list
   */
  @ChildAggregate( order = 0 )
  public List<InvestmentStatementResponseTransaction> getStatementResponses() {
    return statementResponses;
  }


  /**
   * Sets the statement reponse list. Most OFX files have a single statement response.
   *
   * @param statementResponses the statement response list
   */
  public void setStatementResponses(
      List<InvestmentStatementResponseTransaction> statementResponses) {
    this.statementResponses = statementResponses;
  }


  /**
   * Gets the first statement response. Use getStatementResponses() if you are expecting multiple
   * responses.
   *
   * @return the first investment statement response.
   */
  public InvestmentStatementResponseTransaction getStatementResponse() {
    return statementResponses == null || statementResponses.isEmpty() ? null : statementResponses.get(0);
  }

  /**
   * Sets the statement response if there is a single response.
   *
   * @param statementResponse The statement response.
   */
  public void setStatementResponse(InvestmentStatementResponseTransaction statementResponse) {
    this.statementResponses = Collections.singletonList(statementResponse);
  }

  // Inherited.
  public List<ResponseMessage> getResponseMessages() {
    return new ArrayList<ResponseMessage>(statementResponses);
  }
}
