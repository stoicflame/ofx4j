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

import net.sf.ofx4j.domain.data.common.Status;
import net.sf.ofx4j.domain.data.common.StatusCode;
import net.sf.ofx4j.domain.data.common.UnknownStatusCode;

import java.sql.Time;
import java.util.*;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * Utility class for conversion to/from OFX strings.
 *
 * @author Ryan Heaton
 */
public class DefaultStringConversion implements StringConversion {

  public static final TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");
  public static final int DATE_FORMAT_LENGTH = "yyyyMMddHHmmss.SSS".length();
  public static final int TIME_FORMAT_LENGTH = "HHmmss.SSS".length();

  public String toString(Object value) {
    if (value == null) {
      return null;
    }
    else if (Boolean.class.isInstance(value)) {
      return ((Boolean) value) ? "Y" : "N";
    }
    else if (Time.class.isInstance(value)) {
      return formatTime((Time) value);
    }
    else if (Date.class.isInstance(value)) {
      return formatDate((Date) value);
    }
    else {
      return String.valueOf(value);
    }
  }

  public <E> E fromString(Class<E> clazz, String value) throws OFXSyntaxException {
    if (value == null) {
      return null;
    }
    else if (String.class.isAssignableFrom(clazz)) {
      return (E) value;
    }
    else if (StatusCode.class.isAssignableFrom(clazz)) {
      int code = 2000;
      try {
        code = Integer.parseInt(value);
      }
      catch (NumberFormatException e) {
        throw new OFXSyntaxException(e);
      }

      StatusCode statusCode = Status.KnownCode.fromCode(code);
      if (statusCode == null) {
        statusCode = new UnknownStatusCode(code, "Unknown status code.", Status.Severity.ERROR);
      }
      
      return (E) statusCode;
    }
    else if (Enum.class.isAssignableFrom(clazz)) {
      return (E) Enum.valueOf((Class<? extends Enum>) clazz, value);
    }
    else if ((Boolean.class.isAssignableFrom(clazz)) || (Boolean.TYPE == clazz)) {
      return (E) (Boolean) "Y".equals(value.toUpperCase());
    }
    else if ((Integer.class.isAssignableFrom(clazz)) || (Integer.TYPE == clazz)) {
      return (E) new Integer(Integer.parseInt(value));
    }
    else if ((Short.class.isAssignableFrom(clazz)) || (Short.TYPE == clazz)) {
      return (E) new Short(Short.parseShort(value));
    }
    else if ((Float.class.isAssignableFrom(clazz)) || (Float.TYPE == clazz)) {
      return (E) new Float(Float.parseFloat(value));
    }
    else if ((Double.class.isAssignableFrom(clazz)) || (Double.TYPE == clazz)) {
      return (E) new Double(Double.parseDouble(value));
    }
    else if (Time.class.isAssignableFrom(clazz)) {
      return (E) parseTime(value);
    }
    else if (Date.class.isAssignableFrom(clazz)) {
      return (E) parseDate(value);
    }
    else if (URL.class.isAssignableFrom(clazz)) {
      try {
        return (E) new URL(value);
      }
      catch (MalformedURLException e) {
        throw new OFXSyntaxException(e);
      }
    }
    return (E) value;
  }

  /**
   * Parses a date according to OFX.
   *
   * @param value The value of the date.
   * @return The date value.
   */
  protected Date parseDate(String value) {
    char[] parseableDate = new char[DATE_FORMAT_LENGTH];
    Arrays.fill(parseableDate, '0');
    parseableDate[parseableDate.length - 4] = '.';
    char[] valueChars = value.toCharArray();
    int index = 0;
    while (index < valueChars.length && valueChars[index] != '[') {
      if (index < DATE_FORMAT_LENGTH) {
        parseableDate[index] = valueChars[index];
      }
      
      index++;
    }

    int year = Integer.parseInt(new String(parseableDate, 0, 4));
    int month = Integer.parseInt(new String(parseableDate, 4, 2)) - 1; //java month numberss are zero-based
    int day = Integer.parseInt(new String(parseableDate, 6, 2));
    int hour = Integer.parseInt(new String(parseableDate, 8, 2));
    int minute = Integer.parseInt(new String(parseableDate, 10, 2));
    int second = Integer.parseInt(new String(parseableDate, 12, 2));
    int milli = Integer.parseInt(new String(parseableDate, 15, 3));

    //set up a new calendar at zero, then set all the fields.
    GregorianCalendar calendar = new GregorianCalendar(year, month, day, hour, minute, second);
    if (index < valueChars.length && valueChars[index] == '[') {
      String tzoffset = value.substring(index);
      calendar.setTimeZone(parseTimeZone(tzoffset));
    }
    else {
      calendar.setTimeZone(GMT_TIME_ZONE);
    }
    calendar.add(GregorianCalendar.MILLISECOND, milli);

    return calendar.getTime();
  }

  /**
   * Format the date according to the OFX spec.
   *
   * @param date The date.
   * @return The date format.
   */
  protected String formatDate(Date date) {
    GregorianCalendar calendar = new GregorianCalendar(GMT_TIME_ZONE);
    calendar.setTime(date);
    return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS.%1$tL", calendar);
  }

  /**
   * Parses a time according to OFX.
   *
   * @param value The value of the date.
   * @return The date value.
   */
  protected Time parseTime(String value) {
    char[] parseableTime = new char[TIME_FORMAT_LENGTH];
    Arrays.fill(parseableTime, '0');
    parseableTime[parseableTime.length - 4] = '.';
    value.getChars(0, Math.min(parseableTime.length, value.length()), parseableTime, 0);

    int hour = Integer.parseInt(new String(parseableTime, 0, 2));
    int minute = Integer.parseInt(new String(parseableTime, 2, 2));
    int second = Integer.parseInt(new String(parseableTime, 4, 2));
    int milli = Integer.parseInt(new String(parseableTime, 7, 3));

    //set up a new calendar at zero, then set all the fields.
    GregorianCalendar calendar = new GregorianCalendar(0, 0, 0, hour, minute, second);
    if (value.length() > parseableTime.length) {
      String tzoffset = value.substring(parseableTime.length);
      calendar.setTimeZone(parseTimeZone(tzoffset));
    }
    else {
      calendar.setTimeZone(GMT_TIME_ZONE);
    }
    calendar.add(GregorianCalendar.MILLISECOND, milli);

    return new Time(calendar.getTimeInMillis());
  }

  /**
   * Format the time according to the OFX spec.
   *
   * @param time The time to format.
   * @return The formatted time.
   */
  protected String formatTime(Time time) {
    GregorianCalendar calendar = new GregorianCalendar(GMT_TIME_ZONE);
    calendar.setTime(time);
    return String.format("%1$tH%1$tM%1$tS.%1$tL", calendar);
  }

  /**
   * Parse the timezone offset of the form [HOURS_OFF_GMT:TZ_ID]
   *
   * @param tzoffset The offset pattern.
   * @return The timezone.
   */
  protected TimeZone parseTimeZone(String tzoffset) {
    StringTokenizer tokenizer = new StringTokenizer(tzoffset, "[]:");
    TimeZone tz = GMT_TIME_ZONE;
    if (tokenizer.hasMoreTokens()) {
      String hoursOff = tokenizer.nextToken();
      tz = TimeZone.getTimeZone("GMT" + hoursOff);
    }

    return tz;
  }
}
