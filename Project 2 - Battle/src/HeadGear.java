/**
 * The HeadGear class defines the HeadGear functionalities.
 */
public class HeadGear extends AbstractGear {

  /**
   * The constructor of the HeadGear.
   * @param name - name of the HeadGear
   * @param newConstitution - the new Constitution received from this gear
   */
  public HeadGear(String name, int newConstitution) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid name");
    }
    this.gearName = name;
    this.gearType = GearType.HEADGEAR; // enum
    this.newStrength = 0;    // parameters
    this.newConstitution = newConstitution;
    this.newCharisma = 0;
    this.newDexterity = 0;
  }

  /**
   * This method compares the different objects in the defined order.
   * @param o object to be compared
   * @return result of the comparison
   */
  @Override public int compareTo(Gear o) {
    if (o instanceof Potions || o instanceof Belts || o instanceof FootWear) {
      return -1;
    } else if (o instanceof HeadGear) {
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
