/**
 * This class defines the weapon axes.
 */
public class Axes extends AbstractWeapon {

  /**
   * This is the constructor of the class Axes which initializes the name, minDamage, maxDamage done
   * by the weapon.
   * @param name name of axes
   */
  public Axes(Weapons name) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid name");
    } else {
      this.name = name;
      this.minDamage = 6;
      this.maxDamage = 10;
    }
  }
}
