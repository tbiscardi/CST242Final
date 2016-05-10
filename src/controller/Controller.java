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
import view.EditableSAINReport;
import view.FindStudentView;
import view.LoginScreenView;
import view.MySAINHomeScreen;
import view.NewMajorView;
import view.SAINReportEditable;
import view.SAINReportUneditable;
import view.SearchStudentWIView;
import view.View;

/**
 * This is the main controller class where all data from the view gets inputted
 * and is then manipulated accordingly. Here is where we check what type of
 * person logs in and based on that gives them access to certain features.
 * 
 * @author Tom Biscardi
 * @version 1.0
 */
public class Controller implements Observer {

	private Database model;
	private View view;
	private Person p;
	private Student searched, appendedStudent;

	/**
	 * Initializes Database model and View view parameters and adds this
	 * Observer to the View class
	 * 
	 * @param model
	 * @param view
	 */
	public Controller(Database model, View view) {
		view.addObserver(this);
		this.model = model;
		this.view = view;
	}

	/**
	 * Since this class extends Observer, we can override the implemented method
	 * update. This method checks whether the data passed from the
	 * NotifyObserver method is valid and then calls the private method
	 * doButtonFunction to change views and manipulate data based on preceding
	 * info.
	 * 
	 * @param args
	 * @precondition The data notified by this Observer is an enum in the class
	 *               GlobalVariables
	 * @postcondition Based on the button pressed, an action will be performed
	 */
	@Override
	public void update(Object args) {
		if (args instanceof Events) {
			doButtonFunction((Events) args);
		}
	}

	/**
	 * Based on the event enum that was passed, the application will continue to
	 * simulate actions accordingly
	 * 
	 * @param event
	 * @precondition data passed is an enum
	 * @postcondition an action on the GUI part and the database is performed
	 */
	private void doButtonFunction(Events event) {
		switch (event) {
		case LOG_IN_BUTTON:
			loginSequence();
			break;
		case SAIN_REPORT_BUTTON:
			sainReportSequence();
			break;
		case WHAT_IF_BUTTON:
			whatIfSequence();
			break;
		case DO_WHAT_IF:
			doWhatIf();
			break;
		case SEARCH_STUDENT_BUTTON:
			searchStudentSequence();
			break;
		case CHANGE_MAJOR:
			changeMajorSequence();
			break;
		case BACK_BUTTON:
			backButtonSequence();
			break;
		case ADD_COURSE:
			addCourseSeq();
			break;
		case REMOVE_COURSE:
			removeCourseSequence();
			break;
		case EDIT_COURSE:
			editCourseSequence();
			break;
		default:
			break;
		}

	}

	/**
	 * Edit Courses sequence changes the grade of the class selected.
	 * 
	 * @precondition Course was selected from SAIN report
	 * @postcondition Course's grade was modified
	 */
	private void editCourseSequence() {
		String toEdit = ((EditableSAINReport) view).getSelectedCoursesItem();
		String course = ((EditableSAINReport) view).getCLabel();
		String grade = ((EditableSAINReport) view).getGrade();
		String[] edit = toEdit.split("\t");
		Course newC = new Course(course, grade);
		Course oldC = new Course(edit[0], edit[1]);
		try {
			searched.delete(oldC);
			searched.addCourse(newC);
		} catch (NullPointerException e) {
			searched.addCourse(oldC);
		}
		model.saveData();
		generateEditableSAINReport(searched);
	}

	/**
	 * Remove Course Sequence Deletes the selected Course from the Student.
	 * 
	 * @precondition Course was selected from SAIN Report
	 * @postcondition Course was deleted from Student
	 */
	private void removeCourseSequence() {
		String toDelete = ((EditableSAINReport) view).getSelectedCoursesItem();
		if (toDelete == null) {

		} else {
			String[] str = toDelete.split("\t");
			Course c = new Course(str[0], str[1]);
			searched.delete(c);
		}
		model.saveData();

		generateEditableSAINReport(searched);
	}

