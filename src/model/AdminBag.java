package model;

import java.io.Serializable;
import java.util.ArrayList;

public class AdminBag implements Bag, Serializable {

	private ArrayList<Admin> admins;
	public int size;

	public AdminBag() {
		admins = new ArrayList<>();
		size = 0;
	}

	public AdminBag(ArrayList<Admin> list) {
		admins = list;
		size = list.size();
	}

	@Override
	public void add(Person p) {
		if (p.getType() == 2) {
			admins.add((Admin) p);
			size++;
		}
	}

	@Override
	public void delete(Person p) {
		admins.remove(p);
		size--;
	}

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
