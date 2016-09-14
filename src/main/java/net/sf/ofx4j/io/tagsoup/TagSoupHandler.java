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

package net.sf.ofx4j.io.tagsoup;

import net.sf.ofx4j.io.OFXHandler;
import net.sf.ofx4j.io.OFXParseEvent;
import net.sf.ofx4j.io.OFXParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Handler for TagSoup-created parse events. <em>Note that this handler makes a VITAL assumption: that OFX aggregates DO NOT contain any non-whitespace
 * character data. If this ever changes in a future OFX spec, this parser will no longer be valid. This is because the start element of the aggregate
 * will be assigned a value of the character data and processed as an OFX element.</em>
 *
 * @author Ryan Heaton
 */
public class TagSoupHandler extends DefaultHandler {

  private static final Log LOG = LogFactory.getLog(TagSoupHandler.class);

  private final Stack<OFXParseEvent> eventStack = new Stack<OFXParseEvent>();
  private final OFXHandler ofxHandler;
  private final List<OFXParseEvent> startedEvents = new ArrayList<OFXParseEvent>();

  public TagSoupHandler(OFXHandler ofxHandler) {
    if (ofxHandler == null) {
      throw new IllegalArgumentException("An OFX handler must be supplied.");
    }
    
    this.ofxHandler = ofxHandler;
  }

  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    qName = qName.toUpperCase();// tag soup converts to lower case for HTML tags.

    if (LOG.isDebugEnabled()) {
      LOG.debug("START ELEMENT: " + qName);
    }

    String lastElement;
    try {
      lastElement = processLastCharactersIfNecessary();
    }
    catch (IOException e) {
      throw new SAXException(e);
    }

    if ((lastElement == null) && (!eventStack.isEmpty()) && (eventStack.peek().getEventType() == OFXParseEvent.Type.ELEMENT) && (!isStarted(eventStack.peek()))) {
      String eventValue = eventStack.peek().getEventValue();
      if (LOG.isDebugEnabled()) {
        LOG.debug("Element " + qName + " is starting aggregate " + eventValue);
      }
      //no last element was ended; we are assuming we've started a new aggregate.
      try {
        this.ofxHandler.startAggregate(eventValue);
      }
      catch (OFXParseException e) {
        throw new SAXException(e);
      }

      this.startedEvents.add(eventStack.peek());
    }

    eventStack.push(new OFXParseEvent(OFXParseEvent.Type.ELEMENT, qName));
  }

  /**
   * Whether the specified element aggregate has already been started.
   *
   * @param event The event containing the start.
   * @return Whether the specified element aggregate has already been started.
   */
  protected boolean isStarted(OFXParseEvent event) {
    return this.startedEvents.contains(event);
  }

  /**
   * Process the last characters that were encountered, if any.</br></br>
   *
   * If there was a start element before the last characters, it will be processed as an OFX element (with the characters assigned as its value) and it's name
   * will be returned. <em>Note that this ENTIRE processing scheme makes a VITAL assumption: that OFX aggregates DO NOT contain any non-whitespace character data.
   * If this ever changes in a future OFX spec, this parser will no longer be valid. This is because the start element of the aggregate will be assigned a value
   * of the character data and processed as an element.</em><br/><br/>
   *
   * If there was NO start element before the last characters, the characters at the top of the stack are assumed to define OFX 1.0 headers.
   *
   * @return The name of the OFX element that was processed, or null if no OFX element was processed.
   */
  protected String processLastCharactersIfNecessary() throws IOException, SAXException {
    if (!eventStack.isEmpty() && eventStack.peek().getEventType() == OFXParseEvent.Type.CHARACTERS) {
      String chars = eventStack.pop().getEventValue().trim();

      if (eventStack.isEmpty()) {
        throw new IllegalStateException("Illegal character data outside main OFX root element: \"" + chars + "\".");
      }
      else {
        OFXParseEvent elementEvent = eventStack.pop();
        if (elementEvent.getEventType() != OFXParseEvent.Type.ELEMENT) {
          throw new IllegalStateException("Illegal OFX event before characters \"" + chars + "\" (" + elementEvent.getEventType() + ")!");
        }
        else {
          String value = elementEvent.getEventValue();
          if (LOG.isDebugEnabled()) {
            LOG.debug("Element " + value + " processed with value " + chars);
          }
          try {
            this.ofxHandler.onElement(value, chars);
          }
          catch (OFXParseException e) {
            throw new SAXException(e);
          }

          return value;
        }
      }
    }

    return null;
  }

  public void endElement(String uri, String localName, String qName) throws SAXException {
    qName = qName.toUpperCase();// tag soup converts to lower case for HTML tags.

    if (LOG.isDebugEnabled()) {
      LOG.debug("END ELEMENT: " + qName);
    }

    String lastElement;
    try {
      lastElement = processLastCharactersIfNecessary();
    }
    catch (IOException e) {
      throw new SAXException(e);
    }

    //if this is the end element tag of the last element that was ended, we must be parsing an OFX 2.0 document.
    //otherwise, this is a potential end element of an OFX aggregate.
    if ((lastElement == null) || (!lastElement.equals(qName))) {
      if (eventStack.isEmpty()) {
        throw new IllegalStateException("End of element '" + qName + "' at an empty event stack.");
      }

      switch (eventStack.peek().getEventType()) {
        case CHARACTERS:
          throw new IllegalStateException("End of element '" + qName + "' with characters \"" + eventStack.peek().getEventValue() + "\" on the stack!");
        case ELEMENT:
          if (qName.equals(eventStack.peek().getEventValue())) {
            //the last element on the stack is ours; we're ending an OFX aggregate.
            OFXParseEvent event = eventStack.pop();
            String value = event.getEventValue();
            if (LOG.isDebugEnabled()) {
              LOG.debug("Ending aggregate " + value);
            }
            try {
              this.ofxHandler.endAggregate(value);
            }
            catch (OFXParseException e) {
              throw new SAXException(e);
            }
            this.startedEvents.remove(event);
            break;
          }
          else {
            //ignore; tagsoup is just ending an OFX element for us.
            break;
          }
        default:
          //ignore; unknown OFX event
      }
    }
  }

  public void characters(char ch[], int start, int length) throws SAXException {
    String value = new String(ch, start, length);
    if (value.trim().length() > 0) {
      OFXParseEvent event;
      if ((!eventStack.isEmpty()) && (eventStack.peek().getEventType() == OFXParseEvent.Type.CHARACTERS)) {
        //append the characters...
        event = new OFXParseEvent(OFXParseEvent.Type.CHARACTERS, eventStack.pop().getEventValue() + value);
      }
      else {
        event = new OFXParseEvent(OFXParseEvent.Type.CHARACTERS, value);
      }
      eventStack.push(event);
    }
  }

}
