package net.sf.ofx4j.io.v2;

import net.sf.ofx4j.io.v1.OFXV1Writer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

/**
 * OFX writer to XML, suitable for OFX version 2.0.
 *
 * @author Ryan Heaton
 */
public class OFXV2Writer extends OFXV1Writer {

  public OFXV2Writer(OutputStream out) {
    super(out);
  }

  public OFXV2Writer(Writer writer) {
    super(writer);
  }

  public void writeHeaders(Map<String, String> headers) throws IOException {
    if (headersWritten) {
      throw new IllegalStateException("Headers have already been written!");
    }

    //write out the XML PI
    print("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
    String security = headers.get("SECURITY");
    if (security == null) {
      security = "NONE";
    }
    String olduid = headers.get("OLDFILEUID");
    if (olduid == null) {
      olduid = "NONE";
    }
    println(olduid);
    String uid = headers.get("NEWFILEUID");
    if (uid == null) {
      uid = "NONE";
    }

    print(String.format("<?OFX OFXHEADER=\"200\" VERSION=\"211\" SECURITY=\"%s\" OLDFILEUID=\"%s\" NEWFILEUID=\"%s\"?>", security, olduid, uid));
    this.headersWritten = true;
  }

  public void writeElement(String name, String value) throws IOException {
    super.writeElement(name, value);
    print("</");
    print(name);
    print('>');
  }
}