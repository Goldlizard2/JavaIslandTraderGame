package seng201.islandtradergame.core;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * bruh
 */
public class Island extends JLabel {
	private static final long serialVersionUID = 1L;
	private String islandName;
	private Store islandStore;
	private ArrayList<Route> islandRoutes;
	private Image myImage;
	private String islandSprite;
	
	/**
	 * Creates a island with a name store and list of routes to other islands.
	 * 
	 * @param name The name of the island 
	 * @param store The store object for this island
	 * @param routes The array of routes to other islands from this island
	 * @param sprite The name of the image the island will be
	 */
	public Island(String name ,Store store, ArrayList<Route> routes, String sprite) {
		islandName = name;
		islandStore = store;
		islandRoutes = routes;
		islandSprite = sprite;
		loadImages();
	}
	
	/**
	 * Loads in the image file which can be changed by changing the isalndSprite string.
	 * This image has its size setup this is based of the images width and height.
	 */
	private void loadImages() {
		myImage = new ImageIcon(Island.class.getResource("/seng201/islandtradergame/ui/gui/Images/" + islandSprite + ".png")).getImage();
            setPreferredSize(new Dimension(myImage.getWidth(null), myImage.getHeight(null)));
    }
	
	/**
	 * Draws the image to the screen each time the paint component is called.
	 */
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    var g2d = (Graphics2D) g;

        g2d.drawImage(myImage, 0, 0, null);

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
