package seng201.islandtradergame.tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seng201.islandtradergame.core.Island;
import seng201.islandtradergame.core.Item;
import seng201.islandtradergame.core.Ship;

class ShipTests {
	private Ship shipTest;
	private Item testItem;
	private Island island;
	
	@BeforeEach
	public void init() {
		shipTest = new Ship("Johnny", 1, 300, 4, 200);
		testItem = new Item("testItem", 5, 3);
		island = new Island("testIsland", null, null, null);
	}
	
	/**
	 * Tests if the buying the item will not fit in the ship as there is not enough space left
	 */
	@Test 
	public void buyItemTestNotEnoughCapacity() {
		Ship ship = new Ship("Johnny", 1, 1, 4, 200);
		boolean buy = ship.buyItem(testItem);
		assertEquals(true, buy);
	}
	
	@Test 
	public void buyItemTest() {
		boolean buy = shipTest.buyItem(testItem);
		assertEquals(false, buy);
	}
	
	/**
	 * Tests if the user can sell an item if the item they are trying to sell is in there cargo
	 */
	@Test
	public void sellItemTest() {
		shipTest.buyItem(testItem);
		boolean sell = shipTest.sellItem(testItem, island);
		assertEquals(true, sell);
	}
	
	/**
	 * Tests if the user can not sell the item as they don't have the item
	 */
	@Test
	public void sellItemTestNotSameItem() {
		Item item = new Item("testItem2", 5, 3);
		shipTest.buyItem(testItem);
		boolean sell = shipTest.sellItem(item, island);
		assertEquals(false, sell);
	}
}
