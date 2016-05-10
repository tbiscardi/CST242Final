package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class used to store an ArrayList of Admins. Implements Bag class.
 * 
 * @author Tom Biscardi
 */
public class AdminBag implements Bag, Serializable {

	private ArrayList<Admin> admins;
	public int size;

	/**
	 * Constructor that initialized ArrayList called admins and sets size to 0
	 */
	public AdminBag() {
		admins = new ArrayList<>();
		size = 0;
	}

	/**
	 * Constructor that sets admins to the list in the arguments
	 * 
	 * @param list
	 */
	public AdminBag(ArrayList<Admin> list) {
		admins = list;
		size = list.size();
	}

	/**
	 * Adds a person to the ArrayList.
	 * 
	 * @param p
	 * @precondition Person entered is an Admin with type 2.
	 * @postcondition Size increases by 1 and p is added to admins.
	 */
	@Override
	public void add(Person p) {
		if (p.getType() == 2) {
			admins.add((Admin) p);
			size++;
		}
	}

	/**
	 * Deletes a person from the ArrayList
	 * 
	 * @param p
	 */
	@Override
	public void delete(Person p) {
		admins.remove(p);
		size--;
	}

	/**
	 * Get Admin 
	 * 
	 * @param username
	 * @param password
	 * @return Admin searched
	 */
	public Admin get(String username, String password) {
		for (int i = 0; i < admins.size(); i++) {
			if ((admins.get(i).getUsername().equals(username))
					&& (admins.get(i).getPassword().equals(password))) {
				return admins.get(i);
			} else {

			}
		}
		return null;
	}

}
