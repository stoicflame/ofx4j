/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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