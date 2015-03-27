package application;

import java.util.*;

public class Controller {
    private static DBFunctions db;
    
    public Controller(){
        db = new DBFunctions();
    }
    
    public static void addTask(String title, String desc, String startDate, int sH, int sM, String sapm,
            String endDate, int eH, int eM, String eapm) {
        long startT;
        long endT;
        
        System.out.println("The Info."  + title + desc + startDate + sH + sM + sapm + endDate + eH + eM + eapm);
        
        startT = 	(Long.parseLong(startDate.substring(6, 10)) * ((long)Math.pow(10, 10))) + //year
        			(Long.parseLong(startDate.substring(0, 2)) * ((long)Math.pow(10, 8))) + //month
        			(Long.parseLong(startDate.substring(3, 5)) * ((long)Math.pow(10, 6))); //day
        
        endT = 		(Long.parseLong(endDate.substring(6, 10)) * ((long)Math.pow(10, 10))) + //year
    				(Long.parseLong(endDate.substring(0, 2)) * ((long)Math.pow(10, 8))) + //month
    				(Long.parseLong(endDate.substring(3, 5)) * ((long)Math.pow(10, 6))); //day
  
 
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
        System.out.println(startT);
        System.out.println(endT);
        db.addTask(title,  startT, endT, desc);
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
         
         db.modifyTask(toChange.getID(), title,  startT, endT, desc);
    	
    }
    
    
    public List<Task> upcomingTasks() {
        List<Task> taskList = db.viewUpcoming();
        
        /*List<String> sendTasks = new ArrayList<String>();
        
        //temporary solution
        for (List<String> list: tasklis) {
            sendTasks.add(list.get(0));
        }
        */
        
        return taskList;
    }
}
