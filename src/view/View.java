package view;

import java.util.ArrayList;

import handling.Observer;
import handling.Subject;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Scene implements Subject{
	
	protected ArrayList<Observer> obsArr;
	protected Stage stage;
	protected boolean first = true;

	public View(Stage stage, Parent root, int width, int height) {
		super(root, width, height);
		this.stage = stage;
		stage.setTitle("MySCCC");
	}
	
	protected void init(){
		if(first){
			stage.setResizable(false);
			stage.setTitle("Suffolk County Commumity College");
			stage.show();
			first = false;
		}
	}

	@Override
	public void addObserver(Observer o) {
		obsArr.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		obsArr.remove(o);
	}

	@Override
	public void NotifyObserver(Object args) {
		for (Observer observer : obsArr) {
			observer.update(args);
		}
	}
	
	public Stage getStage(){
		return stage;
	}
	
}
