import java.util.Random;

/**
 * The interface BattlePlayer defines the methods required in the Battle Player.
 */
public interface BattlePlayer {

  /**
   * This method sets the name of the Player.
   * @param name - name of player
   */
  public void setName(String name);

  /**
   * This method gets the name of the Player.
   * @return name
   */
  public String getName();

  public void setStrength(int strength);

  public int getStrength();

  public void setConstitution(int constitution);

  public int getConstitution();

  public void setDexterity(int dexterity);

  public int getDexterity();

  public void setCharisma(int charisma);

  public int getCharisma();

  public void setStrikingPower(int strikingPower);

  public int getStrikingPower();

  public void setHealth(double health);

  public double getHealth();

  public void setAvoidanceAbility(int avoidanceAbility);

  public int getAvoidanceAbility();

  public boolean addGears(Gear g);

  public boolean removeGears(Gear g);

  public void setWeapon(Weapon weapon);

  public Weapon getWeapon();

  public void setNumberOfWeapons(int numberOfWeapons);

  public int getNumberOfWeapons();

  public double getDamageByWeapon(Random random);

  public String toString();

  public String getPlayerFullInfo();

  public void sortGears();

}
