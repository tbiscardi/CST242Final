package view;

import java.util.ArrayList;

import handling.GlobalVariables;
import handling.GlobalVariables.Events;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends View{
	
	private LoginScreenView loginView;

	public MainView(Stage stage) {
		super(stage, new VBox(10), GlobalVariables.width, GlobalVariables.height);
		obsArr = new ArrayList<>();
		LoginScreenView loginView = new LoginScreenView(stage);
		
	}
	
	public String getUsername(){
		return loginView.getUsername();
	}
	
	public String getPassword(){
		return loginView.getPassword();
	}

}
