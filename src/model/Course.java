package model;

import java.io.Serializable;

public class Course implements Serializable{

	private String name;
	private String subject;
	private String number;
	private String grade;
	private double credits;
	private double qualityPts;

	public Course(String subject, String number, String grade) {
		super();
		this.subject = subject;
		this.number = number;
		this.grade = grade;
		this.name = subject + number;
	}

	public Course(String name, String grade) {
		super();
		this.name = name;
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	public double getQualityPts() {
		return qualityPts;
	}

	public void setQualityPts(double qualityPts) {
		this.qualityPts = qualityPts;
	}

}
