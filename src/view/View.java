package view;

import java.util.ArrayList;

import handling.Observer;
import handling.Subject;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class that implements Subject which is the class observed by the Observer.
 * Also extends Scene and is the generic view class.
 * 
 * @author tommy_000
 */
public class View extends Scene implements Subject {

	protected ArrayList<Observer> obsArr;
	protected Stage stage;
	protected boolean first = true;

	/**
	 * Creates the generic Scene properties
	 * 
	 * @param stage
	 * @param root
	 * @param width
	 * @param height
	 */
	public View(Stage stage, Parent root, int width, int height) {
		super(root, width, height);
		this.stage = stage;
		stage.setTitle("MySCCC");
	}

	/**
	 * Checks if the view is the first to pop up when program starts. If it is
	 * then resizable is false and the title is set.
	 * 
	 * @precondtion first = true
	 * @postcondition stage is shown
	 */
	protected void init() {
		if (first) {
			stage.setResizable(false);
			stage.setTitle("Suffolk County Commumity College");
			stage.show();
			first = false;
		}
	}

	/**
	 * Adds an observer to the obsArr ArrayList
	 * 
	 * param o
	 */
	@Override
	public void addObserver(Observer o) {
		obsArr.add(o);
	}

	/**
	 * Removes an observer from the obsArr ArrayList
	 * 
	 * param o
	 */
	@Override
	public void removeObserver(Observer o) {
		obsArr.remove(o);
	}

	/**
	 * Notifys the Observer of a specific Event. In this program this method is
	 * used to pass the enums from GlobalVariables.Events to the controller
	 * class.
	 */
	@Override
	public void NotifyObserver(Object args) {
		for (Observer observer : obsArr) {
			observer.update(args);
		}
	}

	/**
	 * Gets the stage
	 * @return this.stage
	 */
	public Stage getStage() {
		return stage;
	}

}
