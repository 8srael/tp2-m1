package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import models.Group;
import models.UE;
import models.Year;
import utils.CombinedDataGroup;
import utils.Utils;

public class GroupPaneController implements Initializable {

 	@FXML
    private StackPane stackField1;

    @FXML
    private StackPane stackField2;

    @FXML
    private StackPane stackField3;

    @FXML
    private TextField gcmField;

    @FXML
    private TextField gtdField;

    @FXML
    private TextField gtpField;
    
    @FXML
    private Label yearLabel;
    
    @FXML
    private JFXComboBox<UE> ueCombo;

    @FXML
    private JFXComboBox<Year> yearCombo;

    @FXML
    private TableView<CombinedDataGroup> groupTableView;

    @FXML
    private TableColumn<CombinedDataGroup, String> codeColumn;

    @FXML
    private TableColumn<CombinedDataGroup, String> libelleColumn;

    @FXML
    private TableColumn<CombinedDataGroup, Integer> hcmColumn;

    @FXML
    private TableColumn<CombinedDataGroup, Integer> gcmColumn;

    @FXML
    private TableColumn<CombinedDataGroup, Integer> htdColumn;

    @FXML
    private TableColumn<CombinedDataGroup, Integer> gtdColumn;

    @FXML
    private TableColumn<CombinedDataGroup, Integer> htpColumn;
    
    @FXML
    private TableColumn<CombinedDataGroup, Integer> gtpColumn;

    @FXML
    private Label lblClose;

    private Alert alert;
    private List<StackPane> stackFields = new ArrayList<>();
	 
	ObservableList<CombinedDataGroup> combinedDataObsList = FXCollections.observableArrayList();

	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ueCombo.setItems(Utils.getObsListUE());
		yearCombo.setItems(Utils.getObsListYear());
		
		//tableview backend treatment
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		libelleColumn.setCellValueFactory(new PropertyValueFactory<>("label"));
		hcmColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursCM"));
		gcmColumn.setCellValueFactory(new PropertyValueFactory<>("nGroupsCM"));
		htdColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursTD"));
		gtdColumn.setCellValueFactory(new PropertyValueFactory<>("nGroupsTD"));
		htpColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursTP"));
		gtpColumn.setCellValueFactory(new PropertyValueFactory<>("nGroupsTP"));
		
//		tableViewData();
//		groupTableView.setItems(combinedDataObsList);
		
		
		yearCombo.getSelectionModel().selectedItemProperty().addListener((obs, old, neww) -> {
			if(neww != null) {
				System.out.println(neww.getLabel());
				yearLabel.setText(neww.getLabel());
				
				System.out.println(neww.getGroups() + "\t" + neww.getGroups().size() );
				tableViewData(neww);
				groupTableView.setItems(combinedDataObsList);
				
			}
		});
	}
	
	@FXML
    void addBtn() {
		if(gcmField.getText().isEmpty() || gtdField.getText().isEmpty() 
				|| gtpField.getText().isEmpty()
				||yearCombo.getSelectionModel().getSelectedItem() == null 
				|| ueCombo.getSelectionModel().getSelectedItem() == null) {
			alert = new Alert(AlertType.WARNING);
			
			alert.setContentText("One textfield at least is empty");
			alert.show();
		} else {
			Group group = new Group(ueCombo.getSelectionModel().selectedItemProperty().get(),
									yearCombo.getSelectionModel().selectedItemProperty().get(),
									Integer.parseInt(gcmField.getText()), Integer.parseInt(gtdField.getText()), Integer.parseInt(gtpField.getText()));
			Utils.getGroupEntity().create(group);
			addCombinedData(group);
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Group creation information".toUpperCase());
			alert.setContentText("Group created successfully");
			alert.show();
			clearControls();
			
		}
		
		clearControls();
    }

    @FXML
    void deleteBtn() {
    	
    }

    @FXML
    void updateBtn() {

    }

    private void clearControls() {
		yearCombo.getSelectionModel().clearSelection();
		ueCombo.getSelectionModel().clearSelection();

		gcmField.setText("0");
		gtdField.setText("0");
		gtpField.setText("0");
		
		yearLabel.setText("ANNEE ACADEMIQUE	");
	 }
    
    
    private void tableViewData(Year year) {
    	groupTableView.getItems().clear();	
    	for(Group group: year.getGroups()) {
    		System.out.println(group.getUe().getLabel());
    		addCombinedData(group);
    	}
    }
    
    private void addCombinedData(Group group) {
		CombinedDataGroup combinedData = new CombinedDataGroup();
		combinedData.setCode(group.getUe().getCode());
		combinedData.setLabel(group.getUe().getLabel());
		combinedData.setNHoursCM(group.getUe().getNHoursCM());
	    combinedData.setNHoursTD(group.getUe().getNHoursTD());
	    combinedData.setNHoursTP(group.getUe().getNHoursTP());
	    combinedData.setNGroupsCM(group.getnGroupsCM());
	    combinedData.setNGroupsTD(group.getnGroupsTD());
	    combinedData.setNGroupsTP(group.getnGroupsTP());
	    combinedDataObsList.add(combinedData);
	}
}
