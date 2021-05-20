package seng201.islandtradergame.ui;


import seng201.islandtradergame.core.Island;
import seng201.islandtradergame.core.Item;
import seng201.islandtradergame.core.Route;
import seng201.islandtradergame.core.Ship;
import seng201.islandtradergame.core.Store;
import seng201.islandtradergame.core.Trader;
import seng201.islandtradergame.ui.gui.GameWindow;
import seng201.islandtradergame.ui.gui.MainMenu;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	private Random rand = new Random();
	private static int islandNum = 5;
	private static Island[] islands = new Island[islandNum];
	
	Route r1;
	Route r2;

	ArrayList<Route> a = new ArrayList<Route>();
	ArrayList<Route> b = new ArrayList<Route>();
	ArrayList<Route> m = new ArrayList<Route>();
	ArrayList<Route> q = new ArrayList<Route>();
	ArrayList<Route> o = new ArrayList<Route>();
	
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
			Item cannon = new Item("Cannon", 5, getRandomNumber(4, 8 ));
			
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
			Item[] aStoreB = {hammer, planks, leatherBoots, woolJumper};
			Item[] aStoreS = {bread, rice, cow, pig, sheep, potassiumNitrate};
			
			//Sells minerals and ship cannons buys food, tools and clothes
			Item[] bStoreB = {pickAxe, leatherBoots, gunPowder};
			Item[] bStoreS = {gold, silver, iron, copper, sulphur};
			
			//Sells tools buys metals, food, wood and clothes
			Item[] mStoreB = {iron, copper, planks, leatherBoots};
			Item[] mStoreS = {loom, shears, axe, gunPowder, pickAxe, hammer, cannon};
			
			//Sells clothes buys tools and food
			Item[] qStoreB = {loom, sheep, cow, bread, shears};
			Item[] qStoreS = {woolJumper, leatherBoots};
			
			//Sells wood buys tools, food and clothes
			Item[] oStoreB = {bread, gold, axe, leatherBoots, woolJumper, pig};
			Item[] oStoreS = {planks, charcoal};
	
	
	//Creates each store and gives it lists of buyable and sellabel items
	Store aStore = new Store(aStoreB, aStoreS);
	Store bStore = new Store(bStoreB, bStoreS);
	Store mStore = new Store(mStoreB, mStoreS);
	Store qStore = new Store(qStoreB, qStoreS);
	Store oStore = new Store(oStoreB, oStoreS);
	
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
		islands[0] = new Island("Anisly island", aStore, a); 
		islands[1] = new Island("Berkly island", bStore, b); 
		islands[2] = new Island("Montoriki island", mStore, m); 
		islands[3] = new Island("Quail island", qStore, q); 
		islands[4] = new Island("Ohinau island", oStore, o); 
		
		int g = 1;
		for (int c = 0; c < 4; c++) {
			for (int d = g;d < 5; d++) {
				System.out.println(d);
				//Creates a route between two islands which has a distance and danger probability out of 10. 10 is 100% & 1 is 10%
				r1 = new Route(islands[c], islands[d], getRandomNumber(4, 8), getRandomNumber(1, 6));
				r2 = new Route(islands[c], islands[d], getRandomNumber(1, 4), getRandomNumber(6, 10));	
				
				if (c == 0) {
					a.add(r1);
					a.add(r2);
				} if (c == 1 || d == 1) {
					b.add(r1);
					b.add(r2);
				} if (c == 2 || d == 2) {
					m.add(r1);
					m.add(r2);
				} if (c == 3 || d == 3) {
					q.add(r1);
					q.add(r2);
				} if (c == 4 || d == 4) {
					o.add(r1);
					o.add(r2);
				}	
			}
			g++;
		}
			
	}
	
	public static void main(String[] args) {
		Main it = new Main();
		it.islandSetup();
		int shipSize = 1;
		Ship ship = new Ship("James" ,shipSize, 100 * shipSize, shipSize, 100);
		Trader trader = new Trader(ship);
		//GameWindow game = new GameWindow(islands, trader, 50);
		new MainMenu(islands);
	}
}
