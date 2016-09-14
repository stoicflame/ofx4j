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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.*;

/**
 * Marshaller for aggregate objects.
 *
 * @author Ryan Heaton
 */
public class AggregateMarshaller {

  private static final Log LOG = LogFactory.getLog(AggregateMarshaller.class);

  private StringConversion conversion = new DefaultStringConversion();

  /**
   * Marshal the specified aggregate object.
   *
   * @param aggregate The aggregate to marshal.
   * @param writer    The writer.
   */
  public void marshal(Object aggregate, OFXWriter writer) throws IOException {
    AggregateInfo aggregateInfo = AggregateIntrospector.getAggregateInfo(aggregate.getClass());
    if (aggregateInfo == null) {
      throw new IllegalArgumentException(String.format("Unable to marshal object of type %s (no aggregate metadata found).", aggregate.getClass().getName()));
    }

    if (aggregateInfo.hasHeaders()) {
      Map<String, Object> headerValues = aggregateInfo.getHeaders(aggregate);
      Map<String, String> convertedValues = new TreeMap<String, String>();
      for (String header : headerValues.keySet()) {
        convertedValues.put(header, getConversion().toString(headerValues.get(header)));
      }
      writer.writeHeaders(convertedValues);
    }

    writer.writeStartAggregate(aggregateInfo.getName());
    SortedSet<AggregateAttribute> AggregateAttributes = aggregateInfo.getAttributes();
    writeAggregateAttributes(aggregate, writer, AggregateAttributes);
    writer.writeEndAggregate(aggregateInfo.getName());
  }

  /**
   * Write the aggregate attributes for the specified aggregate.
   *
   * @param aggregate           The aggregate.
   * @param writer              The writer.
   * @param aggregateAttributes The aggregate attributes.
   */
  protected void writeAggregateAttributes(Object aggregate, OFXWriter writer, SortedSet<AggregateAttribute> aggregateAttributes) throws IOException {
    for (AggregateAttribute aggregateAttribute : aggregateAttributes) {
      Object childValue = null;
      try {
        childValue = aggregateAttribute.get(aggregate);
      }
      catch (Exception e) {
        LOG.error(String.format("Unable to get %s", aggregateAttribute.toString()), e);
      }

      if (childValue != null) {
        switch (aggregateAttribute.getType()) {
          case CHILD_AGGREGATE:
            Collection childValues;
            if (childValue instanceof Collection) {
              childValues = (Collection) childValue;
            }
            else {
              childValues = Arrays.asList(childValue);
            }

            for (Object value : childValues) {
              AggregateInfo aggregateInfo = AggregateIntrospector.getAggregateInfo(value.getClass());
              if (aggregateInfo == null) {
                throw new IllegalArgumentException(String.format("Unable to marshal object of type %s (no aggregate metadata found).", value.getClass().getName()));
              }

              String attributeName = aggregateAttribute.getName();
              if (aggregateAttribute.isCollection()) {
                attributeName = aggregateInfo.getName();
              }
              
              writer.writeStartAggregate(attributeName);
              writeAggregateAttributes(value, writer, aggregateInfo.getAttributes());
              writer.writeEndAggregate(attributeName);
            }
            break;
          case ELEMENT:
            String value = getConversion().toString(childValue);
            if ((value != null) && (!"".equals(value.trim()))) {
              writer.writeElement(aggregateAttribute.getName(), value);
            }
            break;
          default:
            throw new IllegalStateException("Unknown aggregate attribute type: " + aggregateAttribute.getType());
        }
      }
      else if (aggregateAttribute.isRequired()) {
        throw new RequiredAttributeException("Required " + aggregateAttribute.toString() + " is null or empty.");
      }
    }
  }

  /**
   * The conversion.
   *
   * @return The conversion.
   */
  public StringConversion getConversion() {
    return conversion;
  }

  /**
   * The conversion.
   *
   * @param conversion The conversion.
   */
  public void setConversion(StringConversion conversion) {
    this.conversion = conversion;
  }
}