	/**
	 * Change Major Sequence that changes the major of the searched Student to
	 * what was selected in the ComboBox
	 * 
	 * @precondition a major was selected
	 * @postcondition major of student was changed
	 */
	private void changeMajorSequence() {
		try {
			if (((EditableSAINReport) view).getMajor().equals(
					searched.getMajor().getName())) {

			} else {
				searched.setMajor(model.getMajors().get(
						((EditableSAINReport) view).getMajor()));
				model.saveData();
				generateEditableSAINReport(searched);
			}
		} catch (NullPointerException e) {

		}
	}

	/**
	 * Add Course Sequence that adds any course to the Student's SAIN Report.
	 * 
	 * @precondition Course is entered properly
	 * @postcondition Course is added and placed in the right spot(s) on the
	 *                SAIN Report.
	 */
	private void addCourseSeq() {
		if (((EditableSAINReport) view).getCourseA() == null
				|| ((EditableSAINReport) view).getGrade() == null
				|| ((EditableSAINReport) view).getCourseA().equals(
						"e.g. CST141")) {

		} else {
			Course newCourse = new Course(
					((EditableSAINReport) view).getCourseA(),
					((EditableSAINReport) view).getGrade());
			searched.addCourse(newCourse);
			model.saveData();
			generateEditableSAINReport(searched);
		}
	}

	/**
	 * Generates the editable SAIN Report that only admins may access.
	 * 
	 * @param s
	 * @precondition student s is searched and p is of type 2 (admin)
	 * @postcondition Editable SAIN Report for Student s is generated
	 */
	private void generateEditableSAINReport(Student s) {
		((EditableSAINReport) view).setName(s.getfName() + " " + s.getlName());
		((EditableSAINReport) view).setId(s.getId());
		((EditableSAINReport) view).setGpa(s.calculateGPA());
		((EditableSAINReport) view).setMajor(s.getMajor().getName());
		s.resetCourses();

		((EditableSAINReport) view).setMajor(s.getMajor().getName());

		((EditableSAINReport) view).removeItems();

		for (int i = 0; i < model.getMajors().size(); i++) {
			((EditableSAINReport) view).addMajors(model.getMajors().get(i)
					.getName());
		}

		ArrayList<String> tempCourses = new ArrayList<>();
		for (int i = 0; i < s.getTaken().size(); i++) {
			tempCourses.add(s.getTaken().get(i).getName() + "\t"
					+ s.getTaken().get(i).getGrade());
		}
		((EditableSAINReport) view).setTakenCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();

		for (int i = 0; i < s.getTaking().size(); i++) {
			tempCourses.add(s.getTaking().get(i).getName() + "\t"
					+ s.getTaking().get(i).getGrade());
		}
		((EditableSAINReport) view).setTakingCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();

		for (int i = 0; i < s.getOther().size(); i++) {
			tempCourses.add(s.getOther().get(i).getName() + "\t"
					+ s.getOther().get(i).getGrade());
		}
		((EditableSAINReport) view).setOtherCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();

		for (int i = 0; i < s.getFailed().size(); i++) {
			tempCourses.add(s.getFailed().get(i).getName() + "\t"
					+ s.getFailed().get(i).getGrade());
		}
		((EditableSAINReport) view).setFailedCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();

		for (int i = 0; i < s.getNeeded().size(); i++) {
			tempCourses.add(s.getNeeded().get(i).getName() + "\t"
					+ s.getNeeded().get(i).getGrade());
		}
		((EditableSAINReport) view).setNeededCoursesList(tempCourses);
		appendedStudent = new Student();
		appendedStudent.setfName("");
		appendedStudent.setMajor(s.getMajor());
	}

