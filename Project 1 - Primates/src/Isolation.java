import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class defines Isolation and the methods required for implementing the same.
 */
public class Isolation extends AbstractHousing {

  Animal[] cages;

  /**
   * This method is the constructor of the Isolation class that initialized that cages.
   *
   * @param n - this defines the number of isolation cages.
   */
  public Isolation(int n) {
    if (n < 1) {
      throw new IllegalArgumentException("Number of cages is less than 1!");
    } else {
      this.cages = new Animal[n];
    }
  }

  /**
   * This method gives the cages as its output.
   *
   * @return cages
   */
  public Animal[] getCages() {
    return this.cages;
  }

  /**
   * This method gives the count of the animals present in the cages and adds the animal
   * in an isolation if it finds any null spot, Else it throws an Exception.
   *
   * @param a - the animal is given as an input
   */
  @Override public void cage(Animal a) {
    int count = 0;
    for (Animal animal : this.cages) {
      if (animal != null) {
        count += 1;
      } else {
        break;
      }
    }
    if (count != cages.length) {
      this.cages[count] = a;
    } else {
      throw new IllegalArgumentException("The cage is full!");
    }
  }

  /**
   * This method takes the parameters of the animal and returns medical attention given
   * true once it has done taking the animal paramters.
   *
   * @param id      - the cage id
   * @param name    - the animal name
   * @param species - the animal species
   * @param size    - the animal size
   * @param weight  - the animal weight
   * @param age     - the animal age
   * @param food    - the animal's favourite food
   * @param sex     - the animal's sex
   */
  public void giveMedicalAttention(int id, String name, Species species, int size, int weight,
      int age, Food food, Sex sex) {
    // Valid Cage
    // If medical attention not given
    Animal a = this.cages[id];
    a.medical = true;
    a.setName(name);
    a.setSpecies(species);
    a.setSize(size);
    a.setWeight(weight);
    a.setAge(age);
    a.setFood(food);
    a.setSex(sex);
  }

  /**
   * This method removes the animal from the isolation once it has been added to the enclosure.
   *
   * @param a - cage id of the animal that has been added to the enclosure
   */
  @Override public void removeAnimal(int a) {
    this.cages[a] = null;
  }

  /**
   * This method reports the species in a Housing - here Isolation.
   *
   * @return species
   */
  //doubt
  @Override public List<Species> reportSpecies() {
    List<Species> species = new ArrayList<>();
    for (Animal animal : this.cages) {
      if (animal != null) {
        if (animal.isMedicalAttention()) {
          species.add(animal.getSpecies());
        }
      }
    }
    return species;
  }

  /**
   * This method produces a shopping list for the animals depending on their size.
   *
   * @return result - dictionary of food and the quantity of the food
   */
  @Override public Map<Food, Integer> produceShoppingList() {
    Map<Food, Integer> result = new HashMap<>();
    for (Animal a : this.cages) {
      if (a != null && a.isMedicalAttention()) {
        result.put(a.getFood(),
            Housing.getQuantity(a.getSize()) + result.getOrDefault(a.getFood(), 0));
      }
    }
    return result;
  }

  /**
   * This method gives us the names of the animals in alphabetic order present
   * in the Isolation Housing.
   *
   * @return result - list of animals
   */
  @Override public List<String> produceNames() {
    List<String> result = new ArrayList<>();

    for (int i = 0; i < this.cages.length; i++) {
      Animal a = this.cages[i];
      if (a != null && a.isMedicalAttention()) {
        result.add(a.getName() + " : House - Isolation : Id - " + i);
      }
    }
    return result;
  }

  /**
   * This method increases the cages count in the isolation if required.
   *
   * @param size - the size we want to increase it by
   */
  @Override public void increaseCageLength(int size) {
    if (size < 1) {
      throw new IllegalArgumentException("Not increased!");
    } else {
      int newCageLength = this.cages.length + size;
      Animal[] newCages = new Animal[newCageLength];
      System.arraycopy(this.cages, 0, newCages, 0, this.cages.length);
      this.cages = newCages;
    }
  }

  /**
   * This method gets us the animal at the particular cage id that we want.
   *
   * @param cageId - cage id we want to access the animal from
   *
   * @return animal object in the cage
   */
  @Override public Animal getAnimal(int cageId) {
    return this.cages[cageId];
  }
}
