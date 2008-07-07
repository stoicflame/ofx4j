package net.sf.ofx4j.ser;

/**
 * Utility class for conversion to/from OFX strings.
 *
 * @author Ryan Heaton
 */
public class DefaultStringConversion implements StringConversion {

  public String toString(Object value) {
    if (value == null) {
      return null;
    }
    //todo: other conversion types.
    else {
      return String.valueOf(value);
    }
  }

  public <E> E fromString(Class<E> clazz, String value) {
    if (value == null) {
      return null;
    }
    else if (String.class.isAssignableFrom(clazz)) {
      return (E) value;
    }
    //todo: really convert.
    return (E) value;
  }
}
