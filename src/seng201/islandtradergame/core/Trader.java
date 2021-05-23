package seng201.islandtradergame.core;

public class Trader {
	private Ship traderShip;
	private float money;
	
	public Trader(Ship ship) {
		traderShip = ship;
		money = 100;
	}
	
	public float getMoney() {
		return money;
	}
	
	public void updatedMoney(double money) {
		this.money += money;
	}
	
	public Ship getShip() {
		return traderShip;
	}
	
	public int getCrewWage() {
		return traderShip.getWages();
	}
	
	public String buyItem(Item item) {
		if(money >= item.getValue()) {
			money -= item.getValue();
		return traderShip.buyItem(item);
		} else {
			return "You do not have enough money!";
		}
	}
	
	public String sellItem(Item item , Island island) {
		if (traderShip.sellItem(item, island)) {
			updatedMoney(item.getValue());
			return "Sold";
		} else {
			return "You do not have this item";
		}
			
	}
}
