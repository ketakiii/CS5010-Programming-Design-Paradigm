/**
 * This class is the abstract class of the gear interface.
 */
public class AbstractWeapon implements Weapon {

  protected Weapons name;
  protected int minDamage;
  protected int maxDamage;
  protected SwordType swordType;

  /**
   * The override method that returns the weapon name.
   * @return weapon name
   */
  @Override public Weapons getWeaponName() {
    return this.name;
  }

  /**
   * The override method that returns the min damage done by the weapon.
   * @return min damage
   */
  @Override public int getMinDamage() {
    return this.minDamage;
  }

  /**
   * The override method that returns the max damage done by the weapon.
   * @return max damage
   */
  @Override public int getMaxDamage() {
    return this.maxDamage;
  }

  protected SwordType getSwordType() {
    return swordType;
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Weapons {" + "name = '").append(this.getWeaponName()).append('\'')
        .append(", minDamage = ").append(this.getMinDamage()).append(", maxDamage = ")
        .append(this.getMaxDamage()).append("}");
    return sb.toString();
  }
}
