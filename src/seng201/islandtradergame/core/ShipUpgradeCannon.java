package seng201.islandtradergame.core;

public class ShipUpgradeCannon extends Item {

	public ShipUpgradeCannon(int value) {
		super("Cannon", 7, value);
	}
	
	@Override
	public int getDefence() {
		return 20;
	}

}
