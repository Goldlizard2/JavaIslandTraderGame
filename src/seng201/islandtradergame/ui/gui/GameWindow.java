package seng201.islandtradergame.ui.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import seng201.islandtradergame.core.Island;
import seng201.islandtradergame.core.Item;
import seng201.islandtradergame.core.Route;
import seng201.islandtradergame.core.Trader;
import seng201.islandtradergame.ui.Main;

import javax.swing.JPanel;
import java.util.Random;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextPane;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import java.awt.Toolkit;

/**
 * This class implements the gameWindow GUI, this is used for the game itself. User interactions with GUI components are used to play the game.
 *
 * @author Kei Carden
 */
public class GameWindow implements MouseListener {
	
	private Random rand = new Random();
	private int islandNum = 4;
	private Island[] islands;
	private Trader trader;
	private Island currentIsland;
	private int gameDay, gameDays, routeSelection;
	
	JFrame frame;
	private JTabbedPane tabbedPane;
	
	private JList<Item> traderCargoList, storeSellList, storeBuyList, selling, buying;
	private JList<Route> routes;
	private JButton acceptSelection;
	private JLabel shipSprite, islandName, day, money;
	private JPanel islandStats, map, stats;
	private boolean islandSelected = false, statsSelected = false;
	private Island selectedIsland;
	private JTextPane shipStats, boughtSoldItems;
	private JProgressBar shipCapacity, damage;
	private int shipx, shipy;
	
	/**
	 * Creates the game window with a array of islands, a trader and the game length in days.
	 * 
	 * @param isl A array of islands
	 * @param trad The trader object
	 * @param days How many days the game will run for
	 */
	public GameWindow(Island[] isl, Trader trad, int days) {
		islands = isl;
		trader = trad;
		gameDays = days;
		currentIsland = islands[rand.nextInt(islandNum)];
		
		
		initialise();
		frame.setVisible(true);	
		updateGuiElements();
	}

