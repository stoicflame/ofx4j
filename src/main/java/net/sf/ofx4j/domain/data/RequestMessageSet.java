package net.sf.ofx4j.domain.data;

import java.util.List;

/**
 * A message set enclosed in an OFX request envelope.
 *
 * @author Ryan Heaton
 */
public abstract class RequestMessageSet implements Comparable<RequestMessageSet> {

  private String version = "1";

  public abstract MessageSetType getType();

  /**
   * The version of this request message.
   *
   * @return The version of this request message.
   */
  public String getVersion() {
    return version;
  }

  /**
   * The version of this request message.
   *
   * @param version The version of this request message.
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * The request messages for this request message set.
   *
   * @return The request messages for this request message set.
   */
  public abstract List<RequestMessage> getRequestMessages();

  // Inherited.
  public int compareTo(RequestMessageSet o) {
    return getType().compareTo(o.getType());
  }
}
