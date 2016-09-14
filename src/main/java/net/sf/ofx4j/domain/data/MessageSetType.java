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

package net.sf.ofx4j.domain.data;

/**
 * The message set type, used to define message set order in the envelope.
 *
 * @author Ryan Heaton
 * @see "Section 2.4.5.2, OFX spec"
 */
public enum MessageSetType {

  signon,

  signup,

  banking,

  creditcard,

  investment,

  interbank_transfer,

  wire_transfer,

  payments,

  email,

  investment_security,

  profile,

  tax1099

}
