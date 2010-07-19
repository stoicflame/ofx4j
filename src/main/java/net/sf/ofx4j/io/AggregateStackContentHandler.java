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

    this.stack.push(new AggregateInfoHolder(root, aggregateInfo, aggregateInfo.getName()));
    this.conversion = conversion;
  }

  public void onHeader(String name, String value) throws OFXSyntaxException {
    Class headerType = this.stack.peek().info.getHeaderType(name);
    if (headerType != null) {
      this.stack.peek().info.setHeader(this.stack.peek().aggregate, name, this.conversion.fromString(headerType, value));
    }
  }

  public void onElement(String name, String value) throws OFXSyntaxException {
    if (!this.stack.peek().isBeingSkipped()) {
      AggregateAttribute attribute = this.stack.peek().info.getAttribute(name, this.stack.peek().currentAttributeIndex);
      if (attribute != null && attribute.getType() == AggregateAttribute.Type.ELEMENT) {
        try {
          attribute.set(this.conversion.fromString(attribute.getAttributeType(), value), this.stack.peek().aggregate);
        }
        catch (Exception e) {
          LOG.error("Unable to set " + attribute.toString(), e);
        }
        this.stack.peek().currentAttributeIndex = attribute.getOrder();
      }
      else if (LOG.isInfoEnabled()) {
        LOG.info(String.format("Element %s is not supported on aggregate %s (class %s) at index %s.",
                               name,
                               this.stack.peek().info.getName(),
                               this.stack.peek().aggregate.getClass().getName(),
                               this.stack.peek().currentAttributeIndex));
      }
    }
  }

  public void startAggregate(String aggregateName) throws OFXSyntaxException {
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

            infoHolder = new AggregateInfoHolder(aggregate, aggregateInfo, aggregateName);
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
          LOG.info(String.format("Child aggregate %s is not supported on aggregate %s (class %s): no attributes found by that name after index %s.",
                                 aggregateName,
                                 this.stack.peek().info.getName(),
                                 this.stack.peek().aggregate.getClass().getName(),
                                 this.stack.peek().currentAttributeIndex));
        }

        //child aggregate not supported.  push a skipping aggregate on the stack.
        infoHolder = new AggregateInfoHolder(aggregateName);
      }

      this.stack.push(infoHolder);
    }
  }

  public void endAggregate(String aggregateName) throws OFXSyntaxException {
    AggregateInfoHolder infoHolder = this.stack.pop();
    if (!aggregateName.equals(infoHolder.aggregateName)) {
      throw new OFXSyntaxException("Unexpected end aggregate " + aggregateName + ". (Perhaps " +
        infoHolder.aggregateName + " is an element with an empty value, making it impossible to parse.)");
    }

    if (!this.stack.isEmpty()) {
      if (!infoHolder.isSkipping(aggregateName)) {
        //we're not skipping the top aggregate, so process it.
        AggregateAttribute attribute = this.stack.peek().info.getAttribute(
            aggregateName, this.stack.peek().currentAttributeIndex, infoHolder.aggregate.getClass());
        try {
          if (attribute != null) {
            attribute.set(infoHolder.aggregate, this.stack.peek().aggregate);
          } else {
            if (LOG.isInfoEnabled()) {
              LOG.info(String.format("Child aggregate %s is not supported on aggregate %s (class %s): no attributes found by that name after index %s.",
                                     aggregateName,
                                     this.stack.peek().info.getName(),
                                     this.stack.peek().aggregate.getClass().getName(),
                                     this.stack.peek().currentAttributeIndex));
            }
          }
        }
        catch (Exception e) {
          LOG.error("Unable to set " + attribute.toString(), e);
        }
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
    private final String aggregateName;
    private int currentAttributeIndex = 0;

    private AggregateInfoHolder(String ignoredAggregateName) {
      this.aggregate = null;
      this.info = null;
      this.aggregateName = ignoredAggregateName;
    }

    private AggregateInfoHolder(Object aggregate, AggregateInfo info, String aggregateName) {
      this.aggregateName = aggregateName;
      this.aggregate = aggregate;
      this.info = info;
    }

    public boolean isBeingSkipped() {
      return this.aggregate == null || this.info == null;
    }

    public boolean isSkipping(String aggregateName) {
      return isBeingSkipped() && aggregateName.equals(this.aggregateName);
    }
  }
}
