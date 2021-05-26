package seng201.islandtradergame.core;

/**
 * This class implements a Route which specifies the route from one island to another. These routes are 
 * shared by two islands. 
 *
 * @author Kei Carden
 */
public class Route {
	private Island islandOne;
	private Island islandTwo;
	private int tripDistance;
	private int routeSaftyProbability;
	
	/**
	 * Creates a route with two islands that can be sailed to and from, the distance and how safe the route is.
	 * 
	 * @param islad1
	 * @param islad2
	 * @param nauticalMiles
	 * @param safety
	 */
	public Route(Island islad1, Island islad2, int nauticalMiles, int safety) {
		islandOne = islad1;
		islandTwo = islad2; 
		tripDistance = nauticalMiles;
		routeSaftyProbability = safety;
	}
	
	/**
	 * Returns the name of an island
	 * 
	 * @return String The name of the isalnd
	 */
	public String getIslandOne() {
		return islandOne.toString();
	}
	
	/**
	 * Checks to see if the parsed in island is the same as any of the islands in this route.
	 * 
	 * @param island The island the routes want to be found for
	 * @return boolean Whether the island is part of this route or not
	 */
	public boolean oneOfIslands(Island island) {
		if (island.equals(islandOne) || island.equals(islandTwo)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Returns the distance the trip is.
	 * 
	 * @return tripDistance The distance between islandOne and islandTwo for this route
	 */
	public int getRouteDistance() {
		return tripDistance;
	}
	
	/**
	 * Returns the danger level of the selected route
	 * 
	 * @return routeSaftyProbability The danger level of the route
	 */
	public int getDangerLevel() {
		return routeSaftyProbability;
	}
	
	/**
	 * Constructs a string made up of the two islands, trip distance and how safe the route is
	 * 
	 * @return String The info about the route
	 */
	public String toString() {
		return islandOne + " to " + islandTwo + " distance " + tripDistance + " days problity of death " + routeSaftyProbability;
	}
}
