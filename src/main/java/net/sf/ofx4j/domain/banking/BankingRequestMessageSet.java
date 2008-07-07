package net.sf.ofx4j.domain.banking;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.RequestMessageSet;

/**
 * @author Ryan Heaton
 */
public class BankingRequestMessageSet extends RequestMessageSet {

  public MessageSetType getType() {
    return MessageSetType.banking;
  }
}
