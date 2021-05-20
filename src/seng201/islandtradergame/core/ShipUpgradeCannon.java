package seng201.islandtradergame.core;

public class ShipUpgradeCannon extends Item {

	public ShipUpgradeCannon(String name, int size, int value) {
		super(name, size, value);
	}
	
	public int defence() {
		return 20;
	}

}
