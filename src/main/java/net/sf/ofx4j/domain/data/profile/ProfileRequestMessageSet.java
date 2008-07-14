package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.RequestMessageSet;
import net.sf.ofx4j.domain.data.RequestMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Ryan Heaton
 * @see "Section 7 OFX Spec"
 */
@Aggregate ( "PROFMSGSRQV1" )
public class ProfileRequestMessageSet extends RequestMessageSet {

  private ProfileRequestTransaction profileRequest;

  public MessageSetType getType() {
    return MessageSetType.profile;
  }

  /**
   * The profile request.
   *
   * @return The profile request.
   */
  @ChildAggregate ( required = true, order = 0 )
  public ProfileRequestTransaction getProfileRequest() {
    return profileRequest;
  }

  /**
   * The profile request.
   *
   * @param profileRequest The profile request.
   */
  public void setProfileRequest(ProfileRequestTransaction profileRequest) {
    this.profileRequest = profileRequest;
  }


  // Inherited.
  public List<RequestMessage> getRequestMessages() {
    ArrayList<RequestMessage> requestMessages = new ArrayList<RequestMessage>();
    if (getProfileRequest() != null) {
      requestMessages.add(getProfileRequest());
    }
    return requestMessages;
  }
}
