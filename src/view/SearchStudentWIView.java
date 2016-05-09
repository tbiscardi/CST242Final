package view;

import handling.GlobalVariables;
import handling.GlobalVariables.Events;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchStudentWIView extends View {

	private TextField idField;
	private ComboBox<String> majors;

	public SearchStudentWIView(Stage stage) {
		super(stage, new VBox(10), GlobalVariables.width,
				GlobalVariables.height);
		obsArr = new ArrayList<>();
		VBox vb = (VBox) getRoot();
		vb.setStyle("-fx-alignment: center center");

		Label searchLabel = new Label("Search by Student ID:");
		idField = new TextField("Enter ID here");
		idField.setMaxWidth(200);

		Label majorLabel = new Label("Select Desired Major (View Only)");
		majors = new ComboBox<>();
		Button submit = new Button("Submit");

		submit.setOnAction(e -> {
			NotifyObserver(Events.DO_WHAT_IF);
		});

		Button back = new Button("Back to Home Screen");
		back.setOnAction(e -> {
			NotifyObserver(Events.BACK_BUTTON);
		});

		vb.getChildren().addAll(searchLabel, idField, majorLabel, majors,
				submit, back);

		stage.setScene(this);
		stage.setResizable(false);
		stage.setTitle("Student What-If Search");
		stage.show();
	}

	public String getId() {
		return idField.getText();
	}

	public void setId(String id) {
		idField.setText(id);
	}

	public void setMajor(String major) {
		this.majors.setPromptText(major);
	}

	public boolean isMajorEmpty() {
		return this.majors.getPromptText() == "";
	}

	public void addMajors(String major) {
		this.majors.getItems().add(major);
	}

	public String getMajor() {
		return majors.getValue();
	}

}
