package net.sf.ofx4j.domain.data.banking;

import net.sf.ofx4j.domain.data.common.StatementResponse;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate ("STMTRS")
public class BankStatementResponse extends StatementResponse {

  private BankAccountDetails account;

  /**
   * The account for the statement.
   *
   * @return The account for the statement.
   */
  @ChildAggregate ( name ="BANKACCTFROM", order = 10)
  public BankAccountDetails getAccount() {
    return account;
  }

  /**
   * The account for the statement.
   *
   * @param account The account for the statement.
   */
  public void setAccount(BankAccountDetails account) {
    this.account = account;
  }

}
