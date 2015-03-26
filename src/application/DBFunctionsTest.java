package application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DBFunctionsTest {

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

}
