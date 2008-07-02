package net.sf.ofx4j.ser;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Collections;

/**
 * Holder for meta information about an aggregate class.
 *
 * @author Ryan Heaton
 */
public class AggregateInfo {

  private final String name;
  private final SortedSet<AggregateAttribute> attributes;

  AggregateInfo(Class clazz) {
    Aggregate aggregateInfo = (Aggregate) clazz.getAnnotation(Aggregate.class);
    if (aggregateInfo == null) {
      throw new IllegalArgumentException(clazz.getName() + " isn't an aggregate.");
    }

    this.name = aggregateInfo.value();
    SortedSet<AggregateAttribute> attributes = new TreeSet<AggregateAttribute>();
    PropertyDescriptor[] pds;
    try {
      pds = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
    }
    catch (IntrospectionException e) {
      throw new IllegalStateException(e);
    }

    for (PropertyDescriptor pd : pds) {
      if (pd.getReadMethod() != null && pd.getWriteMethod() != null) {
        ChildAggregate childAggregateInfo = pd.getReadMethod().getAnnotation(ChildAggregate.class);
        Element elementInfo = pd.getReadMethod().getAnnotation(Element.class);
        if (childAggregateInfo != null && elementInfo != null) {
          throw new IllegalStateException(String.format("Illegal annotations for property '%s' on aggregate %s: contains both a @ChildAggregate and @Element annotation.", pd.getName(), clazz.getName()));
        }
        else if (childAggregateInfo != null) {
          AggregateAttribute attribute = new AggregateAttribute(pd, childAggregateInfo);
          if (!attributes.add(attribute)) {
            throw new IllegalStateException(String.format("Unable to add child aggregate property '%s' to meta information for aggregate %s.  Order duplicates another aggregate property?", pd.getName(), clazz.getName()));
          }
        }
        else if (elementInfo != null) {
          AggregateAttribute attribute = new AggregateAttribute(pd, elementInfo);
          if (!attributes.add(attribute)) {
            throw new IllegalStateException(String.format("Unable to add element property '%s' to meta information for aggregate %s.  Order duplicates another aggregate property?", pd.getName(), clazz.getName()));
          }
        }
      }
    }

    this.attributes = Collections.unmodifiableSortedSet(attributes);
  }

  /**
   * The name of the aggregate.
   *
   * @return The name of the aggregate.
   */
  public String getName() {
    return name;
  }

  /**
   * The attributes.
   *
   * @return The attributes.
   */
  public SortedSet<AggregateAttribute> getAttributes() {
    return attributes;
  }

  /**
   * Get the attribute by the specified name.
   *
   * @param name The name of the attribute.
   * @return The attribute.
   */
  public AggregateAttribute getAttribute(String name) {
    for (AggregateAttribute attribute : attributes) {
      if (attribute.getName().equals(name)) {
        return attribute;
      }
    }

    return null;
  }
}
