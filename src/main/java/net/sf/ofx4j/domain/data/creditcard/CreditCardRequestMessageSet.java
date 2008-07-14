package net.sf.ofx4j.domain.data.creditcard;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.RequestMessageSet;
import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "CREDITCARDMSGSRQV1" )
public class CreditCardRequestMessageSet extends RequestMessageSet {

  private CreditCardStatementRequestTransaction statementRequest;

  public MessageSetType getType() {
    return MessageSetType.creditcard;
  }

  /**
   * The request.
   *
   * @return The request.
   */
  @ChildAggregate (order = 0)
  public CreditCardStatementRequestTransaction getStatementRequest() {
    return statementRequest;
  }

  /**
   * The request.
   *
   * @param statementRequest The request.
   */
  public void setStatementRequest(CreditCardStatementRequestTransaction statementRequest) {
    this.statementRequest = statementRequest;
  }

  // Inherited.
  public List<RequestMessage> getRequestMessages() {
    ArrayList<RequestMessage> requestMessages = new ArrayList<RequestMessage>();
    if (getStatementRequest() != null) {
      requestMessages.add(getStatementRequest());
    }
    return requestMessages;
  }
}
