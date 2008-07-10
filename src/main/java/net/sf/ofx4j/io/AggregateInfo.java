package net.sf.ofx4j.io;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.meta.Header;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * Holder for meta information about an aggregate class.
 *
 * @author Ryan Heaton
 */
public class AggregateInfo {

  private final String name;
  private final SortedSet<AggregateAttribute> attributes;
  private final Map<String, PropertyDescriptor> headers;

  AggregateInfo(Class<?> clazz) {
    Aggregate aggregateInfo = clazz.getAnnotation(Aggregate.class);
    if (aggregateInfo == null) {
      throw new IllegalArgumentException(clazz.getName() + " isn't an aggregate.");
    }

    this.name = aggregateInfo.value();
    SortedSet<AggregateAttribute> attributes = new TreeSet<AggregateAttribute>();
    Map<String, PropertyDescriptor> headers = new TreeMap<String, PropertyDescriptor>();
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

        if (pd.getReadMethod().isAnnotationPresent(Header.class)) {
          headers.put(pd.getReadMethod().getAnnotation(Header.class).name(), pd);
        }
      }
    }

    this.headers = Collections.unmodifiableMap(headers);
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
   * @param order The order at which the attribute must come after.
   * @return The attribute.
   */
  public AggregateAttribute getAttribute(String name, int order) {
    AggregateAttribute collectionBucket = null;
    for (AggregateAttribute attribute : attributes) {
      if (attribute.getOrder() >= order) {
        if (name.equals(attribute.getName())) {
          return attribute;
        }
        else if (collectionBucket == null && attribute.isCollection()) {
          collectionBucket = attribute;
        }
      }
    }

    return collectionBucket;
  }

  /**
   * Whether this aggregate has headers.
   *
   * @return Whether this aggregate has headers.
   */
  public boolean hasHeaders() {
    return !this.headers.isEmpty();
  }

  /**
   * Get the headers defined by the specific aggregate instance.
   *
   * @param instance The aggregate instance.
   * @return The headers.
   */
  public Map<String, Object> getHeaders(Object instance) {
    Map<String, Object> headers = new TreeMap<String, Object>();
    for (PropertyDescriptor header : this.headers.values()) {
      Object headerValue;
      try {
        headerValue = header.getReadMethod().invoke(instance);
      }
      catch (RuntimeException e) {
        throw e;
      }
      catch (Exception e) {
        throw new IllegalStateException(e);
      }

      headers.put(header.getReadMethod().getAnnotation(Header.class).name(), headerValue);
    }
    return headers;
  }

  /**
   * The type of the specified header.
   *
   * @param name The header name.
   * @return The header type, or null if no header by the specified name exists.
   */
  public Class getHeaderType(String name) {
    return this.headers.containsKey(name) ? this.headers.get(name).getReadMethod().getReturnType() : null;
  }

  /**
   * Set the header value for the specified instance.
   *
   * @param instance The instance.
   * @param name     The name of the header.
   * @param value    the value of the header.
   */
  public void setHeader(Object instance, String name, Object value) {
    if (this.headers.containsKey(name)) {
      try {
        this.headers.get(name).getWriteMethod().invoke(instance, value);
      }
      catch (RuntimeException e) {
        throw e;
      }
      catch (Exception e) {
        throw new IllegalStateException(e);
      }
    }
  }
}
