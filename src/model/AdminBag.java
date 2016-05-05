package model;

import java.io.Serializable;
import java.util.ArrayList;

public class AdminBag implements Bag, Serializable{
	
private ArrayList<Admin> admins;
	
	public AdminBag() {
		admins = new ArrayList<>();
	}
	
	public AdminBag(ArrayList<Admin> list) {
		admins = list;
	}

	@Override
	public void add(Person p) {
		if(p.getType() == 2) {
			admins.add((Admin) p);
		}
	}

	@Override
	public void delete(Person p) {
		admins.remove(p);
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
