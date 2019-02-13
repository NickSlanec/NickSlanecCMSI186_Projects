/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  MainProgLoopDemo.java
 *  Purpose       :  Demonstrates the use of input from a command line for use with Yahtzee
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-14
 *  Description   :
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-14  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2019-02-11  Nick Slanec   Starting Assingment
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{

   public static void main( String args[] ) {
      int count = Integer.parseInt(args [0]);
      int sides = Integer.parseInt(args [1]);
      DiceSet set = new DiceSet(count, sides);
      int highscore = 0;

      System.out.println( "\n   Welcome to High Roll!!\n" );
      System.out.println( "1. Roll all the dice" );
      System.out.println( "2. Roll a single die" );
      System.out.println( "3. Calculate the score for this set" );
      System.out.println( "4. Save this score as High Score" );
      System.out.println( "5. Display the High Score" );
      System.out.println( "Press the 'q' key to quit the program." );

     // This line uses the two classes to assemble an "input stream" for the user to type
     // text into the program
      BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
      while( true ) {
         System.out.print( ">>" );
         String inputLine = null;
         try {
            inputLine = input.readLine();
            if( 0 == inputLine.length() ) {
              System.out.println("Enter a number!");
              continue;
            }
            if( 'q' == inputLine.charAt(0) ) {
               break;
            }
            if ( '1' == inputLine.charAt(0)) {
              set.roll();
              System.out.println("");
              System.out.println("You rolled: " + set.toString());

              System.out.println("");
              System.out.println( "1. Roll all the dice" );
              System.out.println( "2. Roll a single die" );
              System.out.println( "3. Calculate the score for this set" );
              System.out.println( "4. Save this score as High Score" );
              System.out.println( "5. Display the High Score" );
              System.out.println( "q. Quit the program" );
            }
            if ('2' == inputLine.charAt(0)){
              System.out.println("Enter the die you want to re-roll");
              set.rollIndividual(Integer.parseInt(input.readLine()) - 1);
              System.out.println("");
              System.out.println("            " + set.toString());

              System.out.println("");
              System.out.println( "1. Roll all the dice" );
              System.out.println( "2. Roll a single die" );
              System.out.println( "3. Calculate the score for this set" );
              System.out.println( "4. Save this score as High Score" );
              System.out.println( "5. Display the High Score" );
              System.out.println( "q. Quit the program" );
            }
            if ('3' == inputLine.charAt(0)){
             System.out.println("");
             System.out.println("Your score for this set is: " + set.sum());

             System.out.println("");
             System.out.println( "1. Roll all the dice" );
             System.out.println( "2. Roll a single die" );
             System.out.println( "3. Calculate the score for this set" );
             System.out.println( "4. Save this score as High Score" );
             System.out.println( "5. Display the High Score" );
             System.out.println( "q. Quit the program" );
            }
            if ('4' == inputLine.charAt(0)) {
              highscore = set.sum();
              System.out.println("");
              System.out.println("Your current High Score is: " + highscore);

              System.out.println("");
              System.out.println( "1. Roll all the dice" );
              System.out.println( "2. Roll a single die" );
              System.out.println( "3. Calculate the score for this set" );
              System.out.println( "4. Save this score as High Score" );
              System.out.println( "5. Display the High Score" );
              System.out.println( "q. Quit the program" );
            }
            if ('5' == inputLine.charAt(0)){
              System.out.println("");
              System.out.println("Your current High Score is: " + highscore);

              System.out.println("");
              System.out.println( "1. Roll all the dice" );
              System.out.println( "2. Roll a single die" );
              System.out.println( "3. Calculate the score for this set" );
              System.out.println( "4. Save this score as High Score" );
              System.out.println( "5. Display the High Score" );
              System.out.println( "q. Quit the program" );
            }
          }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
      }
   }
}
