import static org.junit.Assert.assertEquals;

import org.junit.Test;
import person.Book;
import person.Person;

/**
 * A JUnit test class for the person.Book class.
 */

public class BookTest {

  @Test public void testsomethingimportant() {
    int expected = 3;
    assertEquals(expected, 3);
  }

  @Test public void testAnotherthing() {
    String expected = "Northeastern";
    assertEquals("MESSAGE", expected, "Northeastern");
  }

  @Test public void testgetTitle() {
    Person p = new Person("Nitin", "Kolhatkar", 1968);
    Book b = new Book("Hello", p, 199);
    assertEquals("Hello", b.getTitle());
  }

  @Test public void testgetPrice() {
    Person p = new Person("Yash", "Kolhatkar", 2004);
    Book b = new Book("Hello", p, 100);
    assertEquals(100, b.getPrice(), 0);
  }

  @Test public void testgetAuthor() {
    Person p = new Person("Vaishali", "Kolhatkar", 1968);
    Book b = new Book("Hello", p, 99);
    assertEquals(p, b.getAuthor());
  }

}