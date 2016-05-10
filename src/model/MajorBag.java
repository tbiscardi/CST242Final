package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Bag class that holds an ArrayList of Majors
 * 
 * @author Tom Biscardi
 */
public class MajorBag implements Serializable {

	private ArrayList<Major> majors;

	public MajorBag() {
		majors = new ArrayList<>();
	}

	/**
	 * Adds a major to the ArrayList
	 * 
	 * @param m
	 * @precondition m is a Major
	 * @postcondition m added to ArrayList
	 */
	public void add(Major m) {
		majors.add(m);
	}

	/**
	 * Removes a major from the ArrayList
	 * 
	 * @param m
	 * @precondition m is in the list
	 * @postcondition m is removed from list
	 */
	public void remove(Major m) {
		majors.remove(m);
	}

	/**
	 * Returns a Major by its name
	 * 
	 * @param name
	 * @return major with name param name
	 */
	public Major get(String name) {
		for (int i = 0; i < majors.size(); i++) {
			if (majors.get(i).getName().equals(name)) {
				return majors.get(i);
			} else {

			}
		}
		return null;
	}

	/**
	 * Returns a major by its index in the ArrayList
	 * 
	 * @param index
	 * @return major of param index
	 */
	public Major get(int index) {
		return majors.get(index);
	}

	/**
	 * Returns the size of the ArrayList
	 * 
	 * @return size of majors
	 */
	public int size() {
		return majors.size();
	}

}
