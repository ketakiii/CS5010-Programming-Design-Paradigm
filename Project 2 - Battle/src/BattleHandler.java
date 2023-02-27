import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class BattleHandler contains methods that help run the Battle class.
 */
public class BattleHandler {
  public static final int SEED = 1;
  protected Player[] playersList;
  protected List<Gear> gearBag;
  protected List<Weapons> armory;
  private boolean isBattleOver;
  private boolean gameState;
  private Random random;

  /**
   * This is the constructor of the BattleHandler class.
   * @param numberOfPlayers - number of players
   */
  public BattleHandler(int numberOfPlayers) {
    if (numberOfPlayers != 2) {
      throw new IllegalArgumentException("Number of Players is not given as 2!");
    } else {
      setArena(numberOfPlayers);
    }
  }

  /**
   * This is a helper method that helps us generate a random number for making the no of gears.
   * @param constant - constant
   * @param upperbound - upperbound of the random number to be generated
   * @param random - random variable
   * @return a random number
   */
  public static int helperInit(int constant, int upperbound, Random random) {
    if (random == null) {
      throw new IllegalArgumentException("Initialize Random!");
    } else {
      int intRandom = random.nextInt(upperbound);
      return constant + intRandom;
    }
  }

  /**
   * Returns the player's basic information.
   * @return string format information
   */
  public String getPlayerInfo() {
    StringBuilder info = new StringBuilder();
    for (Player player : this.playersList) {
      info.append(String.format("%s", player.toString() + "\n"));
      info.append("-------------------------------------------\n");
    }
    return info.toString();
  }

  /**
   * Returns the gear's information from the gear bag.
   * @return gear info
   */
  public String getGearInfo() {
    StringBuilder info = new StringBuilder();
    for (Gear g : gearBag) {
      info.append(String.format("%s", g.toString() + "\n"));
      info.append("-------------------------------------------\n");
    }
    return info.toString();
  }

  /**
   * This method helps us remove gears if required from an equipped player.
   * @param g gear
   * @param p player
   */
  public void removeGearForPlayer(Gear g, Player p) {
    if (p.removeGears(g)) {
      this.gearBag.add(g);
    }
  }

  /**
   * This method assigns the gears to a player randomly.
   */
  public void assignGearsToPlayers() {
    for (Player e : playersList) {
      int count = 0;
      int failCount = 0;
      while (count != 20 && failCount != 3) {
        int value = helperInit(0, gearBag.size(), random);
        if (e.addGears(gearBag.get(value))) {
          gearBag.remove(value);
          count += 1;
        } else {
          failCount++;
        }
      }
      e.sortGears();
      e.generateStrikingPower(random);
      e.generateAvoidancePower(random);
      e.adjustAbility();
    }
  }

  /**
   * Returns the players full information.
   * @return string
   */
  public String getPlayerFullInfo() {
    StringBuilder sb = new StringBuilder();
    for (Player e : this.playersList) {
      sb.append(e.getPlayerFullInfo()).append("\n");
      sb.append("-------------------------------------------\n");
    }
    return sb.toString();
  }

  /**
   * This method is for creating the armory of weapons.
   */
  public void createArmory() {
    armory = new ArrayList<>();
    armory.add(Weapons.FLAILS);
    armory.add(Weapons.AXES);
    armory.add(Weapons.KATANAS);
    armory.add(Weapons.TWOHANDEDSWORDS);
    armory.add(Weapons.BROADSWORDS);
    int value = helperInit(1, 20, random);
    while (value != 0) {
      int index = helperInit(1, 5, random);
      armory.add(armory.get(index - 1));
      value -= 1;
    }
    Collections.shuffle(armory);
  }

