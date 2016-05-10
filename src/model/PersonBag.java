package model;

import java.io.Serializable;

/**
 * Bag class that holds an ArrayList of Persons
 * 
 * @author Tom Biscardi
 */
public class PersonBag implements Serializable {

	private StudentBag sb;
	private FacultyBag fb;
	private AdminBag ab;

	public PersonBag() {
		sb = new StudentBag();
		fb = new FacultyBag();
		ab = new AdminBag();
	}

	public PersonBag(StudentBag sb, FacultyBag fb, AdminBag ab) {
		this.sb = sb;
		this.fb = fb;
		this.ab = ab;
	}

	/**
	 * Sets StudentBag
	 * 
	 * @param sb
	 */
	public void addStudents(StudentBag sb) {
		this.sb = sb;
	}

	/**
	 * Sets FacultyBag
	 * 
	 * @param fb
	 */
	public void addFaculty(FacultyBag fb) {
		this.fb = fb;
	}

	/**
	 * Sets AdminBag
	 * 
	 * @param ab
	 */
	public void addAdmin(AdminBag ab) {
		this.ab = ab;
	}

	/**
	 * Removes StudentBag
	 */
	public void removeStudents() {
		this.sb = null;
	}

	/**
	 * Removes FacultyBag
	 */
	public void removeFaculty() {
		this.fb = null;
	}

	/**
	 * Removes AdminBag
	 */
	public void removeAdmin() {
		this.ab = null;
	}

	public StudentBag getStudentBag() {
		return sb;
	}

	public FacultyBag getFacultyBag() {
		return fb;
	}

	public AdminBag getAdminBag() {
		return ab;
	}
}
