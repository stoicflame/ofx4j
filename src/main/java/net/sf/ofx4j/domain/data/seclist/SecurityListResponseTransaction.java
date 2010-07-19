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
package net.sf.ofx4j.domain.data.seclist;

import net.sf.ofx4j.domain.data.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * Security list transaction response.
 * @see "Section 13.8.3.1, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "SECLISTTRNRS")
public class SecurityListResponseTransaction
    extends TransactionWrappedResponseMessage<SecurityListResponse> {

  private SecurityListResponse message;

  /**
   * The message.
   *
   * @return The message.
   */
  @ChildAggregate( required = true, order = 30 )
  public SecurityListResponse getMessage() {
    return message;
  }

  /**
   * The message.
   *
   * @param message The message.
   */
  public void setMessage(SecurityListResponse message) {
    this.message = message;
  }

  // Inherited.
  public SecurityListResponse getWrappedMessage() {
    return getMessage();
  }
}
