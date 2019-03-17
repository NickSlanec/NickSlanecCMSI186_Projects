public class Ball {

  //Private Instance Data
  private static double xPosition;
  private static double yPosition;
  private static double xVelocity;
  private static double yVelocity;
  private static final double MAXIMUM_X_POSITION = 180.0;
  private static final double MAXIMUM_Y_POSITION = 120.0;
  private static final double BALL_RADIUS = 0.3708;
  private static double timeSlice = 1.0;
  private static final double MINIMUM_VELOCITY = 0.0833333;
  public double[] velocity = new double[2];
  public double[] position = new double[2];

  //Public Constructor
  public Ball(double xPosition,double yPosition,double xVelocity,double yVelocity) {
    position[0] = xPosition;
    position[1] = yPosition;
    velocity[0] = xVelocity;
    velocity[1] = yVelocity;
  }

  // Alternative block that only returns a boolean for validation
  public static boolean isPositionValid(String xPositionArg, String yPositionArg) {
    try {
      double xPosition = Double.parseDouble(xPositionArg);
      double yPosition = Double.parseDouble(yPositionArg);
      if (Math.abs(xPosition) < MAXIMUM_X_POSITION && Math.abs(yPosition) < MAXIMUM_Y_POSITION){
        return true;
      } else {
        return false;
      }
    } catch (NumberFormatException nfe) {
      System.out.println("Please enter a valid position");
      System.exit(0);
    }
    return false;
  }

  public double[] friction(double timeSlice) {
    velocity[0] *= (timeSlice * .99);
    velocity[1] *= (timeSlice * .99);
    return velocity;
  }

  // Moves ball for each time slice
  public double[] move() {
    position[0] += velocity[0];
    position[1] += velocity[1];
    return position;
  }

  public boolean isOutOfBounds() {
    if ((Math.abs(position[0]) > MAXIMUM_X_POSITION) || (Math.abs(position[1]) > MAXIMUM_Y_POSITION)) {
      System.out.println("Out of Bounds");
      return true;
    }
    return false;
  }

  public boolean isStillMoving (){
    if ((velocity[0] > MINIMUM_VELOCITY) || (velocity[1] > MINIMUM_VELOCITY)){
      return true;
    }
    return false;
  }

  public String toStringPosition () {
    return (position[0] + ", " + position[1]);
  }

  public String toStringVelocity() {
    return ("X: " + velocity [0]+ " Y: " + velocity[1] );
  }

  public double[] getPosition() {
    return position;
  }

  public double[] getVelocity(){
    return velocity;
  }

}
