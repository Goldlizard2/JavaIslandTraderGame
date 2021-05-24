package seng201.islandtradergame.core;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author EpicPC
 *
 */
public class Ship {
	private Random rand = new Random();
	private int shipSpeed, shipCapacity, shipCrewNum, shipEndurance, shipHealth, crewWage = 1, cargoValue, defence = 5;
	private String shipName;
	private ArrayList<Item> cargo = new ArrayList<Item>(), soldItems = new ArrayList<Item>();
	
	/**
	 * Creates a ship
	 * 
	 * @param name The name of ship they user inputed in the main menu
	 * @param speed The speed of the ship selected from the main menu can be (1 - 4)
	 * @param capacity The ship capacity selected can be (100kL - 400kL)
	 * @param crewNum
	 * @param endurance
	 */
	public Ship(String name, int speed, int capacity, int crewNum, int endurance) {
		shipName = name;
		shipSpeed = speed;
		shipCapacity = capacity;
		shipCrewNum = crewNum;
		shipEndurance = endurance;	
		shipHealth = shipEndurance;
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
			if (itm.equals(item)) {
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
			if(item instanceof ShipUpgradeCannon) {
				defence += item.getDefence();
			}
			
			shipCapacity -= item.getItemSize();
			cargoValue += item.getValue();
			cargo.add(new Item(item.getName()));
			return "Purchase Successful";
		} else {
			return "Not enough capacity left!";
		}
	}
	
	/**
	 * Clears the cargo list and returns cargoValue. 
	 * 
	 * @return cargoValue The value of all the items in your cargo
	 */
	public int cargoValue() {	
		cargo.clear();
		return cargoValue;
	}
	
	public int getRandomNumber(int min, int max) {
	    return rand.nextInt(max - min) + min;
	}

	/**
	 * Returns a string containing all the info about the ship and crew
	 */
	public String toString() {
		return "Ships name " +  shipName + "\nSpeed " + shipSpeed + "\nCost to sail per day £" + getWages() + " for " + shipCrewNum + " crew members\nDefence " + defence;
	}
	
	/**
	 * Returns the ships capacity.
	 * 
	 * @return shipCapacity The ships current capacity
	 */
	public int shipCapacity() {
		return shipCapacity;
	}
	
	/**
	 * Returns the repair cost.
	 * 
	 * @return int The cost to repair the traders ship
	 */
	public int repairCost() {
		return (shipEndurance - shipHealth)/10;
	}
	
	/**
	 * Returns the cargo list.
	 * 
	 * @return cargo The list of items the user has bought
	 */
	public ArrayList<Item> itemsInInventory() {
		return cargo;
	}
	
	/**
	 * Returns the soldItems list.
	 * 
	 * @return soldItems The list of items the user has sold
	 */
	public ArrayList<Item> getSoldItems() {
		return soldItems;
	}
	
	/**
	 * Returns the shipEndurance.
	 * 
	 * @return shipEndurance The ships state with no damage.
	 */
	public int getShipEndurance() {
		return shipEndurance;
	}
	
	/**
	 * Returns shipHealth.
	 * 
	 * @return shipHealth The ships state currently
	 */
	public int getShipHealth() {
		return shipHealth;
	}
	
	/**
	 * Updates the ships health by applying a random amount of damage (10 to 99).
	 * 
	 * @return int The amount of damage the ship received
	 */
	public int updateDamage() {
		shipHealth -= getRandomNumber(10, 100);
		return shipEndurance - shipHealth;
	}
	
	/**
	 * Sets the ships health to its original value.
	 */
	public void repairShip() {
		shipHealth = shipEndurance;
	}
	
	/**
	 * Returns the cost to pay all crew.
	 * 
	 * @return int The total amount to pay all crew
	 */
	public int getWages() {
		return shipCrewNum * crewWage;
	}
	
	/**
	 * Returns shipSpeed.
	 * 
	 * @return shipSpeed The speed the ship travels
	 */
	public int shipSpeed() {
		return shipSpeed;
	}
	
	/**
	 * Returns defence.
	 * 
	 * @return defence The defence the ship has against attacks
	 */
	public int getShipDefence() {
		return defence;
	}
}

