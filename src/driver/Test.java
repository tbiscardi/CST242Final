package driver;

import model.Database;

public class Test {

	public static void main(String[] args) {
		Database db = new Database();
		db.readTextFiles();
		db.saveData();
		db.openData();
	}

}
