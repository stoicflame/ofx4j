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

package net.sf.ofx4j.domain.data.common;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "XFERPRCSTS" )
public class TransferStatus {

  private TransferStatusEvent event;
  private Date date;

  /**
   * The event.
   *
   * @return The event.
   */
  @Element ( name = "XFERPRCCODE", required = true, order = 0 )
  public TransferStatusEvent getEvent() {
    return event;
  }

  /**
   * The event.
   *
   * @param event The event.
   */
  public void setEvent(TransferStatusEvent event) {
    this.event = event;
  }

  /**
   * The date of the event.
   *
   * @return The date of the event.
   */
  @Element ( name = "DTXFERPRC", required = true, order = 10 )
  public Date getDate() {
    return date;
  }

  /**
   * The date of the event.
   *
   * @param date The date of the event.
   */
  public void setDate(Date date) {
    this.date = date;
  }
}
