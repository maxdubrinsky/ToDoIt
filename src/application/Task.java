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

}
