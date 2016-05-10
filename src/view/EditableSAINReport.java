package view;

import java.util.ArrayList;

import handling.GlobalVariables;
import handling.GlobalVariables.Events;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * View that creates a Scene for the Student or Faculty to view their Uneditable
 * SAIN Report.
 * 
 * @author Tom Biscardi
 */
public class EditableSAINReport extends View {

	private Stage newStage2;
	private Label nameLabel, idLabel, gpaLabel, majorLabel, cLabel;
	private TextField name, id, gpa;
	private ComboBox<String> major, gradeField1;
	private ObservableList<String> items, items2;
	private ListView<String> takenList, takingList, neededList, otherList,
			failedList, coursesList;
	private TextField newCourseField;

	/**
	 * Scene is changed so that is shows all the students data in labels and
	 * listViews.
	 * 
	 * @param stage
	 */
	public EditableSAINReport(Stage stage) {
		super(stage, new VBox(10), GlobalVariables.width,
				GlobalVariables.height * 2);
		obsArr = new ArrayList<>();
		VBox vb = (VBox) getRoot();
		vb.setStyle("-fx-alignment: center center");

		GridPane gp = new GridPane();
		nameLabel = new Label("Name:");
		name = new TextField();

		idLabel = new Label("ID:");
		id = new TextField();
		id.setEditable(false);

		gpaLabel = new Label("GPA:");
		gpa = new TextField();
		gpa.setEditable(false);

		majorLabel = new Label("Major:");
		major = new ComboBox<>();

		Button go = new Button("Change");

		GridPane.setConstraints(nameLabel, 0, 0);
		GridPane.setConstraints(name, 1, 0);
		GridPane.setConstraints(idLabel, 0, 1);
		GridPane.setConstraints(id, 1, 1);
		GridPane.setConstraints(gpaLabel, 0, 2);
		GridPane.setConstraints(gpa, 1, 2);
		GridPane.setConstraints(majorLabel, 0, 3);
		GridPane.setConstraints(major, 1, 3);
		GridPane.setConstraints(go, 2, 3);

		go.setOnAction(e -> {
			NotifyObserver(Events.CHANGE_MAJOR);
		});

		gp.getChildren().addAll(nameLabel, name, idLabel, id, gpaLabel, gpa,
				majorLabel, major, go);
		gp.setAlignment(Pos.TOP_CENTER);

		HBox coursePane1 = new HBox(10);
		HBox coursePane2 = new HBox(10);

		Label takenL = new Label("Required Courses Taken:");
		takenList = new ListView<String>();
		VBox l1 = new VBox();
		l1.getChildren().addAll(takenL, takenList);
		l1.setAlignment(Pos.CENTER);

		Label takingL = new Label("Currently Taking:");
		takingList = new ListView<String>();
		VBox l2 = new VBox();
		l2.getChildren().addAll(takingL, takingList);
		l2.setAlignment(Pos.CENTER);

		Label otherL = new Label("Other Courses Taken:");
		otherList = new ListView<String>();
		VBox l3 = new VBox();
		l3.getChildren().addAll(otherL, otherList);
		l3.setAlignment(Pos.CENTER);

		Label failedL = new Label("Withdrawn/Failed:");
		failedList = new ListView<String>();
		VBox l4 = new VBox();
		l4.getChildren().addAll(failedL, failedList);
		l4.setAlignment(Pos.CENTER);

		Label neededL = new Label("Courses Needed:");
		neededList = new ListView<String>();
		VBox l5 = new VBox();
		l5.getChildren().addAll(neededL, neededList);
		l5.setAlignment(Pos.CENTER);

		takenL.setMaxWidth(200);
		takingL.setMaxWidth(200);
		otherL.setMaxWidth(200);
		failedL.setMaxWidth(200);
		neededL.setMaxWidth(200);

		coursePane1.getChildren().addAll(l1, l2, l3);
		coursePane1.setAlignment(Pos.CENTER);
		coursePane2.getChildren().addAll(l4, l5);
		coursePane2.setAlignment(Pos.CENTER);

		HBox buttonPane = new HBox(10);
		Button add = new Button("Add Course");
		Button remove = new Button("Remove Course");
		Button editCourse = new Button("Edit Course");
		add.setOnAction(e -> {
			addButtonStage();
		});
		remove.setOnAction(e -> {
			removeButtonStage();

		});
		editCourse.setOnAction(e -> {
			removeButtonStage();
		});

		buttonPane.getChildren().addAll(add, remove, editCourse);
		buttonPane.setAlignment(Pos.CENTER);

		Button back = new Button("Return");
		back.setOnAction(e -> {
			NotifyObserver(Events.BACK_BUTTON);
		});

		vb.getChildren().addAll(gp, coursePane1, coursePane2, buttonPane, back);
		stage.setScene(this);
		stage.setResizable(false);
		stage.setTitle("SAIN Report");
		stage.show();

	}

