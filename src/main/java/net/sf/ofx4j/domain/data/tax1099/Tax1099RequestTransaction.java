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

import net.sf.ofx4j.domain.data.TransactionWrappedRequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Aparna Gawali
 * aparna.gawali@sungard.com
 */
@Aggregate ("TAX1099TRNRQ")
public class Tax1099RequestTransaction extends TransactionWrappedRequestMessage<Tax1099Request> {

  private Tax1099Request tax1099Request;

  /**
   * The tax1099Request.
   *
   * @return The tax1099Request.
   */
  @ChildAggregate( required = true, order = 30 )
  public Tax1099Request getTax1099Request() {
    return tax1099Request;
  }

  /**
   * The tax1099Request.
   *
   * @param tax1099Request The message.
   *
   */
  public void setTax1099Request(Tax1099Request tax1099Request) {
    this.tax1099Request = tax1099Request;
  }

  // Inherited.
  public void setWrappedMessage(Tax1099Request tax1099Request) {
	  setTax1099Request(tax1099Request);
  }
}
