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

/**
 * View that creates a Scene that an admin or faculty access in order to do a
 * What-If Analysis for a searched by ID student and choose their major.
 * 
 * @author Tom Biscardi
 */
public class SearchStudentWIView extends View {

	private TextField idField;
	private ComboBox<String> majors;

	/**
	 * Scene created that contains data areas to enter a student ID to search
	 * and a major from a ComboBox.
	 * 
	 * @param stage
	 */
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

	/**
	 * Boolean that checks if a Major was entered
	 * @return
	 */
	public boolean isMajorEmpty() {
		return this.majors.getPromptText() == "";
	}

	/**
	 * Adds a value to the majors ComboBox
	 * 
	 * @param major
	 */
	public void addMajors(String major) {
		this.majors.getItems().add(major);
	}

	/**
	 * Gets the value of the majors ComboBox entered
	 * @return
	 */
	public String getMajor() {
		return majors.getValue();
	}

}
