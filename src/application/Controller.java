package application;

import java.util.ArrayList;

public class Controller {
	private static DBFunctions db = new DBFunctions();

	public Controller() {

	}

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

		DBFunctions.addTask(title, endT, desc, Integer.parseInt(priority));

	}

	
	public static void deleteTask(Task toDelete) {
		
		db.deleteTask(toDelete.getID());
		
	}

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

		 db.modifyTask(toChange.getID(), title, endT, desc, Integer.parseInt(priority));

	}

	public static ArrayList<Task> upcomingTasks() {
		ArrayList<Task> taskList = new ArrayList<Task>();

		try {
			taskList = db.viewUpcoming();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * List<String> sendTasks = new ArrayList<String>();
		 * 
		 * //temporary solution for (List<String> list: tasklis) {
		 * sendTasks.add(list.get(0)); }
		 */

		return taskList;
	}
}
