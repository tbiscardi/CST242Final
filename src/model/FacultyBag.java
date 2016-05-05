package model;

import java.io.Serializable;
import java.util.ArrayList;

public class FacultyBag implements Bag, Serializable {

	private ArrayList<Faculty> faculty;

	public FacultyBag() {
		faculty = new ArrayList<>();
	}

	public FacultyBag(ArrayList<Faculty> list) {
		faculty = list;
	}

	@Override
	public void add(Person p) {
		if (p.getType() == 1) {
			faculty.add((Faculty) p);
		}
	}

	@Override
	public void delete(Person p) {
		faculty.remove(p);
	}

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
