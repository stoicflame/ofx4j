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

import net.sf.ofx4j.domain.data.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Aparna Gawali
 * aparna.gawali@sungard.com
 */
@Aggregate ( "TAX1099TRNRS")
public class Tax1099ResponseTransaction extends TransactionWrappedResponseMessage<Tax1099Response> {

  private Tax1099Response tax1099Response;

  /**
   * The tax1099Response.
   *
   * @return The tax1099Response.
   */
  @ChildAggregate(required=false, order = 2)
  public Tax1099Response getTax1099Response() {
    return tax1099Response;
  }

  /**
   * The tax1099Response.
   *
   * @param tax1099Response The message.
   */
  public void setTax1099Response(Tax1099Response tax1099Response) {
    this.tax1099Response = tax1099Response;
  }

  // Inherited.
  public Tax1099Response getWrappedMessage() {
    return getTax1099Response();
  }
}
