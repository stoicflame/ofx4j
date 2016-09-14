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
