/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private static final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private double hourHandPosition = 0.0;
   private double minuteHandPosition = 0.0;
   private double elapsedTime = 0.0;
   private static double timeSlice = 0.0;
   private static double targetAngle = 0.0;
   private int hoursPassed = 0;
   private int minutesPassed = 0;
   private double adjustedElapsedTime = 0.0;
   private static double epsilonValue = 0.0;

  /**
   *  Constructor goes here
   */
   public Clock() {
     this.hourHandPosition = 0;
     this.minuteHandPosition = 0;
     this.elapsedTime = 0;
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick(double timeSlice) {
     return elapsedTime += timeSlice;
   }

  /**
   *  Method to validate the angle argument
   *  @param   angleArg String from the main programs args[0] input
   *  @return  targetAngle double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public static double validateAngleArg( String angleArg ) {
     targetAngle = Double.parseDouble(angleArg);
      if (targetAngle > 0 && targetAngle < MAXIMUM_DEGREE_VALUE){
        return targetAngle;
      } else {
        throw new IllegalArgumentException ("Please return an angle between 0 and 359");
      }
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public static double validateTimeSliceArg( String argValue ) {
      timeSlice = Double.parseDouble(argValue);
      if (timeSlice > 0 && timeSlice < MAX_TIME_SLICE_IN_SECONDS){
        return timeSlice;
      } else {
        return INVALID_ARGUMENT_VALUE;
      }
   }


   /**
   * Method to validate an optional epsilon value
   */
   public static double validateEpsilon( String epsilonArg ) {
      epsilonValue = Double.parseDouble(epsilonArg);
      if (epsilonValue > 0) {
        return epsilonValue;
      } else {
        return INVALID_ARGUMENT_VALUE;
      }
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      hourHandPosition = (elapsedTime * HOUR_HAND_DEGREES_PER_SECOND);
      return hourHandPosition;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      minuteHandPosition = ((elapsedTime * MINUTE_HAND_DEGREES_PER_SECOND) % 360);
      return minuteHandPosition;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      return Math.abs(hourHandPosition - minuteHandPosition);
   }

   public double getCounterAngle() {
     return (360.00 - this.getHandAngle());
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return elapsedTime;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
    hoursPassed =((int)(elapsedTime / 3600));
    adjustedElapsedTime = (elapsedTime - (hoursPassed * 3600));
    minutesPassed = ((int)(adjustedElapsedTime / 60));
    adjustedElapsedTime = (adjustedElapsedTime - (minutesPassed * 60));
    return (hoursPassed + ":" + minutesPassed + ":" + adjustedElapsedTime);
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  2 degrees', expecting double value   2.0" );
      try { System.out.println( (2.0 == clock.validateAngleArg( "2.0" )) ? " - got 2.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
