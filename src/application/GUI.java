package application;

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
			}
		});

		editTaskBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Edit Task Event");
			}
		});

		viewTaskBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("View Task Event");
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

		// Rough Time stamp
		Calendar calendar = new GregorianCalendar();		

		hbox.getChildren().add(new Label(calendar.getTime().toString()));


		return hbox;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
