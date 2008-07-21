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

package net.sf.ofx4j.domain.data.common;

/**
 * Holder for an unknown status code.
 *
 * @author Ryan Heaton
 */
public class UnknownStatusCode implements StatusCode {

  private final int code;
  private final String message;
  private final Status.Severity defaultSeverity;

  public UnknownStatusCode(int code, String message, Status.Severity defaultSeverity) {
    this.code = code;
    this.message = message;
    this.defaultSeverity = defaultSeverity;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public Status.Severity getDefaultSeverity() {
    return defaultSeverity;
  }

  @Override
  public String toString() {
    return String.valueOf(code);
  }
}
