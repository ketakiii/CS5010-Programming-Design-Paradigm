import java.util.ArrayList;
import java.util.List;

/**
 * This class defines methods required in and about the Troops class.
 */
public class Troops {
  Species species;
  int troopsize;
  List<Animal> animalList;

  /**
   * This defines the constructor of the Troops class - initializing the animalList and troopsize.
   *
   * @param troopsize - defines the troop size of the Animals
   */
  public Troops(int troopsize) {
    this.animalList = new ArrayList<>();
    this.troopsize = troopsize;
  }

  /**
   * This method sets the species of a troop.
   *
   * @param species - sets the Species of the troop
   */
  public void setSpecies(Species species) {
    this.species = species;
  }

  /**
   * This method gives the species of the troops that was set earlier.
   *
   * @return species
   */
  public Species getSpecies() {
    return species;
  }

  /**
   * This method sets a troopsize.
   *
   * @return troopsize
   */
  public int getTroopsize() {
    return troopsize;
  }

  /**
   * This method gives the animalList of the troop.
   *
   * @return animalList
   */
  public List<Animal> getAnimalList() {
    return animalList;
  }

  /**
   * This method gives size of the animalList - of a troop
   *
   * @return currentSize
   */
  public int getAnimalsOccupiedSize() {
    int currentSize = 0;
    for (Animal animal : animalList) {
      currentSize += Housing.getSize(animal.getSize());
    }
    return currentSize;
  }

  /**
   * This method adds an animal in an animalList.
   *
   * @param animal - an animal to add in the animalList
   *
   * @return updated animalList
   */
  public boolean addAnimal(Animal animal) {
    return this.animalList.add(animal);
  }

  /**
   * This method checks if animalList if empty.
   *
   * @return a boolean on if the animalList is empty
   */
  public boolean isEmpty() {
    return this.animalList.size() == 0;
  }

}
