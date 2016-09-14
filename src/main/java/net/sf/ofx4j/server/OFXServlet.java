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

package net.sf.ofx4j.server;

import net.sf.ofx4j.domain.data.RequestEnvelope;
import net.sf.ofx4j.domain.data.RequestMessageSet;
import net.sf.ofx4j.domain.data.signon.FinancialInstitution;
import net.sf.ofx4j.domain.data.signon.SignonRequestMessageSet;
import net.sf.ofx4j.io.AggregateMarshaller;
import net.sf.ofx4j.io.AggregateUnmarshaller;
import net.sf.ofx4j.io.OFXParseException;
import net.sf.ofx4j.io.OFXWriter;
import net.sf.ofx4j.io.v1.OFXV1Writer;
import net.sf.ofx4j.io.v2.OFXV2Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Basic servlet that processes an OFX request.
 *
 * @author Ryan Heaton
 */
public class OFXServlet extends HttpServlet {

  private OFXServer server;
  private boolean OFXVersion2;

  @Override
  public void init() throws ServletException {
    super.init();

    this.server = loadOFXServer();
    String version = getServletConfig().getInitParameter("ofx-version");
    this.OFXVersion2 = "2".equals(version);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      if (!request.getContentType().toLowerCase().equals("application/x-ofx")) {
        throw new OFXRequestException("Unsupported content type: " + request.getContentType());
      }

      AggregateUnmarshaller<RequestEnvelope> unmarshaller = createUnmarshaller();
      RequestEnvelope requestEnvelope;
      try {
        requestEnvelope = unmarshaller.unmarshal(request.getReader());
      }
      catch (OFXParseException e) {
        throw new OFXRequestException("Unable to parse client request: " + e.getMessage());
      }
      String orgFid = request.getParameter("ORGFID");
      String org = orgFid.substring(0,orgFid.indexOf(','));
		String fid = orgFid.substring(orgFid.indexOf(',')+1);
	 RequestMessageSet messageSet= requestEnvelope.getMessageSets().first();
	 SignonRequestMessageSet signonRequestMessageSet = (SignonRequestMessageSet)messageSet;
	 FinancialInstitution fi = new FinancialInstitution();
	 fi.setId(fid);
	 fi.setOrganization(org);
	 signonRequestMessageSet.getSignonRequest().setFinancialInstitution(fi);
      AggregateMarshaller marshaller = new AggregateMarshaller();
      OFXWriter writer = createOFXWriter(response.getOutputStream());
      marshaller.marshal(getServer().getResponse(requestEnvelope), writer);
      writer.close();
    }
    catch (OFXRequestException e) {
      String message = e.getMessage();
      if (message == null) {
        message = "Problem with client request.";
      }
      response.sendError(400, message);
    }
  }

  /**
   * Create the OFX writer.
   *
   * @param out The output stream.
   * @return The writer.
   */
  protected OFXWriter createOFXWriter(OutputStream out) {
    return isOFXVersion2() ? new OFXV2Writer(out) : new OFXV1Writer(out);
  }

  /**
   * Create the aggregate unmarshaller.
   *
   * @return The aggregate unmarshaller.
   */
  protected AggregateUnmarshaller<RequestEnvelope> createUnmarshaller() {
    return new AggregateUnmarshaller<RequestEnvelope>(RequestEnvelope.class);
  }

  /**
   * Load the OFX server instance that is to be used for this servlet.
   *
   * @return the OFX server.
   */
  protected OFXServer loadOFXServer() throws ServletException {
    String serverClassname = getServletConfig().getInitParameter("ofx-server-class");
    if (serverClassname == null) {
      throw new ServletException("Missing init parameter: ofx-server-class");
    }

    try {
      return (OFXServer) Class.forName(serverClassname).newInstance();
    }
    catch (Exception e) {
      throw new ServletException(e);
    }
  }

  /**
   * Get the server instance.
   *
   * @return The server instance.
   */
  public OFXServer getServer() {
    return server;
  }

  /**
   * Whether this servlet is OFX version 2.
   *
   * @return Whether this servlet is OFX version 2.
   */
  public boolean isOFXVersion2() {
    return OFXVersion2;
  }
}
