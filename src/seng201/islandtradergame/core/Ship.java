package seng201.islandtradergame.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Ship {
	private Random rand = new Random();
	private int shipSpeed;
	private int shipCapacity;
	private int shipCrewNum;
	private int shipEndurance;
	private int crewWage = 1;
	private String shipName;
	private String repairCost;
	private ArrayList<Item> cargo = new ArrayList<Item>();
	private ArrayList<Item> soldItems = new ArrayList<Item>();
	private int cargoValue;
	
	/**
	 * 
	 *  
	 *  @param name The name of ship they user inputed in the main menu
	 *  @param speed The speed of the ship selected from the main menu can be (1 - 4)
	 *  @param capacity The ship capacity selected can be (100kL - 400kL)
	 */
	public Ship(String name, int speed, int capacity, int crewNum, int endurance) {
		shipName = name;
		shipSpeed = speed;
		shipCapacity = capacity;
		shipCrewNum = crewNum;
		shipEndurance = endurance;
		
		
	}
		
	/**
	 * Checks if the player has currently got the item they want to sell.
	 * This is done by checking if the objects have the same name. 
	 * When the item is found it is updated with the island it was sold on and added to the sold items list.
	 * The item is removed from the cargo list and the ships capacity is updated to have the current capacity plus the size of the item.
	 * 
	 * @param item The item the player is trying to sell
	 * @param island The island the player is currently on
	 * 
	 * @return boolean This is true when a sale is successful and false when the player does not have the item
	 */
	public boolean sellItem(Item item, Island island) {
		for (Item itm : cargo) {
			if (itm.getName() == item.getName()) {
				itm.itemSoldIslandName(island);
				cargoValue -= item.getValue();
				soldItems.add(item);
				shipCapacity += item.getItemSize();
				cargo.remove(itm);
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Checks if the player has enough capacity left by comparing the size of the item to the ships current capacity.
	 * When they have enough space the ships capacity is updated by removing the size of the item from the current capacity.
	 * A new item object is created containing only a name, this is then added to the cargo list.
	 * 
	 * @param item The item the player has selected to buy
	 * 
	 * @return String This either states you have bought the item or you don't have enough space.
	 */
	public String buyItem(Item item) {
		if (item.getItemSize() <= shipCapacity) {
			shipCapacity -= item.getItemSize();
			cargoValue += item.getValue();
			cargo.add(new Item(item.getName()));
			return "Purchase Successful";
		} else {
			return "Not enough capacity left!";
		}
	}
	
	public int cargoValue() {	
		cargo.clear();
		return cargoValue;
	}
	
	public int getRandomNumber(int min, int max) {
	    return rand.nextInt(max - min) + min;
	}

	public String toString() {
		return "Ships name " +  shipName + "\nSpeed " + shipSpeed + "\nCost to sail per day £" + getWages() + " for " + shipCrewNum + " crew members\n";
	}
	
	public int shipCapacity() {
		return shipCapacity;
	}
	
	public int getCrewNum() {
		return shipCrewNum;
	}
	
	public int repairCost() {
		return (100 - shipEndurance)/10;
	}
	
	
	public ArrayList<Item> itemsInInventory() {
		return cargo;
	}
	
	public ArrayList<Item> getSoldItems() {
		return soldItems;
	}
	
	public int getShipEndurance() {
		return shipEndurance;
	}
	
	public int updateDamage() {
		shipEndurance -= getRandomNumber(10, 100);
		return shipEndurance;
	}
	
	public void repairShip() {
		shipEndurance = 100;
	}
	
	public int getWages() {
		return shipCrewNum * crewWage;
	}
	
	public int shipSpeed() {
		return shipSpeed;
	}
}
