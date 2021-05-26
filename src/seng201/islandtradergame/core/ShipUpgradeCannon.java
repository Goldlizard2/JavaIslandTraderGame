package seng201.islandtradergame.core;

/**
 * This class implements a Item which is a cannon, the item allows the trader to have a higher chance of wining a battle against the pirates.
 *
 * @author Kei Carden
 */
public class ShipUpgradeCannon extends Item {

	/**
	 * Creates a special item (Cannon) with a cost of value.
	 * 
	 * @param value The cost of the cannon
	 */
	public ShipUpgradeCannon(int value) {
		super("Cannon", 7, value);
	}
	
	/**
	 * Returns a large defence value
	 * 
	 * @return 20
	 */
	@Override
	public int getDefence() {
		return 20;
	}

}
