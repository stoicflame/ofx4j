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
