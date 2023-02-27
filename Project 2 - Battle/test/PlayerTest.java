import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import org.junit.Test;

/**
 * This class PlayerTest tests the methods in the Player class.
 */
public class PlayerTest {
  Random random = new Random(1);
  Player player = new Player("Player1", random);
  Random random1;

  @Test public void testPlayer() {
    assertThrows(IllegalArgumentException.class, () -> new Player(null, random));
    assertThrows(IllegalArgumentException.class, () -> new Player("Player1", random1));
  }

  @Test public void testGetName() {
    player.setName("Player1");
    assertEquals("Player1", player.getName());
  }

  @Test public void testGetStrength() {
    assertEquals(14, player.getStrength());
    player.setStrength(22);
    assertEquals(22, player.getStrength());
  }

  @Test public void testBoundary() {
    assertTrue(player.getStrength() >= 8);
    assertTrue(player.getStrength() <= 24);

    assertTrue(player.getConstitution() >= 8);
    assertTrue(player.getConstitution() <= 24);

    assertTrue(player.getCharisma() >= 8);
    assertTrue(player.getCharisma() <= 24);

    assertTrue(player.getDexterity() >= 8);
    assertTrue(player.getDexterity() <= 24);

    assertTrue(player.getCharisma() >= 8);
    assertTrue(player.getCharisma() <= 24);
  }

  @Test public void testGetConstitution() {
    assertEquals(15, player.getConstitution());
    player.setConstitution(30);
    assertEquals(30, player.getConstitution());
  }

  @Test public void testGetDexterity() {
    assertEquals(16, player.getDexterity());
    player.setDexterity(1);
    assertEquals(1, player.getDexterity());
  }

  @Test public void testGetCharisma() {
    assertEquals(14, player.getCharisma());
    player.setCharisma(2);
    assertEquals(2, player.getCharisma());
  }

  @Test public void testRemoveGears() {
    Player player1 = new Player("Player1", random);
    Gear g1 = new HeadGear("headgear", 10);
    assertEquals(25, player1.generateStrikingPower(random));
    player1.addGears(g1);
    assertEquals(16, player1.generateStrikingPower(random));
    player1.removeGears(g1);
    assertEquals(21, player1.generateStrikingPower(random));
  }

  @Test public void testToString() {
    System.out.println(player.toString());
  }

  @Test public void testGetStrikingPower() {
    assertEquals(0, player.getStrikingPower());
    player.setStrikingPower(20);
    assertEquals(20, player.getStrikingPower());
  }

  @Test public void testGetAvoidanceAbility() {
    assertEquals(0, player.getAvoidanceAbility());
    player.setAvoidanceAbility(20);
    assertEquals(20, player.getAvoidanceAbility());
  }

  @Test public void testGetHealth() {
    Player player1 = new Player("Player1", random);
    assertEquals(72.0, player1.getHealth(), 0.0);
  }

  @Test public void testAddGears() {
    Player player1 = new Player("Player1", random);
    Gear g1 = new HeadGear("headgear", 10);
    player1.addGears(g1);
    assertEquals(1, player1.gearCount());
    Gear g2 = new HeadGear("headgear", 10);
    player1.addGears(g2);
    Gear g3 = new HeadGear("headgear", 10);
    player1.addGears(g3);
    assertEquals(3, player1.gearCount());
  }

  @Test public void testGetWeapon() {
    assertEquals(null, player.getWeapon());
    Weapon w1 = new Flails(Weapons.FLAILS);
    player.setWeapon(w1);


  }

  @Test public void testGetNumberOfWeapons() {
    Player player1 = new Player("Player", random);
    assertEquals(1, player1.getNumberOfWeapons());
    //player.setWeapon();
  }

  @Test public void testGetDamageByWeapon() {
    Random random = new Random(1);
    Player p = new Player("Player p", random);

    p.setDexterity(20);
    Weapon w1 = new Flails(Weapons.FLAILS);
    p.setWeapon(w1);
    assertEquals(11.0, p.getDamageByWeapon(random), 0.0);
    p.setDexterity(10);
    assertEquals(5.5, p.getDamageByWeapon(random), 0.0);

    Weapon w2 = new Axes(Weapons.AXES);
    p.setWeapon(w2);
    assertEquals(7, p.getDamageByWeapon(random), 0.0);

    Weapon w3 = new Katanas(Weapons.KATANAS);
    p.setWeapon(w3);
    assertEquals(4.0, p.getDamageByWeapon(random), 0.0);

    Weapon w4 = new TwoHandedSwords(Weapons.TWOHANDEDSWORDS);
    p.setStrength(20);
    p.setWeapon(w4);
    p.getDamageByWeapon(random);
    assertEquals(9.0, p.getDamageByWeapon(random), 0.0);
    p.setStrength(10);
    p.getDamageByWeapon(random);
    assertEquals(4.5, p.getDamageByWeapon(random), 0.0);

    Weapon w5 = new Broadswords(Weapons.BROADSWORDS);
    p.setWeapon(w5);
    assertEquals(7.0, p.getDamageByWeapon(random), 0.0);

  }

  @Test public void testGenerateStrikingPower() {
    player.setStrength(10);
    assertEquals(13, player.generateStrikingPower(random));
  }

  @Test public void testGetValidGear() {
    Player player1 = new Player("Player1", random);
    Gear g1 = new Belts("Medium Belt", BeltSize.MEDIUM, 10, 10);
    assertEquals(true, player1.addGears(g1));
    Gear g2 = new Belts("Medium Belt", BeltSize.MEDIUM, 10, 10);
    assertEquals(true, player1.addGears(g2));
    Gear g3 = new Belts("Large Belt", BeltSize.LARGE, 10, 10);
    assertEquals(true, player1.addGears(g3));
    Gear g4 = new Belts("Large Belt", BeltSize.LARGE, 10, 10);
    assertEquals(false, player1.addGears(g4));

    Player player2 = new Player("Player1", random);
    Gear t1 = new Belts("Large Belt", BeltSize.LARGE, 10, 10);
    player2.addGears(t1);
    Gear t2 = new Belts("Large Belt", BeltSize.LARGE, 10, 10);
    player2.addGears(t2);
    Gear t3 = new Belts("Medium Belt", BeltSize.MEDIUM, 10, 10);
    assertEquals(true, player2.addGears(t3));
  }

  @Test public void testGetValidFootWear() {
    Player player = new Player("Player 1", random);
    Gear g1 = new FootWear("Footwear 1", 10);
    assertEquals(true, player.addGears(g1));
    Gear g2 = new FootWear("Footwear 2", 10);
    player.addGears(g2);
    assertEquals(false, player.addGears(g2));
  }

  @Test public void testGetValidHeadGear() {
    Player player = new Player("Player 1", random);
    Gear g1 = new HeadGear("Footwear 1", 10);
    assertEquals(true, player.addGears(g1));
    Gear g2 = new HeadGear("Footwear 2", 10);
    player.addGears(g2);
    assertEquals(false, player.addGears(g2));
  }


}