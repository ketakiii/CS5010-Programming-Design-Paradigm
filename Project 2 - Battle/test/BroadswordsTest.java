import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * This class BroadswordsTest tests the methods in the Broadswords class.
 */
public class BroadswordsTest {

  @Test public void testBroadswords() {
    assertThrows(IllegalArgumentException.class, () -> new Broadswords(null));
  }
}