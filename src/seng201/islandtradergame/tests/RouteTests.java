package seng201.islandtradergame.tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seng201.islandtradergame.core.Island;
import seng201.islandtradergame.core.Route;

class RouteTests {

	Route testRoute;
	Island testIslandOne;
	Island testIslandTwo;
	Island testIslandThree;
	
	@BeforeEach
	public void init() {
		testIslandOne = new Island("testIslandOne", null, null, "island");
		testIslandTwo = new Island("testIslandTwo", null, null, "island");
		testIslandThree = new Island("testIslandThree", null, null, "island");
		testRoute = new Route(testIslandOne, testIslandTwo, 10, 5);
		
	}
	
	@Test
	public void oneOfIslandsTestIslandOne() {
		boolean isalndOne = testRoute.oneOfIslands(testIslandOne);
		assertEquals(true, isalndOne);
	}
	
	@Test
	public void oneOfIslandsTestIslandTwo() {
		boolean isalndTwo = testRoute.oneOfIslands(testIslandTwo);
		assertEquals(true, isalndTwo);
		
	}
	
	@Test
	public void oneOfIslandsTestIslandNoAssosatedIsland() {
		boolean isalndThree = testRoute.oneOfIslands(testIslandThree);
		assertEquals(false, isalndThree);
	}

}
