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

import net.sf.ofx4j.domain.data.TransactionWrappedResponseMessage;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ("PROFTRNRS")
public class ProfileResponseTransaction extends TransactionWrappedResponseMessage<ProfileResponse> {

  private ProfileResponse message;

  /**
   * The message.
   *
   * @return The message.
   */
  @ChildAggregate ( required = true, order = 30 )
  public ProfileResponse getMessage() {
    return message;
  }

  /**
   * The message.
   *
   * @param message The message.
   */
  public void setMessage(ProfileResponse message) {
    this.message = message;
  }

  // Inherited.
  public ProfileResponse getWrappedMessage() {
    return getMessage();
  }
}