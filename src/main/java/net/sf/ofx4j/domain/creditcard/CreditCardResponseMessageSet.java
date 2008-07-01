package net.sf.ofx4j.domain.creditcard;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.ResponseMessageSet;

/**
 * @author Ryan Heaton
 */
public class CreditCardResponseMessageSet extends ResponseMessageSet {

  public MessageSetType getType() {
    return MessageSetType.creditcard;
  }
}