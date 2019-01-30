public class CountTheDays {
  public static void main (String [] args) {
    CalendarStuff calendarStuff = new CalendarStuff();

    long month1 = args [0];
    long day1 = args [1];
    long year1 = args [2];
    long month = args [3];
    long day2 = args [4];
    long year2 = args [5];
    long daysBetween = calendarStuff.daysBetween(month1, day1, year1, month2, day2, year2);
  }
}
