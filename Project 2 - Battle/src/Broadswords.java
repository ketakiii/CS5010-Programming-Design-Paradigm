/**
 * This class defines the weapon broadswords.
 */
public class Broadswords extends AbstractWeapon {

  /**
   * This is the constructor of the class Broadswords which initializes the name, minDamage,
   * maxDamage done by the weapon.
   * @param name - name of the broadsword
   */
  public Broadswords(Weapons name) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid name!");
    }
    this.name = name;
    this.swordType = SwordType.BROADSWORDS;
    this.minDamage = 6;
    this.maxDamage = 10;
  }

  @Override public SwordType getSwordType() {
    return this.swordType;
  }

  @Override public String toString() {
    return "Weapons {" + "name='" + this.getWeaponName() + '\''
        + ", minDamage = " + this.getMinDamage() + ", maxDamage = " + this.getMaxDamage()
        + ", SwordType = " + this.getSwordType() + "}";
  }
}
