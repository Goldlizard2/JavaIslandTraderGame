package seng201.islandtradergame.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author EpicPC
 *
 */
public class Store {
	private List<Item> buyableItems;
	private Item[] sellableItems;
	
	/**
	 * Creates a store with two arrays of items one for the items the trader can sell and the other
	 * for the items the trader can buy. The items in the buy list is updated to have different cost values. 
	 * The items in the array are cloned and the value is changed buy the clone method in item this new item is then added to a list.
	 * 
	 * @param bItems A array of items that the trader can sell
	 * @param sItems A array of items that the trader can buy
	 */
	public Store(Item[] bItems, Item[] sItems) {
		buyableItems = new ArrayList<Item>();
		for (Item item : bItems) {
			buyableItems.add(item.cloneBuyableItem());
		}
		sellableItems = sItems;
		//storeName = name;
	}
	
	/**
	 * Returns list of buyabelItems.
	 * 
	 * @return buyableItems A list containing all of items that the trader can sell
	 */
	public List<Item> getBuyableItems() {
		return buyableItems;
	}
	
	/**
	 * Returns array sellableItems.
	 * 
	 * @return sellableItems A array of items that the trader can buy
	 */
	public Item[] getSellableItems() {
		return sellableItems;
	}
}
