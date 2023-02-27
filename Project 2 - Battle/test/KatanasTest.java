import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * This class tests the methods in the Katana class.
 */
public class KatanasTest {

  @Test public void testKatanas() {
    assertThrows(IllegalArgumentException.class, () -> new Katanas(null));
  }
}