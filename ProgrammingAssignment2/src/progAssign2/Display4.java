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
	
	//charButtons global for use with action listeners
	private JButton charButtons[] = null;
	private static final int MAX_NUM_OF_CHARS = 5;

	public Display4() throws SQLException
	{
		//Initialize db
//		m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
//		DatabaseMetaData meta = m_dbConn.getMetaData();
//		activateJDBC();
		//Activate GUI
		activateGUI();
	}
	
	/**
	 * Calls methods necessary to draw and show Display 4
	 */
	private void activateGUI()
	{
		JFrame frame = new JFrame();
		
		JPanel mainPanel = generateMainPanel();
		frame.add(mainPanel);
		
		//packs then displays the GUI
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Creates the main panel
	 * @return
	 */
	private JPanel generateMainPanel()
	{
		JPanel main = new JPanel();
		BorderLayout border = new BorderLayout();
		main.setLayout(border);
		
		//Creates grid that will display characters
		//TODO adjust to work with a changing number of characters
		GridLayout charGrid = new GridLayout(5,1);
		JPanel west = new JPanel();
		west.setLayout(charGrid);
		generateCharList(west);
		main.add("West", west);
		
		//Creates main item display area
		JPanel center = new JPanel();
		BorderLayout itemArea = new BorderLayout();
		center.setLayout(itemArea);
		
		
		return main;
		
		
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
	 * Contains the actions for the events
	 * @param event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
	}
}
