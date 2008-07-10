package net.sf.ofx4j.domain.creditcard;

import net.sf.ofx4j.domain.common.StatementRequest;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;

/**
 * @author Ryan Heaton
 */
@Aggregate("CCSTMTRQ")
public class CreditCardStatementRequest extends StatementRequest {

  private CreditCardAccountDetails account;

  /**
   * The account details.
   *
   * @return The account details.
   */
  @ChildAggregate ( name = "BANKACCTFROM", required = true, order = 0 )
  public CreditCardAccountDetails getAccount() {
    return account;
  }

  /**
   * The account details.
   *
   * @param account The account details.
   */
  public void setAccount(CreditCardAccountDetails account) {
    this.account = account;
  }

}