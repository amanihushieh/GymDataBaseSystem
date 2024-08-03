package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SimpleDB {

	private static String dbUsername = "root";
	private static String dbPassword = "as1200219";
	private static String URL = "127.0.0.1";
	private static String port = "3306";
	private static String dbName = "GymManagementSystem";
	private static Connection con;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPassword);

		con = a.connectDB();
		System.out.println("Connection established");
		String s="select * from member";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(s);
           while(rs.next()) {
        	   System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5)+" "
        			   +rs.getString(6)+" "+rs.getString(7));
           }
           rs.close();
           stmt.close();
   		
   		con.close();
   		System.out.println("Connection closed");
	}

}

class DBConn {

	private Connection con;
	private String dbURL;
	private String dbUsername;
	private String dbPassword;
	private String URL;
	private String port;
	private String dbName;

	DBConn(String URL, String port, String dbName, String dbUsername, String dbPassword) {

		this.URL = URL;
		this.port = port;
		this.dbName = dbName;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
	}

	public Connection connectDB() throws ClassNotFoundException, SQLException {

		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
		System.out.println(dbURL);

		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(dbURL, p);

		return con;
	}
}
