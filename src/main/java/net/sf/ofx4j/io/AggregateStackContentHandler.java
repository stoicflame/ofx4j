package net.sf.ofx4j.io;

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
  private boolean parsingRoot = false;

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
      AggregateAttribute attribute = this.stack.peek().info.getAttribute(name, this.stack.peek().currentAttributeIndex);
      if (attribute != null && attribute.getType() == AggregateAttribute.Type.ELEMENT) {
        attribute.set(this.conversion.fromString(attribute.getAttributeType(), value), this.stack.peek().aggregate);
        this.stack.peek().currentAttributeIndex = attribute.getOrder();
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
    else if (!parsingRoot) {
      if (!aggregateName.equals(this.stack.peek().info.getName())) {
        throw new IllegalStateException("Unexpected root element: " + aggregateName);
      }

      parsingRoot = true;
    }
    else {
      AggregateInfoHolder infoHolder;

      AggregateAttribute attribute = this.stack.peek().info.getAttribute(aggregateName, this.stack.peek().currentAttributeIndex);
      if (attribute != null) {
        if (attribute.getType() == AggregateAttribute.Type.CHILD_AGGREGATE) {
          Class aggregateType;
          if (attribute.isCollection()) {
            aggregateType = AggregateIntrospector.findAggregateByName(aggregateName);
          }
          else {
            aggregateType = attribute.getAttributeType();
          }

          if (aggregateType != null) {
            AggregateInfo aggregateInfo = AggregateIntrospector.getAggregateInfo(aggregateType);
            if (aggregateInfo == null) {
              throw new IllegalStateException("Unable to locate aggregate info for type " + aggregateType.getName());
            }

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

            infoHolder = new AggregateInfoHolder(aggregate, aggregateInfo);
          }
          else {
            if (LOG.isInfoEnabled()) {
              LOG.info(String.format("Child aggregate %s is not supported on aggregate %s (class %s): name not assigned a type.",
                                     aggregateName,
                                     this.stack.peek().info.getName(),
                                     this.stack.peek().aggregate.getClass().getName()));
            }

            //element not supported.  push a skipping aggregate on the stack.
            infoHolder = new AggregateInfoHolder(aggregateName);
          }

          this.stack.peek().currentAttributeIndex = attribute.getOrder();
        }
        else {
          if (LOG.isInfoEnabled()) {
            LOG.info(String.format("Child aggregate %s is not supported on aggregate %s (class %s): no child aggregate, but there does exist an element by that name.",
                                   aggregateName,
                                   this.stack.peek().info.getName(),
                                   this.stack.peek().aggregate.getClass().getName()));
          }

          //child aggregate not supported.  push a skipping aggregate on the stack.
          infoHolder = new AggregateInfoHolder(aggregateName);
        }
      }
      else {
        if (LOG.isInfoEnabled()) {
          LOG.info(String.format("Child aggregate %s is not supported on aggregate %s (class %s): no attributes found by that name.",
                                 aggregateName,
                                 this.stack.peek().info.getName(),
                                 this.stack.peek().aggregate.getClass().getName()));
        }

        //child aggregate not supported.  push a skipping aggregate on the stack.
        infoHolder = new AggregateInfoHolder(aggregateName);
      }

      this.stack.push(infoHolder);
    }
  }

  public void endAggregate(String aggregateName) {
    AggregateInfoHolder infoHolder = this.stack.pop();
    if (!this.stack.isEmpty()) {
      if (!infoHolder.isSkipping(aggregateName)) {
        //we're not skipping the top aggregate, so process it.
        AggregateAttribute attribute = this.stack.peek().info.getAttribute(aggregateName, this.stack.peek().currentAttributeIndex);
        attribute.set(infoHolder.aggregate, this.stack.peek().aggregate);
        this.stack.peek().currentAttributeIndex = attribute.getOrder();
      }
    }
    else {
      //ended the root element.
    }
  }

  private class AggregateInfoHolder {

    private final Object aggregate;
    private final AggregateInfo info;
    private int currentAttributeIndex = 0;

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
