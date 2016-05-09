package model;

import java.io.Serializable;

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
