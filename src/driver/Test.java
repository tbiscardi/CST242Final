package driver;

import model.Database;

/**
 * This class is simply used for testing purposes and the code contained in Main
 * was run through when I changed data in my models
 * 
 * @author Tom Biscardi
 */
public class Test {

	public static void main(String[] args) {
		Database db = new Database();
		db.readTextFiles();
		db.saveData();
		db.openData();
	}

}
