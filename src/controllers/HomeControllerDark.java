package controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import models.Year;
import utils.GenericEntity;
import utils.JPAUtil;

public class HomeControllerDark implements Initializable {

    @FXML
    private Label lblClose;
    
    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;
    
    @FXML
    private Button enseignantBtn;

    @FXML
    private Button homeBtn;
    
  

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	
    	
        lblClose.setOnMouseClicked(e -> { // make a translation animation of the root
//        	FadeTransition fadeOut = new FadeTransition(Duration.millis(90), lblClose.getParent().getParent().getParent());
//		    fadeOut.setFromValue(1);
//		    fadeOut.setToValue(0);
//		    
//		    fadeOut.setOnFinished((event) -> System.exit(0));
//		    
//		    fadeOut.play();
        	Platform.exit();        	
        });

    
    }
    
    @FXML
    private void handleClicks(ActionEvent event) {
    	if(event.getSource() == homeBtn) {
    		pane1.toFront();
    	}
    	if(event.getSource() == enseignantBtn) {
    		pane2.toFront();
    	}
    }

}
