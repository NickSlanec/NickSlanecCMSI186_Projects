/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  Collatz.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  Nick Slanec
 * Date       :  2019-05-24
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2019-05-24  Nick Slanec   Creating Program
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

 public class Collatz{

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"

   public static void main(String[] args){
     System.out.println("Running Collatz:");
     System.out.println("~~~~~~~~~~~~~~~~");

   int loops = 0;
   BrobInt arg = new BrobInt(args[0]);
   System.out.println(arg.toString());

   while(arg.equals(BrobInt.ONE) == false){
     if(arg.remainder(BrobInt.TWO).equals(BrobInt.ZERO)){
       arg = arg.divide(BrobInt.TWO);
       loops++;
     } else if (arg.remainder(BrobInt.TWO).equals(BrobInt.ONE)){
       arg = arg.multiply(BrobInt.THREE);
       arg = arg.add(BrobInt.ONE);
       loops++;
     }
   }
   System.out.println("Loops: " + loops);
   //System.out.println("Arg: " + arg);
  }
}
