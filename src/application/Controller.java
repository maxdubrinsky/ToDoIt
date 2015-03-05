package application;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class Controller {
    private DBFunctions db;
    
    public Controller(){
        db = new DBFunctions();
        db.connect();
    }
    
    @SuppressWarnings("deprecation")
    public void addTask(String title, String desc, String startDate, int sH, int sM, String sapm,
            String endDate, int eH, int eM, String eapm) {
        
        //assume dates passed in are in format mm/dd/yyyy
        Date startD = new Date(    Integer.parseInt(startDate.substring(6, 9)), //year
                                Integer.parseInt(startDate.substring(0, 1)), //month
                                Integer.parseInt(startDate.substring(3, 4))); //day
        
        Date endD = new Date(    Integer.parseInt(endDate.substring(6, 9)), //year
                                Integer.parseInt(endDate.substring(0, 1)), //month
                                Integer.parseInt(endDate.substring(3, 4))); //day
        
        Time startTime;
        if (sapm.equals("PM") && sH != 12) {
            startTime = new Time(sH + 12, sM, 0);
        } else if (sapm.equals("AM") && sH == 12) {
            startTime = new Time(sH - 12, sM, 0);
        } else {
            startTime = new Time(sH, sM, 0);
        }
        
        Time endTime;
        if (eapm.equals("PM") && eH != 12) {
            endTime = new Time(eH + 12, eM, 0);
        } else if (eapm.equals("AM") && eH == 12) {
            endTime = new Time(eH - 12, eM, 0);
        } else {
            endTime = new Time(eH, eM, 0);
        }
        
        db.addTask(title, startD, startTime, endD, endTime, desc);
    }
    
    public List<String> upcomingTasks() {
        List<List<String>> tasklist = db.viewUpcoming();
        
        List<String> sendTasks = new ArrayList<String>();
        
        //temporary solution
        for (List<String> list: tasklist) {
            sendTasks.add(list.get(0));
        }
        
        return sendTasks;
    }
}
