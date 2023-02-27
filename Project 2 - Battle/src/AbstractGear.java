/**
 * This class is the abstract class of the gear interface.
 */
public class AbstractGear implements Gear {

  protected String gearName;
  protected GearType gearType;
  protected int newStrength;
  protected int newConstitution;
  protected int newDexterity;
  protected int newCharisma;
  protected BeltSize size;
  protected int attribute = 1;

  /**
   * The override method that returns the gear name.
   * @return gear name
   */
  @Override public String getName() {
    return this.gearName;
  }

  /**
   * The override method that returns the gear type.
    * @return gear type
   */
  @Override public GearType getType() {
    return this.gearType;
  }

  @Override public int getNewStrength() {
    return this.newStrength * this.attribute;
  }

  @Override public int getNewConstitution() {
    return this.newConstitution * this.attribute;
  }

  @Override public int getNewDexterity() {
    return this.newDexterity * this.attribute;
  }

  @Override public int getNewCharisma() {
    return this.newCharisma * this.attribute;
  }

  /**
   * The override method that compares different objects and their order as defined.
   * @param g - gear
   * @return the ordered value
   */
  @Override public int compareTo(Gear g) {
    return 0;
  }

  /**
   * The override method that returns the name and type of a gear in the string format.
   * @return gear name and type in a string
   */
  @Override public String toString() {
    return String.format("Name: %s Gear Type: (%s)", this.getName(), this.getType());
  }

  /**
   * The override method that sets the attribute of a gear.
   */
  @Override public void setAttribute() {
    attribute = -1;
  }

  @Override public BeltSize getBeltSize() {
    return size;
  }
}
