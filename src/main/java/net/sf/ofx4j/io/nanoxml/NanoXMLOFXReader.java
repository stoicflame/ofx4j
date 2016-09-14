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

import net.n3.nanoxml.*;
import net.sf.ofx4j.io.BaseOFXReader;
import net.sf.ofx4j.io.OFXParseException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

/**
 * OFX reader using the <a href="http://nanoxml.cyberelf.be/">NanoXML</a> library.<br/><br/>
 *
 * Much of this code was yanked (with gratitude) from the NanoXML project, written by Marc De Scheemaecker.
 *
 * @author Ryan Heaton
 */
public class NanoXMLOFXReader extends BaseOFXReader {

  protected void parseV1FromFirstElement(Reader reader) throws IOException, OFXParseException {
    try {
      StdXMLReader xmlReader = new StdXMLReader(reader);
      XMLUtilBackdoor.skipWhitespace(xmlReader, null);
      char ch = xmlReader.read();
      if (ch != '<') {
        throw new OFXParseException("Unexpected content before root <OFX> tag: " + ch);
      }
      
      processOFXTag(xmlReader, new XMLEntityResolver());
    }
    catch (XMLParseException e) {
      throw new OFXParseException(e.getMessage(), e);
    }
  }

  /**
   * Processes an OFX tag (aggregate or element).
   *
   * @param reader The reader.
   * @param entityResolver The entity resolver.
   * @return the name of the end tag that finished the processing.
   */
  protected String processOFXTag(IXMLReader reader, IXMLEntityResolver entityResolver) throws IOException, XMLParseException, OFXParseException {
    String startTag = readTagName(reader);
    StringBuffer buffer = new StringBuffer(16);
    boolean aggregateStarted = false;
    StringBuilder tagContent = null;

    while (!reader.atEOF()) {
      String str;

      while (true) {
        XMLUtilBackdoor.skipWhitespace(reader, buffer);
        str = XMLUtilBackdoor.read(reader, '&');

        if ((str.charAt(0) == '&') && (str.charAt(1) != '#')) {
          XMLUtilBackdoor.processEntity(str, reader, entityResolver);
        }
        else {
          break;
        }
      }

      if (str.charAt(0) == '<') {
        str = XMLUtilBackdoor.read(reader, '\0');

        if (str.charAt(0) == '/') {
          XMLUtilBackdoor.skipWhitespace(reader, null);
          String endTag = XMLUtilBackdoor.scanIdentifier(reader);
          XMLUtilBackdoor.skipWhitespace(reader, null);

          //we could be ending an element as well as an aggregate...
          if (tagContent != null && tagContent.toString().trim().length() > 0) {
            getContentHandler().onElement(startTag, tagContent.toString().trim());
          }

          if (reader.read() != '>') {
            throw new XMLParseException(reader.getSystemID(), reader.getLineNr(), "Non-empty closing tag.");
          }
          
          if(!startTag.equals(endTag) || aggregateStarted) {
            getContentHandler().endAggregate(endTag);
          }
          
          return endTag;
        }
        else if (str.charAt(0) == '!') {
          //CDATA
          str = XMLUtilBackdoor.read(reader, '&');
          char ch = str.charAt(0);

          switch(ch) {
            case '&':
              throw new XMLParseException(reader.getSystemID(), reader.getLineNr(), "Unexpected entity: " + str);
            case '[':
              if (!XMLUtilBackdoor.checkLiteral(reader, "CDATA[")) {
                throw new XMLParseException(reader.getSystemID(), reader.getLineNr(), "Expected <![CDATA[");
              }
              String cdata = readCharacters(new CDATAReaderBackdoor(reader));
              if (tagContent == null) {
                tagContent = new StringBuilder(cdata);
              }
              else {
                tagContent.append(cdata);
              }
              break;
            default:
              throw new XMLParseException(reader.getSystemID(), reader.getLineNr(), "Unexpected special tag: " + str);
          }

        }
        else {
          //a new tag encountered. if character data was processed, it's an element.  Otherwise, its the start of a new aggregate.
          reader.unread(str.charAt(0));
          if (tagContent == null || tagContent.toString().trim().length() == 0) {
            if (!aggregateStarted) {
              getContentHandler().startAggregate(startTag);
              aggregateStarted = true;
            }
            String endTag = processOFXTag(reader, entityResolver);
            if (endTag.equals(startTag)) {
              //we could have processed our own end tag.  If so, we're done.
              //otherwise, we processed the end tag of a child aggregate, so continue.
              return endTag;
            }
          }
          else {
            getContentHandler().onElement(startTag, tagContent.toString().trim());
            tagContent = null;
            startTag = readTagName(reader);
          }
        }
      }
      else { // [^<]
        if (str.charAt(0) == '&') {
          char ch = XMLUtilBackdoor.processCharLiteral(str);
          buffer.append(ch);
        }
        else {
          reader.unread(str.charAt(0));
        }

        String chars = readCharacters(new ContentReaderBackdoor(reader, entityResolver, buffer.toString()));
        if (tagContent == null) {
          tagContent = new StringBuilder(chars);
        }
        else {
          tagContent.append(chars);
        }
      }
    }

    throw new OFXParseException("Unexpected EOF. We never finished parsing the " + startTag + " tag.");
  }

  protected String readTagName(IXMLReader reader) throws IOException, XMLParseException {
    String fullName = XMLUtilBackdoor.scanIdentifier(reader);
    XMLUtilBackdoor.skipWhitespace(reader, null);
    char ch = reader.read();
    if (ch != '>') {
      throw new IllegalStateException("Unexpected character '" + ch + "' after element name " + fullName + " on line " + reader.getLineNr() + ".");
    }
    return fullName;
  }

  protected String readCharacters(Reader contentReader) throws IOException {
    String tagContent;
    StringWriter contentWriter = new StringWriter();
    char[] contentBuffer = new char[200]; //200 characters should be adequate for OFX...
    int charsRead = contentReader.read(contentBuffer);
    while (charsRead != -1) {
      contentWriter.write(contentBuffer, 0, charsRead);
      charsRead = contentReader.read(contentBuffer);
    }
    contentReader.close();
    contentWriter.close();
    tagContent = contentWriter.toString();
    return tagContent;
  }

}
