/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Nick Slanec
 *  Date          :  1/28/19
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2019-01-28  Nick Slanec
 */
public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;


  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   */
   private static int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      if ((year % 4 == 0) && (year % 100 != 0)) {
       return true;
    } else {
       return false;
    }
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
    if ((isLeapYear(year) == true) && (month == 2)){
      return 29;
    }
    else {
      return days[(int)(month - 1)];
    }
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
    if((month1 == month2) && (day1 == day2) && (year1 == year2)) {
      return true;
    }else{
      return false;
    }
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     if (year1 < year2){
        return -1;
     } else if (year1 > year2) {
        return 1;
     } else {
       if (month1 < month2) {
          return -1;
       } else if (month1 > month2){
          return 1;
       } else {
         if (day1 < day2) {
            return -1;
         } else if (day1 > day2) {
            return 1;
         } else {
           return 0;
         }
        }
       }
    }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      if (month <= 0 || day <= 0 || year < 0 || month > 12){
        return false;
      }
      if ((days[(int) (month - 1)]) < day ) {
        return false;
      }
      if (!isLeapYear(year) && month == 2 && day == 29 ){
        return false;
      }
      return true;
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   * This code is heavily based on the example on Oracle's website: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
   */
    public static String toMonthString( int month ) {
      String monthString;
      switch( month - 1) {
        case 0 : monthString = "January";
          break;
        case 1 :  monthString = "February";
          break;
        case 2 :  monthString = "March";
          break;
        case 3 :  monthString = "April";
          break;
        case 4 :  monthString = "May";
          break;
        case 5 :  monthString = "June";
          break;
        case 6 :  monthString = "July";
          break;
        case 7 :  monthString = "August";
          break;
        case 8 :  monthString = "September";
          break;
        case 9 :  monthString = "October";
          break;
        case 10:  monthString = "November";
          break;
        case 11:  monthString = "December";
          break;
        default: monthString = "Invalid Month";
          break;
      }
      return monthString;
    }


  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   * This code is heavily based on the example on Oracle's website: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
   */
   public static String toDayOfWeekString( int day ) {
     String dayString;
     switch( day - 1 ) {
       case 0 :  dayString = "Sunday";
         break;
       case 1 :  dayString = "Monday";
         break;
       case 2 :  dayString = "Tuesday";
         break;
       case 3 :  dayString = "Wednesday";
         break;
       case 4 :  dayString = "Thursday";
         break;
       case 5 :  dayString = "Friday";
         break;
       case 6 :  dayString = "Saturday";
         break;
       default:  dayString = "Invalid Day";
         break;
     }
     return dayString;
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     long dayCount = 0;
     long earlierMonth = 0;
     long earlierDay = 0;
     long earlierYear = 0;
     long laterMonth = 0;
     long laterDay = 0;
     long laterYear = 0;
     if (isValidDate(month1, day1, year1) && isValidDate(month2, day2, year2)) {

        if (compareDate(month1, day1, year1, month2, day2, year2) == -1) {
        earlierMonth = month1;
        earlierDay = day1;
        earlierYear = year1;
        laterMonth = month2;
        laterDay = day2;
        laterYear = year2;
      } else if (compareDate(month1, day1, year1, month2, day2, year2) == 1){
        earlierMonth = month2;
        earlierDay = day2;
        earlierYear = year2;
        laterMonth = month1;
        laterDay = day1;
        laterYear = year1;
      } else if (compareDate(month1, day1, year1, month2, day2, year2) == 0){
        dayCount = 0;
        return dayCount;
        }
        if ((month1 > month2) && (compareDate(month1, day1, year1, month2, day2, year2) == -1)) {
          dayCount += ((year2 - (year1 + 1)) * 365);
          for (long monthHedge1 = month1; monthHedge1 <= 12; monthHedge1++){
            dayCount = dayCount + days[(int)(monthHedge1 - 1)];
          }
          for (long monthHedge2 = 1; monthHedge2 < month2; monthHedge2++){
            dayCount = dayCount + days[(int)(monthHedge2 - 1)];
          }
          dayCount = (dayCount - day1 + day2);
          for (long year = earlierYear; year <= laterYear; year++){
            if (isLeapYear(year)){
              dayCount = dayCount + 1;
            }
          }
          return dayCount;
        }

        for(long month = earlierMonth; month < laterMonth; month++){
          dayCount = dayCount + days[(int)(month - 1)];
        }
        long yearDays = (laterYear - earlierYear) * 365;
        dayCount = (dayCount - earlierDay + laterDay + yearDays);
        for (long year = earlierYear; year <= laterYear; year++){
          if (isLeapYear(year)){
            dayCount = dayCount + 1;
          }
        }
        return dayCount;
     }  else {
        return dayCount;
        }
   }
}
