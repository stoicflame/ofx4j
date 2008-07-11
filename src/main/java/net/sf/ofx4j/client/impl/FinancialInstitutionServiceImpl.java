package net.sf.ofx4j.client.impl;

import net.sf.ofx4j.client.FinancialInstitutionService;
import net.sf.ofx4j.client.FinancialInstitution;
import net.sf.ofx4j.client.FinancialInstitutionData;
import net.sf.ofx4j.client.FinancialInstitutionDataStore;
import net.sf.ofx4j.net.OFXConnection;
import net.sf.ofx4j.net.OFXV1Connection;

/**
 * @author Ryan Heaton
 */
public class FinancialInstitutionServiceImpl implements FinancialInstitutionService {

  private OFXConnection connection = new OFXV1Connection();
  private FinancialInstitutionDataStore dataStore;

  public FinancialInstitution getFinancialInstitution(String fid) {
    return dataStore == null ? null : getFinancialInstitution(getDataStore().getInstitutionData(fid));
  }

  public FinancialInstitution getFinancialInstitution(FinancialInstitutionData data) {
    if (data == null) {
      return null;
    }

    return new FinancialInstitutionImpl(data, getConnection());
  }

  public FinancialInstitutionDataStore getDataStore() {
    return dataStore;
  }

  public void setDataStore(FinancialInstitutionDataStore dataStore) {
    this.dataStore = dataStore;
  }

  public OFXConnection getConnection() {
    return connection;
  }

  public void setConnection(OFXConnection connection) {
    this.connection = connection;
  }
}
