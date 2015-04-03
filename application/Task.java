package application;

public class Task {
	
	private String title;
	private String description;
	private Object startTime;
	private Object endTime;
	private int taskID;
	private int priority;
	
	/**
	 * returns the ID of the task as an integer
	 * 
	 * @return taskID
	 */
	public int getID() {
		return taskID;
	}
	
	/**
	 * sets the ID for the Task object
	 * @param id
	 */
	public void setID(int id) {
		taskID = id;
	}
	
	/**
	 * returns the title of the Task
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * sets the title of the task
	 * @param taskTitle
	 */
	public void setTitle(String taskTitle) {
		title = taskTitle;
	}
	
	/**
	 * returns the description of the Task
	 * @return description
	 */
	public String getDesc() {
		return description;
	}
	
	/**
	 * sets the description of the task
	 * @param desc
	 */
	public void setDesc(String desc) {
		description = desc;
	}
	
	/**
	 * returns the start(creation) time of the task in the form of an Object
	 * @return startTime
	 */
	public Object getStart() {
		return startTime;
	}
	
	/**
	 * sets the start(creation) time of the task in the form of an Object
	 * @param start
	 */
	public void setStart(Object start) {
		startTime = start;
	}
	
	/**
	 * returns the end time of a task in the form of an Object
	 * @return endTime
	 */
	public Object getEnd() {
		return endTime;
	}
	/**
	 * sets the end time of a task in the form of an Object
	 * @param end
	 */
	
	public void setEnd(Object end) {
		endTime = end;
	}
	
	/**
	 * returns the priority value of the task
	 * @return priority
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * sets the priority value of the task
	 * @param pri
	 */
	public void setPriority(int pri) {
		priority = pri;
	}
	
	/**
	 * generic constructor
	 */
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
		String output = getEndDate();	
		return output;
	}
}
