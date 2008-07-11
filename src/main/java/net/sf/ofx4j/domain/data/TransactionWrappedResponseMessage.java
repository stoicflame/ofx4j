package net.sf.ofx4j.domain.data;

import net.sf.ofx4j.domain.data.common.Status;
import net.sf.ofx4j.domain.data.common.StatusHolder;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

/**
 * A response message wrapped in a transaction.
 *
 * @author Ryan Heaton
 * @see "Section 2.4.6, OFX Spec"
 */
public abstract class TransactionWrappedResponseMessage extends ResponseMessage implements StatusHolder {

  private String UID;
  private String clientCookie;
  private Status status;
  private final String messageName;

  protected TransactionWrappedResponseMessage(String messageName) {
    this.messageName = messageName;
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
    return this.messageName;
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

}