  /**
   * This method is for picking weapons for a player.
   * @param id - id of the player
   */
  public void pickWeaponForPlayer(int id) {
    Weapon weapon = null;
    int index = helperInit(1, armory.size(), random);
    Player player = playersList[id];
    switch (armory.get(index - 1)) {
      case FLAILS -> weapon = new Flails(Weapons.FLAILS); //+ index
      case AXES  -> weapon = new Axes(Weapons.AXES);
      case KATANAS -> weapon = new Katanas(Weapons.KATANAS);
      case TWOHANDEDSWORDS -> weapon = new TwoHandedSwords(Weapons.TWOHANDEDSWORDS);
      case BROADSWORDS -> weapon = new Broadswords(Weapons.BROADSWORDS);
      default -> {

      }
    }
    if (weapon.getWeaponName().equals(Weapons.KATANAS)) {
      int randomNumber = helperInit(1, 100, random);
      if (randomNumber > 50) {
        player.setNumberOfWeapons(2);
      }
    }
    player.setWeapon(weapon);
    armory.remove(index - 1);
  }

  /**
   * This method defines the player who would be going for the first strike.
   * @return id of the player playing first
   */
  public int getFirstStrikingPlayer() {
    Player p1 = playersList[0];
    Player p2 = playersList[1];
    if (p1.getCharisma() >= p2.getCharisma()) {
      return 1;
    } else {
      return 2;
    }
  }

  /**
   * Calculates the Potential Striking Damage.
   * @param player player
   * @return Potential Striking Damage
   */
  public double getPotentialStrikingDamage(Player player) {
    double damage = 0;
    if (null != armory && armory.size() != 0) {
      damage = player.getDamageByWeapon(random);
    }
    return player.getStrength() + damage;
  }

  /**
   * Calculates the Actual Damage.
   * @param player player 1
   * @param opponent player 2
   * @return Actual Damage
   */
  public double getActualDamage(Player player, Player opponent) {
    return this.getPotentialStrikingDamage(player) - opponent.getConstitution();
  }

  /**
   * Calculates the Updated Health.
   * @param player player
   * @param opponent opponent player
   * @return Updated Health
   */
  public double getUpdatedHealth(Player player, Player opponent) {
    double opponent2Health = opponent.getHealth();
    return opponent2Health - this.getActualDamage(player, opponent);
  }

  /**
   * The battle action.
   * @param player - player 1
   * @param opponent - player 2
   * @return string
   */
  public String battleAction(Player player, Player opponent) {
    StringBuilder sb = new StringBuilder();
    sb.append("\n \nBattle round: Striking player: ").append(player.getName())
        .append(", Opponent player: ").append(opponent.getName()).append("\n");
    int opponent1AttackingPower = player.generateStrikingPower(random);
    int opponent2DefencePower = opponent.generateAvoidancePower(random);
    double actualDamage;
    if (opponent1AttackingPower > opponent2DefencePower) {
      sb.append("Striking player is striking!").append("\n");
      Weapon opponent1Weapon = player.getWeapon();
      actualDamage = this.getActualDamage(player, opponent);
      if (actualDamage > 0) {
        sb.append("Opponent incurred damage: ").append(actualDamage).append("\n");
        opponent.setHealth(this.getUpdatedHealth(player, opponent));
      } else {
        sb.append("Opponent defended successfully!").append("\n");
      }
    } else {
      sb.append("Opponent player has a higher defence!").append("\n");
    }
    if (opponent.getHealth() <= 0) {
      sb.append("The Battle is Over! The winner is ").append(player.getName()).append("\n");
      isBattleOver = true;
    } else {
      sb.append("Waiting for opponent's move.").append("\n");
    }
    return sb.toString();
  }

  /**
   * The battle.
   * @param numRound number of rounds to be played
   * @return string value of the battle
   */
  public String play(int numRound) {
    StringBuilder sb = new StringBuilder();
    int index = getFirstStrikingPlayer() - 1;
    Player strikingPlayer;
    Player opponentPlayer;
    for (int i = 0; (i < numRound && !isBattleOver); i++) {
      if (null != armory && this.armory.size() != 0) {
        this.pickWeaponForPlayer(index);    // for the striking player
      }
      if (null != armory && this.armory.size() != 0) {
        this.pickWeaponForPlayer((index + 1) % 2);
      }
      strikingPlayer = this.playersList[index];
      opponentPlayer = this.playersList[(index + 1) % 2];
      sb.append(this.battleAction(strikingPlayer, opponentPlayer));
      if (!isBattleOver) {
        index = (index + 1) % 2;
      }
    }
    return sb.toString();
  }

