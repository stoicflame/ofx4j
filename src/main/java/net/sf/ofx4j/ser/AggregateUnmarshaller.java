package net.sf.ofx4j.ser;

import net.sf.ofx4j.io.tagsoup.TagSoupOFXReader;
import net.sf.ofx4j.io.OFXParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Unmarshaller for aggregate objects.
 * 
 * @author Ryan Heaton
 */
public class AggregateUnmarshaller<A> {

  private final Class<A> clazz;
  private StringConversion conversion = new DefaultStringConversion();

  public AggregateUnmarshaller(Class<A> clazz) {
    this.clazz = clazz;
  }

  public A unmarshal(InputStream stream) throws IOException, OFXParseException {
    try {
      A aggregate = clazz.newInstance();
      TagSoupOFXReader reader = new TagSoupOFXReader();
      reader.setContentHandler(new AggregateStackContentHandler<A>(aggregate, getConversion()));
      reader.parse(stream);
      return aggregate;
    }
    catch (OFXParseException e) {
      throw e;
    }
    catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public A unmarshal(Reader reader) throws IOException, OFXParseException {
    try {
      A aggregate = clazz.newInstance();
      TagSoupOFXReader ofxReader = new TagSoupOFXReader();
      ofxReader.setContentHandler(new AggregateStackContentHandler<A>(aggregate, getConversion()));
      ofxReader.parse(reader);
      return aggregate;
    }
    catch (OFXParseException e) {
      throw e;
    }
    catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * The conversion.
   *
   * @return The conversion.
   */
  public StringConversion getConversion() {
    return conversion;
  }

  /**
   * The conversion.
   *
   * @param conversion The conversion.
   */
  public void setConversion(StringConversion conversion) {
    this.conversion = conversion;
  }
}
