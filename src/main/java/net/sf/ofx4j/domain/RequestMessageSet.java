package net.sf.ofx4j.domain;

/**
 * A message set enclosed in an OFX request envelope.
 *
 * @author Ryan Heaton
 */
public abstract class RequestMessageSet implements Comparable<RequestMessageSet> {

  private String version = "1";

  public abstract MessageSetType getType();

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public int compareTo(RequestMessageSet o) {
    return getType().compareTo(o.getType());
  }
}
