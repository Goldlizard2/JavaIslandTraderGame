package seng201.islandtradergame.core;

/**
 * This class implements a Route which specifies the route from one island to another. These routes are 
 * shared by two islands. 
 *
 * @author Kei Carden
 */
public class Route {
	/**
	 * The first island in the route
	 */
	private Island islandOne;
	/**
	 * The second island in the route
	 */
	private Island islandTwo;
	/**
	 * The distance of the trip
	 */
	private int tripDistance;
	/**
	 * The probability of getting a bad random event
	 */
	private int routeSaftyProbability;
	/**
	 * The islands the trader is currently on
	 */
	private Island islandCurrent;
	/**
	 * The island the trader wants to travel to
	 */
	private Island islandTravlingTo;
	
	/**
	 * Creates a route with two islands that can be sailed to and from, the distance and how safe the route is.
	 * 
	 * @param islad1 The first of two islands in this route
	 * @param islad2 The second of two islands in this route
	 * @param nauticalMiles The distance the route is from one island to the other
	 * @param safety The probability of getting a bad random event
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
	 * If the island is the same as islandOne then they traders current island is island one and wants to travel to islandTwo.
	 * islandTravlingTo becomes the second island if islandTwo is the same as the current island then islandTravlingTo becomes islandOne. 
	 * 
	 * @param island The island the routes want to be found for
	 * @return boolean Whether the island is part of this route or not
	 */
	public boolean oneOfIslands(Island island) {
		islandTravlingTo = island;
		if (island.equals(islandOne)) {
			islandCurrent = islandTwo;
			return true;
		} else if (island.equals(islandTwo)) {
			islandCurrent = islandOne;
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
		return islandCurrent + " to " + islandTravlingTo + " distance " + tripDistance + " days probability of death " + routeSaftyProbability;
	}
}
