package net.sf.ofx4j.client.impl;

import junit.framework.TestCase;
import net.sf.ofx4j.client.FinancialInstitutionData;
import net.sf.ofx4j.domain.data.fi.BaseFinancialInstitutionData;

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
