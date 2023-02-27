import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Random;
import org.junit.Test;

/**
 * This class BeltsTest tests the methods in the Belts class.
 */
public class BeltsTest {
  Random random;
  Belts belts = new Belts("Belt1", BeltSize.MEDIUM, 20, 13);

  @Test public void testBelts() {
    assertThrows(IllegalArgumentException.class, () -> new Belts(null, BeltSize.MEDIUM, 0, 0));
    assertThrows(IllegalArgumentException.class, () -> new Belts("Belt2", null, 0, 10));
  }

  @Test public void testGetBeltSize() {
    BeltSize expected = BeltSize.MEDIUM;
    assertEquals(expected, belts.getBeltSize());
    assertThrows(IllegalArgumentException.class, () -> new Belts("Belt2", null, 0, 10));
  }

  @Test public void testCompareTo() {
    random = new Random();
    HeadGear headGear = new HeadGear("HeadGear1", 20);
    Potions potions = new Potions("Potions1", 20, random);
    FootWear footWear = new FootWear("Footwear1", 20);

    //assertEquals(1, belts5.compareTo(belts1));
    Belts belts1 = new Belts("Belt1", BeltSize.SMALL, 20, 13);
    assertEquals(1, belts1.compareTo(headGear));
    assertEquals(1, belts1.compareTo(potions));
    assertEquals(-1, belts1.compareTo(footWear));

    Belts belts2 = new Belts("A", BeltSize.SMALL, 20, 13);
    assertEquals(1, belts2.compareTo(headGear));
    assertEquals(1, belts2.compareTo(potions));
    assertEquals(-1, belts2.compareTo(footWear));

    Belts belts4 = new Belts("Belt1", BeltSize.MEDIUM, 20, 13);
    assertEquals(1, belts4.compareTo(headGear));
    assertEquals(1, belts4.compareTo(potions));
    assertEquals(-1, belts4.compareTo(footWear));

    Belts belts5 = new Belts("Belt1", BeltSize.LARGE, 20, 13);
    assertEquals(1, belts5.compareTo(headGear));
    assertEquals(1, belts5.compareTo(potions));
    assertEquals(-1, belts5.compareTo(footWear));

    Belts belts3 = new Belts("C", BeltSize.SMALL, 20, 13);
    assertEquals(1, belts3.compareTo(headGear));
    assertEquals(1, belts3.compareTo(potions));
    assertEquals(-1, belts3.compareTo(footWear));


  }

  @Test public void testToString() {

  }


}