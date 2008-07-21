/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.ofx4j.domain.data.profile;

import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.domain.data.MessageSetProfile;
import net.sf.ofx4j.domain.data.ApplicationSecurity;
import net.sf.ofx4j.domain.data.MessageSetType;

/**
 * Information specific to a version of a message set.
 *
 * @author Ryan Heaton
 * @see "Section 7.2.1, OFX Spec"
 */
public abstract class VersionSpecificMessageSetInfo implements MessageSetProfile {

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

  /**
   * The message set type.
   *
   * @return The message set type.
   */
  public abstract MessageSetType getMessageSetType();

  public String getVersion() {
    return core != null ? core.getVersion() : null;
  }

  public String getServiceProviderName() {
    return core != null ? core.getServiceProviderName() : null;
  }

  public String getUrl() {
    return core != null ? core.getUrl() : null;
  }

  public ApplicationSecurity getSecurity() {
    return core != null ? core.getSecurity() : null;
  }

  public boolean isSslRequired() {
    return core != null && core.getSslRequired() != null ? core.getSslRequired() : true;
  }

  public String getRealm() {
    return core != null ? core.getRealm() : null;
  }

  public String getLanguage() {
    return core != null ? core.getLanguage() : null;
  }

  public SynchronizationCapability getSyncCapability() {
    return core != null ? core.getSyncCapability() : null;
  }

  public boolean hasFileBasedErrorRecoverySupport() {
    return core != null && core.getFileBasedErrorRecoverySupport() != null ? core.getFileBasedErrorRecoverySupport() : false;
  }
}
