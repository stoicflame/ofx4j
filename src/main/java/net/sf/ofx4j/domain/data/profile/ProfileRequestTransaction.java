package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.domain.data.TransactionWrappedRequestMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate( "PROFTRNRQ" )
public class ProfileRequestTransaction extends TransactionWrappedRequestMessage<ProfileRequest> {

  private ProfileRequest message;

  /**
   * The wrapped message.
   *
   * @return The wrapped message.
   */
  @ChildAggregate ( required = true, order = 30 )
  public ProfileRequest getMessage() {
    return message;
  }

  /**
   * The wrapped message.
   *
   * @param message The wrapped message.
   */
  public void setMessage(ProfileRequest message) {
    this.message = message;
  }

  // Inherited.
  public void setWrappedMessage(ProfileRequest message) {
    setMessage(message);
  }
}
