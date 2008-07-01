package net.sf.ofx4j.domain;

import net.sf.ofx4j.meta.Header;
import net.sf.ofx4j.meta.Aggregate;

/**
 * Envelope for enclosing an OFX request.
 *
 * @author Ryan Heaton
 * @see "Section 2.4.3, OFX Spec"
 */
@Aggregate ("OFX")
public class RequestEnvelope {

  //headers
  private ApplicationSecurity security;
  private String UID;
  private String lastProcessedUID;

  //content
  

  /**
   * The security of this envelope.
   *
   * @return The security of this envelope.
   * @see "Section 2.2, OFX spec"
   */
  @Header ( name = "SECURITY", versions = {"100", "200"} )
  public ApplicationSecurity getSecurity() {
    return security;
  }

  /**
   * The security of this envelope.
   *
   * @param security The security of this envelope.
   * @see "Section 2.2, OFX spec"
   */
  public void setSecurity(ApplicationSecurity security) {
    this.security = security;
  }

  /**
   * The UID for the envelope.
   *
   * @return The UID for the envelope.
   * @see "Section 2.2, OFX spec"
   */
  @Header ( name = "NEWFILEUID", versions = {"100", "200"} )
  public String getUID() {
    return UID;
  }

  /**
   * The UID for the envelope.
   *
   * @param UID The UID for the envelope.
   * @see "Section 2.2, OFX spec"
   */
  public void setUID(String UID) {
    this.UID = UID;
  }

  /**
   * The UID of the last-processed request/response (used for file-based error recovery).
   *
   * @return The UID of the last-processed request/response (used for file-based error recovery).
   * @see "Section 2.2, OFX spec"
   */
  @Header ( name = "OLDFILEUID", versions = {"100", "200"} )
  public String getLastProcessedUID() {
    return lastProcessedUID;
  }

  /**
   * The UID of the last-processed request/response (used for file-based error recovery).
   *
   * @param lastProcessedUID The UID of the last-processed request/response (used for file-based error recovery).
   * @see "Section 2.2, OFX spec"
   */
  public void setLastProcessedUID(String lastProcessedUID) {
    this.lastProcessedUID = lastProcessedUID;
  }
}
