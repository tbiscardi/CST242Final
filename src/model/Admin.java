package model;

import java.io.Serializable;

/**
 * Extension of Person class with the automatically set type as 2.
 * 
 * @author Tom Biscardi
 */
public class Admin extends Person implements Serializable {

	public Admin() {
		super();
		setType(2);
	}

	public Admin(String fName, String lName, String username, String password) {
		super(fName, lName, username, password);
		setType(2);
	}

}
