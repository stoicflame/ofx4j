package net.sf.ofx4j.domain.profile.info;

import net.sf.ofx4j.domain.profile.VersionSpecificMessageSetInfo;
import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "EMAILMSGSETV1" )
public class EmailV1MessageSetInfo extends VersionSpecificMessageSetInfo {

  public MessageSetType getMessageSetType() {
    return MessageSetType.email;
  }
}