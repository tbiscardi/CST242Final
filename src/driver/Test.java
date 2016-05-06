package driver;

import model.Database;

public class Test {

	public static void main(String[] args) {
		Database db = new Database();
//		db.openData();
//		System.out.println(db.getPersons().getStudentBag().get("6666").getfName());
		db.readTextFiles();
		db.saveData();
		db.openData();
	}

}
