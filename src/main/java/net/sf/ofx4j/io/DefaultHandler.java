package net.sf.ofx4j.io;

/**
 * Default (no-op) implementation of an OFX handler.
 *
 * @author Ryan Heaton
 */
public class DefaultHandler implements OFXHandler {

  public void onHeader(String name, String value) {
  }

  public void onElement(String name, String value) {
  }

  public void startAggregate(String aggregateName) {
  }

  public void endAggregate(String aggregateName) {
  }

}