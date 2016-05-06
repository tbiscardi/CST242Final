package view;

import handling.GlobalVariables;
import handling.GlobalVariables.Events;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewMajorView extends View {
	
	private ComboBox<String> majors;

	public NewMajorView(Stage stage) {
		super(stage, new VBox(10), GlobalVariables.width, GlobalVariables.height);
		obsArr = new ArrayList<>();
		VBox vb = (VBox) getRoot();
		vb.setStyle("-fx-alignment: center center");
		
		Label majorLabel = new Label("Select Desired Major (View Only)");
		majors = new ComboBox<>();
		Button submit = new Button("Submit");
		
		submit.setOnAction(e -> {
			NotifyObserver(Events.DO_WHAT_IF);
		});
		
		vb.getChildren().addAll(majorLabel, majors, submit);
		
		stage.setScene(this);
		stage.setResizable(false);
		stage.setTitle("Choose Major");
		stage.show();
	}
	
	public void setMajor(String major) {
		this.majors.setPromptText(major);
	}
	
	public void addMajors(String major) {
		this.majors.getItems().add(major);
	}
	
	public String getMajor() {
		return majors.getValue();
	}

}
