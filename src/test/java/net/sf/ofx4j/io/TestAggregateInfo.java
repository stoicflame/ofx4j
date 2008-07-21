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
