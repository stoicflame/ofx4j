package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;

/**
 * List of signon information.
 *
 * @author Ryan Heaton
 * @see "Section 7.2.2, OFX Spec"
 */
@Aggregate ( "SIGNONINFOLIST" )
public class SignonInfoList {

  private List<SignonInfo> infoList;

  /**
   * List of sign-on information.
   *
   * @return List of sign-on information.
   */
  @ChildAggregate ( order = 0 )
  public List<SignonInfo> getInfoList() {
    return infoList;
  }

  /**
   * List of sign-on information.
   *
   * @param infoList List of sign-on information.
   */
  public void setInfoList(List<SignonInfo> infoList) {
    this.infoList = infoList;
  }
}
