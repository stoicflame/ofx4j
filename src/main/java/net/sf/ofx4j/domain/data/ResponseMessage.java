package net.sf.ofx4j.domain.data;

/**
 * A message applicable to a response message set.
 *
 * @author Ryan Heaton
 */
public abstract class ResponseMessage {

  /**
   * The name of the response message.
   *
   * @return The name of the response message.
   */
  public abstract String getResponseMessageName();

}