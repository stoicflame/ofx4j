package net.sf.ofx4j.client.net;

import net.sf.ofx4j.io.OFXWriter;
import net.sf.ofx4j.io.v2.OFXV2Writer;

import java.io.OutputStream;

/**
 * @author Ryan Heaton
 */
public class OFXV2Connection extends OFXV1Connection {

  @Override
  protected OFXWriter newOFXWriter(OutputStream out) {
    return new OFXV2Writer(out);
  }
}
