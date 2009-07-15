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

package net.sf.ofx4j.io.tagsoup;

import org.xml.sax.*;
import org.ccil.cowan.tagsoup.Parser;

import java.io.*;

import net.sf.ofx4j.io.*;

/**
 * Implementation of an OFX reader using <a href="http://ccil.org/~cowan/XML/tagsoup/">TagSoup</a>.<br/><br/>
 *
 * <tt>TagSoup</tt> was implemented as an HTML reader, and so it has extra overhead for dealing with HTML elements and other special HTML nuances.  But since
 * HTML is a descendant of SGML, TagSoup works well for both SGML and XML documents, too.  It's interface allows us to deal with both an OFX 1.0 and an OFX 2.0
 * document in the same way. For these reasons, <tt>TagSoup</tt> has turned out to be a very useful tool for parsing OFX documents.<br/><br/>
 *
 * Since TagSoup was designed for HTML parsing, it has been deprecated for the {@link net.sf.ofx4j.io.nanoxml.NanoXMLOFXReader}, but it should still work...<br/><br/>
 *
 * @author Ryan Heaton
 */
public class TagSoupOFXReader extends BaseOFXReader {

  public void parseV1FromFirstElement(Reader reader) throws IOException, OFXParseException {
    Parser parser = new Parser();
    try {
      parser.setFeature(Parser.restartElementsFeature, false);
    }
    catch (Exception e) {
      throw new OFXParseException(e);
    }
    parser.setContentHandler(new TagSoupHandler(getContentHandler()));

    try {
      parser.parse(new InputSource(reader));
    }
    catch (SAXException e) {
      if (e.getCause() instanceof OFXParseException) {
        throw (OFXParseException) e.getCause();
      }
      
      throw new OFXParseException("Error parsing OFX document.", e);
    }
  }

}
