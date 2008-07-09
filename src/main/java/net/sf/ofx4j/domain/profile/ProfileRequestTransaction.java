package net.sf.ofx4j.domain.profile;

import net.sf.ofx4j.domain.TransactionWrappedRequestMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate( "PROFTRNRQ" )
public class ProfileRequestTransaction extends TransactionWrappedRequestMessage {

  private ProfileRequest message;

  @ChildAggregate ( required = true, order = 30 )
  public ProfileRequest getMessage() {
    return message;
  }

  public void setMessage(ProfileRequest message) {
    this.message = message;
  }
}