	/**
	 * Initialise the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialise() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/seng201/islandtradergame/ui/gui/Images/ship.png")));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createOverLay();
			
		//Creates a tabbed pane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1264, 681);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(tabbedPane);
		
		/**
		 * Initialises the tabs of the tabbedPane.
		 */
		createMapTab();
		createStoreTab();
	}
	
	/**
	 * Creates the map tab and initlises all the islands, ship and the islands stats panel.
	 */
	private void createMapTab() {
		map = new JPanel();
		map.setBackground(new Color(0, 162, 232));
		tabbedPane.addTab("Map", null, map, null);
		map.setLayout(null);
		
		/**
		 * Initialises the island stats overlay.
		 */	
		islandStats = new JPanel();
		islandStats.setBounds(10, 108, 450, 300);
		islandStats.setLayout(null);
		islandStats.setVisible(false);
		
		JButton sail = new JButton("Sail");
		sail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!routes.isSelectionEmpty()) {
					sail();
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a route");
					
				}
			}
		});
		
		sail.setBounds(180, 230, 89, 23);
		islandStats.add(sail);
		
		islandName = new JLabel("");
		islandName.setBounds(10, 11, 104, 14);
		islandStats.add(islandName);
		
		JLabel lblNewLabel_2 = new JLabel("For sale");
		lblNewLabel_2.setBounds(44, 36, 70, 14);
		islandStats.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("What we buy");
		lblNewLabel_3.setBounds(320, 36, 84, 14);
		islandStats.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Routes to island");
		lblNewLabel_4.setBounds(20, 234, 104, 14);
		islandStats.add(lblNewLabel_4);
		
		selling = new JList<Item>();
		selling.setBounds(10, 61, 180, 160);
		islandStats.add(selling);
		
		buying = new JList<Item>();
		buying.setBounds(260, 61, 180, 160);
		islandStats.add(buying);
		
		routes = new JList<Route>();
		routes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		routes.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		routes.setBounds(10, 259, 430, 37);
		islandStats.add(routes);

		map.add(islandStats);
		
		map.addMouseListener(this);
		
		/**s
		 * Initialises the island and ship sprites.
		 */
		
		shipSprite = new JLabel("");
		shipSprite.setBounds(890, 311, 100, 70);
		shipSprite.setIcon(new ImageIcon(GameWindow.class.getResource("/seng201/islandtradergame/ui/gui/Images/ship.png")));
		map.add(shipSprite);
		
		for (int island = 0; island <= 4; island++) {
			islands[island].setLayout(null);
			islands[island].setVisible(true);
			map.add(islands[island]);
			islands[island].addMouseListener(this);
		}
		
		islands[0].setBounds(80, 26, 200, 196);
		islands[1].setBounds(215, 461, 300, 183);
		islands[2].setBounds(627, 157, 128, 128);
		islands[3].setBounds(1025, 431, 200, 196);
		islands[4].setBounds(925, 26, 300, 183);
	}
	
	/**
	 * Creates the store tab and initlises all the other components in this tab.
	 */
	private void createStoreTab() {
		
		
		JPanel storeTab = new JPanel();
		tabbedPane.addTab("Store", null, storeTab, null);
		
		/**
		 * Initialises the store lists.
		 */
		traderCargoList = new JList<Item>();
		traderCargoList.setBounds(105, 68, 200, 280);
		traderCargoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		traderCargoList.addMouseListener(new MouseAdapter() {
		});
		traderCargoList.setValueIsAdjusting(true);
		
		/**
		 * Initialises the stores sellabel items.
		 */
		storeSellList = new JList<Item>();
		storeSellList.setBounds(958, 68, 200, 280);
		storeSellList.setValueIsAdjusting(true);
		
		/**
		 * Initialises the stores buyabel items.
		 */
		storeBuyList = new JList<Item>();
		storeBuyList.setBounds(315, 68, 633, 280);
		storeBuyList.setValueIsAdjusting(true);
		
		
		acceptSelection = new JButton("Accept Selection");
		acceptSelection.setBounds(105, 378, 1053, 242);
		acceptSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buySell();
			}
		});

		
		/**
		 * Initialises the labels of the store.
		 */
		JLabel youItemsLabel = new JLabel("Your Items");
		youItemsLabel.setBounds(151, 11, 182, 46);
		youItemsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel itemsStoreBuys = new JLabel("Sell");
		itemsStoreBuys.setBounds(643, 11, 67, 46);
		itemsStoreBuys.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel itemsStoreSells = new JLabel("Buy");
		itemsStoreSells.setBounds(1042, 11, 76, 46);
		itemsStoreSells.setFont(new Font("Tahoma", Font.PLAIN, 20));
		storeTab.setLayout(null);
		storeTab.add(youItemsLabel);
		storeTab.add(itemsStoreBuys);
		storeTab.add(itemsStoreSells);
		storeTab.add(traderCargoList);
		storeTab.add(storeBuyList);
		storeTab.add(storeSellList);
		storeTab.add(acceptSelection);
	}
	
	/**
	 * Creates the overlay panel and initlises all the other components in this tab.
	 */
	private void createOverLay() {
		stats = new JPanel();
		stats.setBounds(422, 200, 380, 260);
		frame.getContentPane().add(stats);
		stats.setBackground(Color.WHITE);
		stats.setLayout(null);
		stats.setVisible(false);
		
		boughtSoldItems = new JTextPane();
		boughtSoldItems.setBackground(new Color(204, 255, 204));
		boughtSoldItems.setBounds(187, 35, 184, 214);
		boughtSoldItems.setEditable(false);
		stats.add(boughtSoldItems);
		
		shipStats = new JTextPane();
		shipStats.setBackground(new Color(204, 255, 204));
		shipStats.setBounds(10, 35, 167, 214);
		shipStats.setText((String) null);
		shipStats.setEditable(false);
		stats.add(shipStats);
		
		JLabel lblNewLabel = new JLabel("Ship Stats");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(57, 11, 99, 20);
		stats.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bought and Sold Items");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(187, 11, 183, 20);
		stats.add(lblNewLabel_1);
		
		JPanel info = new JPanel();
		info.setBounds(100, 0, 1164, 20);
		frame.getContentPane().add(info);
		info.setLayout(null);
		
		JButton btnStats = new JButton("Stats");
		btnStats.addMouseListener(new MouseAdapter() {
			int count;
			@Override
			public void mouseClicked(MouseEvent e) {
				count++;
				if(count == 1) {
					stats.setVisible(true);
					statsSelected = true;
				}
				else {
					stats.setVisible(false);
					statsSelected = false;
					count = 0;
				}
			}
		});
		btnStats.setBounds(1074, 0, 90, 20);
		info.add(btnStats);
		
		day = new JLabel("day");
		day.setBounds(300, 0, 60, 20);
		info.add(day);
		
		money = new JLabel("9999");
		money.setBounds(370, 0, 50, 20);
		info.add(money);
		
		damage = new JProgressBar();
		damage.setMaximum(trader.getShip().getShipEndurance());
		damage.setBounds(536, 2, 100, 20);
		damage.setStringPainted(true);
		damage.setForeground(new Color(102, 51, 0));
		info.add(damage);
		
		JLabel damageLB = new JLabel("Damage");
		damageLB.setBounds(469, 0, 70, 20);
		info.add(damageLB);
		
		shipCapacity = new JProgressBar();
		shipCapacity.setMaximum(trader.getShip().shipCapacity());
		shipCapacity.setBounds(707, 2, 100, 20);
		shipCapacity.setStringPainted(true);
		shipCapacity.setForeground(new Color(255, 51, 0));
		info.add(shipCapacity);
		
		JLabel lblNewLabel_5 = new JLabel("Capacity");
		lblNewLabel_5.setBounds(646, 0, 70, 20);
		info.add(lblNewLabel_5);
	}
	
	
