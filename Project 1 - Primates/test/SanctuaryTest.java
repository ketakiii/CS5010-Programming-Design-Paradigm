import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the Sanctuary class and its methods.
 */
public class SanctuaryTest {

  /**
   * This class tests if the Sanctuary is being initialized properly.
   */
  @Test public void testSanctuary() {
    assertThrows(IllegalArgumentException.class, () -> new Sanctuary(1, -1,
        3, 5));
    assertThrows(IllegalArgumentException.class, () -> new Sanctuary(2, 2,
        0, 5));
  }

  /**
   * This class tests if the animals are getting received correctly into the sanctuary - isolation.
   */
  @Test public void testreceiveAnimal() {
    Sanctuary s1 = new Sanctuary(22, 1, 3, 50);
    Animal a1 = new Animal();
    Animal a2 = new Animal();
    s1.receiveAnimal(a1);
    int count = 0;
    for (Animal a: s1.isolation.getCages()) {
      if (a != null) {
        count += 1;
      }
    }
    assertEquals(1, count);
    assertThrows(IllegalArgumentException.class, () -> s1.receiveAnimal(a2));
  }

  /**
   * This method tests if the medical help is being given and the fields are being initialized
   * validly.
   */
  @Test public void testgiveMedicalHelp() {
    Sanctuary s = new Sanctuary(22, 3, 3, 50);
    Animal a1 = new Animal();
    Animal a2 = new Animal();
    Animal a3 = new Animal();
    s.receiveAnimal(a1);
    s.receiveAnimal(a2);
    s.receiveAnimal(a3);
    s.giveMedicalHelp(0, "Tom", Species.SPIDER, 2,
        20, 2, Food.INSECTS, Sex.MALE);
    assertThrows(IllegalArgumentException.class,
        () -> s.giveMedicalHelp(1, "Daniel", Species.SPIDER, 20,
            -20, 2, Food.INSECTS, Sex.MALE));
  }

  /**
   * This method tests if a single animal can be moved to an enclosure.
   */
  @Test public void testsendAnimal() {
    Sanctuary s = new Sanctuary(22, 3, 1, 10);  // try with 2
    Animal a = new Animal();
    Animal b = new Animal();
    Animal c = new Animal();
    s.receiveAnimal(a);
    s.receiveAnimal(b);
    s.receiveAnimal(c);
    s.giveMedicalHelp(0, "Tom", Species.SPIDER, 25,
        20, 2, Food.INSECTS, Sex.MALE);
    s.giveMedicalHelp(1, "Daniel", Species.SPIDER, 25,
        20, 2, Food.INSECTS, Sex.MALE);
    assertEquals(true, s.sendAnimal(a));
    assertEquals(false, s.sendAnimal(b));
  }

  /**
   * This method tests if all the animals in the cages are being moved to the enclosure
   * given medical help and space in the enclosure.
   */
  @Test public void testsendAllAnimal() {
    Sanctuary s = new Sanctuary(22, 4, 2, 10);
    Animal a = new Animal();
    Animal b = new Animal();
    Animal c = new Animal();
    Animal d = new Animal();
    s.receiveAnimal(a);
    s.receiveAnimal(b);
    s.receiveAnimal(c);
    s.receiveAnimal(d);
    s.giveMedicalHelp(0, "Daniel", Species.SPIDER, 25,
        20, 2, Food.NUTS, Sex.MALE);
    s.giveMedicalHelp(1, "Surabhi", Species.MANGABEY, 25,
        20, 2, Food.NUTS, Sex.MALE);
    s.giveMedicalHelp(2, "Pranav", Species.HOWLER, 25,
        20, 2, Food.NUTS, Sex.MALE);
    s.sendAllAnimal();
    s.giveMedicalHelp(3, "Tom", Species.SPIDER, 25,
        20, 2, Food.NUTS, Sex.FEMALE);
    s.sendAllAnimal();
    assertEquals("[Isolation]", s.getSpeciesHouse(Species.HOWLER).toString());
    assertEquals("[Isolation, Enclosure]", s.getSpeciesHouse(Species.SPIDER).toString());
    int curLength = 0;
    for (Animal animal : s.isolation.getCages()) {
      if (animal != null) {
        curLength++;
      }
    }
    s.sendAllAnimal();
    int newLength = 0;
    for (Animal animal : s.isolation.getCages()) {
      if (animal != null) {
        newLength++;
      }
    }
    assertTrue(newLength <= curLength);

    Sanctuary s1 = new Sanctuary(22, 1, 1, 30);
    Animal a1 = new Animal();
    s1.receiveAnimal(a1);
    s1.giveMedicalHelp(0, "Daniel", Species.SPIDER, 25,
        20, 2, Food.NUTS, Sex.MALE);
    assertEquals("[Isolation]", s1.getSpeciesHouse(Species.SPIDER).toString());
    s1.sendAllAnimal();
    assertEquals("[Enclosure]", s1.getSpeciesHouse(Species.SPIDER).toString());
  }

