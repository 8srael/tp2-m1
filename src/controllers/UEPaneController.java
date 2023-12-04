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
import models.UE;
import utils.Utils;

public class UEPaneController implements Initializable {

	@FXML
    private StackPane stackField1;

    @FXML
    private TextField libelleField;

    @FXML
    private StackPane stackField2;

    @FXML
    private TextField codeField;

    @FXML
    private TextField hcmField;

    @FXML
    private TextField htdField;

    @FXML
    private StackPane stackField3;

    @FXML
    private TextField htpField;

    @FXML
    private TableView<UE> ueTableView;

    @FXML
    private TableColumn<UE, String> codeColumn;

    @FXML
    private TableColumn<UE, String> libelleColumn;

    @FXML
    private TableColumn<UE, Integer> hcmColumn;

    @FXML
    private TableColumn<UE, Integer> htdColumn;

    @FXML
    private TableColumn<UE, Integer> htpColumn;
    
    private Alert alert;

    @FXML
    private Label lblClose;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		libelleColumn.setCellValueFactory(new PropertyValueFactory<>("label"));
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		hcmColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursCM"));
		htdColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursTD"));
		htpColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursTP"));

		ueTableView.setItems(Utils.getObsListUE());
		
	}
	
	@FXML
    void addBtn() {
		if(codeField.getText().isEmpty() || libelleField.getText().isEmpty() || 
				hcmField.getText().isEmpty() || hcmField.getText().isEmpty() || hcmField.getText().isEmpty()) {
			alert = new Alert(AlertType.WARNING);
			
			alert.setContentText("One textfield at least is empty");
			alert.show();
		} else {
			UE ue = new UE(codeField.getText(), libelleField.getText(), Integer.parseInt(hcmField.getText()), Integer.parseInt(htdField.getText()), Integer.parseInt(htpField.getText()));
			Utils.getUeEntity().create(ue);
			Utils.getObsListUE().add(ue);
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("UE creation information".toUpperCase());
			alert.setContentText("UE created successfully");
			alert.show();
			clearControls();
			
		}

    }

    private void clearControls() {
		codeField.clear();
		libelleField.clear();
		hcmField.setText("0");
		htdField.setText("0");
		htpField.setText("0");
		
	}

	@FXML
    void deleteBtn() {

    }

    @FXML
    void updateBtn() {

    }

}
