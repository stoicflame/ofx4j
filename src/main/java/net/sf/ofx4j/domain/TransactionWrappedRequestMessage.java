package net.sf.ofx4j.domain;

import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * A request message wrapped in a transaction.
 *
 * @author Ryan Heaton
 * @see "Section 2.4.6, OFX Spec"
 */
public class TransactionWrappedRequestMessage<M extends RequestMessage> extends RequestMessage {

  private String UID;
  private String clientCookie;
  private String transactionAuthorizationNumber;

  private M message;

  /**
   * UID of this transaction.
   *
   * @return UID of this transaction.
   */
  @Element ( value = "TRNUID", required = true, order = 0 )
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
  @Element ( value = "CLTCOOKIE", order = 10 )
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
  @Element ( value = "TAN", order = 20 )
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
   * The request message being wrapped in a transaction.
   *
   * @return The request message being wrapped in a transaction.
   */
  @ChildAggregate ( required = true, order = 30 )
  public M getMessage() {
    return message;
  }

  /**
   * The request message being wrapped in a transaction.
   *
   * @param message The request message being wrapped in a transaction.
   */
  public void setMessage(M message) {
    this.message = message;
  }
}