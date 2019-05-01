/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Finds the best combination of change to add up to a certain amount of money
 * @author    :  Nick Slanec
 * Date       :  2019-04-29
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework07.html'>Assignment Page</a>
 * Notes      :  cd OneDrive\Computer Science 186\Homework07
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2019-04-29  Nick Slanec   Initial writing and begin coding
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 import java.util.Arrays;


public class DynamicChangeMaker {
  public static int[] inputRA;
  public static int money;
  public int[] zerosRA;
  public static int finalTotal;
  public static boolean DEBUG_ON = false;


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to parse the string arguments from the terminal and set them to variables for use in the program
   *  @param  String[]   Inputs from the terminal
   *  @return Nothing
   *  NOTE: Sets the values to global variables
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public void parseArgs(String[] inputs){
    String[] stringArgs = inputs[0].split(",");
    inputRA = new int[stringArgs.length];
    zerosRA = new int[stringArgs.length];
    for (int i = 0; i < stringArgs.length; i++){
      inputRA[i] = Integer.parseInt(stringArgs[i]);
      zerosRA[i] = 0;
    }
    money = Integer.parseInt(inputs[1]);
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to parse the string arguments from the tester and set them to variables for use in the program
   *  @param  int[] int   Inputs from the tester
   *  @return Nothing
   *  NOTE: Sets the values to global variables
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public void parseIntArgs(int[] inputs, int cash){
    int[] coinArgs = inputs;
    inputRA = new int[coinArgs.length];
    zerosRA = new int[coinArgs.length];
    for (int i = 0; i < coinArgs.length; i++){
      inputRA[i] = coinArgs[i];
      zerosRA[i] = 0;
    }
    money = cash;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid inputs
   *  @throws  IllegalArgumentException if arguments are invalid
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public void validateArgs() {
    if (money <= 0){
      throw new IllegalArgumentException("Cannot have a zero or negative amount of money");
    }
    for(int i = 0; i < inputRA.length; i++){
      if (inputRA[i] == 0){
        throw new IllegalArgumentException("Cannot have a zero cent coin");
      }
      if (inputRA[i] < 0){
        throw new IllegalArgumentException("Cannot have a negative cent coin");
      }
      for (int j = 0; j < inputRA.length; j++){
        if (inputRA[i] == inputRA[j] && i != j){
          throw new IllegalArgumentException("Cannot have multiple coins of the same value");
        }
      }
    }
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to create a two dimentional table of all of the change options and returns the best one
   *  @param  String[] the arguments array inputted by the user
   *  @return int[]    the best combination of coins
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static Tuple makeChangeWithDynamicProgramming(int[] args, int secondargs){
    DynamicChangeMaker cwd = new DynamicChangeMaker();
    cwd.parseIntArgs(args, secondargs);
    cwd.validateArgs();
    Tuple inputTuple = new Tuple(cwd.inputRA);
    Tuple[][] table = new Tuple[cwd.inputRA.length][cwd.money + 1];
    for (int i = 0; i < cwd.inputRA.length; i++){
      for (int j = 0; j < cwd.money + 1; j++){
        if (j == 0){
          table[i][j] = new Tuple(cwd.zerosRA);
        } else {
          if(inputTuple.getElement(i) <= j){
            table[i][j] = new Tuple(cwd.zerosRA);
            table[i][j].setElement(i,1);
            if(j-inputTuple.getElement(i) < 0){
              table[i][j] = Tuple.IMPOSSIBLE;
            } else if(table[i][j].isImpossible() || table[i][j-inputTuple.getElement(i)].isImpossible()) {
              table[i][j] = Tuple.IMPOSSIBLE;
            } else {
              table[i][j] = table[i][j].add(table[i][j-inputTuple.getElement(i)]);
            }
          } else {
            table[i][j] = Tuple.IMPOSSIBLE;
          }
        }
        if(i != 0){
          if((table[i-1][j].isImpossible() == false && table[i-1][j].total() < table[i][j].total()) || ((table[i-1][j].isImpossible() == false) && (table[i][j].isImpossible() == true))){
            table[i][j] = table[i-1][j];
          }
        }
        if(DEBUG_ON){
          System.out.println("Row: " + i + " Column: " + j +" "+ table[i][j]);
        }
      }
    }
    Tuple resultTuple = table[inputRA.length - 1][money];
    finalTotal = resultTuple.total();
    return (resultTuple);
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  The main method allows users to manually input values and will recieve an answer
   *  @param  String[]   Inputs from the terminal
   *  @return Strinified resultTuple
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static void main (String[] args){
    DynamicChangeMaker dcm = new DynamicChangeMaker();
    dcm.parseArgs(args);
    int[] tempArgs = dcm.inputRA;
    int cash = dcm.money;
    dcm.validateArgs();
    if(DEBUG_ON){
      System.out.println("Coins: " + Arrays.toString(dcm.inputRA));
      System.out.println("Money: $" + dcm.money);
    }
    Tuple resultTuple = dcm.makeChangeWithDynamicProgramming(tempArgs, cash);
    for(int i = 0; i < inputRA.length; i++){
      System.out.println(resultTuple.getElement(i) + " x " + inputRA[i] + " cents");
    }
    System.out.println("Final Total: " + dcm.finalTotal + " coins");
  }

}
