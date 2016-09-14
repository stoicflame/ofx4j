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

package net.n3.nanoxml;

import java.io.IOException;

/**
 * Backdoor to XMLUtil.  Just making necesasry methods public.
 *
 * @author Ryan Heaton
 */
public class XMLUtilBackdoor extends XMLUtil {

  public static String scanIdentifier(IXMLReader reader) throws IOException, XMLParseException {
    return XMLUtil.scanIdentifier(reader);
  }

  public static void skipWhitespace(IXMLReader reader, StringBuffer buffer) throws IOException {
    XMLUtil.skipWhitespace(reader, buffer);
  }

  public static String read(IXMLReader reader, char entityChar) throws IOException, XMLParseException {
    return XMLUtil.read(reader, entityChar);
  }

  public static void processEntity(String entity, IXMLReader reader, IXMLEntityResolver entityResolver) throws IOException, XMLParseException {
    XMLUtil.processEntity(entity, reader, entityResolver);
  }

  public static char processCharLiteral(String entity) throws IOException, XMLParseException {
    return XMLUtil.processCharLiteral(entity);
  }

  public static boolean checkLiteral(IXMLReader reader, String literal) throws IOException, XMLParseException {
    return XMLUtil.checkLiteral(reader, literal);
  }

}
