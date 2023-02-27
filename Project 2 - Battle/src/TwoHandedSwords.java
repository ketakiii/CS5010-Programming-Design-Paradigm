/**
 * This class defines the methods required by the TwoHandedSwords.
 */
public class TwoHandedSwords extends AbstractWeapon {

  /**
   * Constructor of the TwoHandedSwords class.
   * @param name name of the weapon
   */
  public TwoHandedSwords(Weapons name) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid name!");
    } else {
      this.name = name;
      this.swordType = SwordType.TWOHANDEDSWORDS;
      this.minDamage = 8;
      this.maxDamage = 12;
    }
  }

  /**
   * Returns the sword type.
   * @return sword type
   */
  @Override public SwordType getSwordType() {
    return this.swordType;
  }

  /**
   * Returns the string value of the TwoHandedSwords information.
   * @return string
   */
  @Override public String toString() {
    return "Weapons {" + "name='" + this.getWeaponName() + '\''
        + ", minDamage = " + this.getMinDamage() + ", maxDamage = " + this.getMaxDamage()
        + ", SwordType = " + this.getSwordType() + "}";
  }
}
