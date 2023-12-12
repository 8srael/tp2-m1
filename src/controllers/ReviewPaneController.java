package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Teacher;
import models.Teacher_UE_Year;
import models.Year;
import utils.CombinedDataReview;
import utils.Utils;

public class ReviewPaneController implements Initializable {
	
	@FXML
    private JFXComboBox<Teacher> teacherCombo;
	
	@FXML
	private JFXComboBox<Year> yearCombo;
	
	@FXML
    private Label totalCMLabel;

    @FXML
    private Label totalTDLabel;

    @FXML
    private Label totalTPLabel;
    
    
    @FXML
    private Label fullNameLabel;

    @FXML
    private TableView<CombinedDataReview> reviewTableview;

    @FXML
    private TableColumn<CombinedDataReview, String> ueColumn;

    @FXML
    private TableColumn<CombinedDataReview, Integer> cmColumn;

    @FXML
    private TableColumn<CombinedDataReview, Integer> tdColumn;

    @FXML
    private TableColumn<CombinedDataReview, Integer> tpColumn;
    
	private ObservableList<CombinedDataReview> combinedDataObsList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		yearCombo.setItems(Utils.getObsListYear());
		teacherCombo.setItems(Utils.getObsListTeacher());
		
		yearCombo.getSelectionModel().selectedItemProperty().addListener((obsY, oldY, newY)->{
			teacherCombo.getSelectionModel().clearSelection();
			fullNameLabel.setText("");
			teacherCombo.getSelectionModel().selectedItemProperty().addListener((obsT, oldT, newT)->{
				if(newT != null) {
					
					fullNameLabel.setText(newT.getFirstName() + ' ' + newT.getLastName());
					
					tableViewData(newT, newY);
					reviewTableview.setItems(combinedDataObsList);
					
					if((Number)Utils.getSum(newT, newY)[0] == null 
							|| (Number)Utils.getSum(newT, newY)[1] == null
								|| (Number)Utils.getSum(newT, newY)[2] == null) {
						System.out.println("ok");
						totalCMLabel.setText("0");
						totalTDLabel.setText("0");
						totalTPLabel.setText("0");
					} else {
						totalCMLabel.setText((Number)Utils.getSum(newT, newY)[0] + "");
						totalTDLabel.setText((Number)Utils.getSum(newT, newY)[1] + "");
						totalTPLabel.setText((Number)Utils.getSum(newT, newY)[2] + "");
					}
	
					Utils.labelCenter(totalCMLabel, totalTDLabel, totalTPLabel);
				}
			});
		});
		
		
		// TableView treatment
		ueColumn.setCellValueFactory(new PropertyValueFactory<>("ueLabel"));
		cmColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursCM"));
		tdColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursTD"));
		tpColumn.setCellValueFactory(new PropertyValueFactory<>("nHoursTP"));
	}
	
	
	private void tableViewData(Teacher teacher, Year year) {
    	this.reviewTableview.getItems().clear();
    	if(year != null && teacher != null) {
			for( Teacher_UE_Year tuy: Utils.getTuyByTeacherAndYear(teacher, year)) {
				addCombinedData(tuy);
			}
			
			System.out.println(combinedDataObsList);
    	}
    }
	
	private void addCombinedData(Teacher_UE_Year tuy) {
		CombinedDataReview combinedData = new CombinedDataReview();
		combinedData.setUeLabel(tuy.getUe().getLabel());
		combinedData.setNHoursCM(tuy.getNHoursCMAss());
		combinedData.setNHoursTD(tuy.getNHoursTDAss());
		combinedData.setNHoursTP(tuy.getNHoursTPAss());
		
  	    combinedDataObsList.add(combinedData);
	} 

}
