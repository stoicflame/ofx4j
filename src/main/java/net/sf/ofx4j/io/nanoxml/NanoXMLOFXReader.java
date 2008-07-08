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
      processOFXTag(new StdXMLReader(reader), new XMLEntityResolver());
    }
    catch (XMLParseException e) {
      throw new OFXParseException(e);
    }
  }

  /**
   * Processes an OFX tag (aggregate or element).
   *
   * @param reader The reader.
   * @param entityResolver The entity resolver.
   */
  protected void processOFXTag(IXMLReader reader, IXMLEntityResolver entityResolver) throws IOException, XMLParseException {
    String fullName = XMLUtilBackdoor.scanIdentifier(reader);
    XMLUtilBackdoor.skipWhitespace(reader, null);
    char ch = reader.read();
    if (ch != '>') {
      throw new IllegalStateException("Unexpected character '" + ch + "' after element name " + fullName + " on line " + reader.getLineNr() + ".");
    }

    StringBuffer buffer = new StringBuffer(16);
    String tagContent = null;
    
    while (true) {
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
          XMLUtilBackdoor.scanIdentifier(reader);
          XMLUtilBackdoor.skipWhitespace(reader, null);

          //ending an aggregate...

          if (reader.read() != '>') {
            throw new XMLParseException(reader.getSystemID(), reader.getLineNr(), "Non-empty closing tag.");
          }

          break;
        }
        else { // <[^/]
          //a new tag encountered. if character data was processed, it's an element.  Otherwise, its the start of a new aggregate.
          if (tagContent == null) {
            getContentHandler().startAggregate(fullName);
          }
          else {
            getContentHandler().onElement(fullName, tagContent);
          }

          reader.unread(str.charAt(0));
          processOFXTag(reader, entityResolver);
        }
      }
      else { // [^<]
        if (str.charAt(0) == '&') {
          ch = XMLUtilBackdoor.processCharLiteral(str);
          buffer.append(ch);
        }
        else {
          reader.unread(str.charAt(0));
        }

        StringWriter contentWriter = new StringWriter();
        ContentReaderBackdoor contentReader = new ContentReaderBackdoor(reader, entityResolver, buffer.toString());
        char[] contentBuffer = new char[200]; //200 characters should be adequate for OFX...
        int charsRead = contentReader.read(contentBuffer);
        while (charsRead != -1) {
          contentWriter.write(contentBuffer, 0, charsRead);
          charsRead = contentReader.read(contentBuffer);
        }
        contentReader.close();
        contentWriter.close();
        tagContent = contentWriter.toString();
      }
    }

    getContentHandler().endAggregate(fullName);
  }

}
