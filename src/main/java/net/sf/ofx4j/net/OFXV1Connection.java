package net.sf.ofx4j.net;

import net.sf.ofx4j.domain.data.RequestEnvelope;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.io.OFXParseException;
import net.sf.ofx4j.io.OFXWriter;
import net.sf.ofx4j.io.AggregateMarshaller;
import net.sf.ofx4j.io.v1.OFXV1Writer;
import net.sf.ofx4j.io.AggregateUnmarshaller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base implementation for an OFX connection.
 *
 * @author Ryan Heaton
 */
public class OFXV1Connection implements OFXConnection {

  private static final Log LOG = LogFactory.getLog(OFXV1Connection.class);

  private AggregateMarshaller marshaller = new AggregateMarshaller();
  private AggregateUnmarshaller<ResponseEnvelope> unmarshaller = new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class);

  public ResponseEnvelope sendRequest(RequestEnvelope request, URL url) throws OFXConnectionException {
    try {
      if (!url.getProtocol().toLowerCase().startsWith("http")) {
        throw new IllegalArgumentException("Invalid URL: " + url + " only http(s) is supported.");
      }

      //marshal to memory so we can determine the size...
      ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
      OFXWriter ofxWriter = newOFXWriter(outBuffer);
      getMarshaller().marshal(request, ofxWriter);
      ofxWriter.close();
      logRequest(outBuffer);
      return sendBuffer(url, outBuffer);
    }
    catch (IOException e) {
      throw new OFXConnectionException(e);
    }
  }

  protected void logRequest(ByteArrayOutputStream outBuffer) throws UnsupportedEncodingException {
    if (LOG.isInfoEnabled()) {
      LOG.info("Marshalling " + outBuffer.size() + " bytes of the OFX request.");
      if (LOG.isDebugEnabled()) {
        LOG.debug(outBuffer.toString("utf-8"));
      }
    }
  }

  protected ResponseEnvelope sendBuffer(URL url, ByteArrayOutputStream outBuffer) throws IOException, OFXConnectionException {
    HttpURLConnection connection = openConnection(url);
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/x-ofx");
    connection.setRequestProperty("Content-Length", String.valueOf(outBuffer.size()));
    connection.setRequestProperty("Accept", "*/*, application/x-ofx");
    connection.setDoOutput(true);
    connection.connect();

    OutputStream out  = connection.getOutputStream();
    out.write(outBuffer.toByteArray());

    InputStream in;
    int responseCode = connection.getResponseCode();
    if (responseCode >= 200 && responseCode < 300) {
      in = connection.getInputStream();
    }
    else if (responseCode >= 400 && responseCode < 500) {
      throw new OFXServerException("Error with client request: " + connection.getResponseMessage(), responseCode);
    }
    else {
      throw new OFXServerException("Invalid response code from OFX server: " + connection.getResponseMessage(), responseCode);
    }

    try {
      return getUnmarshaller().unmarshal(in);
    }
    catch (OFXParseException e) {
      throw new OFXConnectionException("Unable to parse the OFX response.", e);
    }
  }

  protected HttpURLConnection openConnection(URL url) throws IOException {
    return (HttpURLConnection) url.openConnection();
  }

  protected OFXWriter newOFXWriter(OutputStream out) {
    return new OFXV1Writer(out);
  }

  public AggregateMarshaller getMarshaller() {
    return marshaller;
  }

  public void setMarshaller(AggregateMarshaller marshaller) {
    this.marshaller = marshaller;
  }

  public AggregateUnmarshaller<ResponseEnvelope> getUnmarshaller() {
    return unmarshaller;
  }

  public void setUnmarshaller(AggregateUnmarshaller<ResponseEnvelope> unmarshaller) {
    this.unmarshaller = unmarshaller;
  }
}
