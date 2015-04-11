package application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DBFunctionsTest {

	@Test
	public void viewTest() {
		DBFunctions dbf = new DBFunctions();

		ArrayList<Task> tasks = dbf.viewUpcoming();

		for (int i = 0; i < tasks.size(); i++) {
			System.out.println(tasks.get(i).getTitle()
					+ tasks.get(i).getPriority() + tasks.get(i).getDesc()
					+ tasks.get(i).getEnd() + tasks.get(i).getStart());
		}

		// System.out.println(tasks.toString());

	}

	@Test
	public void addTaskTest() {
		DBFunctions dbf = new DBFunctions();

		ArrayList<Task> tasks = dbf.viewAll();

		int size = tasks.size();

		dbf.addTask("hello, world", 20150906123000l, "something clever", 5);

		if (dbf.viewAll().size() != (size + 1)) {
			fail("did not add task correctly");
		}
	}

	@Test
	public void deleteTaskTest() {

		DBFunctions dbf = new DBFunctions();

		ArrayList<Task> tasks = dbf.viewAll();
		int id = tasks.get(0).getID();
		int size = tasks.size();

		dbf.deleteTask(id);

		if (dbf.viewAll().size() != (size - 1)) {
			fail("did not delete task");
		}

	}

	@Test
	public void modifyTaskTest() {

		DBFunctions dbf = new DBFunctions();

		ArrayList<Task> tasks = dbf.viewUpcoming();
		int id = tasks.get(0).getID();
		int size = tasks.size();

		dbf.modifyTask(id, "oof-dah", 20150202020202l, "this is weird", 3);

		if (tasks.size() != size) {
			fail("something got added or deleted that wasn't supposed to");
		}
	}

}
