package view;

import handling.GlobalVariables.Events;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreenView extends View {

	private TextField unField;
	private PasswordField pwField;

	public LoginScreenView(Stage stage) {
		super(stage, new VBox(10), 600, 400);
		obsArr = new ArrayList<>();

		VBox vb = (VBox) getRoot();
		vb.setStyle("-fx-alignment: center center");
		Label mysccc = new Label("MySCCC Login");
		Label username = new Label("Username:");
		unField = new TextField();
		Label password = new Label("Password:");
		pwField = new PasswordField();
		Button login = new Button("LOGIN");

		unField.setMaxWidth(200);
		pwField.setMaxWidth(200);

		login.setDefaultButton(true);

		login.setOnAction(e -> {
			NotifyObserver(Events.LOG_IN_BUTTON);
		});

		vb.getChildren().addAll(mysccc, username, unField, password, pwField,
				login);

		stage.setScene(this);
		init();

	}

	public String getUsername() {
		String usernameS = unField.getText();
		return usernameS;
	}

	public String getPassword() {
		String passwordS = pwField.getText();
		return passwordS;
	}

}
