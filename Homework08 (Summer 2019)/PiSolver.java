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
        for (int i = 0; i <= points; i++){
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