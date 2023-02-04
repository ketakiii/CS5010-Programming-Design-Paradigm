import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;
import org.junit.Test;
import transmission.AutomaticTransmission;
import transmission.Transmission;

/**
 * This constructor is for defining the methods required in the AutomaticTransmission.
 */
public class AutomaticTransmissionTest {

  private final Transmission at = new AutomaticTransmission(10, 20, 30, 40, 50);

  /**
   * This method tests the expected value and the actual value of the testgetSpeed.
   */

  @Test public void testgetSpeed() {
    int expected = 0;
    assertEquals(expected, at.getSpeed());
  }

  /**
   * This method tests the expected value and the actual value of the testgetGear.
   */

  @Test public void testgetGear() {
    int expected = 0;
    assertEquals(expected, at.getGear());
  }

  /**
   * This method tests the expected value and the actual value of the testincreaseSpeed.
   */

  @Test public void testincreaseSpeed() {
    Transmission t = new AutomaticTransmission(2, 4, 6, 8, 10);
    assertEquals(0, t.getSpeed());
    assertEquals(0, t.getGear());

    t.increaseSpeed();
    assertEquals(1, t.getSpeed());
    assertEquals(1, t.getGear());

    t.increaseSpeed();
    t.increaseSpeed();
    assertEquals(3, t.getSpeed());
    assertEquals(2, t.getGear());

    t.increaseSpeed();
    t.increaseSpeed();
    assertEquals(5, t.getSpeed());
    assertEquals(3, t.getGear());

    t.increaseSpeed();
    t.increaseSpeed();
    assertEquals(7, t.getSpeed());
    assertEquals(4, t.getGear());

    t.increaseSpeed();
    t.increaseSpeed();
    assertEquals(9, t.getSpeed());
    assertEquals(5, t.getGear());

    t.increaseSpeed();
    t.increaseSpeed();
    assertEquals(11, t.getSpeed());
    assertEquals(6, t.getGear());
  }

  /**
   * This method tests the expected value and the actual value of the testdecreaseSpeed.
   */

  @Test public void testdecreaseSpeed() {
    Transmission t = new AutomaticTransmission(2, 4, 6, 8, 10);
    assertThrows(IllegalStateException.class, t::decreaseSpeed); // if speed = 0 throw an exception
    t.increaseSpeed();
    t.increaseSpeed();
    t.decreaseSpeed();
    assertEquals(1, t.getSpeed());

    for (int i = 1; i < 11; i++) {
      t.increaseSpeed();
    }

    assertEquals(11, t.getSpeed());
    assertEquals(6, t.getGear());

    t.decreaseSpeed();
    t.decreaseSpeed();
    assertEquals(9, t.getSpeed());
    assertEquals(5, t.getGear());

    t.decreaseSpeed();
    t.decreaseSpeed();
    assertEquals(7, t.getSpeed());
    assertEquals(4, t.getGear());

    t.decreaseSpeed();
    t.decreaseSpeed();
    assertEquals(5, t.getSpeed());
    assertEquals(3, t.getGear());

    t.decreaseSpeed();
    t.decreaseSpeed();
    assertEquals(3, t.getSpeed());
    assertEquals(2, t.getGear());

    t.decreaseSpeed();
    t.decreaseSpeed();
    assertEquals(1, t.getSpeed());
    assertEquals(1, t.getGear());

    t.decreaseSpeed();
    assertEquals(0, t.getSpeed());
    assertEquals(0, t.getGear());

  }

  /**
   * This method tests the validity of the thresholds given in the AutomaticTransmission
   * constructor.
   */

  @Test public void testvalidThresholds() {
    assertThrows(IllegalArgumentException.class, () -> new AutomaticTransmission(-1, 2, 3, 4, 5));
    assertThrows(IllegalArgumentException.class, () -> new AutomaticTransmission(1, 2, -3, -4, 5));
    assertThrows(IllegalArgumentException.class, () -> new AutomaticTransmission(1, 3, 2, 4, 5));
    assertThrows(IllegalArgumentException.class, () -> new AutomaticTransmission(7, 2, 3, 4, 5));
    assertThrows(IllegalArgumentException.class, () -> new AutomaticTransmission(7, 2, 50, 4, 5));
  }

  /**
   * This method tests if the actual toString method returns the same thing as the expected
   * toString method.
   */

  @Test public void testtoString() {
    String expected = "Transmission (speed = 0, gear = 0)";
    assertEquals(expected, at.toString());

    at.increaseSpeed();
    String expected1 = "Transmission (speed = 1, gear = 1)";
    assertEquals(expected1, at.toString());

    at.increaseSpeed();
    String expected2 = "Transmission (speed = 2, gear = 1)";
    assertEquals(expected2, at.toString());

    at.decreaseSpeed();
    String expected3 = "Transmission (speed = 1, gear = 1)";
    assertEquals(expected3, at.toString());
  }

  /**
   * This method tests if the testEquals method checks the equality of similar/ different objects
   * in an accurate fashion.
   */

  @Test public void testEquals() {
    LocalDate ld = LocalDate.now();
    Transmission at2 = new AutomaticTransmission(10, 20, 30, 40, 50);
    Transmission at3 = new AutomaticTransmission(4, 20, 25, 40, 50);
    boolean expected1 = true;
    boolean expected2 = false;

    assertEquals(at, at2);
    assertEquals(at2, at3);
    assertNotEquals(ld, at);
  }

  /**
   * This method tests if the testHashCode method checks the hashcode generated of objects
   * in an accurate fashion.
   */

  @Test public void testhashCode() {
    Transmission at2 = new AutomaticTransmission(10, 20, 30, 40, 50);
    at2.increaseSpeed();
    at2.increaseSpeed();

    Transmission at3 = new AutomaticTransmission(4, 20, 25, 40, 50);
    at3.increaseSpeed();
    at3.increaseSpeed();
    at3.decreaseSpeed();

    int expected1 = 0;
    int expected2 = 3;
    int expected3 = 2;

    assertEquals(expected1, at.hashCode());
    assertEquals(expected2, at2.hashCode());
    assertEquals(expected3, at3.hashCode());
  }
}