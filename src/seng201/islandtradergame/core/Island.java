package seng201.islandtradergame.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Island extends JPanel {
	private String islandName;
	private Store islandStore;
	private ArrayList<Route> islandRoutes;
	private Image myImage;
	
	/**
	 * Creates a island with a name store and list of routes to other islands.
	 * 
	 * @param name The name of the island 
	 * @param store The store object for this island
	 * @param routes The array of routes to other islands from this island
	 */
	public Island(String name ,Store store, ArrayList<Route> routes) {
		islandName = name;
		islandStore = store;
		islandRoutes = routes;
		loadImages();
	}
	
	private void loadImages() {

        	myImage = new ImageIcon("src\\seng201\\islandtradergame\\ui\\gui\\Images\\island.png").getImage();
        	
        	var dm = new Dimension(myImage.getWidth(null), myImage.getHeight(null));
            setPreferredSize(dm);

    }
	
	@Override
	public void paintComponent(Graphics g) {
	    
	    super.paintComponent(g);
	    var g2d = (Graphics2D) g;

        g2d.drawImage(myImage, 100, 200, null);

        g2d.dispose();
	}
	
	/**
	 * Returns the islands name 
	 * 
	 * @return islandName The islands name 
	 */
	public String toString()  {
		return islandName;
	}

	/**
	 * Returns a array of the items to store buys for this particular island
	 * 
	 * @return Item[] The items the store buys
	 */
	public List<Item> getStoreBuyItems()  {
		return islandStore.getBuyableItems();
	}
	
	/**
	 * Returns a array of the items to store sells for this particular island
	 * 
	 * @return Item[] The items the store sells
	 */
	public Item[] getStoreSellItems()  {
		return islandStore.getSellableItems();
	}
	
	/**
	 * Returns the distance the other island is from the current island
	 * 
	 * @param routeSelection An index of the islandRoutes list for the selected route
	 * 
	 * @return int The distance the island is 
	 */
	public int routeDistance(int routeSelection) {
		return islandRoutes.get(routeSelection).getRouteDistance();
	}
	
	/**
	 * Returns the danger level of the selected route
	 * 
	 * @param routeSelection An index of the islandRoutes list for the selected route
	 * 
	 * @return int The danger level of the route
	 */
	public int routeDangerLevel(int routeSelection) {
		return islandRoutes.get(routeSelection).getDangerLevel();
	}
	
	/**
	 * Creates a list of all the routes to an island for the current island from the islands routes list
	 * 
	 * @param island The island the player wants to travel to
	 * 
	 * @return ArrayList<Route> A list of all the possible routes to this island
	 */
	public ArrayList<Route> getRoutes(Island island) {
		ArrayList<Route> routesToIsland = new ArrayList<Route>();
		for (Route route : islandRoutes) {
			if (route.oneOfIslands(island)) {
				routesToIsland.add(route);
			}
		}
		return routesToIsland;
	}
}
