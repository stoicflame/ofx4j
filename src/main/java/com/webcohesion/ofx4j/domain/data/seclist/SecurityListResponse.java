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

package com.webcohesion.ofx4j.domain.data.seclist;

import com.webcohesion.ofx4j.domain.data.ResponseMessage;
import com.webcohesion.ofx4j.meta.Aggregate;

/**
 * Security list response. This is an empty aggregate. The actual security information is included
 * in the "SECLIST" aggregate.
 * @see "Section 13.8.3, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "SECLISTRS" )
public class SecurityListResponse extends ResponseMessage {
  public String getResponseMessageName() {
    return "security list";
  }
}
