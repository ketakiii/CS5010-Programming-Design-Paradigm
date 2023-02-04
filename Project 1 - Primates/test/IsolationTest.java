import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import java.util.Map;

/**
 * This method tests the Isolation class and its methods.
 */
public class IsolationTest {
  Housing isolation = new Isolation(3);
  Animal animal1 = new Animal();
  Animal animal2 = new Animal();
  Animal animal3 = new Animal();
  Animal animal4 = new Animal();
  Animal[] cage;

  /**
   * This method tests the constructor of the Isolation class.
   */
  @Test public void testIsolation() {
    assertThrows(IllegalArgumentException.class, () -> new Isolation(-3));
  }

  /**
   * This method tests the getCage method in the Isolation class.
   */
  @Test public void testgetCages() {
    assertEquals(3, isolation.getCages().length);
  }

  @Test public void testcage() {
    isolation.cage(animal1);
    isolation.cage(animal2);
    isolation.cage(animal3);
    assertThrows(IllegalArgumentException.class, () -> isolation.cage(animal4));
  }

  /**
   * This method tests the giveMedicalAttention method.
   */
  @Test public void testgiveMedicalAttention() {
    isolation.cage(animal1);
    isolation.cage(animal2);
    isolation.cage(animal3);

    assertThrows(IllegalArgumentException.class,
        () -> isolation.giveMedicalAttention(0, "Tom", Species.SPIDER, -2, 22, 3, Food.INSECTS,
            Sex.MALE));
    assertThrows(IllegalArgumentException.class,
        () -> isolation.giveMedicalAttention(0, "Tom", Species.SPIDER, 2, -22, 3, Food.INSECTS,
            Sex.MALE));
  }

  /**
   * This method tests the removal of an animal from an isolation when moving it to an enclosure.
   */
  @Test public void testremoveAnimal() {
    isolation.cage(animal1);
    isolation.cage(animal2);
    isolation.cage(animal3);
    isolation.removeAnimal(0);
    assertEquals(null, isolation.getCages()[0]);
  }

  /**
   * This method tests if the species are being reported in correct expected order.
   */
  @Test public void testreportSpecies() {
    isolation.cage(animal1);
    isolation.cage(animal2);
    isolation.cage(animal3);
    isolation.giveMedicalAttention(0, "Tom", Species.SPIDER, 2, 22, 3, Food.INSECTS, Sex.MALE);
    isolation.giveMedicalAttention(1, "Tom", Species.MANGABEY, 2, 22, 3, Food.INSECTS, Sex.MALE);
    isolation.giveMedicalAttention(2, "Tom", Species.HOWLER, 2, 22, 3, Food.INSECTS, Sex.MALE);
    assertEquals("[SPIDER, MANGABEY, HOWLER]", isolation.reportSpecies().toString());
  }

  /**
   * This method tests if the shopping list is being produced in the desired order -quanity of food.
   */
  @Test public void testproduceShoppingList() {
    isolation.cage(animal1);
    isolation.cage(animal2);
    isolation.cage(animal3);
    isolation.giveMedicalAttention(0, "Tom", Species.SPIDER, 20, 22, 3, Food.INSECTS, Sex.MALE);
    isolation.giveMedicalAttention(1, "Tom", Species.MANGABEY, 2, 22, 3, Food.FRUITS, Sex.MALE);
    isolation.giveMedicalAttention(2, "Tom", Species.HOWLER, 12, 22, 3, Food.NUTS, Sex.MALE);
    Map<Food, Integer> shoppingList = isolation.produceShoppingList();
    assertEquals(Integer.valueOf(300), shoppingList.get(Food.INSECTS));
  }

  /**
   * This method tests if the names are being produced in the correct alphabetic order.
   */
  @Test public void testproduceNames() {
    isolation.cage(animal1);
    isolation.cage(animal2);
    isolation.cage(animal3);
    isolation.giveMedicalAttention(0, "Tom", Species.SPIDER, 20, 22, 3, Food.INSECTS, Sex.MALE);
    isolation.giveMedicalAttention(1, "Tom", Species.MANGABEY, 2, 22, 3, Food.FRUITS, Sex.MALE);
    isolation.giveMedicalAttention(2, "Tom", Species.HOWLER, 12, 22, 3, Food.NUTS, Sex.MALE);
    assertEquals(
        "[Tom : House - Isolation : Id - 0, " + "Tom : House - Isolation : Id - 1, " + "Tom : House - Isolation : Id - 2]",
        isolation.produceNames().toString());
  }

  /**
   * This method tests if the cages are getting increased.
   */
  @Test public void testincreaseCageLength() {
    assertEquals(3, isolation.getCages().length);
    isolation.increaseCageLength(5);
    assertNotEquals(3, isolation.getCages().length);
    assertEquals(8, isolation.getCages().length);
    assertThrows(IllegalArgumentException.class, () -> isolation.increaseCageLength(-9));
  }

  @Test public void testgetAnimal() {
    isolation.cage(animal1);
    isolation.cage(animal2);
    isolation.cage(animal3);
    isolation.giveMedicalAttention(0, "Tom", Species.SPIDER, 20, 22, 3, Food.INSECTS, Sex.MALE);
    isolation.giveMedicalAttention(1, "Tom", Species.MANGABEY, 2, 22, 3, Food.FRUITS, Sex.MALE);
    isolation.giveMedicalAttention(2, "Tom", Species.HOWLER, 12, 22, 3, Food.NUTS, Sex.MALE);
    assertNotNull(isolation.getAnimal(0));
    assertNotNull(isolation.getAnimal(1));
    assertNotNull(isolation.getAnimal(2));
  }
}