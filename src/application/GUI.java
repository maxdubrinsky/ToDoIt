package application;

import java.time.LocalDate;
import java.time.Month;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;




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
			addTaskStage.setHeight(500);
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
	 * @return VBox required for VBox
	 */
	public VBox addVBoxAddTask() {


		VBox vbox = new VBox(8);
		vbox.setPadding(new Insets(6));
		vbox.setSpacing(4);

		vbox.getStyleClass().addAll("pane", "vboxaddtask");

		Label taskTitle = new Label("Task Title");
		Label textAreaTitle = new Label("Task Discription");
		Label hboxStartAndEndDateTitle = new Label("Start Date                                     End Date");

		// Add a text field for the title
		TextField taskTitleBox = new TextField();
		taskTitleBox.setMaxWidth(300);
		taskTitleBox.setMinWidth(150);
		addTextLimiter(taskTitleBox, 50);

		// Add a text box for description
		TextArea textArea = new TextArea();
		textArea.setPrefRowCount(5);    



		// This is all HBox for the buttons submit and clear
		HBox hboxButtons = new HBox();
		hboxButtons.setAlignment(Pos.CENTER);
		hboxButtons.setSpacing(12);


		Button submit = new Button("Submit");
		Button clear = new Button("Clear");

		hboxButtons.getChildren().addAll(submit,clear);


		// This is the HBox for the start time
		HBox hboxStartAndEndDate = new HBox();
		hboxStartAndEndDate.setAlignment(Pos.BASELINE_LEFT);
		hboxStartAndEndDate.setSpacing(12);

		ComboBox<String> startTimeMonthComboBox = new ComboBox();
		startTimeMonthComboBox.getItems().addAll(
				"Jan",
				"Feb",
				"Mar",
				"Apr",
				"May",
				"Jun",
				"Jul",
				"Aug",
				"Sep",
				"Oct",
				"Nov",
				"Dec"
				);

		TextField taskStartTimeDate = new TextField();
		taskStartTimeDate.setMaxWidth(40);
		taskStartTimeDate.setMinWidth(40);
		addTextLimiter(taskStartTimeDate, 2);

		TextField taskStartTimeYear = new TextField();
		taskStartTimeYear.setMaxWidth(80);
		taskStartTimeYear.setMinWidth(80);
		addTextLimiter(taskStartTimeYear, 4);
		
		ComboBox<String> endTimeMonthComboBox = new ComboBox();
		endTimeMonthComboBox.getItems().addAll(
				"Jan",
				"Feb",
				"Mar",
				"Apr",
				"May",
				"Jun",
				"Jul",
				"Aug",
				"Sep",
				"Oct",
				"Nov",
				"Dec"
				);

		TextField taskEndTimeDate = new TextField();
		taskEndTimeDate.setMaxWidth(40);
		taskEndTimeDate.setMinWidth(40);
		addTextLimiter(taskEndTimeDate, 2);

		TextField taskEndTimeYear = new TextField();
		taskEndTimeYear.setMaxWidth(80);
		taskEndTimeYear.setMinWidth(80);
		addTextLimiter(taskEndTimeYear, 4);



		hboxStartAndEndDate.getChildren().addAll(startTimeMonthComboBox, taskStartTimeDate, taskStartTimeYear,endTimeMonthComboBox,taskEndTimeDate,taskEndTimeYear);

		Label hboxStartAndEndTimeTitle = new Label("Start Time                                     End Time");

		// HBOX for Start Time and End Time
		HBox hboxStartAndEndTime = new HBox();
		hboxStartAndEndTime.setAlignment(Pos.BASELINE_LEFT);
		hboxStartAndEndTime.setSpacing(12);

		TextField taskStartTimeHour = new TextField();
		taskStartTimeHour.setMaxWidth(64);
		taskStartTimeHour.setMinWidth(64);
		addTextLimiter(taskStartTimeHour, 2);

		TextField taskStartTimeMin = new TextField();
		taskStartTimeMin.setMaxWidth(64);
		taskStartTimeMin.setMinWidth(64);
		addTextLimiter(taskStartTimeMin, 2);

		ComboBox<String> startTimeAmPmComboBox = new ComboBox();
		startTimeAmPmComboBox.getItems().addAll(
				"AM",
				"PM"
				);

		TextField taskEndTimeHour = new TextField();
		taskEndTimeHour.setMaxWidth(64);
		taskEndTimeHour.setMinWidth(64);
		addTextLimiter(taskEndTimeHour, 2);

		TextField taskEndTimeMin = new TextField();
		taskEndTimeMin.setMaxWidth(64);
		taskEndTimeMin.setMinWidth(64);
		addTextLimiter(taskEndTimeMin, 2);

		ComboBox<String> endTimeAmPmComboBox = new ComboBox();
		endTimeAmPmComboBox.getItems().addAll(
				"AM",
				"PM"
				);


		hboxStartAndEndTime.getChildren().addAll(taskStartTimeHour, taskStartTimeMin, startTimeAmPmComboBox,taskEndTimeHour,taskEndTimeMin,endTimeAmPmComboBox);


		// Make a new label for the 
		Label status = new Label();

		vbox.getChildren().add(taskTitle);
		vbox.getChildren().add(taskTitleBox);
		vbox.getChildren().add(textAreaTitle);
		vbox.getChildren().add(textArea);
		vbox.getChildren().add(hboxStartAndEndDateTitle);
		vbox.getChildren().add(hboxStartAndEndDate);
		vbox.getChildren().add(hboxStartAndEndTimeTitle);
		vbox.getChildren().add(hboxStartAndEndTime);
		vbox.getChildren().add(status);
		vbox.getChildren().add(hboxButtons);


		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if ((taskTitleBox.getText() != null && !taskTitleBox.getText().isEmpty())) {
					status.setText("Worked");
					System.out.println("I has text!");
					System.out.println(taskTitleBox.getText().toString());
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


	public static void addTextLimiter (final TextField textField, final int maxLength) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> oldval,
					String oldValue, String newValue) {
				if (textField.getText().length() > maxLength) {
					String aString = textField.getText().substring(0, maxLength);
					textField.setText(aString);
				}

			}

		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}

