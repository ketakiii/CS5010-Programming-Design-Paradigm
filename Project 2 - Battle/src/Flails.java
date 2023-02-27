/**
 * This class defines the weapon Flails.
 */
public class Flails extends AbstractWeapon {

  /**
   * This is the constructor of the class Flails which initializes the name, minDamage,
   * maxDamage done by the weapon.
   * @param name name of the flails
   */
  public Flails(Weapons name) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid name!");
    } else {
      this.name = name;
      this.minDamage = 8;
      this.maxDamage = 12;
    }
  }
}
