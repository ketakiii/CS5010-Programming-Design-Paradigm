import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * This class tests the Troop class and the methods within.
 */
public class TroopsTest {

  Troops troops = new Troops(20);
  Animal animal1 = new Animal();
  Animal animal2 = new Animal();
  Animal animal3 = new Animal();

  /**
   * This method tests the getSpecies method where it gives the set Species.
   */
  @Test public void testgetSpecies() {
    troops.setSpecies(Species.SAKI);
    Species expected = Species.SAKI;
    assertEquals(expected, troops.getSpecies());
  }

  /**
   * This method tests the getTroopsize method where it gives the set Troop size.
   */
  @Test public void testgetTroopsize() {
    int expected = troops.troopsize;
    assertEquals(expected, troops.getTroopsize());
  }

  /**
   * This method test the AnimalList method where it gives the list of animals added
   * to the animalList.
   */
  @Test public void testgetAnimalList() {
    List<Animal> a = new ArrayList<>();
    troops.animalList = a;
    a.add(animal1);
    a.add(animal2);
    assertEquals(a, troops.getAnimalList());
  }

  /**
   * This method tests the getAnimalsOccupiedSize which gives the occupied size of the animals
   * in the animalList.
   */
  @Test public void testgetAnimalsOccupiedSize() {
    animal1.setSize(20);
    animal2.setSize(25);
    List<Animal> a = new ArrayList<>();
    a.add(animal1);
    a.add(animal2);
    troops.animalList = a;
    assertEquals(15, troops.getAnimalsOccupiedSize());
  }

  /**
   * This method tests the addAnimal which adds animals to the animalList.
   */
  @Test public void testaddAnimal() {
    List<Animal> listofanimals = new ArrayList<>();
    listofanimals.add(animal1);
    listofanimals.add(animal2);
    List<Animal> copy = new ArrayList<>();
    copy.addAll(listofanimals);
    troops.animalList = copy;
    listofanimals.add(animal3);
    boolean expected = true;
    assertEquals(expected, troops.addAnimal(animal3));
  }

  /**
   * This method tests the isEmpty method - which checks if the animalList is empty.
   */
  @Test public void testisEmpty() {
    List<Animal> newlist = new ArrayList<>();
    boolean expected = true;
    assertEquals(expected, newlist.isEmpty());
    newlist.add(animal1);
    assertNotEquals(expected, newlist.isEmpty());
  }


}