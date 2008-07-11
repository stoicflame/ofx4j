package net.sf.ofx4j.domain.data.profile.info;

import net.sf.ofx4j.domain.data.profile.AbstractMessageSetInfo;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "SECLISTMSGSET" )
public class SecurityListMessageSetInfo extends AbstractMessageSetInfo {

  private SecurityListV1MessageSetInfo version1Info;

  @ChildAggregate ( order = 0 )
  public SecurityListV1MessageSetInfo getVersion1Info() {
    return version1Info;
  }

  public void setVersion1Info(SecurityListV1MessageSetInfo version1Info) {
    this.version1Info = version1Info;
  }
}