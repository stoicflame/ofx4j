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

import junit.framework.TestCase;

import java.util.Date;
import java.util.GregorianCalendar;
import java.sql.Time;

import net.sf.ofx4j.io.DefaultStringConversion;

/**
 * @author Ryan Heaton
 */
public class TestDefaultStringConversion extends TestCase {

  /**
   * tests date parsing
   */
  public void testDateParsing() throws Exception {
    DefaultStringConversion conversion = new DefaultStringConversion();

    Date date = conversion.parseDate("20061005132200.124[-5:EST]");
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.setTimeZone(DefaultStringConversion.GMT_TIME_ZONE);
    assertEquals(2006, calendar.get(GregorianCalendar.YEAR));
    assertEquals(GregorianCalendar.OCTOBER, calendar.get(GregorianCalendar.MONTH));
    assertEquals(5, calendar.get(GregorianCalendar.DAY_OF_MONTH));
    assertEquals(18, calendar.get(GregorianCalendar.HOUR_OF_DAY));
    assertEquals(22, calendar.get(GregorianCalendar.MINUTE));
    assertEquals(0, calendar.get(GregorianCalendar.SECOND));
    assertEquals(124, calendar.get(GregorianCalendar.MILLISECOND));
    assertEquals("20061005182200.124", conversion.toString(date));

    date = conversion.parseDate("20061005132200.124");
    calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.setTimeZone(DefaultStringConversion.GMT_TIME_ZONE);
    assertEquals(2006, calendar.get(GregorianCalendar.YEAR));
    assertEquals(GregorianCalendar.OCTOBER, calendar.get(GregorianCalendar.MONTH));
    assertEquals(5, calendar.get(GregorianCalendar.DAY_OF_MONTH));
    assertEquals(13, calendar.get(GregorianCalendar.HOUR_OF_DAY));
    assertEquals(22, calendar.get(GregorianCalendar.MINUTE));
    assertEquals(0, calendar.get(GregorianCalendar.SECOND));
    assertEquals(124, calendar.get(GregorianCalendar.MILLISECOND));
    assertEquals("20061005132200.124", conversion.toString(date));

    date = conversion.parseDate("2006100513");
    calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.setTimeZone(DefaultStringConversion.GMT_TIME_ZONE);
    assertEquals(2006, calendar.get(GregorianCalendar.YEAR));
    assertEquals(GregorianCalendar.OCTOBER, calendar.get(GregorianCalendar.MONTH));
    assertEquals(5, calendar.get(GregorianCalendar.DAY_OF_MONTH));
    assertEquals(13, calendar.get(GregorianCalendar.HOUR_OF_DAY));
    assertEquals(0, calendar.get(GregorianCalendar.MINUTE));
    assertEquals(0, calendar.get(GregorianCalendar.SECOND));
    assertEquals(0, calendar.get(GregorianCalendar.MILLISECOND));
    assertEquals("20061005130000.000", conversion.toString(date));

    date = conversion.parseDate("20061005");
    calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.setTimeZone(DefaultStringConversion.GMT_TIME_ZONE);
    assertEquals(2006, calendar.get(GregorianCalendar.YEAR));
    assertEquals(GregorianCalendar.OCTOBER, calendar.get(GregorianCalendar.MONTH));
    assertEquals(5, calendar.get(GregorianCalendar.DAY_OF_MONTH));
    assertEquals(0, calendar.get(GregorianCalendar.HOUR_OF_DAY));
    assertEquals(0, calendar.get(GregorianCalendar.MINUTE));
    assertEquals(0, calendar.get(GregorianCalendar.SECOND));
    assertEquals(0, calendar.get(GregorianCalendar.MILLISECOND));
    assertEquals("20061005000000.000", conversion.toString(date));

    date = conversion.parseDate("20060624120000[0:GMT]");
    calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.setTimeZone(DefaultStringConversion.GMT_TIME_ZONE);
    assertEquals(2006, calendar.get(GregorianCalendar.YEAR));
    assertEquals(GregorianCalendar.JUNE, calendar.get(GregorianCalendar.MONTH));
    assertEquals(24, calendar.get(GregorianCalendar.DAY_OF_MONTH));
    assertEquals(12, calendar.get(GregorianCalendar.HOUR_OF_DAY));
    assertEquals(0, calendar.get(GregorianCalendar.MINUTE));
    assertEquals(0, calendar.get(GregorianCalendar.SECOND));
    assertEquals(0, calendar.get(GregorianCalendar.MILLISECOND));
  }

  /**
   * tests time parsing
   */
  public void testTimeParsing() throws Exception {
    DefaultStringConversion conversion = new DefaultStringConversion();

    Time time = conversion.parseTime("132200.124[-5:EST]");
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(time);
    calendar.setTimeZone(DefaultStringConversion.GMT_TIME_ZONE);
    assertEquals(18, calendar.get(GregorianCalendar.HOUR_OF_DAY));
    assertEquals(22, calendar.get(GregorianCalendar.MINUTE));
    assertEquals(0, calendar.get(GregorianCalendar.SECOND));
    assertEquals(124, calendar.get(GregorianCalendar.MILLISECOND));
    assertEquals("182200.124", conversion.toString(time));

    time = conversion.parseTime("132200.124");
    calendar = new GregorianCalendar();
    calendar.setTime(time);
    calendar.setTimeZone(DefaultStringConversion.GMT_TIME_ZONE);
    assertEquals(13, calendar.get(GregorianCalendar.HOUR_OF_DAY));
    assertEquals(22, calendar.get(GregorianCalendar.MINUTE));
    assertEquals(0, calendar.get(GregorianCalendar.SECOND));
    assertEquals(124, calendar.get(GregorianCalendar.MILLISECOND));
    assertEquals("132200.124", conversion.toString(time));

    time = conversion.parseTime("13");
    calendar = new GregorianCalendar();
    calendar.setTime(time);
    calendar.setTimeZone(DefaultStringConversion.GMT_TIME_ZONE);
    assertEquals(13, calendar.get(GregorianCalendar.HOUR_OF_DAY));
    assertEquals(0, calendar.get(GregorianCalendar.MINUTE));
    assertEquals(0, calendar.get(GregorianCalendar.SECOND));
    assertEquals(0, calendar.get(GregorianCalendar.MILLISECOND));
    assertEquals("130000.000", conversion.toString(time));
  }

}
