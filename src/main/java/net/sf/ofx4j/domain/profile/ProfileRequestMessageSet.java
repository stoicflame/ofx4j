package net.sf.ofx4j.domain.profile;

import net.sf.ofx4j.domain.RequestMessageSet;
import net.sf.ofx4j.domain.MessageSetType;

/**
 * @author Ryan Heaton
 */
public class ProfileRequestMessageSet extends RequestMessageSet {

  public MessageSetType getType() {
    return MessageSetType.profile;
  }
}
