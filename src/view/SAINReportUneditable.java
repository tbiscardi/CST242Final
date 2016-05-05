package view;

import java.util.ArrayList;

import handling.GlobalVariables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SAINReportUneditable extends View {

	private Label nameLabel, name, idLabel, id, gpaLabel,
			gpa, majorLabel, major;
	private ObservableList<String> items;
	private ListView<String> takenList, takingList, neededList, otherList, failedList;

	public SAINReportUneditable(Stage stage) {
		super(stage, new VBox(10), GlobalVariables.width * 2,
				GlobalVariables.height * 2);
		obsArr = new ArrayList<>();
		VBox vb = (VBox) getRoot();
		vb.setStyle("-fx-alignment: center center");
		
		GridPane gp = new GridPane();
		nameLabel = new Label("Name:");
		name = new Label();
		
		idLabel = new Label("ID:");
		id = new Label();
		
		gpaLabel = new Label("GPA:");
		gpa = new Label();
		
		majorLabel = new Label("Major:");
		major = new Label();
		
		GridPane.setConstraints(nameLabel, 0, 0);
		GridPane.setConstraints(name, 1, 0);
		GridPane.setConstraints(idLabel, 0, 1);
		GridPane.setConstraints(id, 1, 1);
		GridPane.setConstraints(gpaLabel, 0, 2);
		GridPane.setConstraints(gpa, 1, 2);
		GridPane.setConstraints(majorLabel, 0, 3);
		GridPane.setConstraints(major, 1, 3);
		
		gp.getChildren().addAll(nameLabel, name, idLabel, id, gpaLabel, gpa, majorLabel, major);
		gp.setAlignment(Pos.TOP_CENTER);
		
		HBox coursePane1 = new HBox(10);
		HBox coursePane2 = new HBox(10);
		
		takenList = new ListView<String>();
		takenList.setItems(items);
		
		takingList = new ListView<String>();
		takingList.setItems(items);
		
		otherList = new ListView<String>();
		otherList.setItems(items);
		
		failedList = new ListView<String>();
		failedList.setItems(items);
		
		neededList = new ListView<String>();
		neededList.setItems(items);
		
		coursePane1.getChildren().addAll(takenList, otherList, failedList);
		coursePane1.setAlignment(Pos.CENTER);
		coursePane2.getChildren().addAll(takingList, neededList);
		coursePane2.setAlignment(Pos.CENTER);
		
		vb.getChildren().addAll(gp, coursePane1, coursePane2);
		stage.setScene(this);
		stage.setResizable(false);
		stage.setTitle("SAIN Report");
		stage.show();
		
	}
	
	public void setTakenCoursesList (ArrayList<String> cTakenList) {
		items = FXCollections.observableArrayList (cTakenList);
	}
	
	public void setTakingCoursesList (ArrayList<String> cTakingList) {
		items = FXCollections.observableArrayList (cTakingList);
	}
	
	public void setOtherCoursesList (ArrayList<String> cOtherList) {
		items = FXCollections.observableArrayList (cOtherList);
	}
	
	public void setFailedCoursesList (ArrayList<String> cFailedList) {
		items = FXCollections.observableArrayList (cFailedList);
	}
	
	public void setNeededCoursesList (ArrayList<String> cNeededList) {
		items = FXCollections.observableArrayList (cNeededList);
	}
	
	public void setName(String name) {
		this.name.setText("\t\t\t\t" + name);
	}
	
	public void setId(String id) {
		this.id.setText("\t\t\t\t" + id);
	}
	
	public void setGpa(double gpa) {
		this.gpa.setText("\t\t\t\t" + Double.toString(gpa));
	}
	
	public void setMajor(String major) {
		this.major.setText("\t\t\t\t" + major);
	}

}
