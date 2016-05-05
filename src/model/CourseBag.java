package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseBag implements Serializable{

	private ArrayList<Course> courses;
	
	public CourseBag() {
		courses = new ArrayList<>();
	}
	
	public void add(Course c) {
		courses.add(c);
	}
	
	public void remove(Course c) {
		courses.add(c);
	}
	
	public int size() {
		return courses.size();
	}
	
	public Course get(int index) {
		return courses.get(index);
	}

}
