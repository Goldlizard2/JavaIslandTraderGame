package seng201.islandtradergame.core;

/**
 * 
 * @author EpicPC
 *
 */
public class Route {
	private Island islandOne;
	private Island islandTwo;
	private int tripDistance;
	private int routeSaftyProbability;
	
	/**
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
	 * 
	 * @return
	 */
	public String getIslandOne() {
		return islandOne.toString();
	}
	
	/**
	 * 
	 * @param island
	 * @return
	 */
	public boolean oneOfIslands(Island island) {
		if (island.equals(islandOne) || island.equals(islandTwo)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @return tripDistance The distance this route is 
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
	 * 
	 */
	public String toString() {
		return islandOne + " to " + islandTwo + " distance " + tripDistance + " days problity of death " + routeSaftyProbability;
	}
}
