package net.sf.ofx4j.domain.profile.info;

import net.sf.ofx4j.domain.profile.VersionSpecificMessageSetInfo;
import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "SECLISTMSGSETV1" )
public class SecurityListV1MessageSetInfo extends VersionSpecificMessageSetInfo {

  public MessageSetType getMessageSetType() {
    return MessageSetType.investment_security;
  }
}