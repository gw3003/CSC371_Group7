package progAssign2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	private String[] chars = null;
	private static final int MAX_NUM_OF_CHARS = 5;
	private int currentChar = -1;
	
	//player data
	private String playerName = null;
	
	//item data
	private static final int MAX_NUM_OF_ITEMS = 10;
	private int editedItem = 0;
	
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
	private JButton generateItem = null;
	
	//Text fields
	private JTextField deleteField = null;
	private JTextField mass = null;
	private JTextField volume = null;
	private JTextField id = null;
	private JTextField fId1 = null;
	private JTextField val1 = null;
	private JTextField val2 = null;
	
	//Setup
	private JTextField player = null;

	public Display4() throws SQLException
	{
		//Initialize db
		m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
		DatabaseMetaData meta = m_dbConn.getMetaData();
		activateJDBC();
		
		//Activate GUI
		activateGUI();
	}
	
	/**
	 * Calls methods necessary to draw and show Display 4
	 */
	private void activateGUI()
	{
		mainFrame = new JFrame();
		
		//Quick frame to get the desired player name to get characters for
		JPanel startPane = new JPanel();
		GridLayout grid = new GridLayout(2,1);
		startPane.setLayout(grid);
		startPane.add(new JLabel("Enter player you wish to see"));
		player = new JTextField();
		player.addActionListener(this);
		startPane.add(player);
		mainFrame.add(startPane);
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
		GridLayout grid = new GridLayout(MAX_NUM_OF_ITEMS+1, 1);
		itemPane.setLayout(grid);
		
		//generate item pane
		String[] items;
		try {
			items = getCharItems();
			JLabel iLabel[] = new JLabel[MAX_NUM_OF_ITEMS];
			
			itemPane.add(new JLabel(chars[currentChar] + "'s items"));
			
			for(int i=0; i< items.length; i++)
			{
				iLabel[i] = new JLabel(items[i]);
				itemPane.add("West", iLabel[i]);
			}
			
			pane.add("West",itemPane);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Queries the database to get an items a character has
	 * @throws SQLException 
	 */
	private String[] getCharItems() throws SQLException
	{
		String[] items;
		
		String sql = "CALL getCharItems(?)";
		CallableStatement stmt = m_dbConn.prepareCall(sql);
		try {
			stmt.setString(1, chars[currentChar]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stmt.execute();
		ResultSet set = stmt.getResultSet();
		ArrayList<String> theseItems = new ArrayList<String>();
		while(set.next())
		{
			theseItems.add(set.getString("ItemID"));
		}
		int size = theseItems.size();
		items = new String[size];
		
		for(int i = 0; i < size; i++)
		{
			items[i] = theseItems.get(i);
		}
		
		return items;
	}
	
	/**
	 * When called will generate the list of characters owned by a player
	 * @param pane the panel that needs worked on
	 */
	private void generateCharList(JPanel pane)
	{
		//TODO Update with SQL code to query the db for characters
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
		armor.addActionListener(this);
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
		
		buttonPane.add(new JLabel("Enter exact name of item to delete"));
		buttonPane.add(new JLabel("If no match is found nothing will be deleted"));
		
		deleteField = new JTextField();
		deleteField.addActionListener(this);
		buttonPane.add(deleteField);
		
		mainItemDisplay.add("East", buttonPane);
		
		mainItemDisplay.revalidate();
		mainItemDisplay.repaint();
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Resets add/delete buttons + calls sql delete statement
	 */
	private void executeDelete(String deleteTarget)
	{
		//Daisy chains through try/catch blocks till it can successfully delete
		String sql = "CALL deleteGenItem(?)";
		try {
			CallableStatement stmt = m_dbConn.prepareCall(sql);
			stmt.setString(1, deleteTarget);
			stmt.execute();
			sql = "CALL deleteItem(?)";
			stmt = m_dbConn.prepareCall(sql);
			stmt.setString(1, deleteTarget);
			stmt.execute();
			
		} catch (SQLException e) {
			
			try {
				sql = "CALL deleteArmor(?)";
				CallableStatement stmt = m_dbConn.prepareCall(sql);
				stmt.setString(1, deleteTarget);
				stmt.execute();
				sql = "CALL deleteItem(?)";
				stmt = m_dbConn.prepareCall(sql);
				stmt.setString(1, deleteTarget);
				stmt.execute();
			} catch (SQLException e1) {
				try {
					sql = "CALL deleteWeapon(?)";
					CallableStatement stmt = m_dbConn.prepareCall(sql);
					stmt.setString(1, deleteTarget);
					stmt.execute();
					sql = "CALL deleteItem(?)";
					stmt = m_dbConn.prepareCall(sql);
					stmt.setString(1, deleteTarget);
					stmt.execute();
				} catch (SQLException e2) {
					try {
						sql = "CALL deleteContainer(?)";
						CallableStatement stmt = m_dbConn.prepareCall(sql);
						stmt.setString(1, deleteTarget);
						stmt.execute();
						sql = "CALL deleteItem(?)";
						stmt = m_dbConn.prepareCall(sql);
						stmt.setString(1, deleteTarget);
						stmt.execute();
					} catch (SQLException e3) {
						e3.printStackTrace();
					}
				}
			}
			
		}
		//Reset button/item fields
		mainItemDisplay.remove(buttonPane);
		mainItemDisplay.remove(itemPane);
		
		generateItemLayout(mainItemDisplay);
		generateButtonsToEast(mainItemDisplay);
		
		mainItemDisplay.revalidate();
		mainItemDisplay.repaint();
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Shows display to add a generic item
	 */
	private void buildGenItem()
	{
		mainItemDisplay.remove(buttonPane);
		buttonPane = new JPanel();
		GridLayout grid = new GridLayout(8,1);
		buttonPane.setLayout(grid);
		
		buttonPane.add(new JLabel("Enter stats for Generic Item"));
		
		buttonPane.add(new JLabel("Enter item name"));
		id = new JTextField();
		buttonPane.add(id);
		
		buttonPane.add(new JLabel("Enter mass"));
		mass = new JTextField();
		buttonPane.add(mass);
		
		buttonPane.add(new JLabel("Enter volume"));
		volume = new JTextField();
		buttonPane.add(volume);
		
		generateItem = new JButton("Create Item");
		generateItem.addActionListener(this);
		buttonPane.add(generateItem);
		
		mainItemDisplay.add(buttonPane);
		mainItemDisplay.revalidate();
		mainItemDisplay.repaint();
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	/**
	 * Builds the armor item frame
	 */
	private void buildArmorItem()
	{
		mainItemDisplay.remove(buttonPane);
		buttonPane = new JPanel();
		GridLayout grid = new GridLayout(12,1);
		buttonPane.setLayout(grid);
		
		buttonPane.add(new JLabel("Enter stats for Armor Item"));
		
		buttonPane.add(new JLabel("Enter item name"));
		id = new JTextField();
		buttonPane.add(id);
		
		buttonPane.add(new JLabel("Enter weight"));
		mass = new JTextField();
		buttonPane.add(mass);
		
		buttonPane.add(new JLabel("Enter volume"));
		volume = new JTextField();
		buttonPane.add(volume);
		
		buttonPane.add(new JLabel("Enter Damage Reduction"));
		val1 = new JTextField();
		buttonPane.add(val1);
		
		
		buttonPane.add(new JLabel("Enter Slot"));
		val2 = new JTextField();
		buttonPane.add(val2);
		
		generateItem = new JButton("Create Item");
		generateItem.addActionListener(this);
		buttonPane.add(generateItem);
		
		mainItemDisplay.add(buttonPane);
		mainItemDisplay.revalidate();
		mainItemDisplay.repaint();
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Allows for a weapon item to be created
	 */
	private void buildWeaponItem()
	{
		mainItemDisplay.remove(buttonPane);
		buttonPane = new JPanel();
		GridLayout grid = new GridLayout(12,1);
		buttonPane.setLayout(grid);
		
		buttonPane.add(new JLabel("Enter stats for Weapon Item"));
		
		buttonPane.add(new JLabel("Enter item name"));
		id = new JTextField();
		buttonPane.add(id);
		
		buttonPane.add(new JLabel("Enter weight"));
		mass = new JTextField();
		buttonPane.add(mass);
		
		buttonPane.add(new JLabel("Enter volume"));
		volume = new JTextField();
		buttonPane.add(volume);
		
		buttonPane.add(new JLabel("Enter associated ability"));
		fId1 = new JTextField();
		buttonPane.add(fId1);
		
		buttonPane.add(new JLabel("Enter where weapon is currently held"));
		val1 = new JTextField();
		buttonPane.add(val1);
		
		generateItem = new JButton("Create Item");
		generateItem.addActionListener(this);
		buttonPane.add(generateItem);
		
		mainItemDisplay.add(buttonPane);
		mainItemDisplay.revalidate();
		mainItemDisplay.repaint();
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Sets layout for building a container item
	 */
	private void buildContainerItem()
	{
		mainItemDisplay.remove(buttonPane);
		buttonPane = new JPanel();
		GridLayout grid = new GridLayout(12,1);
		buttonPane.setLayout(grid);
		
		buttonPane.add(new JLabel("Enter stats for Container Item"));
		
		buttonPane.add(new JLabel("Enter item name"));
		id = new JTextField();
		buttonPane.add(id);
		
		buttonPane.add(new JLabel("Enter weight"));
		mass = new JTextField();
		buttonPane.add(mass);
		
		buttonPane.add(new JLabel("Enter volume"));
		volume = new JTextField();
		buttonPane.add(volume);
		
		buttonPane.add(new JLabel("Enter maximum weight"));
		val1 = new JTextField();
		buttonPane.add(val1);
		
		buttonPane.add(new JLabel("Enter maximum volume"));
		val2 = new JTextField();
		buttonPane.add(val2);
		
		generateItem = new JButton("Create Item");
		generateItem.addActionListener(this);
		buttonPane.add(generateItem);
		
		mainItemDisplay.add(buttonPane);
		mainItemDisplay.revalidate();
		mainItemDisplay.repaint();
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Will attempt to add an item to database
	 * then revert displays back to normal
	 * @throws SQLException 
	 */
	private void generateItem() throws SQLException
	{
		String isql = "CALL addItem(?,?,?)";
		CallableStatement stmt = m_dbConn.prepareCall(isql);
		stmt.setString(1, id.getText());
		String sql = "";
		
		switch (editedItem) {
		case 1:
			//Add item
			stmt.setString(2, null);
			stmt.setString(3, chars[currentChar]);
			stmt.execute();
			//add generic item
			sql = "CALL addGenItem(?,?,?)";
			stmt = m_dbConn.prepareCall(sql);
			stmt.setString(1, id.getText());
			stmt.setInt(2, Integer.parseInt(volume.getText()));
			stmt.setInt(3, Integer.parseInt(mass.getText()));
			stmt.execute();
			
			break;
		case 2:
			//Add item
			stmt.setString(2, chars[currentChar]);
			stmt.setString(3, null);
			stmt.execute();
			
			sql = "CALL addArmor(?,?,?,?,?)";
			stmt = m_dbConn.prepareCall(sql);
			stmt.setString(1, id.getText());
			stmt.setInt(2, Integer.parseInt(volume.getText()));
			stmt.setInt(3, Integer.parseInt(mass.getText()));
			stmt.setString(4, val2.getText());
			stmt.setInt(5, Integer.parseInt(val1.getText()));
			stmt.execute();
			break;
		case 3:
			//weapon
			break;
		case 4:
			//container
			break;
		}
		
		//Reset button/item fields
		mainItemDisplay.remove(buttonPane);
		mainItemDisplay.remove(itemPane);
				
		generateItemLayout(mainItemDisplay);
		generateButtonsToEast(mainItemDisplay);
				
		mainItemDisplay.revalidate();
		mainItemDisplay.repaint();
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Queries database to get the list of characters for a player
	 * @param targetPlayer
	 * @throws SQLException 
	 */
	private void getCharList(String targetPlayer) throws SQLException
	{
		String sql = "CALL getCharList(?)";
		CallableStatement stmt = m_dbConn.prepareCall(sql);
		stmt.setString(1, targetPlayer);
		stmt.execute();
		ResultSet set = stmt.getResultSet();
		ArrayList<String> theseChars = new ArrayList<String>();
		while(set.next())
		{
			theseChars.add(set.getString("PName"));
		}
		int size = theseChars.size();
		chars = new String[size];
		
		for(int i = 0; i < size; i++)
		{
			chars[i] = theseChars.get(i);
		}
		
	}
	
	/**
	 * Contains the actions for the events
	 * @param event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == player)
		{
			playerName = player.getText();
			mainFrame = new JFrame();
			try {
				getCharList(player.getText());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mainFrame.add(generateInitialMainPanel());
			mainFrame.revalidate();
			mainFrame.repaint();
			mainFrame.pack();
			mainFrame.setVisible(true);
		}
		if(event.getSource() == charButtons[0])
		{
			currentChar = 0;
			updateFrameWithItems(0);
		}
		if(event.getSource() == charButtons[1])
		{
			currentChar = 1;
			updateFrameWithItems(1);
		}
		if(event.getSource() == charButtons[2])
		{
			currentChar = 2;
			updateFrameWithItems(2);
		}
		if(event.getSource() == charButtons[3])
		{
			currentChar = 3;
			updateFrameWithItems(3);
		}
		if(event.getSource() == charButtons[4])
		{
			currentChar = 4;
			updateFrameWithItems(4);
		}
		if(event.getSource() == add)
		{
			addItemToChar();
		}
		if(event.getSource() == delete)
		{
			deleteItem();
		}
		if(event.getSource() == deleteField)
		{
			executeDelete(deleteField.getText());
		}
		if(event.getSource() == generic)
		{
			editedItem = 1;
			buildGenItem();
		}
		if(event.getSource() == armor)
		{
			editedItem = 2;
			buildArmorItem();
		}
		if(event.getSource() == weapon)
		{
			editedItem = 3;
			buildWeaponItem();
		}
		if(event.getSource() == container)
		{
			editedItem = 4;
			buildContainerItem();
		}
		if(event.getSource() == generateItem)
		{
			try {
				generateItem();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}