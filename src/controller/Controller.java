package controller;

import handling.GlobalVariables.Events;
import handling.Observer;
import view.LoginScreenView;
import view.MainView;
import view.View;
import model.Database;
import model.Person;
import model.PersonBag;

public class Controller implements Observer{
	
	private Database model;
	private View view;
	private Person p;
	
	public Controller(Database model, View view) {
		view.addObserver(this);
		this.model = model;
		this.view = view;
	}

	@Override
	public void update(Object args) {
		if(args instanceof Events) {
			doButtonFunction((Events)args);
		}
	}

	private void doButtonFunction(Events event) {
		switch(event) {
		case LOG_IN_BUTTON:
			model.openData();
			p = model.getPersons().getStudentBag().get(((LoginScreenView) view).getUsername(), ((LoginScreenView) view).getPassword());
			if(p == null) {
				p = model.getPersons().getFacultyBag().get(((LoginScreenView) view).getUsername(), ((LoginScreenView) view).getPassword());
				if(p == null) {
					p = model.getPersons().getAdminBag().get(((LoginScreenView) view).getUsername(), ((LoginScreenView) view).getPassword());
					if(p == null) {
						view = new LoginScreenView(view.getStage());
					}
					else {
						view = new MySAINHomeScreen(view.getStage()); //create this view class
					}
				} else {
					view = new MySAINHomeScreen(view.getStage()); //create this view class
				}
			} else {
				view = new MySAINHomeScreen(view.getStage()); //create this view class
			}
			break;
		case SAIN_REPORT_BUTTON:
			break;
		case WHAT_IF_BUTTON:
			break;
		case SEARCH_STUDENT_BUTTON:
			break;
		default:
			break;
		}
		
	}

}
