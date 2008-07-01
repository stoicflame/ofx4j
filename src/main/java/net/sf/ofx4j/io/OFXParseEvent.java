package net.sf.ofx4j.io;

/**
 * An event during OFX parsing.
 *
 * @author Ryan Heaton
 */
public class OFXParseEvent {

  public static enum Type {

    CHARACTERS,

    ELEMENT
  }

  private final Type eventType;
  private final String eventValue;

  public OFXParseEvent(Type eventType, String eventValue) {
    this.eventType = eventType;
    this.eventValue = eventValue;
  }

  public Type getEventType() {
    return eventType;
  }

  public String getEventValue() {
    return eventValue;
  }
}
