package net.sf.ofx4j.io.tagsoup;

import org.xml.sax.*;
import org.ccil.cowan.tagsoup.Parser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import net.sf.ofx4j.io.OFXReader;
import net.sf.ofx4j.io.OFXHandler;
import net.sf.ofx4j.io.DefaultHandler;
import net.sf.ofx4j.io.OFXParseException;

/**
 * Implementation of an OFX reader using <a href="http://ccil.org/~cowan/XML/tagsoup/">TagSoup</a>.<br/><br/>
 *
 * <tt>TagSoup</tt> was implemented as an HTML reader, and so it has extra overhead for dealing with HTML elements and other special HTML nuances.  But since
 * HTML is a descendant of SGML, TagSoup works well for both SGML and XML documents, too.  It's interface allows us to deal with both an OFX 1.0 and an OFX 2.0
 * document in the same way. For these reasons, <tt>TagSoup</tt> has turned out to be a very useful tool for parsing OFX documents.<br/><br/>
 *
 * One day, we might implement our own OFX reader that does the parsing itself without the extra TagSoup overhead.<br/><br/>
 *
 * @author Ryan Heaton
 */
public class TagSoupOFXReader implements OFXReader {

  private static final Log LOG = LogFactory.getLog(TagSoupOFXReader.class);

  public static final Pattern OFX_2_PROCESSING_INSTRUCTION_PATTERN = Pattern.compile("<\\?OFX ([^\\?]+)\\?>");

  private OFXHandler contentHandler = new DefaultHandler();

  public void setContentHandler(OFXHandler handler) {
    this.contentHandler = handler;
  }

  public void parse(InputStream stream) throws IOException, OFXParseException {
    //todo: what about UTF-16 or other unicode encodings?
    parse(new InputStreamReader(stream, "utf-8"));
  }

  public void parse(Reader reader) throws IOException, OFXParseException {
    //make sure we're buffering...
    reader = new BufferedReader(reader);
    
    StringBuilder header = new StringBuilder();
    final char[] firstElementStart = { '<', 'O', 'F', 'X' };
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
      }
      else {
        LOG.info("Processing OFX 1 headers...");
        processOFXv1Headers(header.toString());
      }
      
      reader.reset();
      parseFromFirstElement(reader);
    }
  }

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

  public void parseFromFirstElement(Reader reader) throws IOException, OFXParseException {
    Parser parser = new Parser();
    try {
      parser.setFeature(Parser.restartElementsFeature, false);
    }
    catch (Exception e) {
      throw new OFXParseException(e);
    }
    parser.setContentHandler(new TagSoupHandler(contentHandler));

    try {
      parser.parse(new InputSource(reader));
    }
    catch (SAXException e) {
      throw new OFXParseException("Error parsing OFX document.", e);
    }
  }

  /**
   * Process the given characters as OFX version 1 headers.
   *
   * @param chars The characters to process.
   */
  protected void processOFXv1Headers(String chars) throws IOException {
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
  protected void processOFXv2Headers(String chars) {
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
