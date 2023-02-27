import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The Player class defines the methods required by the player.
 */
public class Player implements BattlePlayer {

  private String name;
  private double health;
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;
  private int strikingPower;
  private int avoidanceAbility;
  private List<Gear> gears;
  private Weapon weapon = null;
  private int numberOfWeapon;
  private int beltUnitsLimit;
  private double mulFactor = 0;
  private int count = 0;

  /**
   * The constructor for the Player class.
   * @param name name of the player
   * @param random random
   */
  public Player(String name, Random random) {
    if (name == null || random == null) {
      throw new IllegalArgumentException("Invalid arguments");
    }
    this.name = name;
    this.constitution = getAbility(random);
    this.dexterity = getAbility(random);
    this.strength = getAbility(random);
    this.charisma = getAbility(random);
    this.health = this.constitution + this.strength + this.dexterity + this.charisma;
    this.strikingPower = 0;
    this.avoidanceAbility = 0;
    this.gears = new ArrayList<Gear>();
    this.numberOfWeapon = 1;
  }

  /**
   * Sets the name of the player.
   * @param name - name of player
   */
  @Override public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the player name.
   */
  @Override public String getName() {
    return this.name;
  }

  /**
   * Sets the strength of the player.
   * @param strength strength
   */
  @Override public void setStrength(int strength) {
    this.strength = strength;
  }

  /**
   * Returns the strength of the player.
   * @return strength strength
   */
  @Override public int getStrength() {
    return this.strength;
  }

  /**
   * Sets the constitution of the player.
   * @param constitution constitution
   */
  @Override public void setConstitution(int constitution) {
    this.constitution = constitution;
  }

  /**
   * Returns the constitution of the player.
   * @return constitution
   */
  @Override public int getConstitution() {
    return this.constitution;
  }

  /**
   * Sets the dexterity of the player.
   * @param dexterity dexterity
   */
  @Override public void setDexterity(int dexterity) {
    this.dexterity = dexterity;
  }

  /**
   * Returns the dexterity of the player.
   * @return dexterity
   */
  @Override public int getDexterity() {
    return this.dexterity;
  }

  /**
   * Sets the charisma of the player.
   * @param charisma charisma
   */
  @Override public void setCharisma(int charisma) {
    this.charisma = charisma;
  }

  /**
   * Returns the charisma of the player.
   * @return charisma
   */
  @Override public int getCharisma() {
    return this.charisma;
  }

  /**
   * Returns the information of the player in string format.
   * @return string
   */
  @Override public String toString() {
    return String.format(
        "Name: %s, Strength: %s, Constitution: %s, Dexterity: %s,"
            + "Charisma: %s ", this.getName(), this.getStrength(),
        this.getConstitution(), this.getDexterity(), this.getCharisma());
  }

  @Override public void setStrikingPower(int strikingPower) {
    this.strikingPower = strikingPower;
  }

  /**
   * Returns the striking power of the player.
   * @return striking power
   */
  @Override public int getStrikingPower() {
    return this.strikingPower;
  }

  /**
   * Sets the avoidance Ability of the player.
   * @param avoidanceAbility avoidance Ability
   */
  @Override public void setAvoidanceAbility(int avoidanceAbility) {
    this.avoidanceAbility = avoidanceAbility;
  }

  /**
   * Returns the avoidance Ability of the player.
   * @return avoidance Ability
   */
  @Override public int getAvoidanceAbility() {
    return this.avoidanceAbility;
  }

  /**
   * Sets the health of the player.
   * @param health health
   */
  @Override public void setHealth(double health) {
    this.health = health;
  }

  /**
   * Returns the health of the player.
   * @return health
   */
  @Override public double getHealth() {
    return health;
  }

  /**
   * Adds gears to the player.
   * @param g gears
   * @return true if the gear is added
   */
  @Override public boolean addGears(Gear g) {
    if (getValidGear(g)) {
      gears.add(g);
      return true;
    }
    return false;
  }

  /**
   * Gives the gear count the player has.
   * @return gears list size
   */
  public int gearCount() {
    return gears.size();
  }

  /**
   * Gets the detailed information of the player.
   * @return string
   */
  @Override public String getPlayerFullInfo() {
    StringBuilder sb = new StringBuilder();
    sb.append(this).append("\n");
    sb.append("Gear Details: \n");
    for (Gear g : gears) {
      sb.append(g.toString()).append("\n");
    }
    if (null != weapon) {
      sb.append("****************************").append("\n");
      sb.append(weapon.toString()).append("\n");
    }

    return sb.toString();
  }

  /**
   * Sorts the gears.
   */
  @Override public void sortGears() {
    Collections.sort(gears);
  }

