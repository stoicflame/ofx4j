package net.sf.ofx4j.domain.data.creditcard;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.domain.data.ResponseMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "CREDITCARDMSGSRSV1" )
public class CreditCardResponseMessageSet extends ResponseMessageSet {

  private CreditCardStatementResponseTransaction statementResponse;

  public MessageSetType getType() {
    return MessageSetType.creditcard;
  }

  /**
   * The statement response.
   *
   * @return The statement response.
   */
  @ChildAggregate( order = 0 )
  public CreditCardStatementResponseTransaction getStatementResponse() {
    return statementResponse;
  }

  /**
   * The statement response.
   *
   * @param statementResponse The statement response.
   */
  public void setStatementResponse(CreditCardStatementResponseTransaction statementResponse) {
    this.statementResponse = statementResponse;
  }


  // Inherited.
  public List<ResponseMessage> getResponseMessages() {
    ArrayList<ResponseMessage> messages = new ArrayList<ResponseMessage>();

    if (getStatementResponse() != null) {
      messages.add(getStatementResponse());
    }

    return messages;
  }
}