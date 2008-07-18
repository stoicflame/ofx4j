package net.sf.ofx4j.client.net;

import net.sf.ofx4j.domain.data.RequestEnvelope;
import net.sf.ofx4j.domain.data.ResponseEnvelope;

import java.net.URL;

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
  ResponseEnvelope sendRequest(RequestEnvelope request, URL url) throws OFXConnectionException;

}
