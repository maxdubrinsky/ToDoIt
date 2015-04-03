package application;

import java.util.*;

public class Controller {
    private static DBFunctions db;
    
    public Controller(){
        db = new DBFunctions();
    }
    
    public static void addTask(String title, String desc, int priority, String eYear, String eMonth, String eDate, 
    		String eHour, String eMin, String eAMPM) {
      
    	long endT;
        
        // Convert eMonth to a number
        switch (eMonth) {
        case "Jan" :
        	eMonth = "01";
        	break;
        case "Feb" :
        	eMonth = "02";
        	break;
        case "Mar" :
        	eMonth = "03";
        	break;
        case "Apr" :
        	eMonth = "04";
        	break;
        case "May" :
        	eMonth = "05";
        	break;
        case "Jun" :
        	eMonth = "06";
        	break;
        case "Jul" :
        	eMonth = "07";
        	break;
        case "Aug" :
        	eMonth = "08";
        	break;
        case "Sep" :
        	eMonth = "09";
        	break;
        case "Oct" :
        	eMonth = "10";
        	break;
        case "Nov" :
        	eMonth = "11";
        	break;
        case "Dec" :
        	eMonth = "12";
        	break;
        }
        
        //Convert priority to a two digit number
        //TODO
        
        
        // Convert time to 24 Hour format from 12 Hour        
        if (eAMPM.equals("PM") && !eHour.equals("12") ) {
        	int eH = Integer.parseInt(eHour);
        	eH = eH + 12;
        	eHour = String.valueOf(eH);
        }
        else if (eAMPM.equals("PM") && eHour.equals("12") ) {
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
        
        endT = 		Long.parseLong(concatEndT);
  
        System.out.println("Controller Print Statement");
        System.out.println(title);
        System.out.println(endT);
        System.out.println(desc);
        //TODO Waiting on proper method signature.
        //DBFunctions.addTask(title, endT, desc);
        
        System.out.println("Hi");
    }
    
    
    public void deleteTask(Task toDelete) {
    	db.deleteTask(toDelete.getID());
    }
    
    public void modifyTask(Task toChange, String title, String desc, String startDate, int sH, int sM, String sapm,
            String endDate, int eH, int eM, String eapm) {
    	
    	 long startT;
         long endT;
         startT = 	(Long.parseLong(startDate.substring(6, 10)) * ((long)Math.pow(10, 10))) + //year
         			(Long.parseLong(startDate.substring(0, 2)) * ((long)Math.pow(10, 8))) + //month
         			(Long.parseLong(startDate.substring(3, 5)) * ((long)Math.pow(10, 6))); //day
         
         endT = 		(Long.parseLong(endDate.substring(6, 10)) * ((long)Math.pow(10, 10))) + //year
     				(Long.parseLong(endDate.substring(0, 2)) * ((long)Math.pow(10, 8))) + //month
     				(Long.parseLong(endDate.substring(3, 5)) * ((long)Math.pow(10, 6))); //day
         
         System.out.println(startT);
         System.out.println(endT);
         if (sapm.equals("PM") && sH != 12) {
             startT += ((sH + 12) * 10000) + (sM * 100);
         } else if (sapm.equals("AM") && sH == 12) {
             startT += ((sH - 12) * 10000) + (sM * 100);
         } else {
             startT += (sH * 10000) + (sM * 100);
         }
         
         if (eapm.equals("PM") && eH != 12) {
             endT += ((eH + 12) * 10000) + (eM * 100);
         } else if (eapm.equals("AM") && eH == 12) {
             endT += ((eH - 12) * 10000) + (eM * 100);
         } else {
             endT += (eH * 10000) + (eM * 100);
         }
         
      //   db.modifyTask(toChange.getID(), title,  startT, endT, desc);
    	
    }
    
    
    public static ArrayList<Task> upcomingTasks() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        taskList = db.viewUpcoming();
        
        // If viewUpcoming returns an empty list, i.e. no upcoming tasks,
        // Create dummy task stating that there are no upcoming tasks and
        // add it to the taskList
//        if (taskList.isEmpty) {
//        	Task t = new Task();
//        	t.setTitle("No Upcoming Tasks");
//        	t.setDesc("No Upcoming Tasks");
//        	t.setID(9999);
//        	t.setPriority(1);
//        	        	
//        	taskList.add(t);
//        	
//        }
        
        /*List<String> sendTasks = new ArrayList<String>();
        
        //temporary solution
        for (List<String> list: tasklis) {
            sendTasks.add(list.get(0));
        }
        */
        
        return taskList;
    }
}
