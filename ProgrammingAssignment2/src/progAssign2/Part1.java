package progAssign2;

import java.sql.*;

/**
 *  Class used to create the database based
 *  off of work from Homework 3 Part 2
 *  
 * @author Jared Sawyers
 *
 */
public class Part1 {
	
	public static void main(String args[]) throws Exception
	{
		Part1 p1 = new Part1();
		p1.createDatabase();
	}
	
	public void createDatabase() throws Exception{
		activateJDBC();
		createConnection();
		createTableManager();
		createTableManagerPrivileges();
		createTableModerator();
		createTableModeratorPrivileges();
		createTablePlayer();
		createTableLocation();
		createTableExit();
		createTableCreature();
		alterTableCreature();
		createTableCreatureTerrain();
		createTablePlayerChar();
		createTableItem();
		createTableArmor();
		createTableGenericItem();
		createTableContainer();
		alterTableItem();
		createTableAbility();
		createTableWeapon();
	}
	
	/**
     * This is the recommended way to activate the JDBC drivers, but is
     * only setup to work with one specific driver.  Setup to work with
     * a MySQL JDBC driver.
     *
     * If the JDBC Jar file is not in your build path this will not work.
     * I have the Jar file posted in D2L.
     * 
     * @return Returns true if it successfully sets up the driver.
     */
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
    
    /**
     * Creates the manager table for the DB
     * to track managers
     * @throws Exception
     */
    public void createTableManager() throws Exception{
    	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE MANAGER" +
							"(Login VARCHAR(16)	Not Null, " +
							"Password VARCHAR(64) Not Null," +
							"Email	VARCHAR(64) Not Null," +
							"PRIMARY KEY(Login));";
		
		statement.executeUpdate(createTable);
		statement.close();
    }
    
    /**
     * Creates the manager privileges for the DB
     * to track managers
     * @throws Exception
     */
    public void createTableManagerPrivileges() throws Exception{
    	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE MANAGER_PRIVILEGES" +
							"(ManLogin 		VARCHAR(16)	 Not Null, " +
							"ManPrivilege 	VARCHAR(255) Not Null," +
							"PRIMARY KEY(ManPrivilege, ManLogin)" +
							"FOREIGN KEY(ManLogin) REFERENCES (MANAGER (LOGIN)));";
		
		statement.executeUpdate(createTable);
		statement.close();
    }
    
    /**
     * Creates the moderator table for the DB
     * to track moderators
     * @throws Exception
     */
    public void createTableModerator() throws Exception{
    	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE MODERATOR" +
							"(Login 	VARCHAR(16)	Not Null, " +
							"Password 	VARCHAR(64) Not Null," +
							"E-mail		VARCHAR(64) Not Null," +
							"PRIMARY KEY(Login));";
		
		statement.executeUpdate(createTable);
		statement.close();
    }
    
    /**
     * Creates the moderator privileges for the DB
     * to track moderator privileges
     * @throws Exception
     */
    public void createTableModeratorPrivileges() throws Exception{
    	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE MODERATOR_PRIVILEGES" +
							"(ModLogin 		VARCHAR(16)	 Not Null, " +
							"ModPrivilege 	VARCHAR(255) Not Null," +
							"PRIMARY KEY(ModPrivilege, ModLogin)," +
							"FOREIGN KEY(ModLogin) REFERENCES (MODERATOR (LOGIN)));";
		
		statement.executeUpdate(createTable);
		statement.close();
    }
    
    /**
     * Creates the player table for the DB
     * to track players
     * @throws Exception
     */
    public void createTablePlayer() throws Exception{
    	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE PLAYER" +
							"(Login 	VARCHAR(16)	Not Null, " +
							"Password 	VARCHAR(64) Not Null," +
							"E-mail		VARCHAR(64) Not Null," +
							"PRIMARY KEY(Login));";
		
		statement.executeUpdate(createTable);
		statement.close();
    }
    
