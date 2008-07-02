package net.sf.ofx4j.domain.profile;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.domain.RequestMessage;

import java.util.Date;

/**
 * @author Ryan Heaton
 * @see "Section 7.1.5, OFX Spec"
 */
@Aggregate("PROFRQ")
public class ProfileRequest extends RequestMessage {

  private ClientRoutingCapability routingCapability = ClientRoutingCapability.NONE;
  private Date profileLastUpdated;

  /**
   * The client routing capability.
   *
   * @return The client routing capability.
   */
  @Element ("CLIENTROUTING", order=0)
  public ClientRoutingCapability getRoutingCapability() {
    return routingCapability;
  }

  /**
   * The client routing capability.
   *
   * @param routingCapability The client routing capability.
   */
  public void setRoutingCapability(ClientRoutingCapability routingCapability) {
    this.routingCapability = routingCapability;
  }

  /**
   * The date the profile was last updated.
   *
   * @return The date the profile was last updated.
   */
  @Element("DTPROFUP", order=10)
  public Date getProfileLastUpdated() {
    return profileLastUpdated;
  }

  /**
   * The date the profile was last updated.
   *
   * @param profileLastUpdated The date the profile was last updated.
   */
  public void setProfileLastUpdated(Date profileLastUpdated) {
    this.profileLastUpdated = profileLastUpdated;
  }
}
