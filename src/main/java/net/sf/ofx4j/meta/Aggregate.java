package net.sf.ofx4j.meta;

import java.lang.annotation.*;

/**
 * Annotation for a method that returns an OFX aggregate.
 *
 * @author Ryan Heaton
 */
@Target ( ElementType.TYPE )
@Retention ( RetentionPolicy.RUNTIME )
public @interface Aggregate {

  /**
   * The name of the aggregate.
   *
   * @return The name of the aggregate.
   */
  String value() default "#NOT_SET#";
}
