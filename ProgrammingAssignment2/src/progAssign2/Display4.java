package progAssign2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates display 4 for programming assignment 2
 * @author Gabe Werick
 *
 */
public class Display4 implements ActionListener {
	
	public static void main(String[] args) throws SQLException
	{
		Display4 ds = new Display4();
	}
	
	//SQL Connection stuff
	public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_23";
	public static final String LOGIN_NAME = "csc371_23";
	public static final String PASSWORD = "Password23";
	// Make sure and use the java.sql imports.
	protected static Connection m_dbConn = null;
	protected static DatabaseMetaData meta = null;
	
	//character data
	private JButton charButtons[] = null;
	private static final int MAX_NUM_OF_CHARS = 5;
	private int currentChar = -1;
	
	//player data
	private String playerName = null;
	
	//item data
	private JButton iButtons[] = null;
	private static final int MAX_NUM_OF_ITEMS = 10;
	
	//add and delete buttons
	private JButton add = null;
	private JButton delete = null;
	
	//containers
	private JFrame mainFrame = null;
	private JPanel mainPanel = null;
	private JPanel charPanel = null;
	private JPanel mainItemDisplay = null;
	private JPanel itemPane = null;
	private JPanel buttonPane = null;
	
	//item button
	private JButton generic = null;
	private JButton weapon = null;
	private JButton armor = null;
	private JButton container = null;

	public Display4() throws SQLException
	{
		//Initialize db
//		m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
//		DatabaseMetaData meta = m_dbConn.getMetaData();
//		activateJDBC();
		
		this.playerName = "Bob";
		//Activate GUI
		activateGUI();
	}
	
	/**
	 * Calls methods necessary to draw and show Display 4
	 */
	private void activateGUI()
	{
		mainFrame = new JFrame();
		
		mainFrame.add(generateInitialMainPanel());
		
		//packs then displays the GUI
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	
	/**
	 * Generates the GUI in an initial state
	 * @return
	 */
	private JPanel generateInitialMainPanel()
	{
		mainPanel = new JPanel();
		BorderLayout border = new BorderLayout();
		mainPanel.setLayout(border);
		
		//Creates grid that will display characters
		//TODO adjust to work with a changing number of characters
		GridLayout charGrid = new GridLayout(MAX_NUM_OF_CHARS,1);
		charPanel = new JPanel();
		charPanel.setLayout(charGrid);
		generateCharList(charPanel);
		mainPanel.add("West", charPanel);
		
		//Creates main item display area
		mainItemDisplay = new JPanel();
		BorderLayout itemArea = new BorderLayout();
		mainItemDisplay.setLayout(itemArea);
		
		//Display Player Name
		JLabel label = new JLabel("Player: " + playerName);
		mainItemDisplay.add("North", label);
		
		//creates a blank panel for spacing
		JPanel blank = new JPanel();
		GridLayout grid = new GridLayout(10,1);
		blank.setLayout(grid);
		blank.add(new JLabel("           Please select a character to view their items                       "));
		mainItemDisplay.add("West", blank);
		
		
		mainPanel.add("Center", mainItemDisplay);
		
		
		return mainPanel;
		
	}
	
	/**
	 * Creates the main panel with items for the selected character
	 * @return
	 */
	private JPanel redrawMainPanel()
	{
		mainPanel = new JPanel();
		BorderLayout border = new BorderLayout();
		mainPanel.setLayout(border);
		
		//Creates grid that will display characters
		//TODO adjust to work with a changing number of characters
		GridLayout charGrid = new GridLayout(MAX_NUM_OF_CHARS,1);
		JPanel west = new JPanel();
		west.setLayout(charGrid);
		generateCharList(west);
		mainPanel.add("West", west);
		
		//TODO Update once database becomes usable to query for the right items
		//Creates main item display area
		mainItemDisplay = new JPanel();
		BorderLayout itemArea = new BorderLayout();
		mainItemDisplay.setLayout(itemArea);
		
		//Display Player Name
		JLabel label = new JLabel("Player: " + playerName);
		mainItemDisplay.add("North", label);
		
		generateItemLayout(mainItemDisplay);
		generateButtonsToEast(mainItemDisplay);
		mainPanel.add("Center", mainItemDisplay);
		
		
		return mainPanel;
		
		
	}
	
	/**
	 * Creates the item add and item delete buttons
	 * @param pane
	 */
	private void generateButtonsToEast(JPanel pane)
	{
		buttonPane = new JPanel();
		GridLayout grid = new GridLayout(5,1);
		buttonPane.setLayout(grid);
		
		add = new JButton("Add");
		add.addActionListener(this);
		delete = new JButton("Delete");
		delete.addActionListener(this);
		
		buttonPane.add(new JLabel(""));
		buttonPane.add(add);
		buttonPane.add(new JLabel(""));
		buttonPane.add(delete);
		buttonPane.add(new JLabel(""));
		
		pane.add("East", buttonPane);
	}
	
	/**
	 * Generates the main item display
	 * @param pane
	 */
	private void generateItemLayout(JPanel pane)
	{
		
		itemPane = new JPanel();
		GridLayout grid = new GridLayout(MAX_NUM_OF_ITEMS, 1);
		itemPane.setLayout(grid);
		
		//TODO Update with SQL code
		//generate item pane
		String[] items = {"Clacky Balls", "Lucky Sword", "Plucky Sword", "Bag of Holding"};
		iButtons = new JButton[MAX_NUM_OF_ITEMS];
		
		for(int i=0; i< items.length; i++)
		{
			iButtons[i] = new JButton(items[i]);
			iButtons[i].addActionListener(this);
			itemPane.add("West", iButtons[i]);
		}
		
		pane.add("West",itemPane);
		
	}
	
	/**
	 * When called will generate the list of characters owned by a player
	 * @param pane the panel that needs worked on
	 */
	private void generateCharList(JPanel pane)
	{
		//TODO Update with SQL code to query the db for characters
		String[] chars = {"Dio", "Jonathan", "Jotaro", "Joseph", "Speedwagon"};
		charButtons = new JButton[MAX_NUM_OF_CHARS];
		//Loop through and add buttons to pane with the names of chars
		for(int i = 0; i < chars.length; i++)
		{
			charButtons[i] = new JButton(chars[i]);
			charButtons[i].addActionListener(this);
			pane.add(charButtons[i]);
		}
	}
	
	/**
	 * Activates database
	 * @return
	 */
	private boolean activateJDBC() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return true;
	}
	
