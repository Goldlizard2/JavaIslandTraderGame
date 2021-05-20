package seng201.islandtradergame.core;

import java.util.ArrayList;

public class Item {
	private String itemName;
	private int itemSize;
	private String asosatedIslandName;
	private int[] upgradeProperties;
	private int value;
	
	public Item(String name) {
		itemName = name;
	}
	
	public Item(String name, int size, int value) {
		itemName = name;
		itemSize = size;
		this.value = value;
	}
	
	/**
	 * Constructs a string of the items value name and size
	 * 
	 * @return String   
	 */
	public String toString() {
		if(value == 0) {
			return itemName;
		} else {
			return "£" + value + " " + itemName + " Size " + itemSize;
		}
		
	}
	
	/**
	 * Constructs a String of the {@value toString()} and where the item was sold 
	 * 
	 * @return String   
	 */
	public String itemOrigin() {
		return toString() + " was sold " + asosatedIslandName;
	}
	
	
	public void itemSoldIslandName(Island island) {
		asosatedIslandName = island.toString();
	}
	
	public String getName() {
		return itemName;
	}
	
	public int getItemSize() {
		return itemSize;
	}
	
	public int[] getUpgradeProperties() {
		return upgradeProperties;
	}
	
	public int getValue() {
		return value;
	}

}
