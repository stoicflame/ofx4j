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

package net.sf.ofx4j.domain.data.common;

import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "STMTRQ" )
public class StatementRequest extends RequestMessage {

  private StatementRange statementRange;

  /**
   * The statement range.
   *
   * @return The statement range.
   */
  @ChildAggregate ( name = "INCTRAN", required = false, order = 10 )
  public StatementRange getStatementRange() {
    return statementRange;
  }

  /**
   * The statement range.
   *
   * @param statementRange The statement range.
   */
  public void setStatementRange(StatementRange statementRange) {
    this.statementRange = statementRange;
  }
}
