package net.sf.ofx4j.io;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Ryan Heaton
 */
public class OFXV2ContentHandler extends org.xml.sax.helpers.DefaultHandler {

  private static final Log LOG = LogFactory.getLog(OFXV2ContentHandler.class);

  private final Stack<OFXParseEvent> eventStack = new Stack<OFXParseEvent>();
  private final OFXHandler ofxHandler;
  private final List<OFXParseEvent> startedEvents = new ArrayList<OFXParseEvent>();

  public OFXV2ContentHandler(OFXHandler ofxHandler) {
    if (ofxHandler == null) {
      throw new IllegalArgumentException("An OFX handler must be supplied.");
    }

    this.ofxHandler = ofxHandler;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    if (LOG.isDebugEnabled()) {
      LOG.debug("START ELEMENT: " + qName);
    }

    if ((!eventStack.isEmpty()) && (eventStack.peek().getEventType() == OFXParseEvent.Type.ELEMENT) && (!isAlreadyStarted(eventStack.peek()))) {
      String eventValue = eventStack.peek().getEventValue();
      if (LOG.isDebugEnabled()) {
        LOG.debug("Element " + qName + " is starting aggregate " + eventValue);
      }

      //the last element started was not ended; we are assuming we've started a new aggregate.
      this.ofxHandler.startAggregate(eventValue);
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
  protected boolean isAlreadyStarted(OFXParseEvent event) {
    return this.startedEvents.contains(event);
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (LOG.isDebugEnabled()) {
      LOG.debug("END ELEMENT: " + qName);
    }

    OFXParseEvent eventToFinish = eventStack.pop();
    if (eventToFinish.getEventType() == OFXParseEvent.Type.CHARACTERS) {
      String chars = eventToFinish.getEventValue().trim();

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
          this.ofxHandler.onElement(value, chars);
        }
      }
    }
    else if (eventToFinish.getEventType() == OFXParseEvent.Type.ELEMENT) {
      //we're ending an aggregate (no character data on the stack).
      if (qName.equals(eventToFinish.getEventValue())) {
        //the last element on the stack is ours; we're ending an OFX aggregate.
        String value = eventToFinish.getEventValue();
        if (LOG.isDebugEnabled()) {
          LOG.debug("Ending aggregate " + value);
        }
        this.ofxHandler.endAggregate(value);
        this.startedEvents.remove(eventToFinish);
      }
      else {
        throw new IllegalStateException("Unexpected end tag: " + eventToFinish.getEventValue());
      }
    }
    else {
      throw new IllegalStateException("Illegal OFX event: " + eventToFinish.getEventType());
    }
  }

  @Override
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
