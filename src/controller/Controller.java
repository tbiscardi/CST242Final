package controller;

import handling.GlobalVariables.Events;
import handling.Observer;
import view.LoginScreenView;
import view.MainView;
import view.MySAINHomeScreen;
import view.SAINReportEditable;
import view.SAINReportUneditable;
import view.View;
import model.Database;
import model.Person;
import model.PersonBag;
import model.Student;

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
						view = new MySAINHomeScreen(view.getStage());
					}
				} else {
					view = new MySAINHomeScreen(view.getStage());
				}
			} else {
				view = new MySAINHomeScreen(view.getStage());
			}
			view.addObserver(this);
			break;
		case SAIN_REPORT_BUTTON:
			switch (p.getType()) {
			case 0:
				view = new SAINReportUneditable(view.getStage());
				((SAINReportUneditable)view).setName(p.getfName() + " " + p.getlName());
				((SAINReportUneditable)view).setId(((Student)p).getId());
				((SAINReportUneditable)view).setGpa(((Student)p).calculateGPA());
				((SAINReportUneditable)view).setMajor(((Student)p).getMajor().getName());
				
				break;
			case 1:
				view = new SAINReportUneditable(view.getStage());
				break;
			case 2:
//				view = new SAINReportEditable(view.getStage());
				break;
			default:
				view = new LoginScreenView(view.getStage());
				break;
			}
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
