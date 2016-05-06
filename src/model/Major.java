package model;

import java.io.Serializable;

public class Major implements Serializable{

	private String name;
	private CourseBag needed;
	private double minGPA;

	public Major(String name, CourseBag needed) {
		super();
		this.name = name;
		this.needed = needed;
		this.minGPA = 2.0;
	}

	public Major() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CourseBag getNeeded() {
		return needed;
	}

	public void setNeeded(CourseBag needed) {
		this.needed = needed;
	}

	public double getMinGPA() {
		return minGPA;
	}

	public void setMinGPA(double minGPA) {
		this.minGPA = minGPA;
	}

}
