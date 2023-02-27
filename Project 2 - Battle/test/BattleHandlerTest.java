import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;

/**
 * This class BattleHandlerTest tests the methods in the BattleHandler class.
 */
public class BattleHandlerTest {

  BattleHandler battleHandler = new BattleHandler(2);

  @Test public void testBattleHandler() {
    assertThrows(IllegalArgumentException.class, () -> new BattleHandler(1));
    assertThrows(IllegalArgumentException.class, () -> new BattleHandler(3));
    assertThrows(IllegalArgumentException.class, () -> new BattleHandler(0));
  }

  @Test public void testHelperInit() {
    Random random = new Random(BattleHandler.SEED);
    assertEquals(6, BattleHandler.helperInit(1, 10, random));
  }

  @Test public void testGetPlayerInfo() {
    StringBuilder sb = new StringBuilder();
    sb.append("Name: Player 0, Strength: 14, Constitution: 15, Dexterity: 16,Charisma: 14 ")
        .append("\n").append("-------------------------------------------").append("\n");
    sb.append("Name: Player 1, Strength: 15, Constitution: 17, Dexterity: 20,Charisma: 20 ")
        .append("\n").append("-------------------------------------------").append("\n");
    assertEquals(sb.toString(), battleHandler.getPlayerInfo());
  }

  @Test public void testGetGear() {
    BattleHandler bh = new BattleHandler(2);
    assertTrue(0 != bh.getGearInfo().length());
  }

  @Test public void testAssignGearsToPlayers() {
    for (int i = 0; i < battleHandler.playersList.length; i++) {
      for (Gear g : battleHandler.gearBag) {
        System.out.println(i + " " + battleHandler.playersList[i]);
      }
    }
    battleHandler.assignGearsToPlayers();
    for (int i = 0; i < battleHandler.playersList.length; i++) {
      System.out.println(battleHandler.playersList[i]);
    }
  }

