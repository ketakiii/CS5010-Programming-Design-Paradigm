/**
 * This class defines the Katanas methods as required.
 */
public class Katanas extends AbstractWeapon {

  /**
   * This is the constructor of the Katana class.
   * @param name name of weapon
   */
  public Katanas(Weapons name) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid name!");
    } else {
      this.name = Weapons.KATANAS;
      this.swordType = SwordType.KATANAS;
      this.minDamage = 4;
      this.maxDamage = 6;
    }
  }

  @Override public SwordType getSwordType() {
    return this.swordType;
  }

  @Override public String toString() {
    return "AbstractWeapon{" + "name='" + this.getWeaponName() + '\''
        + ", minDamage = " + this.getMinDamage() + ", maxDamage = " + this.getMaxDamage()
        + ", SwordType = " + this.getSwordType() + "}";
  }
}
