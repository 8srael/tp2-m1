package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import utils.Utils;

public class HomeController implements Initializable {
	@FXML
    private Button homeBtn;
	
	@FXML
    private Button yearBtn;
	 
	@FXML
	private Button groupBtn;

    @FXML
    private Button teacherBtn;

    @FXML
    private Button ueBtn;

    @FXML
    private Button repartitionBtn;

    @FXML
    private Button reviewBtn;
    
    @FXML
    private Pane homePane;

    @FXML
    private StackPane parent_container;
    
    @FXML
    private Label yearLabel;
    
    @FXML
    private Label allTeacherLabel;

    @FXML
    private Label allUeLabel;


    
    private Pane teacherPane, uePane, repartitionPane, reviewPane, yearPane, groupPane;
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {	    
    	System.out.println(LocalDate.now());
    	allTeacherLabel.setText(Utils.getObsListTeacher().size() + "");

    	allUeLabel.setText(Utils.getObsListTeacher().size() + "");
    	
    	
    	// fxml files loading
    	try {
	    	teacherPane = FXMLLoader.load(getClass().getResource("/views/teacher_pane.fxml"));
			uePane = FXMLLoader.load(getClass().getResource("/views/ue_pane.fxml"));
			repartitionPane = FXMLLoader.load(getClass().getResource("/views/repartition_pane.fxml"));
			reviewPane = FXMLLoader.load(getClass().getResource("/views/review_pane.fxml"));
			yearPane = FXMLLoader.load(getClass().getResource("/views/year_pane.fxml"));
			groupPane = FXMLLoader.load(getClass().getResource("/views/group_pane.fxml"));
		

			// Add all fxml panes to home stackpane
			parent_container.getChildren().addAll(reviewPane, repartitionPane, uePane, teacherPane, yearPane, groupPane);
			
			toFront(homePane, teacherPane, uePane, repartitionPane, reviewPane, yearPane, groupPane);
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
    	if(event.getSource() == homeBtn) {
//    		load();
    		toFront(homePane, teacherPane, uePane, repartitionPane, reviewPane, yearPane, groupPane);
    	}
    	if(event.getSource() == teacherBtn) {
//    		load();
    		toFront(teacherPane, uePane, homePane, repartitionPane, reviewPane,  yearPane, groupPane);
    	}
    	if(event.getSource() == ueBtn) {
//			load();
    		toFront(uePane, homePane, teacherPane, repartitionPane, reviewPane, yearPane, groupPane);
    	}
    	if(event.getSource() == repartitionBtn) {    	
//			load();
    		toFront(repartitionPane, homePane, teacherPane, uePane, reviewPane, yearPane, groupPane);
    	}
    	if(event.getSource() == reviewBtn) {
//			load();
    		toFront(reviewPane, homePane, teacherPane, uePane, repartitionPane,  yearPane, groupPane);
    	}
    	if(event.getSource() == yearBtn) {
//			load();
    		toFront(yearPane, reviewPane, homePane, teacherPane, uePane, repartitionPane, groupPane);
    	}
    	if(event.getSource() == groupBtn) {
//			load();
    		toFront(groupPane, reviewPane, homePane, teacherPane, uePane, repartitionPane, yearPane);
    	}
    }
    
//    private void toFront(Pane pane) {
//    	if(pane == homePane) {
//    		teacherPane.setVisible(false);
//			uePane.setVisible(false);
//			homePane.setVisible(true);
//			homePane.toFront();
//    	}
//    	
//    	if(pane == teacherPane) {
//    		homePane.setVisible(false);
//			uePane.setVisible(false);
//			teacherPane.setVisible(true);
//			teacherPane.toFront();
//    	}
//    	
//    	if(pane == uePane) {
//    		homePane.setVisible(false);
//			teacherPane.setVisible(false);
//			uePane.setVisible(true);
//			uePane.toFront();
//    	}
//    	
//    }
    
    private void toFront(Pane ...panes) {
    	short i = 0;
    	for(Pane pane : panes) {
    		if(i == 0) {
    			pane.setVisible(true);
    			pane.toFront();
    		} else {
    			pane.setVisible(false);
    			pane.toBack();
    		}
    		++i;
    	}
    }
    
    private void load() {
    	try {
	    	teacherPane = FXMLLoader.load(getClass().getResource("/views/teacher_pane.fxml"));
			uePane = FXMLLoader.load(getClass().getResource("/views/ue_pane.fxml"));
			repartitionPane = FXMLLoader.load(getClass().getResource("/views/repartition_pane.fxml"));
			reviewPane = FXMLLoader.load(getClass().getResource("/views/review_pane.fxml"));
//			parent_container.getChildren().addAll(homePane, reviewPane, repartitionPane, uePane, teacherPane);

    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
}
