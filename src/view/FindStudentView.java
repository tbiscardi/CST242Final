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

public class FindStudentView extends View {

	private ComboBox<String> comboBox;
	private TextField idField;

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

		// Label cbLabel = new Label("Select Major");
		// comboBox = new ComboBox<>();
		// Button cbButton = new Button("Generate What-If Analysis");
		// cbButton.setOnAction(e -> {
		// NotifyObserver(Events.SEARCH_STUDENT_BUTTON);
		// });

	}

	public String getId() {
		return idField.getText();
	}

	// public void setComboBoxItems(String major) {
	// comboBox.getItems().add(major);
	// }

}
