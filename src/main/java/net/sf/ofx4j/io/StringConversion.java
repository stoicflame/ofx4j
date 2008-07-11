package net.sf.ofx4j.io;

/**
 * Interface for converting to/from OFX strings.
 *
 * @author Ryan Heaton
 */
public interface StringConversion {

  /**
   * Convert the specified object to a string.
   *
   * @param value The value to convert to a string.
   * @return The string.
   */
  String toString(Object value);

  /**
   * Convert the specified value to an object of the specified type.
   *
   * @param clazz The class.
   * @param value The value.
   * @return The converted value.
   * @throws OFXSyntaxException If there was something wrong with the syntax of the string.
   */
  <E> E fromString(Class<E> clazz, String value) throws OFXSyntaxException;
}
