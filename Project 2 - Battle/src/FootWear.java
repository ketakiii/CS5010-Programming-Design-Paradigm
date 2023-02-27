/**
 * This class defines the Footwear and its required methods.
 */
public class FootWear extends AbstractGear {

  /**
   * This is the constructor of the FootWear class.
   * @param name - footwear name
   * @param newDexterity - footwear dexterity
   */
  public FootWear(String name, int newDexterity) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid name");
    }
    this.gearName = name;
    this.gearType = GearType.FOOTWEAR;
    this.newStrength = 0;
    this.newConstitution = 0;
    this.newCharisma = 0;
    this.newDexterity = newDexterity;
  }

  @Override public int compareTo(Gear o) {
    if (o instanceof HeadGear || o instanceof Potions || o instanceof Belts) {
      return 1;
    } else if (o instanceof FootWear) {
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
