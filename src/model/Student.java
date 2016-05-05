package model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Student extends Person implements Serializable{

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
		
	}

	public Student(String fName, String lName, String username, String password, String id) {
		super(fName, lName, username, password);
		this.id = id;
		setType(0);
	}
	
	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	public String calculateGPA(){
		double avg = 0.0;
		double size = taken.size() + other.size() + failed.size();
		for(int i = 0; i < taken.size(); i ++) {
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
		
		for(int i = 0; i < other.size(); i ++) {
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
		this.major = major;
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
	
	public void fillCoursesNeeded() {
		needed = this.major.getNeeded();
		for(int i = 0; i < needed.size(); i ++) {
			String name = needed.get(i).getName();
			if(taken.containsByName(name) || taking.containsByName(name) || other.containsByName(name)) {
				needed.remove(needed.get(i));
				i--;
				System.out.println();
			} else {
				
			}
		}
	}

}
