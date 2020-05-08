package progAssign2;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;


public class Display2 extends JFrame implements ActionListener{

	/**
	 * Creates all buttons and labels
	 */
	private static final long serialVersionUID = 1L;
	JButton update = new JButton("Update");
	JLabel answer = new JLabel("");
	JFrame c = new JFrame();
	JButton c1 = new JButton("Dio");
	JButton c2 = new JButton("Joseph");
	JButton c3 = new JButton("Jonathan");
	JButton c4 = new JButton("Jotaro");
	JButton c5 = new JButton("Speedwagon");
	JLabel strength = new JLabel("Strength: 0");
	JLabel stamina = new JLabel("Stamina: 0");
	JLabel dmgReduction = new JLabel("Damage Reduction: 0");
	JLabel maxHP = new JLabel("Max HP: 0");
	JLabel curHP = new JLabel("Current HP: 0");
	String name;
	
	//connect to database
	  public boolean activateJDBC()
	    {
	        try
	        {
	            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	        }
	        catch (SQLException sqle)
	        {
	            sqle.printStackTrace();
	        }

	        return true;
	    }
	    
	    public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_23";
	    public static final String LOGIN_NAME = "csc371_23";
	    public static final String PASSWORD = "Password23";
	    // Make sure and use the java.sql imports.
	    
	    protected Connection m_dbConn = null;
	    
	   /** 
	    * Creates a connection to the database that you can then send commands to.
	    */
	    public void createConnection() throws Exception{
	    	m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
	    }
	
