package utils;

import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class FXControlsUtils {
	
	public static void textFieldAnimation(List<StackPane> stackpanes) {
		for(StackPane stackpane: stackpanes) {
			TextField textField = (TextField) stackpane.getChildren().get(0);
			Label label = (Label) stackpane.getChildren().get(1);
			
			if(!textField.getText().isEmpty()) {
				label.setVisible(true);
				label.setTranslateY(-15);
				textField.setPromptText("");
			}
			
//            TranslateTransition transition = new TranslateTransition(Duration.millis(200), label);
	    	textField.focusedProperty().addListener((obs, oldValue, newValue) -> {
	            if(newValue) {
//	            	System.out.println("In the side");
	                 TranslateTransition transition = new TranslateTransition(Duration.millis(200), label);
	            	 label.setVisible(true);
	            	 label.setStyle("-fx-text-fill:#47b3e1;-fx-font-size:13px;-fx-font-family:SimSun;");
	            	 textField.setStyle("-fx-border-color: #47b3e1;");
	            	 textField.setPromptText("");
	            	 transition.setToY(-15);
	            	 transition.play();
	            } else {
//	            	System.out.println("In the otherside");
	                TranslateTransition transition = new TranslateTransition(Duration.millis(200), label);
	                transition.setOnFinished((e) -> {
	                	if (textField.getText().isEmpty()) {
	                		label.setVisible(false);
	                    	transition.setToY(0);
	   	            	 	textField.setStyle("-fx-border-color: #fc7499;");
		                	textField.setPromptText(label.getText());
	                    } else {
		                	label.setVisible(true);
	                    }
	                });
	                
	                if (textField.getText().isEmpty()) {
	                	label.setVisible(false);
                    	transition.setToY(0);
   	            	 	textField.setStyle("-fx-border-color: #fc7499;");
	                	textField.setPromptText(label.getText());
	                } else
	                	label.setVisible(false);
	                transition.play();
	           }
	        });
		}
    }
}
