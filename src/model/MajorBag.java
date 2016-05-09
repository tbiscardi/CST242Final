package model;

import java.io.Serializable;
import java.util.ArrayList;

public class MajorBag implements Serializable {

	private ArrayList<Major> majors;

	public MajorBag() {
		majors = new ArrayList<>();
	}

	public void add(Major m) {
		majors.add(m);
	}

	public void remove(Major m) {
		majors.remove(m);
	}

	public Major get(String name) {
		for (int i = 0; i < majors.size(); i++) {
			if (majors.get(i).getName().equals(name)) {
				return majors.get(i);
			} else {

			}
		}
		return null;
	}

	public Major get(int index) {
		return majors.get(index);
	}

	public int size() {
		return majors.size();
	}

}
