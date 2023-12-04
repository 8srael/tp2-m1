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
    
//    private Year year;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		libelleColumn.setCellValueFactory(new PropertyValueFactory<>("label"));

		yearTableView.setItems(Utils.getObsListYear());
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

    }

    @FXML
    void updateBtn() {

    }
}
