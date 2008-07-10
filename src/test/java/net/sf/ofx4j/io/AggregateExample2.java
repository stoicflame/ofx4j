package net.sf.ofx4j.io;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * @author Ryan Heaton
 */
@Aggregate ("EXAMPLE2")
public class AggregateExample2 {

  private String element;

  @Element ( name ="EXAMPLE2EL1", order=0)
  public String getElement() {
    return element;
  }

  public void setElement(String element) {
    this.element = element;
  }
}