	/**
	 * Set the values of the takenList ListView
	 * 
	 * @param tempCourses
	 */
	public void setTakenCoursesList(ArrayList<String> tempCourses) {
		items = FXCollections.observableArrayList(tempCourses);
		takenList.setItems(items);
	}

	/**
	 * Set the values of the takingList ListView
	 * 
	 * @param tempCourses
	 */
	public void setTakingCoursesList(ArrayList<String> tempCourses) {
		items = FXCollections.observableArrayList(tempCourses);
		takingList.setItems(items);
	}

	/**
	 * Set the values of the otherList ListView
	 * 
	 * @param tempCourses
	 */
	public void setOtherCoursesList(ArrayList<String> tempCourses) {
		items = FXCollections.observableArrayList(tempCourses);
		otherList.setItems(items);
	}

	/**
	 * Set the values of the failedList ListView
	 * 
	 * @param tempCourses
	 */
	public void setFailedCoursesList(ArrayList<String> tempCourses) {
		items = FXCollections.observableArrayList(tempCourses);
		failedList.setItems(items);
	}

	/**
	 * Set the values of the neededList ListView
	 * 
	 * @param tempCourses
	 */
	public void setNeededCoursesList(ArrayList<String> tempCourses) {
		items = FXCollections.observableArrayList(tempCourses);
		neededList.setItems(items);
	}

	/**
	 * Set the values of the takenList ListView
	 * 
	 * @param tempCourses
	 */
	public void setTakenCoursesList(ObservableList<String> tempCourses) {
		items = FXCollections.observableArrayList(tempCourses);
		takenList.setItems(items);
	}

	/**
	 * Set the values of the takingList ListView
	 * 
	 * @param tempCourses
	 */
	public void setTakingCoursesList(ObservableList<String> tempCourses) {
		items = FXCollections.observableArrayList(tempCourses);
		takingList.setItems(items);
	}

	/**
	 * Set the values of the otherList ListView
	 * 
	 * @param tempCourses
	 */
	public void setOtherCoursesList(ObservableList<String> tempCourses) {
		items = FXCollections.observableArrayList(tempCourses);
		otherList.setItems(items);
	}

	/**
	 * Set the values of the failedList ListView
	 * 
	 * @param tempCourses
	 */
	public void setFailedCoursesList(ObservableList<String> tempCourses) {
		items = FXCollections.observableArrayList(tempCourses);
		failedList.setItems(items);
	}

	/**
	 * Set the values of the neededList ListView
	 * 
	 * @param tempCourses
	 */
	public void setNeededCoursesList(ObservableList<String> tempCourses) {
		items = FXCollections.observableArrayList(tempCourses);
		neededList.setItems(items);
	}

	/**
	 * Set the values of the coursesList ListView
	 * 
	 * @param tempCourses
	 */
	public void setCoursesList(ObservableList<String> tempCourses) {
		items = FXCollections.observableArrayList(tempCourses);
		coursesList.setItems(items2);
	}

	public void setName(String name) {
		this.name.setText(name);
	}

	public void setId(String id) {
		this.id.setText(id);
	}

	public void setGpa(String gpa) {
		this.gpa.setText(gpa);
	}

	public void setMajor(String major) {
		this.major.setPromptText(major);
	}

	/**
	 * Adds a value to the major ComboBox
	 * 
	 * @param major
	 */
	public void addMajors(String major) {
		this.major.getItems().add(major);
	}

	/**
	 * Removes all items in the major ComboBox
	 */
	public void removeItems() {
		major.getItems().removeAll(major.getItems());
	}

	public String getName() {
		return name.getText();
	}

	public String getId() {
		return id.getText();
	}

	public String getGpa() {
		return gpa.getText();
	}

	public String getMajor() {
		return major.getValue();
	}

	public String getPromptTextMajor() {
		return major.getPromptText();
	}

	public ObservableList<String> getTakingArea() {
		return takingList.getItems();
	}

	public ObservableList<String> getTakenArea() {
		return takenList.getItems();
	}

	public ObservableList<String> getOtherArea() {
		return otherList.getItems();
	}

	public ObservableList<String> getNeededArea() {
		return neededList.getItems();
	}

	public ObservableList<String> getFailedArea() {
		return failedList.getItems();
	}

