import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Random;
import org.junit.Test;


/**
 * This class PotionsTest tests the methods in the Potions class.
 */
public class PotionsTest {
  Random random;

  @Test public void testPotions() {
    assertThrows(IllegalArgumentException.class, () -> new Potions(null, 10, random));
  }

  @Test public void testCompareTo() {
    random = new Random();
    HeadGear headGear = new HeadGear("HeadGear1", 10);
    Potions potions = new Potions("Potions1", 10, random);
    assertEquals(1, potions.compareTo(headGear));
    Potions potions2 = new Potions("Potions1", 10, random);
    assertEquals(0, potions.compareTo(potions2));
    Belts belts1 = new Belts("Belts1", BeltSize.SMALL, 10, 10);
    assertEquals(-1, potions.compareTo(belts1));
    Belts belts2 = new Belts("Belts2", BeltSize.LARGE, 20, 20);
    assertEquals(-1, potions.compareTo(belts2));
    FootWear footWear = new FootWear("Footwear1", 10);
    assertEquals(-1, potions.compareTo(footWear));
  }
}