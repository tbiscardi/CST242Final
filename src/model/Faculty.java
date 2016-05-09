package model;

import java.io.Serializable;

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
