/*
 * Copyright 2012 TheStash
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

package net.sf.ofx4j.domain.data.profile.info.banking;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Email Profile
 * @author Scott Priddy
 * @see "Section 11.13.2.3 OFX Spec"
 */
@Aggregate( "EMAILPROF")
public class EmailProfile {

  private Boolean canEmail;
  private Boolean canNotify;

  @Element( name = "CANEMAIL", required = true, order = 10 )
  public Boolean getCanEmail() {
    return canEmail;
  }

  public void setCanEmail(Boolean canEmail) {
    this.canEmail = canEmail;
  }

  @Element( name = "CANNOTIFY", required = true, order = 20 )
  public Boolean getCanNotify() {
    return canNotify;
  }

  public void setCanNotify(Boolean canNotify) {
    this.canNotify = canNotify;
  }


}
