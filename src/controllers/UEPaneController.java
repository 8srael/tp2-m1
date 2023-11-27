package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

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
import utils.FXControlsUtils;
import utils.GenericEntity;
import utils.JPAUtil;
import utils.Utils;

public class UEPaneController implements Initializable {

 	@FXML
    private StackPane stackField1;

    @FXML
    private StackPane stackField2;

    @FXML
    private StackPane stackField3;

    @FXML
    private StackPane stackField4;

    @FXML
    private StackPane stackField5;

    @FXML
    private StackPane stackField6;
    
    @FXML
    private StackPane stackField7;

    @FXML
    private StackPane stackField8;

    @FXML
    private TextField libelleField;


    @FXML
    private TextField codeField;

    @FXML
    private TextField hcmField;

    @FXML
    private TextField gcmField;
    
    @FXML
    private TextField htdField;

    @FXML
    private TextField gtdField;

    @FXML
    private TextField htpField;
    
    @FXML
    private TextField gtpField;

    @FXML
    private TableView<CombinedDataGroup> ueTableView;

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

    Alert alert;
    private List<StackPane> stackFields = new ArrayList<>();
	    
    private EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	private GenericEntity<Group> groupEntity = new GenericEntity<>(entityManager, Group.class);
	private GenericEntity<UE> ueEntity = new GenericEntity<>(entityManager, UE.class);
	private GenericEntity<Year> yearEntity = new GenericEntity<>(entityManager, Year.class);
	private Year year = yearEntity.read(1);
	ObservableList<CombinedDataGroup> combinedDataObsList = FXCollections.observableArrayList();

	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		stackFields.add(stackField1);
		stackFields.add(stackField2);
		stackFields.add(stackField3);
		stackFields.add(stackField4);
		stackFields.add(stackField5);
		stackFields.add(stackField6);
		stackFields.add(stackField7);
		stackFields.add(stackField8);
		FXControlsUtils.textFieldAnimation(stackFields);
		
		
		
		//tableview backend treatment
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		libelleColumn.setCellValueFactory(new PropertyValueFactory<>("label"));
		hcmColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursCM"));
		gcmColumn.setCellValueFactory(new PropertyValueFactory<>("nGroupsCM"));
		htdColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursTD"));
		gtdColumn.setCellValueFactory(new PropertyValueFactory<>("nGroupsTD"));
		htpColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursTP"));
		gtpColumn.setCellValueFactory(new PropertyValueFactory<>("nGroupsTP"));
		

		tableViewData();
		ueTableView.setItems(combinedDataObsList);
	}
	
	@FXML
    void addBtn() {
		 if(codeField.getText().isEmpty() || libelleField.getText().isEmpty() || hcmField.getText().isEmpty() || htdField.getText().isEmpty() || htpField.getText().isEmpty()
				 || gcmField.getText().isEmpty() || gtdField.getText().isEmpty() || gtpField.getText().isEmpty()) {
			 alert = new Alert(AlertType.WARNING);
			
			alert.setContentText("One textfield at least is empty");
			alert.show();
		} else {
			
			// Ue storing
			UE ue = new UE(codeField.getText(), libelleField.getText(), Integer.parseInt(hcmField.getText()), Integer.parseInt(htdField.getText()), Integer.parseInt(htpField.getText()));
			ueEntity.create(ue);
			Utils.getObsListUE().add(ue);
			
			
			// Group storing
			Group group = new Group(ue, year, Integer.parseInt(gcmField.getText()),  Integer.parseInt(gtdField.getText()), Integer.parseInt(gtpField.getText()));
			groupEntity.create(group);
			
			//tableview updated
			addCombinedData(ue, group);
			
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("UE creation information".toUpperCase());
			alert.setContentText("UE created successfully");
			alert.show();
			clearControls();
			
		}
		
    }

    @FXML
    void deleteBtn() {
    	
    }

    @FXML
    void updateBtn() {

    }

    private void clearControls() {
		codeField.clear();
		libelleField.clear();
		hcmField.setText("0");
		htdField.setText("0");
		htpField.setText("0");
		gcmField.setText("0");
		gtdField.setText("0");
		gtpField.setText("0");
	 }
    
    
    private void tableViewData() {
    	for(UE ue: Utils.getUeEntity().all()) {
	    	for(Group group: ue.getGroups()) {
	    		addCombinedData(ue, group);
	    	}
    	}
    	
    }
    
    private void addCombinedData(UE ue, Group group) {
		CombinedDataGroup combinedData = new CombinedDataGroup();
		combinedData.setCode(ue.getCode());
		combinedData.setLabel(ue.getLabel());
		combinedData.setNHoursCM(ue.getnHoursCM());
	    combinedData.setNHoursTD(ue.getnHoursTD());
	    combinedData.setNHoursTP(ue.getnHoursTP());
	    combinedData.setNGroupsCM(group.getnGroupsCM());
	    combinedData.setNGroupsTD(group.getnGroupsTD());
	    combinedData.setNGroupsTP(group.getnGroupsTP());
	    combinedDataObsList.add(combinedData);
	}
}
