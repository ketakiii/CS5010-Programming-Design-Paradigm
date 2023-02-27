/**
 * This class defines the Belts and its required methods.
 */
public class Belts extends AbstractGear {

  /**
   * This is the constructor of the Belts class.
   * @param name - belt name
   * @param size - belt size
   * @param newCharisma - the charisma assigned
   * @param newStrength - the strength assigned
   */
  public Belts(String name, BeltSize size, int newCharisma, int newStrength) {
    if (name == null || size == null) {
      throw new IllegalArgumentException("Invalid values!");
    }
    this.gearName = name;
    this.gearType = GearType.BELTS;
    this.newStrength = newAbility(size);
    this.newConstitution = 0;
    this.newCharisma = newCharisma;
    this.newDexterity = 0;
    this.size = size;
  }

  /**
   * This method retuns the belt size of the belt.
   * @return belt size
   */
  public BeltSize getBeltSize() {
    if (this.size == null) {
      throw new IllegalArgumentException("Invalid size!");
    } else {
      return this.size;
    }
  }

  /**
   * This method compares the different objects in the ordered defined.
   * @param o - object
   * @return ordered objects compared
   */
  @Override public int compareTo(Gear o) {
    if (o instanceof HeadGear || o instanceof Potions) {
      return 1;
    } else if (o instanceof FootWear) {
      return -1;
    } else if (o instanceof Belts p) {
      int id =  this.getBeltSize().compareTo(p.getBeltSize());
      if (id == 0) {
        return this.getName().compareTo(o.getName());
      } else {
        return id;
      }
    }
    return 0;
  }

  @Override public String toString() {
    return String.format("Name: %s, Gear Type: (%s - %s)",
        this.getName(), this.getType(), this.getBeltSize());
  }

  @Override public GearType getType() {
    return this.gearType;
  }

  @Override public String getName() {
    return this.gearName;
  }

  //private method
  private int newAbility(BeltSize size) {
    if (size == BeltSize.SMALL) {
      return 1;
    } else if (size == BeltSize.MEDIUM) {
      return 2;
    } else if (size == BeltSize.LARGE) {
      return 3;
    }
    return 0;
  }
}
