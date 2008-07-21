/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.ofx4j.client.main;

import net.sf.ofx4j.client.*;
import net.sf.ofx4j.client.impl.LocalResourceFIDataStore;
import net.sf.ofx4j.client.impl.FinancialInstitutionImpl;
import net.sf.ofx4j.domain.data.banking.AccountType;
import net.sf.ofx4j.domain.data.banking.BankAccountDetails;
import net.sf.ofx4j.domain.data.creditcard.CreditCardAccountDetails;
import net.sf.ofx4j.client.net.OFXV1Connection;
import net.sf.ofx4j.io.v1.OFXV1Writer;
import net.sf.ofx4j.io.AggregateMarshaller;
import net.sf.ofx4j.io.OFXWriter;
import net.sf.ofx4j.io.v2.OFXV2Writer;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.ExampleMode;

import java.util.Date;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

/**
 * Downloads a statement request.
 *
 * @author Ryan Heaton
 */
public class DownloadStatement {

  public enum FinancialInstitutionAccountType {

    banking,

    creditcard

  }

  @Option ( name = "-s", aliases = "-start", usage = "The start date (yyyy-MM-dd)." )
  private String startDateValue;
  @Option ( name = "-e", aliases = "-end", usage = "The end date (yyyy-MM-dd)." )
  private String endDateValue;
  @Option ( name = "-f", aliases = "-fid", required = true, usage = "The financial institution id. (Look it up at http://www.ofxhome.com/index.php/home/directory)" )
  private String fid;
  @Option ( name = "-t", aliases = "-account-type", required = true, usage = "The account type." )
  private FinancialInstitutionAccountType accountType;
  @Option ( name = "-u", aliases = "-username", required = true, usage = "The username of the user with an account." )
  private String username;
  @Option ( name = "-p", aliases = "-password", required = true, usage = "The password of the user with an account." )
  private String password;
  @Option ( name = "-n", aliases = "-account-number", required = true, usage = "The account number." )
  private String accountNumber;
  @Option ( name = "-b", aliases = "-bank-account-type", usage = "The banking account type (ignored for credit card accounts)." )
  private AccountType bankAccountType;
  @Option ( name = "-r", aliases = {"-routing-number", "-bank-id"}, usage = "The routing number (i.e. bank id)." )
  private String routingNumber;
  @Option ( name = "-o", aliases = "-out", usage = "The file to write the statement to." )
  private File out;
  @Option ( name = "-2", aliases = "-v2", usage = "Whether to print the statement in OFX version 2." )
  private boolean v2 = false;

  public void doMain(String[] args) throws Exception {
    CmdLineParser parser = new CmdLineParser(this);
    parser.setUsageWidth(120);

    try {
      parser.parseArgument(args);

      FinancialInstitutionDataStore dataStore = new LocalResourceFIDataStore();
      FinancialInstitutionData data = null;
      for (FinancialInstitutionData item : dataStore.getInstitutionDataList()) {
        if (fid.equals(item.getFinancialInstitutionId())) {
          data = item;
          break;
        }
      }
      if (data == null) {
        exit("Unknown financial institution: " + fid);
      }

      OFXV1Connection connection = new OFXV1Connection();
      FinancialInstitution fi = new FinancialInstitutionImpl(data, connection);
      FinancialInstitutionAccount account;

      switch (accountType) {
        case banking:
          if (bankAccountType == null) {
            throw new CmdLineException("A bank account type must be specified.");
          }
          if (routingNumber == null) {
            throw new CmdLineException("A routing number must be specified.");
          }

          BankAccountDetails details = new BankAccountDetails();
          details.setAccountNumber(accountNumber);
          details.setAccountType(bankAccountType);
          details.setBankId(routingNumber);
          account = fi.loadBankAccount(details, username, password);
          break;
        case creditcard:
          CreditCardAccountDetails ccDetails = new CreditCardAccountDetails();
          ccDetails.setAccountNumber(accountNumber);
          account = fi.loadCreditCardAccount(ccDetails, username, password);
          break;
        default:
          throw new CmdLineException("Invalid institution account type: " + accountType);
      }

      Date endDate = new Date();
      if (endDateValue != null) {
        endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateValue);
      }

      Date startDate;
      if (startDateValue != null) {
        //default start date is 4 weeks before the end date.
        startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateValue);
      }
      else {
        startDate  = new Date(endDate.getTime() - (4L * 7 * 24 * 60 * 60 * 1000));
      }

      AccountStatement statement = account.readStatement(startDate, endDate);
      AggregateMarshaller marshaller = new AggregateMarshaller();
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      OFXWriter writer = new OFXV1Writer(bytes);
      ((OFXV1Writer)writer).setWriteAttributesOnNewLine(true);
      if (v2) {
        writer = new OFXV2Writer(bytes);
      }
      marshaller.marshal(statement, writer);
      writer.close();
      System.out.println(bytes.toString());
      System.out.flush();
      
      if (out != null) {
        FileOutputStream stream = new FileOutputStream(out);
        stream.write(bytes.toByteArray());
        stream.flush();
        stream.close();
      }
    }
    catch (CmdLineException e) {
      invalidArgs(parser, e);
    }
  }

  private void invalidArgs(CmdLineParser parser, CmdLineException e) {
    System.err.println(e.getMessage());
    System.err.println("java DownloadStatement [options...] arguments...");
    // print the list of available options
    parser.printUsage(System.err);
    System.err.println();

    // print option sample. This is useful some time
    System.err.println("  Example: java DownloadStatement " + parser.printExample(ExampleMode.ALL));

    System.exit(1);
  }

  private void exit(String message) {
    System.out.println(message);
    System.exit(1);
  }

  public static void main(String[] args) throws Exception {
    new DownloadStatement().doMain(args);
  }

}