	    //start the display
	public Display2 (){
		
		super("Player 1"); setBounds(100,100,150,350);
		
		JPanel pane = new JPanel();
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container con = this.getContentPane(); 
	    con.add(pane); 
	    //adds the buttons and action listeners
	    pane.add(c1); c1.requestFocus();
	    c1.addActionListener(this); 
	    pane.add(c2); c2.requestFocus();
	    c2.addActionListener(this); 
	    pane.add(c3); c3.requestFocus();
	    c3.addActionListener(this); 
	    pane.add(c4); c4.requestFocus();
	    c4.addActionListener(this); 
	    pane.add(c5); c5.requestFocus();
	    c5.addActionListener(this);
	    c.add(update); update.requestFocus();
		update.addActionListener(this);
	    pane.add(answer);
	    setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		//checks to see which character was selected
		if(source == c1) {
			c.setLayout(null);;
			c.setTitle("Dio's Stats");
			c.setSize(300,300);
			c.setVisible(true);
			c.setResizable(false);
			
			strength.setBounds(0, 0, 100, 20);
			stamina.setBounds(0, 50, 100, 20);
			dmgReduction.setBounds(0, 100, 150, 20);
			maxHP.setBounds(0, 150, 100, 20);
			curHP.setBounds(0, 200, 100, 20);
			update.setBounds(100, 250, 100, 20);
			
			c.add(strength);
			c.add(stamina);
			c.add(dmgReduction);
			c.add(maxHP);
			c.add(curHP);
			
			name = getName1();
			
		}
		
		if(source == c2) {
			c.setLayout(null);;
			c.setTitle("Joseph's Stats");
			c.setSize(300,300);
			c.setVisible(true);
			c.setResizable(false);
			
			strength.setBounds(0, 0, 100, 20);
			stamina.setBounds(0, 50, 100, 20);
			dmgReduction.setBounds(0, 100, 150, 20);
			maxHP.setBounds(0, 150, 100, 20);
			curHP.setBounds(0, 200, 100, 20);
			update.setBounds(100, 250, 100, 20);
			
			c.add(strength);
			c.add(stamina);
			c.add(dmgReduction);
			c.add(maxHP);
			c.add(curHP);
			
			name = getName2();
		}
		
		if(source == c3) {
			c.setLayout(null);;
			c.setTitle("Jonathan's Stats");
			c.setSize(300,300);
			c.setVisible(true);
			c.setResizable(false);
			
			strength.setBounds(0, 0, 100, 20);
			stamina.setBounds(0, 50, 100, 20);
			dmgReduction.setBounds(0, 100, 150, 20);
			maxHP.setBounds(0, 150, 100, 20);
			curHP.setBounds(0, 200, 100, 20);
			update.setBounds(100, 250, 100, 20);
			
			c.add(strength);
			c.add(stamina);
			c.add(dmgReduction);
			c.add(maxHP);
			c.add(curHP);
			
			name = getName3();
		}
		
		if(source == c4) {
			c.setLayout(null);;
			c.setTitle("Jotaro's Stats");
			c.setSize(300,300);
			c.setVisible(true);
			c.setResizable(false);
			
			strength.setBounds(0, 0, 100, 20);
			stamina.setBounds(0, 50, 100, 20);
			dmgReduction.setBounds(0, 100, 150, 20);
			maxHP.setBounds(0, 150, 100, 20);
			curHP.setBounds(0, 200, 100, 20);
			update.setBounds(100, 250, 100, 20);
			
			c.add(strength);
			c.add(stamina);
			c.add(dmgReduction);
			c.add(maxHP);
			c.add(curHP);
			
			name = getName4();
		}
		
		if(source == c5) {
			c.setLayout(null);;
			c.setTitle("Speedwagon's Stats");
			c.setSize(300,300);
			c.setVisible(true);
			c.setResizable(false);
			
			strength.setBounds(0, 0, 100, 20);
			stamina.setBounds(0, 50, 100, 20);
			dmgReduction.setBounds(0, 100, 150, 20);
			maxHP.setBounds(0, 150, 100, 20);
			curHP.setBounds(0, 200, 100, 20);
			update.setBounds(100, 250, 100, 20);
			
			c.add(strength);
			c.add(stamina);
			c.add(dmgReduction);
			c.add(maxHP);
			c.add(curHP);
			
			name = getName5();
			
		}
		
		//updates stats based on characters
		if(source == update) {
			try {
				strength.setText("Strength: " + getStrength(name));
				stamina.setText("Stamina: " + getStamina(name));
				dmgReduction.setText("Damage Reduction: " + getDmgReduction(name));
				maxHP.setText("Max HP: " + getMaxHP(name));
				curHP.setText("Current HP: " + getCurHP(name));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
	
	
	//gets strength of character from database
	public ResultSet getStrength(String name) throws SQLException {
		this.name = name;
		String select = new String("SELECT Strength FROM PLAYER_CHAR" +
							"WHERE PName=" + name);
		PreparedStatement stmt = (PreparedStatement) m_dbConn.prepareStatement(select);
		ResultSet rs = stmt.executeQuery(select);
		
		return rs;
	}
	
	//gets stamina of character from database
	public ResultSet getStamina(String name) throws SQLException {
		this.name = name;
		String select = new String("SELECT Stamina FROM PLAYER_CHAR" +
							"WHERE PName=" + name);
		PreparedStatement stmt = (PreparedStatement) m_dbConn.prepareStatement(select);
		ResultSet rs = stmt.executeQuery(select);
		
		return rs;
	}
	
	//gets damage reduction of character from database
	public ResultSet getDmgReduction(String name) throws SQLException {
		this.name = name;
		String select = new String("SELECT Damage reduction FROM PLAYER_CHAR" +
							"WHERE PName=" + name);
		PreparedStatement stmt = (PreparedStatement) m_dbConn.prepareStatement(select);
		ResultSet rs = stmt.executeQuery(select);
		
		return rs;
	}
	
	//gets max HP of character from database
	public ResultSet getMaxHP(String name) throws SQLException {
		this.name = name;
		String select = new String("SELECT Max_HP FROM PLAYER_CHAR" +
							"WHERE PName=" + name);
		PreparedStatement stmt = (PreparedStatement) m_dbConn.prepareStatement(select);
		ResultSet rs = stmt.executeQuery(select);
		
		return rs;
	}
	
	//gets current HP of character from database
	public ResultSet getCurHP(String name) throws SQLException {
		this.name = name;
		String select = new String("SELECT Current_HP FROM PLAYER_CHAR" +
							"WHERE PName=" + name);
		PreparedStatement stmt = (PreparedStatement) m_dbConn.prepareStatement(select);
		ResultSet rs = stmt.executeQuery(select);
		
		return rs;
	}
	
	
	//calls stored procedure
	public void testCallStoredProcedure() throws Exception{
		String sql = "CALL char_info(?)";
		CallableStatement stmt = (CallableStatement) m_dbConn.prepareCall(sql);
		stmt.setString(1, "PName");
		ResultSet set = stmt.getResultSet();
		while(set.next()) {
			String data = set.getString("Strength, Stamina, Max_HP, Current_HP");
		}
	}
	
	public String getName1() {
		return "Dio";
	}
	
	public String getName2() {
		return "Joseph";
	}
	
	public String getName3() {
		return "Jonathan";
	}
	
	public String getName4() {
		return "Jotaro";
	}
	
	public String getName5() {
		return "Speedwagon";
	}

	
	public static void main(String[] args) throws Exception {
		
		new Display2();
	}
}
