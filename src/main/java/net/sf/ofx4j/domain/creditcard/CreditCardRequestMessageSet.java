package net.sf.ofx4j.domain.creditcard;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.RequestMessageSet;

/**
 * @author Ryan Heaton
 */
public class CreditCardRequestMessageSet extends RequestMessageSet {

  public MessageSetType getType() {
    return MessageSetType.creditcard;
  }
}
