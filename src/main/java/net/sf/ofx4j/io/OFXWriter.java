package net.sf.ofx4j.io;

import java.io.IOException;
import java.util.Map;

/**
 * @author Ryan Heaton
 */
public interface OFXWriter {

  /**
   * Write the specified headers.
   *
   * @param headers The headers to be written.
   */
  void writeHeaders(Map<String, String> headers) throws IOException;

  /**
   * Write the start of a new aggregate.
   *
   * @param aggregateName The aggregate name.
   */
  void writeStartAggregate(String aggregateName) throws IOException;

  /**
   * Write an element to the current aggregate.
   *
   * @param name The name of the element.
   * @param value The value of the element.
   */
  void writeElement(String name, String value) throws IOException;

  /**
   * Write the end of an aggregate.
   *
   * @param aggregateName The aggregate name.
   * @throws IllegalArgumentException If the specified aggregate hasn't been started.
   */
  void writeEndAggregate(String aggregateName) throws IOException;

  /**
   * Close this OFX writer.
   */
  void close() throws IOException;
}
