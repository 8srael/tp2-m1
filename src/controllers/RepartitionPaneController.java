package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

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
import models.Teacher;
import models.Teacher_UE_Year;
import models.UE;
import models.Year;
import utils.CombinedDataAttribution;
import utils.GenericEntity;
import utils.JPAUtil;
import utils.Utils;

public class RepartitionPaneController implements Initializable {
	
	//  left = heures par groupe ; 
	// middle = Reste à distribuer
	// right - totale heures

	@FXML
    private JFXComboBox<UE> ueCombo;

    @FXML
    private JFXComboBox<Teacher> teacherCombo;

    @FXML
    private JFXComboBox<Year> yearCombo;

    @FXML
    private StackPane stackFieldCM;

    @FXML
    private TextField cmField;

    @FXML
    private StackPane stackFieldTD;
    
    @FXML
    private TextField tdField;


    @FXML
    private StackPane stackFieldTP;

    @FXML
    private TextField tpField;
    
    @FXML
    private Label leftCmLabel;

    @FXML
    private Label leftTdLabel;

    @FXML
    private Label leftTpLabel;

    @FXML
    private Label middleCmLabel;

    @FXML
    private Label middleTdLabel;

    @FXML
    private Label middleTpLabel;

    @FXML
    private Label rightCmLabel;

    @FXML
    private Label rightTdLabel;

    @FXML
    private Label rightTpLabel;
 
    @FXML
    private TableColumn<CombinedDataAttribution, String> fullNameColumn;

    @FXML
    private TableColumn<CombinedDataAttribution, Integer> cmColumn;

    @FXML
    private TableColumn<CombinedDataAttribution, Integer> tdColumn;

    @FXML
    private TableColumn<CombinedDataAttribution, Integer> tpColumn;
    
    @FXML
    private TableView<CombinedDataAttribution> recapTableView;

    
	private EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	// Generic Entity Instances
	private GenericEntity<UE> ueEntity = new GenericEntity<>(entityManager, UE.class);
	GenericEntity<Year> yearEntity = new GenericEntity<>(entityManager, Year.class);
	GenericEntity<Teacher_UE_Year> tuyEntity = new GenericEntity<Teacher_UE_Year>(entityManager, Teacher_UE_Year.class);
	
	// Entities ObservableList
	private ObservableList<CombinedDataAttribution> combinedDataObsList = FXCollections.observableArrayList();
	
	
	Alert alert;
	

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(stackFieldCM.getChildren().get(0));
		
		teacherCombo.setItems(Utils.getObsListTeacher());
		
//		System.out.println(Utils.getObsListUE());
		ueCombo.setItems(Utils.getObsListUE());
		yearCombo.setItems(Utils.getObsListYear());
		
