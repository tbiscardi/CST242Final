package model;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * A subclass of Person object and automatically sets type to 0
 * 
 * @author Tom Biscardi
 */
public class Student extends Person implements Serializable {

	private String id;
	private Major major;
	private double gpa;
	private CourseBag taken;
	private CourseBag taking;
	private CourseBag needed;
	private CourseBag failed;
	private CourseBag other;
	private double credits;
	private DecimalFormat df = new DecimalFormat("#.##");

	public Student() {
		super();
		setType(0);
		major = new Major();
		taken = new CourseBag();
		taking = new CourseBag();
		needed = new CourseBag();
		failed = new CourseBag();
		other = new CourseBag();
	}

	public Student(String fName, String lName, String username,
			String password, String id) {
		super(fName, lName, username, password);
		this.id = id;
		setType(0);
		major = new Major();
		taken = new CourseBag();
		taking = new CourseBag();
		needed = new CourseBag();
		failed = new CourseBag();
		other = new CourseBag();
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Calculates GPA based on the grades the student has an the amount of
	 * classes they take. Note: This calculation assumes that all classes are
	 * worth the same amount of credits.
	 * 
	 * @precondition student took classes
	 * @postcondition gpa is set to what was calculated
	 * @return GPA as a String
	 */
	public String calculateGPA() {
		double avg = 0.0;
		double size = taken.size() + other.size() + failed.size();
		for (int i = 0; i < taken.size(); i++) {
			switch (taken.get(i).getGrade()) {
			case "A":
				avg += 4.0;
				break;
			case "B+":
				avg += 3.5;
				break;
			case "B":
				avg += 3.0;
				break;
			case "C+":
				avg += 2.5;
				break;
			case "C":
				avg += 2.0;
				break;
			case "D+":
				avg += 1.5;
				break;
			case "D":
				avg += 1.0;
				break;
			default:
				break;
			}
		}

		for (int i = 0; i < other.size(); i++) {
			switch (other.get(i).getGrade()) {
			case "A":
				avg += 4.0;
				break;
			case "B+":
				avg += 3.5;
				break;
			case "B":
				avg += 3.0;
				break;
			case "C+":
				avg += 2.5;
				break;
			case "C":
				avg += 2.0;
				break;
			case "D+":
				avg += 1.5;
				break;
			case "D":
				avg += 1.0;
				break;
			default:
				break;
			}
		}
		double calcGpa = avg / size;
		return df.format(calcGpa);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		if (this.major == null) {
			this.major = major;

		} else {
			this.major = major;
			resetCourses();
		}
	}

	public CourseBag getTaken() {
		return taken;
	}

	public void setTaken(CourseBag taken) {
		this.taken = taken;
	}

	public CourseBag getTaking() {
		return taking;
	}

	public void setTaking(CourseBag taking) {
		this.taking = taking;
	}

	public CourseBag getNeeded() {
		return needed;
	}

	public void setNeeded(CourseBag needed) {
		this.needed = needed;
	}

	public CourseBag getFailed() {
		return failed;
	}

	public void setFailed(CourseBag failed) {
		this.failed = failed;
	}

	public CourseBag getOther() {
		return other;
	}

	public void setOther(CourseBag other) {
		this.other = other;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	/**
	 * Fills the Needed Courses CourseBag based on the Students major
	 * 
	 * @precondition student has a major
	 * @postcondition CourseBag needed is filled correctly
	 */
	public void fillCoursesNeeded() {
		needed = new CourseBag();
		for (int i = 0; i < this.major.getNeeded().size(); i++) {
			needed.add(this.major.getNeeded().get(i));
		}
		for (int i = 0; i < needed.size(); i++) {
			String name = needed.get(i).getName();
			if (taken.containsByName(name) || taking.containsByName(name)
					|| other.containsByName(name)) {
				needed.remove(needed.get(i));
				i--;
			} else {

			}
		}
	}

	/**
	 * Checks if Student took, or is taking a course
	 * 
	 * @param c
	 * @return true if student has course c false if student does have course c
	 */
	public boolean hasCourse(Course c) {
		if (taken.containsByName(c)) {
			return true;
		} else if (taking.containsByName(c)) {
			return true;
		} else if (other.containsByName(c)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Usually called when major is changed because then the courses need to
	 * move to different CourseBags that they belong in based on the new major.
	 * 
	 * @precondition student has a major
	 * @postcondition courses are now in the correct CourseBags
	 */
	public void resetCourses() {
		needed.removeAll();
		CourseBag cb = major.getNeeded();
		for (int i = 0; i < cb.size(); i++) {
			Course temp = cb.get(i);
			if (!hasCourse(temp)) {
				needed.add(temp);
			} else if (other.containsByName(temp)) {
				taken.add(other.getByName(temp));
				other.remove(other.getByName(temp));
			}
		}
		for (int i = 0; i < taken.size(); i++) {
			if (cb.containsByName(taken.get(i))) {

			} else {
				other.add(taken.getByName(taken.get(i)));
				taken.remove(taken.getByName(taken.get(i)));
				i--;
			}
		}
	}

}