  /**
   * Sets the weapon for the player.
   * @param weapon weapon
   */
  @Override public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  /**
   * Returns the weapon of a player.
   * @return weapon
   */
  @Override public Weapon getWeapon() {
    return this.weapon;
  }

  /**
   * Sets the number of weapons for a player.
   * @param numberOfWeapons number of players
   */
  @Override public void setNumberOfWeapons(int numberOfWeapons) {
    this.numberOfWeapon = numberOfWeapons;
  }

  /**
   * Returns the number of weapons for a player.
   * @return number of players
   */
  @Override public int getNumberOfWeapons() {
    return this.numberOfWeapon;
  }

  /**
   * Remove gears to the player.
   * @param g gears
   * @return true if the gear is removed
   */
  @Override public boolean removeGears(Gear g) {
    if (gears.contains(g)) {
      gears.remove(g);
      return true;
    }
    return false;
  }

  /**
   * Returns the damage done by a weapon.
   * @return double
   */
  @Override public double getDamageByWeapon(Random random) {
    weapon = getWeapon();
    double inflictedDamage = 0;
    switch (weapon.getWeaponName()) {
      case FLAILS -> {
        inflictedDamage = BattleHandler.helperInit(8, 4, random);
        if (this.getDexterity() < 14) {
          this.mulFactor = 0.5;
        } else {
          this.mulFactor = 1;
        }
      }
      case AXES -> {
        inflictedDamage = BattleHandler.helperInit(6, 4, random);
        this.mulFactor = 1;
      }
      case KATANAS -> {
        this.mulFactor = 1;
        inflictedDamage = BattleHandler.helperInit(4, 2, random);
      }
      case TWOHANDEDSWORDS -> {
        inflictedDamage = BattleHandler.helperInit(8, 4, random);
        if (this.getStrength() < 14) {
          this.mulFactor = 0.5;
        } else {
          this.mulFactor = 1;
        }
      }
      case BROADSWORDS -> {
        this.mulFactor = 1;
        inflictedDamage = BattleHandler.helperInit(6, 4, random);
      }
      default -> {
      }
    }
    return mulFactor * getNumberOfWeapons() * inflictedDamage;
  }

  /**
   * Generates the Striking Power of a weapon.
   * @param random random
   * @return Striking Power
   */
  public int generateStrikingPower(Random random) {
    int value = 0;
    for (Gear g : gears) {
      value += g.getNewStrength();
    }
    return this.strength + value + BattleHandler.helperInit(1, 10, random);
  }

  /**
   * Generates the Avoidance Power of a weapon.
   * @param random random
   * @return Avoidance Power
   */
  public int generateAvoidancePower(Random random) {
    int value = 0;
    for (Gear g : gears) {
      value += g.getNewDexterity();
    }
    return this.dexterity + value + BattleHandler.helperInit(1, 6, random);
  }

  /**
   * Adjust the abilities of a player.
   */
  public void adjustAbility() {
    int strengthValue = 0;
    int constitutionValue = 0;
    int dexterityValue = 0;
    int charismaValue = 0;
    for (Gear g : gears) {
      strengthValue += g.getNewStrength();
      constitutionValue += g.getNewConstitution();
      dexterityValue += g.getNewDexterity();
      charismaValue += g.getNewCharisma();
    }
    this.strength += strengthValue;
    this.constitution += constitutionValue;
    this.dexterity += dexterityValue;
    this.charisma += charismaValue;
  }

  //private methods

  private int getAbility(Random random) {
    int ability = 0;
    int count = 0;
    while (count != 4) {
      int val = BattleHandler.helperInit(1, 6, random);
      if (val != 1) {
        ability += val;
        count++;
      }
    }
    return ability;
  }

  private boolean getValidGear(Gear g) {
    if (g.getType().equals(GearType.HEADGEAR)) {
      return !this.gears.contains(g);
    } else if (g.getType().equals(GearType.FOOTWEAR)) {
      if (this.gears.contains(g)) {
        return false;
      }
    } else if (g.getType().equals(GearType.BELTS)) {
      int beltLimit = checkBeltUnitLimit(beltUnitsLimit, g);
      if (beltLimit > 10) {
        return false;
      }
      beltUnitsLimit = beltLimit;
    }
    return true;
  }

  private int checkBeltUnitLimit(int beltLimit, Gear g) {
    if (g.getType() == GearType.BELTS) {
      if (g.getBeltSize() == BeltSize.SMALL) {
        beltLimit += 1;
      }
      if (g.getBeltSize() == BeltSize.MEDIUM) {
        beltLimit += 2;
      }
      if (g.getBeltSize() == BeltSize.LARGE) {
        beltLimit += 4;
      }
    }
    return beltLimit;
  }
}