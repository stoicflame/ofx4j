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

package net.sf.ofx4j.domain.data;

import net.sf.ofx4j.domain.data.common.Status;
import net.sf.ofx4j.domain.data.common.StatusHolder;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.meta.Aggregate;

/**
 * A response message wrapped in a transaction.
 *
 * @author Ryan Heaton
 * @see "Section 2.4.6, OFX Spec"
 */
public abstract class TransactionWrappedResponseMessage<M extends ResponseMessage> extends ResponseMessage implements StatusHolder {

  private String UID;
  private String clientCookie;
  private Status status;

  /**
   * UID of this transaction.
   *
   * @return UID of this transaction.
   */
  @Element ( name = "TRNUID", required = true, order = 0 )
  public String getUID() {
    return UID;
  }

  /**
   * UID of this transaction.
   *
   * @param UID UID of this transaction.
   */
  public void setUID(String UID) {
    this.UID = UID;
  }

  /**
   * Client cookie (echoed back by the response).
   *
   * @return Client cookie (echoed back by the response).
   */
  @Element ( name = "CLTCOOKIE", order = 20 )
  public String getClientCookie() {
    return clientCookie;
  }

  /**
   * Client cookie (echoed back by the response).
   *
   * @param clientCookie Client cookie (echoed back by the response).
   */
  public void setClientCookie(String clientCookie) {
    this.clientCookie = clientCookie;
  }

  // Inherited.
  public String getStatusHolderName() {
    return getResponseMessageName();
  }

  // Inherited.
  public String getResponseMessageName() {
    String name = "transaction response";
    if (getWrappedMessage() != null) {
      name = getWrappedMessage().getResponseMessageName() + " transaction";
    }
    else if (getClass().isAnnotationPresent(Aggregate.class)) {
      name = getClass().getAnnotation(Aggregate.class).value() + " transaction";
    }

    return name;
  }

  /**
   * Status of the transaction.
   *
   * @return Status of the transaction.
   */
  @ChildAggregate ( required = true, order = 10 )
  public Status getStatus() {
    return status;
  }

  /**
   * Status of the transaction.
   *
   * @param status Status of the transaction.
   */
  public void setStatus(Status status) {
    this.status = status;
  }

  /**
   * Get the wrapped message.
   *
   * @return The wrapped message.
   */
  public abstract M getWrappedMessage();

}