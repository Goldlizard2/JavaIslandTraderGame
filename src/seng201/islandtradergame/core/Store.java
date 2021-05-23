package seng201.islandtradergame.core;

import java.util.ArrayList;
import java.util.List;


public class Store {
	private List<Item> buyableItems;
	private Item[] sellableItems;
	
	public Store(Item[] bItems, Item[] sItems) {
		buyableItems = new ArrayList<Item>();
		for (Item item : bItems) {
			buyableItems.add(item.cloneBuyableItem());
		}
		sellableItems = sItems;
		//storeName = name;
	}
	
	public List<Item> getBuyableItems() {
		return buyableItems;
	}
	
	public Item[] getSellableItems() {
		return sellableItems;
	}
	
	public int amountBS(int selection) {
		if (selection == 1) {
			return buyableItems.size();
		} else {
			return sellableItems.length;
	}
		
		
	}
}
