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
		courses.remove(c);
	}
	
	public int size() {
		return courses.size();
	}
	
	public Course get(int index) {
		return courses.get(index);
	}
	
	public boolean contains(Course c) {
		for(int i = 0; i < size(); i ++) {
			if(get(i).equals(c)) {
				return true;
			} else {
			}
		}
		return false;
	}
	
	public boolean containsByName(String name) {
		for(int i = 0; i < size(); i ++) {
			if(get(i).getName().equals(name)) {
				return true;
			} else {
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
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
		CourseBag other = (CourseBag) obj;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		return true;
	}
	
	

}
