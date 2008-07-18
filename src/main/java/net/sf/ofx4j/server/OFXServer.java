package net.sf.ofx4j.server;

import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.domain.data.RequestEnvelope;

/**
 * Basic interface for an OFX server.
 *
 * @author Ryan Heaton
 */
public interface OFXServer {

  /**
   * Get a response for the given request.
   *
   * @param request The request.
   * @return The response.
   */
  ResponseEnvelope getResponse(RequestEnvelope request);
}
