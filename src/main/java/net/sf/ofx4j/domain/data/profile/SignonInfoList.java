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
