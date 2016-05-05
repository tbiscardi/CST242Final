package view;

import java.util.ArrayList;

import handling.GlobalVariables;
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
		
		Image logo = new Image(getClass().getResourceAsStream("sccclog.gif"));
		ImageView logoImg = new ImageView(logo);
		logoImg.fitWidthProperty().bind(stage.widthProperty());
		logoImg.setPreserveRatio(true);
		
		//SAIN button and WHAT-IF button
	}
	
}
