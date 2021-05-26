package seng201.islandtradergame.ui;


import seng201.islandtradergame.core.Island;
import seng201.islandtradergame.core.Item;
import seng201.islandtradergame.core.Route;
import seng201.islandtradergame.core.ShipUpgradeCannon;
import seng201.islandtradergame.core.Store;
import seng201.islandtradergame.ui.gui.MainMenu;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class implements Main which creates the items, stores and islands.
 * 
 * @author Kei Carden
 */
public class Main {
	/**
	 * Creates a random object
	 */
	private Random rand = new Random();
	/**
	 * The number of islands
	 */
	private static int islandNum = 5;
	/**
	 * Creates an array to hold the islands
	 */
	private static Island[] islands = new Island[islandNum];
	
	/**
	 * Creates route 1 with no values
	 */
	private Route routeOne;
	/**
	 * Creates route 2 with no values
	 */
	private Route routeTwo;

	/**
	 * A set of routes to Anisly island
	 */
	ArrayList<Route> anislyRoutes = new ArrayList<Route>();
	/**
	 * A set of routes to Berkly island
	 */
	ArrayList<Route> berklyRoutes = new ArrayList<Route>();
	/**
	 * A set of routes to Montoriki island
	 */
	ArrayList<Route> montorikiRoutes = new ArrayList<Route>();
	/**
	 * A set of routes to Quail island
	 */
	ArrayList<Route> quailRoutes = new ArrayList<Route>();
	/**
	 * A set of routes to Ohinau island
	 */
	ArrayList<Route> ohinauRoutes = new ArrayList<Route>();
	
		//Creates items with a item name and item size in L
			//Food liveStock
			Item bread = new Item("Bread", 1, getRandomNumber(3, 4 ));
			Item rice = new Item("Rice", 1, getRandomNumber(2, 3 ));
			Item pig = new Item("Pig", 3, getRandomNumber(4, 8 ));
			Item cow = new Item("Cow", 4, getRandomNumber(5, 9 ));
			Item sheep = new Item("Sheep", 3, getRandomNumber(3, 8 ));
			Item potassiumNitrate = new Item("Potassium Nitrate", 3, getRandomNumber(4, 8 ));
			
			//Tools
			Item axe = new Item("Axe", 2, getRandomNumber(4, 8 ));
			Item shears = new Item("Shears", 2, getRandomNumber(4, 8 ));
			Item loom = new Item("Loom", 1, getRandomNumber(4, 8 ));
			Item pickAxe = new Item("PickAxe", 2, getRandomNumber(4, 8 ));
			Item gunPowder = new Item("gunPowder", 1, getRandomNumber(4, 8 ));
			Item hammer = new Item("Hammer", 2, getRandomNumber(4, 8 ));
			Item cannon = new ShipUpgradeCannon(getRandomNumber(4, 8 ));
			
			//Clothes
			Item woolJumper = new Item("Wool Jumper", 2, getRandomNumber(4, 8 ));
			Item leatherBoots = new Item("Leather boots", 2, getRandomNumber(4, 8 ));
			
			//Wood
			Item planks = new Item("Planks", 3, getRandomNumber(4, 8 )); 
			Item charcoal = new Item("Charcoal", 3, getRandomNumber(4, 8 ));
			
			//Minerals
			Item gold = new Item("Gold", 5, getRandomNumber(4, 8 ));
			Item silver = new Item("Silver", 2, getRandomNumber(4, 8 ));
			Item iron = new Item("Iron", 3, getRandomNumber(4, 8 ));
			Item copper = new Item("Copper", 2, getRandomNumber(4, 8 ));	
			Item sulphur = new Item("Sulphur", 1, getRandomNumber(4, 8 ));
		
			//Creates list of items each store sells and buys
			
			//Sells food products buys tools and clothes
			Item[] anislyStoreBuy = {hammer, planks, leatherBoots, woolJumper, sulphur, shears, axe};
			Item[] anislyStoreSell = {bread, rice, cow, pig, sheep, potassiumNitrate};
			
