import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;
import org.junit.Test;
import weather.StevensonReading;

/**
 * This constructor is for defining the methods required in the StevensonReading.
 */
public class StevensonReadingTest {
  private final StevensonReading sr = new StevensonReading(
      66,
      60,
      22,
      34);

  /**
   * This method tests the expected value and the actual value of the airTemp.
   */
  @Test public void getTemperature() {
    final double expected = 66;
    assertEquals(expected, sr.getTemperature(), 0.0);
  }

  /**
   * This method tests the expected value and the actual value of the dewTemp.
   */
  @Test public void getDewPoint() {
    final double expected = 60;
    assertEquals(expected, sr.getDewPoint(), 0.0);
  }

  /**
   * This method tests the expected value and the actual value of the windSpeed.
   */

  @Test public void getWindSpeed() {
    final double expected = 22;
    assertEquals(expected, sr.getWindSpeed(), 0.0);
  }

  /**
   * This method tests the expected value and the actual value of the rain.
   */
  @Test public void getTotalRain() {
    final double expected = 34;
    assertEquals(expected, sr.getTotalRain(), 0.0);
  }

  /**
   * This method tests the expected value and the actual value of the relativeHumidity.
   */
  @Test public void getRelativeHumidity() {
    final double expected = 93;
    assertEquals(expected, sr.getRelativeHumidity(), 0.0);
  }

  /**
   * This method tests the expected value and the actual value of the heatIndex.
   */
  @Test public void getHeatIndex() {
    final int expected = 396;
    assertEquals(expected, sr.getHeatIndex());
  }

  /**
   * This method tests the expected value and the actual value of the windChill.
   */
  @Test public void getWindChill() {
    final int expected = 80;
    assertEquals(expected, sr.getWindChill());
  }

  /**
   * This method tests the expected value - expected exception and the actual exception thrown.
   * while checking if the dewTemp is greater than the airTemp
   */
  @Test public void airTempGreaterThandewTemp() {
    assertThrows(IllegalArgumentException.class, () -> new StevensonReading(
        66,
        67,
        22,
        34));
  }

  /**
   * This method tests the expected value - expected exception and the actual exception thrown.
   * while checking if the windSpeed is a non-positive number
   */
  @Test public void checkWindSpeed() {
    assertThrows(IllegalArgumentException.class, () -> new StevensonReading(
        66,
        60,
        -22,
        34));
  }

  /**
   * This method tests the expected value - expected exception and the actual exception thrown.
   * while checking if the rain is a non-positive number
   */
  @Test public void checkRain() {
    assertThrows(IllegalArgumentException.class, () -> new StevensonReading(
        66,
        60,
        22,
        -0.35));
  }

  /**
   * This method tests if the actual toString method returns the same thing as the.
   * expected toString method
   */

  @Test public void testToString() {
    assertEquals("Reading: T = 66, D = 60, v = 22, rain = 34", sr.toString());
  }

  /**
   * This method tests if the testEquals method checks the equality of similar/ different objects.
   * in an accuarte fashion
   */
  @Test public void testEquals() {
    StevensonReading sr1;
    sr1 = new StevensonReading(
        66,
        60,
        22,
        34);
    StevensonReading sr2;
    sr2 = new StevensonReading(
        61,
        60,
        21,
        35);
    assertEquals(sr, sr);
    assertNotEquals(sr, sr2);
    assertEquals(sr, sr1);

    LocalDate ld = LocalDate.now();
    assertNotEquals(sr, sr2);
    assertNotEquals(sr, ld);
  }

  /**
   * This method tests if the testHashCode method checks the hashcode generated of objects.
   * in an accuarte fashion
   */
  @Test public void testHashcode() {
    StevensonReading sr1 = new StevensonReading(
        66,
        60,
        22,
        34);
    StevensonReading sr2 = new StevensonReading(
        61,
        60,
        21,
        35);
    assertEquals(68834, sr.hashCode());
    assertEquals(sr1.hashCode(), sr.hashCode());
    assertNotEquals(sr2.hashCode(), sr.hashCode());
  }
}