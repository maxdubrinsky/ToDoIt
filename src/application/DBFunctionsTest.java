package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DBFunctionsTest {
/*
	@Test
	public void addTaskTest() {
		DBFunctions dbf = new DBFunctions();

		ArrayList<Task> tasks = dbf.viewUpcoming();

		int size = tasks.size();

		dbf.addTask("hello, world", 20150326034200l, 20150326044200l,
				"something clever");

		if (dbf.viewUpcoming().size() != (size + 1)) {
			fail("did not add task correctly");
		}
	}

	@Test
	public void deleteTaskTest() {

		DBFunctions dbf = new DBFunctions();

		ArrayList<Task> tasks = dbf.viewUpcoming();
		int id = tasks.get(0).getID();
		int size = tasks.size();

		dbf.deleteTask(id);

		if (dbf.viewUpcoming().size() != (size - 1)) {
			fail("did not delete task");
		}

	}

	@Test
	public void modifyTaskTest() {

		DBFunctions dbf = new DBFunctions();

		ArrayList<Task> tasks = dbf.viewUpcoming();
		int id = tasks.get(0).getID();
		int size = tasks.size();

		dbf.modifyTask(id, "oof-dah", 20150101010101l,
				20150202020202l, "this is weird");

		if (tasks.size() != size){
			fail("something got added or deleted that wasn't supposed to");
		}
	}
	*/
	
	@Test
	public void controlTest() {
		Controller control = new Controller();
		List<Task> tasks = new ArrayList<Task>();
		tasks = control.upcomingTasks();
		
		//wipe all current tasks.
		while (tasks.size() > 0) {
			control.deleteTask(tasks.get(0));
			tasks = control.upcomingTasks();
		}
		
		control.addTask("CS3421", "Team software project", "02/27/2014", 3, 05, "PM", "02/27/2014", 3, 55, "PM");
		control.addTask("Work", "MacDonalds", "02/27/2014", 5, 00, "PM", "02/27/2014", 10,00, "PM");
		control.addTask("Team Meeting", "Team software project all night programming session", "02/27/2014", 11, 59, "PM", "02/28/2014", 8, 00, "AM");
		control.addTask("MA3160 exam", "Calc 3 exam", "02/28/2014", 8, 05, "AM", "02/28/2014", 9, 55, "AM");
		tasks = control.upcomingTasks();
		if (tasks.size() != 4) {
			fail("Didnt add correctly");
		}
		
		for (Task t : tasks) {
			if (t.getTitle().equals("Work")) {
				control.modifyTask(t, "2nd Job", "Subway", "02/27/2014", 5, 00, "PM", "02/27/2014", 8, 00, "PM");
			}
		}
		tasks = control.upcomingTasks();
		for (Task t : tasks) {
			if (t.getTitle().equals("Work")) {
				fail("Didnt modify correctly.");
			}
		}
		
		String start = "2014-02-17 05:07:33";
		String out = 	start.substring(5, 7)  + " " + start.substring(8, 10) + " " + start.substring(2, 4) +
				" " + start.substring(11, 13) + " " + start.substring(14, 16);
		
		System.out.println(out);
		
	}
	

}