	/**
	 * Generates the SAIN Report that both Students and Faculty may access. This
	 * contains data that is only viewable.
	 * 
	 * @param s
	 * @precondition Student entered was valid and database was accessible
	 * @postcondition Generates SAIN Report page that is only viewable data
	 */
	private void generateSain(Student s) {
		view = new SAINReportUneditable(view.getStage());
		((SAINReportUneditable) view)
				.setName(s.getfName() + " " + s.getlName());
		((SAINReportUneditable) view).setId(s.getId());
		((SAINReportUneditable) view).setGpa(s.calculateGPA());
		((SAINReportUneditable) view).setMajor(s.getMajor().getName());
		s.resetCourses();
		ArrayList<String> tempCourses = new ArrayList<>();
		for (int i = 0; i < s.getTaken().size(); i++) {
			tempCourses.add(s.getTaken().get(i).getName() + "\t"
					+ s.getTaken().get(i).getGrade());
		}
		((SAINReportUneditable) view).setTakenCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();

		for (int i = 0; i < s.getTaking().size(); i++) {
			tempCourses.add(s.getTaking().get(i).getName() + "\t"
					+ s.getTaking().get(i).getGrade());
		}
		((SAINReportUneditable) view).setTakingCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();

		for (int i = 0; i < s.getOther().size(); i++) {
			tempCourses.add(s.getOther().get(i).getName() + "\t"
					+ s.getOther().get(i).getGrade());
		}
		((SAINReportUneditable) view).setOtherCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();

		for (int i = 0; i < s.getFailed().size(); i++) {
			tempCourses.add(s.getFailed().get(i).getName() + "\t"
					+ s.getFailed().get(i).getGrade());
		}
		((SAINReportUneditable) view).setFailedCoursesList(tempCourses);
		tempCourses = null;
		tempCourses = new ArrayList<>();

		for (int i = 0; i < s.getNeeded().size(); i++) {
			tempCourses.add(s.getNeeded().get(i).getName() + "\t"
					+ s.getNeeded().get(i).getGrade());
		}
		((SAINReportUneditable) view).setNeededCoursesList(tempCourses);

	}

	/**
	 * Sequence activated while user tries to log in. Here the data entered by
	 * the user is verified with the database containing users and will allow
	 * the appropriate access depending on the account logging in.
	 * 
	 * @precondition Database is accessible and data entered verifies with the
	 *               database
	 * @postcondition Based on the type of account logging in, the global
	 *                variable Person p will be set to the person entered and
	 *                different access will be granted.
	 */
	private void loginSequence() {
		model.openData();
		String username = ((LoginScreenView) view).getUsername();
		String password = ((LoginScreenView) view).getPassword();
		p = model.getPersons().getStudentBag().get(username, password);
		if (p == null) {
			p = model.getPersons().getFacultyBag().get(username, password);
			if (p == null) {
				p = model.getPersons().getAdminBag().get(username, password);
				if (p == null) {
					view = new LoginScreenView(view.getStage());
				} else {
					view = new MySAINHomeScreen(view.getStage());
				}
			} else {
				view = new MySAINHomeScreen(view.getStage());
			}
		} else {
			view = new MySAINHomeScreen(view.getStage());
		}
		view.addObserver(this);
	}

	/**
	 * When SAIN Report button is pressed, this method directs where the user is
	 * sent and the view will change accordingly
	 * 
	 * @precondition Person p has a valid type
	 * @postcondition For a Student, their SAIN report is viewable; for a
	 *                Faculty and Admin, a different view will show asking the
	 *                user to search for a Student by their ID
	 */
	private void sainReportSequence() {
		switch (p.getType()) {
		case 0:
			generateSain((Student) p);
			view.addObserver(this);
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
			view.addObserver(this);
			break;
		}
	}

