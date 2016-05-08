package view;

import java.util.ArrayList;

import handling.GlobalVariables;
import handling.GlobalVariables.Events;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
		super(stage, new VBox(10), GlobalVariables.width,
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
		
		Label takenL = new Label("Required Courses Taken:");
		takenList = new ListView<String>();
		VBox l1 = new VBox();
		l1.getChildren().addAll(takenL, takenList);
		l1.setAlignment(Pos.CENTER);
		
		Label takingL = new Label("Currently Taking:");
		takingList = new ListView<String>();
		VBox l2 = new VBox();
		l2.getChildren().addAll(takingL, takingList);
		l2.setAlignment(Pos.CENTER);
		
		Label otherL = new Label("Other Courses Taken:");
		otherList = new ListView<String>();
		VBox l3 = new VBox();
		l3.getChildren().addAll(otherL, otherList);
		l3.setAlignment(Pos.CENTER);
		
		Label failedL = new Label("Withdrawn/Failed:");
		failedList = new ListView<String>();
		VBox l4 = new VBox();
		l4.getChildren().addAll(failedL, failedList);
		l4.setAlignment(Pos.CENTER);
		
		Label neededL = new Label("Courses Needed:");
		neededList = new ListView<String>();
		VBox l5 = new VBox();
		l5.getChildren().addAll(neededL, neededList);
		l5.setAlignment(Pos.CENTER);
		
		takenL.setMaxWidth(200);
		takingL.setMaxWidth(200);
		otherL.setMaxWidth(200);
		failedL.setMaxWidth(200);
		neededL.setMaxWidth(200);
		
		coursePane1.getChildren().addAll(l1, l2, l3);
		coursePane1.setAlignment(Pos.CENTER);
		coursePane2.getChildren().addAll(l4, l5);
		coursePane2.setAlignment(Pos.CENTER);
		
		Button back = new Button("Return");
		back.setOnAction(e -> {
			NotifyObserver(Events.BACK_BUTTON);
		});
		
		vb.getChildren().addAll(gp, coursePane1, coursePane2, back);
		stage.setScene(this);
		stage.setResizable(false);
		stage.setTitle("SAIN Report");
		stage.show();
		
	}
	
	public void setTakenCoursesList (ArrayList<String> cTakenList) {
		items = FXCollections.observableArrayList (cTakenList);
		takenList.setItems(items);
	}
	
	public void setTakingCoursesList (ArrayList<String> cTakingList) {
		items = FXCollections.observableArrayList (cTakingList);
		takingList.setItems(items);
	}
	
	public void setOtherCoursesList (ArrayList<String> cOtherList) {
		items = FXCollections.observableArrayList (cOtherList);
		otherList.setItems(items);
	}
	
	public void setFailedCoursesList (ArrayList<String> cFailedList) {
		items = FXCollections.observableArrayList (cFailedList);
		failedList.setItems(items);
	}
	
	public void setNeededCoursesList (ArrayList<String> cNeededList) {
		items = FXCollections.observableArrayList (cNeededList);
		neededList.setItems(items);
	}
	
	public void setName(String name) {
		this.name.setText("\t\t\t" + name);
	}
	
	public void setId(String id) {
		this.id.setText("\t\t\t" + id);
	}
	
	public void setGpa(String gpa) {
		this.gpa.setText("\t\t\t" +gpa);
	}
	
	public void setMajor(String major) {
		this.major.setText("\t\t\t" + major);
	}

}
