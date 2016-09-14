/*
 * Copyright 2010 Web Cohesion
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

package net.sf.ofx4j.domain.data.investment.accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jon Perlow
 * @see "OFX Spec, Section 13.6.2.1"
 */
public enum UnitedStatesAccountType {

  /** A 401(k) retirement account */
  R401K,

  /** A 403(B) retirement account */
  R403B,

  /** An IRA retirement account */
  IRA,

  /** Keough (money purchase/profit sharing) account */
  KEOUGH,

  /** Other account type */
  OTHER,

  /** Salary Reduction Employer Pension Plan */
  SARSEP,

  /** Savings Incentive Match Plan for Employees*/
  SIMPLE,

  /** Regular investment account */
  NORMAL,

  /** Tax Deferred Annuity */
  TDA,

  /** Trust (including UTMA) */
  TRUST,

  /** Custodial account */
  UGMA;

  static Map<String, UnitedStatesAccountType> ofxMapping =
      new HashMap<String, UnitedStatesAccountType>();
  static {
    ofxMapping.put("401K", R401K);
    ofxMapping.put("403B", R403B);
    ofxMapping.put("IRA", IRA);
    ofxMapping.put("KEOUGH", KEOUGH);
    ofxMapping.put("OTHER", OTHER);
    ofxMapping.put("SARSEP", SARSEP);
    ofxMapping.put("SIMPLE", SIMPLE);
    ofxMapping.put("NORMAL", NORMAL);
    ofxMapping.put("TDA", TDA);
    ofxMapping.put("TRUST", TRUST);
    ofxMapping.put("UGMA", UGMA);
  }

  public static UnitedStatesAccountType fromOfx(String ofxVal) {
    return ofxVal == null ? null : ofxMapping.get(ofxVal);
  }

}
