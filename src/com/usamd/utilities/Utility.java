/**
 * 
 */
package com.usamd.utilities;

import java.util.Calendar;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Utility.
 *
 * @author USAWebMD
 */
public abstract class Utility {
  /**
   * <p>Checks if the first date is before the second date ignoring time.</p>
   * @param date1 the first date, not altered, not null
   * @param date2 the second date, not altered, not null
   * @return true if the first date day is before the second date day.
   * @throws IllegalArgumentException if the date is <code>null</code>
   */
  public static boolean isBeforeDay(Date date1, Date date2) {
      if (date1 == null || date2 == null) {
          throw new IllegalArgumentException("The dates must not be null");
      }
      Calendar cal1 = Calendar.getInstance();
      cal1.setTime(date1);
      Calendar cal2 = Calendar.getInstance();
      cal2.setTime(date2);
      return isBeforeDay(cal1, cal2);
  }
  
  /**
   * <p>Checks if the first calendar date is before the second calendar date ignoring time.</p>
   * @param cal1 the first calendar, not altered, not null.
   * @param cal2 the second calendar, not altered, not null.
   * @return true if cal1 date is before cal2 date ignoring time.
   * @throws IllegalArgumentException if either of the calendars are <code>null</code>
   */
  public static boolean isBeforeDay(Calendar cal1, Calendar cal2) {
      if (cal1 == null || cal2 == null) {
          throw new IllegalArgumentException("The dates must not be null");
      }
      if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return true;
      if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return false;
      if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return true;
      if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return false;
      return cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR);
  }

}