		labelCenter(leftCmLabel, leftTdLabel, leftTpLabel, middleCmLabel, middleTdLabel, middleTpLabel, rightCmLabel, rightTdLabel, rightTpLabel);
				
		
		ueCombo.getSelectionModel().selectedItemProperty().addListener((obs, old, neww) -> {
			if(obs != null) {
				tableViewData(obs.getValue());
				System.out.println("In uecombo listener " + combinedDataObsList);
				recapTableView.setItems(combinedDataObsList);
				Year year = yearCombo.getSelectionModel().getSelectedItem();
				
				if(year != null) {
					UE ue = obs.getValue();
				
					Group group = ueEntity.getGroupByYearAndUe(ue, year);
					
					int totalCM = group.getUe().getnHoursCM() * group.getnGroupsCM();
					int totalTD = group.getUe().getnHoursTD() * group.getnGroupsTD();
					int totalTP = group.getUe().getnHoursTP() * group.getnGroupsTP();
					
					// CM,TD,TP par crew
					leftCmLabel.setText(group.getUe().getnHoursCM() + "");
					leftTdLabel.setText(group.getUe().getnHoursTD() + "");
					leftTpLabel.setText(group.getUe().getnHoursTP() + "");
					
					// CM, TD, TP total
					middleCmLabel.setText(totalCM + "");
					middleTdLabel.setText(totalTD + "");
					middleTpLabel.setText(totalTP +"");
					
					
					// CM, TD, TP Reste à distribuer
					rightCmLabel.setText(totalCM - getSumAttributionHoursByUE(ue)[0] + "");
					rightTdLabel.setText(totalTD - getSumAttributionHoursByUE(ue)[1] + "");
					rightTpLabel.setText(totalTP - getSumAttributionHoursByUE(ue)[2] + "");
					
					
					labelCenter(leftCmLabel, leftTdLabel, leftTpLabel, middleCmLabel, middleTdLabel, middleTpLabel, rightCmLabel, rightTdLabel, rightTpLabel);
					
					
				}
				
			}
		});
		
		
		// TableView treatment
		fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		cmColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursCMAss"));
		tdColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursTDAss"));
		tpColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursTPAss"));
		
	}

    @FXML
    void addBtn() {
    	if(cmField.getText().isEmpty() || tpField.getText().isEmpty()|| tdField.getText().isEmpty()
    			|| yearCombo.getSelectionModel().getSelectedItem() == null
    			|| ueCombo.getSelectionModel().getSelectedItem() == null
    			|| teacherCombo.getSelectionModel().getSelectedItem() == null){
    		
    		alert = new Alert(AlertType.WARNING);
			
			alert.setContentText("One textfield at least is empty");
			alert.show();
    	} else {
    		Teacher teacher  = teacherCombo.getSelectionModel().getSelectedItem();
    		UE ue  = ueCombo.getSelectionModel().getSelectedItem();
    		Year year  = yearCombo.getSelectionModel().getSelectedItem();
    		
    		Teacher_UE_Year teacher_ue_year = new Teacher_UE_Year();
    		teacher_ue_year.setYear(year);
    		teacher_ue_year.setUe(ue);
    		teacher_ue_year.setTeacher(teacher);
    		teacher_ue_year.setNHoursCMAss(Integer.parseInt(cmField.getText()));
    		teacher_ue_year.setNHoursTDAss(Integer.parseInt(tdField.getText()));
    		teacher_ue_year.setNHoursTPAss(Integer.parseInt(tpField.getText()));
    		
    		tuyEntity.create(teacher_ue_year);
    		
    		//tableview observableList updated
    		System.out.println("Teacher_UE_Year created : " + teacher_ue_year.getTeacher().getFirstName());
    		addCombinedData(teacher_ue_year);
    		
    		alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attribution information".toUpperCase());
			alert.setContentText("Attribution done successfully");
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
    
    private void tableViewData(UE ue) {
//    	this.recapTableView.getItems().clear();
    	if(ue != null) {
			for( Teacher_UE_Year tuy: ue.getTeacher_UE_Years()) {
	    		System.out.println(ue.getTeacher_UE_Years());
				addCombinedData(tuy);
			}
			
			System.out.println(combinedDataObsList);
    	}
    }
    
    private void labelCenter(Label ...labels) {
    	for(Label label: labels) {
//    		System.out.println(label.getText().length());
    		if(label.getText().length() == 1)
    			label.setLayoutX(24);
    		else
    			label.setLayoutX(20);
    	}
    }
    
    
    // add a element in combinesData
    private void addCombinedData(Teacher_UE_Year tuy) {
  		CombinedDataAttribution combinedData = new CombinedDataAttribution();
  		System.out.println("In add combined data");
  		combinedData.setFullName(tuy.getTeacher().getFirstName(), tuy.getTeacher().getLastName());
  		combinedData.setNHoursCMAss(tuy.getNHoursCMAss());
  		combinedData.setNHoursTDAss(tuy.getNHoursTDAss());
  		combinedData.setNHoursTPAss(tuy.getNHoursTPAss());
  		
  		System.out.println("Combine data list before : " +combinedDataObsList);
  	    combinedDataObsList.add(combinedData);
  	}
    
    // Sum of all attribution hours CM, TD, TP
    private int[] getSumAttributionHoursByUE(UE ue) {
    	int [] sum = new int[3];
    	int sumCM = 0, sumTD = 0, sumTP = 0;
  
    	for(Teacher_UE_Year tuy: ue.getTeacher_UE_Years()) {    		
    		sumCM += tuy.getNHoursCMAss();
    		sumTD += tuy.getNHoursTDAss();
    		sumTP += tuy.getNHoursTPAss();
    	}
    	sum[0] = sumCM; sum[1] = sumTD; sum[2] = sumTP;
    	
    	return sum;
      
    }
    
    public void clearControls() {
    	cmField.setText("0");
    	tdField.setText("0");
    	tpField.setText("0");
//    	leftCmLabel.setText("0");
//    	leftTdLabel.setText("0");
//    	leftTpLabel.setText("0");
//    	middleCmLabel.setText("0");
//    	middleTdLabel.setText("0");
//    	middleTpLabel.setText("0");
//    	rightCmLabel.setText("0");
//    	rightTdLabel.setText("0");
//    	rightTpLabel.setText("0");

    	
//    	yearCombo.getSelectionModel().clearSelection();
//    	ueCombo.getSelectionModel().clearSelection();
    	teacherCombo.getSelectionModel().clearSelection();
//    	recapTableView.getItems().clear();	
    }
    
}
