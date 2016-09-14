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
 * Activation status of an account.
 * @see "Section 13.6.2, OFX Spec"
 *
 * @author Jon Perlow
 */
public enum ActivationStatus {
  ACTIVE,
  PENDING,
  AVAILABLE;

  public static ActivationStatus fromOfx(String ofxVal) {
    if ("ACTIVE".equals(ofxVal)) {
      return ACTIVE;
    } else if ("PEND".equals(ofxVal)) {
      return PENDING;
    } else if ("AVAIL".equals(ofxVal)) {
      return AVAILABLE;
    } else {
      return null;
    }
  }
}