  /**
   * This method tests if the species are being reported in the alphabetic manner as expected.
   */
  @Test public void testreportSpecies() {
    Sanctuary s = new Sanctuary(22, 4, 2, 50);
    Animal a = new Animal();
    Animal b = new Animal();
    Animal c = new Animal();
    Animal d = new Animal();
    s.receiveAnimal(a);
    s.receiveAnimal(b);
    s.receiveAnimal(c);
    s.receiveAnimal(d);
    s.giveMedicalHelp(0, "Tom", Species.SPIDER, 2,
        22, 3, Food.INSECTS, Sex.MALE);
    s.giveMedicalHelp(1, "Daniel", Species.SPIDER, 2,
        22, 3, Food.INSECTS, Sex.FEMALE);
    s.giveMedicalHelp(2, "Ted", Species.GUEREZA, 2,
        22, 3, Food.INSECTS, Sex.MALE);
    s.giveMedicalHelp(3, "Tom", Species.MANGABEY, 2,
        22, 3, Food.INSECTS, Sex.MALE);
    s.sendAllAnimal();
    assertEquals(("[GUEREZA : Enclosure, MANGABEY : Isolation, SPIDER : Enclosure]"),
        s.reportSpecies().toString());
  }

  /**
   * This method tests if the species housed in isolation and enclosure are being reported.
   */
  @Test public void testgetSpeciesHouse() {
    Sanctuary s = new Sanctuary(22, 4, 2, 20);
    Animal a = new Animal();
    Animal b = new Animal();
    Animal c = new Animal();
    Animal d = new Animal();
    s.receiveAnimal(a);
    s.receiveAnimal(b);
    s.receiveAnimal(c);
    s.receiveAnimal(d);
    s.giveMedicalHelp(0, "Daniel", Species.SPIDER, 25,
        20, 2, Food.NUTS, Sex.MALE);
    s.giveMedicalHelp(1, "Surabhi", Species.SPIDER, 25,
        20, 2, Food.NUTS, Sex.MALE);
    s.giveMedicalHelp(2, "Pranav", Species.HOWLER, 25,
        20, 2, Food.NUTS, Sex.MALE);
    s.sendAllAnimal();
    s.giveMedicalHelp(3, "Tom", Species.SPIDER, 25,
        20, 2, Food.NUTS, Sex.FEMALE);
    s.sendAllAnimal();
    assertEquals("[Enclosure]", s.getSpeciesHouse(Species.HOWLER).toString());
    assertEquals("[Isolation, Enclosure]", s.getSpeciesHouse(Species.SPIDER).toString());
    assertEquals("[This species has no housing yet!]", s.getSpeciesHouse(Species.MANGABEY).toString());
  }

  /**
   * This method tests if the sign is produced for all the animals in the required format.
   */
  @Test public void testproduceSign() {
    Sanctuary s1 = new Sanctuary(2, 3, 2, 30);
    Animal a1 = new Animal();
    Animal a2 = new Animal();
    Animal a3 = new Animal();
    s1.receiveAnimal(a1);
    s1.receiveAnimal(a2);
    s1.receiveAnimal(a3);
    s1.giveMedicalHelp(0, "Daniel", Species.SPIDER, 25,
        20, 2, Food.INSECTS, Sex.MALE);
    s1.giveMedicalHelp(1, "Surabhi", Species.SPIDER, 25,
        20, 2, Food.FRUITS, Sex.MALE);
    s1.giveMedicalHelp(2, "Pranav", Species.HOWLER, 25,
        20, 2, Food.NUTS, Sex.MALE);
    s1.sendAllAnimal();
    assertEquals("[Daniel MALE INSECTS, Surabhi MALE FRUITS]",
        s1.produceSign(0).toString());
    assertEquals("[Pranav MALE NUTS]", s1.produceSign(1).toString());
  }

