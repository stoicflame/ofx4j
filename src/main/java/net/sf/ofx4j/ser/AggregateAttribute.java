package net.sf.ofx4j.ser;

import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * A generic descriptor for an attribute of an OFX aggregate.
 *
 * @author Ryan Heaton
 */
public class AggregateAttribute implements Comparable<AggregateAttribute> {

  public enum Type {

    CHILD_AGGREGATE,

    ELEMENT

  }

  private final Method readMethod;
  private final Method writeMethod;
  private final Class aggregateType;
  private final String name;
  private final int order;
  private final boolean required;
  private final Type type;

  AggregateAttribute(PropertyDescriptor property, Element elementInfo) {
    this.readMethod = property.getReadMethod();
    this.writeMethod = property.getWriteMethod();
    if (this.readMethod == null) {
      throw new IllegalStateException(String.format("Illegal property '%s' for aggregate %s: no read method.",
                                                    property.getName(), property.getWriteMethod().getDeclaringClass().getName()));
    }
    else if (this.writeMethod == null) {
      throw new IllegalStateException(String.format("Illegal property '%s' for aggregate %s: no write method.",
                                                    property.getName(), property.getReadMethod().getDeclaringClass().getName()));
    }

    this.aggregateType = this.readMethod.getReturnType();
    this.name = elementInfo.value();
    this.order = elementInfo.order();
    this.required = elementInfo.required();
    this.type = Type.ELEMENT;
  }

  AggregateAttribute(PropertyDescriptor property, ChildAggregate childAggregate) {
    this.readMethod = property.getReadMethod();
    this.writeMethod = property.getWriteMethod();
    if (this.readMethod == null) {
      throw new IllegalStateException(String.format("Illegal property '%s' for aggregate %s: no read method.",
                                                    property.getName(), property.getWriteMethod().getDeclaringClass().getName()));
    }
    else if (this.writeMethod == null) {
      throw new IllegalStateException(String.format("Illegal property '%s' for aggregate %s: no write method.",
                                                    property.getName(), property.getReadMethod().getDeclaringClass().getName()));
    }

    this.aggregateType = this.readMethod.getReturnType();
    this.name = "##not_specified##".equals(childAggregate.name()) ? null : childAggregate.name();
    this.order = childAggregate.order();
    this.required = childAggregate.required();
    this.type = Type.ELEMENT;
  }

  public Object get(Object instance) {
    try {
      return this.readMethod.invoke(instance);
    }
    catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public void set(Object value, Object instance) {
    try {
      this.writeMethod.invoke(instance, value);
    }
    catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public Class getAggregateType() {
    return aggregateType;
  }

  public String getName() {
    return name;
  }

  public boolean isRequired() {
    return required;
  }

  public Type getType() {
    return type;
  }

  public int compareTo(AggregateAttribute other) {
    return this.order - other.order;
  }
}
