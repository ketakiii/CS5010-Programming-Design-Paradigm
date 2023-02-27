import java.util.Random;

/**
 * This class Potions defines the methods and functionalities required in Potions.
 */
public class Potions extends AbstractGear {

  /**
   * The constructor of the Potions class.
   * @param name name of Potion
   * @param newStrength the new Strength obtained from potion
   * @param random random number
   */
  public Potions(String name, int newStrength, Random random) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid name");
    }
    this.gearName = name;
    this.gearType = GearType.POTIONS;
    this.newStrength = newStrength;
    this.newConstitution = 0;
    this.newCharisma = 0;
    this.newDexterity = 0;
    int potionEffect = BattleHandler.helperInit(1, 5, random);
  }

  /**
   * This method compares the different objects in the defined order.
   * @param o object to be comapred with
   * @return result of the comparison
   */
  @Override public int compareTo(Gear o) {
    if (o instanceof HeadGear) {
      return 1;
    } else if (o instanceof Belts || o instanceof FootWear) {
      return -1;
    } else if (o instanceof Potions) {
      return this.getName().compareTo(o.getName());
    }
    return 0;
  }

  @Override public GearType getType() {
    return this.gearType;
  }

  @Override public String getName() {
    return this.gearName;
  }
}
