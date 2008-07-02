package net.sf.ofx4j.ser;

import net.sf.ofx4j.meta.Aggregate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan Heaton
 */
public class AggregateIntrospector {

  private static final Map<Class, AggregateInfo> INFO_MAP = new HashMap<Class, AggregateInfo>();

  public static AggregateInfo getAggregateInfo(Class clazz) {
    AggregateInfo info = null;
    if (clazz.isAnnotationPresent(Aggregate.class)) {
      synchronized (INFO_MAP) {
        info = INFO_MAP.get(clazz);
        if (info == null) {
          INFO_MAP.put(clazz, new AggregateInfo(clazz));
        }
      }
    }
    return info;
  }
}
