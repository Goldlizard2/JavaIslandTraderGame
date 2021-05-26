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
		testItem = Item("testItem", 10, (float) 10);
		ship = new Ship("testName", 5, 5, 5, 5);
		traderTest = new Trader(ship);
		
	}
	
	@Test
	public void test() {
		traderTest.buyItem(null);
		
		assertEquals();
	}

}
