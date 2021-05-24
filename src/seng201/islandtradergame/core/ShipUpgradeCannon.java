package seng201.islandtradergame.core;

/**
 * 
 * @author EpicPC
 *
 */
public class ShipUpgradeCannon extends Item {

	/**
	 * Creates a spechial item (Cannon) with a cost of value.
	 * 
	 * @param value The cost of the cannon
	 */
	public ShipUpgradeCannon(int value) {
		super("Cannon", 7, value);
	}
	
	@Override
	public int getDefence() {
		return 20;
	}

}
