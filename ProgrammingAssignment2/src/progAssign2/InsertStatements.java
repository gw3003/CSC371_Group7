package progAssign2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Gabe Werick
 * 
 * This class has insert statements to add data to tables programming assignment2
 *
 */
public class InsertStatements {
	
	public static void main(String[] args)
	{
		
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
	public boolean activateJDBC() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return true;
	}

	

}
