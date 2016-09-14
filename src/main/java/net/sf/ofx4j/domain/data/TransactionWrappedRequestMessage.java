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

import net.sf.ofx4j.meta.Element;

import java.util.UUID;

/**
 * A request message wrapped in a transaction.
 *
 * @author Ryan Heaton
 * @see "Section 2.4.6, OFX Spec"
 */
public abstract class TransactionWrappedRequestMessage<M extends RequestMessage> extends RequestMessage {

  private String UID;
  private String clientCookie;
  private String transactionAuthorizationNumber;

  public TransactionWrappedRequestMessage() {
    this.UID = UUID.randomUUID().toString();
  }

  public TransactionWrappedRequestMessage(String UID) {
    this.UID = UID;
  }

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
  @Element ( name = "CLTCOOKIE", order = 10 )
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

  /**
   * The transaction authorization number.
   *
   * @return The transaction authorization number.
   */
  @Element ( name = "TAN", order = 20 )
  public String getTransactionAuthorizationNumber() {
    return transactionAuthorizationNumber;
  }

  /**
   * The transaction authorization number.
   *
   * @param transactionAuthorizationNumber The transaction authorization number.
   */
  public void setTransactionAuthorizationNumber(String transactionAuthorizationNumber) {
    this.transactionAuthorizationNumber = transactionAuthorizationNumber;
  }

  /**
   * Set the wrapped message.
   *
   * @param message The wrapped message.
   */
  public abstract void setWrappedMessage(M message);

}