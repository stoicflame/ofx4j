package net.sf.ofx4j.io.v1;

import net.sf.ofx4j.io.OFXWriter;

import java.util.Map;
import java.io.*;

/**
 * OFX writer to SGML, suitable for OFX versions < 2.0.
 *
 * @author Ryan Heaton
 */
public class OFXV1Writer implements OFXWriter {

  protected boolean headersWritten = false;
  protected final PrintWriter writer;

  public OFXV1Writer(OutputStream out) {
    try {
      this.writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
    }
    catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public OFXV1Writer(Writer writer) {
    this.writer = new PrintWriter(writer);
  }

  public void writeHeaders(Map<String, String> headers) throws IOException {
    if (headersWritten) {
      throw new IllegalStateException("Headers have already been written!");
    }

    //write out the 1.0 headers
    this.writer.println("OFXHEADER:100");
    this.writer.println("DATA:OFXSGML");
    this.writer.println("VERSION:103");

    this.writer.print("SECURITY:");
    String security = headers.get("SECURITY");
    if (security == null) {
      security = "NONE";
    }
    this.writer.println(security);
    this.writer.println("ENCODING:UNICODE");
    this.writer.println("CHARSET:UTF-8");
    this.writer.println("COMPRESSION:NONE");
    this.writer.print("OLDFILEUID:");
    String olduid = headers.get("OLDFILEUID");
    if (olduid == null) {
      olduid = "NONE";
    }
    this.writer.println(olduid);
    String uid = headers.get("NEWFILEUID");
    if (uid == null) {
      uid = "NONE";
    }
    this.writer.println(uid);
    this.writer.println();

    this.headersWritten = true;
  }

  public void writeStartAggregate(String aggregateName) throws IOException {
    this.writer.print('<');
    this.writer.print(aggregateName);
    this.writer.print('>');
  }

  public void writeElement(String name, String value) throws IOException {
    if ((value == null) || ("".equals(value))) {
      throw new IllegalArgumentException("Illegal element value for element '" + name + "' (value must not be null or empty).");
    }
    
    this.writer.print('<');
    this.writer.print(name);
    this.writer.print('>');
    this.writer.print(value);
  }

  public void writeEndAggregate(String aggregateName) throws IOException {
    this.writer.print("</");
    this.writer.print(aggregateName);
    this.writer.print('>');
  }

  public void close() throws IOException {
    this.writer.flush();
    this.writer.close();
  }
}
