package seng201.islandtradergame.ui.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import seng201.islandtradergame.core.Island;
import seng201.islandtradergame.core.Item;
import seng201.islandtradergame.core.Route;
import seng201.islandtradergame.core.Trader;

import javax.swing.JPanel;
import java.util.Random;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GameWindow implements MouseListener {
	
	private Random rand = new Random();
	private int islandNum = 4;
	private Island[] islands;
	private Trader trader;
	private Island currentIsland;
	private int selection;
	private int gameDay = 0;
	private int gameDays;
	private int routeSelection;
	
	JFrame frame;
	private JTabbedPane tabbedPane;
	
	private JList traderCargoList, storeSellList, storeBuyList, selling, buying, routes;
	DefaultListModel cargo = new DefaultListModel();
	DefaultListModel<Route> routeModel = new DefaultListModel<Route>();
	private JButton acceptSelection;
	private JLabel island1, island2, island3, island4, island5, shipSprite, islandName, day, money;
	private JPanel islandStats, map, stats;
	private boolean islandSelected = false;
	private Island selectedIsland;
	private JTextPane shipStats, boughtSoldItems;
	private JLabel lblNewLabel, lblNewLabel_1;
	private JProgressBar shipCapacity, damage;
	private int shipx, shipy;
	
	
	/**
	* Creates the game window with .
	*
	*/
	public GameWindow(Island[] isl, Trader trad, int days) {
		islands = isl;
		trader = trad;
		gameDays = days;
		currentIsland = islands[rand.nextInt(islandNum)];
		
		
		initialise();
		//map.add(currentIsland);
		
		frame.setVisible(true);
		
		updateGuiElements();
	}

	/**
	 * Initialise the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialise() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createOverLay();
			
		//Creates a tabbed pane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1264, 681);
		frame.getContentPane().setLayout(null);
		
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
		
		lblNewLabel = new JLabel("Ship Stats");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(57, 11, 70, 20);
		stats.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Bought and Sold Items");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(210, 11, 160, 20);
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
				}
				else {
					stats.setVisible(false);
					count = 0;
				}
			}
		});
		btnStats.setBounds(1074, 0, 90, 20);
		info.add(btnStats);
		
		day = new JLabel("");
		day.setBounds(300, 0, 50, 20);
		info.add(day);
		
		money = new JLabel("");
		money.setBounds(380, 0, 40, 20);
		info.add(money);
		
		damage = new JProgressBar();
		damage.setBounds(500, 0, 100, 20);
		damage.setStringPainted(true);
		damage.setForeground(new Color(102, 51, 0));
		info.add(damage);
		
		JLabel damageLB = new JLabel("Damage");
		damageLB.setBounds(440, 0, 50, 20);
		info.add(damageLB);
		
		shipCapacity = new JProgressBar();
		shipCapacity.setBounds(680, 0, 100, 20);
		shipCapacity.setStringPainted(true);
		shipCapacity.setForeground(new Color(255, 51, 0));
		info.add(shipCapacity);
		
		JLabel lblNewLabel_5 = new JLabel("Capacity");
		lblNewLabel_5.setBounds(620, 0, 50, 20);
		info.add(lblNewLabel_5);
		frame.getContentPane().add(tabbedPane);
		
		/**
		 * Initialises the tabs of the tabbedPane.
		 */
		createMapTab();
		createStoreTab();
	}
	
	private void createMapTab() {
		/**
		 * Creates the map tab.
		 */
		
		/**
		 * Initialises the island and ship sprites.
		 */
		JPanel storeTab = new JPanel();
	
	}
	
	private void createStoreTab() {
		
		
		JPanel storeTab = new JPanel();
		tabbedPane.addTab("Store", null, storeTab, null);
		
		/**
		 * Initialises the store lists.
		 */
		traderCargoList = new JList();
		traderCargoList.setBounds(105, 68, 200, 280);
		traderCargoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		traderCargoList.addMouseListener(new MouseAdapter() {
		});
		traderCargoList.setValueIsAdjusting(true);
		
		/**
		 * Initialises the stores sellabel items.
		 */
		storeSellList = new JList();
		storeSellList.setBounds(958, 68, 200, 280);
		storeSellList.setValueIsAdjusting(true);
		
		/**
		 * Initialises the stores buyabel items.
		 */
		storeBuyList = new JList();
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
		map = new JPanel();
		map.setBackground(new Color(0, 162, 232));
		tabbedPane.addTab("Map", null, map, null);
		
		
		shipSprite = new JLabel("");
		shipSprite.setBounds(1159, 546, 100, 70);
		shipSprite.setIcon(new ImageIcon(GameWindow.class.getResource("/seng201/islandtradergame/ui/gui/Images/ship.png")));
		
		island1 = new JLabel("");
		island1.setBounds(80, 26, 200, 196);
		island1.setIcon(new ImageIcon(GameWindow.class.getResource("/seng201/islandtradergame/ui/gui/Images/Tree Island.png")));
		
		island2 = new JLabel("");
		island2.setBounds(215, 461, 300, 183);
		island2.setIcon(new ImageIcon(GameWindow.class.getResource("/seng201/islandtradergame/ui/gui/Images/PalmIsland.png")));
		
		island3 = new JLabel("");
		island3.setBounds(627, 157, 128, 128);
		island3.setIcon(new ImageIcon(GameWindow.class.getResource("/seng201/islandtradergame/ui/gui/Images/island.png")));
		
		island4 = new JLabel("");
		island4.setBounds(1025, 431, 200, 196);
		island4.setIcon(new ImageIcon(GameWindow.class.getResource("/seng201/islandtradergame/ui/gui/Images/Tree Island.png")));
		
		island5 = new JLabel("");
		island5.setBounds(925, 26, 300, 183);
		island5.setHorizontalAlignment(SwingConstants.CENTER);
		island5.setIcon(new ImageIcon(GameWindow.class.getResource("/seng201/islandtradergame/ui/gui/Images/PalmIsland.png")));
		
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
		
		islandStats = new JPanel();
		islandStats.setBounds(61, 89, 454, 307);
		islandStats.setLayout(null);
		islandStats.setVisible(false);
		
		islandName = new JLabel("");
		islandName.setBounds(10, 11, 104, 14);
		islandStats.add(islandName);
		
		JLabel lblNewLabel_2 = new JLabel("For sale");
		lblNewLabel_2.setBounds(44, 36, 46, 14);
		islandStats.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("What we buy");
		lblNewLabel_3.setBounds(196, 36, 84, 14);
		islandStats.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Routes to island");
		lblNewLabel_4.setBounds(20, 234, 104, 14);
		islandStats.add(lblNewLabel_4);
		
		selling = new JList();
		selling.setBounds(10, 61, 139, 159);
		islandStats.add(selling);
		
		buying = new JList();
		buying.setBounds(159, 61, 139, 159);
		islandStats.add(buying);
		
		routes = new JList<Route>(routeModel);
		routes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		routes.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		routes.setBounds(10, 259, 412, 37);
		islandStats.add(routes);
		
		sail.setBounds(134, 230, 89, 23);
		islandStats.add(sail);
		map.setLayout(null);
		map.add(islandStats);
		map.add(shipSprite);
		map.add(island1);
		map.add(island3);
		map.add(island2);
		map.add(island4);
		map.add(island5);
		
		map.addMouseListener(this);
		island1.addMouseListener(this);
		island2.addMouseListener(this);
		island3.addMouseListener(this);
		island4.addMouseListener(this);
		island5.addMouseListener(this);
	}
	
	private void createOverLay() {
	}
	
	//Gets current balance
	private int balance() {
		return trader.getMoney();
	}
	
	
