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

import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
  private final Class collectionEntryType;
  private final String name;
  private final int order;
  private final boolean required;
  private final Type type;
  private final String toString;
  private final boolean collection;

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
    this.collectionEntryType = null;
    this.name = elementInfo.name();
    this.order = elementInfo.order();
    this.required = elementInfo.required();
    this.type = Type.ELEMENT;
    this.toString = String.format("%s '%s' (property '%s' of aggregate %s)",
                                  getType().toString().toLowerCase().replace('_', ' '),
                                  getName(), property.getName(),
                                  property.getReadMethod().getDeclaringClass().getName());
    this.collection = false;

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

    this.readMethod.getGenericReturnType();
    this.attributeType = this.readMethod.getReturnType();
    this.collection = Collection.class.isAssignableFrom(this.attributeType);
    if (this.collection) {
      this.name = null;
      this.collectionEntryType = getGenericCollectionType(this.readMethod.getGenericReturnType());
    }
    else if ("##not_specified##".equals(childAggregate.name())) {
      AggregateInfo aggregateInfo = AggregateIntrospector.getAggregateInfo(this.attributeType);
      if (aggregateInfo == null) {
        throw new IllegalStateException(String.format("Illegal child aggregate type %s (property %s of aggregate %s): no aggregate information available.",
                                                      this.attributeType.getName(), property.getName(), property.getReadMethod().getDeclaringClass().getName()));
      }

      this.name = aggregateInfo.getName();
      if ("##not_specified##".equals(this.name)) {
        throw new IllegalStateException(String.format("Illegal child aggregate type %s (property %s of aggregate %s): a child aggregate name must be specified.",
                                                      this.attributeType.getName(), property.getName(), property.getReadMethod().getDeclaringClass().getName()));
      }
      this.collectionEntryType = null;
    }
    else {
      this.name = childAggregate.name();
      this.collectionEntryType = null;
    }

    this.order = childAggregate.order();
    this.required = childAggregate.required();
    this.type = Type.CHILD_AGGREGATE;
    this.toString = String.format("%s '%s' (property '%s' of aggregate %s)",
                                  getType().toString().toLowerCase().replace('_', ' '),
                                  getName(),
                                  property.getName(),
                                  property.getReadMethod().getDeclaringClass().getName());
  }

  private Class getGenericCollectionType(java.lang.reflect.Type collectionType) {
    if (!(collectionType instanceof ParameterizedType)) {
      return null;
    }
    ParameterizedType parameterizedCollectionType = (ParameterizedType) collectionType;
    Class collectionClass = (Class) parameterizedCollectionType.getRawType();
    Method addMethod;
    try {
      addMethod = collectionClass.getMethod("add", Object.class);
    }
    catch (NoSuchMethodException e) {
      throw new RuntimeException("Collection doesn't implement add?");
    }
    java.lang.reflect.Type[] actualTypeArgs = parameterizedCollectionType.getActualTypeArguments();
    TypeVariable[] typeVariables = collectionClass.getTypeParameters();
    TypeVariable addParamType = (TypeVariable) addMethod.getGenericParameterTypes()[0];
    for (int i = 0; i < typeVariables.length; i++) {
      TypeVariable typeVariable = typeVariables[i];
      if (typeVariable.getName().equals(addParamType.getName())) {
        return (Class) actualTypeArgs[i];
      }
    }
    return null;
  }

  public Object get(Object instance) throws Exception {
    return this.readMethod.invoke(instance);
  }

  public void set(Object value, Object instance) throws Exception {
    if (Collection.class.isAssignableFrom(getAttributeType())) {
      Collection collection = (Collection) get(instance);
      if (collection == null) {
        collection = newCollectionInstance();
      }
      collection.add(value);
      value = collection;
    }
    else if (BigDecimal.class.isAssignableFrom(getAttributeType())) {
      if (value != null) {
        value = new BigDecimal(value.toString());
      }
    }

    this.writeMethod.invoke(instance, value);
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

  public Class getCollectionEntryType() {
    return collectionEntryType;
  }

  public String getName() {
    return name;
  }

  public boolean isRequired() {
    return required;
  }

  public int getOrder() {
    return order;
  }

  public Type getType() {
    return type;
  }

  public int compareTo(AggregateAttribute other) {
    return this.order - other.order;
  }

  public boolean isCollection() {
    return collection;
  }

  @Override
  public String toString() {
    return this.toString;
  }
}
