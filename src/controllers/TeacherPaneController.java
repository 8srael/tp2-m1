package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import com.jfoenix.controls.JFXComboBox;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import models.Teacher;
import utils.GenericEntity;
import utils.JPAUtil;
import utils.Utils;

public class TeacherPaneController implements Initializable {

 	@FXML
    private StackPane stackField1;

    @FXML
    private StackPane stackField2;

    @FXML
    private StackPane stackField3;

    @FXML
    private StackPane stackField4;
    
    @FXML
    private TableColumn<Teacher, String> firstNameColumn;

    @FXML
    private TableColumn<Teacher, String> lastNameColumn;

    @FXML
    private TableColumn<Teacher, String> telColumn;

    @FXML
    private TableColumn<Teacher, String> emailColumn;

    @FXML
    private TableColumn<Teacher, String> gradeColumn;
    
    @FXML
    private JFXComboBox<String> gradeComboBox;
    
    @FXML
    private TextField firstNameField;
    
    @FXML
    private TextField lastNameField	;
    
    @FXML
    private TextField telField;
    
    @FXML
    private TextField emailField;
    
    private Teacher selectedTeacher;
    
    private Alert alert;
    
    @FXML
    private TableView<Teacher> teacherTableView;
	    
    //Animated textfields container
	private List<StackPane> stackFields = new ArrayList<>();
		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		stackFields.add(stackField1);
		stackFields.add(stackField2);
		stackFields.add(stackField3);
		stackFields.add(stackField4);
//		FXControlsUtils.textFieldAnimation(stackFields);
		
		System.out.println(Utils.getObsListTeacher());
	
		// Grade Combo
		System.out.println(gradeComboBox);
		gradeComboBox.getItems().addAll("Professeur Titulaire".toUpperCase(), "Maitre de Conférences".toUpperCase(), "Maitre Assisatnt".toUpperCase(), "Assistant".toUpperCase());
		
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
		
		teacherTableView.getSelectionModel().selectedItemProperty().addListener((obs, old, neww) -> {
			if(obs != null) {
				
				selectedTeacher = obs.getValue();
				if(selectedTeacher != null) {
					firstNameField.setText(selectedTeacher.getFirstName());
					lastNameField.setText(selectedTeacher.getLastName());
					gradeComboBox.setValue(selectedTeacher.getGrade());
					telField.setText(selectedTeacher.getTel());
					emailField.setText(selectedTeacher.getEmail());
				}

				
			}
		});
		
		teacherTableView.setItems(Utils.getObsListTeacher());		
		
	}
	
	 @FXML
	 public void addButton() {
		 System.out.println(gradeComboBox.getSelectionModel().getSelectedItem());
		 
		 if(firstNameField.getText().length() == 0 || lastNameField.getText().length() == 0 || emailField.getText().length() == 0
				 || telField.getText().length() == 0 || gradeComboBox.getSelectionModel().getSelectedItem() == null) {
			alert = new Alert(AlertType.WARNING);
			
			alert.setContentText("One textfield at least is empty");
			alert.show();
		} else {
			Teacher teacher = new Teacher(firstNameField.getText().toUpperCase(), lastNameField.getText(), emailField.getText(), telField.getText(), gradeComboBox.getSelectionModel().selectedItemProperty().get());;
			Utils.getTeacherEntity().create(teacher);
			Utils.getObsListTeacher().add(teacher);
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Teacher creation information".toUpperCase());
			alert.setContentText("Teacher created successfully");
			alert.show();
			clearControls();
			
		}
	 }

	 @FXML
	 public void updateButton() {
		 if(selectedTeacher == null) {
			 alert = new Alert(AlertType.WARNING);
    		alert.setTitle("UPDATE WARNING");
    		alert.setContentText("No teacher selected !\nPlease select a teacher 👤");
    		alert.show();
		 } else {
	 		if(!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty()  && !emailField.getText().isEmpty() || !telField.getText().isEmpty() && !gradeComboBox.getValue().isEmpty()) {
	 			selectedTeacher.setFirstName(firstNameField.getText());
	 			selectedTeacher.setLastName(lastNameField.getText());
	 			selectedTeacher.setGrade(gradeComboBox.getSelectionModel().selectedItemProperty().get());
	 			selectedTeacher.setTel(telField.getText());
	 			selectedTeacher.setEmail(emailField.getText());
	 			
	 			Utils.getObsListTeacher().set(Utils.getObsListTeacher().indexOf(selectedTeacher), Utils.getTeacherEntity().update(selectedTeacher));
	 			
	 			clearControls();
	 				 			
	 			teacherTableView.getSelectionModel().clearSelection();
	 		}
	 		
	 		alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Teacher updated information".toUpperCase());
			alert.setContentText("Teacher updated successfully");
			alert.show();
		 }
	 }
	 
	 @FXML
	 public void deleteBtn() {
		 if(selectedTeacher == null) {
			 alert = new Alert(AlertType.WARNING);
			 alert.setTitle("DELET WARNING");
			 alert.setContentText("No teacher selected !\nPlease select a teacher 👤");
			 alert.show();
		 } else {
			   Utils.getTeacherEntity().delete(selectedTeacher.getId());
			 
				// delete the chosen teacher instance in observableList of person 
			   Utils.getObsListTeacher().remove(selectedTeacher);
	    		alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Teacher delete information".toUpperCase());
				alert.setContentText("Teacher deleted successfully");
				alert.show();
				
				clearControls();
	 			teacherTableView.getSelectionModel().clearSelection();
		 }
		 
	 }
	 
	 private  void clearControls() {
		firstNameField.clear();
		lastNameField.clear();
		telField.clear();
		emailField.clear();
		gradeComboBox.getSelectionModel().clearSelection();
	 }

}
