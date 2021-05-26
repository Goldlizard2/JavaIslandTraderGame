package seng201.islandtradergame.core;

import java.util.Random;

/**
 * This class implements an Item which can be bought and sold at the other islands in exchange
 * for money. The items take up space on the ship so the trader must make sure not to buy to much at once.
 *
 * @author Kei Carden
 */
public class Item {
	private Random rand = new Random();
	/**
	 * The name of the item
	 */
	private String itemName, 
	/**
	 * The name of the island that the item was sold at
	 */
	asosatedIslandName;
	/**
	 * The size of the item
	 */
	private int itemSize;
	/**
	 * The sell/buy value of the item
	 */
	private float value;
	
	/**
	 * Creates a Item with just a name, this is the items stored in the traders cargo.
	 * 
	 * @param name
	 */
	public Item(String name) {
		itemName = name;
	}
	
	/**
	 * Creates a Item with a specific name, size and value.
	 * 
	 * @param name The name of the item
	 * @param size The size of the item
	 * @param value The size of the item
	 */
	public Item(String name, int size, float value) {
		itemName = name;	
		itemSize = size;
		this.value = value;
	}
	
	/**
	 * Compare the name of this object to the name of another object if they have the same name they are the same object.
	 * 
	 * @param obj The object being compared to this item object
	 * @return boolean Whether the two objects have the same name or not
	 */
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
	 * If the item has not value e.g. 0 then only the name of the item is returned.
	 * Otherwise the name, value and size are returned in a formated string.
	 * 
	 * @return String The info about the item
	 */
	public String toString() {
		if(value == 0) {
			return itemName;
		} else {
			return String.format("£ %.2f %s size %d",value ,itemName , itemSize);
		}
		
	}
	
	/**
	 * Constructs a String of toString and where the item was sold. 
	 * 
	 * @return String All info about the item
	 */
	public String itemOrigin() {
		return toString() + " was sold " + asosatedIslandName;
	}
	
	/**
	 * Sets the asosatedIslandName to the parsed in islands name.
	 * 
	 * @param island The island the item was sold at
	 */
	public void itemSoldIslandName(Island island) {
		asosatedIslandName = island.toString();
	}
	
	/**
	 * Returns the name of the item.
	 * 
	 * @return itemName this is a string of the items name
	 */
	public String getName() {
		return itemName;
	}
	
	/**
	 * Returns the size of the item.
	 * 
	 * @return itemSize this is an integer of the items size
	 */
	public int getItemSize() {
		return itemSize;
	}
	
	/**
	 * Returns the value of the item.
	 * 
	 * @return double The item value
	 */
	public double getValue() {
		return value;
	}
	
	public double getRandomNumber(double min, double max) {
	    return rand.nextFloat() * (max - min) + min;
	}
	
	/**
	 * Creates an exact copy of the item with a changed value.
	 * The new value is made by multiplying a random number between 1.6 and 2.5 wit the old value.
	 * 
	 * @return Item The same item with a different value
	 */
	public Item cloneBuyableItem() {
		Item toReturn = new Item(itemName, itemSize, value);
		toReturn.value *= getRandomNumber(1.6, 2.5);
		return toReturn;
	}
	
	/**
	 * Returns the defence of the item.
	 * 
	 * @return int The defence value of the item
	 */
	public int getDefence() {
		return 0;
	}
}
