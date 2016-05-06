package controller;

import handling.GlobalVariables.Events;
import handling.Observer;

import java.util.ArrayList;

import model.Course;
import model.CourseBag;
import model.Database;
import model.Major;
import model.Person;
import model.Student;
import view.FindStudentView;
import view.LoginScreenView;
import view.MySAINHomeScreen;
import view.SAINReportEditable;
import view.SAINReportUneditable;
import view.View;

public class Controller implements Observer{
	
	private Database model;
	private View view;
	private Person p;
	private Student searched, appendedStudent;
	
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
			
			model.getPersons().getStudentBag().get("6666");
			
			
			
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
				generateSain((Student)p);
				break;
			case 1:
				view = new FindStudentView(view.getStage());
				view.addObserver(this);
				break;
			case 2:
				view = new FindStudentView(view.getStage());
				view.addObserver(this);
				break;
			default:
				view = new LoginScreenView(view.getStage());
				break;
			}
			break;
		case WHAT_IF_BUTTON:
			break;
		case SEARCH_STUDENT_BUTTON:
			switch (p.getType()) {
			case 1:
				searched = model.getPersons().getStudentBag().get(((FindStudentView)view).getId());
				System.out.println(searched.getfName());
				if(searched == null) {
					view = new FindStudentView(view.getStage());
				} else {
					view = new SAINReportUneditable(view.getStage());
					generateSain(searched);
				}
				view.addObserver(this);
				break;
			case 2:
				searched = model.getPersons().getStudentBag().get(((FindStudentView)view).getId());
				if(searched == null) {
					view = new FindStudentView(view.getStage());
				} else {
					view = new SAINReportEditable(view.getStage());
					generateSainEditable(searched);
					
				}
				view.addObserver(this);
				break;
			default:
				view = new LoginScreenView(view.getStage());
				break;
			}
			break;
		case CONFIRM_CHANGES:
			appendedStudent = new Student();
			String[] str = ((SAINReportEditable)view).getName().split(" ");
			appendedStudent.setfName(str[0]);
			appendedStudent.setlName(str[1]);
			appendedStudent.setMajor(new Major(((SAINReportEditable)view).getMajor(), model.getMajors().get(((SAINReportEditable)view).getMajor()).getNeeded()));
			CourseBag cb = new CourseBag();
			
			String[] courses = ((SAINReportEditable)view).getTakenArea().split("\n");
			
			for(int i = 0; i < courses.length; i ++) {
				String[] temp = courses[i].split("\t");
				cb.add(new Course(temp[0], temp[1]));
			}
			appendedStudent.setTaken(cb);
			cb = new CourseBag();
			courses = ((SAINReportEditable)view).getTakingArea().split("\n");
			
			for(int i = 0; i < courses.length; i ++) {
				String[] temp = courses[i].split("\t");
				cb.add(new Course(temp[0], temp[1]));
			}
			appendedStudent.setTaking(cb);
			
			cb = new CourseBag();
			courses = ((SAINReportEditable)view).getOtherArea().split("\n");
			
			for(int i = 0; i < courses.length; i ++) {
				String[] temp = courses[i].split("\t");
				cb.add(new Course(temp[0], temp[1]));
			}
			appendedStudent.setOther(cb);
			
			cb = new CourseBag();
			courses = ((SAINReportEditable)view).getFailedArea().split("\n");
			
			for(int i = 0; i < courses.length; i ++) {
				String[] temp = courses[i].split("\t");
				cb.add(new Course(temp[0], temp[1]));
			}
			appendedStudent.setFailed(cb);
			
			cb = new CourseBag();
			courses = ((SAINReportEditable)view).getNeededArea().split("\n");
			
			for(int i = 0; i < courses.length; i ++) {
				String[] temp = courses[i].split("\t");
				cb.add(new Course(temp[0], ""));
			}
			appendedStudent.setNeeded(cb);
			appendedStudent.setId(((SAINReportEditable)view).getId());
			appendedStudent.setUsername(model.getPersons().getStudentBag().get(((SAINReportEditable)view).getId()).getUsername());
			appendedStudent.setPassword(model.getPersons().getStudentBag().get(((SAINReportEditable)view).getId()).getPassword());
			appendedStudent.calculateGPA();
			model.delete(((SAINReportEditable)view).getId());
			model.saveData();
			model.addStudent(appendedStudent);
			
			model.saveData();
		default:
			break;
		}
		
	}
	
	private void generateSainEditable(Student s) {
		((SAINReportEditable)view).setName(s.getfName() + " " + s.getlName());
		((SAINReportEditable)view).setId(s.getId());
		((SAINReportEditable)view).setGpa(s.calculateGPA());
		((SAINReportEditable)view).setMajor(s.getMajor().getName());
		
		for(int i = 0; i < model.getMajors().size(); i ++) {
			((SAINReportEditable)view).addMajors(model.getMajors().get(i).getName());
		}
		
		String tempCourses = "";
		for(int i = 0; i < s.getTaken().size(); i ++) {
			tempCourses = tempCourses + (s.getTaken().get(i).getName() + "\t" + s.getTaken().get(i).getGrade()) + "\n";
		}
		((SAINReportEditable)view).setTakenArea(tempCourses);
		tempCourses = "";
		
		for(int i = 0; i < s.getTaking().size(); i ++) {
			tempCourses = tempCourses + (s.getTaking().get(i).getName() + "\t" + s.getTaking().get(i).getGrade()) + "\n";
		}
		((SAINReportEditable)view).setTakingArea(tempCourses);
		tempCourses = "";
		
		for(int i = 0; i < s.getOther().size(); i ++) {
			tempCourses = tempCourses + (s.getOther().get(i).getName() + "\t" + s.getOther().get(i).getGrade()) + "\n";
		}
		((SAINReportEditable)view).setOtherArea(tempCourses);
		tempCourses = "";
		
		for(int i = 0; i < s.getFailed().size(); i ++) {
			tempCourses = tempCourses + (s.getFailed().get(i).getName() + "\t" + s.getFailed().get(i).getGrade()) + "\n";
		}
		((SAINReportEditable)view).setFailedArea(tempCourses);
		tempCourses = "";
		
		for(int i = 0; i < s.getNeeded().size(); i ++) {
			tempCourses = tempCourses + (s.getNeeded().get(i).getName() + "\t" + s.getNeeded().get(i).getGrade()) + "\n";
		}
		((SAINReportEditable)view).setNeededArea(tempCourses);
	}

	private void generateSain(Student s) {
		view = new SAINReportUneditable(view.getStage());
		((SAINReportUneditable)view).setName(s.getfName() + " " + s.getlName());
		((SAINReportUneditable)view).setId(s.getId());
		((SAINReportUneditable)view).setGpa(s.calculateGPA());
		((SAINReportUneditable)view).setMajor(s.getMajor().getName());
		ArrayList<String> tempCourses = new ArrayList<>();
		for(int i = 0; i < s.getTaken().size(); i ++) {
			tempCourses.add(s.getTaken().get(i).getName() + "\t" + s.getTaken().get(i).getGrade());
		}
		((SAINReportUneditable)view).setTakenCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();
		
		for(int i = 0; i < s.getTaking().size(); i ++) {
			tempCourses.add(s.getTaking().get(i).getName() + "\t" + s.getTaking().get(i).getGrade());
		}
		((SAINReportUneditable)view).setTakingCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();
		
		for(int i = 0; i < s.getOther().size(); i ++) {
			tempCourses.add(s.getOther().get(i).getName() + "\t" + s.getOther().get(i).getGrade());
		}
		((SAINReportUneditable)view).setOtherCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();
		
		for(int i = 0; i < s.getFailed().size(); i ++) {
			tempCourses.add(s.getFailed().get(i).getName() + "\t" + s.getFailed().get(i).getGrade());
		}
		((SAINReportUneditable)view).setFailedCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();
		
		for(int i = 0; i < s.getNeeded().size(); i ++) {
			tempCourses.add(s.getNeeded().get(i).getName() + "\t" + s.getNeeded().get(i).getGrade());
		}
		((SAINReportUneditable)view).setNeededCoursesList(tempCourses);
	}

}
