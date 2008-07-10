package net.sf.ofx4j.domain.banking;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * Base account details.  (Credit card, savings, checking, money market.)
 *
 * @author Ryan Heaton
 * @see "OFX Spec, Section 11.3.1"
 */
@Aggregate
public class BankAccountDetails {

  private String bankId;
  private String branchId;
  private String accountNumber;
  private AccountType accountType;
  private String accountKey;

  /**
   * The routing and transit number.
   *
   * @return The routing and transit number.
   */
  @Element ( name = "BANKID", required = true, order = 0 )
  public String getBankId() {
    return bankId;
  }

  /**
   * The routing and transit number.
   *
   * @param bankId The routing and transit number.
   */
  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  /**
   * The branch id.
   *
   * @return The branch id.
   */
  @Element ( name = "BRANCHID", order = 10 )
  public String getBranchId() {
    return branchId;
  }

  /**
   * The branch id.
   *
   * @param branchId The branch id.
   */
  public void setBranchId(String branchId) {
    this.branchId = branchId;
  }

  /**
   * The account number.
   *
   * @return The account number.
   */
  @Element ( name = "ACCTID", required = true, order = 20)
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
   * The account type.
   *
   * @return The account type.
   */
  @Element ( name = "ACCTTYPE", required = true, order = 30)
  public AccountType getAccountType() {
    return accountType;
  }

  /**
   * The account type.
   *
   * @param accountType The account type.
   */
  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  /**
   * The account key.
   *
   * @return The account key.
   */
  @Element( name = "ACCTKEY", order = 40 )
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
