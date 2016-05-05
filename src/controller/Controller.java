package controller;

import handling.GlobalVariables.Events;
import handling.Observer;

import java.util.ArrayList;

import model.Course;
import model.Database;
import model.Person;
import model.Student;
import view.LoginScreenView;
import view.MySAINHomeScreen;
import view.SAINReportUneditable;
import view.View;

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
				ArrayList<String> tempCourses = new ArrayList<>();
				for(int i = 0; i < ((Student)p).getTaken().size(); i ++) {
					tempCourses.add(((Student)p).getTaken().get(i).getName() + "\t" + ((Student)p).getTaken().get(i).getGrade());
				}
				((SAINReportUneditable)view).setTakenCoursesList(tempCourses);
				tempCourses = null;
				tempCourses = new ArrayList<>();
				
				for(int i = 0; i < ((Student)p).getTaking().size(); i ++) {
					tempCourses.add(((Student)p).getTaking().get(i).getName() + "\t" + ((Student)p).getTaking().get(i).getGrade());
				}
				((SAINReportUneditable)view).setTakingCoursesList(tempCourses);
				tempCourses = null;
				tempCourses = new ArrayList<>();
				
				for(int i = 0; i < ((Student)p).getOther().size(); i ++) {
					tempCourses.add(((Student)p).getOther().get(i).getName() + "\t" + ((Student)p).getOther().get(i).getGrade());
				}
				((SAINReportUneditable)view).setOtherCoursesList(tempCourses);
				tempCourses = null;
				tempCourses = new ArrayList<>();
				
				for(int i = 0; i < ((Student)p).getFailed().size(); i ++) {
					tempCourses.add(((Student)p).getFailed().get(i).getName() + "\t" + ((Student)p).getFailed().get(i).getGrade());
				}
				((SAINReportUneditable)view).setFailedCoursesList(tempCourses);
				tempCourses = null;
				tempCourses = new ArrayList<>();
				
				for(int i = 0; i < ((Student)p).getNeeded().size(); i ++) {
					tempCourses.add(((Student)p).getNeeded().get(i).getName() + "\t" + ((Student)p).getNeeded().get(i).getGrade());
				}
				((SAINReportUneditable)view).setNeededCoursesList(tempCourses);
				
				break;
			case 1:
//				p = model.getPersons().getStudentBag().get(((FindStudentView)view).getId);
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
