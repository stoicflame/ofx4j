package net.sf.ofx4j.io;

/**
 * Handler for events during OFX parsing.
 *
 * @author Ryan Heaton
 */
public interface OFXHandler {

  /**
   * Handler an OFX header.
   *
   * @param name The name of the header.
   * @param value The value of the header.
   */
  void onHeader(String name, String value) throws OFXSyntaxException;

  /**
   * Handle a new OFX element.
   *
   * @param name The name of the element.
   * @param value The value of the element.
   */
  void onElement(String name, String value) throws OFXSyntaxException;

  /**
   * Handle the start of a new OFX aggregate.
   *
   * @param aggregateName The name of the aggregate.
   */
  void startAggregate(String aggregateName) throws OFXSyntaxException;

  /**
   * Handle the end of an OFX aggregate.
   *
   * @param aggregateName The aggregate name.
   */
  void endAggregate(String aggregateName) throws OFXSyntaxException;

}
