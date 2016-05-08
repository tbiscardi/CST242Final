package view;

import handling.GlobalVariables;
import handling.GlobalVariables.Events;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SAINReportEditable extends View {

	private Label nameLabel, idLabel, gpaLabel, majorLabel;
	private TextField name, id, gpa;
	private ComboBox<String> major;
	private TextArea takenList, takingList, neededList, otherList, failedList;

	public SAINReportEditable(Stage stage) {
		super(stage, new VBox(10), GlobalVariables.width,
				GlobalVariables.height * 2);
		obsArr = new ArrayList<>();
		VBox vb = (VBox) getRoot();
		vb.setStyle("-fx-alignment: center center");

		GridPane gp = new GridPane();
		nameLabel = new Label("Name:");
		name = new TextField();

		idLabel = new Label("ID:");
		id = new TextField();
		id.setEditable(false);

		gpaLabel = new Label("GPA:");
		gpa = new TextField();
		gpa.setEditable(false);

		majorLabel = new Label("Major:");
		major = new ComboBox<>();

		GridPane.setConstraints(nameLabel, 0, 0);
		GridPane.setConstraints(name, 1, 0);
		GridPane.setConstraints(idLabel, 0, 1);
		GridPane.setConstraints(id, 1, 1);
		GridPane.setConstraints(gpaLabel, 0, 2);
		GridPane.setConstraints(gpa, 1, 2);
		GridPane.setConstraints(majorLabel, 0, 3);
		GridPane.setConstraints(major, 1, 3);

		gp.getChildren().addAll(nameLabel, name, idLabel, id, gpaLabel, gpa,
				majorLabel, major);
		gp.setAlignment(Pos.TOP_CENTER);

		HBox coursePane1 = new HBox(10);
		HBox coursePane2 = new HBox(10);

		Label takenL = new Label("Required Courses Taken:");
		takenList = new TextArea();
		VBox l1 = new VBox();
		l1.getChildren().addAll(takenL, takenList);
		l1.setAlignment(Pos.CENTER);

		Label takingL = new Label("Currently Taking:");
		takingList = new TextArea();
		VBox l2 = new VBox();
		l2.getChildren().addAll(takingL, takingList);
		l2.setAlignment(Pos.CENTER);

		Label otherL = new Label("Other Courses Taken:");
		otherList = new TextArea();
		VBox l3 = new VBox();
		l3.getChildren().addAll(otherL, otherList);
		l3.setAlignment(Pos.CENTER);

		Label failedL = new Label("Withdrawn/Failed:");
		failedList = new TextArea();
		VBox l4 = new VBox();
		l4.getChildren().addAll(failedL, failedList);
		l4.setAlignment(Pos.CENTER);

		Label neededL = new Label("Courses Needed:");
		neededList = new TextArea();
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

		Button confirm = new Button("Confirm Changes");

		confirm.setOnAction(e -> {
			NotifyObserver(Events.CONFIRM_CHANGES);
		});
		
		Button back = new Button("Return");
		back.setOnAction(e -> {
			NotifyObserver(Events.BACK_BUTTON);
		});		

		vb.getChildren().addAll(gp, coursePane1, coursePane2, confirm, back);
		stage.setScene(this);
		stage.setResizable(false);
		stage.setTitle("SAIN Report");
		stage.show();
	}

	public void setName(String name) {
		this.name.setText(name);
	}

	public void setId(String id) {
		this.id.setText(id);
	}

	public void setGpa(String gpa) {
		this.gpa.setText(gpa);
	}

	public void setMajor(String major) {
		this.major.setPromptText(major);
	}
	
	public void addMajors(String major) {
		this.major.getItems().add(major);
	}

	public String getName() {
		return name.getText();
	}

	public String getId() {
		return id.getText();
	}

	public String getGpa() {
		return gpa.getText();
	}

	public String getMajor() {
		return major.getValue();
	}

	public void setTakenArea(String taken) {
		takenList.setText(taken);
	}

	public void setTakingArea(String taking) {
		takingList.setText(taking);
	}

	public void setNeededArea(String needed) {
		neededList.setText(needed);
	}

	public void setOtherArea(String other) {
		otherList.setText(other);
	}

	public void setFailedArea(String failed) {
		failedList.setText(failed);
	}

	public String getTakenArea() {
		return takenList.getText();
	}

	public String getTakingArea() {
		return takingList.getText();
	}

	public String getNeededArea() {
		return neededList.getText();
	}

	public String getOtherArea() {
		return otherList.getText();
	}

	public String getFailedArea() {
		return failedList.getText();
	}

}
