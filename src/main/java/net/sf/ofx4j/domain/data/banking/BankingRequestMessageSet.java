package net.sf.ofx4j.domain.data.banking;

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
@Aggregate ( "BANKMSGSRQV1" )
public class BankingRequestMessageSet extends RequestMessageSet {

  private BankStatementRequestTransaction statementRequest;

  public MessageSetType getType() {
    return MessageSetType.banking;
  }

  /**
   * The statement request.
   *
   * @return The statement request.
   */
  @ChildAggregate( order = 0 )
  public BankStatementRequestTransaction getStatementRequest() {
    return statementRequest;
  }

  /**
   * The statement request.
   *
   * @param statementRequest The statement request.
   */
  public void setStatementRequest(BankStatementRequestTransaction statementRequest) {
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
