package application;

import static org.junit.Assert.*;

import org.junit.Test;


public class DBFunctionsTest {

	@Test
	public void connectTest() {
		DBFunctions dbf = new DBFunctions();
		
		int test = dbf.connect();
		
		if (test != 0){
			fail("Connection unsuccessful");
		}
		
		dbf.disconnect();
		
	}
	
	@Test
	public void disconnectTest() {
		DBFunctions dbf = new DBFunctions();
		
		dbf.connect();
		int test = dbf.disconnect();
		
		if (test != 0){
			fail("Disconnection unsuccessful");
		}
	}
	
	@Test
	public void addTaskTest() {
		DBFunctions dbf = new DBFunctions();
		dbf.connect();
		
		String title = "Hello";
		String desc = "This is a salutation";
		java.sql.Date startDate = null;
		java.sql.Time startTime = null;
		java.sql.Date endDate = null;
		java.sql.Time endTime = null;
		
		
		int test = dbf.addTask(title, startDate, startTime, endDate, endTime, desc);
		
		if (test != 1) {
			fail("Add did not succeed");
		}
		if (test == 2) {
			fail("Add is trying to modify an incorrect number of rows");
		}
		
		dbf.disconnect();
	}
	
	@Test
	public void deleteTaskTest() {
		DBFunctions dbf = new DBFunctions();
		dbf.connect();
		
		int taskID = 1;
		
		int test = dbf.deleteTask(taskID);
		
		if (test != 1) {
			fail("Delete did not succeed");
		}
		
		if (test == 2) {
			fail("Delete is trying to modify an incorrect number of lines");
		}
		dbf.disconnect();
	}
	
	@Test
	public void modifyTaskTest() {
		DBFunctions dbf = new DBFunctions();
		dbf.connect();
		
		String title = "Hello";
		String desc = "This is a salutation";
		java.sql.Date startDate = null;
		java.sql.Time startTime = null;
		java.sql.Date endDate = null;
		java.sql.Time endTime = null;
		int taskId = 1;
		
		int test = dbf.modifyTask(taskId, title, startDate, startTime, endDate, endTime, desc);
		
		if (test != 1) {
			fail("Modify did not succeed");
		}
		
		if (test == 2) {
			fail("Modify is trying to modify an incorrect number of lines");
		}
	}

}
