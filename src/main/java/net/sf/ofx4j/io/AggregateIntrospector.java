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

package net.sf.ofx4j.io;

import net.sf.ofx4j.meta.Aggregate;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Introspector for aggregate information.
 *
 * @author Ryan Heaton
 */
public class AggregateIntrospector {

  private static final Log LOG = LogFactory.getLog(AggregateIntrospector.class);
  static final Map<Class, AggregateInfo> INFO_MAP = new HashMap<Class, AggregateInfo>();
  static final Map<String, Class> AGGREGATE_CLASSES_BY_NAME;
  static {
    AGGREGATE_CLASSES_BY_NAME = Collections.synchronizedMap(new TreeMap<String, Class>());
    InputStream aggregateList = AggregateIntrospector.class.getResourceAsStream("/META-INF/ofx4j/ofx-aggregate.list");
    if (aggregateList != null) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(aggregateList));
      try {
        String classname = reader.readLine();
        while (classname != null) {
          try {
            Class<?> clazz = Class.forName(classname);
            AggregateInfo info = getAggregateInfo(clazz);
            AGGREGATE_CLASSES_BY_NAME.put(info.getName(), clazz);
          }
          catch (ClassNotFoundException e) {
            LOG.error("Aggregate class " + classname + " listed at classpath:/META-INF/ofx4j/ofx-aggregate.list not found.", e);
          }
          classname = reader.readLine();
        }
      }
      catch (IOException e) {
        LOG.error("Error while reading the aggregate class list at classpath:/META-INF/ofx4j/ofx-aggregate.list", e);
      }
    }
    else {
      LOG.warn("No aggregate class list found at classpath:/META-INF/ofx4j/ofx-aggregate.list");
    }
  }

  /**
   * Get the aggregate meta information for the specified class.
   *
   * @param clazz the aggregate class.
   * @return The aggregate meta information, or null if the class isn't an aggregate.
   */
  public static AggregateInfo getAggregateInfo(Class clazz) {
    AggregateInfo info = null;
    if (clazz.isAnnotationPresent(Aggregate.class)) {
      synchronized (INFO_MAP) {
        info = INFO_MAP.get(clazz);
        if (info == null) {
          info = new AggregateInfo(clazz);
          INFO_MAP.put(clazz, info);
        }
      }
    }
    return info;
  }

  /**
   * Find the aggregate class by name.
   *
   * @param aggregateName The name of the aggregate.
   * @return The aggregate class.
   */
  public static Class findAggregateByName(String aggregateName) {
    return AGGREGATE_CLASSES_BY_NAME.get(aggregateName);
  }

}
