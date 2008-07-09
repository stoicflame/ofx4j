package net.sf.ofx4j.net;

import net.sf.ofx4j.domain.ResponseEnvelope;
import net.sf.ofx4j.domain.RequestEnvelope;

import java.net.URL;
import java.io.IOException;

/**
 * Connection to an OFX interface.
 *
 * @author Ryan Heaton
 */
public interface OFXConnection {

  /**
   * Send a request.
   *
   * @param request The request to send.
   * @param url The URL to which to send the request.
   * @return The response.
   */
  ResponseEnvelope sendRequest(RequestEnvelope request, URL url) throws IOException, OFXConnectionException;

}
