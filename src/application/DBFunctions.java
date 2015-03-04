package application;

import java.sql.*;
import java.util.*;

public class DBFunctions {
	
	Connection conn = null;
	PreparedStatement add = null;
	PreparedStatement delete = null;
	PreparedStatement modify = null;
	PreparedStatement view = null;
	ResultSet rs = null;

	/**
	 * logs into database
	 * @return
	 */
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

	/**
	 * closes connection with database
	 */
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
	public void addTask(String title, java.sql.Date startDate, Time startTime,
			java.sql.Date endDate, Time endTime, String desc) {
		
		int count;
		
		try {
			// Initialize prepareStatment and insert variables
			add = conn.prepareStatement("INSERT INTO TABLE task(task_desc, "
					+ "end_date, end_time, task_title, start_date, start_time) "
					+ "VALUES (?, ?, ?, ?, ?, ?);");
			
			add.setString(1, desc);
			add.setDate(2, endDate);
			add.setTime(3, endTime);
			add.setString(4, title);
			add.setDate(5, startDate);
			add.setTime(6, startTime);
			
			// executes the update and determines the number of rows modified
			count = add.executeUpdate();
			
			// if an incorrect number of rows are modified, rollback changes.  
			// Otherwise, commit
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
 /**
  * Changes the properties of a Task
  * 
  * @param taskId
  * @param title
  * @param startDate
  * @param startTime
  * @param endDate
  * @param endTime
  * @param desc
  */
	public void modifyTask(Integer taskId, String title, java.sql.Date startDate,
			Time startTime, java.sql.Date endDate, Time endTime, String desc) {
		
		int count;
		
		try {
			
			// Initializes prepareStatement and inserts variables
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
			
			// executes the update and counts the rows affected
			count = modify.executeUpdate();
			
			// if incorrect number of rows are affected, roll back.  
			// Otherwise, commit
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

	/**
	 * Removes a task from the table
	 * 
	 * @param title
	 * @param startDate
	 * @param startTime
	 */
	public void deleteTask(String title, java.sql.Date startDate, Time startTime) {
		
		int count;
		
		try {
			
			// initializes prepareStatement and inserts variables
			delete = conn.prepareStatement("DELETE FROM task WHERE (task_title "
					+ "= ? AND start_date = ? AND start_time = ?);");
			
			delete.setString(1, title);
			delete.setDate(2, startDate);
			delete.setTime(3, startTime);
			
			// Executes table update and counts affected rows
			count = delete.executeUpdate();
			
			// if incorrect number of rows have been modified, roll back.
			// Otherwise commit
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
	/**
	 * Returns up to the next 8 tasks in the table in order of start time
	 * @return List<List<String>>
	 */
	public List<List<String>> viewUpcoming() {
		
		// Create date object for use in prepared statement
		Calendar cal = Calendar.getInstance();
		java.sql.Date date = new java.sql.Date(cal.getTime().getTime());
		
		
		try {
			// Query DB to return next 8 tasks in order
			view = conn.prepareStatement("SELECT * FROM task WHERE start_date >= ? desc LIMIT 8;");
			
			view.setDate(1, date);
			
			rs = view.executeQuery();
			
			// Retrieve column count from result set object
			ResultSetMetaData meta = rs.getMetaData();
			int numCols = meta.getColumnCount();
			
			// Initialize the list of tasks
			List<List<String>> nextTasks = new ArrayList<>();
			
			// Populate the list
			while (rs.next()) {
				List<String> taskTuple = new ArrayList<>(numCols);
				int i = 1;
				
				while (i <= numCols){
					taskTuple.add(rs.getString(i++));
				}
				nextTasks.add(taskTuple);
			}
			
			
			return nextTasks;

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		
		return null;

	}

	/**
	 * default constructor
	 */
	public DBFunctions() {
		
	}
	
	public static void Main(String[] args) {

	}

}
