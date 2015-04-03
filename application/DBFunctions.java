package application;

import java.util.*;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.concurrent.Task;

import org.json.*;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;



public class DBFunctions {

	/**
	 * Takes in critical information about a task and creates a new entry in the
	 * database
	 * 
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param desc
	 */
	//TODO Remove startTime from the method signature and make start time off that local function we talking about in meeting.
	public static void addTask(String title, long startTime, long endTime,
			String desc, int priority) {

		try {
			// Create http client
			HttpClient client = HttpClientBuilder.create().build();

			// Create url
			java.net.URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("carbon.dubrinsky.com")
					.setPath("/add_task.php")
					.setParameter("title", title)
					.setParameter("desc", desc)
					.setParameter("end", String.valueOf(endTime))
					.setParameter("priority", String.valueOf(priority))
					.build();

			// Create Get Request
			HttpGet get = new HttpGet(uri);

			// Execute request and capture response
			client.execute(get);

		} catch (ClientProtocolException e) {

			// If exception, print stack trace
			e.printStackTrace();

		} catch (IOException e) {

			// If exception, print stack trace
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Changes the properties of a Task
	 * 
	 * @param taskId
	 * @param title
	 * @param startDate
	 * @param startTime
	 * @param endDate
	 * @param endTime
	 * @param desc
	 */
	public void modifyTask(Integer taskId, String title, long startTime,
			long endTime, String desc, int priority) {

		try {
			// Create http client
			HttpClient client = HttpClientBuilder.create().build();

			// Create url
			java.net.URI uri = new URIBuilder()
			.setScheme("http")
			.setHost("carbon.dubrinsky.com")
			.setPath("/update_task.php")
			.setParameter("title", title)
			.setParameter("desc", desc)
			.setParameter("end", String.valueOf(endTime))
			.setParameter("taskID", String.valueOf(taskId))
			.setParameter("priority", String.valueOf(priority))
			.build();
			

			// Create Get Request
			HttpGet get = new HttpGet(uri);

			// Execute request and capture response
			client.execute(get);

		} catch (ClientProtocolException e) {

			// If exception, print stack trace
			e.printStackTrace();

		} catch (IOException e) {

			// If exception, print stack trace
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Removes a task from the table
	 * 
	 * @param title
	 * @param startDate
	 * @param startTime
	 */
	public void deleteTask(Integer taskID) {
		try {
			// Create http client
			HttpClient client = HttpClientBuilder.create().build();

			// Create url
			java.net.URI uri = new URIBuilder()
			.setScheme("http")
			.setHost("carbon.dubrinsky.com")
			.setPath("/delete_task.php")
			.setParameter("taskID", String.valueOf(taskID))
			.build();
			
			// Create Get Request
			HttpGet get = new HttpGet(uri);

			// Execute request and capture response
			client.execute(get);

		} catch (ClientProtocolException e) {

			// If exception, print stack trace
			e.printStackTrace();

		} catch (IOException e) {

			// If exception, print stack trace
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Returns up to the next 8 tasks in the table in order of start time
	 * 
	 * @return List<List<String>>
	 */
	public ArrayList<Task> viewUpcoming() {
		
		try {
			// Create http client
			HttpClient client = HttpClientBuilder.create().build();

			// Create url
			java.net.URI uri = new URIBuilder()
			.setScheme("http")
			.setHost("carbon.dubrinsky.com")
			.setPath("/read.php")
			.build();
					
			// Create Get Request
			HttpGet get = new HttpGet(uri);

			// Execute request and capture response
			HttpResponse result = client.execute(get);
			
			ArrayList<Task<V>> tasks = new ArrayList<Task>();
			
			String json = EntityUtils.toString(result.getEntity());
			
			JSONArray data = new JSONArray(json);
			JSONObject element;
			Task tsk;
			
			// Grab task attributes from JSON object and add to a Task object
			for(int i = 0; i < data.length(); i++) {
				element = data.getJSONObject(i);
				String title = element.getString("task_title");
				String desc = element.getString("task_desc");
				Object start = element.get("start_time");
				Object end = element.get("end_time");
				int id = element.getInt("task_id");
				int priority = element.getInt("priority");
				
				
				tsk = new Task();		
				
				tsk.setID(id);
				tsk.setTitle(title);
				tsk.setDesc(desc);
				tsk.setStart(start);
				tsk.setEnd(end);
				tsk.setPriority(priority);
				
				tasks.add(tsk);
			}
			
			if (tasks.isEmpty()) {
				Task t = new Task();
				
				t.setTitle("No Upcoming Tasks");
				t.setDesc("No Upcoming Tasks");
				
				tasks.add(t);
			}
			
			return tasks;

		} catch (ClientProtocolException e) {

			// If exception, print stack trace
			e.printStackTrace();

		} catch (IOException e) {

			// If exception, print stack trace
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return null;

	}
	
public ArrayList<Task> viewAll() {
		
		try {
			// Create http client
			HttpClient client = HttpClientBuilder.create().build();

			// Create url
			java.net.URI uri = new URIBuilder()
			.setScheme("http")
			.setHost("carbon.dubrinsky.com")
			.setPath("/read_all.php")
			.build();
					
			// Create Get Request
			HttpGet get = new HttpGet(uri);

			// Execute request and capture response
			HttpResponse result = client.execute(get);
			
			ArrayList<Task> tasks = new ArrayList<Task>();
			
			String json = EntityUtils.toString(result.getEntity());
			
			JSONArray data = new JSONArray(json);
			JSONObject element;
			Task tsk;
			
			// Grab task attributes from JSON object and add to a Task object
			for(int i = 0; i < data.length(); i++) {
				element = data.getJSONObject(i);
				String title = element.getString("task_title");
				String desc = element.getString("task_desc");
				Object start = element.get("start_time");
				Object end = element.get("end_time");
				int id = element.getInt("task_id");
				int priority = element.getInt("priority");
				
				tsk = new Task();		
				
				tsk.setID(id);
				tsk.setTitle(title);
				tsk.setDesc(desc);
				tsk.setStart(start);
				tsk.setEnd(end);
				tsk.setPriority(priority);
				
				tasks.add(tsk);
			}
			
			return tasks;

		} catch (ClientProtocolException e) {

			// If exception, print stack trace
			e.printStackTrace();

		} catch (IOException e) {

			// If exception, print stack trace
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return null;

	}

	/**
	 * default constructor
	 */
	public DBFunctions() {

	}

	public static void Main(String[] args) {

	}

}
