import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.sort;

/**
 * The Sanctuary class defines method required to perform all the activities in isolation, enclosure
 * moving animals from one housing to another, providing reports, etc.
 */
public class Sanctuary {

  int m;
  int n;
  int id;
  int troopSize;
  Housing isolation;
  Housing[] enclosure;

  /**
   * This is the constrcutor of the Sanctuary class and initializes the required parameters.
   *
   * @param id        - sanctuary id
   * @param n         - number of isolations
   * @param m         - number of enclosures
   * @param troopSize - troop size in the enclosures
   */
  public Sanctuary(int id, int n, int m, int troopSize) {
    this.m = m;
    this.n = n;
    this.troopSize = troopSize;
    if (n < 1) {
      throw new IllegalArgumentException("No isolation created!");
    }
    if (m < 1) {
      throw new IllegalArgumentException("No enclosure created!");
    }
    this.id = id;
    this.isolation = new Isolation(n);
    this.enclosure = new Enclosure[m];

    // for each enclousre intialize it with troop size
    for (int i = 0; i < enclosure.length; i++) {
      this.enclosure[i] = new Enclosure(troopSize);
    }
  }

  /**
   * This method receives the animal into an isolation.
   *
   * @param a - animal to be received
   *
   * @throws IllegalArgumentException - if isolation, enclosure initialized wrongly
   */
  public void receiveAnimal(Animal a) throws IllegalArgumentException {
    this.isolation.cage(a);
  }

  /**
   * This method gives medical help to an animal and assigns (initializes) it many characteristics.
   *
   * @param id      - cage id
   * @param name    - animal name
   * @param species - animal species
   * @param size    - animal size
   * @param weight  - animal weight
   * @param age     - animal age
   * @param food    - animal favourite food
   * @param sex     - animal sex
   */
  public void giveMedicalHelp(int id, String name, Species species, int size, int weight, int age,
      Food food, Sex sex) {
    this.isolation.giveMedicalAttention(id, name, species, size, weight, age, food, sex);
  }

  /**
   * This method sends one animal to an enclosure: this method is never called by itself for sending
   * an animal to an encloure. This is called iteratively for all the monkeys in the cage in the
   * sendAllAnimals method.
   *
   * @param animal - animal to be sent to enclosure
   *
   * @return true if sent
   */
  public boolean sendAnimal(Animal animal) {
    for (Housing e : this.enclosure) {
      if (e.getSpecies() == animal.getSpecies()) {
        if (e.getSpace() >= Housing.getSize(animal.getSize())) {
          if (e.addAnimal(animal)) {
            return true;
          }
        }
      }
    }

    for (Housing e : this.enclosure) {
      if (e.getSpace() >= Housing.getSize(animal.getSize()) && e.isEmpty()) {
        if (e.addAnimal(animal)) {
          e.setSpecies(animal.getSpecies());
          return true;
        }
      }
    }

    return false;
  }

  /**
   * This method sends all the animals in the cage to enclosures if medical help is given
   * and if there is space for that species in the enclosure.
   */
  public void sendAllAnimal() {
    // List all animals from isolation
    Animal[] animal_list = this.isolation.getCages();
    // For each animal call -> sendAnimal(animal)
    for (int a = 0; a < animal_list.length; a++) {
      if (animal_list[a] != null && animal_list[a].isMedicalAttention()) {
        if (sendAnimal(animal_list[a])) {
          this.isolation.removeAnimal(a);
        }
      }
    }
  }

  /**
   * This method reports the species in respective housings in alphabetic order.
   *
   * @return merge list
   */
  public List<String> reportSpecies() {
    List<Species> list1 = this.isolation.reportSpecies();
    List<Species> list2 = new ArrayList<>();
    for (Housing e : this.enclosure) {
      if (e.getSpecies() != null) {
        list2.add(e.getSpecies());
      }
    }
    List<String> merge = new ArrayList<>();
    for (Species species : list1) {
      merge.add(species + " : Isolation");
    }
    for (Species species : list2) {
      merge.add(species + " : Enclosure");
    }
    sort(merge);
    return merge;
  }

  /**
   * This method returns the housing for a particular species and reports if the species
   * is not housed in anywhere in the sanctuary.
   *
   * @param species - species to found where it is housed
   *
   * @return newset - set of the housing
   */
  public Set<String> getSpeciesHouse(Species species) {
    List<Species> list1 = this.isolation.reportSpecies();
    List<Species> list2 = new ArrayList<>();
    Set<String> newset = new HashSet<>();
    for (Housing e : this.enclosure) {
      if (e.getSpecies() != null) {
        list2.add(e.getSpecies());
      }
    }
    if (list1.contains(species)) {
      newset.add("Isolation");
    }
    if (list2.contains(species)) {
      newset.add("Enclosure");
    }
    if (newset.size() < 1) {
      newset.add("This species has no housing yet!");
    }
    return newset;
  }

