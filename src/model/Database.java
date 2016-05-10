package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Database class can read from the text files and store data into them such as
 * Persons and Majors.
 * 
 * @author Tom Biscardi
 */
public class Database {

	private PersonBag persons;
	private MajorBag majors;

	public Database() {
		persons = new PersonBag();
		majors = new MajorBag();
	}

	/**
	 * Reads text files into the binary file "Data.dat"
	 * 
	 * @precondition Files can be opened
	 * @postcondition This class holds the values in the text files
	 */
	public void readTextFiles() {
		String fileName1 = "Students.txt";
		String fileName2 = "OtherUsers.txt";
		String fileName3 = "Majors.txt";
		File fr1;
		File fr2;
		File fr3;
		StudentBag sb = new StudentBag();
		FacultyBag fb = new FacultyBag();
		AdminBag ab = new AdminBag();
		try {
			CourseBag cb;
			fr3 = new File(fileName3);
			Scanner input3 = new Scanner(fr3);
			String[] str3;
			while (input3.hasNextLine()) {
				String name = input3.nextLine();
				str3 = input3.nextLine().split(",");
				cb = new CourseBag();
				for (int i = 0; i < str3.length; i++) {
					cb.add(new Course(str3[i], ""));
				}
				final Major newMajor = new Major(name, cb);
				majors.add(newMajor);
			}
			fr1 = new File(fileName1);
			Scanner input1 = new Scanner(fr1);
			String[] str;

			while (input1.hasNextLine()) {

				str = input1.nextLine().split(",");
				Student s1 = new Student(str[0], str[1], str[2], str[3], str[4]);
				s1.setMajor(majors.get(str[5]));
				str = input1.nextLine().split(",");
				cb = new CourseBag();
				for (int i = 0; i < str.length; i++) {
					String[] temp = str[i].split(" ");
					cb.add(new Course(temp[0], temp[1]));

				}
				s1.setTaken(cb);

				cb = new CourseBag();
				str = input1.nextLine().split(",");
				for (int i = 0; i < str.length; i++) {
					cb.add(new Course(str[i], "IP"));
				}
				s1.setTaking(cb);

				cb = new CourseBag();
				str = input1.nextLine().split(",");
				for (int i = 0; i < str.length; i++) {
					String[] temp = str[i].split(" ");
					cb.add(new Course(temp[0], temp[1]));

				}
				s1.setFailed(cb);

				cb = new CourseBag();
				str = input1.nextLine().split(",");
				for (int i = 0; i < str.length; i++) {
					String[] temp = str[i].split(" ");
					cb.add(new Course(temp[0], temp[1]));

				}
				s1.setOther(cb);
				s1.fillCoursesNeeded();
				sb.add(s1);

			}
			fr2 = new File(fileName2);
			Scanner input2 = new Scanner(fr2);
			input2.nextLine();
			String[] str2;
			while (input2.hasNextLine()) {
				str2 = input2.nextLine().split(" ");
				int type = Integer.parseInt(str2[4]);
				if (type == 1) {
					Faculty f1 = new Faculty(str2[0], str2[1], str2[2], str2[3]);
					fb.add(f1);
				} else {
					Admin a1 = new Admin(str2[0], str2[1], str2[2], str2[3]);
					ab.add(a1);
				}
			}

			input1.close();
			input2.close();
			input3.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		persons.addStudents(sb);
		persons.addFaculty(fb);
		persons.addAdmin(ab);
	}

	/**
	 * Saves any modified data to binary file
	 * 
	 * @precondition binary file is accessible
	 * @postcondition binary file data is updated
	 */
	public void saveData() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("Data.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(majors);
			oos.writeObject(persons);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Sets majors and persons to the MajorBag and PersonBag stored in the
	 * binary file
	 * 
	 * @precondition binary file can be opened
	 * @postcondition majors is set to the MajorBag and persons is set to the
	 *                PersonBag in the file
	 */
	public void openData() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("Data.dat");
			ois = new ObjectInputStream(fis);
			majors = (MajorBag) ois.readObject();
			persons = (PersonBag) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			readTextFiles();
			saveData();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			readTextFiles();
			saveData();
		} catch (Exception e) {
			readTextFiles();
			saveData();
		}

	}

	public PersonBag getPersons() {
		return persons;
	}

	public MajorBag getMajors() {
		return majors;
	}

	/**
	 * Deletes a Student from the file by their ID
	 * 
	 * @param id
	 * @throws NullPointerException
	 * @precondition student ID needs to be found
	 * @postcondition student is delete from binary file
	 */
	public void delete(String id) throws NullPointerException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("Data.dat");
			ois = new ObjectInputStream(fis);
			majors = (MajorBag) ois.readObject();
			persons = (PersonBag) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		persons.getStudentBag().delete(persons.getStudentBag().get(id));
	}

	/**
	 * Adds a student to the binary file
	 * 
	 * @param s
	 * @precondition student with specified ID does not exist in file
	 * @postcondition 
	 */
	public void addStudent(Student s) {
		if (s.getType() == 0) {
			for(int i = 0; i < persons.getStudentBag().size(); i ++) {
				if(!persons.getStudentBag().get(s.getId()).equals(null)) {
					return;
				}
			}
			
			if ((s.getId().equals(null))) {
				
			} else {
				persons.getStudentBag().add(s);
			}
		}
	}

}
