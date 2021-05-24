package seng201.islandtradergame.core;

import java.util.Random;

/**
 * 
 * @author EpicPC
 *
 */
public class Item {
	private Random rand = new Random();
	private String itemName, asosatedIslandName;
	private int itemSize;
	private float value;
	
	/**
	 * 
	 * @param name
	 */
	public Item(String name) {
		itemName = name;
	}
	
	/**
	 * 
	 * @param name
	 * @param size
	 * @param value
	 */
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
	 * Constructs a String of toString and where the item was sold 
	 * 
	 * @return String   
	 */
	public String itemOrigin() {
		return toString() + " was sold " + asosatedIslandName;
	}
	
	/**
	 * 
	 * @param island
	 */
	public void itemSoldIslandName(Island island) {
		asosatedIslandName = island.toString();
	}
	
	/**
	 * 
	 * @return itemName this is a string of the items name
	 */
	public String getName() {
		return itemName;
	}
	
	/**
	 * 
	 * @return itemSize this is an integer of the items size
	 */
	public int getItemSize() {
		return itemSize;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getValue() {
		return value;
	}
	
	public double getRandomNumber(double min, double max) {
	    return rand.nextFloat() * (max - min) + min;
	}
	
	/**
	 * 
	 * @return
	 */
	public Item cloneBuyableItem() {
		Item toReturn = new Item(itemName, itemSize, value);
		toReturn.value *= getRandomNumber(1.6, 2.5);
		return toReturn;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDefence() {
		return 0;
	}
}
