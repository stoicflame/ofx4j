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
 * One day, we might implement our own OFX reader that does the parsing itself without the extra TagSoup overhead. Perhaps look into
 * <a href="http://nanoxml.cyberelf.be/">NanoXML</a>?<br/><br/>
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
      throw new OFXParseException("Error parsing OFX document.", e);
    }
  }

}
