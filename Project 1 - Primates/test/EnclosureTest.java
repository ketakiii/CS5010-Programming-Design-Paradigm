import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import java.util.Map;
import java.util.Optional;

/**
 * This class test the Enclosure class and its methods.
 */
public class EnclosureTest {
  int troopsize = 50;
  Housing enclosure = new Enclosure(troopsize);
  Animal animal1 = new Animal();
  Animal animal2 = new Animal();

  /**
   * This method test the constructor of the Enclosure class.
   */

  @Test public void testEnclosure() {
    assertThrows(IllegalArgumentException.class, () -> new Enclosure(-2));
  }

  /**
   * This method test the getSpecies method - which is set by the setSpecies method.
   */
  @Test public void testgetSpecies() {
    enclosure.setSpecies(Species.SPIDER);
    Species expected = Species.SPIDER;
    assertEquals(expected, enclosure.getSpecies());
  }

  /**
   * This method tests the getSpace method - which gives the space left in a troop.
   */
  @Test public void testgetSpace() {
    int expected1 = 50;
    assertEquals(expected1, enclosure.getSpace());
  }

  /**
   * This method tests if the given animal has been added to the troop with a boolean value.
   */
  @Test public void testaddAnimal() {
    boolean expected = true;
    assertEquals(expected, enclosure.addAnimal(animal1));
  }

  /**
   * This method tests if the troop is empty at a given point.
   */
  @Test public void testisEmpty() {
    boolean ex1 = true;
    assertEquals(ex1, enclosure.isEmpty());
    enclosure.addAnimal(animal1);
    boolean ex2 = false;
    assertEquals(ex2, enclosure.isEmpty());
  }

  /**
   * This method tests if the method produceShopping list works well before adding any animals and
   * after adding animals to the AnimalList.
   */
  @Test public void testproduceShoppingList() {
    assertEquals(0, enclosure.produceShoppingList().size());
    animal1.setFood(Food.FRUITS);
    animal2.setFood(Food.NUTS);
    animal1.setSize(10);
    animal2.setSize(15);
    enclosure.addAnimal(animal1);
    enclosure.addAnimal(animal2);
    Map<Food, Integer> actual = enclosure.produceShoppingList();
    assertEquals(Integer.valueOf(100), actual.get(Food.FRUITS));
    assertEquals(Integer.valueOf(300), actual.get(Food.NUTS));
  }

  @Test public void testproduceSign() {
    enclosure.addAnimal(animal1);
    animal1.setName("test1");
    animal1.setFood(Food.INSECTS);
    animal1.setSex(Sex.MALE);
    assertEquals("test1 MALE INSECTS", enclosure.produceSign().get(0));
  }

  @Test public void testproduceNames() {
    enclosure.addAnimal(animal1);
    animal1.setName("test1");
    assertEquals("test1 : House - Enclosure : Id - ", enclosure.produceNames().get(0));
  }
}