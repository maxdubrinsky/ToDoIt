package application;

public class Task {
	
	private String title;
	private String description;
	private Object startTime;
	private Object endTime;
	private int taskID;
	
	public int getID() {
		return taskID;
	}
	
	public void setID(int id) {
		taskID = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String taskTitle) {
		title = taskTitle;
	}
	
	public String getDesc() {
		return description;
	}
	
	public void setDesc(String desc) {
		description = desc;
	}
	
	public Object getStart() {
		return startTime;
	}
	
	public void setStart(Object start) {
		startTime = start;
	}
	
	public Object getEnd() {
		return endTime;
	}
	
	public void setEnd(Object end) {
		endTime = end;
	}
	
	public Task(){
		
	}
	
	public String getStartDate() {
		String start = String.valueOf(startTime); //yyyy-mm-dd hh:mm:ss
		String out = 	start.substring(5, 7)  + " " + start.substring(8, 10) + " " + start.substring(2, 4) +
						" " + start.substring(11, 13) + " " + start.substring(14, 16); //mm dd yy hh mm
		
		return out;
	}
	
	public String getEndDate() {
		String end = String.valueOf(endTime); //yyyy-mm-dd hh:mm:ss
		String out = 	end.substring(5, 7)  + " " + end.substring(8, 10) + " " + end.substring(2, 4) +
						" " + end.substring(11, 13) + " " + end.substring(14, 16); //mm dd yy hh mm
		
		return out;
	}
	
	// Form of Title 10, then the Date then the Time.
	public String toString() {
		return null;
	}
}
