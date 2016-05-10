package view;

import handling.GlobalVariables;
import handling.GlobalVariables.Events;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * View that creates a Scene that a Student accesses to enter a major for the
 * What-If Analysis.
 * 
 * @author Tom Biscardi
 */
public class NewMajorView extends View {

	private ComboBox<String> majors;

	/**
	 * Scene is changed so that a major is to be selected from a ComboBox
	 * 
	 * @param stage
	 */
	public NewMajorView(Stage stage) {
		super(stage, new VBox(10), GlobalVariables.width,
				GlobalVariables.height);
		obsArr = new ArrayList<>();
		VBox vb = (VBox) getRoot();
		vb.setStyle("-fx-alignment: center center");

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

		vb.getChildren().addAll(majorLabel, majors, submit, back);

		stage.setScene(this);
		stage.setResizable(false);
		stage.setTitle("Choose Major");
		stage.show();
	}

	/**
	 * Sets the prompt text of the major ComboBox
	 * 
	 * @param major
	 */
	public void setMajor(String major) {
		this.majors.setPromptText(major);
	}

	/**
	 * Adds an item to the ComboBox
	 * 
	 * @param major
	 */
	public void addMajors(String major) {
		this.majors.getItems().add(major);
	}

	/**
	 * Returns the value of the major ComboBox from what was selected.
	 * 
	 * @return
	 */
	public String getMajor() {
		return majors.getValue();
	}

}
