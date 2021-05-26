package seng201.islandtradergame.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seng201.islandtradergame.core.Island;
import seng201.islandtradergame.core.Item;

class ItemTests {
	private Item testItem;
	private Item testItem2;
	private Island island = new Island("testIsland", null, null, null);
	
	@BeforeEach
	public void init() {
		testItem = new Item("testItem", 5, 3);
		testItem2 = new Item("testItem2");
	}
	
	@Test
	public void toStringTestNormal() {
		
		String itemText = testItem.toString();
		assertEquals("£ 3.00 testItem size 5", itemText);

	}
	
	@Test
	public void toStringValueZero() {
		String itemText = testItem2.toString();
		assertEquals("testItem2", itemText);
	}

	/**
	 * Tests to see if two objects with the same name are counted as being equal.
	 */
	@Test
	public void equalsTestSameName() {
		Item testItem3 = new Item("testItem");
		boolean equal = testItem.equals(testItem3);
		assertEquals(true, equal);
	}
	
	/**
	 * Tests to see if two objects with two different names are counted as not being equal.
	 */
	@Test
	public void equalsTestNameNotSame() {
		boolean equal = testItem.equals(testItem2);
		assertEquals(false, equal);
	}

	/**
	 * Tests to see if itemSoldIslandName updated the associated island var.
	 */
	@Test 
	public void itemSoldIslandNameTest() {
		testItem2.itemSoldIslandName(island);
		assertEquals("testItem2 was sold testIsland", testItem2.itemOrigin());
	}
	
	/**
	 * Checks if the value passed in is grater than 3.
	 * 
	 * @param value The value from clone item
	 * @return boolean weather the value was greater than 3 true or less than false
	 */
	private boolean graterThanTest(double value) {
		if (value > 3) {
			return true;
		} else {
			return false;
		}	
	}
	
	/**
	 * Tests to see if the cloned item has all the same characteristics except the value is grater than 3
	 */
	@Test
	public void cloneBuyableItemTest() {
		Item cloneItem = testItem.cloneBuyableItem();
		assertEquals("testItem", cloneItem.getName());
		assertEquals(5, cloneItem.getItemSize());
		assertEquals(true, graterThanTest(cloneItem.getValue()));
	}
}
