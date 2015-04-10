package application;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class ControllerTest {

	@Test
	public void controllerTest() {
		List<Task> tasks = new ArrayList<Task>();
		tasks = Controller.upcomingTasks();
		
		//wipe all current tasks
		while (tasks.size() > 1) {
			Controller.deleteTask(tasks.get(0));
			tasks = Controller.upcomingTasks();
			
			System.out.println("1");
		}
		
		try {
			if (tasks.size() != 1) {
				fail("Delete did not remove tasks");
			}
			
		} catch(NullPointerException e) {
			
		}
		
		Controller.addTask("CS3421", "Team software project", "2", "2014", "7", "2", "3", "5", "PM");
		Controller.addTask("Work", "MacDonalds", "7", "2014", "07", "2", "12", "0", "PM");
		Controller.addTask("Team Meeting", "Team software project programming session", "1", "2014", "7", "02", "12", "00", "AM");
		
		tasks = Controller.upcomingTasks();
		if (tasks.size() != 3) {
			fail("Didnt add correctly");
		}
		
		for (Task t : tasks) {
			if (t.getTitle().equals("Work")) {
				Controller.modifyTask(t, "2nd Job", "Subway", "7", "2014", "07", "2", "2", "0", "PM");
			}
		}
		
		tasks = Controller.upcomingTasks();
		for (Task t : tasks) {
			if (t.getTitle().equals("Work")) {
				fail("Didnt modify correctly.");
			}
		}
	}
	
}
