package net.sf.ofx4j.net;

import net.sf.ofx4j.domain.RequestEnvelope;
import net.sf.ofx4j.domain.ResponseEnvelope;
import net.sf.ofx4j.io.OFXParseException;
import net.sf.ofx4j.io.OFXWriter;
import net.sf.ofx4j.io.v1.OFXV1Writer;
import net.sf.ofx4j.ser.AggregateMarshaller;
import net.sf.ofx4j.ser.AggregateUnmarshaller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Base implementation for an OFX connection.
 *
 * @author Ryan Heaton
 */
public class OFXV1Connection implements OFXConnection {

  private AggregateMarshaller marshaller = new AggregateMarshaller();
  private AggregateUnmarshaller<ResponseEnvelope> unmarshaller = new AggregateUnmarshaller<ResponseEnvelope>(ResponseEnvelope.class);

  public ResponseEnvelope sendRequest(RequestEnvelope request, URL url) throws IOException, OFXConnectionException {
    if (!url.getProtocol().toLowerCase().startsWith("http")) {
      throw new IllegalArgumentException("Invalid URL: " + url + " only http(s) is supported.");
    }
    HttpURLConnection connection = openConnection(url);
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/x-ofx;charset=utf-8");
    connection.setDoOutput(true);
    connection.connect();

    OutputStream out  = connection.getOutputStream();
    OFXWriter ofxWriter = newOFXWriter(out);
    getMarshaller().marshal(request, ofxWriter);

    InputStream in;
    int responseCode = connection.getResponseCode();
    if (responseCode >= 200 && responseCode < 300) {
      in = connection.getInputStream();
    }
    else if (responseCode >= 400 && responseCode < 500) {
      in = connection.getErrorStream();
    }
    else {
      throw new OFXConnectionException("Invalid response code from OFX server: " + responseCode + " " + connection.getResponseMessage());
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
