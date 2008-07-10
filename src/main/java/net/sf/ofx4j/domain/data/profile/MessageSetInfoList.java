package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;

/**
 * @author Ryan Heaton
 * @see "Section 7.2, OFX Spec"
 */
@Aggregate ( "MSGSETLIST" )
public class MessageSetInfoList {

  private List<AbstractMessageSetInfo> informationList;

  /**
   * The list of information for each message set.
   *
   * @return The list of information for each message set.
   */
  @ChildAggregate ( order = 0 )
  public List<AbstractMessageSetInfo> getInformationList() {
    return informationList;
  }

  /**
   * The list of information for each message set.
   *
   * @param informationList The list of information for each message set.
   */
  public void setInformationList(List<AbstractMessageSetInfo> informationList) {
    this.informationList = informationList;
  }
}
