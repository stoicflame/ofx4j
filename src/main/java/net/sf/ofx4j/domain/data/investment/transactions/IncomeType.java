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

package net.sf.ofx4j.domain.data.investment.transactions;

/**
 * Type of income.
 * @see "Section 13.9.2.4.2, OFX Spec"
 *
 * @author Jon Perlow
 */
public enum IncomeType {
  LONG_TERM_CAP_GAINS,
  SHORT_TERM_CAP_GAINS,
  DIVIDEND,
  INTEREST,
  MISC;

  public static IncomeType fromOfx(String ofxVal) {
    if ("CGLONG".equals(ofxVal)) {
      return LONG_TERM_CAP_GAINS;
    } else if ("CGSHORT".equals(ofxVal)) {
      return SHORT_TERM_CAP_GAINS;
    } else if ("DIV".equals(ofxVal)) {
      return DIVIDEND;
    } else if ("INTEREST".equals(ofxVal)) {
      return INTEREST;
    } else if ("MISC".equals(ofxVal)) {
      return MISC;
    } else {
      return null;
    }
  }
}
