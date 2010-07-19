/*
 * Copyright 2010 Web Cohesion
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

package net.sf.ofx4j.domain.data.investment.positions;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Represents an options position.
 * @see "Section 13.9.2.6.1, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "POSOPT" )
public class OptionsPosition extends BasePosition {
  private String secured;

  /**
   * Gets how the options position is secured (for short positions).
   *
   * @return how the options position is secured
   */
  @Element( name = "SECURED", order = 20)
  public String getSecured() {
    return secured;
  }

  /**
   * Sets how the options position is secured (for short positions).
   *
   * @param secured how the options position is secured
   */
  public void setSecured(String secured) {
    this.secured = secured;
  }

  /**
   * Gets how the options position is secured as a well-known type.
   *
   * @return how the option position is secured or null if it's not a well-known type
   */
  ShortOptionSecurity getSecuredEnum() {
    return ShortOptionSecurity.fromOfx(getSecured());
  }
}