    /**
     * Creates the location table for the DB
     * to track locations
     * @throws Exception
     */
    public void createTableLocation() throws Exception{
    	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE LOCATION" +
							"(LocID 	CHAR(16)	Not Null, " +
							"LocType 	VARCHAR(64) ," +
							"Size		INT ," +
							"PRIMARY KEY(LocID));";
		
		statement.executeUpdate(createTable);
		statement.close();
    }
    
    /**
     * Creates the exit table for the DB
     * to track location exits
     * @throws Exception
     */
    public void createTableExit() throws Exception{
    	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE EXIT" +
							"(LocID 	CHAR(16) Not Null, " +
							"exitLocID 	CHAR(64) Not Null," +
							"Location	VARCHAR(64) ," +
							"PRIMARY KEY(LocID, exitLocID)," +
							"FOREIGN KEY(LocID) REFERENCES(LOCATION (LocID))," +
							"FOREIGN KEY(exitLocID) REFERENCES(LOCATION (LocID)));";
		
		statement.executeUpdate(createTable);
		statement.close();
    }
    
    /**
     * Creates the creature table for the DB
     * to track creatures
     * @throws Exception
     */
    public void createTableCreature() throws Exception{
    	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE CREATURE" +
							"(Strength Int, " +
							"Stamina Int," +
							"Damage_Reduction Int," +
							"CreID CHAR(16) Not Null," +
							"Max_HP INT," +
							"Current_HP Int," +
							"LocID CHAR(16) Not Null," +
							"Likes CHAR(16) Not Null," +
							"Dislikes CHAR(16) Not Null," +
							"PRIMARY KEY(CreID)," +
							"FOREIGN KEY(LocID) REFERENCES (LOCATION (LocID)));";
		
		statement.executeUpdate(createTable);
		statement.close();
    }
    
    /**
     * Alters the creature table to add a foreign key
     * that will point to a different entry in the creature table.
     * @throws Exception
     */
    public void alterTableCreature() throws Exception{
    	Statement statement = m_dbConn.createStatement();
    	
    	String alterTable = "ALTER TABLE CREATURE" +
    						"(ADD FOREIGN KEY(Likes) REFERNCES(CREATURE(CreID))" +
    						"ADD FOREIGN KEY(Dislikes) REFERNCES(CREATURE(CreID)));";
    	
    	statement.executeUpdate(alterTable);
    	statement.close();
    }
    
    /**
     * Creates the creature terrain table for the DB
     * to track the locations that a creature can go to
     * @throws Exception
     */
    public void createTableCreatureTerrain() throws Exception{
    	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE CREATURE_TERRAIN" +
							"(CreID 	CHAR(16) Not Null, " +
							"Area 	VARCHAR(64) Not Null," +
							"PRIMARY KEY(CreID, Area)," +
							"FOREIGN KEY(CreID) REFERENCES(CREATURE (CreID)));";
		
		statement.executeUpdate(createTable);
		statement.close();
    }
    
    /**
     * Creates the player character table for the DB
     * to track player characters
     * @throws Exception
     */
    public void createTablePlayerChar() throws Exception{
    	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE PLAYER_CHAR" +
							"(LocID CHAR(16) Not Null," +
							"Strength Int, " +
							"Stamina Int," +
							"Location VARCHAR(64)," +
							"PName VARCHAR(64)," +
							"Max_HP INT," +
							"Current_HP Int," +
							"Player_Login VARCHAR(16) Not Null," +
							"Liked_By CHAR(16) Not Null," +
							"Disliked_By CHAR(16) Not Null," +
							"PRIMARY KEY(PName)," +
							"FOREIGN KEY(Liked_By) REFERENCES CREATURE (CreID)" +
							"FOREIGN KEY(Disliked_By) REFERENCES CREATURE (CreID)" +
							"FOREIGN KEY(Player_Login) REFERENCES PLAYER (Login)" +
							"FOREIGN KEY(LocID) REFERENCES LOCATION (LocID));";
		
		statement.executeUpdate(createTable);
		statement.close();
    }
    
