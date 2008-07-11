package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 * @see "Section 7 OFX Spec"
 */
@Aggregate ("PROFMSGSRSV1")
public class ProfileResponseMessageSet extends ResponseMessageSet {

  private ProfileResponseTransaction profileResponse;

  public MessageSetType getType() {
    return MessageSetType.profile;
  }

  /**
   * The profile response.
   *
   * @return The profile response.
   */
  @ChildAggregate ( required = true, order = 0 )
  public ProfileResponseTransaction getProfileResponse() {
    return profileResponse;
  }

  /**
   * The profile response.
   *
   * @param profileResponse The profile response.
   */
  public void setProfileResponse(ProfileResponseTransaction profileResponse) {
    this.profileResponse = profileResponse;
  }
}