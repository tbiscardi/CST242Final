package driver;

import controller.Controller;
import model.Database;
import model.PersonBag;
import view.LoginScreenView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This program is a simulation of how the Suffolk County Community College
 * website allows different accounts to use the SAIN Report and What-If Analysis
 * functions. Here the Controller is initialized and the program starts.
 * 
 * Here are the login account usernames, passwords and ID's respectively:
 * 	Students:
 * 		tbiscardi	password		1234
 * 		jdoe 		jdoe			5678
 * 		dtrump		uglyduckling	6666
 * 	Faculty:
 * 		chenb		imsanta			
 * 		chue		mathisfun
 * 	Admin:
 * 		admin		hacker
 * 
 * @author Tom Biscardi
 * 
 */
public class Driver extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Controller controller = new Controller(new Database(),
				new LoginScreenView(primaryStage));
	}

}