  /**
   * This method produces the sign for every animal based on its cage id.
   *
   * @param id - cage id of the animal
   *
   * @return list of string of sign
   */
  public List<String> produceSign(int id) {
    return this.enclosure[id].produceSign();
  }

  /**
   * This method returns the shopping list for animals in isolation and enclosure depending
   * on their size.
   *
   * @return list of string of food and quantity required by animals
   */
  public List<String> produceShoppingList() {
    // Get Dict from Isolation
    Map<Food, Integer> isolationMap = this.isolation.produceShoppingList();
    // Get Dict from Enclosure
    Map<Food, Integer> enclosureMap = this.getEnclosureShoppingList();
    // Add two Dicts
    Set<Food> keys = new HashSet<>(isolationMap.keySet());
    keys.addAll(enclosureMap.keySet());
    Map<Food, Integer> result = new HashMap<>();
    for (Food food : keys) {
      result.put(food, isolationMap.getOrDefault(food, 0) + enclosureMap.getOrDefault(food, 0));
    }

    //return List string
    List<String> resultString = new ArrayList<>();
    for (Food food : result.keySet()) {
      resultString.add(food + " : " + result.get(food));
    }
    return resultString;
  }

  /**
   * This is a private method that gets the shopping list for animals in enclosure.
   *
   * @return result
   */
  private Map<Food, Integer> getEnclosureShoppingList() {
    Map<Food, Integer> result = new HashMap<>();
    for (Housing e : this.enclosure) {
      Map<Food, Integer> enclosureList = e.produceShoppingList();

      for (Food food : enclosureList.keySet()) {
        result.put(food, enclosureList.getOrDefault(food, 0) + result.getOrDefault(food, 0));
      }
    }
    return result;
  }

  /**
   * This method produces the names of animals in sanctuary in alphabetic order.
   *
   * @return list of names sorted
   */
  public List<String> produceNames() {
    List<String> isolationNames = this.isolation.produceNames();
    List<String> enclosureNames = this.produceEnclosureNames();
    isolationNames.addAll(enclosureNames);
    sort(isolationNames);
    return isolationNames;
  }

  /**
   * This is a private method that appends the animal names and id in enclosure
   *
   * @param enclosureNames - names of animals in enclosure
   * @param id             - id
   *
   * @return result
   */
  private List<String> appendEnclosureId(List<String> enclosureNames, int id) {
    List<String> result = new ArrayList<>();
    for (String name : enclosureNames) {
      result.add(name + id);
    }
    return result;
  }

  /**
   * This method returns the names of animals in the enclosure.
   *
   * @return result
   */
  private List<String> produceEnclosureNames() {
    List<String> result = new ArrayList<>();
    for (int i = 0; i < this.enclosure.length; i++) {
      Housing e = this.enclosure[i];
      result.addAll(this.appendEnclosureId(e.produceNames(), i));
    }
    return result;
  }

  /**
   * This method increases the isolation size if required.
   *
   * @param size - size by which we need to increase
   */
  public void increaseIsolationSize(int size) {
    this.isolation.increaseCageLength(size);
  }

  /**
   * This method increases the enclosure size if required.
   *
   * @param size - size by which we need to increase
   */
  public void increaseEnclosureSize(int size) {
    int newSize = this.enclosure.length + size;
    Housing[] newEnclosure = new Enclosure[newSize];
    // for each enclousre intialize it with troop size
    for (int i = 0; i < newEnclosure.length; i++) {
      newEnclosure[i] = new Enclosure(troopSize);
    }

    System.arraycopy(this.enclosure, 0, newEnclosure, 0, this.enclosure.length);
    this.enclosure = newEnclosure;
  }

  /**
   * The method returns true if the animal has been successfully moved to an enclosure.
   *
   * @param cageId - cage id
   * @param s1     - sanctuary to be moved to
   *
   * @return true if successful
   */
  public boolean moveAnimal(int cageId, Sanctuary s1) {
    Animal animal = this.isolation.getAnimal(cageId);
    if (animal != null && animal.isMedicalAttention()) {
      if (s1.sendAnimal(animal)) {
        this.isolation.removeAnimal(cageId);
        return true;
      }
    }
    return false;
  }

  /**
   * This method returns the isolation size.
   *
   * @return isolation size
   */
  public int getIsolationSize() {
    return this.isolation.getCages().length;
  }

  /**
   * This method gives the enclosure size.
   *
   * @return enclosure size
   */
  public int getEnclosureSize() {
    return this.enclosure.length;
  }
}
