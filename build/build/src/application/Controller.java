package application;

import java.util.ArrayList;

public class Controller {
	private static DBFunctions db = new DBFunctions();

	public Controller() {

	}

	/**
	 * This function takes in many strings from the GUI, converts them into a
	 * format representing a task that is accepted by the database, then adds a
	 * new task to the database.
	 * 
	 * Dates are converted into yyyymmddhhmmss longs.
	 * 
	 * @param title The title of the task.
	 * @param desc A description of the task, max 255 charsm
	 * @param priority The priority level of the taskm
	 * @param eYear The year the task ends in.
	 * @param eMonth The month the task ends in.
	 * @param eDate The day the task ends in.
	 * @param eHour The hour the task ends in.
	 * @param eMin The minute the task ends in.
	 * @param eAMPM Am or PM string, for a 12 hour time system.
	 */
	public static void addTask(String title, String desc, String priority,
			String eYear, String eMonth, String eDate, String eHour,
			String eMin, String eAMPM) {

		long endT;

		// Convert eMonth to a number
		switch (eMonth) {
		case "Jan":
			eMonth = "01";
			break;
		case "Feb":
			eMonth = "02";
			break;
		case "Mar":
			eMonth = "03";
			break;
		case "Apr":
			eMonth = "04";
			break;
		case "May":
			eMonth = "05";
			break;
		case "Jun":
			eMonth = "06";
			break;
		case "Jul":
			eMonth = "07";
			break;
		case "Aug":
			eMonth = "08";
			break;
		case "Sep":
			eMonth = "09";
			break;
		case "Oct":
			eMonth = "10";
			break;
		case "Nov":
			eMonth = "11";
			break;
		case "Dec":
			eMonth = "12";
			break;
		}

		// Convert time to 24 Hour format from 12 Hour
		if (eAMPM.equals("PM") && !eHour.equals("12")) {
			int eH = Integer.parseInt(eHour);
			eH = eH + 12;
			eHour = String.valueOf(eH);
		} else if (eAMPM.equals("PM") && eHour.equals("12")) {
			eHour = "00";
		}

		// Checks to make end values two digits
		if (eMonth.length() == 1) {
			eMonth = "0" + eMonth;
		}
		if (eDate.length() == 1) {
			eDate = "0" + eDate;
		}
		if (eHour.length() == 1) {
			eHour = "0" + eHour;
		}
		if (eMin.length() == 1) {
			eMin = "0" + eMin;
		}

		String concatEndT = eYear + eMonth + eDate + eHour + eMin + "00";

		endT = Long.parseLong(concatEndT);

		//Some sanitation
		DBFunctions.addTask(title.replace("'", "''"), endT,
				desc.replace("'", "''"), Integer.parseInt(priority));

	}
	

	/**
	 * This function takes in a Task object from the GUI and sends the id to the database for deletion.
	 * 
	 * @param toDelete The task object to delete.
	 */
	public static void deleteTask(Task toDelete) {
		db.deleteTask(toDelete.getID());
	}

	/**
	 * This function takes in a task object from the GUI as well as several strings
	 * for which fields to modify, converts them into a format representing a task
	 * that is accepted by the database, then modifies that existing task in the database.
	 * 
	 * Dates are converted into yyyymmddhhmmss longs.
	 * 
	 * @param toChange The Task object needing modifcation.
	 * @param title The title of the task.
	 * @param desc A description of the task, max 255 chars
	 * @param priority The priority level of the task
	 * @param eYear The year the task ends in.
	 * @param eMonth The month the task ends in.
	 * @param eDate The day the task ends in.
	 * @param eHour The hour the task ends in.
	 * @param eMin The minute the task ends in.
	 * @param eAMPM Am or PM string, for a 12 hour time system.
	 */
	public static void modifyTask(Task toChange, String title, String desc,
			String priority, String eYear, String eMonth, String eDate,
			String eHour, String eMin, String eAMPM) {

		long endT;

		// Convert eMonth to a number
		switch (eMonth) {
		case "Jan":
			eMonth = "01";
			break;
		case "Feb":
			eMonth = "02";
			break;
		case "Mar":
			eMonth = "03";
			break;
		case "Apr":
			eMonth = "04";
			break;
		case "May":
			eMonth = "05";
			break;
		case "Jun":
			eMonth = "06";
			break;
		case "Jul":
			eMonth = "07";
			break;
		case "Aug":
			eMonth = "08";
			break;
		case "Sep":
			eMonth = "09";
			break;
		case "Oct":
			eMonth = "10";
			break;
		case "Nov":
			eMonth = "11";
			break;
		case "Dec":
			eMonth = "12";
			break;
		}

		// Convert time to 24 Hour format from 12 Hour
		if (eAMPM.equals("PM") && !eHour.equals("12")) {
			int eH = Integer.parseInt(eHour);
			eH = eH + 12;
			eHour = String.valueOf(eH);
		} else if (eAMPM.equals("PM") && eHour.equals("12")) {
			eHour = "00";
		}

		// Checks to make end values two digits
		if (eMonth.length() == 1) {
			eMonth = "0" + eMonth;
		}
		if (eDate.length() == 1) {
			eDate = "0" + eDate;
		}
		if (eHour.length() == 1) {
			eHour = "0" + eHour;
		}
		if (eMin.length() == 1) {
			eMin = "0" + eMin;
		}

		String concatEndT = eYear + eMonth + eDate + eHour + eMin + "00";

		endT = Long.parseLong(concatEndT);

		//Some sanitation
		db.modifyTask(toChange.getID(), title.replace("'", "''"), endT,
				desc.replace("'", "''"), Integer.parseInt(priority));

	}

	
	/**
	 * This function simply fetches the list of upcoming tasks in the database and returns them to the GUI.
	 * 
	 * @return The list of upcoming tasks in the database
	 */
	public static ArrayList<Task> upcomingTasks() {
		ArrayList<Task> taskList = new ArrayList<Task>();

		try {
			taskList = db.viewUpcoming();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return taskList;
	}
}
