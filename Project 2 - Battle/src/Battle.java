/**
 * This class defines the Battle and methods required for a battle between players.
 */
public class Battle implements Battles {

  private int numberOfPlayers;
  private final BattleHandler battleHandler;

  /**
   * This is the constructor of the Battle class that initializes the parameters required.
   * @param numberOfPlayers - number of players
   */
  public Battle(int numberOfPlayers) {
    if (numberOfPlayers != 2) {
      throw new IllegalArgumentException("The number of players should be 2!");
    } else {
      this.numberOfPlayers = numberOfPlayers;
      battleHandler = new BattleHandler(numberOfPlayers);
    }
  }

  @Override public String getPlayerInfo() {
    return battleHandler.getPlayerInfo();
  }

  @Override public String getGearInfo() {
    return battleHandler.getGearInfo();
  }

  @Override public boolean assignGearsToPlayers() {
    battleHandler.assignGearsToPlayers();
    return true;
  }

  @Override public String getPlayerFullInfo() {
    return battleHandler.getPlayerFullInfo();
  }

  @Override public boolean createArmory() {
    battleHandler.createArmory();
    return true;
  }

  @Override public boolean pickWeaponForPlayer(int id) {
    battleHandler.pickWeaponForPlayer(id);
    return true;
  }

  @Override public int getFirstStrikingPlayer() {
    return battleHandler.getFirstStrikingPlayer();
  }

  @Override public String play() {
    return battleHandler.play(100);
  }

  public Battle resetNewBattle(int numberOfPlayers) {
    return new Battle(numberOfPlayers);
  }
}
