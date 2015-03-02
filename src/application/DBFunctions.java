package application;

import java.sql.*;

public class DBFunctions {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public int connect() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://carbon.dubrinksy.com/todoit", "greg", "horseboxfrog");
			
			System.out.println("connection successful");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return 1;
		}
		
		return 0;
	}
	
	public void disconnect() {
		try {
			conn.close();
			
			System.out.println("connection closed");
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		
	}
	
	public void upcomingTasks() {
		
	}
	
	
	public static void Main (String[] args){
		DBFunctions db1 = new DBFunctions();
		
		db1.connect();
		db1.disconnect();
	}

}

