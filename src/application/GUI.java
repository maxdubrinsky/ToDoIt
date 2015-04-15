package application;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;

import org.controlsfx.control.Notifications;

import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Duration;

/**
 * This class is the GUI of the TODOIT application. It uses javafx to make a
 * working GUI.
 * 
 * @author Bryan Ehrke Spring 2015
 * @author Max D. Helped with thread support and Error Boxes.
 * 
 *
 */
public class GUI extends Application {

	boolean isATaskWindowOpen = false;
	boolean isTaskCompleted = false;

	private ArrayList<Task> upcommingTasks = new ArrayList<Task>();
	private ListView<String> list = new ListView<String>();
	private VBox primaryVBox;

	private Thread notification;

	/**
	 * This is the start method that starts the primary stage for the GUI to
	 * use. AKA the main window.
	 * 
	 * @param primaryStage
	 *            is the primary stage being used to start.
	 */
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

			// Makes use of the css sheet
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());

			// Setting up the arguments and setting for your main stage
			primaryStage.setScene(scene);
			primaryStage.setTitle("ToDoIt");
			primaryStage.setWidth(600);
			primaryStage.setHeight(600);
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("file:logo.png"));
			primaryStage.show();

			// Make the notification thread
			notification = new Thread(new Runnable() {
				@Override
				public void run() {
					// Create a list to store acknowledged tasks
					ArrayList<Task> acknowledgedTasks = new ArrayList<Task>();

					// Run FOREVERRRRRRRRR
					while (true) {
						try {
							// Check to make sure there are upcoming tasks
							if (!upcommingTasks.isEmpty()) {
								// Grab the next task (found by taking the next
								// on the
								// list compared to the acknowledged ones)
								Task next = upcommingTasks.get(0);

								// Check if the task has been acknowledged
								if (acknowledgedTasks.contains(next)) {
									Thread.sleep(5000);
									System.out
											.println("Notification thread sleeping...");
									continue;
								}

								// Start checking dates
								Calendar calendar = Calendar.getInstance();

								String taskEnd = String.valueOf(next.getEnd());
								int y = Integer.parseInt(taskEnd
										.substring(0, 4));
								int m = Integer.parseInt(taskEnd
										.substring(5, 7));
								int d = Integer.parseInt(taskEnd.substring(8,
										10));
								int h = Integer.parseInt(taskEnd.substring(11,
										13));
								int min = Integer.parseInt(taskEnd.substring(
										14, 16));

								// Check if the task is within 30 minutes of
								// completion
								// Can be tweaked with some fiddling
								if (y == calendar.get(Calendar.YEAR)
										&& m == calendar.get(Calendar.MONTH) + 1
										&& d == calendar
												.get(Calendar.DAY_OF_MONTH)
										&& (h - calendar
												.get(Calendar.HOUR_OF_DAY)) % 25 <= 1
										&& (min - calendar.get(Calendar.MINUTE)) % 61 <= 30) {

									// Put the notification on the JavaFX Thread
									// Stack of Fun
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											Notifications n = Notifications
													.create();
											n.title(next.getTitle());
											n.text(next.getDesc());
											n.hideAfter(Duration.INDEFINITE);
											n.showConfirm();
										}
									});

									// Acknowledge me!1!!1!!
									acknowledgedTasks.add(next);
								}
							}

							// Sleep for a mo'
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			});
			// Start the magic
			notification.setDaemon(true);
			notification.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This starts another scene that is triggered by a button in the main
	 * scene. This controls the AddTask Window.
	 * 
	 * @param addTaskStage
	 *            is the scene to be started.
	 */
	public void startAddTask(Stage addTaskStage) {
		try {

			// Sets up the add task manager frame
			BorderPane gui = new BorderPane();
			Scene scene = new Scene(gui, 500, 400);

			// Use the center of this window for adding a vbox
			gui.setCenter(addVBoxAddTask());

			// Makes the frame use the css sheet
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());

			// Sets the scene for the frame
			addTaskStage.setScene(scene);
			addTaskStage.setTitle("Add Task Manager");
			addTaskStage.setWidth(500);
			addTaskStage.setHeight(400);
			addTaskStage.setResizable(false);
			addTaskStage.getIcons().add(new Image("file:logo.png"));
			addTaskStage.show();

			addTaskStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					isATaskWindowOpen = false;
					updateTasks();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This starts another scene that is triggered by a button in the main
	 * scene. This controls the RemoveTask Window.
	 * 
	 * @param addRemoveStage
	 *            is the scene to be started.
	 */
	public void startRemoveTask(Stage addRemoveStage) {
		try {

			// Sets up the add task manager frame
			BorderPane gui = new BorderPane();
			Scene scene = new Scene(gui, 500, 400);

			gui.setCenter(addVBoxRemoveTask());

			// Makes the frame use the css sheet
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());

			// Sets the scene for the frame
			addRemoveStage.setScene(scene);
			addRemoveStage.setTitle("Remove Task Manager");
			addRemoveStage.setWidth(500);
			addRemoveStage.setHeight(400);
			addRemoveStage.setResizable(false);
			addRemoveStage.getIcons().add(new Image("file:logo.png"));
			addRemoveStage.show();

			addRemoveStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					isATaskWindowOpen = false;
					updateTasks();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This starts another scene that is triggered by a button in the main
	 * scene. This controls the EditTask Window.
	 * 
	 * @param addEditStage
	 *            is the Stage to be started.
	 */
	public void startEditTask(Stage addEditStage) {
		try {

			// Sets up the add task manager frame
			BorderPane gui = new BorderPane();
			Scene scene = new Scene(gui, 500, 400);

			gui.setCenter(addVBoxEditTask());

			// Makes the frame use the css sheet
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());

			// Sets the scene for the frame
			addEditStage.setScene(scene);
			addEditStage.setTitle("Edit Task Manager");
			addEditStage.setWidth(500);
			addEditStage.setHeight(600);
			addEditStage.setResizable(false);
			addEditStage.getIcons().add(new Image("file:logo.png"));
			addEditStage.show();

			addEditStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					isATaskWindowOpen = false;
					updateTasks();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This starts the view task window triggered by a button in the main
	 * window.
	 * 
	 * @param addViewStage
	 *            is the stage to be started.
	 */
	public void startViewTask(Stage addViewStage) {
		try {

			// Sets up the add task manager frame
			BorderPane gui = new BorderPane();
			Scene scene = new Scene(gui, 500, 400);

			gui.setCenter(addVBoxViewTask());

			// Makes the frame use the css sheet
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());

			// Sets the scene for the frame
			addViewStage.setScene(scene);
			addViewStage.setTitle("View Task Manager");
			addViewStage.setWidth(500);
			addViewStage.setHeight(500);
			addViewStage.setResizable(false);
			addViewStage.getIcons().add(new Image("file:logo.png"));
			addViewStage.show();

			addViewStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					isATaskWindowOpen = false;
					updateTasks();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to add a VBox in the Add Task GUI
	 * 
	 * @return VBox required for VBox
	 */
	public VBox addVBoxAddTask() {

		// Sets up VBOX
		VBox vbox = new VBox(8);
		vbox.setPadding(new Insets(6));
		vbox.setSpacing(8);

		// Makes use of Css sheet
		vbox.getStyleClass().addAll("pane", "vboxaddtask");

		Label taskTitleAndPriorty = new Label(
				"Task Title                        Task Priorty");
		Label textAreaTitle = new Label("Task Discription");

		// Add a text field for the title and priorty combo box in a h box
		HBox hBoxTitleAndPriority = new HBox();
		hBoxTitleAndPriority.setSpacing(12);

		TextField taskTitleBox = new TextField();
		taskTitleBox.setMaxWidth(300);
		taskTitleBox.setMinWidth(150);
		addTextLimiter(taskTitleBox, 50);

		ComboBox<String> priortyComboBox = new ComboBox<String>();
		priortyComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7",
				"8", "9", "10");
		priortyComboBox.getSelectionModel().select(4);

		hBoxTitleAndPriority.getChildren()
				.addAll(taskTitleBox, priortyComboBox);
		// End Hbox

		// Add a text box for description
		TextArea textArea = new TextArea();
		textArea.setPrefRowCount(5);
		textArea.setWrapText(true);

		// This is all HBox for the buttons submit and clear
		HBox hboxButtons = new HBox();
		hboxButtons.setAlignment(Pos.CENTER);
		hboxButtons.setSpacing(12);

		Button submit = new Button("Submit");
		Button clear = new Button("Clear");

		hboxButtons.getChildren().addAll(submit, clear);
		// End

		// This is the HBox for the end time and title
		Label hboxEndDateAndTimeTitle = new Label(
				"End Date                                      End Time");
		HBox hBoxEndDate = new HBox();
		hBoxEndDate.setAlignment(Pos.BASELINE_LEFT);
		hBoxEndDate.setSpacing(12);

		ComboBox<String> endTimeMonthComboBox = new ComboBox<String>();
		endTimeMonthComboBox.getItems().addAll("Jan", "Feb", "Mar", "Apr",
				"May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

		TextField taskEndTimeDate = new TextField();
		taskEndTimeDate.setMaxWidth(40);
		taskEndTimeDate.setMinWidth(40);
		addTextLimiter(taskEndTimeDate, 2);

		TextField taskEndTimeYear = new TextField();
		taskEndTimeYear.setMaxWidth(80);
		taskEndTimeYear.setMinWidth(80);
		addTextLimiter(taskEndTimeYear, 4);

		TextField taskEndTimeHour = new TextField();
		taskEndTimeHour.setMaxWidth(64);
		taskEndTimeHour.setMinWidth(64);
		addTextLimiter(taskEndTimeHour, 2);

		TextField taskEndTimeMin = new TextField();
		taskEndTimeMin.setMaxWidth(64);
		taskEndTimeMin.setMinWidth(64);
		addTextLimiter(taskEndTimeMin, 2);

		ComboBox<String> endTimeAmPmComboBox = new ComboBox<String>();
		endTimeAmPmComboBox.getItems().addAll("AM", "PM");

		hBoxEndDate.getChildren().addAll(endTimeMonthComboBox, taskEndTimeDate,
				taskEndTimeYear, taskEndTimeHour, taskEndTimeMin,
				endTimeAmPmComboBox);

		vbox.getChildren().addAll(taskTitleAndPriorty, hBoxTitleAndPriority,
				textAreaTitle, textArea, hboxEndDateAndTimeTitle, hBoxEndDate,
				hboxButtons);

		// This checks for faulty input then also submits the input to the
		// database via the controller.
		// If faulty input is found a error box is sent.
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if ((taskTitleBox.getText() != null && !taskTitleBox.getText()
						.isEmpty())
						&& (!taskEndTimeYear.getText().isEmpty() && taskEndTimeYear
								.getText() != null)
						&& (!taskEndTimeDate.getText().isEmpty() && taskEndTimeDate
								.getText() != null)
						&& (!taskEndTimeHour.getText().isEmpty() && taskEndTimeHour
								.getText() != null)
						&& (!taskEndTimeMin.getText().isEmpty() && taskEndTimeMin
								.getText() != null)
						&& endTimeAmPmComboBox.getValue() != null
						&& endTimeMonthComboBox.getValue() != null
						&& !isTaskCompleted) {

					Controller.addTask(taskTitleBox.getText(),
							textArea.getText(), priortyComboBox.getValue(),
							taskEndTimeYear.getText(),
							endTimeMonthComboBox.getValue(),
							taskEndTimeDate.getText(),
							taskEndTimeHour.getText(),
							taskEndTimeMin.getText(),
							endTimeAmPmComboBox.getValue());

					isTaskCompleted = true;
					isATaskWindowOpen = false;
					updateTasks();

					// Make a short sleep
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					// Closes the sub window.
					Node source = (Node) e.getSource();
					Stage stage = (Stage) source.getScene().getWindow();
					stage.close();

				} else {
					// Alert Box setup
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Missing Parameters");
					alert.setHeaderText("Input Error");
					alert.setContentText("Your task is missing some input. Please correct and resubmit.");
					alert.setResizable(false);
					alert.showAndWait();
				}
				// synchronized(taskUpdateThread) {
				// notify();
				// }
			}
		});

		// Clears all the text fields when button is pushed
		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				taskTitleBox.clear();
				textArea.clear();
				priortyComboBox.getSelectionModel().clearSelection();
				taskEndTimeYear.clear();
				endTimeMonthComboBox.getSelectionModel().clearSelection();
				taskEndTimeDate.clear();
				taskEndTimeHour.clear();
				taskEndTimeMin.clear();
				endTimeAmPmComboBox.getSelectionModel().clearSelection();
			}
		});

		return vbox;
	}

	/**
	 * This is elements for the sub window for Remove Task. All inside a VBOX.
	 * 
	 * @return VBox containing all the remove task elements.
	 */
	public VBox addVBoxRemoveTask() {
		// Set up Vbox
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(12));
		vbox.setSpacing(8);

		// Make Vbox use the Css sheet
		vbox.getStyleClass().addAll("pane", "vboxremovetask");

		Label removeTask = new Label("Remove Task:");

		// This is the list view with filler tasks
		ListView<String> list = new ListView<String>();

		list.getStyleClass().addAll("pane", "listview");

		ArrayList<String> taskListString = new ArrayList<String>();

		for (int i = 0; i < upcommingTasks.size(); i++)
			taskListString.add(upcommingTasks.get(i).toString());

		// This is the populated lists
		ObservableList<String> items = FXCollections
				.observableArrayList(taskListString);

		list.setItems(items);

		// This is all HBox for the buttons remove task and deselect
		HBox hboxButtons = new HBox();
		hboxButtons.setAlignment(Pos.CENTER);
		hboxButtons.setSpacing(12);

		Button remove = new Button("Remove Task");
		Button deselect = new Button("Deselect");

		hboxButtons.getChildren().addAll(remove, deselect);

		remove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String toRemove = list.getSelectionModel().getSelectedItem();
				Controller.deleteTask(findMatchingTask(toRemove));
				items.remove(toRemove);
				list.setItems(items);
				vbox.requestLayout();

				isTaskCompleted = true;
				isATaskWindowOpen = false;
				updateTasks();

				// Make a short sleep
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				// Closes the sub window.
				Node source = (Node) e.getSource();
				Stage stage = (Stage) source.getScene().getWindow();
				stage.close();

			}
		});

		deselect.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				list.getSelectionModel().clearSelection();
			}
		});

		vbox.getChildren().addAll(removeTask, list, hboxButtons);

		return vbox;
	}

	/**
	 * This is the VBox for the Edit Task window. Which is the all of the
	 * window.
	 * 
	 * @return VBOX returns a VBOX with such inside elements
	 */
	public VBox addVBoxEditTask() {
		// Set up Vbox
		VBox vbox = new VBox();
		// BLAZE IT
		vbox.setPadding(new Insets(12));
		vbox.setSpacing(8);

		// Make Vbox use the Css sheet
		vbox.getStyleClass().addAll("pane", "vboxedittask");

		Label editTask = new Label("Edit Task:");

		// This is the list view with filler tasks
		ListView<String> list = new ListView<String>();

		list.getStyleClass().addAll("pane", "listview");

		ArrayList<String> taskListString = new ArrayList<String>();

		for (int i = 0; i < upcommingTasks.size(); i++)
			taskListString.add(upcommingTasks.get(i).toString());

		// This is the populated lists
		ObservableList<String> items = FXCollections
				.observableArrayList(taskListString);

		list.setItems(items);
		list.setMinHeight(200);
		list.setMaxHeight(200);

		// Add a text field for the title and priorty combo box in a h box
		Label taskTitleAndPriorty = new Label(
				"Task Title                        Task Priorty");
		HBox hBoxTitleAndPriority = new HBox();
		hBoxTitleAndPriority.setSpacing(12);

		TextField taskTitleBox = new TextField();
		taskTitleBox.setMaxWidth(300);
		taskTitleBox.setMinWidth(150);
		addTextLimiter(taskTitleBox, 50);

		ComboBox<String> priortyComboBox = new ComboBox<String>();
		priortyComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7",
				"8", "9", "10");

		hBoxTitleAndPriority.getChildren()
				.addAll(taskTitleBox, priortyComboBox);
		// End Hbox

		// Add a text box for description
		Label textAreaTitle = new Label("Task Description");
		TextArea textArea = new TextArea();
		textArea.setPrefRowCount(5);
		textArea.setWrapText(true);

		// This is the HBox for the end time
		HBox hboxStartAndEndDate = new HBox();
		hboxStartAndEndDate.setAlignment(Pos.BASELINE_LEFT);
		hboxStartAndEndDate.setSpacing(12);

		// This is the Label for the Date Input for the user
		Label hboxEndDateAndTimeTitle = new Label(
				"End Date                                      End Time");

		ComboBox<String> endTimeMonthComboBox = new ComboBox<String>();
		endTimeMonthComboBox.getItems().addAll("Jan", "Feb", "Mar", "Apr",
				"May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

		TextField taskEndTimeDate = new TextField();
		taskEndTimeDate.setMaxWidth(40);
		taskEndTimeDate.setMinWidth(40);
		addTextLimiter(taskEndTimeDate, 2);

		TextField taskEndTimeYear = new TextField();
		taskEndTimeYear.setMaxWidth(80);
		taskEndTimeYear.setMinWidth(80);
		addTextLimiter(taskEndTimeYear, 4);

		TextField taskEndTimeHour = new TextField();
		taskEndTimeHour.setMaxWidth(64);
		taskEndTimeHour.setMinWidth(64);
		addTextLimiter(taskEndTimeHour, 2);

		TextField taskEndTimeMin = new TextField();
		taskEndTimeMin.setMaxWidth(64);
		taskEndTimeMin.setMinWidth(64);
		addTextLimiter(taskEndTimeMin, 2);

		ComboBox<String> endTimeAmPmComboBox = new ComboBox<String>();
		endTimeAmPmComboBox.getItems().addAll("AM", "PM");

		hboxStartAndEndDate.getChildren().addAll(endTimeMonthComboBox,
				taskEndTimeDate, taskEndTimeYear, taskEndTimeHour,
				taskEndTimeMin, endTimeAmPmComboBox);

		// This is all HBox for the buttons update and clear
		HBox hboxButtons = new HBox();
		hboxButtons.setAlignment(Pos.CENTER);
		hboxButtons.setSpacing(12);

		Button update = new Button("Update Task");
		Button clear = new Button("Clear");

		hboxButtons.getChildren().addAll(update, clear);

		// Checks for faulty input and updates the task to the database via
		// controller
		// If faulty an error box is sent.
		update.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if ((taskTitleBox.getText() != null && !taskTitleBox.getText()
						.isEmpty())
						&& (!taskEndTimeYear.getText().isEmpty() && taskEndTimeYear
								.getText() != null)
						&& (!taskEndTimeDate.getText().isEmpty() && taskEndTimeDate
								.getText() != null)
						&& (!taskEndTimeHour.getText().isEmpty() && taskEndTimeHour
								.getText() != null)
						&& (!taskEndTimeMin.getText().isEmpty() && taskEndTimeMin
								.getText() != null)
						&& (textArea.getText() != null && !textArea.getText()
								.isEmpty())
						&& endTimeAmPmComboBox.getValue() != null
						&& endTimeMonthComboBox.getValue() != null
						&& !isTaskCompleted) {

					Task taskToUpdate = new Task();
					String toUpdate = list.getSelectionModel()
							.getSelectedItem();
					taskToUpdate = findMatchingTask(toUpdate);
					Controller.modifyTask(taskToUpdate, taskTitleBox.getText(),
							textArea.getText(), priortyComboBox.getValue(),
							taskEndTimeYear.getText(),
							endTimeMonthComboBox.getValue(),
							taskEndTimeDate.getText(),
							taskEndTimeHour.getText(),
							taskEndTimeMin.getText(),
							endTimeAmPmComboBox.getValue());

					isTaskCompleted = true;
					isATaskWindowOpen = false;
					updateTasks();

					// Make a short sleep
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					// Closes the sub window.
					Node source = (Node) e.getSource();
					Stage stage = (Stage) source.getScene().getWindow();
					stage.close();

				}
				// Alert Box setup
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Missing Parameters");
					alert.setHeaderText("Input Error");
					alert.setContentText("Your task is missing some input. Please correct and resubmit.");
					alert.setResizable(false);
					alert.showAndWait();
				}

			}
		});
		// Clears all text fields when button is pushed
		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				list.getSelectionModel().clearSelection();
				taskTitleBox.clear();
				textArea.clear();
				priortyComboBox.getSelectionModel().clearSelection();
				taskEndTimeYear.clear();
				endTimeMonthComboBox.getSelectionModel().clearSelection();
				taskEndTimeDate.clear();
				taskEndTimeHour.clear();
				taskEndTimeMin.clear();
				endTimeAmPmComboBox.getSelectionModel().clearSelection();
			}
		});

		// Checks the selected list element
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// Create a temporary task reference
				Task temp = findMatchingTask(list.getSelectionModel()
						.getSelectedItem());

				// Start updating the input fields to match the task's existing
				// parameters
				taskTitleBox.setText(temp.getTitle());
				textArea.setText(temp.getDesc());
				priortyComboBox.getSelectionModel().select(
						temp.getPriority() - 1);

				// Grab a copy of the date for parsing reasons
				String date = String.valueOf(temp.getEnd());
				System.out.println(date);
				taskEndTimeYear.setText(date.substring(0, 4));
				endTimeMonthComboBox.getSelectionModel().select(
						Integer.parseInt(date.substring(5, 7)) - 1);
				taskEndTimeDate.setText(date.substring(8, 10));

				// Grab the hour piece of the task
				int hour = Integer.parseInt(date.substring(11, 13));
				endTimeAmPmComboBox.getSelectionModel().select(hour / 12);

				// Parse hour for 12-hour clocks
				hour = (hour > 12) ? hour - 12 : hour;
				taskEndTimeHour.setText(Integer.toString(hour));
				taskEndTimeMin.setText(date.substring(14, 16));

				// Update the main view
				vbox.requestLayout();
			}

		});

		vbox.getChildren().addAll(editTask, list, taskTitleAndPriorty,
				hBoxTitleAndPriority, textAreaTitle, textArea,
				hboxEndDateAndTimeTitle, hboxStartAndEndDate, hboxButtons);

		return vbox;

	}

	/**
	 * This is the VBox that has all the needed elements for View Task Window.
	 * 
	 * @return VBOX which is a VBox that has all the elements needed for View
	 *         Task.
	 */
	public VBox addVBoxViewTask() {

		// Set up Vbox
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(12));
		vbox.setSpacing(8);

		// Make Vbox use the Css sheet
		vbox.getStyleClass().addAll("pane", "vboxviewtask");

		Label viewTaskTitle = new Label("View Task:");

		// This is the list view with filler tasks
		ListView<String> list = new ListView<String>();

		list.getStyleClass().addAll("pane", "listview");

		ArrayList<String> taskListString = new ArrayList<String>();

		for (int i = 0; i < upcommingTasks.size(); i++)
			taskListString.add(upcommingTasks.get(i).toString());

		// This is the populated lists
		ObservableList<String> items = FXCollections
				.observableArrayList(taskListString);

		list.setItems(items);
		list.setMinHeight(250);
		list.setMaxHeight(250);

		Label viewTaskDiscTitle = new Label("Task Discription:");

		// Add a text box for description
		TextArea textArea = new TextArea();
		textArea.setPrefRowCount(5);
		textArea.setWrapText(true);

		list.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {

					@Override
					public void changed(
							ObservableValue<? extends String> observalable,
							String oldval, String newval) {
						textArea.setText(findMatchingTask(newval).getDesc());
					}

				});

		// The upcoming task label at top
		vbox.getChildren().addAll(viewTaskTitle, list, viewTaskDiscTitle,
				textArea);

		return vbox;
	}

	/**
	 * This contrains the primary VBOX used in the main window. It has a Label
	 * and a list pane.
	 * 
	 * @return VBOX that contains a label and list pane on main window.
	 */
	public VBox addVBoxPrimary() {

		// Set up Vbox
		primaryVBox = new VBox();
		primaryVBox.setPadding(new Insets(12));
		primaryVBox.setSpacing(8);

		// Make Vbox use the Css sheet
		primaryVBox.getStyleClass().addAll("pane", "vbox");

		// The upcoming task label at top
		primaryVBox.getChildren().add(new Label("Upcoming Tasks:"));

		// This is the list view with filler tasks

		list.getStyleClass().addAll("pane", "listview");

		updateTasks();

		// Add to Vbox
		primaryVBox.getChildren().add(list);

		return primaryVBox;
	}

	/**
	 * This flow pane holds the four buttons in the main window.
	 * 
	 * @return FlowPane returns a flow pane with four buttons on it.
	 */
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
		flow.getChildren().addAll(addTaskBut, removeTaskBut, editTaskBut,
				viewTaskBut);

		// Add the four button listeners
		addTaskBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (isATaskWindowOpen == false) {
					Stage addTaskStage = new Stage();
					startAddTask(addTaskStage);
					isATaskWindowOpen = true;
					isTaskCompleted = false;
				}
				System.out.println("Add Task Event");
			}
		});

		removeTaskBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (isATaskWindowOpen == false) {
					Stage addRemoveStage = new Stage();
					startRemoveTask(addRemoveStage);
					isATaskWindowOpen = true;
					isTaskCompleted = false;
				}
				System.out.println("Remove Task Event");

			}
		});

		editTaskBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Edit Task Event");
				if (isATaskWindowOpen == false) {
					Stage addEditStage = new Stage();
					startEditTask(addEditStage);
					isATaskWindowOpen = true;
					isTaskCompleted = false;
				}
			}
		});

		viewTaskBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("View Task Event");
				if (isATaskWindowOpen == false) {
					Stage addViewStage = new Stage();
					startViewTask(addViewStage);
					isATaskWindowOpen = true;
					isTaskCompleted = false;
				}
			}
		});

		return flow;
	}

	/**
	 * This Hbox holds the date at the bottom of the window.
	 * 
	 * @return HBOX this Hbox contains the data at the bottom on the main
	 *         window.
	 */
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

		hbox.getChildren().add(
				new Label(theMonth.toString() + " " + theDay + " " + theYear));

		return hbox;
	}

	/**
	 *
	 * This method updates the list of tasks that the GUI uses. Then sets them.
	 *
	 */
	private void updateTasks() {
		upcommingTasks = Controller.upcomingTasks();
		ObservableList<String> taskStrings = FXCollections
				.observableArrayList();
		for (Task t : upcommingTasks)
			taskStrings.add(t.toString());
		list.setItems(taskStrings);
		primaryVBox.requestLayout();
		System.out.println("Tasks updated");
	}

	/**
	 * This finds a matching task based on the String passed into the method.
	 * 
	 * @param stringToMatch
	 *            is the string used to find its matching Task object.
	 * 
	 * @return Task is the task that is matched with the string.
	 */
	public Task findMatchingTask(String stringToMatch) {

		Task matchingTask = new Task();

		for (Task aTask : upcommingTasks) {
			if (stringToMatch.equals(aTask.toString())) {
				matchingTask = aTask;
			}
		}
		return matchingTask;
	}

	/**
	 * This method limited a given text field by the given character amount.
	 * 
	 * @param textField
	 *            is the field to be limited.
	 * 
	 * @param maxLength
	 *            is the max length to be limited by.
	 */
	public static void addTextLimiter(final TextField textField,
			final int maxLength) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> oldval,
					String oldValue, String newValue) {
				if (textField.getText().length() > maxLength) {
					String aString = textField.getText()
							.substring(0, maxLength);
					textField.setText(aString);
				}

			}

		});
	}

	@SuppressWarnings("deprecation")
	@Override
	public void stop() throws Exception {
		notification.stop();
		super.stop();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
