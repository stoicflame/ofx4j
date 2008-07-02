package net.sf.ofx4j.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Marks a method as providing a child aggregate (or set of them to a top-level aggregate).
 *
 * @author Ryan Heaton
 */
@Target ( ElementType.METHOD )
@Retention ( RetentionPolicy.RUNTIME)
public @interface ChildAggregate {

  /**
   * Used to specify the name of the aggregate in its context as a child aggregate.
   *
   * @return Used to specify the name of the aggregate in its context as a child aggregate.
   */
  String name() default "##not_specified##";

  /**
   * Whether this aggregate is required.
   *
   * @return Whether this aggregate is required.
   */
  boolean required() default false;

  /**
   * The order this child aggregate comes in its parent aggregate.
   *
   * @return The order this child aggregate comes in its parent aggregate.
   */
  int order();

}