	/**
	 * Opens the add button stage which allows the user to input any class name
	 * and select the grade they desire.
	 */
	public void addButtonStage() {
		Stage newStage = new Stage();
		newStage.setTitle("Add Course");
		Label newCourseL = new Label("Enter Course:");
		newCourseField = new TextField("e.g. CST141");

		Label gradeL = new Label("Grade:");
		gradeField1 = new ComboBox<String>();
		gradeField1.getItems().addAll("A", "B+", "B", "C+", "C", "D+", "D",
				"F", "W", "IP");

		GridPane gp = new GridPane();
		GridPane.setConstraints(newCourseL, 0, 0);
		GridPane.setConstraints(newCourseField, 1, 0);
		GridPane.setConstraints(gradeL, 0, 1);
		GridPane.setConstraints(gradeField1, 1, 1);

		Button addB = new Button("Add Course");
		addB.setOnAction(e -> {
			NotifyObserver(Events.ADD_COURSE);
			newStage.close();
		});
		addB.setAlignment(Pos.CENTER);

		gp.getChildren()
				.addAll(newCourseL, newCourseField, gradeL, gradeField1);
		gp.setAlignment(Pos.CENTER);

		VBox localRoot = new VBox(10);
		localRoot.setAlignment(Pos.CENTER);
		localRoot.getChildren().addAll(gp, addB);
		newStage.setScene(new Scene(localRoot, 500, 300));
		newStage.show();
	}

	public String getCourseA() {
		return newCourseField.getText();
	}

	public String getGrade() {
		return gradeField1.getValue();
	}

	/**
	 * Remove and Edit button stage generation where a listview of all the
	 * students courses are created and the user may select one and either
	 * delete it or change the grade.
	 */
	public void removeButtonStage() {
		newStage2 = new Stage();
		newStage2.setTitle("Modify Courses");
		VBox localRoot = new VBox(10);
		Label selectLabel = new Label("Select Course: ");
		coursesList = new ListView<String>();
		coursesList.setMaxWidth(200);

		ObservableList<String> tempCourses = FXCollections.observableArrayList();
		tempCourses.addAll(getTakingArea());
		tempCourses.addAll(getTakenArea());
		tempCourses.addAll(getOtherArea());
		tempCourses.addAll(getFailedArea());
		setCoursesList(tempCourses);

		items2 = FXCollections.observableArrayList(tempCourses);
		coursesList.setItems(items2);

		Button edit = new Button("Edit");
		edit.setOnAction(e -> {
			editStage();
		});

		Button delete = new Button("Delete");
		delete.setOnAction(e -> {
			NotifyObserver(Events.REMOVE_COURSE);
			newStage2.close();
		});

		HBox buttons = new HBox(10);
		buttons.getChildren().addAll(edit, delete);
		buttons.setAlignment(Pos.CENTER);
		localRoot.getChildren().addAll(selectLabel, coursesList, buttons);
		localRoot.setAlignment(Pos.CENTER);

		newStage2.setScene(new Scene(localRoot, 300, 400));
		newStage2.show();
	}

	/**
	 * Gets the data from what the user selected in the courses list
	 * @return String holding course name + "\t" + grade
	 */
	public String getSelectedCoursesItem() {
		return coursesList.getSelectionModel().getSelectedItem();
	}

	/**
	 * Edit stage generated that allows the user to change a grade of the course they selected
	 */
	public void editStage() {
		Stage newStage = new Stage();
		newStage.setTitle("Edit Selected Course");
		String toEdit = getSelectedCoursesItem();
		if (toEdit == null) {

		} else {
			String[] str = getSelectedCoursesItem().split("\t");

			cLabel = new Label(str[0]);
			gradeField1 = new ComboBox<String>();
			gradeField1.getItems().addAll("A", "B+", "B", "C+", "C", "D+", "D",
					"F", "W", "IP");
			gradeField1.setPromptText(str[1]);
			HBox hb = new HBox(10);
			hb.getChildren().addAll(cLabel, gradeField1);
			hb.setAlignment(Pos.CENTER);

			Button editB = new Button("Confirm");

			editB.setOnAction(e -> {
				NotifyObserver(Events.EDIT_COURSE);
				newStage.close();
				newStage2.close();
			});

			VBox localRoot = new VBox(10);
			localRoot.setStyle("-fx-alignment: center center");
			localRoot.getChildren().addAll(hb, editB);
			newStage.setScene(new Scene(localRoot, 300, 200));
			newStage.show();
		}
	}

	public String getCLabel() {
		return cLabel.getText();
	}

}
