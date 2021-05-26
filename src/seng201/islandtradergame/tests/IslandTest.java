package seng201.islandtradergame.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seng201.islandtradergame.core.Island;
import seng201.islandtradergame.core.Route;

class IslandTest {

	Island island;
	Island testIslandOne = new Island("testIslandOne", null, null, "island");
	Island testIslandTwo = new Island("testIslandTwo", null, null, "island");
	Route route = new Route(testIslandOne, testIslandTwo, 0, 0);
	ArrayList<Route> routes = new ArrayList<Route>();
	
	@BeforeEach
	public void init() {
		routes.add(route);
		island = new Island("testIsland", null, routes, "island");
	}
	
	@Test
	void test() {
		ArrayList<Route> routesTwo = island.getRoutes(testIslandOne);
		assertEquals(routes, routesTwo);
	}

}