    /**
     * Creates the item table for the DB
     * to track the locations that a creature can go to
     * @throws Exception
     */
    public void createTableItem() throws Exception{
    	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE ITEM" +
							"(ItemID CHAR(16) Not Null, " +
							"ConID CHAR(16)," +
							"WornItem VARCHAR(64)," +
							"PossessedItem VARCHAR(64)," +
							"LocID CHAR(16)," +
							"PRIMARY KEY(ItemID)," +
							"FOREIGN KEY(WornItem) REFERENCES PLAYER_CHAR (PName)," +
							"FOREIGN KEY(PossessedItem) REFERENCES PLAYER_CHAR (PName));";
		
		statement.executeUpdate(createTable);
		statement.close();
    }
    
    /**
    * Creates the armor table for the DB
    * to track the armor items
    * @throws Exception
    */
   public void createTableArmor() throws Exception{
   	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE ARMOR" +
							"(ArmorID CHAR(16) Not Null, " +
							"Damage_Reduction INT," +
							"Slot VARCHAR(64)," +
							"Volume INT," +
							"Mass Int," +
							"ItemID CHAR(16) Not Null, " +
							"PRIMARY KEY(ArmorID)," +
							"FOREIGN KEY(ItemID) REFERENCES ITEM (ItemID));";
		
		statement.executeUpdate(createTable);
		statement.close();
   }
   
   /**
    * Creates the generic item table for the DB
    * to track the generic items
    * @throws Exception
    */
   public void createTableGenericItem() throws Exception{
   	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE GENERIC_ITEM" +
							"(genItemID CHAR(16) Not Null, " +
							"Volume INT)," +
							"Mass INT," +
							"ItemID CHAR(16)," +
							"PRIMARY KEY(genItemID)," +
							"FOREIGN KEY(ItemID) REFERENCES ITEM (ItemID));";
		
		statement.executeUpdate(createTable);
		statement.close();
   }
   
   /**
    * Creates the container table for the DB
    * to track the containers
    * @throws Exception
    */
   public void createTableContainer() throws Exception{
   	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE CONTAINER" +
							"(ConID CHAR(16) Not Null, " +
							"Mass INT," +
							"Volume Int," +
							"Mass_Limit INT," +
							"Volume_Limit INT," +
							"ItemID CHAR(16)," +
							"PRIMARY KEY(ConID)," +
							"FOREIGN KEY(ItemID) REFERENCES ITEM (ItemID));";
		
		statement.executeUpdate(createTable);
		statement.close();
   }
   
   /**
    * Alters the item table to add a foreign key
    * that will point to the container table.
    * @throws Exception
    */
   public void alterTableItem() throws Exception{
   	Statement statement = m_dbConn.createStatement();
   	
   	String alterTable = "ALTER TABLE ITEM" +
   						"(ADD FOREIGN KEY(ConID) REFERNCES CONTAINER (ConID));";
   	
   	statement.executeUpdate(alterTable);
   	statement.close();
   }
   
   /**
    * Creates the ability table for the DB
    * to track the abilities
    * @throws Exception
    */
   public void createTableAbility() throws Exception{
   	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE ABILITY" +
							"(AbID CHAR(16) Not Null, " +
							"Effect INT," +
							"Stat_Affected VARCHAR(10)," +
							"Cast_Time Time," +
							"Rate_of_Ocurrence Time," +
							"CreID CHAR(16)," +
							"PRIMARY KEY(AbID)," +
							"FOREIGN KEY(CreID) REFERENCES CREATURE (CreID));";
		
		statement.executeUpdate(createTable);
		statement.close();
   }
   
   /**
    * Creates the weapon table for the DB
    * to track the weapon items
    * @throws Exception
    */
   public void createTableWeapon() throws Exception{
   	Statement statement = m_dbConn.createStatement();
		
		String createTable = "CREATE TABLE WEAPON" +
							"(WeapID CHAR(16) Not Null, " +
							"Volume INT," +
							"Mass INT," +
							"Current_Location VARCHAR(8)," +
							"abID CHAR(16)," +
							"ItemID CHAR(16) Not Null," +
							"PRIMARY KEY(WeapID)," +
							"FOREIGN KEY(ItemID) REFERENCES ITEM (ItemID));";
		
		statement.executeUpdate(createTable);
		statement.close();
   }
}