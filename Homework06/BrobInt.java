/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *  1.2.0  2019-04-18  B.J. Johnson  Fixed bug in add() method that was causing errors in Collatz
 *                                     sequence.  Added some tests into the main() method at the bottom
 *                                     of the file to test construction.  Also added two tests there to
 *                                     test multiplication by three and times-3-plus-1 operations
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   public  String internalValue = "";        // internal String representation of this BrobInt
   public  byte   sign          = 0;         // "0" is positive, "1" is negative
  /// You can use this or not, as you see fit.  The explanation was provided in class
   private String reversed      = "";        // the backwards version of the internal String representation
   public int[] nums = null;
   public int[] resultnums = null;
   public int returnSign = 0;

   private static BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
   private static final boolean DEBUG_ON = false;
   private static final boolean INFO_ON  = false;
   public DecimalFormat df = new DecimalFormat("000000000");

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
      if(DEBUG_ON == true){
        System.out.println("value: " + value);
      }
     char signValue = value.charAt(0);
     if (signValue == '+'){
       sign = 0;
       this.internalValue = value.substring(1);
     } else if (signValue == '-'){
       sign = 1;
       this.internalValue = value.substring(1);
     } else {
       this.internalValue = value;
       sign = 0;
     }
     validateDigits();
     if(DEBUG_ON){
       System.out.println("this.internalValue: " + this.internalValue);
     }
     this.nums = new int[(internalValue.length()/9) + 1];
     if(DEBUG_ON){
       System.out.println("this.nums.length: " + this.nums.length);
     }
     int count = nums.length-1;
     for (int i = internalValue.length(); i > 0; i -= 9){
       int stop = i;
       int start = stop - 9;
       if (count == 0){
         start = 0;
       }
       if(DEBUG_ON){
         System.out.println("count: " + count + " || " + "stop: " + stop + " || " + "start: " + start);
         System.out.println("substring: " + internalValue.substring(start, stop));
       }
       nums[count] = Integer.parseInt(internalValue.substring(start, stop));
       count--;
     }

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void validateDigits() {
     for (int i = 0; i < internalValue.length(); i++){
       if(Character.isDigit(internalValue.charAt(i)) == false){
         //System.out.println("InternalValue.length: " + internalValue.length());
         //System.out.println("InternalValue: " + internalValue);
         throw new IllegalArgumentException("Illegal Argument at character: " + i);
       }
     }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  bint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  NOTE: you can use this or not, as you see fit; explanation was given in class
   *  @see StringBuffer API page for an easy way to do this
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt bint ) {
      return new BrobInt(new StringBuffer(bint.toString()).reverse().toString());
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt bint ) {
     int carry = 0;
     if(this.sign == 1 && bint.sign == 1){
       returnSign = 1;
     }

     BrobInt minBint = null;
     BrobInt maxBint = null;
     if(compareTo(bint) == 1){
       minBint = bint;
       maxBint = this;
     } else if ((compareTo(bint) == -1) || (compareTo(bint) == 0)) {
       minBint = this;
       maxBint = bint;
     }

     if((maxBint.allZeroDetect(maxBint) == true) && (minBint.allZeroDetect(minBint) == true)){
       return BrobInt.ZERO;
     }
     int[] minRA = new int[maxBint.nums.length]; // The fact that these are both the length of the max is intentional.
     int[] maxRA = new int[maxBint.nums.length]; // This is so I can populate the arrays, and the smaller will have zeros at the end to match the length of the larger
     for (int i = 0; i < maxRA.length; i++){
       minRA[i] = 000000000;
       maxRA[i] = 000000000;
     }
     int maxIndex = maxBint.nums.length - 1;
     for (int i = minBint.nums.length - 1; i >= 0; i--){
       minRA[maxIndex] = minBint.nums[i];
       maxIndex--;
     }
     for (int i = maxBint.nums.length - 1; i >= 0; i--){
       maxRA[i] = maxBint.nums[i];
     }
     if (INFO_ON == true){
       System.out.println("Max Array:");
       toArray(maxRA);
       System.out.println("Min Array:");
       toArray(minRA);
     }

     int[] sum = new int[maxBint.nums.length + 1];
     int sumIndex = maxBint.nums.length;
     for (int i = maxBint.nums.length - 1; i >= 0 ; i--){
       sum[sumIndex] = minRA[i] + maxRA[i] + carry;
       if(sum[sumIndex] > 999999999){
         sum[sumIndex] -= 1000000000;
         carry = 1;
       } else {
         carry = 0;
       }
       sumIndex--;
       if (INFO_ON == true){
         System.out.println("Sum Array:");
         toArray(sum);
       }
     }

     String sumString = "";
     for (int i = 0; i < sum.length; i++){
       sumString += df.format(sum[i]);
     }
     if(returnSign == 1){
       sumString = "-" + sumString;
     }
     BrobInt finalSum = new BrobInt(sumString.toString());
     finalSum = removeLeadingZeros(finalSum);
     return finalSum;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt bint ) {
     int borrow = 0;
     BrobInt minBint = null;
     BrobInt maxBint = null;

     if(this.compareTo(bint) == 0){
       return BrobInt.ZERO;
     }

     if (this.sign == 0 && bint.sign == 0) {
           if (compareTo(bint) == 1) {
             minBint = bint;
             maxBint = this;
             returnSign = 0;
           } else if (compareTo(bint) == -1) {
             minBint = this;
             maxBint = bint;
             returnSign = 1;
           } else {
             minBint = this;
             maxBint = bint;
           }
         }
         if (this.sign == 1 && bint.sign == 1) {
           if (compareTo(bint) == 1) {
             minBint = bint;
             maxBint = this;
             returnSign = 1;
           } else if (compareTo(bint) == -1) {
             minBint = this;
             maxBint = bint;
             returnSign = 0;
           } else {
             minBint = this;
             maxBint = bint;
           }
         }
         if (this.sign == 1 && bint.sign == 0) {
           returnSign = 1;
           if (compareToAbs(bint) == 1) {
             minBint = this;
             maxBint = bint;
           } else if (compareToAbs(bint) == -1) {
             minBint = bint;
             maxBint = this;
           } else {
             minBint = this;
             maxBint = bint;
           }
           BrobInt finalSum = this.add(bint);
           return finalSum;
         }

         if (this.sign == 0 && bint.sign == 1) {
           returnSign = 0;
           if (compareToAbs(bint) == 1) {
             minBint = this;
             maxBint = bint;
           } else if (compareToAbs(bint) == -1) {
             minBint = bint;
             maxBint = this;
           } else {
             minBint = this;
             maxBint = bint;
           }
           BrobInt finalSum = this.add(bint);
           return finalSum;
         }

     if(INFO_ON == true){
       System.out.println("Min: " + minBint);
       System.out.println("Max: " + maxBint);
     }

     int[] minRA = new int[maxBint.nums.length]; // The fact that these are both the length of the max is intentional.
     int[] maxRA = new int[maxBint.nums.length]; // This is so I can populate the arrays, and the smaller will have zeros at the end to match the length of the larger

     for (int i = 0; i < maxRA.length; i++){
       minRA[i] = 000000000;
       maxRA[i] = 000000000;
     }
     int maxIndex = maxBint.nums.length - 1;
     for (int i = minBint.nums.length - 1; i >= 0; i--){
       minRA[maxIndex] = minBint.nums[i];
       maxIndex--;
     }
     for (int i = maxBint.nums.length - 1; i >= 0; i--){
       maxRA[i] = maxBint.nums[i];
     }
     if (INFO_ON == true){
       System.out.println("Max Array:");
       toArray(maxRA);
       System.out.println("Min Array:");
       toArray(minRA);
     }

     int[] diff = new int[maxBint.nums.length + 1];
     int diffIndex = maxBint.nums.length;
     for (int i = maxBint.nums.length - 1; i >= 0 ; i--){
       diff[diffIndex] = maxRA[i] - minRA[i] - borrow;
       if(diff[diffIndex] < 0){
         diff[diffIndex] -= 1;
         borrow = 1000000000;
       } else {
         borrow = 0;
       }
       diffIndex--;
       if (INFO_ON == true){
         System.out.println("Sum Array:");
         toArray(diff);
       }
     }

     String diffString = "";
     for (int i = 0; i < diff.length; i++){
       diffString += df.format(diff[i]);
     }
     if(returnSign == 1){
       diffString = "-" + diffString;
     }
     BrobInt finalSum = new BrobInt(diffString.toString());
     finalSum = removeLeadingZeros(finalSum);
     return finalSum;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to multiply this by
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt bint ) {
     int carry = 0;
     int counter = 0;
     if((this.sign == 1 && bint.sign == 1) || (this.sign == 0 && bint.sign == 0)){
       returnSign = 0;
     } else {
       returnSign = 1;
     }
     BrobInt minBint = null;
     BrobInt maxBint = null;
     if(compareTo(bint) == 1){
       minBint = bint;
       maxBint = this;
     } else if ((compareTo(bint) == -1) || (compareTo(bint) == 0)) {
       minBint = this;
       maxBint = bint;
     }

     if((maxBint.allZeroDetect(maxBint) == true) || (minBint.allZeroDetect(minBint) == true)){
       return BrobInt.ZERO;
     }

     int[] minRA = new int[maxBint.nums.length];
     int[] maxRA = new int[maxBint.nums.length];
     for (int i = 0; i < maxRA.length; i++){
       minRA[i] = 000000000;
       maxRA[i] = 000000000;
     }
     int maxIndex = maxBint.nums.length - 1;
     for (int i = minBint.nums.length - 1; i >= 0; i--){
       minRA[maxIndex] = minBint.nums[i];
       maxIndex--;
     }
     for (int i = maxBint.nums.length - 1; i >= 0; i--){
       maxRA[i] = maxBint.nums[i];
     }
     if (INFO_ON == true){
       System.out.println("Max Array:");
       toArray(maxRA);
       System.out.println("Min Array:");
       toArray(minRA);
     }

     long[] prod = new long[maxBint.nums.length * 2];
     for(int i = 0; i < prod.length; i++){
       prod[i] = 0;
     }

     int prodIndex = prod.length - 1;

     for(int i = minRA.length - 1; i >= 0; i--){
       for(int j = maxRA.length - 1; j >= 0; j--){
         //System.out.println(carry);
         prod[prodIndex] += (long) maxRA[j] * (long)minRA[i] + (long)carry;
         if(prod[prodIndex] <= 999999999){
           carry = 0;
         } else {
           while(prod[prodIndex] > 999999999){
             prod[prodIndex] -= 1000000000;
             carry += 1;
           }
         }
         prodIndex--;
       }
       counter++;
       prodIndex = maxBint.nums.length * 2 - counter;
     }
     //toArray(prod);
     String prodString = "";
     for (int i = 0; i < prod.length; i++){
       prodString += df.format(prod[i]);
     }
     if(returnSign == 1){
       prodString = "-" + prodString;
     }
     BrobInt finalSum = new BrobInt(prodString.toString());
     finalSum = removeLeadingZeros(finalSum);
     return finalSum;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  bint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt bint ) {
      BrobInt d1 = bint;
      BrobInt d2 = this;
      BrobInt d3;
      BrobInt q = new BrobInt("0");
      int n = 0;
      if(d1.equals(BrobInt.ZERO)){
        throw new IllegalArgumentException ("Cannot divide zero by something");
      }
      if(d1.equals(d2)){
        //System.out.println("d1 = d2");
        return BrobInt.ONE;
      }
      if(d1.compareTo(d2) == 1){
        //System.out.println("d1 > d2");
        return BrobInt.ZERO;
      }

      n = d1.toString().length();
      d3 = new BrobInt(d2.toString().substring(0, n));

      if(d1.compareTo(d3) == 1){
        n++;
        d3 = new BrobInt(d2.toString().substring(0, n));
      }

      while(n <= d2.toString().length()){
        while((d3.compareTo(d1) == 1) || (d3.compareTo(d1) == 0)){
          d3 = d3.subtract(d1);
          q = q.add(BrobInt.ONE);
        }
        if(n == d2.toString().length()){
          break;
        } else {
          n++;
        }
        d3 = d3.multiply(BrobInt.TEN);
        q = q.multiply(BrobInt.TEN);
        BrobInt digit = new BrobInt (d2.toString().substring(n-1, n));
        d3 = d3.add(digit);
      }
      return q;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  bint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt bint ) {
     BrobInt g1 = this;
     BrobInt g2 = bint;
     BrobInt answer = g1.subtract(g1.divide(g2).multiply(g2));
     if(answer.equals(BrobInt.ZERO)){
       return BrobInt.ZERO;
     } else {
       return answer;
     }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  bint  BrobInt to compare to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
   *        It takes into account the length of the two numbers, and if that isn't enough it does a
   *        character by character comparison to determine
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt bint ) {

      // remove any leading zeros because we will compare lengths
       String me  = removeLeadingZeros( this ).toString();
       String arg = removeLeadingZeros( bint ).toString();

      // handle the signs here
       if( 1 == sign && 0 == bint.sign ) {
          return -1;
       } else if( 0 == sign && 1 == bint.sign ) {
          return 1;
       } else if( 0 == sign && 0 == bint.sign ) {
         // the signs are the same at this point ~ both positive
         // check the length and return the appropriate value
         //   1 means this is longer than bint, hence larger positive
         //  -1 means bint is longer than this, hence larger positive
          if( me.length() != arg.length() ) {
             return (me.length() > arg.length()) ? 1 : -1;
          }
       } else {
         // the signs are the same at this point ~ both negative
          if( me.length() != arg.length() ) {
             return (me.length() > arg.length()) ? -1 : 1;
          }
       }

      // otherwise, they are the same length, so compare absolute values
       for( int i = 0; i < me.length(); i++ ) {
          Character a = Character.valueOf( me.charAt(i) );
          Character b = Character.valueOf( arg.charAt(i) );
          if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
             return 1;
          } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
             return (-1);
          }
       }
       return 0;
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to compare a BrobInt's absolute value passed as argument to this BrobInt
     *  @param  bint  BrobInt to compare to this
     *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
     *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
     *        It takes into account the length of the two numbers, and if that isn't enough it does a
     *        character by character comparison to determine
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
     public int compareToAbs( BrobInt bint ) {

        // remove any leading zeros because we will compare lengths
         String me  = removeLeadingZeros( this ).toString();
         String arg = removeLeadingZeros( bint ).toString();

        // otherwise, they are the same length, so compare absolute values
         for( int i = 0; i < me.length(); i++ ) {
            Character a = Character.valueOf( me.charAt(i) );
            Character b = Character.valueOf( arg.charAt(i) );
            if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
               return 1;
            } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
               return (-1);
            }
         }
         return 0;
      }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  bint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt bint ) {
      return (internalValue.equals( bint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value    long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt bi = null;
      try { bi = new BrobInt( Long.valueOf( value ).toString() ); }
      catch( NumberFormatException nfe ) { throw new NumberFormatException( "\n  Sorry, the value must be numeric of type long." ); }
      return bi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
     if(sign == 1){
       return "-" + internalValue;
     }
     return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to remove leading zeros from a BrobInt passed as argument
   *  @param  bint     BrobInt to remove zeros from
   *  @return BrobInt that is the argument BrobInt with leading zeros removed
   *  Note that the sign is preserved if it exists
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt removeLeadingZeros( BrobInt bint ) {
      Character sign = null;
      String returnString = bint.toString();
      int index = 0;

      if( allZeroDetect( bint ) ) {
         return bint;
      }
      if( ('-' == returnString.charAt( index )) || ('+' == returnString.charAt( index )) ) {
         sign = returnString.charAt( index );
         index++;
      }
      if( returnString.charAt( index ) != '0' ) {
         return bint;
      }

      while( returnString.charAt( index ) == '0' ) {
         index++;
      }
      returnString = bint.toString().substring( index, bint.toString().length() );
      if( sign != null ) {
         returnString = sign.toString() + returnString;
      }
      return new BrobInt( returnString );

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a boolean if a BrobInt is all zeros
   *  @param  bint     BrobInt to compare to this
   *  @return boolean  that is true if the BrobInt passed is all zeros, false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean allZeroDetect( BrobInt bint ) {
      for( int i = 0; i < bint.toString().length(); i++ ) {
         if( bint.toString().charAt(i) != '0' ) {
            return false;
         }
      }
      return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  @param   d  byte array from which to display the contents
   *  NOTE: may be changed to int[] or some other type based on requirements in code above
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( int[] d ) {
      System.out.println( "Array contents: " + Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display a prompt for the user to press 'ENTER' and wait for her to do so
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void pressEnter() {
      String inputLine = null;
      try {
         System.out.print( "      [Press 'ENTER' to continue]: >> " );
         inputLine = input.readLine();
      }
      catch( IOException ioe ) {
         System.out.println( "Caught IOException" );
      }

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  NOTE:  we don't really care about these, since we test the BrobInt class with the BrobIntTester
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
      // BrobInt b1 = null;;
      // try { System.out.println( "   Making a new BrobInt: " ); b1 = new BrobInt( "147258369789456123" ); }
      // catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }
      // try { System.out.println( "   expecting: 147258369789456123\n     and got: " + b1.toString() ); }
      // catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }
      // System.out.println( "\n    Adding 82832833 to 3: " );
      // try { System.out.println( "      expecting: 82832836 \n        and got: " + new BrobInt("82832833").add( BrobInt.THREE ) ); }
      // catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }
      //
      // System.out.println( "\n    Multiplying 3 by 82832833 and adding 1: " );
      // try { System.out.println( "      expecting: 248498500\n        and got: " + BrobInt.THREE.multiply( new BrobInt( "82832833" ) ).add( BrobInt.ONE ) ); }
      // catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }
      // System.exit( 0 );
      BrobInt g11 = new BrobInt("20");
      BrobInt g12 = new BrobInt("2");
      System.out.println(g11.remainder(g12));


   }
}
//cd OneDrive\Computer Science 186\Homework06
