package net.sf.ofx4j.domain;

import net.sf.ofx4j.domain.common.Status;
import net.sf.ofx4j.domain.common.StatusHolder;
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
  @Element ( value = "CLTCOOKIE", order = 20 )
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
  public String getResponseMessageName() {
    return getMessage() != null ? getMessage().getResponseMessageName() : null;
  }

  // Inherited.
  public String getStatusHolderName() {
    return getResponseMessageName();
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
   * The response message being wrapped in a transaction.
   *
   * @return The response message being wrapped in a transaction.
   */
  public abstract M getMessage();

}