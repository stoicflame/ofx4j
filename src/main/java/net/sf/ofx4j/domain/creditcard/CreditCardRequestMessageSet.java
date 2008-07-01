package net.sf.ofx4j.domain.creditcard;

import net.sf.ofx4j.domain.RequestMessageSet;
import net.sf.ofx4j.domain.MessageSetType;

/**
 * @author Ryan Heaton
 */
public class CreditCardRequestMessageSet extends RequestMessageSet {

  public MessageSetType getType() {
    return MessageSetType.creditcard;
  }
}
