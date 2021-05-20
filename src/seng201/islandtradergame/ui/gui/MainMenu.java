package seng201.islandtradergame.ui.gui;

import javax.swing.JFrame;
import seng201.islandtradergame.core.Island;
import seng201.islandtradergame.core.Ship;
import seng201.islandtradergame.core.Trader;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainMenu implements MouseListener {

	private JFrame mainMenuFrame;
	private JPanel shipStats;
	private JProgressBar speedBar, capacityBar, crewNumBar, enduranceBar;
	private JButton startGame;
	private JTextField traderNameTextField;
	private JLabel nameErrorLB, shipErrorLB;
	private String nameError = "Must only contain letters and have a minimum of 3 characters", shipNotSelectedError = "You must select a ship";
	private int hullCapacity = 100; // In L
	private int crew = 2; 
	private JRadioButton ship1, ship2, ship3, ship4;
	private ButtonGroup radioGroup = new ButtonGroup();
	private int endurance = 100;
	private int islandNum;
	private boolean shipSelected = false;
	private Island[] islands;
	private int shipSize;
	private JPanel nameInput;
	private JPanel days;
	private JSlider slider;

	/**
	 * Creates the main menu window with the five islands.
	 * Initialise is called setting up the GUI 
	 * 
	 * @param islands The array of islands that was set up in main
	 */
	public MainMenu(Island[] islands) {
		this.islands = islands;
		
		initialise();
		mainMenuFrame.setVisible(true);
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialise() {
		mainMenuFrame = new JFrame();
		mainMenuFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/seng201/islandtradergame/ui/gui/Images/island.png")));
		mainMenuFrame.setTitle("Main Menu");
		mainMenuFrame.setBounds(100, 100, 731, 507);
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setupShipStatsPane();
		
		//Start button
		startGame = new JButton("Start");
		startGame.setEnabled(false);
		startGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent  e) {
				Ship ship = new Ship(traderNameTextField.getText(), shipSize, hullCapacity * shipSize, crew * shipSize, endurance);
				Trader trader = new Trader(ship);
				new GameWindow(islands, trader, slider.getValue());
				quit();
			}
		});
		
		JPanel ships = new JPanel();
		ships.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Select a ship");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 11, 116, 23);
		ships.add(lblNewLabel_1);
		
		//Setup radioButtons
		ship1 = new JRadioButton("Ship 1");
		ship1.setBounds(10, 50, 64, 23);
		ships.add(ship1);
		ship1.setActionCommand("1");
		
		//Group the radio buttons
		radioGroup.add(ship1);
		
		ship2 = new JRadioButton("Ship 2");
		ship2.setBounds(10, 76, 64, 23);
		ships.add(ship2);
		ship2.setActionCommand("2");
		radioGroup.add(ship2);
		
		ship3 = new JRadioButton("Ship 3");
		ship3.setBounds(10, 102, 64, 23);
		ships.add(ship3);
		ship3.setActionCommand("3");
		radioGroup.add(ship3);
		
		ship4 = new JRadioButton("Ship 4");
		ship4.setBounds(10, 128, 64, 23);
		ships.add(ship4);
		ship4.setActionCommand("4");
		radioGroup.add(ship4);
		
		shipErrorLB = new JLabel(shipNotSelectedError);
		shipErrorLB.setBounds(10, 151, 155, 14);
		ships.add(shipErrorLB);
		shipErrorLB.setForeground(Color.RED);
		
		JLabel lblNewLabel_3 = new JLabel("Trader Island");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		nameInput = new JPanel();
		nameInput.setLayout(null);
		
		//Setup labels
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 64, 17);
		nameInput.add(lblNewLabel);
		
		traderNameTextField = new JTextField();
		traderNameTextField.setBounds(73, 13, 86, 20);
		nameInput.add(traderNameTextField);
		traderNameTextField.setColumns(10);
		
		//Setup error labels
		nameErrorLB = new JLabel(nameError);
		nameErrorLB.setBounds(10, 36, 358, 14);
		nameInput.add(nameErrorLB);
		nameErrorLB.setForeground(Color.RED);
		
		days = new JPanel();
		days.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Select Days the game will run for");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 11, 326, 25);
		days.add(lblNewLabel_2);
		
		//Setup slider
		slider = new JSlider();
		slider.setSnapToTicks(true);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 15));
		slider.setBounds(10, 57, 298, 46);
		days.add(slider);
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setValue(35);
		slider.setMinimum(20);
		slider.setMaximum(50);
		slider.setMajorTickSpacing(10);
		GroupLayout groupLayout = new GroupLayout(mainMenuFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(322)
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
					.addGap(257))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(days, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(385, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(ships, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(shipStats, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(369, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(startGame, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(nameInput, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(ships, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(30)
									.addComponent(shipStats, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
							.addGap(11)
							.addComponent(days, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
						.addComponent(startGame, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		mainMenuFrame.getContentPane().setLayout(groupLayout);
		traderNameTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkCanContinue();
			}
		});
		ship4.addMouseListener(this);
		ship3.addMouseListener(this);
		ship2.addMouseListener(this);
		
		//Register a listener for the radio buttons
	    ship1.addMouseListener(this);
	}
	
	
	/**
	 * Creates the JPane that contains the ship stats components.
	 */
	private void setupShipStatsPane() {
		
		shipStats = new JPanel();
		shipStats.setVisible(false);
		shipStats.setLayout(null);
		
		//Bars to show stats
		speedBar = new JProgressBar();
		speedBar.setMaximum(4);
		speedBar.setForeground(Color.ORANGE);
		speedBar.setBounds(80, 11, 100, 14);
		shipStats.add(speedBar);
		
		capacityBar = new JProgressBar();
		capacityBar.setMaximum(1000);
		capacityBar.setForeground(Color.ORANGE);
		capacityBar.setBounds(80, 36, 100, 14);
		shipStats.add(capacityBar);
		
		crewNumBar = new JProgressBar();
		crewNumBar.setMaximum(10);
		crewNumBar.setForeground(Color.ORANGE);
		crewNumBar.setBounds(80, 61, 100, 14);
		shipStats.add(crewNumBar);
		
		enduranceBar = new JProgressBar();
		enduranceBar.setForeground(Color.ORANGE);
		enduranceBar.setBounds(80, 86, 100, 14);
		shipStats.add(enduranceBar);
		
		//Label for the bars
		JLabel speedLB = new JLabel("Speed");
		speedLB.setBounds(10, 11, 46, 14);
		shipStats.add(speedLB);
		
		JLabel capacityLB = new JLabel("Capacity");
		capacityLB.setBounds(10, 36, 60, 14);
		shipStats.add(capacityLB);
		
		JLabel crewNumLB = new JLabel("Crew");
		crewNumLB.setBounds(10, 61, 46, 14);
		shipStats.add(crewNumLB);
		
		JLabel enduranceLB = new JLabel("Endurance");
		enduranceLB.setBounds(10, 86, 70, 14);
		shipStats.add(enduranceLB);
	}
	
	
	/**
	 * Sets the value on the progress bars according to the current ship size
	 * 
	 * @param size The size of ship selected can be from 1 to 4
	 */
	private void barStats(int size) {
		shipStats.setVisible(true);
		speedBar.setValue(5 - size);
		capacityBar.setValue(size * hullCapacity);
		crewNumBar.setValue(size);
		enduranceBar.setValue(size * endurance);
		shipSize = size;
	}
	
	/**
	 * Determines the ship size from the radio button selected.
	 * 
	 *  @param rBTN The radio button that has been clicked
	 */
	private void shipSize(Component rBTN) {
		int size = 0;
		if (rBTN.equals(ship1)) {
			size = 1;
		} else if (rBTN.equals(ship2)) {
			size = 2;
		} else if (rBTN.equals(ship3)) {
			size = 3;
		} else {
			size = 4;
		}
		barStats(size);
	}
	
	
	/**
	 * Determines when a user has clicked one of the radio buttons that implements mouseEvent
	 * 
	 * @param e The component that has been clicked
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		shipSelected = true;
		checkCanContinue();
		shipSize(e.getComponent());	
	}

	/**
	 * Checks if a user has hovered over one of the radio buttons that implements mouseEvent.
	 * If a radio button has been selected then the ship stats bars are not updated
	 * Otherwise the ship stats bars will be updated with the current ship size the user is hovering over 
	 * 
	 * @param e The component that has been hovered over 
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (!shipSelected) {
			shipSize(e.getComponent());	
		}	
	}

	/**
	 * Checks if a user has stopped hovering over one of the radio buttons that implements mouseEvent
	 * If a radio button has been selected then the ship stats bars are not updated
	 * Otherwise the ship stats JPanle will hide when the user stops hovering over a radio button. 
	 * 
	 * @param e The component that has been hovered over 
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (!shipSelected) {
			shipStats.setVisible(false);
		}
	}
	
	/**
	 * Enables the accept button and removes the error labels if the user has selected a ship and has entered
	 * a valid name (Only contains letters and having more than 3 characters). Otherwise disables the accept button.
	 */
	private void checkCanContinue() {
		boolean validName = traderNameTextField.getText().matches("[a-zA-Z]{3,}");
		
		// Hide the name requirements text if the input is valid
		nameErrorLB.setText(validName ? null : nameError);
		shipErrorLB.setText(shipSelected ? null : shipNotSelectedError);

		startGame.setEnabled(validName && shipSelected);
	}
	
	void quit() {
		mainMenuFrame.dispose();
    }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
