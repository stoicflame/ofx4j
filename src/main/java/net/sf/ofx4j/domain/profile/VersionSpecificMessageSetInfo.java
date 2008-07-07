package net.sf.ofx4j.domain.profile;

import net.sf.ofx4j.meta.ChildAggregate;

/**
 * Information specific to a version of a message set.
 *
 * @author Ryan Heaton
 * @see "Section 7.2.1, OFX Spec"
 */
public abstract class VersionSpecificMessageSetInfo {

  private CoreMessageSetInfo core;

  /**
   * The information core.
   *
   * @return The information core.
   */
  @ChildAggregate ( order = 0 )
  public CoreMessageSetInfo getCore() {
    return core;
  }

  /**
   * The information core.
   *
   * @param core The information core.
   */
  public void setCore(CoreMessageSetInfo core) {
    this.core = core;
  }
}
