package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
    private StackPane parent_container;
    
    @FXML
    private Label yearLabel;
    
    
    private Pane teacherPane, uePane, repartitionPane, reviewPane, yearPane, groupPane, dashPane;
    
    private Button activeButton;
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {	    
    	System.out.println(LocalDate.now());
    	  	
    	
    	// fxml files loading
    	try {
			dashPane  = FXMLLoader.load(getClass().getResource("/views/dash.fxml"));
	    	teacherPane = FXMLLoader.load(getClass().getResource("/views/teacher_pane.fxml"));
			uePane = FXMLLoader.load(getClass().getResource("/views/ue_pane.fxml"));
			repartitionPane = FXMLLoader.load(getClass().getResource("/views/repartition_pane.fxml"));
			reviewPane = FXMLLoader.load(getClass().getResource("/views/review_pane.fxml"));
			yearPane = FXMLLoader.load(getClass().getResource("/views/year_pane.fxml"));
			groupPane = FXMLLoader.load(getClass().getResource("/views/group_pane.fxml"));
					

			// Add all fxml panes to home stackpane
			parent_container.getChildren().addAll(dashPane, reviewPane, repartitionPane, uePane, teacherPane, yearPane, groupPane);
			
			toFront(dashPane, teacherPane, uePane, repartitionPane, reviewPane, yearPane, groupPane);
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	// close button action
    }
    
    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
    	showPane((Button) event.getSource());
    	if(event.getSource() == homeBtn) {
//    		load();
//    		homePane = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
//    		parent_container.getChildren().add(dashPane);
    		toFront(dashPane, teacherPane, uePane, repartitionPane, reviewPane, yearPane, groupPane);
    	}
    	if(event.getSource() == teacherBtn) {
//    		load();
//	    	teacherPane = FXMLLoader.load(getClass().getResource("/views/teacher_pane.fxml"));
//	    	parent_container.getChildren().add(teacherPane);
    		toFront(teacherPane, uePane, dashPane, repartitionPane, reviewPane,  yearPane, groupPane);
    	}
    	if(event.getSource() == ueBtn) {
//			load();
//			uePane = FXMLLoader.load(getClass().getResource("/views/ue_pane.fxml"));
    		toFront(uePane, dashPane, teacherPane, repartitionPane, reviewPane, yearPane, groupPane);
    	}
    	if(event.getSource() == repartitionBtn) {    	
//			load();
//			repartitionPane = FXMLLoader.load(getClass().getResource("/views/repartition_pane.fxml"));
    		toFront(repartitionPane, dashPane, teacherPane, uePane, reviewPane, yearPane, groupPane);
    	}
    	if(event.getSource() == reviewBtn) {
//			load();
//			reviewPane = FXMLLoader.load(getClass().getResource("/views/review_pane.fxml"));
    		toFront(reviewPane, dashPane, teacherPane, uePane, repartitionPane,  yearPane, groupPane);
    	}
    	if(event.getSource() == yearBtn) {
//			load();
//			yearPane = FXMLLoader.load(getClass().getResource("/views/year_pane.fxml"));
    		toFront(yearPane, reviewPane, dashPane, teacherPane, uePane, repartitionPane, groupPane);
    	}
    	if(event.getSource() == groupBtn) {
//			load();
//			groupPane = FXMLLoader.load(getClass().getResource("/views/group_pane.fxml"));
    		toFront(groupPane, reviewPane, dashPane, teacherPane, uePane, repartitionPane, yearPane);
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
    
    private void showPane(Button button) {
    	if (activeButton != null)
            activeButton.getStyleClass().remove("active"); // Supprimer la classe CSS du bouton actif précédent
    
        activeButton = button;
        activeButton.getStyleClass().add("active"); // Ajouter la classe CSS au bouton actif actuel	
    }
}
