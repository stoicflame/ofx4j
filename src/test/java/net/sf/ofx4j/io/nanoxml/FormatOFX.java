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

package net.sf.ofx4j.io.nanoxml;

import net.sf.ofx4j.io.OFXFormatterHandler;

/**
 * @author Ryan Heaton
 */
public class FormatOFX {

  public static void main(String[] args) throws Exception {
    NanoXMLOFXReader reader = new NanoXMLOFXReader();
    reader.setContentHandler(new OFXFormatterHandler());
    reader.parse(FormatOFX.class.getResourceAsStream(args[0]));
  }
}
