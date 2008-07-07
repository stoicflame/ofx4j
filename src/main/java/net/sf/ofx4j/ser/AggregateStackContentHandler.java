package net.sf.ofx4j.ser;

import net.sf.ofx4j.io.OFXHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Stack;

/**
 * Content handler that manages the aggregate using a stack-based implementation.
 *
 * @author Ryan Heaton
 */
public class AggregateStackContentHandler<A> implements OFXHandler {

  private static final Log LOG = LogFactory.getLog(AggregateStackContentHandler.class);

  private final Stack<AggregateInfoHolder> stack = new Stack<AggregateInfoHolder>();
  private final StringConversion conversion;

  public AggregateStackContentHandler(A root, StringConversion conversion) {
    AggregateInfo aggregateInfo = AggregateIntrospector.getAggregateInfo(root.getClass());
    if (aggregateInfo == null) {
      throw new IllegalArgumentException(String.format("Unable to marshal object of type %s (no aggregate metadata found).", root.getClass().getName()));
    }

    this.stack.push(new AggregateInfoHolder(root, aggregateInfo));
    this.conversion = conversion;
  }

  public void onHeader(String name, String value) {
    Class headerType = this.stack.peek().info.getHeaderType(name);
    if (headerType != null) {
      this.stack.peek().info.setHeader(this.stack.peek().aggregate, name, this.conversion.fromString(headerType, value));
    }
  }

  public void onElement(String name, String value) {
    if (!this.stack.peek().isBeingSkipped()) {
      AggregateAttribute attribute = this.stack.peek().info.getAttribute(name);
      if (attribute != null && attribute.getType() == AggregateAttribute.Type.ELEMENT) {
        attribute.set(this.conversion.fromString(attribute.getAttributeType(), value), this.stack.peek().aggregate);
      }
      else if (LOG.isInfoEnabled()) {
        LOG.info(String.format("Element %s is not supported on aggregate %s (class %s).", name, this.stack.peek().info.getName(), this.stack.peek().aggregate.getClass().getName()));
      }
    }
  }

  public void startAggregate(String aggregateName) {
    if (this.stack.peek().isBeingSkipped()) {
      this.stack.push(new AggregateInfoHolder(aggregateName));
    }
    else {
      AggregateAttribute attribute = this.stack.peek().info.getAttribute(aggregateName);
      Class aggregateType;
      if (attribute != null && attribute.getType() == AggregateAttribute.Type.CHILD_AGGREGATE) {
        aggregateType = attribute.getAttributeType();
      }
      else {
        aggregateType = AggregateIntrospector.findAggregateByName(aggregateName);
      }

      if (aggregateType != null) {
        AggregateInfo aggregateInfo = AggregateIntrospector.getAggregateInfo(aggregateType);
        Object aggregate;
        try {
          aggregate = aggregateType.newInstance();
        }
        catch (RuntimeException e) {
          throw e;
        }
        catch (Exception e) {
          throw new IllegalStateException(e);
        }
        
        this.stack.push(new AggregateInfoHolder(aggregate, aggregateInfo));
      }
      else {
        if (LOG.isInfoEnabled()) {
          LOG.info(String.format("Element %s is not supported on aggregate %s (class %s).",
                                 aggregateName,
                                 this.stack.peek().info.getName(),
                                 this.stack.peek().aggregate.getClass().getName()));
        }

        //element not supported.  push a skipping aggregate on the stack.
        this.stack.push(new AggregateInfoHolder(aggregateName));
      }
    }
  }

  public void endAggregate(String aggregateName) {
    AggregateInfoHolder infoHolder = this.stack.pop();
    if (!infoHolder.isSkipping(aggregateName)) {
      //we're not skipping the top aggregate, so process it.
      infoHolder.info.getAttribute(aggregateName).set(infoHolder.aggregate, this.stack.peek());
    }
  }

  private class AggregateInfoHolder {

    private final Object aggregate;
    private final AggregateInfo info;

    private AggregateInfoHolder(String ignoredAggregateName) {
      this.aggregate = ignoredAggregateName;
      this.info = null;
    }

    private AggregateInfoHolder(Object aggregate, AggregateInfo info) {
      this.aggregate = aggregate;
      this.info = info;
    }

    public boolean isBeingSkipped() {
      return this.aggregate instanceof String;
    }

    public boolean isSkipping(String aggregateName) {
      return aggregateName.equals(this.aggregate);
    }
  }
}
