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
 *ad
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
		//populateModerator();
		//populateModeratorAbilities();
		//populateManagerAbilities();
		//populatePlayers();
		//SHOW populateLocation();
		//populateExits();
		//populateCreatures();
		//populateAreas();
		//populatePlayerChar();
		//populateItem();
		//populateArmor();
		//populateGenericItem();
		//populateContainer();
		//populateAbility();
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
	private void populateModeratorAbilities() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO MODERATOR_PRIVILEGES(ModLogin, ModPrivilege) VALUES"
				+ "('moderator1', 'WhateverModeratorsSpecialAbilityCanBe?'),"
				+ "('moderator2', 'WhateverModeratorsSpecialAbilityCanBe2?'),"
				+ "('moderator3', 'WhateverModeratorsSpecialAbilityCanBe3?'),"
				+ "('moderator4', 'WhateverModeratorsSpecialAbilityCanBe4?'),"
				+ "('moderator5', 'WhateverModeratorsSpecialAbilityCanBe5?');";
		
		state.execute(sql);

	}
	
	/**
	 * Populate special abilities for managers
	 * @throws SQLException
	 */
	private void populateManagerAbilities() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO MANAGER_PRIVILEGES(ManLogin, ManPrivilege) VALUES"
				+ "('manager1', 'WhateverModeratorsSpecialAbilityCanBe?'),"
				+ "('manager2', 'WhateverModeratorsSpecialAbilityCanBe2?'),"
				+ "('manager3', 'WhateverModeratorsSpecialAbilityCanBe3?'),"
				+ "('manager4', 'WhateverModeratorsSpecialAbilityCanBe4?'),"
				+ "('manager5', 'WhateverModeratorsSpecialAbilityCanBe5?');";
		
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
		
		String sql = "INSERT INTO LOCATION(LocID, LocType, Size) VALUES"
				+ "('FOREST', 'Undead Forest', 10000),"
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
		
		String sql = "INSERT INTO LOCATION_EXIT(LocID, exitLocID, Location) VALUES"
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
		
		String sql = "INSERT INTO CREATURE_TERRAIN(creID, Area) VALUES"
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
		
		String sql = "INSERT INTO PLAYER_CHAR(LocID, Strength, Stamina, Location, PName, Max_HP, Current_HP, Player_Login, Liked_By, DisLiked_By) VALUES"
				+ "('FOREST', 60, 80, 'FOREST', 'Dio', 200, 200, 'player1', '998', '999'),"
				+ "('SCHOOL', 60, 80, 'SCHOOL', 'Joseph', 200, 200, 'player1', '997', '995'),"
				+ "('MANSION', 60, 80, 'MANSION', 'Jonathan', 200, 200, 'player1', '996', '999'),"
				+ "('POOL', 60, 80, 'POOL', 'Jotaro', 200, 200, 'player1', '995', '998'),"
				+ "('PLAIN', 60, 80, 'PLAIN', 'Speedwagon', 200, 200, 'player2', '997', '998');";
		
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
				+ "('Sword of Luck', NULL,'Jonathan', NULL),"
				+ "('Clacky Balls', NULL, 'Joseph',NULL),"
				+ "('Hermit Purple', NULL, 'Joseph', NULL),"
				+ "('Sharp Claws', NULL, 'Dio', NULL),"
				+ "('The World', NULL, 'Dio', NULL),"
				+ "('Cool Hat', 'Jotaro', NULL, NULL),"
				+ "('Fancy Hat', 'Speedwagon', NULL, NULL),"
				+ "('Crazy Headband', 'Joseph', NULL, NULL),"
				+ "('Black Jacket', 'Jotaro', NULL, NULL),"
				+ "('Suit', 'Jonathan', NULL, NULL),"
				+ "('Metal Ball', NULL, 'Joseph', NULL),"
				+ "('Round Ball', NULL, 'Joseph', NULL),"
				+ "('Square Ball', NULL, 'Joseph', NULL),"
				+ "('Book', NULL, 'Dio', NULL),"
				+ "('Sharp Object', NULL, 'Dio', NULL),"
				+ "('Bag', NULL, 'Speedwagon', NULL),"
				+ "('Backpack', NULL, 'Jonathan', NULL),"
				+ "('Pockets', NULL, 'Joseph', NULL),"
				+ "('Secret bag', NULL, 'Jotaro', NULL),"
				+ "('Cool bag', NULL, 'Dio', NULL);";
		
		state.execute(sql);

	}
	
	/**
	 * Populates armor table
	 * @throws SQLException
	 */
	private void populateArmor() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO ARMOR(ArmorID, Damage_Reduction, Slot, Volume, Mass, ItemID) VALUES"
				+ "('Cool Hat', '10', 'HEAD', 50, 50, 'Cool Hat'),"
				+ "('A200', '10', 'HEAD', 50, 50, 'Fancy Hat'),"
				+ "('A300', '10', 'HEAD', 50, 50, 'Crazy Headband'),"
				+ "('A400', '10', 'CHEST', 50, 50, 'Black Jacket'),"
				+ "('A500', '10', 'CHEST', 50, 50, 'Suit');";
		
		state.execute(sql);

	}
	
	/**
	 * Populate generic item
	 * @throws SQLException
	 */
	private void populateGenericItem() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO GENERIC_ITEM(genItemID, Volume, Mass, ItemID) VALUES"
				+ "('Metal Ball', 5, 5, 'Metal Ball'),"
				+ "('Round Ball', 5, 5, 'Round Ball'),"
				+ "('Square Ball', 5, 5, 'Square Ball'),"
				+ "('Book', 5, 5, 'Book'),"
				+ "('Sharp Object', 5, 5, 'Sharp Object');";
		
		state.execute(sql);

	}
	
	/**
	 * Populates container table
	 * @throws SQLException
	 */
	private void populateContainer() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO CONTAINER(ConID, Volume, Mass, Mass_Limit, Volume_Limit, ItemID) VALUES"
				+ "('C100', 100, 100, 100, 100, 'Bag'),"
				+ "('C200', 100, 100, 100, 100, 'Backpack'),"
				+ "('C300', 100, 100, 100, 100, 'Pockets'),"
				+ "('C400', 100, 100, 100, 100, 'Secret bag'),"
				+ "('C500', 100, 100, 100, 100, 'Cool bag');";
		
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
				+ "('AB100', 20, 'HEALTH', 000001, 004000, 999 ),"
				+ "('AB200', 20, 'STRENGTH', 000001, 002000, 998 ),"
				+ "('AB300', 20, 'HEALTH', 000007, 004000, 997 ),"
				+ "('AB400', 20, 'STAMINA', 000100, 003000, 996 ),"
				+ "('AB500', 20, 'HEALTH', 000003, 001000, 995 );";
		
		state.execute(sql);

	}

	private void populateWeapon() throws SQLException
	{
		Statement state = m_dbConn.createStatement();
		
		String sql = "INSERT INTO WEAPON(WeapID, Volume, Mass, Current_Location, abID, ItemID) VALUES"
				+ "('W100', 10, 12, 'Left', 'AB100', 'Sword of Luck'),"
				+ "('W200', 10, 12, 'Right', 'AB200', 'Clacky Balls'),"
				+ "('W300', 10, 12, 'Left', 'AB300', 'Hermit Purple'),"
				+ "('W400', 10, 12, 'Right', 'AB400', 'Sharp Claws'),"
				+ "('W500', 10, 12, 'Left', 'AB500', 'The World');";
		
		state.execute(sql);

	}
}
