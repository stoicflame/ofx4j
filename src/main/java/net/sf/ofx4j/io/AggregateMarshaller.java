package net.sf.ofx4j.io;

import net.sf.ofx4j.io.OFXWriter;

import java.io.IOException;
import java.util.*;

/**
 * Marshaller for aggregate objects.
 *
 * @author Ryan Heaton
 */
public class AggregateMarshaller {

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
      Object childValue = aggregateAttribute.get(aggregate);
      
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
