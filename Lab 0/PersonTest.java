import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import person.Person;


/**
 * A JUnit test class for the person.Person class.
 */
public class PersonTest {

  private Person john;

  @Before
  public void setUp() {

    john = new Person("john", "doe", 1989);
  }

  @Test
  public void testFirst() {
    assertEquals("John", john.getFirstName());

  }

  @Test
  public void testSecond() {
    assertEquals("Doe", john.getLastName());
  }

  @Test
  public void testYearOfBirth() {
    assertEquals(1945, john.getYearOfBirth());
  }

}
