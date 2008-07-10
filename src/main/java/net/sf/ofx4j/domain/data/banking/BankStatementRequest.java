package net.sf.ofx4j.domain.data.banking;

import net.sf.ofx4j.domain.data.common.StatementRequest;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Aggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate("STMTRQ")
public class BankStatementRequest extends StatementRequest {

  private BankAccountDetails account;

  /**
   * The account details.
   *
   * @return The account details.
   */
  @ChildAggregate ( name = "BANKACCTFROM", required = true, order = 0 )
  public BankAccountDetails getAccount() {
    return account;
  }

  /**
   * The account details.
   *
   * @param account The account details.
   */
  public void setAccount(BankAccountDetails account) {
    this.account = account;
  }

}
