/**
 * This is an interface of the Gear which define the methods required by gears.
 */
public interface Gear extends Comparable<Gear> {

  public String getName();

  public GearType getType();

  public int getNewStrength();

  public int getNewConstitution();

  public int getNewDexterity();

  public int getNewCharisma();

  public String toString();

  public void setAttribute();

  public BeltSize getBeltSize();
}
