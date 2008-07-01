package net.sf.ofx4j.domain;

import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.domain.common.Status;

/**
 * A response message wrapped in a transaction.
 *
 * @author Ryan Heaton
 * @see "Section 2.4.6, OFX Spec"
 */
public class TransactionWrappedResponseMessage<M extends ResponseMessage> extends ResponseMessage {

  private String UID;
  private String clientCookie;
  private String transactionAuthorizationNumber;
  private Status status;

  private M message;

  /**
   * UID of this transaction.
   *
   * @return UID of this transaction.
   */
  @Element ( value = "TRNUID", required = true)
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
  @Element ("CLTCOOKIE")
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
  @Element ("TAN")
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
   * Status of the transaction.
   *
   * @return Status of the transaction.
   */
  @ChildAggregate (required = true)
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
   * The response message being wrapped in a transaction.
   *
   * @return The response message being wrapped in a transaction.
   */
  @ChildAggregate (required = true)
  public M getMessage() {
    return message;
  }

  /**
   * The response message being wrapped in a transaction.
   *
   * @param message The response message being wrapped in a transaction.
   */
  public void setMessage(M message) {
    this.message = message;
  }

}