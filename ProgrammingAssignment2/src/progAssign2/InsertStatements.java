package progAssign2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Gabe Werick
 * 
 * This class has insert statements to add data to tables programming assignment2
 *
 */
public class InsertStatements {
	
	public static void main(String[] args) throws SQLException
	{
		InsertStatements in = new InsertStatements();
	}
	
	public InsertStatements() throws SQLException
	{
		//Initialize db
		m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
		DatabaseMetaData meta = m_dbConn.getMetaData();
		activateJDBC();
		
		//populate tables
		System.out.println("Commencing table population");
		
		//populateManager();
		populateModerator();
		populateSpecialAbilities();
		populatePlayers();
		populateLocation();
		populateExits();
		populateCreatures();
		populateAreas();
		populatePlayerChar();
		populateItem();
		populateArmor();
		populateGenericItem();
		populateContainer();
		populateAbility();
		populateWeapon();
		
		System.out.println("Complete!");
	}
	
	public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371_23";
	public static final String LOGIN_NAME = "csc371_23";
	public static final String PASSWORD = "Password23";
	// Make sure and use the java.sql imports.
	protected static Connection m_dbConn = null;
	protected static DatabaseMetaData meta = null;
	
	/**
	 * This is the recommended way to activate the JDBC drivers, but is only setup
	 * to work with one specific driver. Setup to work with a MySQL JDBC driver.
	 *
	 * If the JDBC Jar file is not in your build path this will not work. I have the
	 * Jar file posted in D2L.
	 * 
	 * @return Returns true if it successfully sets up the driver.
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
	 * Populate manager table with 5 values
	 * @throws SQLException
	 */
	private void populateManager() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		String sql = "INSERT INTO MANAGER(Login, Password, Email) VALUES"
				+ "('manager0','Password0','NotAManger0@ship.edu'),"
				+ "('manager2', 'Password2', 'NotAManger2@ship.edu'),"
				+ "('manager3', 'Password3', 'NotAManger3@ship.edu'),"
				+ "('manager4', 'Password4', 'NotAManger4@ship.edu'),"
				+ "('manager5', 'Password5', 'NotAManger5@ship.edu');";
		state.execute(sql);
	}
	
	/**
	 * Populate moderate table with 5 values
	 * @throws SQLException
	 */
	private void populateModerator() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		String sql = "INSERT INTO MODERATOR(login, password, email)	VALUES"
				+ "('moderator1', 'Password1', 'NotAModerator1@ship.edu'),"
				+ "('moderator2', 'Password2', 'NotAModerator2@ship.edu'),"
				+ "('moderator3', 'Password3', 'NotAModerator3@ship.edu'),"
				+ "('moderator4', 'Password4', 'NotAModerator4@ship.edu'),"
				+ "('moderator5', 'Password5', 'NotAModerator5@ship.edu');";
		state.execute(sql);
		
	}
	
	/**
	 * Populate special abilities for moderators
	 * @throws SQLException
	 */
	private void populateSpecialAbilities() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO SPECIAL_ABILITIES(ModLogin, Special_Ability) VALUES"
				+ "('moderator1', 'WhateverModeratorsSpecialAbilityCanBe?'),"
				+ "('moderator2', 'WhateverModeratorsSpecialAbilityCanBe2?'),"
				+ "('moderator3', 'WhateverModeratorsSpecialAbilityCanBe3?'),"
				+ "('moderator4', 'WhateverModeratorsSpecialAbilityCanBe4?'),"
				+ "('moderator5', 'WhateverModeratorsSpecialAbilityCanBe5?');";
		
		state.execute(sql);

	}
	
	/**
	 * Populate players with data
	 * @throws SQLException
	 */
	private void populatePlayers() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO PLAYER(login, password, email)VALUES"
				+ "('player1', 'Password1', 'NotAPlayer1@ship.edu'),"
				+ "('player2', 'Password2', 'NotAPlayer2@ship.edu'),"
				+ "('player3', 'Password3', 'NotAPlayer3@ship.edu'),"
				+ "('player4', 'Password4', 'NotAPlayer4@ship.edu'),"
				+ "('player5', 'Password5', 'NotAPlayer5@ship.edu');";
		
		state.execute(sql);

	}
	
	/**
	 * Populate location table
	 * @throws SQLException
	 */
	private void populateLocation() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO LOCATION(LocID, LocationType, Size) VALUES"
				+ "('FOREST', 'Undead Forest', 10000'),"
				+ "('SCHOOL', 'Place of Study', 10000),"
				+ "('MANSION', 'Spooky Mansion', 10000),"
				+ "('POOL', 'Place of Sharks', 10000),"
				+ "('PLAIN', 'Place of Tall Grass', 10000);";
		
		state.execute(sql);

	}
	
	/**
	 * Populate Exits table
	 * @throws SQLException
	 */
	private void populateExits() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO EXITS(LocID, exitLocID, Location) VALUES"
				+ "('FOREST', 'SCHOOL', 'Shippensburg University'),"
				+ "('SCHOOL', 'FOREST', 'Forest of the Dead'),"
				+ "('MANSION', 'POOL', 'Gatsby Mansion'),"
				+ "('POOL', 'MANSION', 'Pool filled with sharks'),"
				+ "('FOREST', 'PLAIN', 'Normal plain');";
		
		state.execute(sql);

	}

	/**
	 * Populate creature table
	 * @throws SQLException
	 */
	private void populateCreatures() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO CREATURE(Strength, Stamina, Damage_Reduction, CreID, Max_HP, Current_HP, LocID, Likes, Dislikes) VALUES"
				+ "(50, 75, 25, 999, 100, 98, 'FOREST', 'player1', 'player2'),"
				+ "(50, 75, 25, 998, 100, 98, 'SCHOOL', 'player2', 'player1'),"
				+ "(50, 75, 25, 997, 100, 98, 'MANSION', 'player4', 'player3'),"
				+ "(50, 75, 25, 996, 100, 98, 'POOL', 'player2', 'player5'),"
				+ "(50, 75, 25, 995, 100, 98, 'PLAIN', 'player1', 'player4');";
		
		state.execute(sql);

	}
	
	/**
	 * Populates areas
	 * @throws SQLException
	 */
	private void populateAreas() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO AREA_CAN_GO_TO(creID, Area) VALUES"
				+ "(999, 'FOREST'),"
				+ "(998, 'SCHOOL'),"
				+ "(997, 'MANSION'),"
				+ "(996, 'POOL'),"
				+ "(995, 'PLAIN');";
		
		state.execute(sql);

	}
	
	/**
	 * Populates player character
	 * @throws SQLException
	 */
	private void populatePlayerChar() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO PLAYER_CHAR(locID, Strength, Stamina, Location, PName, Max_HP, Current_HP, Player_Login, Disliked_By, Liked_By) VALUES"
				+ "('FOREST', 60, 80, 30, 'FOREST', 'Dio', 200, 200, 'player1', '998', '999'),"
				+ "('SCHOOL', 60, 80, 30, 'SCHOOL', 'Joseph', 200, 200, 'player1', '997', '995'),"
				+ "('MANSION', 60, 80, 30, 'MANSION', 'Jonathan', 200, 200, 'player1', '996', '999'),"
				+ "('POOL', 60, 80, 30, 'POOL', 'Jotaro', 200, 200, 'player1', '995', '998'),"
				+ "('PLAIN', 60, 80, 30, 'PLAIN', 'Speedwagon', 200, 200, 'player1', '997', '998');";
		
		state.execute(sql);

	}
	
	/**
	 * Populate item table
	 * @throws SQLException
	 */
	private void populateItem() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO ITEM(ItemID, WornItem, PossessedItem, locID) VALUES"
				+ "('I500',  'TRUE', 'TRUE', 'FOREST'),"
				+ "('I100', 'TRUE', 'TRUE', 'FOREST'),"
				+ "('I200', 'FALSE', 'TRUE', 'POOL'),"
				+ "('I300', 'TRUE', 'FALSE', 'MANSION'),"
				+ "('I400', 'TRUE', 'TRUE', 'MANSION');";
		
		state.execute(sql);

	}
	
	/**
	 * Populates armor table
	 * @throws SQLException
	 */
	private void populateArmor() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO ARMOR(aID, Damage_Reduction, Slot, Volume, Mass, itemID) VALUES"
				+ "('A100', '10', 'CHEST', 50, 50, 'I100'),"
				+ "('A200', '10', 'HAND', 50, 50, 'I200'),"
				+ "('A300', '10', 'PANTS', 50, 50, 'I300'),"
				+ "('A400', '10', 'CHEST', 50, 50, 'I400'),"
				+ "('A500', '10', 'HAND', 50, 50, 'I500');";
		
		state.execute(sql);

	}
	
	/**
	 * Populate generic item
	 * @throws SQLException
	 */
	private void populateGenericItem() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO GENERIC_ITEM(gID, Volume, Mass, itemID) VALUES"
				+ "('G100', 5, 5, 'I100'),"
				+ "('G200', 5, 5, 'I200'),"
				+ "('G300', 5, 5, 'I300'),"
				+ "('G400', 5, 5, 'I400'),"
				+ "('G500', 5, 5, 'I500');";
		
		state.execute(sql);

	}
	
	/**
	 * Populates container table
	 * @throws SQLException
	 */
	private void populateContainer() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO CONTAINER(cID, Volume, Mass, Volume_Limit, Weight_Limit, itemID) VALUES"
				+ "('C100', 100, 100, 100, 100, 'I100'),"
				+ "('C200', 100, 100, 100, 100, 'I200'),"
				+ "('C300', 100, 100, 100, 100, 'I300'),"
				+ "('C400', 100, 100, 100, 100, 'I400'),"
				+ "('C500', 100, 100, 100, 100, 'I500');";
		
		state.execute(sql);

	}
	
	/**
	 * Populates ability table
	 * @throws SQLException
	 */
	private void populateAbility() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO ABILITY (abID,effect,Stat_Affected, Cast_Time, Rate_of_ocurrence, CreID) VALUES"
				+ "('AB100', 20, 'HEALTH', 00:00:01, 00:40:00, 999 ),"
				+ "('AB200', 20, 'STRENGTH', 00:00:01, 00:20:00, 998 ),"
				+ "('AB300', 20, 'HEALTH', 00:00:07, 00:40:00, 997 ),"
				+ "('AB400', 20, 'STAMINA', 00:01:00, 00:30:00, 996 ),"
				+ "('AB500', 20, 'HEALTH', 00:00:3, 00:10:00, 995 );";
		
		state.execute(sql);

	}

	private void populateWeapon() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO WEAPON(wID, Volume, Mass, Current_Location, abID, itemID) VALUES"
				+ "('W100', 10, 12, 'Left Hand', 'AB100, 'I100'),"
				+ "('W200', 10, 12, 'Right Hand', 'AB200, 'I200'),"
				+ "('W300', 10, 12, 'Left Hand', 'AB300, 'I300'),"
				+ "('W400', 10, 12, 'Right Hand', 'AB400, 'I400'),"
				+ "('W500', 10, 12, 'Left Hand', 'AB500, 'I500');";
		
		state.execute(sql);

	}
}