  @Test public void testGetPlayerFullInfo() {
    System.out.println(battleHandler.getPlayerFullInfo());
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < battleHandler.playersList.length; i++) {
      sb.append("Name: " + "Player " + i + "," + " Strength: "
              + battleHandler.playersList[i].getStrength() + ","
              + " Constitution: " + battleHandler.playersList[i].getConstitution()
              + "," + " Dexterity: " + battleHandler.playersList[i].getDexterity()
              + ",Charisma: " + battleHandler.playersList[i].getCharisma() + " \n"
              + "Gear Details: \n");
      sb.append("\n-------------------------------------------\n");
    }
    assertEquals(sb.toString(), battleHandler.getPlayerFullInfo());
  }

  @Test public void testCreateArmory() {
    assertEquals(null, battleHandler.armory);
    battleHandler.createArmory();
    assertEquals(13, battleHandler.armory.size());
  }

  @Test public void testPickWeaponForPlayer() {
    battleHandler.setRandom(BattleHandler.SEED);
    for (int i = 0; i < battleHandler.playersList.length; i++) {
      assertEquals(null, battleHandler.playersList[i].getWeapon());
    }
    battleHandler.createArmory();
    battleHandler.pickWeaponForPlayer(0);
    battleHandler.pickWeaponForPlayer(1);
    List<Weapons> listOfWeapons = new ArrayList<>();
    listOfWeapons.add(Weapons.AXES);
    listOfWeapons.add(Weapons.KATANAS);
    listOfWeapons.add(Weapons.FLAILS);
    listOfWeapons.add(Weapons.BROADSWORDS);
    listOfWeapons.add(Weapons.TWOHANDEDSWORDS);

    assertEquals(true, listOfWeapons
        .contains(battleHandler.playersList[0].getWeapon().getWeaponName()));
    assertEquals(true, listOfWeapons
        .contains(battleHandler.playersList[1].getWeapon().getWeaponName()));
  }

  @Test public void testGetFirstStrikingPlayer() {
    int player1 = battleHandler.playersList[0].getCharisma();
    int player2 = battleHandler.playersList[1].getCharisma();
    if (player1 > player2) {
      assertEquals(1, battleHandler.getFirstStrikingPlayer());
    } else if (player2 > player1) {
      assertEquals(2, battleHandler.getFirstStrikingPlayer());
    }
  }

  @Test public void testGetPotentialStrikingDamage() {
    Random random = new Random(BattleHandler.SEED);
    battleHandler.setRandom(BattleHandler.SEED);
    Player p2 = new Player("p2", random);
    battleHandler.assignGearsToPlayers();
    battleHandler.createArmory();
    battleHandler.pickWeaponForPlayer(0);
    battleHandler.pickWeaponForPlayer(1);
    Player p1 = battleHandler.playersList[0];
    assertEquals(100.5, battleHandler.getPotentialStrikingDamage(p1), 5.0);
  }

  @Test public void testgetActualDamage() {
    Random random = new Random(BattleHandler.SEED);
    battleHandler.setRandom(BattleHandler.SEED);
    Player p1 = new Player("p1", random);
    Player p2 = new Player("p2", random);
    assertEquals(-3.0, battleHandler.getActualDamage(p1, p2), 0.0);
  }

  @Test public void testGetUpdatedNegativeHealth() {
    Random random = new Random(BattleHandler.SEED);
    battleHandler.setRandom(BattleHandler.SEED);
    Player p1 = new Player("p1", random);
    Player p2 = new Player("p2", random);
    assertEquals(-3.0, battleHandler.getActualDamage(p1, p2), 0.0);
    assertEquals(72.0, p2.getHealth(), 0.0);
    battleHandler.battleAction(p1, p2);
    assertEquals(72.0, p2.getHealth(), 0.0);
  }

  @Test public void testGetUpdatedHealth() {
    Random random = new Random(BattleHandler.SEED);
    battleHandler.setRandom(123);
    battleHandler.assignGearsToPlayers();
    battleHandler.createArmory();
    battleHandler.pickWeaponForPlayer(0);
    battleHandler.pickWeaponForPlayer(1);
    Player p1 = battleHandler.playersList[0];
    Player p2 = battleHandler.playersList[1];
    assertEquals(59.0, p1.getHealth(), 0.0);
    battleHandler.battleAction(p2, p1);
    assertEquals(23.0, battleHandler.getActualDamage(p2, p1), 5.0);
    assertEquals(36.0, p1.getHealth(), 5.0);

  }

  @Test public void testBattleAction() {
    Random random = new Random(BattleHandler.SEED);
    battleHandler.setRandom(BattleHandler.SEED);
    Player player1 = new Player("Player 1", random);
    Player player2 = new Player("Player 2", random);
    StringBuilder sb = new StringBuilder();
    sb.append("\n \nBattle round: Striking player: ").append(player1.getName())
        .append(", Opponent player: ").append(player2.getName()).append("\n");
    sb.append("Opponent player has a higher defence!").append("\n");
    sb.append("Waiting for opponent's move.").append("\n");
    assertEquals(sb.toString(), battleHandler.battleAction(player1, player2));
  }

  @Test public void testPlay() {
    Random random = new Random(BattleHandler.SEED);
    Player player1 = new Player("Player 0", random);
    Player player2 = new Player("Player 1", random);
    StringBuilder sb = new StringBuilder();
    sb.append("\n \nBattle round: Striking player: ").append(player2.getName())
        .append(", Opponent player: ").append(player1.getName()).append("\n");
    sb.append("Striking player is striking!").append("\n");
    sb.append("Opponent defended successfully!").append("\n");
    sb.append("Waiting for opponent's move.").append("\n");

    sb.append("\n \nBattle round: Striking player: ").append(player1.getName())
        .append(", Opponent player: ").append(player2.getName()).append("\n");
    sb.append("Opponent player has a higher defence!").append("\n");
    sb.append("Waiting for opponent's move.").append("\n");

    //System.out.println(sb.toString());
    assertEquals(sb.toString(), battleHandler.play(2));
  }

  @Test public void testPlayWithBareHands() {
    Random random = new Random(BattleHandler.SEED);
    Player player1 = new Player("Player 0", random);
    Player player2 = new Player("Player 1", random);
    StringBuilder sb = new StringBuilder();
    sb.append("\n \nBattle round: Striking player: ").append(player2.getName())
        .append(", Opponent player: ").append(player1.getName()).append("\n");
    sb.append("Opponent player has a higher defence!").append("\n");
    sb.append("Waiting for opponent's move.").append("\n");

    sb.append("\n \nBattle round: Striking player: ").append(player1.getName())
        .append(", Opponent player: ").append(player2.getName()).append("\n");
    sb.append("Opponent player has a higher defence!").append("\n");
    sb.append("Waiting for opponent's move.").append('\n');
    battleHandler.battleAction(player1, player2);
    assertEquals(sb.toString(), battleHandler.play(2));
  }

}


