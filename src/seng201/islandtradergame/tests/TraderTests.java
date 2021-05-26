package seng201.islandtradergame.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seng201.islandtradergame.core.Item;
import seng201.islandtradergame.core.Ship;
import seng201.islandtradergame.core.Trader;

class TraderTests {
	Trader traderTest;
	Ship ship;
	Item testItem;
	
	@BeforeEach
	public void init() {
		ship = new Ship("testName", 5, 500, 5, 5);
		traderTest = new Trader(ship);
		
	}
	
	@Test
	public void buyItemNotEnoughMoney() {
		Item testItemExpensive = new Item("testItemExpensive", 10, 200);
		String buy = traderTest.buyItem(testItemExpensive);
		
		assertEquals("You do not have enough money!", buy);
	}
	
	@Test
	public void buyItemEnoughMoney() {
		Item testItem = new Item("testItem", 10, 10);
		String buy = traderTest.buyItem(testItem);
		
		assertEquals("Purchase Successful", buy);
	}

}
