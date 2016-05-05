package view;

import handling.GlobalVariables;
import handling.GlobalVariables.Events;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MySAINHomeScreen extends View{

	public MySAINHomeScreen(Stage stage) {
		super(stage, new VBox(10), GlobalVariables.width, GlobalVariables.height);
		obsArr = new ArrayList<>();
		VBox vb = (VBox) getRoot();
		vb.setStyle("-fx-alignment: center center");
		
//		Image logo = new Image(getClass().getResourceAsStream("sccclog.gif"));
//		ImageView logoImg = new ImageView(logo);
//		logoImg.fitWidthProperty().bind(stage.widthProperty());
//		logoImg.setPreserveRatio(true);
		
		Button sainButton = new Button("SAIN Report");
		Button whatifButton = new Button("What-If Analysis");
		
		sainButton.setOnAction(e -> {
			NotifyObserver(Events.SAIN_REPORT_BUTTON);
		});
		
		whatifButton.setOnAction(e -> {
			NotifyObserver(Events.WHAT_IF_BUTTON);
		});
		
		vb.getChildren().addAll(/*logoImg, */sainButton, whatifButton);
		
		stage.setScene(this);
		stage.setResizable(false);
		stage.setTitle("MySCCC Home Page");
		stage.show();
	}
	
}