//------------------------------------------------------ Game Workings Below -----------------------------------------
	/**
	 * Updates the store Jlists, day count, money, capacity and damage to ship
	 */
	private void updateGuiElements() {
		islandStats.setVisible(false);
		day.setText("Day " + gameDay + "/" + gameDays);
		money.setText(String.format("£ %.2f",balance()));
		shipCapacity.setValue(trader.getShip().shipCapacity());
		damage.setValue(trader.getShip().getShipHealth());
		setShipLocation();
		storeSellList.setListData(currentIsland.getStoreSellItems());
		storeBuyList.setListData(currentIsland.getStoreBuyItems().toArray(new Item[0]));
		traderCargoList.setListData(trader.getShip().itemsInInventory().toArray(new Item[0]));//Updates the jList of current players items in there inventory	
		shipStats.setText(trader.getShip().toString());
		
		gameOverCheck();
	}
	
	/**
	 * Gets the amount of money the player has.
	 * 
	 * @return double The players current balance
	 */
	private float balance() {
		return trader.getMoney();
	}
	
	/**
	 * Checks if one the player can no longer afford to fix there ship, the game has reached the amount of days set or the players balance has 
	 * reached 0. 
	 */
	private void gameOverCheck() {
		 if (balance() < repairShipCost() || gameDay >= gameDays || balance() <= 0) {
			 gameOver();
		 }
	}
	
	/**
	 * A message is displayed to the user notifying them that the game is over and how much money they made.
	 */
	private void gameOver() {
		JOptionPane.showMessageDialog(frame, "Game Over you made £" + balance());
		Main main = new Main();
		main.restart();
		quit();
	}
	
	/**
	 * Checks if an item from the buy or sell list is selected then updates the cargo arrayList and money balance  
	 */
	private void buySell() {
		
		if (!storeBuyList.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(frame,trader.sellItem((Item) storeBuyList.getSelectedValue(), currentIsland));
			storeBuyList.clearSelection();
		} else {
			JOptionPane.showMessageDialog(frame,trader.buyItem((Item) storeSellList.getSelectedValue()));
			storeSellList.clearSelection();
		}

		updateGuiElements();
		shoppingHistory(); //Updates the shopping history jList for the stats menu
	}
	
	/**
	 * Creates a two strings containing the items the user has bought and sold
	 * This is done by looping through the list of bought or sold items and adding the returned string to a variable this is then displayed on a JTextPanel
	 */
	private void shoppingHistory() {
		String boughtItems = "";
		String soldItems = "";
		for (Item bought : trader.getShip().itemsInInventory()) {
			boughtItems += bought + "\n";
		}
		
		for (Item sold : trader.getShip().getSoldItems()) {
			soldItems += sold.itemOrigin() + "\n";
		}
		
		boughtSoldItems.setText("Bought items:\n" + boughtItems + "Sold items:\n" + soldItems);
	}
	
	void quit() {
		frame.dispose();
    }
	
	/**
	 * Calculates the days the trip will take from the ships speed and the distance of the route
	 * 
	 * @return int The days the trip will take
	 */
	private int tripDays() {
		return (int) Math.ceil(currentIsland.routeDistance(routeSelection) / trader.getShip().shipSpeed());
	}
	
	
	public int getRandomNumber(int min, int max) {
	    return rand.nextInt(max - min) + min;
	}
	
	private void setShipLocation() {
		shipx = currentIsland.getX() + currentIsland.getWidth() - 50;
		shipy = currentIsland.getY() + currentIsland.getHeight() - 70;
		shipSprite.setLocation(shipx, shipy);
	}
	
	/**
	 * Updates the game day to the trip length plus the current game day.
	 * Changes the island to the island the player selected to travel to.
	 * Displays to the player the amount of money spent on wages
	 * Updated the trader balance and updates the all other components with gameloop.
	 * 
	 * @param crewWage The amount of money spent on crew for the trip
	 */
	private void travelIsland(int crewWage) {
		gameDay += tripDays();
		currentIsland = selectedIsland;
		
		JOptionPane.showMessageDialog(frame, "-£" + crewWage + " On crew wages");
		trader.updatedMoney(-crewWage);		
		islandSelected = false;
		updateGuiElements();
	}
	
	/**
	 * Calculates the cost to repair the ship
	 * 
	 * @return int The cost to repair the ship
	 */
	private int repairShipCost() {
		return trader.getShip().repairCost();
	}
	
	private void pirates(int crewWage) {
		JOptionPane.showMessageDialog(frame, "You encounter some pirates, you begin to battle with them");
		if(rand.nextInt(trader.getShip().getShipDefence()) <= 3) {
			JOptionPane.showMessageDialog(frame, "You lose the battle with the pirates they board your ship");
			
			if (trader.getShip().cargoValue() > getRandomNumber(20, 30)) {
				JOptionPane.showMessageDialog(frame, "Your cargo meets the pirates value, you are let go minuse your items");
				travelIsland(crewWage);
			} else {
				JOptionPane.showMessageDialog(frame, "Your cargo does not meet the pirates value you and your crew are made to walk the plank");
				gameOver();
			}
			
		} else {
			JOptionPane.showMessageDialog(frame, "You win the battle with the pirates");
			travelIsland(crewWage);
		}		
	}
	
	/**
	 * Checks if the ship has no damage and if you have enough money to sail.
	 * Each route has a probability of an event occurring the larger this value the higher chance a sea event will happen.
	 * If the random number generated from the value is less than two you have made it safe to the island. Otherwise a random event is piked from the
	 * three possible sea events. These are bad whether the ship is receives a random amount of damage, 
	 */
	private void sail() {
		if (trader.getShip().getShipHealth() >= 100) {
			
			int crewWage = trader.getCrewWage() * tripDays();
			
			if (balance() >= crewWage) {

				if (rand.nextInt(((Route) routes.getSelectedValue()).getDangerLevel()) <= 2) {
					
					if (rand.nextInt(3) == 0) {
						JOptionPane.showMessageDialog(frame, "You saved some sailors you get £20 as a reward");
						trader.updatedMoney(20);
					}
					
					travelIsland(crewWage);
				} else {
					switch(rand.nextInt(3)) {
					case 0:
						pirates(crewWage);
						break;
						
					case 1:
					case 2:
						JOptionPane.showMessageDialog(frame, "Bad weather, your ship has been damaged -" + (100 - trader.getShip().updateDamage()));
						travelIsland(crewWage);
						break;
					}
					updateGuiElements();
				}
				
			} else {
				JOptionPane.showMessageDialog(frame, "You do not have enough money to pay your crew");
				gameOver();
			}	
		} else {
			
			int fix = JOptionPane.showConfirmDialog(frame, "You need to fix your boat before you can sail. Cost to repair is £" + repairShipCost());
			if (fix == 0) {
				trader.updatedMoney(repairShipCost());
				trader.getShip().repairShip();
				updateGuiElements();
				sail();
			}
		}
	}
	
	/**
	 * The islands components in the window are JLabel components.
	 * When clicking or hovering the corresponding island object is found.
	 * The island stats panel is displayed with the current island stats.
	 */
	private void islandStats(Island island) {
		
		
		if(!island.equals(currentIsland)) {
			
			if(island.getX() > map.getWidth()/2) {
				islandStats.setLocation(10, 176);
			} else {
				islandStats.setLocation(800, 176);
			}
			
			selectedIsland = island;
			islandName.setText(island.toString());
			selling.setListData(island.getStoreSellItems());
			buying.setListData(island.getStoreBuyItems().toArray(new Item[0]));
			routes.setListData(currentIsland.getRoutes(island).toArray(new Route[0]));
			islandStats.setVisible(true);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(map)) {
			islandSelected = false;
			islandStats.setVisible(false);
		} else if (!statsSelected){
			islandStats((Island) e.getComponent());
			islandSelected = true;
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (!e.getComponent().equals(map) && !islandSelected && !statsSelected) {
			islandStats((Island) e.getComponent());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!islandSelected) {
			islandStats.setVisible(false);
		}
	}
}
