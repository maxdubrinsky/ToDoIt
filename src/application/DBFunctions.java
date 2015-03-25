package application;

import java.util.*;
import java.io.IOException;

import org.json.*;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


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
	public static void addTask(String title, long startTime, long endTime,
			String desc) {

		try {
			// Create http client
			HttpClient client = HttpClientBuilder.create().build();

			// Create url
			String url = "carbon.dubrinsky.com/add_task.php?title=" + title
					+ "&desc=" + desc + "&start=" + startTime + "&end="
					+ endTime;

			// Create Get Request
			HttpGet get = new HttpGet(url);

			// Execute request and capture response
			client.execute(get);

		} catch (ClientProtocolException e) {

			// If exception, print stack trace
			e.printStackTrace();

		} catch (IOException e) {

			// If exception, print stack trace
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
			long endTime, String desc) {

		try {
			// Create http client
			HttpClient client = HttpClientBuilder.create().build();

			// Create url
			String url = "carbon.dubrinsky.com/update_task.php?title=" + title
					+ "&desc=" + desc + "&start=" + startTime + "&end="
					+ endTime + "&taskID=" + taskId;

			// Create Get Request
			HttpGet get = new HttpGet(url);

			// Execute request and capture response
			client.execute(get);

		} catch (ClientProtocolException e) {

			// If exception, print stack trace
			e.printStackTrace();

		} catch (IOException e) {

			// If exception, print stack trace
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
			String url = "carbon.dubrinsky.com/delete_task.php?taskID="
					+ taskID;
			// Create Get Request
			HttpGet get = new HttpGet(url);

			// Execute request and capture response
			client.execute(get);

		} catch (ClientProtocolException e) {

			// If exception, print stack trace
			e.printStackTrace();

		} catch (IOException e) {

			// If exception, print stack trace
			e.printStackTrace();
		}

	}

	/**
	 * Returns up to the next 8 tasks in the table in order of start time
	 * 
	 * @return List<List<String>>
	 */
	public List<List<Object>> viewUpcoming() {
		
		try {
			// Create http client
			HttpClient client = HttpClientBuilder.create().build();

			// Create url
			String url = "carbon.dubrinsky.com/read.php";
					
			// Create Get Request
			HttpGet get = new HttpGet(url);

			// Execute request and capture response
			HttpResponse result = client.execute(get);
			
			ArrayList<ArrayList<Object>> tasks = new ArrayList<ArrayList<Object>>();
			
			JSONArray data = new JSONArray(result);
			JSONObject element;
			
			for(int i = 0; i < data.length(); i++) {
				element = data.getJSONObject(i);
				String title = element.getString("task_title");
				String desc = element.getString("task_desc");
				long start = element.getLong("start_time");
				long end = element.getLong("end_time");
				int id = element.getInt("task_id");
				
				ArrayList<Object> ele = new ArrayList<Object>();
				ele.add(title);
				ele.add(desc);
				ele.add(start);
				ele.add(end);
				ele.add(id);
				
				tasks.add(ele);
			}

		} catch (ClientProtocolException e) {

			// If exception, print stack trace
			e.printStackTrace();

		} catch (IOException e) {

			// If exception, print stack trace
			e.printStackTrace();
		} catch (JSONException e) {
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
