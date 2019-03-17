public class Clock {
  private double elapsedTime = 0.0;
  private static double timeSlice = 0.0;
  private int hoursPassed = 0;
  private int minutesPassed = 0;
  private double adjustedElapsedTime = 0.0;

  public Clock() {
    this.elapsedTime = 0;
  }

  public double tick(double timeSlice) {
    return elapsedTime += timeSlice;
  }

  public double getTotalSeconds() {
     return elapsedTime;
  }

  public String toString() {
   hoursPassed =((int)(elapsedTime / 3600));
   adjustedElapsedTime = (elapsedTime - (hoursPassed * 3600));
   minutesPassed = ((int)(adjustedElapsedTime / 60));
   adjustedElapsedTime = (adjustedElapsedTime - (minutesPassed * 60));
   return (hoursPassed + ":" + minutesPassed + ":" + adjustedElapsedTime);
  }
}
