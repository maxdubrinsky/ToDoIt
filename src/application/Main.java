package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// This defines gui as a type of BorderPane
			BorderPane gui = new BorderPane();
			Scene scene = new Scene(gui, 600,600);


			// Use the right side of GUI for a flow pane
			gui.setRight(addFlowPane());

			// Use the center side of the GUI for a VBox
			gui.setCenter(addVBox());

			gui.setBottom(addHBox());

			//Makes use of the css sheet
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//Makes window show and names the window
			primaryStage.setScene(scene);
			primaryStage.setTitle("ToDoIt");
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}



	public VBox addVBox() {

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




	public FlowPane addFlowPane() {

		// Setting up Flowpane
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(43, 10, 5, 10));
		flow.setVgap(12);
		flow.setHgap(0);
		flow.setPrefWrapLength(170); 


		// This makes the flowpane work with the CSS
		flow.getStyleClass().addAll("pane", "flow-tile");



		// Add four buttons to the GUI
		flow.getChildren().add(new Button("Add Task"));
		
		flow.getChildren().add(new Button("Remove Task"));
		
		flow.getChildren().add(new Button("Edit Task"));
		
		flow.getChildren().add(new Button("View Task"));


		// Loads images maybe down the road
		/*ImageView pages[] = new ImageView[4];
	    for (int i=0 ; i<4 ; i++) {
	        pages[i] = new ImageView();
	        flow.getChildren().add(pages[i]);
	    }
		 */

		return flow;
	}


	public HBox addHBox() {

		// Setting up HBox
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(0, 0, 20, 0));
		hbox.setAlignment(Pos.CENTER);

		// Make HBOX use CSS
		hbox.getStyleClass().addAll("pane", "hbox");
		
		// Rough Time stamp
		hbox.getChildren().add(new Label("Sunday March 1st, 2015"));


		return hbox;
	}




	public static void main(String[] args) {
		launch(args);
	}
}
