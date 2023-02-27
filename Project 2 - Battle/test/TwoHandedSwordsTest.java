import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * This class tests the methods in the TwoHandedSwords class.
 */
public class TwoHandedSwordsTest {

  @Test public void testTwoHandedSwords() {
    assertThrows(IllegalArgumentException.class, () -> new TwoHandedSwords(null));
  }

}