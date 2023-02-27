import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * This class FootWearTest tests the methods in the FootWear class.
 */
public class FootWearTest {
  Random random;

  @Test public void testFootWear() {
    assertThrows(IllegalArgumentException.class, () -> new FootWear(null, 20));
  }

  @Test public void testCompareTo() {
    random = new Random();
    HeadGear headGear = new HeadGear("HeadGear1", 20);
    FootWear footWear = new FootWear("Footwear1", 10);
    assertEquals(1, footWear.compareTo(headGear));
    Belts belts2 = new Belts("Belts2", BeltSize.MEDIUM, 10, 20);
    assertEquals(1, footWear.compareTo(belts2));
    Belts belts1 = new Belts("Belts1", BeltSize.SMALL, 10, 10);
    assertEquals(1, footWear.compareTo(belts1));
    Potions potions = new Potions("Potions1", 20, random);
    assertEquals(1, footWear.compareTo(potions));
    assertEquals(1, footWear.compareTo(headGear));
    FootWear footWear1 = new FootWear("Footwear1", 10);
    assertEquals(0, footWear.compareTo(footWear1));
  }

}