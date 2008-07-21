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

import net.sf.ofx4j.io.nanoxml.NanoXMLOFXReader;

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
      OFXReader reader = newReader();
      reader.setContentHandler(new AggregateStackContentHandler<A>(aggregate, getConversion()));
      reader.parse(stream);
      return aggregate;
    }
    catch (OFXParseException e) {
      throw e;
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public A unmarshal(Reader reader) throws IOException, OFXParseException {
    try {
      A aggregate = clazz.newInstance();
      OFXReader ofxReader = newReader();
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
   * New OFX reader.
   *
   * @return new OFX reader.
   */
  protected OFXReader newReader() {
    return new NanoXMLOFXReader();
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
