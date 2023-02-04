import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class defines the Enclosure of the two available Housings.
 */
public class Enclosure extends AbstractHousing {

  Troops troops;

  /**
   * This is the constructor of the Enclosure class which intializes the
   * troop size of the enclosure.
   *
   * @param troopsize - the troop size of each enclosure
   */
  public Enclosure(int troopsize) {
    if (troopsize < 1) {
      throw new IllegalArgumentException("Invalid troop size");
    } else {
      this.troops = new Troops(troopsize);
    }
  }

  /**
   * This method sets the species of a troop when the first Animal is added.
   *
   * @param species - the species of the first animal added to the troop
   */
  @Override public void setSpecies(Species species) {
    this.troops.setSpecies(species);
  }

  /**
   * This method gives the set Species from the previous method.
   *
   * @return species set previously
   */
  public Species getSpecies() {
    return this.troops.getSpecies();
  }

  /**
   * This method produces the signs - the name, the sex, the favourite food of an Animal.
   *
   * @return String of the Name, Sex, Favourite food of an Animal
   */
  @Override public List<String> produceSign() {
    List<String> result = new ArrayList<>();
    for (Animal a : this.troops.getAnimalList()) {
      String s = a.getName() + " " + a.getSex() + " " + a.getFood();
      result.add(s);
    }
    return result;
  }

  /**
   * This method gives the space left in a troop.
   *
   * @return the space left in a troop after adding Animals
   */
  @Override public int getSpace() {
    // Get Troop Size
    // Get Animal Size
    return this.troops.getTroopsize() - this.troops.getAnimalsOccupiedSize();
  }

  /**
   * This method gives a true value if Animals is added in a troop.
   *
   * @param animal - the animal to be added
   *
   * @return boolean value if the animal is added
   */
  @Override public boolean addAnimal(Animal animal) {
    return this.troops.addAnimal(animal);
  }

  /**
   * This method gives a true value if the troop is empty.
   *
   * @return the boolean value if the troop is empty
   */
  @Override public boolean isEmpty() {
    return this.troops.isEmpty();
  }

  /**
   * This method gives a shopping list based on the size of the animal species and quantity of food
   * required for each animal species based on an animal size.
   *
   * @return result - a map of the food and its quantity required
   */
  @Override public Map<Food, Integer> produceShoppingList() {
    Map<Food, Integer> result = new HashMap<>();
    for (Animal a : this.troops.getAnimalList()) {
      result.put(a.getFood(),
          Housing.getQuantity(a.getSize()) + result.getOrDefault(a.getFood(), 0));
    }
    return result;
  }

  /**
   * This method produces names of animals with their Enclosure location.
   *
   * @return result
   */
  @Override public List<String> produceNames() {
    List<String> result = new ArrayList<>();
    for (Animal a : this.troops.getAnimalList()) {
      result.add(a.getName() + " : House - Enclosure : Id - ");
    }
    return result;
  }
}
