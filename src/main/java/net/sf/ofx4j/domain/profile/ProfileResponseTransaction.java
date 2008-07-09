package net.sf.ofx4j.domain.profile;

import net.sf.ofx4j.domain.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ("PROFTRNRS")
public class ProfileResponseTransaction extends TransactionWrappedResponseMessage<ProfileResponse> {

  private ProfileResponse message;

  @ChildAggregate ( required = true, order = 30 )
  public ProfileResponse getMessage() {
    return message;
  }

  public void setMessage(ProfileResponse message) {
    this.message = message;
  }
}