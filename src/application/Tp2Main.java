package application;
	
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Tp2Main extends Application {
	double x, y = 0;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
			primaryStage.setTitle("Hours Repartition Project");
//			primaryStage.initStyle(StageStyle.UNDECORATED);
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
		    fadeIn.setFromValue(0);
		    fadeIn.setToValue(1);
		
		    fadeIn.play();
			
			root.setOnMousePressed((e) -> {
				x = e.getSceneX();
				y = e.getSceneY();
			});
			
			root.setOnMouseDragged((e) -> {
				primaryStage.setX(e.getSceneX() - x);
				primaryStage.setY(e.getSceneY() - y);
			});
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