  /**
   * sets a random value with a seed.
   * @param seed seed
   */
  public void setRandom(int seed) {
    random = new Random(seed);
  }

  // private helper methods

  private void setArena(int numberOfPlayers) {
    gameState = false;
    random = new Random(SEED);
    initPlayers(numberOfPlayers, random);
    gearBag = initGears(random);
    for (Gear g : gearBag) {
      int value = helperInit(0, gearBag.size(), random);
      if (value < gearBag.size()  / 4) {
        g.setAttribute();
      }
    }
    Collections.sort(gearBag);
  }

  private void initPlayers(int numberOfPlayers, Random random) {
    playersList = new Player[numberOfPlayers];
    for (int i = 0; i < playersList.length; i++) {
      Player p = new Player("Player " + i, random);
      playersList[i] = p;
    }
  }

  private List<Gear> initGears(Random random) {
    List<Gear> gearList = new ArrayList<>();
    gearList.addAll(initHeadGear(random));
    gearList.addAll(initFootwear(random));
    gearList.addAll(initPotions(random));
    gearList.addAll(initBelts(random));
    return gearList;
  }

  /**
   * This method gives us the headgear list.
   * @return headgear list
   */
  private List<Gear> initHeadGear(Random random) {
    int noOfHeadGears = helperInit(5, 2, random);
    List<Gear> headGearList = new ArrayList<Gear>();
    for (int i = 0; i < noOfHeadGears; i++) {
      HeadGear headGear = new HeadGear("Headgear " + i, 10);
      headGearList.add(headGear);
    }
    return headGearList;
  }

  private List<Gear> initFootwear(Random random) {
    int noOfFootWear = helperInit(5, 2, random);
    List<Gear> footWearList = new ArrayList<Gear>();
    for (int i = 0; i < noOfFootWear; i++) {
      FootWear footWear = new FootWear("Footwear " + i, 10);
      footWearList.add(footWear);
    }
    return footWearList;
  }

  private List<Gear> initPotions(Random random) {
    int noOfPotion = helperInit(15, 5, random);
    List<Gear> potionList = new ArrayList<Gear>();
    for (int i = 0; i <= noOfPotion; i++) {
      Potions potions = new Potions("Potions " + i, 10, random);
      potionList.add(potions);
    }
    return potionList;
  }

  private List<Gear> initBelts(Random random) {
    List<Gear> beltList = new ArrayList<Gear>();
    List<Gear> smallBelt = initSmallBelts(random);
    List<Gear> mediumBelt = initMediumBelts(random);
    List<Gear> largeBelt = initLargeBelts(random);
    beltList.addAll(smallBelt);
    beltList.addAll(mediumBelt);
    beltList.addAll(largeBelt);
    return beltList;
  }

  private List<Gear> initSmallBelts(Random random) {
    int noOfBelts = helperInit(5, 5, random);
    List<Gear> beltList = new ArrayList<Gear>();
    for (int i = 0; i <= noOfBelts; i++) {
      Belts belts = new Belts("Small Belt " + i, BeltSize.SMALL, 10, 20);
      beltList.add(belts);
    }
    return beltList;
  }

  private List<Gear> initMediumBelts(Random random) {
    int noOfBelts = helperInit(5, 5, random);
    List<Gear> beltList = new ArrayList<Gear>();
    for (int i = 0; i <= noOfBelts; i++) {
      Belts belts = new Belts("Medium Belt " + i, BeltSize.MEDIUM, 15, 25);
      beltList.add(belts);
    }
    return beltList;
  }

  private List<Gear> initLargeBelts(Random random) {
    int noOfBelts = helperInit(5, 5, random);
    List<Gear> beltList = new ArrayList<Gear>();
    for (int i = 0; i <= noOfBelts; i++) {
      Belts belts = new Belts("Large Belt " + i, BeltSize.LARGE, 20, 30);
      beltList.add(belts);
    }
    return beltList;
  }

}

