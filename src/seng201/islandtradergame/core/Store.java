package seng201.islandtradergame.core;

public class Store {
	private Item[] buyableItems;
	private Item[] sellableItems;
	
	public Store(Item[] bItems, Item[] sItems) {
		buyableItems = bItems;
		sellableItems = sItems;
		//storeName = name;
	}
	
	public Item[] getBuyableItems() {
		return buyableItems;
	}
	
	public Item[] getSellableItems() {
		return sellableItems;
	}
	
	public int amountBS(int selection) {
		if (selection == 1) {
			return buyableItems.length;
		} else {
			return sellableItems.length;
	}
		
		
	}
}
