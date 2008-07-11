package net.sf.ofx4j.io;

import junit.framework.TestCase;
import net.sf.ofx4j.domain.data.profile.ProfileResponseTransaction;
import net.sf.ofx4j.domain.data.profile.CoreMessageSetInfo;

/**
 * @author Ryan Heaton
 */
public class TestAggregateInfo extends TestCase {

  /**
   * tests overriding methods.
   */
  public void testOverridingMethods() throws Exception {
    AggregateInfo info = new AggregateInfo(ProfileResponseTransaction.class);
    assertEquals(4, info.getAttributes().size());

    info = new AggregateInfo(CoreMessageSetInfo.class);
    assertNotNull(info.getAttribute("SPNAME", 0));
  }

}
