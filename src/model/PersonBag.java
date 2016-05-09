package model;

import java.io.Serializable;

public class PersonBag implements Serializable {

	private StudentBag sb;
	private FacultyBag fb;
	private AdminBag ab;

	public PersonBag() {
		ab = null;
		fb = null;
		ab = null;
	}

	public PersonBag(StudentBag sb, FacultyBag fb, AdminBag ab) {
		this.sb = sb;
		this.fb = fb;
		this.ab = ab;
	}

	public void addStudents(StudentBag sb) {
		this.sb = sb;
	}

	public void addFaculty(FacultyBag fb) {
		this.fb = fb;
	}

	public void addAdmin(AdminBag ab) {
		this.ab = ab;
	}

	public void removeStudents() {
		this.sb = null;
	}

	public void removeFaculty() {
		this.fb = null;
	}

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
