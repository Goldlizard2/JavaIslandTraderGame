package seng201.islandtradergame.core;

public class Route {
	private Island islandOne;
	private Island islandTwo;
	private int tripDistance;
	private int routeSaftyProbability;
	
	public Route(Island islad1, Island islad2, int nauticalMiles, int safety) {
		islandOne = islad1;
		islandTwo = islad2; 
		tripDistance = nauticalMiles;
		routeSaftyProbability = safety;
	}
	
	public String getIslandOne() {
		return islandOne.toString();
	}
	
	public boolean oneOfIslands(Island island) {
		if (island.equals(islandOne) || island.equals(islandTwo)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public int getRouteDistance() {
		return tripDistance;
	}
	
	public int getDangerLevel() {
		return routeSaftyProbability;
	}
	
	public String toString() {
		return islandOne + " to " + islandTwo + " distance " + tripDistance + " days problity of death " + routeSaftyProbability;
	}
}
