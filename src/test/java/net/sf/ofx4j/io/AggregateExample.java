package net.sf.ofx4j.io;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.ChildAggregate;
import net.sf.ofx4j.meta.Element;
import net.sf.ofx4j.meta.Header;

import java.util.List;

/**
 * @author Ryan Heaton
 */
@Aggregate ( "EXAMPLE" )
public class AggregateExample {

  private String header1;
  private String header2;
  private String element1;
  private AggregateExample2 aggregate1;
  private AggregateExample2 aggregate2;
  private List aggregateList;

  @Header ( name = "HEADER1" )
  public String getHeader1() {
    return header1;
  }

  public void setHeader1(String header1) {
    this.header1 = header1;
  }

  @Header ( name = "ANOTHERHEADER" )
  public String getHeader2() {
    return header2;
  }

  public void setHeader2(String header2) {
    this.header2 = header2;
  }

  @Element ( name = "SOMEELEMENT", order = 0 )
  public String getElement1() {
    return element1;
  }

  public void setElement1(String element1) {
    this.element1 = element1;
  }

  @ChildAggregate ( order = 10 )
  public AggregateExample2 getAggregate1() {
    return aggregate1;
  }

  public void setAggregate1(AggregateExample2 aggregate1) {
    this.aggregate1 = aggregate1;
  }

  @ChildAggregate ( name = "DIFFERENT", order = 20 )
  public AggregateExample2 getAggregate2() {
    return aggregate2;
  }

  public void setAggregate2(AggregateExample2 aggregate2) {
    this.aggregate2 = aggregate2;
  }

  @ChildAggregate ( order = 12 )
  public List getAggregateList() {
    return aggregateList;
  }

  public void setAggregateList(List aggregateList) {
    this.aggregateList = aggregateList;
  }
}