//------------------------------------------------------ Game Workings Below -----------------------------------------
	/**
	 * Updates the store Jlists, day count, money, capacity and damage to ship
	 */
	private void updateGuiElements() {
		islandStats.setVisible(false);
		day.setText("Day " + gameDay + "/" + gameDays);
		money.setText("£" + Integer.toString(balance()));
		shipCapacity.setValue(trader.getShip().shipCapacity());
		damage.setValue(trader.getShip().getShipEndurance());
		storeSellList.setListData(currentIsland.getStoreSellItems());
		storeBuyList.setListData(currentIsland.getStoreBuyItems());
		traderCargoList.setListData(trader.getShip().itemsInInventory().toArray());//Updates the jList of current players items in there inventory	
		
		gameOverCheck();
	}
	
	/**
	 * Checks if one the player can nolonger afford to fix there ship, the game has reached the amount of days set or the players balance has 
	 * reached 0. A message is displayed to the user notifying them that the game is over.
	 */
	private void gameOverCheck() {
		 if (balance() < repairShipCost() || gameDay >= gameDays || balance() <= 0) {
			 gameOver();
		 }
	}
	
	private void gameOver() {
		JOptionPane.showMessageDialog(frame, "Game Over");
	}
	
	/**
	 * Checks if an item from the buy or sell list is selected then updates the cargo arrayList and money balance  
	 */
	private void buySell() {
		
		if (!storeBuyList.isSelectionEmpty() && storeSellList.isSelectionEmpty()) {
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
	 * Creates a two strings containg the items the user has bought and sold
	 * This is done by looping through the list of bought or sold items and adding the retund stging to a variable this is then displayed on a JTextPanel
	 */
	private void shoppingHistory() {
		String boughtItems = "";
		String soldItems = "";
		for (Item bought : trader.getShip().itemsInInventory()) {
			boughtItems += bought + "\n";
		}
		
		System.out.println("Sold items:");
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
		shipSprite.setLocation(shipx, shipy);
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
		
		if(rand.nextInt(1) != 1) {
			JOptionPane.showMessageDialog(frame, "You lose the battle with the pirates they board your ship");
			
			if (trader.getShip().cargoValue() > getRandomNumber(1, 5)) {
				JOptionPane.showMessageDialog(frame, "Your cargo meets the pirates value, you are let go minuse your items");
				travelIsland(crewWage);
			} else {
				JOptionPane.showMessageDialog(frame, "Your cargo does not meet the pirates value you and your crew are made to walk the plank");
				gameOver();
			}
			
		} else {
			JOptionPane.showMessageDialog(frame, "You win the battle with the pirates they board your ship");
		}		
	}
	
	/**
	 * Checks if the ship has no damage and if you have enough money to sail.
	 * Each route has a probility of an event ocuring the larger this value the higher chance a sea event will happen.
	 * If the random number gerated from the value equals one you have made it safle to the island. Otherwise a random event is piked from the
	 * three possible sea events. These are bad whether the ship is receives a random amount of damage, 
	 */
	private void sail() {
		if (trader.getShip().getShipEndurance() == 100) {
			
			int crewWage = trader.getCrewWage() * tripDays();
			
			if (balance() >= crewWage) {

				if (rand.nextInt(((Route) routes.getSelectedValue()).getDangerLevel()) == 0) {
					
					if (rand.nextInt(2) == 0) {
						JOptionPane.showMessageDialog(frame, "You saved some sailors you get £20 as a reward");
						trader.updatedMoney(20);
					}
					
					travelIsland(crewWage);
				} else {
					switch(rand.nextInt(2)) {
					case 0:
						JOptionPane.showMessageDialog(frame, "Bad Wether, your ship has been damaged -" + (100 - trader.getShip().updateDamage()));
						travelIsland(crewWage);
						break;
					case 1:
						pirates(crewWage);
						break;
					}
					updateGuiElements();
				}
				
			} else {
				JOptionPane.showMessageDialog(frame, "You do not have enough money to pay your crew");
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
	private void islandStats(Component islandLB) {
		
		Island island;
		
		if (islandLB.equals(island1)) {
			islandStats.setLocation(430, 10);
			shipx = 239;
			shipy = 139;
			island = islands[0];
		} else if (islandLB.equals(island2)) {
			island = islands[1];
			islandStats.setLocation(430, 10);
			shipx = 473;
			shipy = 574;
		} else if (islandLB.equals(island3)) {
			islandStats.setLocation(10, 100);
			shipx = 720;
			shipy = 198;
			island = islands[2];
		} else if (islandLB.equals(island4)) {
			islandStats.setLocation(10, 100);
			shipx = 1159;
			shipy = 546;
			island = islands[3];
		} else {
			islandStats.setLocation(10, 100);
			shipx = 1159;
			shipy = 145;
			island = islands[4];
		}
		
		
		if(!island.equals(currentIsland)) {
			
			selectedIsland = island;
			islandName.setText(island.toString());
			selling.setListData(island.getStoreSellItems());
			buying.setListData(island.getStoreBuyItems());
			routes.setListData(currentIsland.getRoutes(island).toArray(new Route[0]));
			islandStats.setVisible(true);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(map)) {
			islandSelected = false;
			islandStats.setVisible(false);
		} else {
			islandStats(e.getComponent());
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
		if (!e.getComponent().equals(map) && !islandSelected) {
			islandStats(e.getComponent());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!islandSelected) {
			islandStats.setVisible(false);
		}
	}
}
