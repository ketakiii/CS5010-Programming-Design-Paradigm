import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This class BattleTest tests the methods in the Battle class.
 */
public class BattleTest {

  Battle battle = new Battle(2);

  @Test public void testBattle() {
    assertThrows(IllegalArgumentException.class, () -> new Battle(3));
  }

  @Test public void testGetPlayerInfo() {
    assertTrue(battle.getPlayerInfo().length() != 0);
  }

  @Test public void testGetGearInfo() {
    assertTrue(battle.getGearInfo().length() != 0);
  }

  @Test public void testAssignGearsToPlayers() {
    assertTrue(battle.assignGearsToPlayers());
  }

  @Test public void testGetPlayerFullInfo() {
    assertTrue(battle.getPlayerInfo().length() != 0);
  }

  @Test public void testCreateArmory() {
    assertTrue(battle.createArmory());
  }

  @Test public void testPickWeaponForPlayerpickWeaponForPlayer() {
    battle.createArmory();
    assertTrue(battle.pickWeaponForPlayer(0));
    assertTrue(battle.pickWeaponForPlayer(1));
  }

  @Test public void testgetFirstStrikingPlayer() {
    assertEquals(2, battle.getFirstStrikingPlayer());
  }

  @Test public void testPlay() {
    assertTrue(battle.play().length() != 0);
  }

  @Test public void testResetNewBattle() {
    assertTrue(battle.resetNewBattle(2) != null);
  }
}