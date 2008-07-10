package net.sf.ofx4j.ser;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * @author Ryan Heaton
 */
@Aggregate ("EXAMPLE3")
public class AggregateExample3 {

  private String element;

  @Element ( name ="EXAMPLE3EL1", order=0)
  public String getElement() {
    return element;
  }

  public void setElement(String element) {
    this.element = element;
  }
}