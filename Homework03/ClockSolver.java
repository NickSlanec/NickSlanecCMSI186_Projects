/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2019-02-21  Nick Slanec   Completion of Assignment
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private static final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private static final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private static final double MAX_TIME_IN_SECONDS        = 43200;
   private static double EPSILON_VALUE              = 0.1;      // small value for double-precision comparisons
   private static double targetAngle = 0.0;
   private static double timeSlice = 0.0;
   private static int correctAngleCounter = 0;


  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }


  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
     ClockSolver cs = new ClockSolver();
     Clock clock = new Clock();
     double[] timeValues = new double[3];
     System.out.println ("Welcome to the clock solver!");
     //Validate Input
     if (args.length == 0){
       throw new IllegalArgumentException ("Please enter an angle");
     } else if (args.length == 1) {
       targetAngle = clock.validateAngleArg(args[0]);
       timeSlice = clock.validateTimeSliceArg(String.valueOf(DEFAULT_TIME_SLICE_SECONDS));
     } else if (args.length == 2) {
       targetAngle = clock.validateAngleArg(args[0]);
       timeSlice = clock.validateTimeSliceArg(args [1]);
     } else if (args.length == 3) {
       targetAngle = clock.validateAngleArg(args[0]);
       timeSlice = clock.validateTimeSliceArg(args [1]);
       EPSILON_VALUE = clock.validateEpsilon(args [2]);
     }
     //Print Inputs
     System.out.println("Your angle: " + targetAngle);
     System.out.println("Your time slice: " + timeSlice);
     System.out.println("Your elpsilon: " + EPSILON_VALUE);
     //Run clock and check for angles
     while( (double) clock.getTotalSeconds() <= MAX_TIME_IN_SECONDS ) {
       clock.getMinuteHandAngle();
       clock.getHourHandAngle();
       clock.getHandAngle();
        //A testing block that prints every tick
        //System.out.println("At " + clock.toString());
        //System.out.println("Minute Hand Angle: " + clock.getMinuteHandAngle());
        //System.out.println("Hour Hand Angle: " + clock.getHourHandAngle());
        //System.out.println("HAND ANGLE: " + clock.getHandAngle());
       if (((clock.getHandAngle() >= (targetAngle - EPSILON_VALUE)) && (clock.getHandAngle() <= targetAngle + EPSILON_VALUE)) ||
        ((clock.getCounterAngle() >= targetAngle - EPSILON_VALUE) && (clock.getCounterAngle() <= targetAngle + EPSILON_VALUE))) {
         correctAngleCounter += 1;
         System.out.println("Found that angle at: " + clock.toString());
         clock.tick(timeSlice);
       } else {
         clock.tick(timeSlice);
        }
      }
      System.out.println("There are " + correctAngleCounter + " instances of that angle");
      System.exit( 0 );
   }
}