	/**
	 * Will redraw the displayed items owned by the correct character
	 */
	private void updateFrameWithItems(int charNum)
	{
		mainFrame.remove(mainPanel);
		mainFrame.add(redrawMainPanel());
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Updates the item panel to display buttons to select what item to add
	 */
	private void addItemToChar()
	{
		mainItemDisplay.remove(itemPane);
		itemPane = new JPanel();
		GridLayout grid = new GridLayout(4,1);
		itemPane.setLayout(grid);
		
		generic = new JButton("Generic Item");
		generic.addActionListener(this);
		armor = new JButton("Armor");
		generic.addActionListener(this);
		weapon = new JButton("Weapon");
		weapon.addActionListener(this);
		container = new JButton("Bag");
		container.addActionListener(this);
		
		itemPane.add(generic);
		itemPane.add(armor);
		itemPane.add(weapon);
		itemPane.add(container);
		mainItemDisplay.add("West", itemPane);
		
		mainItemDisplay.revalidate();
		mainItemDisplay.repaint();
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Adjusts button panel to let you enter the name of an item you want deleted
	 */
	private void deleteItem()
	{
		mainItemDisplay.remove(buttonPane);
		buttonPane = new JPanel();
		GridLayout grid = new GridLayout(3,1);
		buttonPane.setLayout(grid);
		
		
	}

	/**
	 * Contains the actions for the events
	 * @param event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == charButtons[0])
		{
			updateFrameWithItems(0);
			currentChar = 0;
		}
		if(event.getSource() == charButtons[1])
		{
			updateFrameWithItems(1);
			currentChar = 1;
		}
		if(event.getSource() == charButtons[2])
		{
			updateFrameWithItems(2);
			currentChar = 2;
		}
		if(event.getSource() == charButtons[3])
		{
			updateFrameWithItems(3);
			currentChar = 3;
		}
		if(event.getSource() == charButtons[4])
		{
			updateFrameWithItems(4);
			currentChar = 4;
		}
		if(event.getSource() == add)
		{
			addItemToChar();
		}
		if(event.getSource() == delete)
		{
			deleteItem();
		}
	}
}
