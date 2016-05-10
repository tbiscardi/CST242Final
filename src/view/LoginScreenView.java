package view;

import handling.GlobalVariables.Events;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * View that changes the scene to the login screen
 * 
 * @author Tom Biscardi
 */
public class LoginScreenView extends View {

	private TextField unField;
	private PasswordField pwField;

	/**
	 * Sets scene that has a username Textfield and a password PasswordField
	 * 
	 * @param stage
	 */
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

	/**
	 * Returns Username entered
	 * 
	 * @return text from usernameField
	 */
	public String getUsername() {
		String usernameS = unField.getText();
		return usernameS;
	}

	/**
	 * Returns password entered
	 * @return text from passwordField
	 */
	public String getPassword() {
		String passwordS = pwField.getText();
		return passwordS;
	}

}
