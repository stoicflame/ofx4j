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

package net.sf.ofx4j.client.context;

/**
 * @author Ryan Heaton
 */
public class OFXApplicationContextHolder {

  private static OFXApplicationContext CURRENT_CONTEXT = new DefaultApplicationContext("Money", "1600"); //some apps fail unless you're Quicken or Money...

  /**
   * Get the current (thread-safe) context.
   *
   * @return The thread-safe context.
   */
  public static OFXApplicationContext getCurrentContext() {
    //todo: implement a strategy (perhaps for thread-local access or something)?
    return CURRENT_CONTEXT;
  }

  /**
   * Set the current context.
   *
   * @param context The context.
   */
  public static void setCurrentContext(OFXApplicationContext context) {
    CURRENT_CONTEXT = context;
  }
}
