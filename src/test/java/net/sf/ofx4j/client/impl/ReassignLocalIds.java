package net.sf.ofx4j.client.impl;

import net.sf.ofx4j.client.FinancialInstitutionData;

import java.io.FileOutputStream;

/**
 * @author Ryan Heaton
 */
public class ReassignLocalIds {

  public static void main(String[] args) throws Exception {
    LocalResourceFIDataStore store = new LocalResourceFIDataStore();
    for (FinancialInstitutionData data : store.getInstitutionDataList()) {
      BaseFinancialInstitutionData dataImpl = (BaseFinancialInstitutionData) data;
      dataImpl.setId(String.valueOf(dataImpl.hashCode()));
    }

    FileOutputStream out = new FileOutputStream("/tmp/institutions.xml");
    store.storeData(out);
    out.flush();
    out.close();
  }
}
