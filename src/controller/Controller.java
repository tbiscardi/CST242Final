package controller;

import handling.GlobalVariables.Events;
import handling.Observer;

import java.util.ArrayList;

import javafx.collections.ObservableList;
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

	private void editCourseSequence() {
		String toEdit = ((EditableSAINReport) view).getSelectedCoursesItem();
		String course = ((EditableSAINReport) view).getCLabel();
		String grade = ((EditableSAINReport) view).getGrade();
		String[] edit = toEdit.split("\t");
		Course newC = new Course(course, grade);
		Course oldC = new Course(edit[0], edit[1]);
		searched.delete(oldC);
		searched.addCourse(newC);
		model.saveData();
		generateEditableSAINReport(searched);
	}

	private void removeCourseSequence() {
		String toDelete = ((EditableSAINReport) view).getSelectedCoursesItem();
		if(toDelete == null) {
			
		} else {
			String[] str = toDelete.split("\t");
			Course c = new Course(str[0], str[1]);
			searched.delete(c);
		}
		model.saveData();
		
		generateEditableSAINReport(searched);
	}

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

	// private void addCourseSequence() {
	// String[] str = ((EditableSAINReport) view).getName().split(" ");
	// appendedStudent.setfName(str[0]);
	// appendedStudent.setlName(str[1]);
	// appendedStudent.setId(((EditableSAINReport) view).getId());
	//
	// Course newCourse = new Course(((EditableSAINReport) view).getCourseA(),
	// ((EditableSAINReport) view).getGrade());
	// appendedStudent.addCourse(newCourse);
	//
	// for (int i = 0; i < model.getMajors().size(); i++) {
	// ((EditableSAINReport) view).addMajors(model.getMajors().get(i)
	// .getName());
	// }
	// ((EditableSAINReport)
	// view).setMajor(appendedStudent.getMajor().getName());
	//
	// //Adds taken courses
	// ObservableList<String> tempCourses = ((EditableSAINReport)
	// view).getTakenArea();
	// for(int i = 0; i < tempCourses.size(); i ++) {
	// String[] course = tempCourses.get(i).split("\t");
	// appendedStudent.addCourse(new Course(course[0], course[1]));
	// }
	// ((EditableSAINReport) view).setTakenCoursesList(tempCourses);
	//
	//
	// //Adds taking courses
	// tempCourses = ((EditableSAINReport) view).getTakingArea();
	// for(int i = 0; i < tempCourses.size(); i ++) {
	// String[] course = tempCourses.get(i).split("\t");
	// appendedStudent.addCourse(new Course(course[0], course[1]));
	// }
	// ((EditableSAINReport) view).setTakingCoursesList(tempCourses);
	//
	// //Adds other courses
	// tempCourses = ((EditableSAINReport) view).getOtherArea();
	// for(int i = 0; i < tempCourses.size(); i ++) {
	// String[] course = tempCourses.get(i).split("\t");
	// appendedStudent.addCourse(new Course(course[0], course[1]));
	// }
	// ((EditableSAINReport) view).setOtherCoursesList(tempCourses);
	//
	// //Add failed courses
	// tempCourses = ((EditableSAINReport) view).getFailedArea();
	// for(int i = 0; i < tempCourses.size(); i ++) {
	// String[] course = tempCourses.get(i).split("\t");
	// appendedStudent.addCourse(new Course(course[0], course[1]));
	// }
	// ((EditableSAINReport) view).setFailedCoursesList(tempCourses);
	//
	// ArrayList<String> tempCourses2 = new ArrayList<String>();
	// CourseBag n = appendedStudent.getNeeded();
	// for(int i = 0; i < n.size(); i ++) {
	// tempCourses2.add(n.get(i).getName() + "\t" + n.get(i).getGrade());
	// }
	// ((EditableSAINReport) view).setNeededCoursesList(tempCourses2);
	//
	// try {
	// appendedStudent.setUsername(model.getPersons().getStudentBag()
	// .get(((EditableSAINReport) view).getId()).getUsername());
	// appendedStudent.setPassword(model.getPersons().getStudentBag()
	// .get(((EditableSAINReport) view).getId()).getPassword());
	// } catch (Exception e) {
	// appendedStudent.setUsername(searched.getUsername());
	// appendedStudent.setPassword(searched.getPassword());
	// }
	// appendedStudent.calculateGPA();
	// model.delete(((EditableSAINReport) view).getId());
	// model.saveData();
	// model.addStudent(appendedStudent);
	//
	// model.saveData();
	// ((EditableSAINReport) view).removeItems();
	// generateEditableSAINReport(appendedStudent);
	// }

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
	 * Generates the SAIN report page that only Admins may access. Here a
	 * searched Student is passed as the parameter and has all of its data
	 * changed to what has been changed in the view.
	 * 
	 * @param s
	 * @precondition Student was found and database was accessible
	 * @postcondition An Editable SAIN Report page will be generated based on
	 *                data entered
	 */
	private void generateSainEditable(Student s) {
		((SAINReportEditable) view).setName(s.getfName() + " " + s.getlName());
		((SAINReportEditable) view).setId(s.getId());
		((SAINReportEditable) view).setGpa(s.calculateGPA());
		((SAINReportEditable) view).setMajor(s.getMajor().getName());
		s.resetCourses();

		for (int i = 0; i < model.getMajors().size(); i++) {
			((SAINReportEditable) view).addMajors(model.getMajors().get(i)
					.getName());
		}

		String tempCourses = "";
		for (int i = 0; i < s.getTaken().size(); i++) {
			tempCourses = tempCourses
					+ (s.getTaken().get(i).getName() + "\t" + s.getTaken()
							.get(i).getGrade()) + "\n";
		}
		((SAINReportEditable) view).setTakenArea(tempCourses);
		tempCourses = "";

		for (int i = 0; i < s.getTaking().size(); i++) {
			tempCourses = tempCourses
					+ (s.getTaking().get(i).getName() + "\t" + s.getTaking()
							.get(i).getGrade()) + "\n";
		}
		((SAINReportEditable) view).setTakingArea(tempCourses);
		tempCourses = "";

		for (int i = 0; i < s.getOther().size(); i++) {
			tempCourses = tempCourses
					+ (s.getOther().get(i).getName() + "\t" + s.getOther()
							.get(i).getGrade()) + "\n";
		}
		((SAINReportEditable) view).setOtherArea(tempCourses);
		tempCourses = "";

		for (int i = 0; i < s.getFailed().size(); i++) {
			tempCourses = tempCourses
					+ (s.getFailed().get(i).getName() + "\t" + s.getFailed()
							.get(i).getGrade()) + "\n";
		}
		((SAINReportEditable) view).setFailedArea(tempCourses);
		tempCourses = "";

		for (int i = 0; i < s.getNeeded().size(); i++) {
			tempCourses = tempCourses
					+ (s.getNeeded().get(i).getName() + "\t" + s.getNeeded()
							.get(i).getGrade()) + "\n";
		}
		((SAINReportEditable) view).setNeededArea(tempCourses);
		s.resetCourses();
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
	 * When an Editable SAIN Report has been generated when the confirm changes
	 * button is pressed, the student being viewed will have its values changed
	 * based on what is now in the text areas.
	 * 
	 * @precondition Data was entered correctly and Database was accessible
	 * @postcondition Editable SAIN Report page will update and the student
	 *                being viewed will permanently contain the new values
	 *                entered
	 */
	private void confirmChangesSequence() {
		appendedStudent = new Student();
		String[] str = ((SAINReportEditable) view).getName().split(" ");
		appendedStudent.setfName(str[0]);
		appendedStudent.setlName(str[1]);
		appendedStudent.setMajor(new Major(((SAINReportEditable) view)
				.getMajor(), model.getMajors()
				.get(((SAINReportEditable) view).getMajor()).getNeeded()));
		CourseBag cb = new CourseBag();

		String[] courses = ((SAINReportEditable) view).getTakenArea().split(
				"\n");

		for (int i = 0; i < courses.length; i++) {
			String[] temp = courses[i].split("\t");
			cb.add(new Course(temp[0], temp[1]));
		}
		appendedStudent.setTaken(cb);
		cb = new CourseBag();
		courses = ((SAINReportEditable) view).getTakingArea().split("\n");

		for (int i = 0; i < courses.length; i++) {
			String[] temp = courses[i].split("\t");
			cb.add(new Course(temp[0], temp[1]));
		}
		appendedStudent.setTaking(cb);

		cb = new CourseBag();
		courses = ((SAINReportEditable) view).getOtherArea().split("\n");

		for (int i = 0; i < courses.length; i++) {
			String[] temp = courses[i].split("\t");
			cb.add(new Course(temp[0], temp[1]));
		}
		appendedStudent.setOther(cb);

		cb = new CourseBag();
		courses = ((SAINReportEditable) view).getFailedArea().split("\n");

		for (int i = 0; i < courses.length; i++) {
			String[] temp = courses[i].split("\t");
			cb.add(new Course(temp[0], temp[1]));
		}
		appendedStudent.setFailed(cb);

		cb = new CourseBag();
		courses = ((SAINReportEditable) view).getNeededArea().split("\n");

		for (int i = 0; i < courses.length; i++) {
			String[] temp = courses[i].split("\t");
			cb.add(new Course(temp[0], ""));
		}
		appendedStudent.setNeeded(cb);
		appendedStudent.setId(((SAINReportEditable) view).getId());
		appendedStudent.setUsername(model.getPersons().getStudentBag()
				.get(((SAINReportEditable) view).getId()).getUsername());
		appendedStudent.setPassword(model.getPersons().getStudentBag()
				.get(((SAINReportEditable) view).getId()).getPassword());
		appendedStudent.calculateGPA();
		model.delete(((SAINReportEditable) view).getId());
		model.saveData();
		model.addStudent(appendedStudent);

		model.saveData();
		((SAINReportEditable) view).removeItems();
		generateSainEditable(appendedStudent);
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
