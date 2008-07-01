package net.sf.ofx4j.domain.banking;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.ResponseMessageSet;

/**
 * @author Ryan Heaton
 */
public class BankingResponseMessageSet extends ResponseMessageSet {

  public MessageSetType getType() {
    return MessageSetType.banking;
  }
}