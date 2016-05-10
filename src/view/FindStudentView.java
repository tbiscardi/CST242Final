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
 * View that opens up the Scene where a faculty or admin may search for a
 * student when generating a SAIN Report
 * 
 * @author Tom Biscardi
 */
public class FindStudentView extends View {

	private ComboBox<String> comboBox;
	private TextField idField;

	/**
	 * Changes the scene to have an ID search textfield
	 * 
	 * @param stage
	 * @postcondition controller now can search for the student entered in the
	 *                ID textField
	 */
	public FindStudentView(Stage stage) {
		super(stage, new VBox(10), GlobalVariables.width,
				GlobalVariables.height);
		obsArr = new ArrayList<>();
		VBox vb = (VBox) getRoot();
		vb.setStyle("-fx-alignment: center center");

		Label searchLabel = new Label("Search by Student ID:");
		idField = new TextField("Enter ID here");
		idField.setMaxWidth(200);
		Button search = new Button("Generate SAIN Report");
		search.setOnAction(e -> {
			NotifyObserver(Events.SEARCH_STUDENT_BUTTON);
		});

		Button back = new Button("Back to Home Screen");
		back.setOnAction(e -> {
			NotifyObserver(Events.BACK_BUTTON);
		});

		vb.getChildren().addAll(searchLabel, idField, search, back);

		stage.setScene(this);
		stage.setResizable(false);
		stage.setTitle("Search Student");
		stage.show();
	}

	/**
	 * Returns the text entered in the id textField
	 * 
	 * @return text in idField textField
	 */
	public String getId() {
		return idField.getText();
	}

}
