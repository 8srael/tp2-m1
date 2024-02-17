package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import models.Year;
import utils.Utils;

public class YearPaneController implements Initializable {
	
	@FXML
    private StackPane stackField1;

    @FXML
    private TextField libelleField;

    @FXML
    private TableView<Year> yearTableView;

    @FXML
    private TableColumn<String, Year> libelleColumn;

    @FXML
    private Label lblClose;
    
    private Alert alert;
        
    private Year selectedYear;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		libelleColumn.setCellValueFactory(new PropertyValueFactory<>("label"));

		yearTableView.setItems(Utils.getObsListYear());
		
		yearTableView.getSelectionModel().selectedItemProperty().addListener((obs, old, neww) -> {
			selectedYear = obs.getValue();
    		if(selectedYear != null) {
    			libelleField.setText(selectedYear.getLabel());
    		}
		});
	}
	
	@FXML
    void addBtn() {
		if(libelleField.getText().isEmpty()) {
			alert = new Alert(AlertType.WARNING);
			
			alert.setContentText("One textfield at least is empty");
			alert.show();
		} else {
			Year year = new Year(libelleField.getText());
			Utils.getYearEntity().create(year);
			Utils.getObsListYear().add(year);
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Year creation information".toUpperCase());
			alert.setContentText("Year created successfully");
			alert.show();
			libelleField.clear();
			
		}
    }

    @FXML
    void deleteBtn() {
    	if (selectedYear == null) {
    		alert = new Alert(AlertType.WARNING);
    		alert.setTitle("DELETE WARNING");
    		alert.setContentText("No Year selected !\nPlease select a Year 👤");
    		alert.show();
    	} else {
    		System.out.println(selectedYear);
    		Utils.getYearEntity().delete(selectedYear.getId());
    		
    		Utils.getObsListYear().remove(selectedYear);
    		alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Year".toUpperCase());
			alert.setContentText("Year deleted successfully");
			alert.show();
			
			clearControls();
			yearTableView.getSelectionModel().clearSelection();
    	}

    }

    @FXML
    void updateBtn() {
    	if (selectedYear == null) {
    		alert = new Alert(AlertType.WARNING);
    		alert.setTitle("UPDATE WARNING");
    		alert.setContentText("No UE selected !\nPlease select an UE 👤");
    		alert.show();
    	} else {
    		System.out.println(selectedYear);
    		if(!libelleField.getText().isEmpty()) {
   			 	
    			selectedYear.setLabel(libelleField.getText());

   			 	// update the updating person instance in observableList of person 
   			 	Utils.getObsListYear().set(Utils.getObsListYear().indexOf(selectedYear), Utils.getYearEntity().update(selectedYear));
   				libelleField.setText("");
   				// Removes the focus on the updated person
   	    		yearTableView.getSelectionModel().clearSelection();
    		}
    		
    		alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Year update information".toUpperCase());
			alert.setContentText("Year updated successfully");
			alert.show();
    	}
    }
    
    
    private void clearControls() {
		libelleField.clear();
	} 
}
