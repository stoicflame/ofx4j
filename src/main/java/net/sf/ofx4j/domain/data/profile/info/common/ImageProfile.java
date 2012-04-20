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

package net.sf.ofx4j.domain.data.profile.info.common;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Image Profile
 * @author Scott Priddy
 * @see "Section 3.1.6.2 OFX Spec"
 */
@Aggregate( "IMAGEPROF" )
public class ImageProfile {

  private Boolean closingImageAvailable;
  private Boolean transactionImageAvailable;

  @Element( name = "CLOSINGIMGAVAIL", required = true, order = 10 )
  public Boolean getClosingImageAvailable() {
    return closingImageAvailable;
  }

  public void setClosingImageAvailable(Boolean closingImageAvailable) {
    this.closingImageAvailable = closingImageAvailable;
  }

  @Element( name = "TRANIMGAVAIL", required = true, order = 20 )
  public Boolean getTransactionImageAvailable() {
    return transactionImageAvailable;
  }

  public void setTransactionImageAvailable(Boolean transactionImageAvailable) {
    this.transactionImageAvailable = transactionImageAvailable;
  }


}
