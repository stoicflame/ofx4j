package net.sf.ofx4j.domain.profile;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.ResponseMessageSet;

/**
 * @author Ryan Heaton
 */
public class ProfileResponseMessageSet extends ResponseMessageSet {

  public MessageSetType getType() {
    return MessageSetType.profile;
  }
}