	/**
	 * Generates different What-If Analysis based on if the user is a student,
	 * or a faculty or admin. There they will be asked to input the major
	 * desired. Faculty and Admin must also input a Student ID to search.
	 * 
	 * @precondition Person p has a valid type
	 * @postcondition A new View will be shown asking the user for the desired
	 *                major.
	 */
	private void whatIfSequence() {
		if (p.getType() == 0) {
			view = new NewMajorView(view.getStage());
			for (int i = 0; i < model.getMajors().size(); i++) {
				((NewMajorView) view).addMajors(model.getMajors().get(i)
						.getName());
			}
		} else {
			view = new SearchStudentWIView(view.getStage());
			for (int i = 0; i < model.getMajors().size(); i++) {
				((SearchStudentWIView) view).addMajors(model.getMajors().get(i)
						.getName());
			}
		}
		view.addObserver(this);
	}

	/**
	 * Takes in the data from the major selecting view class and generates a
	 * SAIN Report for a Student but with data changed so that their major is
	 * what the user input. Note: this is only temporary and does not change the
	 * Student's actual major for any users.
	 * 
	 * @precondition Valid data was entered in all fields and Person p has a
	 *               valid type
	 * @postcondition A viewable only SAIN Report is generated based on the
	 *                Student and major entered
	 */
	private void doWhatIf() {
		if (p.getType() == 0) {
			appendedStudent = (Student) p;
			Major m = appendedStudent.getMajor();
			String majorTemp = ((NewMajorView) view).getMajor();
			appendedStudent.setMajor(model.getMajors().get(majorTemp));

			generateSain(appendedStudent);
			appendedStudent.setMajor(m);
			view.addObserver(this);
		} else {
			try {
				searched = model.getPersons().getStudentBag()
						.get(((SearchStudentWIView) view).getId());
				Major m = searched.getMajor();
				String majorTemp = ((SearchStudentWIView) view).getMajor();
				searched.setMajor(model.getMajors().get(majorTemp));

				generateSain(searched);
				searched.setMajor(m);
				view.addObserver(this);
			} catch (NullPointerException e) {
				if (((SearchStudentWIView) view).isMajorEmpty()) {
					((SearchStudentWIView) view)
							.setMajor("Please select a major");
				} else {
					((SearchStudentWIView) view).setId("Student Not Found");
				}
			}
		}
	}

	/**
	 * The data entered in the FindStudentView is verified and based on the type
	 * of Person p, a different SAIN Report will be generated depending on the
	 * Student inputted.
	 * 
	 * @precondition Valid data was entered, Database was accessible and Student
	 *               can be found
	 * @postcondition A SAIN Report will be generated based on the data up until
	 *                now
	 */
	private void searchStudentSequence() {
		switch (p.getType()) {
		case 1:
			searched = model.getPersons().getStudentBag()
					.get(((FindStudentView) view).getId());
			if (searched == null) {
				view = new FindStudentView(view.getStage());
			} else {
				view = new SAINReportUneditable(view.getStage());
				generateSain(searched);
			}
			view.addObserver(this);
			break;
		case 2:
			searched = model.getPersons().getStudentBag()
					.get(((FindStudentView) view).getId());
			if (searched == null) {
				view = new FindStudentView(view.getStage());
			} else {
				view = new EditableSAINReport(view.getStage());
				generateEditableSAINReport(searched);

			}
			view.addObserver(this);
			break;
		default:
			view = new LoginScreenView(view.getStage());
			break;
		}
	}

	/**
	 * When the back button on any screen has been pressed, depending on the
	 * screen currently on, the user will be redirected. If on the home screen
	 * the user will log out, on any other screen the user will be sent to the
	 * home screen.
	 * 
	 * @precondition The user is on a valid view page
	 * @postcondition User will either be logged out or sent to the home page
	 *                depending on the page they are currently on
	 */
	private void backButtonSequence() {
		if (view instanceof MySAINHomeScreen) {
			view = new LoginScreenView(view.getStage());
		} else {
			view = new MySAINHomeScreen(view.getStage());
		}
		view.addObserver(this);
	}

}
