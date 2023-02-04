import java.util.List;
import java.util.Map;

/**
 * This is an abstract class that implements the Housing interface.
 */
public abstract class AbstractHousing implements Housing {
  @Override public void cage(Animal a) {
  }

  @Override public Map<Food, Integer> produceShoppingList() {
    return null;
  }

  @Override public List<String> produceSign() {
    return null;
  }

  @Override public boolean addAnimal(Animal animal) {
    return false;
  }

  @Override public void setSpecies(Species species) {
  }

  @Override public boolean isEmpty() {
    return true;
  }

  @Override public void removeAnimal(int a) {
  }

  @Override public int getSpace() {
    return 0;
  }

  @Override public List<Species> reportSpecies() {
    return null;
  }

  @Override public Species getSpecies() {
    return null;
  }

  @Override public Animal[] getCages() {
    return null;
  }

  @Override public Animal getAnimal(int cageId) {
    return null;
  }

  @Override public void increaseCageLength(int size) {
  }

  @Override public void giveMedicalAttention(int id, String name, Species species, int size,
      int weight, int age, Food food, Sex sex) {

  }

  @Override public List<String> produceNames() {
    return null;
  }

}
