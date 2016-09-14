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

package net.sf.ofx4j.io;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Base class for an OFX reader.  Parses the headers and determines whether we're parsing an
 * OFX v2 or OFX v1 element.  For OFX v2, uses a standard SAX library.
 *
 * @author Ryan Heaton
 */
public abstract class BaseOFXReader implements OFXReader {

  private static final Log LOG = LogFactory.getLog(BaseOFXReader.class);
  public static final Pattern OFX_2_PROCESSING_INSTRUCTION_PATTERN = Pattern.compile("<\\?OFX ([^\\?]+)\\?>");
  private OFXHandler contentHandler = new DefaultHandler();

  /**
   * The content handler.
   *
   * @return The content handler.
   */
  public OFXHandler getContentHandler() {
    return contentHandler;
  }

  /**
   * The content handler.
   *
   * @param handler The content handler.
   */
  public void setContentHandler(OFXHandler handler) {
    this.contentHandler = handler;
  }

  /**
   * Parses the stream as UTF-8 encoded data.
   *
   * @param stream The stream to parse.
   */
  public void parse(InputStream stream) throws IOException, OFXParseException {
    //todo: what about UTF-16 or other unicode encodings?
    parse(new InputStreamReader(stream, "utf-8"));
  }

  /**
   * Parse the reader, including the headers.
   *
   * @param reader The reader.
   */
  public void parse(Reader reader) throws IOException, OFXParseException {
    //make sure we're buffering...
    reader = new BufferedReader(reader);

    StringBuilder header = new StringBuilder();
    final char[] firstElementStart = getFirstElementStart();
    final char[] buffer = new char[firstElementStart.length];
    reader.mark(firstElementStart.length);
    int ch = reader.read(buffer);
    while ((ch != -1) && (!Arrays.equals(buffer, firstElementStart))) {
      if (!contains(buffer, '<')) {
        //if the buffer contains a '<', then we might already have marked the beginning.
        reader.mark(firstElementStart.length);
      }
      ch = reader.read();
      char shifted = shiftAndAppend(buffer, (char) ch);
      header.append(shifted);
    }

    if (ch == -1) {
      throw new OFXParseException("Invalid OFX: no root <OFX> element!");
    }
    else {
      Matcher matcher = OFX_2_PROCESSING_INSTRUCTION_PATTERN.matcher(header);
      if (matcher.find()) {
        if (LOG.isInfoEnabled()) {
          LOG.info("Processing OFX 2 header...");
        }

        processOFXv2Headers(matcher.group(1));
        reader.reset();
        parseV2FromFirstElement(reader);
      }
      else {
        LOG.info("Processing OFX 1 headers...");
        processOFXv1Headers(header.toString());
        reader.reset();
        parseV1FromFirstElement(reader);
      }
    }
  }

  /**
   * The first characters of the first OFX element, '<', 'O', 'F', 'X'
   *
   * @return The first characters of the OFX element.
   */
  protected char[] getFirstElementStart() {
    return new char[]{ '<', 'O', 'F', 'X' };
  }

  /**
   * Whether the specified buffer contains the specified character.
   *
   * @param buffer The buffer.
   * @param c The character to search for.
   * @return Whether the specified buffer contains the specified character.
   */
  private boolean contains(char[] buffer, char c) {
    for (char ch : buffer) {
      if (ch == c) {
        return true;
      }
    }
    return false;
  }

  private char shiftAndAppend(char[] buffer, char c) {
    char shifted = buffer[0];
    for (int i = 0; i + 1 < buffer.length; i++) {
      buffer[i] = buffer[i + 1];
    }
    buffer[buffer.length - 1] = c;
    return shifted;
  }

  /**
   * Parse an OFX version 1 stream from the first OFX element (defined by the {@link #getFirstElementStart() first element characters}).
   *
   * @param reader The reader.
   */
  protected abstract void parseV1FromFirstElement(Reader reader) throws IOException, OFXParseException;

  /**
   * Parse an OFX version 2 stream from the first OFX element (defined by the {@link #getFirstElementStart() first element characters}).
   *
   * @param reader The reader.
   */
  protected void parseV2FromFirstElement(Reader reader) throws IOException, OFXParseException {
    try {
      XMLReader xmlReader = XMLReaderFactory.createXMLReader();
      xmlReader.setFeature("http://xml.org/sax/features/namespaces", false);
      xmlReader.setContentHandler(new OFXV2ContentHandler(getContentHandler()));
      xmlReader.parse(new InputSource(reader));
    }
    catch (SAXException e) {
      if (e.getCause() instanceof OFXParseException) {
        throw (OFXParseException) e.getCause();
      }
      
      throw new OFXParseException(e);
    }
  }

  /**
   * Process the given characters as OFX version 1 headers.
   *
   * @param chars The characters to process.
   */
  protected void processOFXv1Headers(String chars) throws IOException, OFXParseException {
    BufferedReader reader = new BufferedReader(new StringReader(chars));
    String line = reader.readLine();
    while (line != null) {
      int colonIndex = line.indexOf(':');
      if (colonIndex >= 0) {
        String name = line.substring(0, colonIndex);
        String value = line.length() > colonIndex ? line.substring(colonIndex + 1) : "";
        this.contentHandler.onHeader(name, value);
      }
      line = reader.readLine();
    }
  }

  /**
   * Process the given characters as OFX version 2 headers.
   *
   * @param chars The characters to process.
   */
  protected void processOFXv2Headers(String chars) throws OFXParseException {
    String[] nameValuePairs = chars.split("\\s+");
    for (String nameValuePair : nameValuePairs) {
      int equalsIndex = nameValuePair.indexOf('=');
      if (equalsIndex >= 0) {
        String name = nameValuePair.substring(0, equalsIndex);
        String value = nameValuePair.length() > equalsIndex ? nameValuePair.substring(equalsIndex + 1) : "";
        value = value.replace('"', ' ');
        value = value.replace('\'', ' ');
        value = value.trim();
        this.contentHandler.onHeader(name, value);
      }
    }
  }
}
