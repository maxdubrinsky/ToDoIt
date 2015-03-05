package application;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import javafx.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;




public class GUI extends Application {


	@Override
	public void start(Stage primaryStage) {
		try {
			// This defines gui as a type of BorderPane
			BorderPane gui = new BorderPane();
			Scene scene = new Scene(gui, 600, 600);

			// Determine which parts of the GUI are used for what
			gui.setCenter(addVBoxPrimary());
			gui.setBottom(addHBoxPrimary());
			gui.setRight(addFlowPanePrimary());

			//Makes use of the css sheet
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Setting up the arguments and setting for your main stage
			primaryStage.setScene(scene);
			primaryStage.setTitle("ToDoIt");
			primaryStage.setWidth(600);
			primaryStage.setHeight(600);
			primaryStage.setResizable(false);
			primaryStage.show();



		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void startAddTask(Stage addTaskStage){
		try {
			// Sets up the add task manager frame
			BorderPane gui = new BorderPane();
			Scene scene = new Scene(gui, 500, 400);
			
			gui.setCenter(addVBoxAddTask());

			// Makes the frame use the css sheet
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Sets the scene for the frame
			addTaskStage.setScene(scene);
			addTaskStage.setTitle("Add Task Manager");
			addTaskStage.setWidth(500);
			addTaskStage.setHeight(400);
			addTaskStage.setResizable(false);
			addTaskStage.show();

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startRemoveTask(Stage addRemoveStage){
		try {
			// Sets up the add task manager frame
			BorderPane gui = new BorderPane();
			Scene scene = new Scene(gui, 500, 400);
		

			// Makes the frame use the css sheet
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Sets the scene for the frame
			addRemoveStage.setScene(scene);
			addRemoveStage.setTitle("Remove Task Manager");
			addRemoveStage.setWidth(500);
			addRemoveStage.setHeight(400);
			addRemoveStage.setResizable(false);
			addRemoveStage.show();

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startEditTask(Stage addEditStage){
		try {
			// Sets up the add task manager frame
			BorderPane gui = new BorderPane();
			Scene scene = new Scene(gui, 500, 400);
			
			// Makes the frame use the css sheet
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Sets the scene for the frame
			addEditStage.setScene(scene);
			addEditStage.setTitle("Edit Task Manager");
			addEditStage.setWidth(500);
			addEditStage.setHeight(400);
			addEditStage.setResizable(false);
			addEditStage.show();

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startViewTask(Stage addViewStage){
		try {
			// Sets up the add task manager frame
			BorderPane gui = new BorderPane();
			Scene scene = new Scene(gui, 500, 400);
			
	
			// Makes the frame use the css sheet
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Sets the scene for the frame
			addViewStage.setScene(scene);
			addViewStage.setTitle("View Task Manager");
			addViewStage.setWidth(500);
			addViewStage.setHeight(400);
			addViewStage.setResizable(false);
			addViewStage.show();

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to add a VBox in the Add Task GUI
	 * 
	 * @return VBox required for VBox
	 */
	public VBox addVBoxAddTask() {
		
		
		VBox vbox = new VBox();
		
		vbox.getStyleClass().addAll("pane", "vboxaddtask");
		
		Label title = new Label("Task Title");
		
		TextField taskName = new TextField();
		taskName.setMaxWidth(325);
		taskName.setMinWidth(150);

		
		Button submit = new Button("Submit");
		
		Label status = new Label();
		
		vbox.getChildren().add(title);
		vbox.getChildren().add(taskName);
		vbox.getChildren().add(submit);
		vbox.getChildren().add(status);
		
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			    public void handle(ActionEvent e) {
			        if ((taskName.getText() != null && !taskName.getText().isEmpty())) {
			            status.setText("Worked");
			            System.out.println("I has text!");
			            System.out.println(taskName.getText().toString());
			            //TODO put controller in this
			            
			       
			            // First arg being title of task, task disc, start date, start time, end date, end time
			            //control.addTask(taskName.getText(), );
			            
			        } else {
			            status.setText("You have not left a title.");
			        }
			     }
			 });
		
		return vbox;
	}
	
	
	public VBox addVBoxPrimary() {

		// Set up Vbox
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(12));
		vbox.setSpacing(8);

		// Make Vbox use the Css sheet
		vbox.getStyleClass().addAll("pane", "vbox");

		// The upcoming task label at top
		vbox.getChildren().add(new Label("Upcoming Tasks:"));

		// This is the list view with filler tasks
		ListView<String> list = new ListView<String>();

		list.getStyleClass().addAll("pane", "listview");

		// This is the populated lists.
		ObservableList<String> items =FXCollections.observableArrayList (
				"Task One", "Task Two", "Task Three", "Task Four");

		list.setItems(items);
		// Add to Vbox
		vbox.getChildren().add(list);
		
		


		return vbox;
	}




	public FlowPane addFlowPanePrimary() {

		// Setting up Flowpane
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(43, 10, 5, 10));
		flow.setVgap(12);
		flow.setHgap(0);
		flow.setPrefWrapLength(170); 


		// This makes the flowpane work with the CSS
		flow.getStyleClass().addAll("pane", "flow-tile");

		// Make the four buttons to add to GUI
		Button addTaskBut = new Button("Add Task");
		Button removeTaskBut = new Button("Remove Task");
		Button editTaskBut = new Button("Edit Task");
		Button viewTaskBut = new Button("View Task");

		// Add four buttons to the GUI
		flow.getChildren().add(addTaskBut);
		flow.getChildren().add(removeTaskBut);
		flow.getChildren().add(editTaskBut);
		flow.getChildren().add(viewTaskBut);

		// Add the four button listerners 
		addTaskBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Add Task Event");
				Stage addTaskStage = new Stage();
				startAddTask(addTaskStage);
			}
		});

		removeTaskBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Remove Task Event");
				Stage addRemoveStage = new Stage();
				startRemoveTask(addRemoveStage);
			}
		});

		editTaskBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Edit Task Event");
				Stage addEditStage = new Stage();
				startEditTask(addEditStage);
			}
		});

		viewTaskBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("View Task Event");
				Stage addViewStage = new Stage();
				startViewTask(addViewStage);
			}
		});

		return flow;
	}


	public HBox addHBoxPrimary() {

		// Setting up HBox
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(0, 0, 20, 0));
		hbox.setAlignment(Pos.CENTER);

		// Make HBOX use CSS
		hbox.getStyleClass().addAll("pane", "hbox");

		
		// Get a rough date stamp for GUI
		LocalDate currentDate = LocalDate.now();
		
		Month theMonth = currentDate.getMonth();
		int theYear = currentDate.getYear();
		int theDay = currentDate.getDayOfMonth();
		
		hbox.getChildren().add(new Label(theMonth.toString() + " " + theDay + " " + theYear));


		return hbox;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
