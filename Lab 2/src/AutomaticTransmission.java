package transmission;

/**
 * AutomaticTransmission class implements the Transmission interface that allows
 * car's computer choose how much rotation to transfer at certain speeds.
 */
public class AutomaticTransmission implements Transmission {
  private int speed;
  private int gear;
  private final int t1;
  private final int t2;
  private final int t3;
  private final int t4;
  private final int t5;

  /**
   * This constructor is for defining the methods required in the AutomaticTransmission
   * throwing exceptions if any value goes beyond the said conditions.
   *
   * @param t1 - Speed threshold 1
   * @param t2 - Speed threshold 2
   * @param t3 - Speed threshold 3
   * @param t4 - Speed threshold 4
   * @param t5 - Speed threshold 5
   */

  public AutomaticTransmission(int t1, int t2, int t3, int t4, int t5) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
    this.t5 = t5;

    // Valid Arg check
    if (!(this.t1 > 0
        && this.t2 > this.t1
        && this.t3 > this.t2
        && this.t4 > this.t3
        && this.t5 > this.t4)) {
      throw new IllegalArgumentException("Error: Parameter values not legal!");
    }

    // Idle State
    this.speed = 0;
    this.gear = 0;
  }

  /**
   * This method adjusts the gear according to the speed.
   */

  private void adjustGear() {
    if (this.speed == 0) {
      this.gear = 0;
    } else if (this.speed < this.t1) {
      this.gear = 1;
    } else if (this.speed < this.t2) {
      this.gear = 2;
    } else if (this.speed < this.t3) {
      this.gear = 3;
    } else if (this.speed < this.t4) {
      this.gear = 4;
    } else if (this.speed < this.t5) {
      this.gear = 5;
    } else {
      this.gear = 6;
    }
  }

  /**
   * This method increases the speed of the car by 1.
   */

  @Override public void increaseSpeed() throws IllegalStateException {
    this.speed += 1;
    this.adjustGear();

  }

  /**
   * This method decreases the speed of the car by 1 or throws an exception if the speed is less
   * than 0.
   */

  @Override public void decreaseSpeed() throws IllegalStateException {
    if (speed == 0) {
      throw new IllegalStateException("Error: Speed will go below 0 if we decrease speed now!");
    }
    this.speed -= 1;
    this.adjustGear();
  }

  /**
   * This method gives the speed of the car.
   *
   * @return speed
   */

  @Override public int getSpeed() {
    return speed;
  }

  /**
   * This method gives the gear of the car.
   *
   * @return gear
   */

  @Override public int getGear() {
    return gear;
  }


  /**
   * This method gives an output of the expected string format.
   *
   * @return speed, gear
   */
  @Override public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", this.getSpeed(), this.getGear());
  }

  /**
   * This method gives a boolean output if the four parameter values of the constructor are equal.
   *
   * @param o - instance of AutomaticTransmission
   * @return the boolean value if the speed and gear satisfy the equals condition
   */

  @Override public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AutomaticTransmission)) {
      return false;
    }
    final AutomaticTransmission that = (AutomaticTransmission) o;
    return this.getSpeed() == that.getSpeed() && this.getGear() == that.getGear();
    //        && this.increaseSpeed() == that.increaseSpeed()
    //        && this.decreaseSpeed() == that.decreaseSpeed()
  }

  /**
   * This method gives an output of an unique hashcode for every object.
   *
   * @return a unique hashcode of the speed and gear
   */

  @Override public int hashCode() {
    return Integer.hashCode(this.getSpeed()) + Integer.hashCode(this.getGear());
  }
}

