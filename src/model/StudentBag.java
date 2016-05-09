package model;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentBag implements Bag, Serializable {

	private ArrayList<Student> students;

	public StudentBag() {
		students = new ArrayList<>();
	}

	public StudentBag(ArrayList<Student> list) {
		students = list;
	}

	@Override
	public void add(Person p) {
		if (p.getType() == 0) {
			students.add((Student) p);
		}
	}

	@Override
	public void delete(Person p) {
		students.remove(p);
	}

	public Student get(String id) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getId().equals(id)) {
				return students.get(i);
			} else {

			}
		}
		return null;
	}

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
