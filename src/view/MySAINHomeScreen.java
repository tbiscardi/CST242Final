package view;

import handling.GlobalVariables;
import handling.GlobalVariables.Events;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MySAINHomeScreen extends View {

	public MySAINHomeScreen(Stage stage) {
		super(stage, new VBox(25), GlobalVariables.width,
				GlobalVariables.height);
		obsArr = new ArrayList<>();
		VBox vb = (VBox) getRoot();
		vb.setStyle("-fx-alignment: center center");

		Label img = new Label();
		img.setGraphic(new ImageView(new Image("sccclogo.png")));

		HBox hb = new HBox(10);
		Button sainButton = new Button("SAIN Report");
		Button whatifButton = new Button("What-If Analysis");
		hb.getChildren().addAll(sainButton, whatifButton);

		Button back = new Button("Sign Out");
		back.setOnAction(e -> {
			NotifyObserver(Events.BACK_BUTTON);
		});

		sainButton.setOnAction(e -> {
			NotifyObserver(Events.SAIN_REPORT_BUTTON);
		});

		whatifButton.setOnAction(e -> {
			NotifyObserver(Events.WHAT_IF_BUTTON);
		});

		hb.setAlignment(Pos.CENTER);

		vb.getChildren().addAll(img, hb, back);

		vb.setStyle("-fx-background-color: white; -fx-alignment: center center;");
		stage.setScene(this);
		stage.setResizable(false);
		stage.setTitle("MySCCC Home Page");
		stage.show();
	}

}
