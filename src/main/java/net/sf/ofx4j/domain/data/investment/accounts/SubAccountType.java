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

/**
 * Types of well-known sub-accounts.
 * @see "Section 13.9.2.4.2, OFX Spec"
 *
 * @author Jon Perlow
 */
public enum SubAccountType {
  CASH,
  MARGIN,
  SHORT,
  OTHER;

  public static SubAccountType fromOfx(String ofxVal) {
    if ("CASH".equals(ofxVal)) {
      return CASH;
    } else if ("MARGIN".equals(ofxVal)) {
      return MARGIN;
    } else if ("SHORT".equals(ofxVal)) {
      return SHORT;
    } else if ("OTHER".equals(ofxVal)) {
      return OTHER;
    } else {
      return null;
    }
  }
}
