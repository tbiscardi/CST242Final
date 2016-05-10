package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Bag class that holds an ArrayList for Faculty
 * 
 * @author Tom Biscardi
 */
public class FacultyBag implements Bag, Serializable {

	private ArrayList<Faculty> faculty;

	/**
	 * Constructor that initialized ArrayList called faculty
	 */
	public FacultyBag() {
		faculty = new ArrayList<>();
	}

	/**
	 * Constructor that sets faculty to the list in the arguments
	 * 
	 * @param list
	 */
	public FacultyBag(ArrayList<Faculty> list) {
		faculty = list;
	}

	/**
	 * Adds a person to the ArrayList.
	 * 
	 * @param p
	 * @precondition Person entered is a Faculty with type 1.
	 * @postcondition Size increases by 1 and p is added to faculty.
	 */
	@Override
	public void add(Person p) {
		if (p.getType() == 1) {
			faculty.add((Faculty) p);
		}
	}

	/**
	 * Deletes a person from the ArrayList
	 * 
	 * @param p
	 */
	@Override
	public void delete(Person p) {
		faculty.remove(p);
	}

	/**
	 * Get faculty 
	 * 
	 * @param username
	 * @param password
	 * @return Faculty searched
	 */
	public Faculty get(String username, String password) {
		for (int i = 0; i < faculty.size(); i++) {
			if ((faculty.get(i).getUsername().equals(username))
					&& (faculty.get(i).getPassword().equals(password))) {
				return faculty.get(i);
			} else {

			}
		}
		return null;
	}

}
