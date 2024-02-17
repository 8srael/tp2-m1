package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utils.Utils;

public class DashController implements Initializable {

	@FXML
	private Label allUeLabel;
	    
	@FXML
	private Label allTeacherLabel;
	    

	@FXML
	private Label closeCircle;

	
	@Override
	public void initialize(URL url, ResourceBundle ressouBundle) {
		
    	allTeacherLabel.textProperty().bind(Bindings.size(Utils.getObsListTeacher()).asString());
    	allUeLabel.textProperty().bind(Bindings.size(Utils.getObsListUE()).asString());
    	
    	closeCircle.setOnMouseClicked((e) -> Platform.exit());
		
	}

}
