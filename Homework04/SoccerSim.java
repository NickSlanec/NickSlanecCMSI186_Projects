public class SoccerSim {
  private static double[] fieldSize = {180.0, 120.0};
  private static double[] polePosition = {(Math.random() * fieldSize[0]), (Math.random() * fieldSize[1])};
  private static final double BALL_RADIUS = 0.3708;
  private static final double POLE_RADIUS = 0.2;
  private static int numBalls = 0;
  private static String[] globalArgs;
  private static Ball[] ball = null;
  private static double timeSlice = 1.00;
  private static int stillBallCounter = 0;
  private static double MAX_TIME_SLICE = 1800.00;
  private static int iterationCounter = 0;

  private static int determineBalls() {
    if ((globalArgs.length % 4 == 0) && globalArgs.length > 3){
      numBalls = (globalArgs.length / 4);
    } else if ((globalArgs.length % 4) - 1 == 0) {
      numBalls = ((globalArgs.length -1) / 4);
    } else if (globalArgs.length <= 3) {
      throw new IllegalArgumentException("Invalid number of arguments");
    } else {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
    System.out.println("There are " + numBalls + " balls.");
    return numBalls;
  }

  private static double validateTimeSlice(){
    if (globalArgs.length % 4 == 0) {
      timeSlice = 1.00;
    } else if (globalArgs.length % 4 == 1){
      if ((Double.parseDouble(globalArgs[globalArgs.length - 1])) <= 0 || (Double.parseDouble(globalArgs[globalArgs.length - 1])) > MAX_TIME_SLICE){
        throw new IllegalArgumentException ("Invalid Time Slice");
      } else {
        timeSlice = (Double.parseDouble(globalArgs[globalArgs.length - 1]));
      }
    }
    return timeSlice;
  }


  private static Ball[] createBalls() {
    ball = new Ball[numBalls];
    int j = 0;
    int argumentLength = globalArgs.length;
    if (globalArgs.length % 4 == 1){
      argumentLength -= 1;
    }
    for(int i = 0; i < argumentLength; i += 4){
      if ((Ball.isPositionValid(globalArgs[i], globalArgs[i + 1])) == true) {
        ball[j] = new Ball(Double.parseDouble(globalArgs[i]), Double.parseDouble(globalArgs[i+1]), Double.parseDouble(globalArgs[i+2]), Double.parseDouble(globalArgs[i+3]));
        System.out.println("Ball " + j + " created at: " + ball[j].toStringPosition());
        j++;
      } 
    }
    System.out.println("Pole created at: " + polePosition[0] + ", " + polePosition[1]);
    System.out.println("-------------------------------------------------------------------------------------------");
    return ball;
  }

  private static boolean isPoleColliding(){
    for (int i = 0; i < numBalls; i++){
      double d = Math.sqrt(Math.pow(polePosition[0] - ball[i].position[0], 2) + (Math.pow(polePosition[1] - ball[i].position[1], 2)));
      if( d <= (POLE_RADIUS + BALL_RADIUS)){
        System.out.println("Ball " + i + " collided with the pole at: " + polePosition[0] + ", " + polePosition[1]);
        return true;
      }
    }
    //System.out.println("No collision with the pole");
    return false;
  }

  private static boolean isBallColliding(){
    for (int i = 0; i < numBalls; i++){
      for (int j = 0; j < numBalls; j++){
        double d = Math.sqrt(Math.pow(ball[i].position[0] - ball[j].position[0], 2) + (Math.pow(ball[i].position[1] - ball[j].position[1], 2)));
        if( d <= (BALL_RADIUS * 2) && (i != j)){
          System.out.println("Ball " + i + " and Ball " + j + " collided at: " + ball[i].toStringPosition());
          return true;
        }
      }
    }
    //System.out.println("No collision between balls");
    return false;
  }

  public static void main(String args[]){
    Clock clock = new Clock();
    globalArgs = args;
    determineBalls();
    validateTimeSlice();
    createBalls();
    while (true){
      System.out.println("Time is: " + clock.toString()+ "\n");

      for (int i = 0; i < numBalls; i++){
        System.out.println("Ball " + i);
        System.out.println("Position: " + ball[i].toStringPosition());
        if (ball[i].isOutOfBounds() == true){
          ball[i].velocity[0] = 0;
          ball[i].velocity[1] = 0;
        }
        if (ball[i].isStillMoving() == false){
          stillBallCounter++;
        }
        System.out.println("Velocity: " + ball[i].toStringVelocity() + "\n");
        ball[i].move(timeSlice);
        ball[i].friction(timeSlice);
      }

      if (isPoleColliding() == true) {
        System.out.println ("The simulation took " + iterationCounter + " iterations");
        break;
      }
      if (isBallColliding() == true) {
        System.out.println ("The simulation took " + iterationCounter + " iterations");
        break;
      }
      if (stillBallCounter == numBalls){
        System.out.println("**All balls came to rest without contact**");
        System.out.println ("The simulation took " + iterationCounter + " iterations to complete.");
        break;
      } else {
        stillBallCounter = 0;
      }
      iterationCounter += 1;
      clock.tick(timeSlice);
      System.out.println("---------------------------------------------------");
    }
  }
}
// cd OneDrive\Computer Science 186\Homework04
