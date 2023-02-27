import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * This class tests the methods in the Flails class.
 */
public class FlailsTest {
  @Test public void testFlails() {
    assertThrows(IllegalArgumentException.class, () -> new Flails(null));
  }
}