package net.sf.ofx4j.domain;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Header;

import java.util.SortedSet;

/**
 * Envelope for enclosing an OFX response.
 *
 * @author Ryan Heaton
 * @see "Section 2.4.3, OFX Spec"
 */
@Aggregate ( "OFX" )
public class ResponseEnvelope {

  //headers
  private ApplicationSecurity security;
  private String UID;

  //content
  private SortedSet<ResponseMessageSet> messageSets;

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
   * The message sets that make up the content of this response.
   *
   * @return The message sets that make up the content of this response.
   * @see "Section 2.4.5, OFX Spec"
   */
  @ChildAggregate ( order = 1 )
  public SortedSet<ResponseMessageSet> getMessageSets() {
    return messageSets;
  }

  /**
   * The message sets that make up the content of this response.
   *
   * @param messageSets The message sets that make up the content of this response.
   * @see "Section 2.4.5, OFX Spec"
   */
  public void setMessageSets(SortedSet<ResponseMessageSet> messageSets) {
    this.messageSets = messageSets;
  }

}