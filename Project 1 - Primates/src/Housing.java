import java.util.List;
import java.util.Map;

/**
 * The interface Housing defines the methods required in the Sanctuary, Isolation and Enclosure.
 */
public interface Housing {

  /**
   * This method defines the quantity of food required for respective size of animal.
   *
   * @param size - size of the animal
   *
   * @return quantity of food
   */
  static Integer getQuantity(int size) {
    if (size <= 10) {
      return 100;
    } else if (size <= 20) {
      return 300;
    }
    return 500;
  }

  /**
   * This method defines the space required for respective size of animals.
   *
   * @param size - size of the animal
   *
   * @return space required by that size of animal
   */
  static Integer getSize(int size) {
    if (size <= 10) {
      return 2;
    } else if (size <= 20) {
      return 5;
    }
    return 10;
  }

  /**
   * Adds the animals in the cage.
   *
   * @param a - animal
   */
  void cage(Animal a);

  /**
   * gives the animal medical attention and other required parameters.
   *
   * @param id      - cage id
   * @param name    - animal name
   * @param species - animal species
   * @param size    - animal size
   * @param weight  - animal weight
   * @param age     - animal age
   * @param food    - animal's favourite food
   * @param sex     - animal sex
   */
  void giveMedicalAttention(int id, String name, Species species, int size, int weight, int age,
      Food food, Sex sex);

  /**
   * Returns the cage with its contents.
   *
   * @return cage
   */
  Animal[] getCages();

  /**
   * Returns the species of an animal.
   *
   * @return species
   */
  Species getSpecies();

  /**
   * Returns the space left in an enclosure is animals are added depending on their size and space
   * required by each.
   *
   * @return space left
   */
  int getSpace();

  /**
   * Adds the animal to the list.
   *
   * @param animal - animal object to be added
   *
   * @return true if animal added
   */
  boolean addAnimal(Animal animal);

  /**
   * Removes animal from an isolation once it is added to an enclosure.
   *
   * @param a - cage id of the animal
   */
  void removeAnimal(int a);

  /**
   * Returns true if the enclosure is empty.
   *
   * @return true if empty
   */
  boolean isEmpty();

  /**
   * Sets the species of an animal.
   *
   * @param species - species to be set
   */
  void setSpecies(Species species);

  /**
   * Returns a list of species in a housing alphabetically.
   *
   * @return list of species.
   */
  List<Species> reportSpecies();

  /**
   * Produces a list of string of the sign - Name Food Sex of an animal in required format.
   *
   * @return list of string - Name Food Sex
   */
  List<String> produceSign();

  /**
   * Returns the shopping list of required quantity of food.
   *
   * @return shopping list of required quantity of food
   */
  Map<Food, Integer> produceShoppingList();

  /**
   * Returns a list of names of animals from a housing alphabetically.
   *
   * @return list of names of animals
   */
  List<String> produceNames();

  /**
   * Increases the cage length by the required number.
   *
   * @param size - the size by which we need to increase
   */
  void increaseCageLength(int size);

  /**
   * Returns the animal at a particular cage id.
   *
   * @param cageId - cage id from where an animal should be returned
   *
   * @return animal object
   */
  Animal getAnimal(int cageId);
}

