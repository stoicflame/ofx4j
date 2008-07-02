package net.sf.ofx4j.domain.profile;

import net.sf.ofx4j.domain.MessageSetType;
import net.sf.ofx4j.domain.RequestMessageSet;
import net.sf.ofx4j.domain.TransactionWrappedRequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 *
 * @see "Section 7 OFX Spec"
 */
@Aggregate ( "PROFMSGSRQV1" )
public class ProfileRequestMessageSet extends RequestMessageSet {

  private TransactionWrappedRequestMessage<ProfileRequest> profileRequest;

  public MessageSetType getType() {
    return MessageSetType.profile;
  }

  /**
   * The profile request.
   *
   * @return The profile request.
   */
  @ChildAggregate ( name = "PROFTRNRQ", required = true, order=0 )
  public TransactionWrappedRequestMessage<ProfileRequest> getProfileRequest() {
    return profileRequest;
  }

  /**
   * The profile request.
   *
   * @param profileRequest The profile request.
   */
  public void setProfileRequest(TransactionWrappedRequestMessage<ProfileRequest> profileRequest) {
    this.profileRequest = profileRequest;
  }
}
