package net.sf.ofx4j.domain.profile;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;

/**
 * @author Ryan Heaton
 * @see "Section 7.2, OFX Spec"
 */
@Aggregate ( "MSGSETLIST" )
public class MessageSetInfoList {

  private List<MessageSetInfo> informationList;

  /**
   * The list of information for each message set.
   *
   * @return The list of information for each message set.
   */
  @ChildAggregate ( order = 0 )
  public List<MessageSetInfo> getInformationList() {
    return informationList;
  }

  /**
   * The list of information for each message set.
   *
   * @param informationList The list of information for each message set.
   */
  public void setInformationList(List<MessageSetInfo> informationList) {
    this.informationList = informationList;
  }
}
