import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * This class tests the Axes class.
 */
public class AxesTest {

  @Test public void testAxes() {
    assertThrows(IllegalArgumentException.class, () -> new Axes(null));
  }

}