/**
 * The interface for defining the Battles.
 */
public interface Battles {

  public String getPlayerInfo();

  public String getGearInfo();

  public boolean assignGearsToPlayers();

  public String getPlayerFullInfo();

  public boolean createArmory();

  public boolean pickWeaponForPlayer(int id);

  public int getFirstStrikingPlayer();

  public String play();
}
