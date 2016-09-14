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

import junit.framework.TestCase;
import net.sf.ofx4j.client.FinancialInstitutionData;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Ryan Heaton
 */
public class TestOFXHomeFIDataStore extends TestCase {

  /**
   * tests the regexp
   */
  public void testRegexp() throws Exception {
    Matcher matcher = OFXHomeFIDataStore.INSTITUTION_HREF_PATTERN.matcher("http://www.ofxhome.com/index.php/institution/view/491");
    assertTrue(matcher.matches());
  }

  public static void main(String[] args) throws Exception {
    LocalResourceFIDataStore resourceStore = new LocalResourceFIDataStore((InputStream) null);
    List<FinancialInstitutionData> dataList = new OFXHomeFIDataStore().getInstitutionDataList();
    for (FinancialInstitutionData data : dataList) {
      resourceStore.add((BaseFinancialInstitutionData) data);
    }
    resourceStore.storeData(System.out);
  }

}