			//Sells minerals and ship cannons buys food, tools and clothes
			Item[] berklyStoreBuy = {pickAxe, leatherBoots, gunPowder, charcoal, rice, pig, sheep, loom};
			Item[] berklyStoreSell = {gold, silver, iron, copper, sulphur};
			
			//Sells tools buys metals, food, wood and clothes
			Item[] montorikiStoreBuy = {iron, copper, planks, leatherBoots, charcoal, potassiumNitrate, sulphur, rice, pig};
			Item[] montorikiStoreSell = {loom, shears, axe, gunPowder, pickAxe, hammer, cannon};
			
			//Sells clothes buys tools and food
			Item[] quailStoreBuy = {loom, sheep, cow, bread, shears, rice, pig, potassiumNitrate, copper, silver, pickAxe};
			Item[] quailStoreSell = {woolJumper, leatherBoots};
			
			//Sells wood buys tools, food and clothes
			Item[] ohinauStoreBuy = {bread, gold, axe, leatherBoots, woolJumper, pig, rice, sheep, copper, silver, iron, copper, hammer};
			Item[] ohinauStoreSell = {planks, charcoal};
	
	
	//Creates each store and gives it lists of buyable and sellabel items
	Store anislyStore = new Store(anislyStoreBuy, anislyStoreSell);
	Store berklyStore = new Store(berklyStoreBuy, berklyStoreSell);
	Store montorikiStore = new Store(montorikiStoreBuy, montorikiStoreSell);
	Store quailStore = new Store(quailStoreBuy, quailStoreSell);
	Store ohinauStore = new Store(ohinauStoreBuy, ohinauStoreSell);
	
	/**
	* Returns a random number between min and max.
	*
	* @param min  The minimum the random number can be
	* @param max  The maximum the random number can be minus 1
	* @return int This returns a random number between min and max
	*/
	public int getRandomNumber(int min, int max) {
	    return rand.nextInt(max - min) + min;
	}
	
	
	/**
	* Creates the five islands and then sets up the routes from one island to another.
	* Each island has a name store and set of routes
	* 
	* Each island has two routes from itself to another island
	*/
	private void islandSetup() {
		islands[0] = new Island("Anisly island", anislyStore, anislyRoutes, "Tree Island"); 
		islands[1] = new Island("Berkly island", berklyStore, berklyRoutes, "PalmIsland"); 
		islands[2] = new Island("Montoriki island", montorikiStore, montorikiRoutes, "island"); 
		islands[3] = new Island("Quail island", quailStore, quailRoutes, "Tree Island"); 
		islands[4] = new Island("Ohinau island", ohinauStore, ohinauRoutes, "PalmIsland"); 
		
		int g = 1;
		for (int c = 0; c < 4; c++) {
			for (int d = g;d < 5; d++) {
				//Creates a route between two islands which has a distance and danger probability out of 10. 10 is 100% & 1 is 10%
				routeOne = new Route(islands[c], islands[d], getRandomNumber(4, 8), getRandomNumber(1, 6));
				routeTwo = new Route(islands[c], islands[d], getRandomNumber(1, 4), getRandomNumber(6, 10));	
				
				if (c == 0) {
					anislyRoutes.add(routeOne);
					anislyRoutes.add(routeTwo);
				} if (c == 1 || d == 1) {
					berklyRoutes.add(routeOne);
					berklyRoutes.add(routeTwo);
				} if (c == 2 || d == 2) {
					montorikiRoutes.add(routeOne);
					montorikiRoutes.add(routeTwo);
				} if (c == 3 || d == 3) {
					quailRoutes.add(routeOne);
					quailRoutes.add(routeTwo);
				} if (c == 4 || d == 4) {
					ohinauRoutes.add(routeOne);
					ohinauRoutes.add(routeTwo);
				}	
			}
			g++;
		}
			
	}
	
	/**
	 * Crates the mainMenu and setup the islands
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.islandSetup();
		new MainMenu(islands);
	}
}
