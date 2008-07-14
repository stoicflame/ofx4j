package net.sf.ofx4j.domain.data;

import java.util.List;

/**
 * A message set enclosed in a response envelope.
 *
 * @author Ryan Heaton
 */
public abstract class ResponseMessageSet implements Comparable<ResponseMessageSet> {

  private String version = "1";

  public abstract MessageSetType getType();

  /**
   * The version of this message set.
   *
   * @return The version of this message set.
   */
  public String getVersion() {
    return version;
  }

  /**
   * The version of this message set.
   *
   * @param version The version of this message set.
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * The list of response messages.
   *
   * @return The list of response messages.
   */
  public abstract List<ResponseMessage> getResponseMessages();

  // Inherited.
  public int compareTo(ResponseMessageSet o) {
    return getType().compareTo(o.getType());
  }

}