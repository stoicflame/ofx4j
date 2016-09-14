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

package net.sf.ofx4j.client.impl;

import net.sf.ofx4j.client.FinancialInstitutionData;
import net.sf.ofx4j.client.FinancialInstitutionDataStore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of a financial institution data store that reads its FI data from <a href="http://www.ofxhome.com">OFX Home</a>.
 *
 * @author Ryan Heaton
 */
public class OFXHomeFIDataStore implements FinancialInstitutionDataStore {

  private static final Log LOG = LogFactory.getLog(OFXHomeFIDataStore.class);
  public static final Pattern INSTITUTION_HREF_PATTERN = Pattern.compile("http\\://www.ofxhome.com/index.php/institution/view/\\w+");
  private String url = "http://www.ofxhome.com/index.php/home/directory/all";
  private Map<String, FinancialInstitutionData> fiData;

  public FinancialInstitutionData getInstitutionData(String fid) {
    return getFiData().get(fid);
  }

  public List<FinancialInstitutionData> getInstitutionDataList() {
    return new ArrayList<FinancialInstitutionData>(getFiData().values());
  }

  public synchronized Map<String, FinancialInstitutionData> getFiData() {
    if (this.fiData == null) {
      this.fiData = new HashMap<String, FinancialInstitutionData>();
      try {
        initializeFIData();
        if (LOG.isInfoEnabled()) {
          LOG.info(this.fiData.size() + " institutions loaded from " + getUrl());
        }
      }
      catch (Exception e) {
        fiData = Collections.emptyMap();
      }
    }

    return fiData;
  }

  private void initializeFIData() throws IOException, SAXException {
    URL url = new URL(getUrl());
    XMLReader xmlReader = new Parser();
    xmlReader.setFeature("http://xml.org/sax/features/namespaces", false);
    xmlReader.setFeature("http://xml.org/sax/features/validation", false);
    xmlReader.setContentHandler(new DirectoryContentHandler());
    xmlReader.parse(new InputSource(url.openStream()));
  }

  private BaseFinancialInstitutionData loadInstitutionData(String href) throws IOException, SAXException {
    if (LOG.isInfoEnabled()) {
      LOG.info("Loading institution data from: " + href);
    }
    
    URL url = new URL(href);
    XMLReader xmlReader = new Parser();
    xmlReader.setFeature("http://xml.org/sax/features/namespaces", false);
    xmlReader.setFeature("http://xml.org/sax/features/validation", false);
    InstitutionContentHandler institutionHandler = new InstitutionContentHandler();
    xmlReader.setContentHandler(institutionHandler);
    xmlReader.parse(new InputSource(url.openStream()));
    return institutionHandler.data;
  }

  /**
   * The URL of the OFX home directory.
   *
   * @return The URL of the OFX home directory.
   */
  public String getUrl() {
    return url;
  }

  /**
   * The URL of the OFX home directory.
   *
   * @param url The URL of the OFX home directory.
   */
  public void setUrl(String url) {
    this.url = url;
  }

  private class DirectoryContentHandler extends DefaultHandler {

    private Set<String> visited = new TreeSet<String>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      if ("a".equalsIgnoreCase(qName) || "a".equalsIgnoreCase(localName)) {
        for (int i = 0; i < attributes.getLength(); i++) {
          String href = attributes.getValue("href");
          if (href != null) {
            Matcher matcher = INSTITUTION_HREF_PATTERN.matcher(href);
            if (matcher.matches() && visited.add(href)) {
              try {
                BaseFinancialInstitutionData data = loadInstitutionData(href);
                data.setName(attributes.getValue("title"));
                fiData.put(data.getId(), data);
              }
              catch (IOException e) {
                throw new SAXException(e);
              }
            }
          }
        }
      }
    }
  }

  private enum FIField {
    ID,

    ORG,

    URL,

    BROKERID
  }

  private class InstitutionContentHandler extends DefaultHandler {

    private FIField currentField = null;
    private StringBuilder fieldNameBuffer = null;
    private StringBuilder fieldValueBuffer = null;
    private BaseFinancialInstitutionData data;

    @Override
    public void startDocument() throws SAXException {
      this.data = new BaseFinancialInstitutionData(UUID.randomUUID().toString());
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      if ("td".equalsIgnoreCase(qName)) {
        if (this.fieldNameBuffer == null) {
          this.fieldNameBuffer = new StringBuilder();
        }
        else if (currentField != null) {
          this.fieldValueBuffer = new StringBuilder();
        }
      }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      if ("td".equalsIgnoreCase(qName)) {
        if (currentField == null) {
          if (fieldNameBuffer != null) {
            String fieldName = this.fieldNameBuffer.toString().toLowerCase();
            if (fieldName.contains("fi id")) {
              this.currentField = FIField.ID;
              return;
            }
            else if (fieldName.contains("fi org")) {
              this.currentField = FIField.ORG;
              return;
            }
            else if (fieldName.contains("fi url")) {
              this.currentField = FIField.URL;
              return;
            }
            else if (fieldName.contains("fi broker id")) {
              this.currentField = FIField.BROKERID;
              return;
            }
          }
        }
        else if (this.fieldValueBuffer != null) {
          String fieldValue = this.fieldValueBuffer.toString().trim();
          if ("".equals(fieldValue)) {
            fieldValue = null;
          }

          switch (currentField) {
            case ID:
              this.data.setFinancialInstitutionId(fieldValue);
              break;
            case BROKERID:
              this.data.setBrokerId(fieldValue);
              break;
            case ORG:
              this.data.setOrganization(fieldValue);
              break;
            case URL:
              try {
                this.data.setOFXURL(new URL(fieldValue));
              }
              catch (MalformedURLException e) {
                //fall through...
              }
          }
        }

        this.currentField = null;
        this.fieldNameBuffer = null;
        this.fieldValueBuffer = null;
      }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
      if (this.currentField != null && fieldValueBuffer != null) {
        fieldValueBuffer.append(ch, start, length);
      }
      else if (fieldNameBuffer != null) {
        fieldNameBuffer.append(ch, start, length);
      }
    }
  }

}
