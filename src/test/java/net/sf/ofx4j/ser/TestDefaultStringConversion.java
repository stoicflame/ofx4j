package net.sf.ofx4j.ser;

import junit.framework.TestCase;

import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.sql.Time;

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
