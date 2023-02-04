import org.junit.Test;
import weather.StevensonReading;

import javax.xml.datatype.Duration;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class StevensonReadingTest {
  StevensonReading sr = new StevensonReading(66, 60, 22, 34);

  @Test
  public void getTemperature() {
    double expected = 66;
    assertEquals(expected, sr.air_temp, 0.0);
  }

  @Test
  public void getDewPoint() {
    double expected = 60;
    assertEquals(expected, sr.dew_temp, 0.0);
  }

  @Test
  public void getWindSpeed() {
    double expected = 22;
    assertEquals(expected, sr.wind_speed, 0.0);
  }

  @Test
  public void getTotalRain() {
    double expected = 34;
    assertEquals(expected, sr.rain, 0.0);
  }

  @Test
  public void getRelativeHumidity() {
    double expected = 99;
    assertEquals(expected, sr.getRelativeHumidity(), 0.0);
  }

  @Test
  public void getHeatIndex() {
    int expected = 141;
    assertEquals(expected, sr.getHeatIndex());
  }

  @Test
  public void getWindChill() {
    int expected = 175;
    assertEquals(expected, sr.getWindChill());
  }

  @Test
  public void testToString() {
    assertEquals("Reading: T = 66, D = 60, v = 22, rain = 34", sr.toString());
  }

  @Test
  public void testEquals() {
    StevensonReading sr1 = new StevensonReading(66, 60, 22, 34);
    StevensonReading sr2 = new StevensonReading(61, 60, 21, 35);
    LocalDate ld = LocalDate.now();
    assertTrue(sr.equals(sr));
    assertFalse(sr.equals(ld));
    assertTrue(sr.equals(sr1));
    assertFalse(sr.equals(sr2));
  }

  @Test
  public void testHashcode() {
    StevensonReading sr1 = new StevensonReading(66, 60, 22, 34);
    StevensonReading sr2 = new StevensonReading(61, 60, 21, 35);
    assertEquals(68834, sr.hashCode());
    assertEquals(sr1.hashCode(), sr.hashCode());
    assertNotEquals(sr2.hashCode(), sr.hashCode());
  }
}