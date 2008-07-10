package net.sf.ofx4j.domain.data;

/**
 * A message set enclosed in a response envelope.
 *
 * @author Ryan Heaton
 */
public abstract class ResponseMessageSet implements Comparable<ResponseMessageSet> {

  private String version = "1";

  public abstract MessageSetType getType();

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public int compareTo(ResponseMessageSet o) {
    return getType().compareTo(o.getType());
  }

}