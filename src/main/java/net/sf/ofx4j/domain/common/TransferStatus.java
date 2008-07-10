package net.sf.ofx4j.domain.common;

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
