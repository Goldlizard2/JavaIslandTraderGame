package seng201.islandtradergame.core;

import java.util.Random;

public class Item {
	private Random rand = new Random();
	private String itemName, asosatedIslandName;
	private int itemSize;
	private int[] upgradeProperties;
	private float value;
	
	public Item(String name) {
		itemName = name;
	}
	
	public Item(String name, int size, float value) {
		itemName = name;	
		itemSize = size;
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj.getClass() == this.getClass())
		{
			if (((Item) obj).itemName == itemName)
			{
				return true;
			}
		}
		return false;
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
			return String.format("£ %.2f %s size %d",value ,itemName , itemSize);
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
	
	public double getValue() {
		return value;
	}
	
	public Item clone() {
		return new Item(itemName, itemSize, value);
	}
	
	public double getRandomNumber(double min, double max) {
	    return rand.nextFloat() * (max - min) + min;
	}
	
	public Item cloneBuyableItem() {
		Item toReturn = clone();
		toReturn.value *= getRandomNumber(1.6, 2.5);
		return toReturn;
	}
	
	public int getDefence() {
		return 0;
	}


}
