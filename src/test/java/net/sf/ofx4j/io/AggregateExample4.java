package net.sf.ofx4j.io;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * @author Ryan Heaton
 */
@Aggregate ("EXAMPLE4")
public class AggregateExample4 {

  private String element;

  @Element ( name ="EXAMPLE4EL1", order=0)
  public String getElement() {
    return element;
  }

  public void setElement(String element) {
    this.element = element;
  }
}