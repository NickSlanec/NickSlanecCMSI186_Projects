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
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class PiSolver {
    public static double total = 0;
    public static double circle = 0;
    public static int points = 1000;
    public static double pi = 0;
    public static int r = 50;
    public static boolean debugOn = false;

    public static double simulate(int points){
        double centerX = r;
        double centerY = r;
        double estimatedPi = 0;
        long startTime = System.currentTimeMillis();
        System.out.println("Start Time: " + startTime);
        System.out.println("Calculating...");
        for (long i = 1; i < points; i++){
            if (i % 10000000 == 1 && i != 1){

                long currentTime = System.currentTimeMillis();
                // System.out.println("Current Time: " + currentTime);
                long timeElapsed = currentTime - startTime;
                // System.out.println("Time Elapsed = " + timeElapsed);
                double loopTime = timeElapsed / (i/10000000);
                // System.out.println("Loop Time: " + timeElapsed + " / " + i + " = " + loopTime);
                double totalTime = loopTime * (points/10000000);
                // System.out.println("Total Estimated Time = " + totalTime);
                double timeRemaining = totalTime - timeElapsed;
                System.out.print("\r");
                System.out.print("Time Remaining: " + (timeRemaining / 1000) + " seconds");
                // System.out.println("----------------------------------------------------------");
            }
            double x = (Math.floor(Math.random() * (r*2)) + 1);
            double y = (Math.floor(Math.random() * (r*2)) + 1);
            double d = Math.sqrt((Math.pow(centerX - x, 2)) + (Math.pow(centerY - y, 2)));
            if(debugOn == true){
                System.out.println("X: " + x);
                System.out.println("Y: " + y);
                System.out.println("Distance: " + d);
            }
            
            if (d <= r) {
                if(debugOn == true){
                    System.out.println("In Circle");
                }
                circle ++;
            }
            total ++;
            estimatedPi = ((circle/total) * 4);
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
            points = Integer.parseInt(args[0]);
        } else if (args.length == 2){
            points = Integer.parseInt(args[0]);
            if(args[1].equals("Debug")){
                System.out.println("Debug is on");
                debugOn = true;
            }
        }
        pi = simulate(points);
        System.out.println("Estimating Pi using " + points + " points.");
        System.out.println("Pi is: " + pi);
    }


}