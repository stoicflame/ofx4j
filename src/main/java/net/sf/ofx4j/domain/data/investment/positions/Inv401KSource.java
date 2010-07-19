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

package net.sf.ofx4j.domain.data.investment.positions;

import java.util.HashMap;
import java.util.Map;

/**
 * Types of 401(k) sources.
 * @see "Section 13.9.2.4.2, OFX Spec"
 *
 * @author Jon Perlow
 */
public enum Inv401KSource {
  PRETAX,
  AFTER_TAX,
  MATCH,
  PROFIT_SHARING,
  ROLLOVER,
  OTHER_VEST,
  OTHER_NONVEST;

  static Map<String, Inv401KSource> ofxMapping = new HashMap<String, Inv401KSource>();
  static {
    ofxMapping.put("PRETAX", PRETAX);
    ofxMapping.put("AFTERTAX", AFTER_TAX);
    ofxMapping.put("MATCH", MATCH);
    ofxMapping.put("PROFITSHARING", PROFIT_SHARING);
    ofxMapping.put("ROLLOVER", ROLLOVER);
    ofxMapping.put("OTHERVEST", OTHER_VEST);
    ofxMapping.put("OTHERNONVEST", OTHER_NONVEST);
  }

  public static Inv401KSource fromOfx(String ofxVal) {
    return ofxVal == null ? null : ofxMapping.get(ofxVal);
  }
}
