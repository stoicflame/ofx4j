package net.sf.ofx4j.io;

import java.util.Arrays;

/**
 * @author Ryan Heaton
 */
public class OFXFormatterHandler extends DefaultHandler {

  private int level = 0;

  @Override
  public void onHeader(String name, String value) {
    System.out.println(name + ":" + value);
  }

  @Override
  public void onElement(String name, String value) {
    char[] tabs = new char[level * 2];
    Arrays.fill(tabs, ' ');
    System.out.println(new String(tabs) + "<" + name + ">" + value);
  }

  @Override
  public void startAggregate(String aggregateName) {
    char[] tabs = new char[level * 2];
    Arrays.fill(tabs, ' ');
    System.out.println(new String(tabs) + "<" + aggregateName + ">");

    level++;
  }

  @Override
  public void endAggregate(String aggregateName) {
    level--;
    
    char[] tabs = new char[level * 2];
    Arrays.fill(tabs, ' ');
    System.out.println(new String(tabs) + "</" + aggregateName + ">");
  }
}
