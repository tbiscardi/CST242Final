package model;

import java.io.Serializable;

/**
 * Person subclass class that automatically sets type to 1
 * 
 * @author Tom Biscardi
 */
public class Faculty extends Person implements Serializable {

	public Faculty() {
		super();
		setType(1);
	}

	public Faculty(String fName, String lName, String username, String password) {
		super(fName, lName, username, password);
		setType(1);
	}

}
