package net.sf.ofx4j.domain.data.profile.info;

import net.sf.ofx4j.domain.data.profile.AbstractMessageSetInfo;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "BANKMSGSET" )
public class BankingMessageSetInfo extends AbstractMessageSetInfo {

  private BankingV1MessageSetInfo version1Info;

  @ChildAggregate ( order = 0 )
  public BankingV1MessageSetInfo getVersion1Info() {
    return version1Info;
  }

  public void setVersion1Info(BankingV1MessageSetInfo version1Info) {
    this.version1Info = version1Info;
  }
}