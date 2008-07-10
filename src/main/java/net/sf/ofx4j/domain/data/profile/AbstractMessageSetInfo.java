package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.meta.ChildAggregate;

import java.util.List;

/**
 * Information about a message set.
 *
 * @author Ryan Heaton
 * @see "Section 7.2.1, OFX Spec"
 */
public abstract class AbstractMessageSetInfo {

  private List<VersionSpecificMessageSetInfo> versionSpecificInformationList;

  /**
   * List of information about a message set for each version supported.
   *
   * @return List of information about a message set for each version supported.
   */
  @ChildAggregate ( order = 0 )
  protected List<VersionSpecificMessageSetInfo> getVersionSpecificInformationList() {
    return versionSpecificInformationList;
  }

  /**
   * List of information about a message set for each version supported.
   *
   * @param versionSpecificInformationList List of information about a message set for each version supported.
   */
  public void setVersionSpecificInformationList(List<VersionSpecificMessageSetInfo> versionSpecificInformationList) {
    this.versionSpecificInformationList = versionSpecificInformationList;
  }
}
