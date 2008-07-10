package net.sf.ofx4j.domain.data.profile.info;

import net.sf.ofx4j.domain.data.profile.VersionSpecificMessageSetInfo;
import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "CREDITCARDMSGSETV1" )
public class CreditCardV1MessageSetInfo extends VersionSpecificMessageSetInfo {
  
  public MessageSetType getMessageSetType() {
    return MessageSetType.creditcard;
  }
}