package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that holds an ArrayList of Courses
 * 
 * @author Tom Biscardi
 */
public class CourseBag implements Serializable {

	private ArrayList<Course> courses;

	public CourseBag() {
		courses = new ArrayList<>();
	}
	
	public void set(ArrayList<Course> courses) {
		this.courses = courses;
	}

	/**
	 * Adds a course to courses ArrayList
	 * 
	 * @param c
	 */
	public void add(Course c) {
		courses.add(c);
	}

	/**
	 * Removes course from courses ArrayList
	 * 
	 * @param c
	 */
	public void remove(Course c) {
		courses.remove(c);
	}

	/**
	 * Clears all data from Arraylist courses
	 */
	public void removeAll() {
		while (!(size() == 0)) {
			remove(courses.get(0));
		}
	}

	/**
	 * Returns the size of ArrayList courses
	 * 
	 * @return size of courses
	 */
	public int size() {
		return courses.size();
	}

	/**
	 * Gets the course at the index
	 * 
	 * @param index
	 * @return course in ArrayList of specified index
	 */
	public Course get(int index) {
		return courses.get(index);
	}

	/**
	 * Gets the course by its name
	 * 
	 * @param c
	 * @return course searched from the name
	 */
	public Course getByName(Course c) {
		for (int i = 0; i < size(); i++) {
			if (get(i).getName().equals(c.getName())) {
				return courses.get(i);
			} else {

			}
		}
		return null;

	}

	/**
	 * Boolean that checks whether this Bag contains param c
	 * 
	 * @param c
	 * @return true if courses contains param c
	 */
	public boolean contains(Course c) {
		for (int i = 0; i < size(); i++) {
			if (get(i).equals(c)) {
				return true;
			} else {
			}
		}
		return false;
	}

	/**
	 * Boolean that checks whether this Bag has a course with the same name as c
	 * 
	 * @param c
	 * @return true if courses contains param c
	 */
	public boolean containsByName(Course c) {
		for (int i = 0; i < size(); i++) {
			if (get(i).getName().equals(c.getName())) {
				return true;
			} else {
			}
		}
		return false;
	}

	/**
	 * Boolean that checks whether this Bag has a course with name param name
	 * 
	 * @param name
	 * @return true if courses contains a course with name param name
	 */
	public boolean containsByName(String name) {
		for (int i = 0; i < size(); i++) {
			if (get(i).getName().equals(name)) {
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
