package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.domain.data.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ("PROFTRNRS")
public class ProfileResponseTransaction extends TransactionWrappedResponseMessage<ProfileResponse> {

  private ProfileResponse message;

  /**
   * The message.
   *
   * @return The message.
   */
  @ChildAggregate ( required = true, order = 30 )
  public ProfileResponse getMessage() {
    return message;
  }

  /**
   * The message.
   *
   * @param message The message.
   */
  public void setMessage(ProfileResponse message) {
    this.message = message;
  }

  // Inherited.
  public ProfileResponse getWrappedMessage() {
    return getMessage();
  }
}