  /**
   * This method tests if the shopping list is being produced correctly with the correct quantity of
   * food required.
   */
  @Test public void testproduceShoppingList() {
    Sanctuary s1 = new Sanctuary(2, 3, 2, 30);
    Animal a1 = new Animal();
    Animal a2 = new Animal();
    Animal a3 = new Animal();
    s1.receiveAnimal(a1);
    s1.receiveAnimal(a2);
    s1.receiveAnimal(a3);
    s1.giveMedicalHelp(0, "Daniel", Species.SPIDER, 10,
        20, 2, Food.INSECTS, Sex.MALE);
    s1.giveMedicalHelp(1, "Surabhi", Species.SPIDER, 25,
        20, 2, Food.FRUITS, Sex.MALE);
    s1.giveMedicalHelp(2, "Pranav", Species.HOWLER, 25,
        20, 2, Food.NUTS, Sex.MALE);
    assertEquals("[NUTS : 500, INSECTS : 100, FRUITS : 500]",
        s1.produceShoppingList().toString());
  }

  /**
   * This method tests if the names of animals are being produced correctly with corresponding info.
   */
  @Test public void testproduceNames() {
    Sanctuary s1 = new Sanctuary(2, 4, 2, 10);
    Animal a1 = new Animal();
    Animal a2 = new Animal();
    Animal a3 = new Animal();
    s1.receiveAnimal(a1);
    s1.receiveAnimal(a2);
    s1.receiveAnimal(a3);
    s1.giveMedicalHelp(0, "Daniel", Species.SPIDER, 30,
        20, 2, Food.INSECTS, Sex.MALE);
    s1.giveMedicalHelp(1, "Surabhi", Species.SPIDER, 25,
        20, 2, Food.FRUITS, Sex.MALE);
    s1.giveMedicalHelp(2, "Palki", Species.HOWLER, 25,
        20, 2, Food.NUTS, Sex.MALE);
    s1.sendAllAnimal();
    assertEquals("[Daniel : House - Enclosure : Id - 0, " +
        "Palki : House - Isolation : Id - 2, " +
        "Surabhi : House - Enclosure : Id - 1]", s1.produceNames().toString());
  }

  /**
   * This method test if the isolation size increases successfully.
   */
  @Test public void testincreaseIsolationSize() {
    Sanctuary s = new Sanctuary(2, 3, 1, 10);
    assertEquals(3, s.getIsolationSize());
    s.increaseIsolationSize(3);
    assertEquals(6, s.getIsolationSize());  // exceptions
  }

  /**
   * This method tests if the enclosure size increases successfully.
   */
  @Test public void testincreaseEnclosureSize() {
    Sanctuary s = new Sanctuary(2, 3, 1, 10);
    assertEquals(1, s.getEnclosureSize());
    s.increaseEnclosureSize(3);
    assertEquals(4, s.getEnclosureSize());  //exceptions
  }

  /**
   * This method tests if the animal is moved from an isolation to enclosure successfully.
   */
  @Test public void testmoveAnimal() {
    Sanctuary s1 = new Sanctuary(2, 4, 2, 10);
    Sanctuary s2 = new Sanctuary(2, 1, 1, 10);
    Animal a1 = new Animal();
    Animal a2 = new Animal();
    Animal a3 = new Animal();
    s1.receiveAnimal(a1);
    s1.receiveAnimal(a2);
    s1.receiveAnimal(a3);
    s1.giveMedicalHelp(0, "Daniel", Species.SPIDER, 30,
        20, 2, Food.INSECTS, Sex.MALE);
    s1.giveMedicalHelp(2, "Palki", Species.HOWLER, 25,
        20, 2, Food.NUTS, Sex.MALE);
    assertEquals(true, s1.moveAnimal(0, s2));
    assertEquals(false, s1.moveAnimal(1, s2));
  }

  @Test public void testgetIsolationSize() {
    Sanctuary s1 = new Sanctuary(2, 4, 2, 10);
    assertEquals(4, s1.getIsolationSize());
  }

  @Test public void testgetEnclosureSize() {
    Sanctuary s1 = new Sanctuary(2, 4, 2, 10);
    assertEquals(2, s1.getEnclosureSize());
  }
}


