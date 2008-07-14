package net.sf.ofx4j.client;

import net.sf.ofx4j.OFXException;

import java.util.Date;

/**
 * A specific account at a financial institution.
 *
 * @author Ryan Heaton
 */
public interface FinancialInstitutionAccount {

  /**
   * Read an account statement.
   *
   * @param start The start date of the statement.
   * @param end The end date of the statement.
   * @return The account statement.
   */
  AccountStatement readStatement(Date start, Date end) throws OFXException;
}
