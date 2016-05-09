package model;

import java.io.Serializable;

public class Course implements Serializable {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(credits);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		temp = Double.doubleToLongBits(qualityPts);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (Double.doubleToLongBits(credits) != Double
				.doubleToLongBits(other.credits))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (Double.doubleToLongBits(qualityPts) != Double
				.doubleToLongBits(other.qualityPts))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

}
