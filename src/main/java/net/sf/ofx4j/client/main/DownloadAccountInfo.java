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

import net.sf.ofx4j.client.FinancialInstitution;
import net.sf.ofx4j.client.FinancialInstitutionData;
import net.sf.ofx4j.client.FinancialInstitutionDataStore;
import net.sf.ofx4j.client.impl.FinancialInstitutionImpl;
import net.sf.ofx4j.client.impl.LocalResourceFIDataStore;
import net.sf.ofx4j.client.net.OFXV1Connection;
import net.sf.ofx4j.domain.data.signup.AccountInfoResponse;
import net.sf.ofx4j.domain.data.signup.AccountProfile;
import net.sf.ofx4j.io.AggregateMarshaller;
import net.sf.ofx4j.io.OFXWriter;
import net.sf.ofx4j.io.v1.OFXV1Writer;
import net.sf.ofx4j.io.v2.OFXV2Writer;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ExampleMode;
import org.kohsuke.args4j.Option;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

/**
 * Downloads account information.
 *
 * @author Ryan Heaton
 */
public class DownloadAccountInfo {

  public enum FinancialInstitutionAccountType {

    banking,

    creditcard

  }

  @Option ( name = "-f", aliases = "-fid", required = true, usage = "The financial institution id. (Look it up at http://www.ofxhome.com/index.php/home/directory)" )
  private String fid;
  @Option ( name = "-u", aliases = "-username", required = true, usage = "The username of the user with an account." )
  private String username;
  @Option ( name = "-p", aliases = "-password", required = true, usage = "The password of the user with an account." )
  private String password;
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
      Collection<AccountProfile> profiles = fi.readAccountProfiles(username, password);
      AccountInfoResponse accountsElement = new AccountInfoResponse();
      accountsElement.setAccounts(profiles);

      AggregateMarshaller marshaller = new AggregateMarshaller();
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      OFXWriter writer = new OFXV1Writer(bytes);
      ((OFXV1Writer)writer).setWriteAttributesOnNewLine(true);
      if (v2) {
        writer = new OFXV2Writer(bytes);
      }
      marshaller.marshal(accountsElement, writer);
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
    System.err.println("java DownloadAccountInfo [options...] arguments...");
    // print the list of available options
    parser.printUsage(System.err);
    System.err.println();

    // print option sample. This is useful some time
    System.err.println("  Example: java DownloadAccountInfo " + parser.printExample(ExampleMode.ALL));

    System.exit(1);
  }

  private void exit(String message) {
    System.out.println(message);
    System.exit(1);
  }

  public static void main(String[] args) throws Exception {
    new DownloadAccountInfo().doMain(args);
  }

}