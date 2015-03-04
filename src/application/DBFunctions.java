package application;

import java.sql.*;

public class DBFunctions {
	Connection conn = null;
	PreparedStatement add = null;
	PreparedStatement delete = null;
	PreparedStatement modify = null;
	PreparedStatement view = null;
	ResultSet rs = null;

	public int connect() {
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://carbon.dubrinksy.com/todoit", "greg",
					"horseboxfrog");

			System.out.println("connection successful");
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}

	}

	/**
	 * Takes in critical information about a task and creates a new entry in the
	 * database
	 * 
	 * @param title
	 * @param startDate
	 * @param startTime
	 * @param endDate
	 * @param endTime
	 * @param desc
	 */
	public void addTask(String title, Date startDate, Time startTime,
			Date endDate, Time endTime, String desc) {
		
		int count;
		
		try {
			add = conn.prepareStatement("INSERT INTO TABLE task(task_desc, "
					+ "end_date, end_time, task_title, start_date, start_time) "
					+ "VALUES (?, ?, ?, ?, ?, ?);");
			
			add.setString(1, desc);
			add.setDate(2, endDate);
			add.setTime(3, endTime);
			add.setString(4, title);
			add.setDate(5, startDate);
			add.setTime(6, startTime);
			
			count = add.executeUpdate();
			
			if (count !=1){
				System.out.println("Trying to modify " + count + " rows.");
				conn.rollback();
			}
			else{
				conn.commit();
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}

	public void modifyTask(Integer taskId, String title, Date startDate,
			Time startTime, Date endDate, Time endTime, String desc) {
		
		int count;
		
		try {
			modify = conn.prepareStatement("UPDATE task SET task_desc = ?,"
					+ " end_date = ?, end_time = ?, task_title = ?, "
					+ "start_date = ?, start_time = ? WHERE task_id = ? ;");
			
			modify.setString(1, desc);
			modify.setDate(2, endDate);
			modify.setTime(3, endTime);
			modify.setString(4, title);
			modify.setDate(5, startDate);
			modify.setTime(6, startTime);
			modify.setInt(7, taskId);
			
			count = modify.executeUpdate();
			
			if(count != 1) {
				System.out.println("trying to modify " + count + "lines");
				conn.rollback();
			}
			else{
				conn.commit();
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}

	}

	public void deleteTask(String title, Date startDate, Time startTime) {
		
		int count;
		
		try {
			delete = conn.prepareStatement("DELETE FROM task WHERE (task_title "
					+ "= ? AND start_date = ? AND start_time = ?);");
			
			delete.setString(1, title);
			delete.setDate(2, startDate);
			delete.setTime(3, startTime);
			
			count = delete.executeUpdate();
			
			if (count != 1) {
				System.out.println("trying to modify " + count + "lines");
				conn.rollback();
			}
			else{
				conn.commit();
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}

	}

	public void viewUpcoming() {
//		try {
//			
//			
//
//		} catch (SQLException e) {
//			System.out.println("SQLException: " + e.getMessage());
//			System.out.println("SQLState: " + e.getSQLState());
//			System.out.println("VendorError: " + e.getErrorCode());
//		}

	}

	public static void Main(String[] args) {

	}

}
