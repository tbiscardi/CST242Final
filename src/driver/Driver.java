package driver;

import controller.Controller;
import model.Database;
import model.PersonBag;
import view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Controller controller = new Controller(new Database(), new MainView(primaryStage));
	}

}
