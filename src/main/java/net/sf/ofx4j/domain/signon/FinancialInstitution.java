package net.sf.ofx4j.domain.signon;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "FI" )
public class FinancialInstitution {

  private String id;
  private String organization;

  /**
   * Financial institution id.
   *
   * @return Financial institution id.
   */
  @Element ( name = "FID", order = 10 )
  public String getId() {
    return id;
  }

  /**
   * Financial institution id.
   *
   * @param id Financial institution id.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * The organization.
   *
   * @return The organization.
   */
  @Element ( name = "ORG", required = true, order = 0 )
  public String getOrganization() {
    return organization;
  }

  /**
   * The organization.
   *
   * @param organization The organization.
   */
  public void setOrganization(String organization) {
    this.organization = organization;
  }
}
