/**
 * This class sets and gets all the Animal parameters.
 */
public class Animal {

  int id;
  String name;
  Species species;
  Sex sex;
  int size;
  int weight;
  int age;
  Food food;
  boolean medical = false;
  int flag = 0;

  /**
   * This method checks if medical attention is given or not.
   *
   * @return medical - true or false
   */
  public boolean isMedicalAttention() {
    return medical;
  }

  /**
   * This method sets the id of the Animal.
   *
   * @param id - this is the id set for the Animal
   */
  public void setId(int id) {
    if (id < 0) {
      this.flag += 1;
      throw new IllegalArgumentException("Id not valid!");
    }
    this.id = id;
  }

  /**
   * This method gives the set id of the Animal.
   *
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * This method sets the name of the Animal.
   *
   * @param name - this is the name assigned to the Animal
   *
   * @return
   */
  public void setName(String name) throws IllegalArgumentException {
    if (name == null) {
      this.flag += 1;
      throw new IllegalArgumentException("Invalid name");
    }
    this.name = name;
  }

  /**
   * This method gives the name of the Animal.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * This method sets the species of the Animal.
   *
   * @param species - this is the species assigned to the Animal
   */
  public void setSpecies(Species species) {
    if (species != null) {
      this.species = species;
    } else {
      this.flag += 1;
      throw new IllegalArgumentException("This species is not in the Species list");
    }
  }

  /**
   * This method gives the species of the Animal.
   *
   * @return species
   */
  public Species getSpecies() {
    return species;
  }

  /**
   * This method sets the size of the Animal.
   *
   * @param size - this is the size assigned to the Animal
   */
  public void setSize(int size) {
    if (size <= 0) {
      this.flag += 1;
      throw new IllegalArgumentException("Invalid Size");
    }
    this.size = size;
  }

  /**
   * This method gives the size of the Animal.
   *
   * @return size
   */
  public int getSize() {
    return size;
  }

  /**
   * This method sets the weight of the Animal.
   *
   * @param weight - this is the weight assigned to the Animal
   */
  public void setWeight(int weight) {
    if (weight <= 0) {
      this.flag += 1;
      throw new IllegalArgumentException("Invalid Weight");
    }
    this.weight = weight;
  }

  /**
   * This method gives the weight of the Animal.
   *
   * @return weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   * This method sets the age of the Animal.
   *
   * @param age - this is the age assigned to the Animal
   */
  public void setAge(int age) {
    if (age < 0) {
      this.flag += 1;
      throw new IllegalArgumentException("Invalid Age");
    }
    this.age = age;
  }

  /**
   * This method gives the age of the Animal.
   *
   * @return age
   */
  public int getAge() {
    return age;
  }

  /**
   * This method sets the food of the Animal.
   *
   * @param food - this is the food assigned to the Animal
   */
  public void setFood(Food food) {
    if (food == null) {
      this.flag += 1;
      throw new IllegalArgumentException("This food is not in the Favourite food list");
    } else {
      this.food = food;
    }
  }

  /**
   * This method gives the food of the Animal.
   *
   * @return food
   */
  public Food getFood() {
    return food;
  }

  /**
   * This method sets the sex of the Animal.
   *
   * @param s - this is the sex assigned to the Animal
   */
  public void setSex(Sex s) {
    if (s != null) {
      this.sex = s;
    } else {
      this.flag += 1;
      throw new IllegalArgumentException("Invalid Sex");
    }
  }

  /**
   * This method gives the sex of the Animal.
   *
   * @return sex
   */
  public Sex getSex() {
    return sex;
  }
}
