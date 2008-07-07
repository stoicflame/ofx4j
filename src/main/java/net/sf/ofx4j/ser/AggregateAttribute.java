package net.sf.ofx4j.ser;

import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

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
  private final Class attributeType;
  private final String name;
  private final int order;
  private final boolean required;
  private final Type type;
  private final String toString;

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

    this.attributeType = this.readMethod.getReturnType();
    this.name = elementInfo.value();
    this.order = elementInfo.order();
    this.required = elementInfo.required();
    this.type = Type.ELEMENT;
    this.toString = String.format("%s '%s' (property '%s' of aggregate %s)", getType().toString().toLowerCase().replace('_', ' '), getName(), property.getName(), getAttributeType().getName());

    //todo: validate known/supported element types here?
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

    this.attributeType = this.readMethod.getReturnType();
    this.name = "##not_specified##".equals(childAggregate.name()) ? null : childAggregate.name();
    this.order = childAggregate.order();
    this.required = childAggregate.required();
    this.type = Type.CHILD_AGGREGATE;
    this.toString = String.format("%s '%s' (property '%s' of aggregate %s)", getType().toString().toLowerCase().replace('_', ' '), getName(), property.getName(), getAttributeType().getName());

    //validation
    if ((this.name != null) && (Collection.class.isAssignableFrom(this.attributeType))) {
      throw new IllegalStateException("ERROR: " + this.toString + ": A collection type cannot have a specific child aggregate name.");
    }
  }

  public Object get(Object instance) {
    try {
      return this.readMethod.invoke(instance);
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public void set(Object value, Object instance) {
    if (Collection.class.isAssignableFrom(getAttributeType())) {
      Collection collection = (Collection) get(instance);
      if (collection == null) {
        collection = newCollectionInstance();
      }
      collection.add(value);
      value = collection;
    }

    try {
      this.writeMethod.invoke(instance, value);
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  protected Collection newCollectionInstance() {
    if (!getAttributeType().isInterface()) {
      try {
        return (Collection) getAttributeType().newInstance();
      }
      catch (RuntimeException e) {
        throw e;
      }
      catch (Exception e) {
        throw new IllegalStateException(e);
      }
    }
    else if (SortedSet.class.isAssignableFrom(getAttributeType())) {
      return new TreeSet();
    }
    else if (Set.class.isAssignableFrom(getAttributeType())) {
      return new HashSet();
    }
    else {
      return new ArrayList();
    }
  }

  public Class getAttributeType() {
    return attributeType;
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

  @Override
  public String toString() {
    return this.toString;
  }
}
