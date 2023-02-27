import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * This class tests the method in the HeadGear class.
 */
public class HeadGearTest {

  Random random;

  @Test public void testHeadGear() {
    assertThrows(IllegalArgumentException.class, () -> new HeadGear(null, 10));
  }

  @Test public void testCompareTo() {
    random = new Random();
    HeadGear headGear = new HeadGear("Headgear1", 10);
    HeadGear headGear2 = new HeadGear("HeadGear1", 10);
    Potions potions = new Potions("Potions1", 10, random);
    assertEquals(-1, headGear.compareTo(potions));
    Belts belts1 = new Belts("Belts1", BeltSize.SMALL, 10, 10);
    assertEquals(-1, headGear.compareTo(belts1));
    Belts belts2 = new Belts("Belts2", BeltSize.LARGE, 15, 20);
    assertEquals(-1, headGear.compareTo(belts2));
    FootWear footWear = new FootWear("Footwear", 10);
    assertEquals(-1, headGear.compareTo(footWear));
    HeadGear headGear1 = new HeadGear("Headgear1", 10);
    assertEquals(0, headGear.compareTo(headGear1));
    //assertEquals(-1, headGear.compareTo(headGear2));
  }

}