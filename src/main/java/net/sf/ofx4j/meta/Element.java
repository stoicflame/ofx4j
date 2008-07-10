package net.sf.ofx4j.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * An OFX element, applied to a javabean property.
 *
 * @author Ryan Heaton
 */
@Target ( ElementType.METHOD )
@Retention ( RetentionPolicy.RUNTIME)
public @interface Element {

  /**
   * The name of the element.
   *
   * @return The name of the element.
   */
  String name();

  /**
   * Whether this element is required.
   *
   * @return Whether this element is required.
   */
  boolean required() default false;

  /**
   * The order this element comes in its parent aggregate.
   *
   * @return The order this element comes in its parent aggregate.
   */
  int order();
}
