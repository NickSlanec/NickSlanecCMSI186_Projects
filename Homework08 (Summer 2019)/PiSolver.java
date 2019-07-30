import java.text.DecimalFormat;

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  PiSolver.java
 * Purpose    :  Determines Pi using a Monte Carlo simulation
 * @author    :  Nick Slanec
 * Date       :  2019-07-26
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework08.html'>Assignment Page</a>
 * Notes      :  C:\Users\25703\Desktop\Files\Code\Homework08\PiSolver.java
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2019-07-26  Nick Slanec   Initial writing
 *  1.1.0  2019-07-29  Nick Slanec   Removed any hardcoding, Created default input, Created feature to
 *                                   turn on debug from terminal
 *  1.2.0  2019-07-30  Nick Slanec   Added a time remaining feature that calculates how much time is left
 *                                   in the process and displays it in the terminal
 *  1.2.1  2019-07-30  Nick Slanec   Added comments
 *  1.3.0  2019-07-30  Nick Slanec   Cleaned up the time remaining feature and added a live preview of 
 *                                   estimated pi
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class PiSolver {
    public static double total = 0;
    public static double circle = 0;
    public static long points = 100000000; // Default number of darts
    public static double pi = 0;
    public static int r = 50; // Radius of circle
    public static boolean debugOn = false;
    public static DecimalFormat df = new DecimalFormat("####");
    public static DecimalFormat df2 = new DecimalFormat("#.######");

    public static double simulate(long points){
        double centerX = r;
        double centerY = r;
        double estimatedPi = 0;
        int modLength = 1000000;                                                                   // Size of Time Slice
        long startTime = System.currentTimeMillis();                                               //Records what time the program starts for later calculation
        //System.out.println("Start Time: " + startTime);
        System.out.println("Calculating...");
        for (long i = 0; i <= points; i++){
            if (i % modLength == 1 && i != 1){                                                     //The numbers would be too small if we calculated how long each iteration takes, so we are timing how long modlength iterations takes (in this case 1000000) and using that as one "Timing loop"
                long currentTime = System.currentTimeMillis();                                     // Pulls current time in the loop
                long timeElapsed = currentTime - startTime;                                        // Determines how much time has passed between starting the process and now
                double loopTime = timeElapsed / (i/modLength);                                     // Takes how much time is passed and divides it by what number time loop we are on (A time loop is what im calling the group of 1000000 iterations defined by our modLength). This gives us how long it takes to complete one time loop
                double totalTime = loopTime * (points/modLength);                                  // Multiplies the time it takes to complete one time loop by the number of time loops needed to complete the process. (points/modLength gives us the number of time loops in the whole process). Gives us how long the program is predicted to take
                double timeRemaining = totalTime - timeElapsed;                                    // Subtracts the total time the process is predicted to take by how much time has passed. 
                System.out.print("\r");                                                            // This is here so the line below keeps replacing itself instead of creating new lines every update. 
                System.out.print("Time Remaining: " + df.format(timeRemaining / 1000) + " || ");   // Time remaining will count down by seconds
                System.out.print("Estimated Pi: " + df2.format(estimatedPi));
            }
            double x = (Math.floor(Math.random() * (r*2)) + 1);                                    // Random x co-ordinate
            double y = (Math.floor(Math.random() * (r*2)) + 1);                                    // Random y co-ordinate
            double d = Math.sqrt((Math.pow(centerX - x, 2)) + (Math.pow(centerY - y, 2)));         // Determines distance from center
            if(debugOn == true){
                System.out.println("X: " + x);
                System.out.println("Y: " + y);
                System.out.println("Distance: " + d);
            }
            
            if (d <= r) {
                if(debugOn == true){
                    System.out.println("In Circle");
                }
                circle ++; // If the point is within the radius of the circle, we add it to the tally
            }
            total ++; 
            estimatedPi = ((circle/total) * 4); // Determine Probability
            if(debugOn == true){
                System.out.println("Circle Total: " + circle);
                System.out.println("Total Points: " + total);
                System.out.println("Estimated Pi: " + estimatedPi);
                System.out.println("-------------------------------------------");
            } 
        }
        System.out.println("\n------------- Finished! -------------");
        System.out.println("Finished in:  " + ((System.currentTimeMillis() - startTime) / 1000) + " seconds.");
        return estimatedPi;
    }

    public static void main (String[] args){
        if(args.length == 1){
            points = Long.parseLong(args[0]);
        } else if (args.length == 2){
            points = Long.parseLong(args[0]);
            if(args[1].equals("Debug")){
                System.out.println("Debug is on");
                debugOn = true;
            }
        }
        pi = simulate(points);
        System.out.println("Estimated Pi using " + points + " points.");
        System.out.println("Pi is: " + pi);
    }


}