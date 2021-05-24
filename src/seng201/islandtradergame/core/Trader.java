package seng201.islandtradergame.core;

/**
 * 
 * @author EpicPC
 *
 */
public class Trader {
	private Ship traderShip;
	private float money;
	
	/**
	 * Creates a trader with a starting amount of 100 pound and a ship with specific attributes
	 * 
	 * @param ship
	 */
	public Trader(Ship ship) {
		traderShip = ship;
		money = 100;
	}
	
	/**
	 * Returns current balance.
	 * 
	 * @return money This is the traders current balance
	 */
	public float getMoney() {
		return money;
	}
	
	/**
	 * Updates the balance of the trader by a specific value.
	 * 
	 * @param money The amount the users money should be changed by
	 */
	public void updatedMoney(double money) {
		this.money += money;
	}
	
	/**
	 * Returns the ship the player chose.
	 * 
	 * @return traderShip The ship object
	 */
	public Ship getShip() {
		return traderShip;
	}
	
	/**
	 * Returns the wage calculated by the ship getWages method.
	 * 
	 * @return int The wage calculated buy the ship object
	 */
	public int getCrewWage() {
		return traderShip.getWages();
	}
	
	/**
	 * Checks if you have sufficient funds to buy this item.
	 * If so the money is removed from your balance.
	 * The item is then used in the ship method buy item which will return a string.
	 * 
	 * @param item The item the trader wants to buy
	 * @return String States you don't have the money for this item
	 */
	public String buyItem(Item item) {
		if(money >= item.getValue()) {
			money -= item.getValue();
		return traderShip.buyItem(item);
		} else {
			return "You do not have enough money!";
		}
	}
	
	/**
	 * Checks if the trader can sell the item by using the ship sellItem method.
	 * If true the users balance is then updated with the updatedMoney method.
	 * And the a string is returned stating the item was sold.
	 * Otherwise a string is returned stating you don't have the item.
	 * 
	 * @param item The item the trader wants to sell
	 * @param island The island the item is getting sold at
	 * @return String States if you have the item or if your have sold it
	 */
	public String sellItem(Item item , Island island) {
		if (traderShip.sellItem(item, island)) {
			updatedMoney(item.getValue());
			return "Sold";
		} else {
			return "You do not have this item";
		}
			
	}
}
