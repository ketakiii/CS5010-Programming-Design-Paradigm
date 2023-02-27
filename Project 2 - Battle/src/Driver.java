import java.util.Scanner;

/**
 * The main class.
 */
public class Driver {

  /**
   * The main method for testing.
   * @param args String[]
   */
  public static void main(String[] args) {
    System.out.println("Welcome to the Battle!\n");

    Battle battle = new Battle(2);

    System.out.println("Player Info:\n");
    System.out.println(battle.getPlayerInfo());
    //System.out.println(battle.getGearInfo());

    System.out.println("\nEquipping the players with Gears:\n");
    battle.assignGearsToPlayers();
    System.out.println(battle.getPlayerInfo());

    System.out.println("Creating an armoury.\n");
    battle.createArmory();
    System.out.println("Picking Weapons for each Player.\n");
    battle.pickWeaponForPlayer(0);
    battle.pickWeaponForPlayer(1);
    System.out.println("\n" + battle.getPlayerFullInfo());

    System.out.println("Player " + battle.getFirstStrikingPlayer() + " gets the first strike!");

    System.out.println(battle.play());

    System.out.println("Do you want to reset the battle?" + " Press R or r to continue: ");
    Scanner object = new Scanner(System.in);
    String reset = object.nextLine();
    if ("R".equals(reset) || "r".equals(reset)) {
      System.out.println("\nThe Battle has been reset!");
      Battle battle2 = battle.resetNewBattle(2);
    }
  }
}
