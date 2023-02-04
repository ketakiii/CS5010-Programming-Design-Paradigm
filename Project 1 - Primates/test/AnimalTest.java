import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * This class tests the methods in the Animal class.
 */
public class AnimalTest {
  Animal animal = new Animal();
  Animal a2 = new Animal();

  /**
   * This method tests if the medical attention is given.
   */
  @Test public void testisMedicalAttention() {
    boolean expected = false;
    assertEquals(expected, animal.isMedicalAttention());
  }

  @Test public void testsetId() {
    assertThrows(IllegalArgumentException.class, () -> animal.setId(-1));
    assertThrows(IllegalArgumentException.class, () -> animal.setId(-5));
  }

  /**
   * This method tests if the set id is returned by the getId method.
   */
  @Test public void testgetId() {
    animal.setId(32);
    int expected = 32;
    assertEquals(expected, animal.getId());
  }

  @Test public void testsetName() {
    assertThrows(IllegalArgumentException.class, () -> animal.setName(null));
  }

  /**
   * This method tests if the set name is returned by the getName method.
   */
  @Test public void testgetName() {
    animal.setName("Tom");
    String expected = "Tom";
    assertEquals(expected, animal.getName());
  }

  @Test public void testsetSpecies() {
    assertThrows(IllegalArgumentException.class, () -> animal.setSpecies(null));
  }

  /**
   * This method tests if the set species is returned by the getSpecies method.
   */
  @Test public void testgetSpecies() {
    animal.setSpecies(Species.HOWLER);
    Species expected = Species.HOWLER;
    assertEquals(expected, animal.getSpecies());
  }

  @Test public void testsetSize() {
    assertThrows(IllegalArgumentException.class, () -> animal.setSize(0));
    assertThrows(IllegalArgumentException.class, () -> animal.setSize(-2));
  }

  /**
   * This method tests if the set size is returned by the getSize method.
   */
  @Test public void testgetSize() {
    animal.setSize(20);
    int expected = 20;
    assertEquals(expected, animal.getSize());
  }

  @Test public void testsetWeight() {
    assertThrows(IllegalArgumentException.class, () -> animal.setWeight(0));
    assertThrows(IllegalArgumentException.class, () -> animal.setWeight(-10));
  }

  /**
   * This method tests if the set weight is returned by the getWeight method.
   */
  @Test public void testgetWeight() {
    animal.setWeight(30);
    int expected = 30;
    assertEquals(expected, animal.getWeight());
  }

  @Test public void testsetAge() {
    assertThrows(IllegalArgumentException.class, () -> animal.setAge(-10));
    assertThrows(IllegalArgumentException.class, () -> animal.setAge(-1));
  }

  /**
   * This method tests if the set age is returned by the getAge method.
   */
  @Test public void testgetAge() {
    animal.setAge(4);
    int expected = 4;
    assertEquals(expected, animal.getAge());
  }

  @Test public void testsetFood() {
    assertThrows(IllegalArgumentException.class, () -> animal.setFood(Food.valueOf("Apricot")));
    assertThrows(IllegalArgumentException.class, () -> animal.setFood(null));
  }

  /**
   * This method tests if the set food is returned by the getFood method.
   */
  @Test public void testgetFood() {
    animal.setFood(Food.FRUITS);
    Food expected = Food.FRUITS;
    assertEquals(expected, animal.getFood());
  }

  @Test public void testsetSex() {
    assertThrows(IllegalArgumentException.class, () -> animal.setSex(null));
  }

  /**
   * This method tests if the set sex is returned by the getSex method.
   */
  @Test public void testgetSex() {
    animal.setSex(Sex.FEMALE);
    Sex expected = Sex.FEMALE;
    assertEquals(expected, animal.getSex());
  }

  @Test public void testValidAnimal() {
    Animal a = new Animal();
    a.setId(1);
    a.setName("Tom");
    a.setSpecies(Species.HOWLER);
    a.setSize(2);
    a.setWeight(20);
    a.setAge(2);
    a.setFood(Food.NUTS);
    a.setSex(Sex.MALE);

    a2.setId(1);
    assertThrows(IllegalArgumentException.class, () -> a2.setName(null));
    a2.setSpecies(Species.HOWLER);
    a2.setSize(2);
    a2.setWeight(20);
    a2.setAge(2);
    assertThrows(IllegalArgumentException.class, () -> a2.setFood(null));
    a2.setSex(Sex.MALE);
    //assertThrows(IllegalArgumentException.class, a2.validAnimal());
  }
}