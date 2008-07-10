package net.sf.ofx4j.domain.creditcard;

import net.sf.ofx4j.meta.Element;

/**
 * @author Ryan Heaton
 * 
 * @see "OFX Spec, Section 11.3.2"
 */
public class CreditCardAccountDetails {

  private String accountNumber;
  private String accountKey;

  /**
   * The account number.
   *
   * @return The account number.
   */
  @Element ( name = "ACCTID", required = true, order = 0 )
  public String getAccountNumber() {
    return accountNumber;
  }

  /**
   * The account number.
   *
   * @param accountNumber The account number.
   */
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * The account key.
   *
   * @return The account key.
   */
  @Element ( name = "ACCKEY", order = 10 )
  public String getAccountKey() {
    return accountKey;
  }

  /**
   * The account key.
   *
   * @param accountKey The account key.
   */
  public void setAccountKey(String accountKey) {
    this.accountKey = accountKey;
  }
}
