package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Bag class that holds an ArrayList of Students
 * 
 * @author Tom Biscardi
 */
public class StudentBag implements Bag, Serializable {

	private ArrayList<Student> students;

	/**
	 * Constructor that initialized ArrayList called students
	 */
	public StudentBag() {
		students = new ArrayList<>();
	}

	/**
	 * Constructor that sets students to the list in the arguments
	 * 
	 * @param list
	 */
	public StudentBag(ArrayList<Student> list) {
		students = list;
	}

	/**
	 * Adds a person to the ArrayList.
	 * 
	 * @param p
	 * @precondition Person entered is a Student with type 2.
	 * @postcondition Size increases by 1 and p is added to students.
	 */
	@Override
	public void add(Person p) {
		if (p.getType() == 0) {
			students.add((Student) p);
		}
	}

	/**
	 * Deletes a person from the ArrayList
	 * 
	 * @param p
	 */
	@Override
	public void delete(Person p) {
		students.remove(p);
	}
	
	/**
	 * Returns size of the StudentBag
	 * 
	 * @return size of students
	 */
	public int size() {
		return students.size();
	}

	/**
	 * Get student by ID
	 * 
	 * @param id
	 * @return Student searched
	 */
	public Student get(String id) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getId().equals(id)) {
				return students.get(i);
			} else {

			}
		}
		return null;
	}

	/**
	 * Get student by username and password
	 * 
	 * @param username
	 * @param password
	 * @return Student searched
	 */
	public Student get(String username, String password) {
		for (int i = 0; i < students.size(); i++) {
			if ((students.get(i).getUsername().equals(username))
					&& (students.get(i).getPassword().equals(password))) {
				return students.get(i);
			} else {

			}
		}
		return null;
	}

}
