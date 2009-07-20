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

package net.sf.ofx4j.client.impl;

import net.sf.ofx4j.client.FinancialInstitutionData;
import net.sf.ofx4j.client.FinancialInstitutionDataStore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of a FI data store that loads its FI data from a local resource.
 *
 * @author Ryan Heaton
 */
public class LocalResourceFIDataStore implements FinancialInstitutionDataStore {

  private static final Log LOG = LogFactory.getLog(LocalResourceFIDataStore.class);
  private final Map<String, FinancialInstitutionData> fiData;

  /**
   * Load the default set of institution data (found at classpath:/institutions.xml).
   *
   * @throws IOException If the resource is unreadable or malformed.
   */
  public LocalResourceFIDataStore() throws IOException {
    this(LocalResourceFIDataStore.class.getResourceAsStream("/META-INF/ofx4j/institutions.xml"));
  }

  /**
   * Load the institution data from a file.
   *
   * @param in The file to read.
   * @throws IOException If the resource is unreadable or malformed.
   */
  public LocalResourceFIDataStore(File in) throws IOException {
    this(new FileInputStream(in));
  }

  /**
   * Load the institution data from a specific stream.
   *
   * @param in The stream.
   * @throws IOException If the resource is unreadable or malformed.
   */
  public LocalResourceFIDataStore(InputStream in) throws IOException {
    Map<String, FinancialInstitutionData> fiData = new HashMap<String, FinancialInstitutionData>();
    if (in != null) {
      try {
        FIDataList list = (FIDataList) JAXBContext.newInstance(FIDataList.class).createUnmarshaller().unmarshal(in);
        if (list != null && list.getData() != null) {
          for (BaseFinancialInstitutionData fi : list.getData()) {
            fiData.put(fi.getId(), fi);
          }
        }
      }
      catch (JAXBException e) {
        throw new IOException(e.getMessage());
      }

      if (LOG.isInfoEnabled()) {
        LOG.info(fiData.size() + " institutions loaded.");
      }
    }

    this.fiData = fiData;
  }

  // Inherited.
  public FinancialInstitutionData getInstitutionData(String fid) {
    return fiData.get(fid);
  }

  // Inherited.
  public List<FinancialInstitutionData> getInstitutionDataList() {
    return new ArrayList<FinancialInstitutionData>(fiData.values());
  }

  /**
   * Add a financial institution.
   *
   * @param data The data to add.
   */
  public void add(BaseFinancialInstitutionData data) {
    this.fiData.put(data.getId(), data);
  }

  /**
   * Store the data somewhere.
   *
   * @param out The output stream.
   * @throws IOException If an error occurs.
   */
  public void storeData(OutputStream out) throws IOException {
    try {
      FIDataList dataList = new FIDataList();
      ArrayList<BaseFinancialInstitutionData> data = new ArrayList<BaseFinancialInstitutionData>();
      for (FinancialInstitutionData datum : fiData.values()) {
        if (datum instanceof BaseFinancialInstitutionData) {
          data.add((BaseFinancialInstitutionData) datum);
        }
      }
      dataList.setData(data);
      JAXBContext.newInstance(FIDataList.class).createMarshaller().marshal(dataList, out);
    }
    catch (JAXBException e) {
      throw new IOException(e.getMessage());
    }
  }

}
